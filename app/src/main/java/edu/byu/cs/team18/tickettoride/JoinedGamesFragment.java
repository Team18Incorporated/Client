package edu.byu.cs.team18.tickettoride;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.Common.*;
import edu.byu.cs.team18.tickettoride.R;



public class JoinedGamesFragment extends Fragment {

    private RecyclerView joinedGamesRV;
    private RecyclerView startedGamesRV;
    private RecyclerView.Adapter joinedAdapter,startedAdapter;
    private RecyclerView.LayoutManager joinedLayoutManager,startedLayoutManager;
    private Button createButton;
    private Button joinButton;
    private GameList joinedGameList;
    private GameList startedGameList;
    private View view;
    private LobbyActivity activity;

    public JoinedGamesFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.joinedgames, container, false);
        refreshView();


        createButton= (Button)view.findViewById(R.id.create_Button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateButtonClicked();
            }
        });

        joinButton= (Button)view.findViewById(R.id.join_Button);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJoinButtonClicked();
            }
        });

        joinedGamesRV = (RecyclerView) view.findViewById(R.id.joined_recycler_view);
        startedGamesRV = (RecyclerView) view.findViewById(R.id.started_recycler_view);

        joinedLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        startedLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        joinedGamesRV.setLayoutManager(joinedLayoutManager);
        //joinedGamesRV.setHasFixedSize(true);
        startedGamesRV.setLayoutManager(startedLayoutManager);
        //startedGamesRV.setHasFixedSize(true);

        joinedAdapter = new GameInfoAdapter(joinedGameList);
        startedAdapter = new GameInfoAdapter(startedGameList);
        joinedGamesRV.setAdapter(joinedAdapter);
        startedGamesRV.setAdapter(startedAdapter);






        JoinedGamesPresenter.instance.setView(this);
        return view;
    }

    public void refreshView(){
        joinedGameList=JoinedGamesPresenter.instance.getJoinedGamesList();
        startedGameList=JoinedGamesPresenter.instance.getStartedGamesList();
        if(joinedGameList.getSize()>0)
        {
            joinedAdapter.notifyDataSetChanged();
        }
        if(startedGameList.getSize()>0)
        {
            startedAdapter.notifyDataSetChanged();
        }

    }

    private void onCreateButtonClicked()
    {
        JoinedGamesPresenter.instance.create();
    }

    private void onJoinButtonClicked()
    {
        detachPresenter();
        activity.openJoin();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (LobbyActivity) context;
    }
    /*
    detaches presenter from view in preparation for view closing
    @pre: none
    @post: none
     */
    private void detachPresenter(){
        JoinedGamesPresenter.instance.clearView();
    }

    public void joinLobby(){
        detachPresenter();
        activity.openLobby(ClientModel.SINGLETON.getCurrentLobby().getGameID());
    }

    //PRIVATE CLASSES
    //----------------------------------------------------------------------------------------------
    private class gamesViewHolder extends RecyclerView.ViewHolder
    {
        private TextView gameName;
        private TextView numPlayers;
        private Button joinButton;
        private GameInfo gameInfo;



        public gamesViewHolder(View itemView) {
            super(itemView);


            gameName=(TextView)itemView.findViewById(R.id.gameName);
            numPlayers=(TextView)itemView.findViewById(R.id.currentNumPlayers);
            joinButton=(Button)itemView.findViewById(R.id.play_button);
            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //NEED TO IMPLEMENT TRANSITION TO JOIN GAME
                    JoinedGamesPresenter.instance.joinGame(gameInfo.getGameID());
                }
            });

        }

        public void bindObject(GameInfo gameInfo)
        {
            this.gameInfo=gameInfo;

            gameName.setText(gameInfo.getGameName());
            numPlayers.setText(gameInfo.getNumPlayers());

        }


    }

    //----------------------------------------------------------------------------------------------
    private class GameInfoAdapter extends RecyclerView.Adapter<gamesViewHolder>
    {
        private GameList gameList;

        public GameInfoAdapter(GameList gameList)
        {
            this.gameList=gameList;
        }

        @Override
        public JoinedGamesFragment.gamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater= LayoutInflater.from(getActivity());
            View view= inflater.inflate(R.layout.game_view, parent, false);
            return new JoinedGamesFragment.gamesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(JoinedGamesFragment.gamesViewHolder holder, int position) {
           if(gameList.getSize()>0) {
               GameInfo gameInfo = gameList.getGame(position);
               holder.bindObject(gameInfo);
           }
        }

        @Override
        public int getItemCount() {
            return gameList.getSize();
        }
    }




}
