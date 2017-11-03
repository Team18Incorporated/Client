package edu.byu.cs.team18.tickettoride.GameView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.byu.cs.team18.tickettoride.ChatFragment;
import edu.byu.cs.team18.tickettoride.ClientModel;
import edu.byu.cs.team18.tickettoride.Common.Commands.CommandList;
import edu.byu.cs.team18.tickettoride.DestinationSelectFragment;
import edu.byu.cs.team18.tickettoride.GameHistoryFragment;
import edu.byu.cs.team18.tickettoride.R;

import static edu.byu.cs.team18.tickettoride.R.drawable.map;

public class GameActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        openGameView();
    }

    public void openGameView(){
        manager.beginTransaction().add(R.id.latice,new GameViewFragment()).commit();
    }
    public void openHand()
    {
        manager.beginTransaction().add(R.id.latice,new HandFragment()).commit();
    }
    public void openDestination(){
        manager.beginTransaction().add(R.id.latice,new DestinationSelectFragment()).commit();
    }
    public void openChat(){
        ChatFragment temp = new ChatFragment();
        temp.setPlayerName(ClientModel.SINGLETON.getCurrentPlayer().getPlayerName());
        temp.setAuthToken(ClientModel.SINGLETON.getCurrentUser().getAuthToken());
        temp.setChatHistory(ClientModel.SINGLETON.getCurrentGame().getChatHistory());
        manager.beginTransaction().add(R.id.latice,temp).commit();
    }
    public void openHistory(){
        GameHistoryFragment fragment = new GameHistoryFragment();
        fragment.setCommandList(new CommandList(ClientModel.SINGLETON.getCurrentGame().getGameHistory()));
        manager.beginTransaction().add(R.id.latice,fragment).commit();
    }
    public void openPlayers(){
        PlayerFragment fragment = new PlayerFragment();
        manager.beginTransaction().add(R.id.latice,fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // go back to main activity //
        switch(item.getItemId()) {
            case R.id.drawDestinations:
                openDestination();
                break;
            case R.id.chat:
                openChat();
                break;
            case R.id.history:
                openHistory();
                break;
            case R.id.players:
                openPlayers();
            default:
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
