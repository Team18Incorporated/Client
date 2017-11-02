package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateDestinationDeckSizeCommand implements ICommand {
    private int size;


    @Override
    public String getSuffix() {
        return "InGameCommands.UpdateDestinationDeckSize";
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateDestinationDeckSize(size);
    }

    public UpdateDestinationDeckSizeCommand(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
