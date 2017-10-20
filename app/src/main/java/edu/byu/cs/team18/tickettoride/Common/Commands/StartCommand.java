package edu.byu.cs.team18.tickettoride.Common.Commands;

/**
 * Created by abram on 10/2/2017.
 */

public class StartCommand implements ICommand{

    private String gameID;
    private String suffix = "Start";

    public String getSuffix() {
        return suffix;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public StartCommand(String ID)
    {
        gameID = ID;
    }



    public void execute(){}
}
