package edu.lewisu.cs.peterschilder.rating_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class Rating_Activity extends AppCompatActivity {

     RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        //get the value from the intent
        Intent send = getIntent();
        String name = send.getStringExtra("name");

        TextView textView = (TextView)findViewById(R.id.helo_textView);
        textView.setText(getString(R.string.hello) + " " + name);

        ratingBar = (RatingBar)findViewById(R.id.rating_Bar);

    }

    public void submit(View v){
        float rating = ratingBar.getRating();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("rating", rating);
        setResult(RESULT_OK, returnIntent);
        finish();

    }
}
