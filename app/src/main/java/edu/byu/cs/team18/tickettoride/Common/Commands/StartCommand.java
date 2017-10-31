package edu.byu.cs.team18.tickettoride.Common.Commands;

/**
 * Created by abram on 10/2/2017.
 */

public class StartCommand implements ICommand{

    private String gameID;

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



    @Override
    public String getSuffix() {
        /*String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);*/
        return "Start";
    }

    @Override
    public void execute(){}
}
