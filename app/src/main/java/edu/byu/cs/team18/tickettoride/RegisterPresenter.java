package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.RegisterCommand;
import edu.byu.cs.team18.tickettoride.Common.GameInfo;
import edu.byu.cs.team18.tickettoride.Common.User;


/**
 * Created by abram on 10/7/2017.
 */

public class RegisterPresenter implements Observer{
    private static RegisterPresenter registerPresenter = null;
    private RegisterFragment view = null;

    private RegisterPresenter(){}

    public static RegisterPresenter getRegisterPresenter()
    {
        if(registerPresenter == null)
        {
            registerPresenter = new RegisterPresenter();
        }
        return registerPresenter;
    }

    public void setServer(String in){
        ClientCommunicator.getSingleton().setServer(in);
    }

    public void setView(RegisterFragment viewIn){
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


    public boolean canDo(String username, String password, String reEnterPassword, Activity A)
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
        if(!password.equals(reEnterPassword))
        {
            CharSequence cs = "Passwords do not match.";
            Toast.makeText(A.getApplicationContext(), cs, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void presentRegister(String username, String password, String reEnterPassword, Activity A)
    {
        if(canDo(username,password,reEnterPassword,A))
        {
            /*RegisterCommand registerCommand = new RegisterCommand(username, password);
            new RegisterAsyncTask(A).execute(registerCommand);*/
            AuthToken token = ServerProxy.getServerProxy().registerUser(username,password);
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
