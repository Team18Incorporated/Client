package edu.byu.cs.team18.tickettoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LobbyActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentManager manager;

    //TODO: add import for joinedGamesFragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.latice,new JoinedGamesFragment()).commit();
    }

    /*
    Sets game in lobbyPresenter then launches LobbyFragment
    @Pre: 0<gameID<10000
    @Post: new LobbyFragment
     */
    public void openLobby(int gameID){
        LobbyPresenter.instance.setGame(gameID);
        manager.beginTransaction().replace(R.id.latice,new LobbyFragment()).commit();
    }

    //TODO: add openJoinedGames and openJoin functions

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
