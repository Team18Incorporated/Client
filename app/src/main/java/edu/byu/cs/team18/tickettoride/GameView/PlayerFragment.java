package edu.byu.cs.team18.tickettoride.GameView;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.ClientGame;
import edu.byu.cs.team18.tickettoride.Common.Player;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;
import edu.byu.cs.team18.tickettoride.R;

public class PlayerFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private TextView name;
    private TextView score;
    private TextView cars;
    private TextView handSize;
    private String nullText = "";
    private Button backButton;



    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void displayPlayer(PlayerInfo player){
        if (player != null) {
            name.setText(player.getPlayerName());
            score.setText(Integer.toString(player.getPoints()));
            cars.setText(Integer.toString(player.getNumTrainPieces()));
            handSize.setText(Integer.toString(player.getNumTrainCards()));
        }
        else{
            name.setText(nullText);
            score.setText(nullText);
            cars.setText(nullText);
            handSize.setText(nullText);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_player, container, false);
        name = (TextView) view.findViewById(R.id.name);
        score = (TextView) view.findViewById(R.id.score);
        cars = (TextView) view.findViewById(R.id.cars);
        handSize = (TextView) view.findViewById(R.id.handSize);

        backButton=(Button)view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backPressed();
            }
        });

        final String[] spinnerPlayers = getResources().getStringArray((R.array.players));
        Spinner spinner = (Spinner) view.findViewById(R.id.playerSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            ClientGame game = ClientModel.SINGLETON.getCurrentGame();
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>=game.getPlayerList().size()) {
                    displayPlayer(null);
                }
                else {
                    displayPlayer(game.getPlayerList().get(position));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Must be orverridden but we don't use it.
                ClientGame game = ClientModel.SINGLETON.getCurrentGame();
                displayPlayer(game.getPlayerList().get(0));
            }
        });
        spinner.setSelection(getIndex(spinner, GamePresenter.SINGLETON.getCurrentPlayerColor()));

        return view;
    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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


    private void backPressed()
    {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
