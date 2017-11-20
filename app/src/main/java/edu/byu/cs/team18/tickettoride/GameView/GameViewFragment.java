package edu.byu.cs.team18.tickettoride.GameView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.CardColor;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;
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
    private ImageButton deck;
    private ImageButton hand;

    private Button testButton;
    private Button destinationDeckButton;
    private Button claim;
    private Boolean claimable = false;

    private ImageView faceUpCard1;
    private ImageView faceUpCard2;
    private ImageView faceUpCard3;
    private ImageView faceUpCard4;
    private ImageView faceUpCard5;

    private TextView trainDeckSize;
    private TextView playerTurn;
    private static int[] routeIDs = new int[] {
            R.id.r1s1,R.id.r1s2,
            R.id.r2s1,R.id.r2s2
    };

    public GameViewFragment() {
        // Required empty public constructor
    }

    public void refreshView(){

        trainDeckSize.setText(Integer.toString(ClientModel.SINGLETON.getCurrentGame().getNumTrainDeck()));
        destinationDeckButton.setText("DD:"+Integer.toString(ClientModel.SINGLETON.getCurrentGame().getNumDestinationDeck()));
        Route temp = ClientModel.SINGLETON.getCurrentRoute();
        if(temp != null) {
            for (Integer car : temp.getSegments()) {
                ImageView tempImg = (ImageView) view.findViewById(car);
                tempImg.setImageResource(R.drawable.car_selected);
            }
            if(ClientModel.SINGLETON.getLastRoute() != null){
                for (Integer car : ClientModel.SINGLETON.getLastRoute().getSegments()) {
                    ImageView tempImg = (ImageView) view.findViewById(car);
                    tempImg.setImageResource(R.drawable.car_clear);
                }
            }
        }
        //set color to select
        setFaceUpCards();
        setPlayerTurn();
    }

    public void setRouteColor (Route in){
        final ArrayList<Integer> segments = in.getSegments();
        final PlayerInfo player = ClientModel.SINGLETON.getCurrentGame().getPlayer(in.getOwnerID());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<segments.size(); i++){
                    ImageView temp = (ImageView) view.findViewById(segments.get(i));
                    switch (player.getColor()){
                        case RED:
                            temp.setImageResource(R.drawable.car_red);
                            break;
                        case BLUE:
                            temp.setImageResource(R.drawable.car_blue);
                            break;
                        case BLACK:
                            temp.setImageResource(R.drawable.car_black);
                            break;
                        case GREEN:
                            temp.setImageResource(R.drawable.car_green);
                            break;
                        case YELLOW:
                            temp.setImageResource(R.drawable.car_yellow);
                    }
                }
            }
        });

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
                    ClientModel.SINGLETON.setLastRoute(ClientModel.SINGLETON.getCurrentRoute());
                    //ClientModel.SINGLETON.setCurrentRoute(null);
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

        /*testButton=(Button) view.findViewById(R.id.oldtest_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePresenter.SINGLETON.incrementTest();
            }
        });*/

        initializeRoutes();
        trainDeckSize=(TextView)view.findViewById(R.id.numTrainDeck);
        playerTurn=(TextView) view.findViewById(R.id.turn_textView);
        setPlayerTurn();
        destinationDeckButton=(Button)view.findViewById(R.id.destination_button);
        destinationDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawDestinationCards();
            }
        });
        setDestinationNum();



        refreshView();
        GamePresenter.SINGLETON.setView(this);
        return view;
    }




    private void initializeRoutes(){
        ArrayList<Route> routes = ClientModel.SINGLETON.getCurrentGame().getMap().getRouteList();
        for (int i=0; i<100; i++){
            Route temp = routes.get(i);
            ArrayList<Integer> segments = new ArrayList<>();
            for (int j=0; j<temp.getLength(); j++){
                Log.d("car", i + " " + j);
                int id = getResources().getIdentifier("r"+(i+1)+"s"+(j+1), "id", this.getContext().getPackageName());
                ImageView car = (ImageView) view.findViewById(id);
                setCarClick(car,temp);
                segments.add(id);
            }
            temp.setSegments(segments);
        }
    }

    private void setCarClick(final ImageView car, final Route route){
        car.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (route.getOwnerID() == null) {
                    GamePresenter.SINGLETON.selectRoute(route);
                    return false;
                }
                else {
                    return false;
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


    public GameActivity getGameActivity()
    {
        return activity;
    }


    private void setPlayerTurn()
    {
        String playerTurnStr = GamePresenter.SINGLETON.getPlayerTurn();
        playerTurn.setText(playerTurnStr);
    }

    private void setDestinationNum()
    {
        destinationDeckButton.setText("DD: "+GamePresenter.SINGLETON.getDestinationNum());
    }

    private void drawDestinationCards()
    {
        GamePresenter.SINGLETON.drawDestinationCards();
    }
}
