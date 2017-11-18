package edu.byu.cs.team18.tickettoride.States;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.AuthToken;
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
    public boolean claimRouteCheck() {
//        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
//        String gameID = ClientModel.SINGLETON.getCurrentGame().getGameID();
//        ServerProxy.getServerProxy().claimRoute(token,gameID, route);
//        ServerProxy.getServerProxy().endTurn(token,gameID,ClientModel.SINGLETON.getCurrentPlayer().getPlayerID());
//        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
        return true;
    }

    @Override
    public boolean drawFaceUp(int index) {
        //add implementation
        TrainCard card = ClientModel.SINGLETON.getCurrentGame().getVisibleCards().get(index);
        if (card.getColor().getColor().equals("wild"))
        {
            //is that right? rainbow?
            ServerProxy.getServerProxy().drawFromFaceUp(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                    ClientModel.SINGLETON.getCurrentGame().getGameID(), index);
            ServerProxy.getServerProxy().endTurn(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                    ClientModel.SINGLETON.getCurrentGame().getGameID(), ClientModel.SINGLETON.getCurrentPlayer().getPlayerID());
            ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
        }
        else
        {
            ServerProxy.getServerProxy().drawFromFaceUp(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), index);
            ClientModel.SINGLETON.setState(DrawCardState.SINGLETON);
        }
        return true;
    }

    @Override
    public boolean drawFromDeck() {
        //add implementation
        ServerProxy.getServerProxy().drawTrainCard(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID());
        ClientModel.SINGLETON.setState(DrawCardState.SINGLETON);
        return true;
    }

    @Override
    public boolean drawDestinationCards() {
        //add implementation
        ServerProxy.getServerProxy().drawDestinationCard(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID());
        ServerProxy.getServerProxy().endTurn(ClientModel.SINGLETON.getCurrentUser().getAuthToken(),
                ClientModel.SINGLETON.getCurrentGame().getGameID(), ClientModel.SINGLETON.getCurrentPlayer().getPlayerID());
        ClientModel.SINGLETON.setState(NotTurnState.SINGLETON);
        return true;
    }
}
