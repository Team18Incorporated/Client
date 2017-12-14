package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateTrainDeckSizeCommand implements ICommand {

    private int size;
    private String className;

    @Override
    public String getSuffix()
    {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateTrainDeckSize( size);
    }


    public UpdateTrainDeckSizeCommand( int size) {
        this.size = size;
        className=getClass().getName();
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    public String toString()
    {
        return "There are now "+ Integer.toString(size) + "card(s) in the Train Card Deck";
    }
}
