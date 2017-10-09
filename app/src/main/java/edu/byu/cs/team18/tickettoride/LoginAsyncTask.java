package com.example.abram.phase1main.AsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abram.phase1main.ModelClasses.AuthToken;
import com.example.abram.phase1main.Results.CommandResult;
import com.example.abram.phase1main.Commands.LoginCommand;
import com.example.abram.phase1main.Results.LoginResult;
import com.example.abram.phase1main.ClientCommunicator;
import com.example.abram.phase1main.ServerProxy;


/**
 * Created by abram on 3/31/2017.
 */

public class LoginAsyncTask extends AsyncTask<LoginCommand,Void,LoginResult> {

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private Activity activity;

    public LoginAsyncTask(Activity act){
        activity = act;
    }

    protected LoginResult doInBackground(LoginCommand... params){
        AuthToken token = ServerProxy.getServerProxy().userLogin(params[0].getUsername(), params[0].getPassword());
        if(token == null)
        {
            String m = "Login invalid.";
            return new LoginResult(token.getToken(), m);
        }
        else{
            String m = "Login successful.";
            return new LoginResult(token.getToken(), m);
        }

    }

    protected void onProgressUpdate(){

    }

    protected void onPostExecute(CommandResult login_result) {
        if(login_result!=null) {
            CharSequence cs = login_result.getMessage();
            Toast.makeText(getActivity(), cs, Toast.LENGTH_SHORT).show();
            /*if (!cs.toString().contains("Error") && !cs.toString().contains("Incorrect")) {
                MainActivity.sAuthToken = login_result.getAuthToken();
            }*/

        }
    }

}
