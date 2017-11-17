package edu.byu.cs.team18.tickettoride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//impo

public class LoginFragment extends Fragment {

    private EditText mUsernameField;
    private String mUsername = "";
    private EditText mPasswordField;
    private String mPassword = "";
    private Button mSubmitButton;
    private String server = "192.168.2.172";

    public EditText getUsernameField() {
        return mUsernameField;
    }

    public void setUsernameField(EditText usernameField) {
        mUsernameField = usernameField;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public EditText getPasswordField() {
        return mPasswordField;
    }

    public void setPasswordField(EditText passwordField) {
        mPasswordField = passwordField;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Button getSubmitButton() {
        return mSubmitButton;
    }

    public void setSubmitButton(Button submitButton) {
        mSubmitButton = submitButton;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mUsernameField = (EditText) v.findViewById(R.id.username_value);
        mUsernameField.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                //blank intentionally
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                setUsername(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Blank on purpose.
            }
        });

        mPasswordField = (EditText) v.findViewById(R.id.password_value);
        mPasswordField.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                //blank intentionally
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                setPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Blank on purpose.
            }
        });

        mSubmitButton = (Button) v.findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Should create a login request object to be sent to the server proxy.
                LoginPresenter.getLoginPresenter().setServer(server);
                LoginPresenter.getLoginPresenter().presentLogin(mUsername,mPassword,getActivity());
            }
        });
        final EditText serverAddress = (EditText) v.findViewById(R.id.server);
        serverAddress.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                //blank intentionally
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                server = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Blank on purpose.
            }
        });
        Button setServer = (Button) v.findViewById(R.id.setServer);
        setServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPresenter.getLoginPresenter().setServer(server);
            }
        });

        LoginPresenter.getLoginPresenter().setView(this);
        return v;
    }
}

//MAKING TOAST
/*
Context context = getApplicationContext();
CharSequence text = "Error. Login failed.";
int duration = Toast.LENGTH_SHORT;

Toast toast = Toast.makeText(context, text, duration);
toast.show();
 */