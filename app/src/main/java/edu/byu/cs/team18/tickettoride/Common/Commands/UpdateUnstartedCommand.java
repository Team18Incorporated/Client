package edu.byu.cs.team18.tickettoride.Common.Commands;


import edu.byu.cs.team18.tickettoride.Common.AuthToken;

/**
 * Created by abram on 10/9/2017.
 */

public class UpdateUnstartedCommand implements ICommand {


    private AuthToken token;
    private String suffix = "UpdateUnstarted";

    @Override
    public String getSuffix() {
        return suffix;
    }



    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public UpdateUnstartedCommand(AuthToken t)
    {
        token = t;
    }
}