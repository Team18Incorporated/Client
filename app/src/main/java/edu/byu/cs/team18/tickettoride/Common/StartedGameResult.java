package edu.byu.cs.team18.tickettoride.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasolomo on 10/11/17.
 */

public class StartedGameResult {


    private boolean started;
    private Game game;
    private Player player;

    public StartedGameResult(boolean started)
    {
        this.started=started;
    }

    public StartedGameResult(boolean started, Game game, Player player)
    {
        this.started=started;
        this.game=game;
        this.player=player;
    }

    public boolean hasStarted()
    {
        return started;
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<TrainCard> getPlayerHand()
    {
        return player.getHand();
    }
}
