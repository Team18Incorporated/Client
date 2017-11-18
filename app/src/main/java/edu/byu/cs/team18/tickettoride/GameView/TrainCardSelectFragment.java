package edu.byu.cs.team18.tickettoride.GameView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.R;

/**
 * Created by Solomons on 11/14/2017.
 */

public class TrainCardSelectFragment extends Fragment  implements
        AdapterView.OnItemSelectedListener {

    private Route route;
    private TextView city1text, city2text, colortext, lengthtext;
    //private TextView numRed,numBlack,numBlue,numGreen,numYellow,numPurple,numWhite,numOrange,numWild;
    private Spinner redSpinner,blackSpinner,blueSpinner,greenSpinner,yellowSpinner,purpleSpinner,whiteSpinner,orangeSpinner,wildSpinner;
    private Button doneButton;
    private Button cancelButton;
    private View view;
    private String[] redList, blackList, blueList, greenList, purpleList, whiteList, yellowList, orangeList, wildList;
    private int numRed,numBlack,numBlue,numGreen,numYellow,numPurple,numWhite,numOrange,numWild;
    private int redSelected=0, blackSelected=0, blueSelected=0, greenSelected=0, yellowSelected=0, purpleSelected=0, whiteSelected=0, orangeSelected=0, wildSelected=0;
    private ArrayAdapter<CharSequence> adapterRed, adapterBlack,adapterBlue,adapterGreen, adapterYellow, adapterPurple, adapterWhite, adapterOrange, adapterWild;






    public TrainCardSelectFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        route=TrainCardSelectPresenter.SINGLETON.getRoute();
        view =inflater.inflate(R.layout.card_color_select, container, false);
        doneButton=(Button)view.findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoneButtonClicked();
            }
        });
        cancelButton=(Button)view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelButtonClicked();
            }
        });

        city1text= (TextView)view.findViewById(R.id.route_city1_text);
        city1text.setText(route.getCity1().getCityName());
        city2text=(TextView)view.findViewById(R.id.route_city2_text);
        city2text.setText(route.getCity2().getCityName());
        colortext=(TextView)view.findViewById(R.id.route_color_text);
        colortext.setText(route.getColor().getColor());
        lengthtext=(TextView)view.findViewById(R.id.route_length_text);
        lengthtext.setText(Integer.toString(route.getLength()));


        createSpinnerLists();

        createSpinners();

        TrainCardSelectPresenter.SINGLETON.setView(this);

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        //NEEDS TESTING
        switch(arg0.getId()){
            case R.id.red_spinner :
                redSelected=Integer.parseInt(redSpinner.getSelectedItem().toString());
                break;
            case R.id.black_spinner :
                blackSelected=Integer.parseInt(blackSpinner.getSelectedItem().toString());
                break;
            case R.id.blue_spinner :
                blueSelected=Integer.parseInt(blueSpinner.getSelectedItem().toString());
                break;
            case R.id.green_spinner :
                greenSelected=Integer.parseInt(greenSpinner.getSelectedItem().toString());
                break;
            case R.id.yellow_spinner :
                yellowSelected=Integer.parseInt(yellowSpinner.getSelectedItem().toString());
                break;
            case R.id.purple_spinner :
                purpleSelected=Integer.parseInt(purpleSpinner.getSelectedItem().toString());
                break;
            case R.id.orange_spinner :
                orangeSelected=Integer.parseInt(orangeSpinner.getSelectedItem().toString());
                break;
            case R.id.white_spinner :
                whiteSelected=Integer.parseInt(whiteSpinner.getSelectedItem().toString());
                break;
            case R.id.wild_spinner :
                wildSelected=Integer.parseInt(wildSpinner.getSelectedItem().toString());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void onDoneButtonClicked()
    {
        if (TrainCardSelectPresenter.SINGLETON.claimRouteCanDo(route, redSelected, blackSelected,
                blueSelected, greenSelected, yellowSelected, purpleSelected, whiteSelected, orangeSelected, wildSelected))
        {
            ArrayList<Integer> numCardsList= new ArrayList<>();
            numCardsList.add(redSelected);
            numCardsList.add(blackSelected);
            numCardsList.add(blueSelected);
            numCardsList.add(greenSelected);
            numCardsList.add(yellowSelected);
            numCardsList.add(purpleSelected);
            numCardsList.add(whiteSelected);
            numCardsList.add(orangeSelected);
            numCardsList.add(wildSelected);
            TrainCardSelectPresenter.SINGLETON.claimRoute(route,numCardsList);
        }
        else
        {
            Toast.makeText(getActivity(), "Bad Card Selection", Toast.LENGTH_SHORT).show();
        }
    }

    private void onCancelButtonClicked()
    {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private void createSpinners()
    {
        redSpinner=(Spinner)view.findViewById(R.id.red_spinner);
        redSpinner.setOnItemSelectedListener(this);
        adapterRed =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,redList);
        adapterRed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        redSpinner.setAdapter(adapterRed);

        blackSpinner=(Spinner)view.findViewById(R.id.black_spinner);
        blackSpinner.setOnItemSelectedListener(this);
        adapterBlack =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,blackList);
        adapterRed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blackSpinner.setAdapter(adapterBlack);

        blueSpinner=(Spinner)view.findViewById(R.id.blue_spinner);
        blueSpinner.setOnItemSelectedListener(this);
        adapterBlue =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,blueList);
        adapterBlue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blueSpinner.setAdapter(adapterBlue);

        greenSpinner=(Spinner)view.findViewById(R.id.green_spinner);
        greenSpinner.setOnItemSelectedListener(this);
        adapterGreen =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,greenList);
        adapterGreen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        greenSpinner.setAdapter(adapterGreen);

        yellowSpinner=(Spinner)view.findViewById(R.id.yellow_spinner);
        yellowSpinner.setOnItemSelectedListener(this);
        adapterYellow =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,yellowList);
        adapterYellow.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yellowSpinner.setAdapter(adapterYellow);

        purpleSpinner=(Spinner)view.findViewById(R.id.purple_spinner);
        purpleSpinner.setOnItemSelectedListener(this);
        adapterPurple =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,purpleList);
        adapterPurple.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        purpleSpinner.setAdapter(adapterPurple);

        whiteSpinner=(Spinner)view.findViewById(R.id.white_spinner);
        whiteSpinner.setOnItemSelectedListener(this);
        adapterWhite =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,whiteList);
        adapterWhite.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whiteSpinner.setAdapter(adapterWhite);

        orangeSpinner=(Spinner)view.findViewById(R.id.orange_spinner);
        orangeSpinner.setOnItemSelectedListener(this);
        adapterOrange =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,orangeList);
        adapterOrange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orangeSpinner.setAdapter(adapterOrange);

        wildSpinner=(Spinner)view.findViewById(R.id.wild_spinner);
        wildSpinner.setOnItemSelectedListener(this);
        adapterWild =    new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_item,wildList);
        adapterWild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wildSpinner.setAdapter(adapterWild);
    }

    private void createSpinnerLists()
    {
        ArrayList<Integer> numCards=TrainCardSelectPresenter.SINGLETON.getCardLists();
        numBlack=numCards.get(0);
        numRed=numCards.get(1);
        numBlue=numCards.get(2);
        numGreen=numCards.get(3);
        numYellow=numCards.get(4);
        numPurple=numCards.get(5);
        numWhite=numCards.get(6);
        numOrange=numCards.get(7);
        numWild=numCards.get(8);

        ArrayList<String> temp= new ArrayList<>();

        for(int i=0; i<=numBlack; i++)
        {
           temp.add(Integer.toString(i));
        }
        blackList=temp.toArray(new String[temp.size()]);
        temp.clear();

        for(int i=0; i<=numRed; i++)
        {
            temp.add(Integer.toString(i));
        }
        redList=temp.toArray(new String[temp.size()]);
        temp.clear();

        for(int i=0; i<=numBlue; i++)
        {
            temp.add(Integer.toString(i));
        }
        blueList=temp.toArray(new String[temp.size()]);
        temp.clear();

        for(int i=0; i<=numGreen; i++)
        {
            temp.add(Integer.toString(i));
        }
        greenList=temp.toArray(new String[temp.size()]);
        temp.clear();

        for(int i=0; i<=numYellow; i++)
        {
            temp.add(Integer.toString(i));
        }
        yellowList=temp.toArray(new String[temp.size()]);
        temp.clear();

        for(int i=0; i<=numPurple; i++)
        {
            temp.add(Integer.toString(i));
        }
        purpleList=temp.toArray(new String[temp.size()]);
        temp.clear();

        for(int i=0; i<=numWhite; i++)
        {
            temp.add(Integer.toString(i));
        }
        whiteList=temp.toArray(new String[temp.size()]);
        temp.clear();

        for(int i=0; i<=numOrange; i++)
        {
            temp.add(Integer.toString(i));
        }
        orangeList=temp.toArray(new String[temp.size()]);
        temp.clear();

        for(int i=0; i<=numWild; i++)
        {
            temp.add(Integer.toString(i));
        }
        wildList=temp.toArray(new String[temp.size()]);
        temp.clear();

    }
}
