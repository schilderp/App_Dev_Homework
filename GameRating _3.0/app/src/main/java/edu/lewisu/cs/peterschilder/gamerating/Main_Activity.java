package edu.lewisu.cs.peterschilder.gamerating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main_Activity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.launchB);
        button.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {



        }
    }

}
