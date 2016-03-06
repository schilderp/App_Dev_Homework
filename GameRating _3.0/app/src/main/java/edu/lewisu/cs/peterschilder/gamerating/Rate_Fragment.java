package edu.lewisu.cs.peterschilder.gamerating;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Rate_Fragment extends Fragment {

    private EditText title;
    private EditText comments;
    private Button submitButton;
    private RadioGroup radioG;
    private RadioButton radioB;
    private RatingBar ratingBar;

    public Rate_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.fragment_rate_);
        View v = inflater.inflate(R.layout.fragment_rate_, container, false);


        title = (EditText) v.findViewById(R.id.editTitle);
        comments = (EditText) v.findViewById(R.id.editComments);
        submitButton = (Button) v.findViewById(R.id.submit_button);
        radioG = (RadioGroup)v.findViewById(R.id.platform);
        ratingBar = (RatingBar)v.findViewById(R.id.ratingBar);
        ClickListener clickListener = new ClickListener();
        submitButton.setOnClickListener(clickListener);
        // Inflate the layout for this fragment
        return v;
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int rb = radioG.getCheckedRadioButtonId();
            radioB = (RadioButton)v.findViewById(rb);
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
          //  setResult(RESULT_OK, returnIntent);
           // finish();

            /*Context context = getApplicationContext();
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            */

        }
    }

}
