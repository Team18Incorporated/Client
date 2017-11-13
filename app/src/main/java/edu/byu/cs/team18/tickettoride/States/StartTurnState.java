package edu.byu.cs.team18.tickettoride.States;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;
import edu.byu.cs.team18.tickettoride.ServerProxy;

/**
 * Created by abram on 11/11/2017.
 */

public class StartTurnState implements IState {
    private StartTurnState(){}
    public static StartTurnState SINGLETON = new StartTurnState();

    @Override
    public void claimRoute(Route route) {
        ServerProxy.getServerProxy().claimRoute(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), route);
        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
    }

    @Override
    public void drawFaceUp(int index) {
        //add implementation
        TrainCard card = ClientModel.SINGLETON.getCurrentGame().getVisibleCards().get(index);
        if (card.getColor().getColor().equals("rainbow"))
        {
            //is that right? rainbow?
            ServerProxy.getServerProxy().drawFromFaceUp(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                    ClientModel.SINGLETON.getCurrentGame().getGameID(), index);
            ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
        }
        else
        {
            ServerProxy.getServerProxy().drawFromFaceUp(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), index);
            ClientModel.SINGLETON.setState(DrawCardState.SINGLETON);
        }
    }

    @Override
    public void drawFromDeck() {
        //add implementation
        ServerProxy.getServerProxy().drawTrainCard(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID());
        ClientModel.SINGLETON.setState(DrawCardState.SINGLETON);
    }

    @Override
    public void drawDestinationCards() {
        //add implementation
        ServerProxy.getServerProxy().drawDestinationCard(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID());
        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
    }
}
