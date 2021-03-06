package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.ChatMessage;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.ServerProxy;

/**
 * Created by abram on 10/28/2017.
 */

public class SendChatCommand implements ICommand {

    private ChatMessage message;
    private AuthToken authToken;
    private String gameID;
    private String className;

    @Override
    public String getSuffix() {
       /* String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);*/
        return "InGameCommands.SendChat";
    }

    @Override
    public void execute() {
        //The server will do this.
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    public SendChatCommand(ChatMessage message, AuthToken token, String gameID) {
        this.message = message;
        this.authToken = token;
        this.gameID=gameID;
        className=getClass().getName();
        System.out.println(className);
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
