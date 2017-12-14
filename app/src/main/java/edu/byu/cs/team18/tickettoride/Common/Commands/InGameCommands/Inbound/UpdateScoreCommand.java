package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.PlayerInfo;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateScoreCommand implements ICommand {

    private int points;
    private String className;


    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateScore(points);
        String currentPlayer= ClientModel.SINGLETON.getCurrentPlayer().getPlayerID();

        for(PlayerInfo pi :ClientModel.SINGLETON.getCurrentGame().getPlayerList())
        {
            if(pi.getPlayerID().equals(currentPlayer))
            {
                pi.setPoints(points);
            }
        }
    }

    public UpdateScoreCommand(int points) {
        this.points = points;
        className=getClass().getName();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
