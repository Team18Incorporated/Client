package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.LoginCommand;
import edu.byu.cs.team18.tickettoride.Common.User;


/**
 * Created by abram on 3/31/2017.
 */

public class LoginAsyncTask extends AsyncTask<LoginCommand,Void,User> {

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

    protected User doInBackground(LoginCommand... params){
        AuthToken token = ServerProxy.getServerProxy().userLogin(params[0].getUsername(), params[0].getPassword());
        return new User(token,params[0].getUsername());
    }

    protected void onProgressUpdate(){

    }

    protected void onPostExecute(User user) {
        if(user.getAuthToken()!=null) {
            ClientFacade.getClientFacade().updateUser(user);
            Toast.makeText(getActivity().getApplicationContext(), "Login succeeded.", Toast.LENGTH_LONG);
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(), "Login succeeded.", Toast.LENGTH_LONG);
        }
    }

}
