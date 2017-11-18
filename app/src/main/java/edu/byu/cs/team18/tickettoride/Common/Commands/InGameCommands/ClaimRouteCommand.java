package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.Route;

/**
 * Created by abram on 10/20/2017.
 */

public class ClaimRouteCommand implements ICommand {

    private AuthToken authToken;
    private String gameID;
    private String playerID;
    private Route route;
    private ArrayList<Integer> discard;
    private String className;

    @Override
    public String getSuffix() {
        return "InGameCommands.ClaimRoute";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    public ClaimRouteCommand(AuthToken authToken, String gameID, Route route, ArrayList<Integer> discard) {
        this.authToken = authToken;
        this.gameID = gameID;
        this.route = route;
        this.discard=discard;
        className=getClass().getName();
    }

    public ClaimRouteCommand(){

    }

    //To be implemented differently on the server.
    @Override
    public void execute() {
        ClientFacade.getClientFacade().claimRoute(gameID, playerID, route);
        ClientModel.SINGLETON.getCurrentGame().addToGameHistory(this);
    }

    @Override
    public String toString()
    {
        String name = ClientFacade.getClientFacade().findPlayerName(playerID);
        return name + " claimed route: " + route.getCity1().getCityName() + ", " + route.getCity2().getCityName() + ".";
    }

    public ArrayList<Integer> getDiscard() {
        return discard;
    }

    public void setDiscard(ArrayList<Integer> discard) {
        this.discard = discard;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}