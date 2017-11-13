package edu.byu.cs.team18.tickettoride.States;

import edu.byu.cs.team18.tickettoride.Common.Route;

/**
 * Created by abram on 11/11/2017.
 */

public class NotTurnState implements IState {
    private NotTurnState(){}
    public static NotTurnState SINGLETON = new NotTurnState();

    @Override
    public void claimRoute(Route route) {

    }

    @Override
    public void drawFaceUp(int index) {

    }

    @Override
    public void drawFromDeck() {

    }

    @Override
    public void drawDestinationCards() {

    }
}
