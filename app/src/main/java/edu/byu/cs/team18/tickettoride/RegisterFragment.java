package com.example.abram.phase1main.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.abram.phase1main.Presenters.RegisterPresenter;
import com.example.abram.phase1main.R;


/**
 * Created by abram on 3/31/2017.
 */

public class RegisterFragment extends Fragment {
    private String mUsername = "";
    private EditText mUsernameField;
    private String mPassword = "";
    private EditText mPasswordField;
    private String mReEnterPassword = "";
    private EditText mReEnterPasswordField;
    private Button mSubmitButton;



    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getReEnterPassword() {
        return mReEnterPassword;
    }

    public void setReEnterPassword(String reEnterPassword) {
        mReEnterPassword = reEnterPassword;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_register, container, false);

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

        mReEnterPasswordField = (EditText) v.findViewById(R.id.re_enter_password_value);
        mReEnterPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setReEnterPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //blank
            }
        });

        mSubmitButton = (Button) v.findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Should create a login request object to be sent to the server proxy.
                RegisterPresenter.getRegisterPresenter().presentRegister(mUsername, mPassword, mReEnterPassword, getActivity());
            }
        });
        return v;
    }
}
