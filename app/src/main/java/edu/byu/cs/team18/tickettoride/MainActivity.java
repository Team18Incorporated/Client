package edu.byu.cs.team18.tickettoride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


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
                RegisterPresenter.getRegisterPresenter().clearView();
                switchToLogin();
            }
        });

        mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPresenter.getLoginPresenter().clearView();
                switchToRegister();
            }
        });

    }

    public void openLobby()
    {
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }
  /*
    @Override
    protected void onResume() {


    }
    */

}

