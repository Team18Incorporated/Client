package edu.byu.cs.team18.tickettoride.Common.Commands;

import com.example.abram.phase1main.ModelClasses.AuthToken;

/**
 * Created by abram on 10/2/2017.
 */

public class CreateCommand implements ICommand{

    private String username;
    private AuthToken token;
    private String suffix = "Create";

    @Override
    public String getSuffix()
    {return suffix;}

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


}
