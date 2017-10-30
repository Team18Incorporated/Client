package edu.byu.cs.team18.tickettoride.GameView;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.byu.cs.team18.tickettoride.R;

import static edu.byu.cs.team18.tickettoride.R.drawable.map;

public class GameActivity extends AppCompatActivity {
    private android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void openGameView(){
        manager.beginTransaction().add(R.id.latice,new GameViewFragment()).commit();
    }
    public void openHand(){}
    public void openDestination(){}
    public void openChat(){}
    public void openHistory(){}
}
