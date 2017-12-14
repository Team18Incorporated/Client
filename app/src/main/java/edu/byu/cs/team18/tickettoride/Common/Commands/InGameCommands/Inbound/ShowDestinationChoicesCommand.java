package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound;

import java.util.List;


import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;

/**
 * Created by abram on 10/22/2017.
 */

public class ShowDestinationChoicesCommand implements ICommand {
    private List<DestinationCard> list;
    private String className;

    @Override
    public String getSuffix() {
        return "IngameCommands.Inbound.ShowDestinationChoices";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().showDestinationCardChoices(list);
    }

    public ShowDestinationChoicesCommand(List<DestinationCard> list) {
        this.list = list;
        className=getClass().getName();
    }

    public List<DestinationCard> getList() {
        return list;
    }

    public void setList(List<DestinationCard> list) {
        this.list = list;
    }
}
