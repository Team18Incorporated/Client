package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.util.List;


import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.DestinationCard;

/**
 * Created by abram on 10/22/2017.
 */

public class ShowDestinationChoicesCommand implements ICommand {
    List<DestinationCard> list;

    @Override
    public String getSuffix() {
        return "InGameCommands.ShowDestinationChoices";
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().showDestinationCardChoices(list);
    }

    public ShowDestinationChoicesCommand(List<DestinationCard> list) {
        this.list = list;
    }

    public List<DestinationCard> getList() {
        return list;
    }

    public void setList(List<DestinationCard> list) {
        this.list = list;
    }
}
