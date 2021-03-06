package edu.byu.cs.team18.tickettoride.Common.Commands.InGameCommands;

import java.util.ArrayList;

import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;
import edu.byu.cs.team18.tickettoride.Common.Route;
import edu.byu.cs.team18.tickettoride.Common.TrainCard;

/**
 * Created by Solomons on 10/31/2017.
 */

public class RemoveCardsCommand implements ICommand {
    private String color;
    private int length;
    private ArrayList<TrainCard> discard = new ArrayList<>();
    private String className;

    public  RemoveCardsCommand(Route route)
    {
        color= route.getColor().getColor();
        length=route.getLength();
        className=getClass().getName();
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }


    @Override
    public String getSuffix() {
        return "InGameCommands.RemoveCards";
    }

    @Override
    public void execute() {
        ArrayList<TrainCard> hand =ClientModel.SINGLETON.getCurrentPlayer().getHand();
       /* int numFound=0;
        for(TrainCard card : hand)
        {
            if(numFound<length)
            {
                if(card.getColor().getColor().equals(color))
                {
                    discard.add(card);
                    hand.remove(card);
                    numFound++;
                }
            }
        }
        if(numFound<length)
        {
            for(TrainCard card : hand)
            {
                if(numFound<length)
                {
                    if(card.getColor().getColor().equals("wild"))
                    {
                        discard.add(card);
                        //hand.remove(card);
                        numFound++;
                    }
                }
            }
            for (TrainCard card : discard)
            {

            }
        }*/

        hand.remove(0);
        hand.remove(0);
        ClientModel.SINGLETON.getCurrentPlayer().setHand(hand);
    }
}
