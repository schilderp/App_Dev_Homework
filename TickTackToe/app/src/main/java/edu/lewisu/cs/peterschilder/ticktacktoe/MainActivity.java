package edu.lewisu.cs.peterschilder.ticktacktoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonGrid[];
    private Button resetButton;
    private Boolean player = true;
    private Boolean isWinner = false;
    private int turn = 0;
    private String toastWinner = "The Winner is ";


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
            if(isWinner == false) {
                turn++;
                for (int i = 0; i < 9; i++) {
                    if (buttonGrid[i] == (Button) v) {
                        if (buttonGrid[i].getText().toString() == "") {
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
            }
            if(turn >= 5){
                if((buttonGrid[0].getText().toString().equals("O") && buttonGrid[3].getText().toString().equals("O") && buttonGrid[6].getText().toString().equals("O"))
                        ||(buttonGrid[0].getText().toString().equals("X") && buttonGrid[3].getText().toString().equals("X") && buttonGrid[6].getText().toString().equals("X")) ){
                    if(player==false){
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 1", Toast.LENGTH_SHORT).show();
                    }else{
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 2", Toast.LENGTH_SHORT).show();
                    }
                }else if((buttonGrid[1].getText().toString().equals("O") && buttonGrid[4].getText().toString().equals("O") && buttonGrid[7].getText().toString().equals("O"))
                        ||(buttonGrid[1].getText().toString().equals("X") && buttonGrid[4].getText().toString().equals("X") && buttonGrid[7].getText().toString().equals("X")) ) {
                    if (player == false) {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 1", Toast.LENGTH_SHORT).show();
                    } else {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 2", Toast.LENGTH_SHORT).show();
                    }
                }else if((buttonGrid[2].getText().toString().equals("O") && buttonGrid[5].getText().toString().equals("O") && buttonGrid[8].getText().toString().equals("O"))
                        ||(buttonGrid[2].getText().toString().equals("X") && buttonGrid[5].getText().toString().equals("X") && buttonGrid[8].getText().toString().equals("X")) ) {
                    if (player == false) {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 1", Toast.LENGTH_SHORT).show();
                    } else {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 2", Toast.LENGTH_SHORT).show();
                    }
                }else if((buttonGrid[0].getText().toString().equals("O") && buttonGrid[1].getText().toString().equals("O") && buttonGrid[2].getText().toString().equals("O"))
                        ||(buttonGrid[0].getText().toString().equals("X") && buttonGrid[1].getText().toString().equals("X") && buttonGrid[2].getText().toString().equals("X")) ) {
                    if (player == false) {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 1", Toast.LENGTH_SHORT).show();
                    } else {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 2", Toast.LENGTH_SHORT).show();
                    }
                }else if((buttonGrid[3].getText().toString().equals("O") && buttonGrid[4].getText().toString().equals("O") && buttonGrid[5].getText().toString().equals("O"))
                        ||(buttonGrid[3].getText().toString().equals("X") && buttonGrid[4].getText().toString().equals("X") && buttonGrid[5].getText().toString().equals("X")) ) {
                    if (player == false) {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 1", Toast.LENGTH_SHORT).show();
                    } else {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 2", Toast.LENGTH_SHORT).show();
                    }
                }else if((buttonGrid[6].getText().toString().equals("O") && buttonGrid[7].getText().toString().equals("O") && buttonGrid[8].getText().toString().equals("O"))
                        ||(buttonGrid[6].getText().toString().equals("X") && buttonGrid[7].getText().toString().equals("X") && buttonGrid[8].getText().toString().equals("X")) ) {
                    if (player == false) {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 1", Toast.LENGTH_SHORT).show();
                    } else {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 2", Toast.LENGTH_SHORT).show();
                    }
                }else if((buttonGrid[0].getText().toString().equals("O") && buttonGrid[4].getText().toString().equals("O") && buttonGrid[8].getText().toString().equals("O"))
                        ||(buttonGrid[0].getText().toString().equals("X") && buttonGrid[4].getText().toString().equals("X") && buttonGrid[8].getText().toString().equals("X")) ) {
                    if (player == false) {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 1", Toast.LENGTH_SHORT).show();
                    } else {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 2", Toast.LENGTH_SHORT).show();
                    }
                }else if((buttonGrid[2].getText().toString().equals("O") && buttonGrid[4].getText().toString().equals("O") && buttonGrid[6].getText().toString().equals("O"))
                        ||(buttonGrid[2].getText().toString().equals("X") && buttonGrid[4].getText().toString().equals("X") && buttonGrid[6].getText().toString().equals("X")) ) {
                    if (player == false) {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 1", Toast.LENGTH_SHORT).show();
                    } else {
                        isWinner = true;
                        Toast.makeText(getApplicationContext(), toastWinner + "Player 2", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(turn >=9){
                        Toast.makeText(getApplicationContext(), "No Winner", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }


    private class ResetButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            turn = 0;
            isWinner = false;
            player = true;
            for (int i=0; i < 9; i++){
                buttonGrid[i].setText(null);
            }
        }
    }
}
