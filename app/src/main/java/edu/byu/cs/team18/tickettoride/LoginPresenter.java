package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.LoginCommand;
import edu.byu.cs.team18.tickettoride.Common.User;


/**
 * Created by abram on 10/7/2017.
 */

public class LoginPresenter implements Observer{


    private static LoginPresenter loginPresenter = null;
    private LoginFragment view = null;

    private LoginPresenter(){}

    public static LoginPresenter getLoginPresenter()
    {
        if(loginPresenter == null)
        {
            loginPresenter = new LoginPresenter();
        }
        return loginPresenter;
    }

    public void setView(LoginFragment viewIn){
        view = viewIn;
        ClientModel.SINGLETON.observerRegister(this);
    }
    /*
    clears view and stops observing ClientModel
    @Pre: none
    @Post: view = null
     */
    public void clearView(){
        view = null;
        ClientModel.SINGLETON.observerRemove(this);
    }


    public boolean canDo(String username, String password, Activity A)
    {
        if(username.length() == 0 || password.length() == 0) {
            CharSequence cs = "Username and password cannot be empty.";
            Toast.makeText(A.getApplicationContext(), cs, Toast.LENGTH_LONG).show();
            return false;
        }
        if(username.contains(" ") || password.contains(" "))
        {
            CharSequence cs = "Username and password must contain no spaces.";
            Toast.makeText(A.getApplicationContext(), cs, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void presentLogin(String username, String password, Activity A)
    {
        if(canDo(username,password,A))
        {
            /*LoginCommand loginCommand = new LoginCommand(username, password);
            new LoginAsyncTask(A).execute(loginCommand);*/
            AuthToken token = ServerProxy.getServerProxy().userLogin(username,password);
            if (token!=null) {
                ClientFacade.getClientFacade().updateUser(new User(token, username));
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o!=null && o instanceof User){
            MainActivity temp = (MainActivity) view.getActivity();
            clearView();
            temp.openLobby();
        }
    }
}
