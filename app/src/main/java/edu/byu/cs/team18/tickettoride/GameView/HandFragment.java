package edu.byu.cs.team18.tickettoride.GameView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.Common.TrainCard;
import edu.byu.cs.team18.tickettoride.R;


public class HandFragment extends Fragment {

    private View view;
    private ArrayList<TrainCard> hand;
    private TextView numBlackText, numRedText, numBlueText, numGreenText, numYellowText, numPurpleText, numWhiteText, numOrangeText, numWildText;
    private int numBlack=0;
    private int numRed=0;
    private int numBlue=0;
    private int numGreen=0;
    private int numYellow=0;
    private int numPurple=0;
    private int numWhite=0;
    private int numOrange=0;
    private int numWild=0;


    public HandFragment() {
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
        view =inflater.inflate(R.layout.fragment_hand, container, false);


        hand = GamePresenter.SINGLETON.getHand();
        getNumColors();
        numBlackText=(TextView)view.findViewById(R.id.numBlackText);
        numBlackText.setText(numBlack);
        numBlueText=(TextView)view.findViewById(R.id.numBlueText);
        numBlueText.setText(numBlue);
        numRedText=(TextView)view.findViewById(R.id.numRedText);
        numRedText.setText(numRed);
        numGreenText=(TextView)view.findViewById(R.id.numGreenText);
        numGreenText.setText(numGreen);
        numYellowText=(TextView)view.findViewById(R.id.numYellowText);
        numYellowText.setText(numYellow);
        numPurpleText=(TextView)view.findViewById(R.id.numPurpleText);
        numPurpleText.setText(numPurple);
        numWhiteText=(TextView)view.findViewById(R.id.numWhiteText);
        numWhiteText.setText(numWhite);
        numOrangeText=(TextView)view.findViewById(R.id.numOrangeText);
        numOrangeText.setText(numOrange);
        numWildText=(TextView)view.findViewById(R.id.numWildText);
        numWildText.setText(numWild);


        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }




    private void getNumColors()
    {
        for(TrainCard c : hand)
        {
            if(c.getColor().getColor().equals("black"))
            {
                numBlack++;
            }
            else if(c.getColor().getColor().equals("blue"))
            {
                numBlue++;
            }
            else if(c.getColor().getColor().equals("red"))
            {
                numRed++;
            }
            else if(c.getColor().getColor().equals("green"))
            {
                numGreen++;
            }
            else if(c.getColor().getColor().equals("yellow"))
            {
                numYellow++;
            }
            else if(c.getColor().getColor().equals("purple"))
            {
                numPurple++;
            }
            else if(c.getColor().getColor().equals("white"))
            {
                numWhite++;
            }
            else if(c.getColor().getColor().equals("orange"))
            {
                numOrange++;
            }
            else if(c.getColor().getColor().equals("wild"))
            {
                numWild++;
            }
        }
    }
}
