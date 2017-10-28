package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.ChatHistory;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/28/2017.
 */

public class UpdateChatHistoryCommand implements ICommand {

    private ChatHistory history;
    //private String gameID;

    public void execute()
    {
        ClientFacade.getClientFacade().updateChatHistory(history);
    }

    public UpdateChatHistoryCommand(ChatHistory history /* , String gameID*/) {
        this.history = history;
       // this.gameID = gameID;
    }

    public ChatHistory getHistory() {
        return history;
    }

    public void setHistory(ChatHistory history) {
        this.history = history;
    }

//    public String getGameID() {
//        return gameID;
//    }
//
//    public void setGameID(String gameID) {
//        this.gameID = gameID;
//    }
}
