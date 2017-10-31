package edu.byu.cs.team18.tickettoride.Common.Commands;


import edu.byu.cs.team18.tickettoride.Common.AuthToken;

/**
 * Created by abram on 10/2/2017.
 */

public class JoinCommand implements ICommand{

    private String gameID;
    private AuthToken token;

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public JoinCommand(String ID, AuthToken t)
    {
        gameID = ID;
        token = t;
    }


    @Override
    public String getSuffix() {
         /*String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);*/
        return "Join";
    }

    @Override
    public void execute(){}

}
