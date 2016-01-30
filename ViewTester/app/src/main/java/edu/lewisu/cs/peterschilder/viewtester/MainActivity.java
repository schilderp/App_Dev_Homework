package edu.lewisu.cs.peterschilder.viewtester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    private TextView label;
    private EditText input;
    private Button mainButton;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private ToggleButton toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        label = (TextView) findViewById(R.id.text_view);
        input = (EditText) findViewById(R.id.input);
        mainButton = (Button) findViewById(R.id.main_button);
        ClickListener clickListener = new ClickListener();
        mainButton.setOnClickListener(clickListener);

        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);

        CheckListener checkListener = new CheckListener();
        checkBox1.setOnClickListener(checkListener);
        checkBox2.setOnClickListener(checkListener);
        checkBox3.setOnClickListener(checkListener);

        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text ="";
                if(toggleButton.isChecked()){
                    text = toggleButton.getTextOn().toString();
                }else {
                    text = toggleButton.getTextOff().toString();
                }

                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String text = input.getText().toString();
            label.setText(text);
        }
    }

    public void radioHandler(View v){
        RadioButton b = (RadioButton)v;
        String text = ((RadioButton)v).getText().toString();
        Toast.makeText(getBaseContext(), text,Toast.LENGTH_SHORT).show();
    }

    private class CheckListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String toastString="";
            if(checkBox1.isChecked()){
                toastString += "Box 1 is checked\n";
            }
            if(checkBox2.isChecked()){
                toastString += "Box 2 is checked\n";
            }
            if(checkBox3.isChecked()){
                toastString += "Box 3 is checked\n";
            }
            if(toastString.length()==0){
                toastString = "No Boxes checked";
            }

            Toast.makeText(getApplicationContext(),toastString,Toast.LENGTH_SHORT).show();
        }
    }


}
