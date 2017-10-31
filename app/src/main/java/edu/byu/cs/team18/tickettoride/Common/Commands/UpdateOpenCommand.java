package edu.byu.cs.team18.tickettoride.Common.Commands;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;

/**
 * Created by abram on 10/2/2017.
 */

public class UpdateOpenCommand implements ICommand{

    private AuthToken authToken;

    public UpdateOpenCommand(AuthToken token)
    {
        authToken = token;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    @Override
    public String getSuffix() {
         /*String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);*/
        return "UpdateOpen";
    }

    @Override
    public void execute(){}
}
