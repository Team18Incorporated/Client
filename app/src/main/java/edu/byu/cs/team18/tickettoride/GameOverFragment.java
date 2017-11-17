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

import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;
import edu.byu.cs.team18.tickettoride.GameView.GameActivity;
import edu.byu.cs.team18.tickettoride.GameView.GamePresenter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameOverFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class GameOverFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private GameActivity activity;
    private View view;

    public GameOverFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_game_over, container, false);

        List<PlayerInfo> players = ClientModel.SINGLETON.getScores();
        for (int i=0; i<players.size(); i++){
            PlayerInfo player = players.get(i);
            //set player's name
            int id = getResources().getIdentifier("PlayerName"+(i+1), "id", this.getContext().getPackageName());
            TextView temp = (TextView) view.findViewById(id);
            temp.setText(player.getPlayerName());
            // set score
            id = getResources().getIdentifier("PlayerScore"+(i+1), "id", this.getContext().getPackageName());
            temp = (TextView) view.findViewById(id);
            temp.setText(player.getPoints());
            // set routes
            id = getResources().getIdentifier("PlayerRoutes"+(i+1), "id", this.getContext().getPackageName());
            temp = (TextView) view.findViewById(id);
            temp.setText(player.getDestinationsCompleted());
            // set cars
            id = getResources().getIdentifier("PlayerCars"+(i+1), "id", this.getContext().getPackageName());
            temp = (TextView) view.findViewById(id);
            int cars = 45-player.getNumTrainPieces();
            temp.setText(cars);
            // set penalties
            id = getResources().getIdentifier("PlayerPenalties"+(i+1), "id", this.getContext().getPackageName());
            temp = (TextView) view.findViewById(id);
            temp.setText(player.getPenalties());
            // set longest route
            id = getResources().getIdentifier("PlayerLongestRoute"+(i+1), "id", this.getContext().getPackageName());
            temp = (TextView) view.findViewById(id);
            if (player.hasLongestRoute()){temp.setVisibility(View.VISIBLE);}
        }

        Button exit = (Button) view.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.closeGame();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (GameActivity) context;
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
}
