package edu.byu.cs.team18.tickettoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LobbyActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.latice,new LobbyFragment()).commit();
    }

    /*
    Starts game. Called by LobbyFragment
    @Pre: None
    @Post: None
     */
    public void startGame(){
        /*Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);*/
    }
}
