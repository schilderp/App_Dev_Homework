package edu.lewisu.cs.peterschilder.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button)findViewById(R.id.false_button);

        trueButton.setOnClickListener(new CorrectClickListener());
        falseButton.setOnClickListener(new IncorrectClickListener());

    }

    private class CorrectClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Toast.makeText(QuizActivity.this,
                    R.string.correct_toast,
                    Toast.LENGTH_SHORT).show();

        }
    }
    private class IncorrectClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(QuizActivity.this,
                    R.string.incorrect_toast,
                    Toast.LENGTH_SHORT).show();

        }
    }
}
