package edu.byu.cs.team18.tickettoride.Common;

/**
 * Created by dasolomo on 10/11/17.
 */

public class StartedGameResult {

    private boolean started;

    public StartedGameResult(boolean started)
    {
        this.started=started;
    }

    public boolean hasStarted()
    {
        return started;
    }
}
