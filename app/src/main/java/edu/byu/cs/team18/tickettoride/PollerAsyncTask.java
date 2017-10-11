package edu.byu.cs.team18.tickettoride;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.GameInfo;
import edu.byu.cs.team18.tickettoride.Common.GameList;


public class PollerAsyncTask extends AsyncTask<Void,Void,Void> {


    @Override
    protected Void doInBackground(Void... voids) {


            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
                    GameList joinableGames = (GameList) ServerProxy.getServerProxy().openGames();
                    GameList joinedGames= (GameList)(ServerProxy.getServerProxy().unstartedGames(token));
                    GameList startedGames= (GameList)(ServerProxy.getServerProxy().inProgressGames(token));

                    ClientModel.SINGLETON.setJoinableGamesList(joinableGames);
                    ClientModel.SINGLETON.setJoinedGamesList(joinedGames);
                    ClientModel.SINGLETON.setStartedGamesList(startedGames);

                }
            }, 0, 5*1000);
        return null;
    }
}
