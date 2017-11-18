package edu.byu.cs.team18.tickettoride;

import java.util.ArrayList;
import java.util.List;


import edu.byu.cs.team18.tickettoride.Common.DestinationCard;

/**
 * Created by Solomons on 10/30/2017.
 */

public class DestinationSelectPresenter {

    public static DestinationSelectPresenter instance = new DestinationSelectPresenter();




    private DestinationSelectPresenter(){}
    private DestinationSelectFragment view;


    public void setView(DestinationSelectFragment viewIn)
    {
        view=viewIn;
    }

    public List<DestinationCard> getCards()
    {
        return ClientModel.SINGLETON.getCurrentPlayer().getDestinationCardChoices();
    }

    public boolean checkStartStatus()
    {
        return ClientModel.SINGLETON.getCurrentGame().getStartStatus();
    }

    public void readyButtonClicked(List<DestinationCard> cards, List<DestinationCard> discard)
    {
        ClientModel.SINGLETON.getCurrentGame().getCurrentPlayer().getDestinationCards().addAll(cards);
        ServerProxy.getServerProxy().sendBackDestinations(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), discard);
    }
}
