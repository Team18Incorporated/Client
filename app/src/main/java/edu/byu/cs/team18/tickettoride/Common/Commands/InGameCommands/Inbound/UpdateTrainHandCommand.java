package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands.Inbound;

import edu.byu.cs.team18.tickettoride.ClientFacade;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by abram on 10/20/2017.
 */

public class UpdateTrainHandCommand implements ICommand {

    private TrainCard card1;

    private String className;


    @Override
    public String getSuffix() {
        String suffix = this.getClass().toString();
        return suffix.substring(0,suffix.length() - 7);
    }

    @Override
    public void execute()
    {
        ClientFacade.getClientFacade().updateTrainHand(card1);
        //ClientModel.SINGLETON.getCurrentGame().addToGameHistory(this);
    }

    public UpdateTrainHandCommand(TrainCard card1, TrainCard card2) {
        this.card1 = card1;
        className=getClass().getName();
    }

    public TrainCard getCard1() {
        return card1;
    }

    public void setCard1(TrainCard card1) {
        this.card1 = card1;
    }


    @Override
    public String toString()
    {
        String name = ClientModel.SINGLETON.getCurrentPlayer().getPlayerName();
        int size = ClientModel.SINGLETON.getCurrentPlayer().getHand().size();
        return name + " now has " + size + " train cards.";
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }
}
