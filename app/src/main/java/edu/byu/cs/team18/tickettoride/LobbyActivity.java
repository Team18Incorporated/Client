package edu.byu.cs.team18.tickettoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.byu.cs.team18.tickettoride.GameView.GameActivity;

public class LobbyActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        //manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.latice,new JoinedGamesFragment()).commit();
    }

    /*
    Sets game in lobbyPresenter then launches LobbyFragment
    @Pre: 0<gameID<10000
    @Post: new LobbyFragment
     */
    public void openLobby(String gameID){
        LobbyPresenter.instance.setGame(gameID);
        manager.beginTransaction().replace(R.id.latice,new LobbyFragment()).addToBackStack(null).commit();


    }

    public void openJoin(){
        manager.beginTransaction().replace(R.id.latice,new JoinFragment()).addToBackStack(null).commit();
    }

    public void openJoinedGames(){
        manager.beginTransaction().replace(R.id.latice,new JoinedGamesFragment()).addToBackStack(null).commit();
    }

    /*
    Starts game. Called by LobbyFragment
    @Pre: None
    @Post: None
     */


    @Override
    public void onBackPressed(){

        if (manager.getBackStackEntryCount() > 0) {

            manager.popBackStack();
        } else {

            super.onBackPressed();
        }
    }



    public void openGame()
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }



    /**
     * Created by Solomons on 10/30/2017.
     */

    public static class DestinationSelectFragment {
    }
}
