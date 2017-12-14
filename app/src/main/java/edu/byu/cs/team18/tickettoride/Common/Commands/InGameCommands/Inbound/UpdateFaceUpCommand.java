package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound;

import java.util.List;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by abram on 10/22/2017.
 */

public class UpdateFaceUpCommand implements ICommand {
    private List<TrainCard> list;
    private final String className;
    public UpdateFaceUpCommand(List<TrainCard> list) {

        this.list = list;
        className=getClass().getName();
    }

    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    //This command will execute on both the client and the server. This is its implementation on the client side.
    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateFaceUp(list);
    }

    public String toString()
    {
        return "Changes made to Face Up Cards";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

}
