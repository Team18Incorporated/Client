package edu.byu.cs.team18.tickettoride.States;

import edu.byu.cs.team18.tickettoride.Common.Route;

/**
 * Created by abram on 11/11/2017.
 */

public interface IState {

    //public IState getSingleton();

    public boolean claimRouteCheck();

    public boolean drawFaceUp(int index);

    public boolean drawFromDeck();

    public boolean drawDestinationCards();
}
