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

        while(true)
        {
            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
                    GameList joinableGames = new GameList((ArrayList<GameInfo>) ServerProxy.getServerProxy().openGames());
                    GameList joinedGames= new GameList((ArrayList<GameInfo>)ServerProxy.getServerProxy().unstartedGames(token));;
                    GameList startedGames= new GameList((ArrayList<GameInfo>)ServerProxy.getServerProxy().inProgressGames(token));;

                    ClientModel.SINGLETON.setJoinableGamesList(joinableGames);
                    ClientModel.SINGLETON.setJoinedGamesList(joinedGames);
                    ClientModel.SINGLETON.setStartedGamesList(startedGames);

                }
            }, 0, 5*1000);
        }

    }
}
