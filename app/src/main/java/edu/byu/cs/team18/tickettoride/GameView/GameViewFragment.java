package edu.byu.cs.team18.tickettoride.GameView;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import edu.byu.cs.team18.tickettoride.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class GameViewFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    GameActivity activity;
    private View view;
    private ImageButton hand;
    private ImageButton deck;
    private ImageButton destinationDeck;

    public GameViewFragment() {
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
        view = inflater.inflate(R.layout.fragment_game_view, container, false);
        hand = (ImageButton) view.findViewById(R.id.hand);
        hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openHand();
            }
        });
        deck = (ImageButton) view.findViewById(R.id.cardDeck);
        deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.drawCard();
            }
        });
        //Todo: implement destinationDeck
        return view;
    }
    /*
    creates a new ImageView for a car at the specified location, and returns a pointer to the ImageView.
    Should be called by segment creation routine
    @pre: pos != null && is the position of the car on the map, angle!=null
    @post: ImageView is initialized, connected, and has its onclick listener set
     */
    public ImageView generateCar(Point pos, int angle){
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.car_clear);
        //set car position and add to xml
        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.gameViewLayout);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = pos.x;
        lp.topMargin = pos.y;
        rl.addView(iv, lp);
        //rotate car
        Matrix matrix = new Matrix();
        iv.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix.postRotate((float) angle, 0, 0);
        iv.setImageMatrix(matrix);
        //set onclick listener
        setCarClick(iv);
        return iv;
    }

    private void setCarClick(final ImageView car){
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.selectRoute(car);
            }
        });
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
        activity = (GameActivity) context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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