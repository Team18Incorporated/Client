package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.widget.Toast;

import edu.byu.cs.team18.tickettoride.Common.Commands.LoginCommand;


/**
 * Created by abram on 10/7/2017.
 */

public class LoginPresenter {


    private static LoginPresenter loginPresenter = null;

    private LoginPresenter(){}

    public static LoginPresenter getLoginPresenter()
    {
        if(loginPresenter == null)
        {
            loginPresenter = new LoginPresenter();
        }
        return loginPresenter;
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
            LoginCommand loginCommand = new LoginCommand(username, password);
            new LoginAsyncTask(A).execute(loginCommand);

        }
    }
}
