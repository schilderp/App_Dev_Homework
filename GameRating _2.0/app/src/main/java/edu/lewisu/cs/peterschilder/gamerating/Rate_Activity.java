package edu.lewisu.cs.peterschilder.gamerating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class Rate_Activity extends AppCompatActivity {


    private EditText title;
    private EditText comments;
    private Button submitButton;
    private RadioGroup radioG;
    private RadioButton radioB;
    private RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (EditText) findViewById(R.id.editTitle);
        comments = (EditText) findViewById(R.id.editComments);
        submitButton = (Button) findViewById(R.id.submit_button);
        radioG = (RadioGroup)findViewById(R.id.platform);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        ClickListener clickListener = new ClickListener();
        submitButton.setOnClickListener(clickListener);

    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int rb = radioG.getCheckedRadioButtonId();
            radioB = (RadioButton)findViewById(rb);
            String text ="Game: ";
            text += title.getText().toString();
            text += "\nComments: ";
            text += comments.getText().toString();
            text += "\nPlatform: ";
            text += radioB.getText().toString();
            text += "\nRating: ";
            text += ratingBar.getRating() + " stars";

            Intent returnIntent = new Intent();
            returnIntent.putExtra("summary", text);
            setResult(RESULT_OK, returnIntent);
            finish();

            /*Context context = getApplicationContext();
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            */

        }
    }

}
