package edu.byu.cs.team18.tickettoride.Common.Commands;


import edu.byu.cs.team18.tickettoride.Common.AuthToken;

/**
 * Created by abram on 10/9/2017.
 */

public class UpdateUnstartedCommand implements ICommand {


    private AuthToken token;



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


    @Override
    public String getSuffix() {
         /*String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);*/
        return "UpdateUnstarted";
    }

    @Override
    public void execute(){}
}
