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

    protected Void doInBackground(Void... voids) {

            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {

                    if(ClientModel.SINGLETON.getCurrentGame()!=null)
                    {


                        //Date date = ClientModel.SINGLETON.getLatestDate();
                        int index = ClientModel.SINGLETON.getCommandIndex();
                        AuthToken token = ClientModel.SINGLETON.getCurrentUser().getAuthToken();

                        CommandList cl =ServerProxy.getServerProxy().getHistory(token, ClientModel.SINGLETON.getCurrentGame().getGameID(),index);
                        if(cl!=null)
                        {
                            cl.execute();
                            //ClientModel.SINGLETON.setLatestDate(cl.getDate());
                            ClientModel.SINGLETON.setCommandIndex(cl.getIndex());
                        }

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
            }, 1000, 1500);
        return null;
    }
}
