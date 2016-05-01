package edu.lewisu.cs.peterschilder.simplegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.stopGame();
    }
}
