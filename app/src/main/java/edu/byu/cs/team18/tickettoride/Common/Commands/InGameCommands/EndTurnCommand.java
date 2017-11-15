package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 11/15/2017.
 */

public class EndTurnCommand implements ICommand {

    private AuthToken token;
    private String gameID;
    private String playerID;

    @Override
    public String getSuffix() {
        return "EndTurn";
    }

    @Override
    public void execute() {

    }

    public EndTurnCommand(AuthToken t, String g, String p)
    {
        token = t;
        gameID = g;
        playerID = p;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
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
}
