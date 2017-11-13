package edu.byu.cs.team18.tickettoride.States;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.ServerProxy;

/**
 * Created by abram on 11/11/2017.
 */

public class DrawCardState implements IState {
    private DrawCardState(){}
    public static DrawCardState SINGLETON = new DrawCardState();

    @Override
    public void claimRoute(Route route) {

    }

    @Override
    public void drawFaceUp(int index) {
        //add implementation
        ServerProxy.getServerProxy().drawFromFaceUp(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), index);
        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
    }

    @Override
    public void drawFromDeck() {
        //add implementation
        ServerProxy.getServerProxy().drawTrainCard(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID());
        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
    }

    @Override
    public void drawDestinationCards() {

    }
}
