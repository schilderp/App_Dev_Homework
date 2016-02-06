package edu.lewisu.cs.peterschilder.rating_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Launch_Activity extends AppCompatActivity {

    private EditText nameEditText;
    private static final int Ratting_ID = 1106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_);
        nameEditText = (EditText)findViewById(R.id.name_editText);
    }

    public void buttonClick(View v){
        Intent launchRating = new Intent(this, Rating_Activity.class);
        String name = nameEditText.getText().toString();
        launchRating.putExtra("name", name);
        startActivityForResult(launchRating, Ratting_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == Ratting_ID){
            float rating = data.getFloatExtra("rating", 0 );

            String toastString = String.format("You submitted a rating of %.1f stars",rating);
            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
        }
    }
}
