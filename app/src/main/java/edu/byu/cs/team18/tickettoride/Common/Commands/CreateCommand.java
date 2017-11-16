package edu.byu.cs.team18.tickettoride.Common.Commands;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;

/**
 * Created by abram on 10/2/2017.
 */

public class CreateCommand implements ICommand{

    private String username;
    private AuthToken token;


    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CreateCommand(String un, AuthToken t)
    {
        username = un;
        token = t;
    }


    @Override
    public String getSuffix() {
        /*String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);*/
        return "Create";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public void execute(){}
}
