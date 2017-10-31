package edu.byu.cs.team18.tickettoride;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.DestinationCard;

/**
 * Created by Solomons on 10/30/2017.
 */

public class DSPInterface {

    public static DSPInterface instance = new DSPInterface();

    private DSPInterface(){}

    public List<DestinationCard> getCards()
    {
        return DestinationSelectPresenter.instance.getCards();
    }

    public boolean checkStartStatus()
    {
        return DestinationSelectPresenter.instance.checkStartStatus();
    }

    public void readyButtonClicked(List<DestinationCard> cards, List<DestinationCard> discard)
    {
        DestinationSelectPresenter.instance.readyButtonClicked(cards, discard);
    }
}
