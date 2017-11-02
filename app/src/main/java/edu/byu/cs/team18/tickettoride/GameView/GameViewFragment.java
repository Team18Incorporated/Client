package edu.byu.cs.team18.tickettoride.GameView;

import android.content.Context;
import android.graphics.Bitmap;
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

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.CardColor;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;
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
    private GameActivity activity;
    private View view;
    //private ImageButton hand;
    private ImageButton deck;
    private ImageButton hand;
    //private ImageButton destinationDeck;
    private Button testButton;
    private Button claim;
    private Boolean claimable = false;

    private ImageView faceUpCard1;
    private ImageView faceUpCard2;
    private ImageView faceUpCard3;
    private ImageView faceUpCard4;
    private ImageView faceUpCard5;

    private static int[] routeIDs = new int[] {
            R.id.r1s1,R.id.r1s2,R.id.r2s1,R.id.r2s2
    };

    public GameViewFragment() {
        // Required empty public constructor
    }

    public void refreshView(){
        //todo: implement refresh
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*
    sets the images for the faceUp cards
    @pre: there are exactly 5 face up cards
    @post: none
     */
    public void setFaceUpCards(){
        ArrayList<TrainCard> temp = ClientModel.SINGLETON.getCurrentGame().getVisibleCards();
        faceUpCard1.setImageResource(pickGraphic(temp.get(0).getColor()));
        faceUpCard2.setImageResource(pickGraphic(temp.get(1).getColor()));
        faceUpCard3.setImageResource(pickGraphic(temp.get(2).getColor()));
        faceUpCard4.setImageResource(pickGraphic(temp.get(3).getColor()));
        faceUpCard5.setImageResource(pickGraphic(temp.get(4).getColor()));
    }
    public int pickGraphic(CardColor color){
        int out = R.drawable.card_rainbow;
        switch (color.getColor()){
            case "black":
                out = R.drawable.card_black;
                break;
            case "blue":
                out = R.drawable.card_blue;
                break;
            case "green":
                out = R.drawable.card_green;
                break;
            case "orange":
                out = R.drawable.card_orange;
                break;
            case "purple":
                out = R.drawable.card_purple;
                break;
            case "wild":
                out = R.drawable.card_rainbow;
                break;
            case "red":
                out = R.drawable.card_red;
                break;
            case "white":
                out = R.drawable.card_white;
                break;
            case "yellow":
                out = R.drawable.card_yellow;
        }
        return out;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gameview, container, false);
        //set buttons
        deck = (ImageButton) view.findViewById(R.id.train_deck_button);
        deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.drawCard();
            }
        });
        hand = (ImageButton) view.findViewById(R.id.hand_button);
        hand.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        activity.openHand();
                                    }
                                }
        );
        claim = (Button) view.findViewById(R.id.claimRoute);
        claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (claimable){
                    GamePresenter.SINGLETON.claimRoute(ClientModel.SINGLETON.getCurrentRoute());
                }
            }
        });
        //initialize cards
        faceUpCard1 = (ImageView) view.findViewById(R.id.nfaceUpCard1);
        faceUpCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.drawFaceUpCard(0);
            }
        });
        faceUpCard2 = (ImageView) view.findViewById(R.id.nfaceUpCard2);
        faceUpCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.drawFaceUpCard(1);
            }
        });
        faceUpCard3 = (ImageView) view.findViewById(R.id.nfaceUpCard3);
        faceUpCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.drawFaceUpCard(2);
            }
        });
        faceUpCard4 = (ImageView) view.findViewById(R.id.nfaceUpCard4);
        faceUpCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.drawFaceUpCard(3);
            }
        });
        faceUpCard5 = (ImageView) view.findViewById(R.id.nfaceUpCard5);
        faceUpCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.drawFaceUpCard(4);
            }
        });
        setFaceUpCards();

        testButton=(Button) view.findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.incrementTest();
            }
        });
        GamePresenter.SINGLETON.setView(this);
        return view;
    }
    /*
    creates a new ImageView for a car at the specified location, and returns a pointer to the ImageView.
    Should be called by segment creation routine
    @pre: pos != null && is the position of the car on the map, angle!=null
    @post: ImageView is initialized, connected, and has its onclick listener set
     */
    public ImageView generateCar(Point pos, int angle, Route route){
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
        setCarClick(iv, route);
        return iv;
    }

    private void initializeRoutes(){
        ArrayList<Route> routes = ClientModel.SINGLETON.getCurrentGame().getMap().getRouteList();
        for (int i=0; i<routes.size(); i++){
            Route temp = routes.get(i);
            ArrayList<Integer> segments = new ArrayList<>();
            for (int j=0; j<temp.getLength(); j++){
                ImageView car = (ImageView) view.findViewById(routeIDs[i+j]);
                setCarClick(car,temp);
                segments.add(routeIDs[i+j]);
            }
            temp.setSegments(segments);
        }
    }

    private void setCarClick(final ImageView car, final Route route){
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (route.getOwnerID().equals(null)) {
                    GamePresenter.SINGLETON.selectRoute(route);
                }
            }
        });
    }

    public void toggleClaim(boolean in){
        if (in = true){
            claim.setVisibility(View.VISIBLE);
        }
        else {
            claim.setVisibility(View.INVISIBLE);
        }
        claimable = in;
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
}
