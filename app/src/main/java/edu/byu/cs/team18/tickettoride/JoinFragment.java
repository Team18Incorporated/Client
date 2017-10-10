package edu.byu.cs.team18.tickettoride;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.byu.cs.team18.tickettoride.Common.*;
import edu.byu.cs.team18.tickettoride.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JoinFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JoinFragment} factory method to
 * create an instance of this fragment.
 */
public class JoinFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView joinableGamesRV;
    private RecyclerView.Adapter joinableAdapter;
    private RecyclerView.LayoutManager joinableLayoutManager;
    private Button joinButton;
    private GameList joinableGameList;
    private View view;
    private LobbyActivity activity;

    public JoinFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_join, container, false);
        refreshView();

        joinableGamesRV = (RecyclerView) view.findViewById(R.id.join_recycler_view);

        joinableLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        joinableGamesRV.setLayoutManager(joinableLayoutManager);

        joinableAdapter = new GameInfoAdapter(joinableGameList);
        joinableGamesRV.setAdapter(joinableAdapter);

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        activity = (LobbyActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void refreshView(){
        joinableGameList=JoinPresenter.instance.getJoinableGamesList();
        joinableAdapter.notifyDataSetChanged();
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
                    //NEED TO IMPLEMENT TRANSITION TO Lobby GAME
                    try {
                        JoinPresenter.instance.joinGame(gameInfo.getGameID());
                    }
                    catch (Exception e){

                    }
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
    private class GameInfoAdapter extends RecyclerView.Adapter<JoinFragment.gamesViewHolder>
    {
        private GameList gameList;

        public GameInfoAdapter(GameList gameList)
        {
            this.gameList=gameList;
        }

        @Override
        public JoinFragment.gamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater= LayoutInflater.from(getActivity());
            View view= inflater.inflate(R.layout.game_view, parent, false);
            return new JoinFragment.gamesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(JoinFragment.gamesViewHolder holder, int position) {
            GameInfo gameInfo= gameList.getGame(position);
            holder.bindObject(gameInfo);
        }

        @Override
        public int getItemCount() {
            return gameList.getSize();
        }
    }
}
