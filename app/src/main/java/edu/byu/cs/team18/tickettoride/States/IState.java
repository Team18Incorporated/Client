package edu.byu.cs.team18.tickettoride.States;

import edu.byu.cs.team18.tickettoride.Common.Route;

/**
 * Created by abram on 11/11/2017.
 */

public interface IState {

    //public IState getSingleton();

    public void claimRoute(Route route);

    public void drawFaceUp(int index);

    public void drawFromDeck();

    public void drawDestinationCards();
}
