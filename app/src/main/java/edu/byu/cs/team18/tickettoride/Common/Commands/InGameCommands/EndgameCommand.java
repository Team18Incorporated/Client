package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;

/**
 * Created by Antman 537 on 11/20/2017.
 */

public class EndgameCommand implements ICommand {

    private ArrayList<PlayerInfo> playerList;
    private String className = getClass().getName();

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void execute() {
        ClientModel.SINGLETON.getCurrentGame().setPlayerList(playerList);
        ClientFacade.getClientFacade().endgame();
    }
}
