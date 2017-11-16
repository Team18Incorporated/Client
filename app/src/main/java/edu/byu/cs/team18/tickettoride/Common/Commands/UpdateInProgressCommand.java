package edu.byu.cs.team18.tickettoride.Common.Commands;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;

/**
 * Created by abram on 10/9/2017.
 */

public class UpdateInProgressCommand implements ICommand{
    private AuthToken token;



    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public UpdateInProgressCommand(AuthToken t)
    {

        token = t;
    }


    @Override
    public String getSuffix() {
         /*String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);*/
        return "UpdateInProgress";
    }

    @Override
    public void execute(){}

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
