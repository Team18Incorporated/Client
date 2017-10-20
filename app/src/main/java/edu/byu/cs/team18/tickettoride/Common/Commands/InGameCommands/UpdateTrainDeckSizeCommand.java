package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateTrainDeckSizeCommand implements ICommand {

    private int size;

    public void execute()
    {
        ClientFacade.getClientFacade().updateTrainDeckSize( size);
    }


    public UpdateTrainDeckSizeCommand( int size) {
        this.size = size;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
