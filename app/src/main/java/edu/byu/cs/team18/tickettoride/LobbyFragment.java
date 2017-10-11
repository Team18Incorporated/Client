package edu.byu.cs.team18.tickettoride;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class LobbyFragment extends Fragment {

    private LobbyActivity activity;
    private View view;
    private Button start;

    public LobbyFragment() {
        // Required empty public constructor
    }


    /*
        Pulls data from LobbyPresenter and updates
        @Pre: none
        @Post: none
         */
    public void refreshView(){
        List<String> players = LobbyPresenter.instance.getPlayers();
        while (players.size()<5){
            players.add("Open");
        }
        TextView playerName = (TextView) view.findViewById(R.id.player1Name);
        playerName.setText(players.get(0));
        playerName = (TextView) view.findViewById(R.id.player2Name);
        playerName.setText(players.get(1));
        playerName = (TextView) view.findViewById(R.id.player3Name);
        playerName.setText(players.get(2));
        playerName = (TextView) view.findViewById(R.id.player4Name);
        playerName.setText(players.get(3));
        playerName = (TextView) view.findViewById(R.id.player5Name);
        playerName.setText(players.get(4));
    }

    /*
    Checks for minimum players, then launches GameActivity
    @Pre: none
    @Post: LobyPresenter.view = null;
     */
    public void startButtonPressed(){
        if (LobbyPresenter.instance.getPlayers().size()>1){
            //LobbyPresenter.instance.clearView();
            LobbyPresenter.instance.start();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lobby, container, false);
        refreshView();
        start = (Button) view.findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButtonPressed();
            }
        });
        LobbyPresenter.instance.setView(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (LobbyActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
}
