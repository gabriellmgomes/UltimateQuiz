package libanthedev.ultimatequiz;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Sports extends Activity {

    private TrueFalse [] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.sports_question_1, false),
            new TrueFalse(R.string.sports_question_2,true),
            new TrueFalse(R.string.sports_question_3,true),
            new TrueFalse(R.string.sports_question_4,false),
            new TrueFalse(R.string.sports_question_5,true),
            new TrueFalse(R.string.sports_question_6, false),




    };

    private TextView textView;
    private TextView scoreText;
    int mCurrentIndex =0;
    int numOfPoints = 0;

    private Button trueButton;
    private Button falseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        Toast.makeText(this, "Welcome to the Sports Quiz", Toast.LENGTH_SHORT).show();

        textView = (TextView) findViewById(R.id.textView);
        scoreText = (TextView) findViewById(R.id.scoreText);
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        textView.setText(question);

        trueButton = (Button) findViewById(R.id.truebutton);
        falseButton = (Button) findViewById(R.id.falsebutton);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }

        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });






        Intent i = new Intent(this, ShareYourScore.class);
        i.putExtra("score", numOfPoints);
        startActivity(i);


    }

    private void updateQuestion() {
        final int newQuestion = mQuestionBank[mCurrentIndex].getQuestion();
        new CountDownTimer(2000,1000) {

            @Override
            public void onTick(long arg0) {}

            @Override
            public void onFinish() {
                textView.setText(newQuestion);
            }
        }.start();
    }
    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            numOfPoints++;
        }
        else
        {
            messageResId = R.string.incorrect_toast;
            numOfPoints--;
        }
        final Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 2000);

        if (numOfPoints<0) {
            numOfPoints=0;
        }
        scoreText.setText(String.valueOf(numOfPoints));


    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sports, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
