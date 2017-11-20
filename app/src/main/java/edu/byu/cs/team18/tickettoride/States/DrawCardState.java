package edu.byu.cs.team18.tickettoride.States;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;
import edu.byu.cs.team18.tickettoride.ServerProxy;

/**
 * Created by abram on 11/11/2017.
 */

public class DrawCardState implements IState {
    private DrawCardState(){}
    public static DrawCardState SINGLETON = new DrawCardState();

    @Override
    public boolean claimRouteCheck() {
        return false;
    }

    @Override
    public boolean drawFaceUp(int index) {
        //add implementation
        TrainCard card = ClientModel.SINGLETON.getCurrentGame().getVisibleCards().get(index);
        if(card.getColor().getColor().equals("wild"))
        {
            return false;
        }
        ServerProxy.getServerProxy().drawFromFaceUp(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), index);
        ServerProxy.getServerProxy().endTurn(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), ClientModel.SINGLETON.getCurrentPlayer().getPlayerID());
        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
        return true;
    }

    @Override
    public boolean drawFromDeck() {
        //add implementation
        ServerProxy.getServerProxy().drawTrainCard(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID());
        ServerProxy.getServerProxy().endTurn(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), ClientModel.SINGLETON.getCurrentPlayer().getPlayerID());
        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
        return true;
    }

    @Override
    public boolean drawDestinationCards() {
        return false;
    }
}
