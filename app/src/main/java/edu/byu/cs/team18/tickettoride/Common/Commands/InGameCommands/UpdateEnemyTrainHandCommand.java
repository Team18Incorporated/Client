package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateEnemyTrainHandCommand implements ICommand {
    private String playerID;
    private int size;
    private String  className;


    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateEnemyTrainHand(playerID,size);
        ClientModel.SINGLETON.getCurrentGame().addToGameHistory(this);
    }

    @Override
    public String toString()
    {
        String name = ClientFacade.getClientFacade().findPlayerName(playerID);
        return name + " now has " + size + " train cards.";
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public UpdateEnemyTrainHandCommand(String playerID, int size) {
        this.playerID = playerID;
        this.size = size;
        className=getClass().getName();
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
