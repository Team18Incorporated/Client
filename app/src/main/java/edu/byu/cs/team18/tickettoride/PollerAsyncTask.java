package edu.byu.cs.team18.tickettoride;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.GameInfo;
import edu.byu.cs.team18.tickettoride.Common.GameList;


public class PollerAsyncTask extends AsyncTask<Void,Void,Void> {

    private Date date;


    @Override
    protected Void doInBackground(Void... voids) {

            date = new Date();
            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {

                    if(ClientModel.SINGLETON.getCurrentGame()!=null)
                    {
                        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();

                        CommandList cl =ServerProxy.getServerProxy().getHistory(token, ClientModel.SINGLETON.getCurrentGame().getGameID(), date);
                        cl.execute();
                        date = cl.getDate();
                    }
                    else
                    {
                        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();
                        GameList joinableGames = (GameList) ServerProxy.getServerProxy().openGames(token);
                        GameList joinedGames= (GameList)(ServerProxy.getServerProxy().unstartedGames(token));
                        GameList startedGames= (GameList)(ServerProxy.getServerProxy().inProgressGames(token));

                        ClientModel.SINGLETON.setJoinableGamesList(joinableGames);
                        ClientModel.SINGLETON.setJoinedGamesList(joinedGames);
                        ClientModel.SINGLETON.setStartedGamesList(startedGames);
                    }


                }
            }, 500, 5*100);
        return null;
    }
}
