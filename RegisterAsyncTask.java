package com.example.abram.phase1main.AsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abram.phase1main.Results.CommandResult;
import com.example.abram.phase1main.Commands.RegisterCommand;
import com.example.abram.phase1main.ServerProxy;

/**
 * Created by abram on 3/31/2017.
 */

public class RegisterAsyncTask extends AsyncTask<RegisterCommand,Void,CommandResult> {

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

    protected CommandResult doInBackground(RegisterCommand... params){
        return ServerProxy.getServerProxy().register(params[0],"register");
    }

    protected void onProgressUpdate(){

    }

    protected void onPostExecute(CommandResult register_result) {
        if(register_result!=null) {
            CharSequence cs = register_result.getMessage();
            Toast.makeText(getActivity(), cs, Toast.LENGTH_SHORT).show();
          /*  if (!cs.toString().contains("Error") && !cs.toString().contains("Incorrect")) {
                MainActivity.sAuthToken = register_result.getAuthToken();
                ((MainActivity) getActivity()).switchToMap();
            }*/
        }
    }
}
