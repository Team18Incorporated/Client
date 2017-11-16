package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.util.List;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by abram on 10/23/2017.
 */

public class DrawFromFaceUpCommand implements ICommand {
    private AuthToken authToken;
    private String gameID;
    private int card;
    private String className;

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public String getSuffix() {
        return "InGameCommands.DrawFromFaceUp";
    }

    @Override
    public void execute()
    {
        //To be implemented on the server side.
    }



    public DrawFromFaceUpCommand(AuthToken authToken, String gameID, int card) {
        this.card = card;
        this.gameID = gameID;
        this.authToken = authToken;
        className=getClass().getName();
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }


    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

}
