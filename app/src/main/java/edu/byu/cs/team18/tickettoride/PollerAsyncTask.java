package edu.byu.cs.team18.tickettoride;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

import edu.byu.cs.team18.tickettoride.Common.GameList;


public class PollerAsyncTask extends AsyncTask<Void,Void,Void> {


    @Override
    protected Void doInBackground(Void... voids) {

        while(true)
        {
            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    GameList joinableGames = new GameList(ServerProxy.getServerProxy.openGames());
                    GameList joinedGames= new GameList(ServerProxy.getServerProxy.unstartedGames());;
                    GameList startedGames= new GameList(ServerProxy.getServerProxy.inProgressGames());;

                    ClientModel.SINGLETON.setJoinableGamesList(joinableGames);
                    ClientModel.SINGLETON.setJoinedGamesList(joinedGames);
                    ClientModel.SINGLETON.setStartedGamesList(startedGames);

                }
            }, 0, 5*1000);
        }

    }
}
