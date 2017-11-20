package edu.byu.cs.team18.tickettoride;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.Common.Commands.ICommand;

/**
 * Created by dasolomo on 11/20/17.
 */

public class GameHistoryPresenter implements Observer {

    public static GameHistoryPresenter SINGELTON = new GameHistoryPresenter();
    private GameHistoryFragment view;

    private GameHistoryPresenter(){}



    public void setView(GameHistoryFragment view)
    {
        this.view=view;
        ClientModel.SINGLETON.observerRegister(this);
    }


    public ArrayList<ICommand> getCommandList()
    {
        return ClientModel.SINGLETON.getGameHistory();
    }







    @Override
    public void update(Observable observable, Object o) {
        updateView();
    }

    private void updateView()
    {
        if(view!=null)
        {
            if(view.getActivity()!=null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(view!=null)
                        {
                            view.refreshView();
                        }
                    }
                });

            }//view.refreshView();
        }

    }
}
