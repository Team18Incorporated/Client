package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.abram.phase1main.AsyncTasks.RegisterAsyncTask;
import com.example.abram.phase1main.Commands.RegisterCommand;

/**
 * Created by abram on 10/7/2017.
 */

public class RegisterPresenter {
    private static RegisterPresenter registerPresenter = null;

    private RegisterPresenter(){}

    public static RegisterPresenter getRegisterPresenter()
    {
        if(registerPresenter == null)
        {
            registerPresenter = new RegisterPresenter();
        }
        return registerPresenter;
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
            RegisterCommand registerCommand = new RegisterCommand(username, password);
            new RegisterAsyncTask(A).execute(registerCommand);
        }
    }
}
