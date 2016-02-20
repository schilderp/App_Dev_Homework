package edu.lewisu.cs.peterschilder.gamerating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Launch_Activity extends AppCompatActivity {

    private static final int GameRate_ID = 2033;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_);
    }

    public void buttonClick(View v){
        Intent launchRateGame = new Intent(this,Rate_Activity.class);
        startActivityForResult(launchRateGame,GameRate_ID);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GameRate_ID) {
            String text = data.getStringExtra("summary");

            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }
    }
}
