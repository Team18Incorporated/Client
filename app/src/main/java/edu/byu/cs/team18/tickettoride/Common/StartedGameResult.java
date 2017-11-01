package edu.byu.cs.team18.tickettoride.Common;

/**
 * Created by dasolomo on 10/11/17.
 */

public class StartedGameResult {


    private boolean started;
    private Game game;

    public StartedGameResult(boolean started)
    {
        this.started=started;
    }

    public StartedGameResult(boolean started, Game game)
    {
        this.started=started;
        this.game=game;
    }

    public boolean hasStarted()
    {
        return started;
    }

    public Game getGame() {
        return game;
    }
}
