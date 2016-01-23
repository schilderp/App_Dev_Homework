package edu.lewisu.cs.peterschilder.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private Question[] questions = new Question[]{
            new Question(R.string.question1,true),
            new Question(R.string.question2,false),
            new Question(R.string.question3,true),
            new Question(R.string.question4,false)
    };

    private int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button)findViewById(R.id.false_button);

        trueButton.setOnClickListener(new TrueClickListener());
        falseButton.setOnClickListener(new FalseClickListener());

        nextButton = (Button)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new NextClickListener());

        questionTextView = (TextView)findViewById(R.id.display_question);

        updateQuestion();

    }

    private void updateQuestion(){
        int question = questions[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPress){
        boolean correctAnswer = questions[currentIndex].isAnswerTrue();

        int toastTextID;

        if(userPress == correctAnswer){
            toastTextID = R.string.correct_toast;
        }
        else {
            toastTextID = R.string.incorrect_toast;
        }

        Toast.makeText(this, toastTextID, Toast.LENGTH_SHORT).show();
    }

    private class TrueClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
           checkAnswer(true);

        }
    }
    private class FalseClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
           checkAnswer(false);
        }
    }

    private class NextClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentIndex = (currentIndex + 1) % questions.length;
            updateQuestion();
        }
    }

    private class PreviousClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentIndex = (currentIndex - 1) % questions.length;
            updateQuestion();
        }
    }
}
