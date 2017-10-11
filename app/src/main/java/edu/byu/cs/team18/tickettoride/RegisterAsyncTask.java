package edu.byu.cs.team18.tickettoride;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import edu.byu.cs.team18.tickettoride.Common.AuthToken;
import edu.byu.cs.team18.tickettoride.Common.Commands.RegisterCommand;
import edu.byu.cs.team18.tickettoride.Common.User;

/**
 * Created by abram on 3/31/2017.
 */

public class RegisterAsyncTask extends AsyncTask<RegisterCommand,Void,User> {

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    private Activity mActivity;

    public RegisterAsyncTask(Activity act){
        mActivity = act;
    }

    protected User doInBackground(RegisterCommand... params){
        AuthToken token = ServerProxy.getServerProxy().registerUser(params[0].getUsername(), params[0].getPassword());
        return new User(token, params[0].getUsername());
    }

    protected void onProgressUpdate(){

    }

    protected void onPostExecute(User user) {
      if(user.getAuthToken()!=null)
      {
          ClientFacade.getClientFacade().updateUser(user);
          Toast.makeText(getActivity().getApplicationContext(), "Registration succeeded.", Toast.LENGTH_LONG).show();
      }
      else
      {
          Toast.makeText(getActivity().getApplicationContext(), "Registration failed.", Toast.LENGTH_LONG).show();
      }
    }
}
