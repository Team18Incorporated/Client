package edu.byu.cs.team18.tickettoride.States;

import edu.byu.cs.team18.tickettoride.Common.Route;

/**
 * Created by abram on 11/11/2017.
 */

public class NotTurnState implements IState {
    private NotTurnState(){}
    private IState prev;
    public static NotTurnState SINGLETON = new NotTurnState();

    @Override
    public boolean claimRouteCheck() {
        return false;
    }

    @Override
    public boolean drawFaceUp(int index) {
        return false;
    }

    @Override
    public boolean drawFromDeck() {
        return false;
    }

    @Override
    public boolean drawDestinationCards() {
        return false;
    }

}
