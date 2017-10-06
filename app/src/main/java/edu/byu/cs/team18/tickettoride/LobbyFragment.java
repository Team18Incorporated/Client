package edu.byu.cs.team18.tickettoride;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LobbyFragment extends Fragment {

    private LobbyActivity activity;

    public LobbyFragment() {
        // Required empty public constructor
    }

    public void refreshView(){
        List<String> players = LobbyPresenter.instance.getPlayers();
        while (players.size()<5){
            players.add("Open");
        }
        TextView playerName = (TextView) findViewById(R.id.player1Name);
        playerName.setText(players.get(1));
        playerName = (TextView) findViewById(R.id.player1Name);
        playerName.setText(players.get(2));
        playerName = (TextView) findViewById(R.id.player1Name);
        playerName.setText(players.get(3));
        playerName = (TextView) findViewById(R.id.player1Name);
        playerName.setText(players.get(4));
        playerName = (TextView) findViewById(R.id.player1Name);
        playerName.setText(players.get(5));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lobby, container, false);
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
