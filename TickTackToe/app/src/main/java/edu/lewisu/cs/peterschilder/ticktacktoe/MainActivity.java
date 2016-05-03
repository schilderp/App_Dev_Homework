package edu.lewisu.cs.peterschilder.ticktacktoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonGrid[];
    private Button resetButton;
    private Boolean player = true;
    public int turn = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGrid = new Button[9];
        buttonGrid[0] = (Button)findViewById(R.id.top_left);
        buttonGrid[1] = (Button)findViewById(R.id.top_center);
        buttonGrid[2] = (Button)findViewById(R.id.top_right);
        buttonGrid[3] = (Button)findViewById(R.id.middle_left);
        buttonGrid[4] = (Button)findViewById(R.id.middle_center);
        buttonGrid[5] = (Button)findViewById(R.id.middle_right);
        buttonGrid[6] = (Button)findViewById(R.id.bottom_left);
        buttonGrid[7] = (Button)findViewById(R.id.bottom_center);
        buttonGrid[8] = (Button)findViewById(R.id.bottom_right);

        GridListener gridListener = new GridListener();
        for (int i=0; i < 9; i++){
            buttonGrid[i].setOnClickListener(gridListener);
        }

        resetButton = (Button)findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new ResetButtonClickListener());
    }

    private class GridListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            turn++;
            for (int i=0; i < 9; i++) {
                if(buttonGrid[i] == (Button)v) {
                    if (buttonGrid[i].getText() == null) {
                        if (player == true) {
                            buttonGrid[i].setText(R.string.o);
                            player = false;
                        } else {
                            buttonGrid[i].setText(R.string.x);
                            player = true;
                        }
                    }
                }
            }

           /* if(turn >= 5){
                switch (){}
            }*/
        }
    }



    private class ResetButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            turn = 0;
            player = true;
            for (int i=0; i < 9; i++){
                buttonGrid[i].setText(null);
            }
        }
    }
}
