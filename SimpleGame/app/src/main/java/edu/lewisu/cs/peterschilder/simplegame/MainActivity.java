package edu.lewisu.cs.peterschilder.simplegame;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = new GameView(this);
        setContentView(gameView);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.stopGame();
    }
}
