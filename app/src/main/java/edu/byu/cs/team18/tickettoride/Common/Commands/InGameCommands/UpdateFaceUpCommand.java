package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

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

    //This command will execute on both the client and the server. This is its implementation on the client side.
    public void execute()
    {
        ClientFacade.getClientFacade().updateFaceUp(list);
    }


}
