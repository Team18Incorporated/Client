package edu.byu.cs.team18.tickettoride;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.DestinationCard;

/**
 * Created by Solomons on 11/14/2017.
 */

public class DestinationViewPresenter  {

    public static DestinationViewPresenter instance = new DestinationViewPresenter();
    private DestinationViewFragment view;


    private DestinationViewPresenter(){}

    public void setView(DestinationViewFragment view)
    {
        this.view=view;
    }

    public ArrayList<DestinationCard> getDestinationCards()
    {
        return ClientModel.SINGLETON.getCurrentPlayer().getDestinationCards();
    }
}
