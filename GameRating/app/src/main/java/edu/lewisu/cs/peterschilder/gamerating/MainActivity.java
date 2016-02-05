package edu.lewisu.cs.peterschilder.gamerating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText title;
    private EditText comments;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (EditText) findViewById(R.id.editTitle);
        comments = (EditText) findViewById(R.id.editComments);
        submitButton = (Button) findViewById(R.id.submit_button);
        ClickListener clickListener = new ClickListener();
        submitButton.setOnClickListener(clickListener);

    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String text ="";
            //RadioButton b = (RadioButton)v;
            text += title.getText().toString();
            text += "\n";
            text += comments.getText().toString();
            text += "\n";

            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            //text += b.getText().toString();
            //Toast.makeText(getBaseContext(), text,Toast.LENGTH_SHORT).show();

        }
    }

    public void radioHandler(View v){
        RadioButton b = (RadioButton)v;
        String text = ((RadioButton)v).getText().toString();
        Toast.makeText(getBaseContext(), text,Toast.LENGTH_SHORT).show();
    }


}
