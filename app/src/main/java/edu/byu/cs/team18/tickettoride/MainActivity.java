package com.example.abram.phase1main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.abram.phase1main.Fragments.LoginFragment;
import com.example.abram.phase1main.Fragments.RegisterFragment;


public class MainActivity extends AppCompatActivity {

    private Button mLoginButton;
    private Button mRegisterButton;

    public void switchToRegister(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new RegisterFragment();
        fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    public void switchToLogin(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new LoginFragment();
        fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLogin();
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });

    }

  /*
    @Override
    protected void onResume() {


    }
    */

}

