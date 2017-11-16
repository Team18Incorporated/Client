package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.util.List;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateDestinationHandCommand implements ICommand {

    private List<DestinationCard> list;
    private String  className;

    @Override
    public String getSuffix() {
        return "InGameCommands.UpdateDestinationHand";
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateDestinationHand(list);
    }

    public UpdateDestinationHandCommand(java.util.List<DestinationCard> list) {
        this.list = list;
        className=getClass().getName();
    }

    public java.util.List<DestinationCard> getList() {
        return list;
    }

    public void setList(java.util.List<DestinationCard> list) {
        this.list = list;
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
