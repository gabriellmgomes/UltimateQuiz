package libanthedev.ultimatequiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class ShareYourScore extends Activity {



    TextView shareYourScore;
    Button  scoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_your_score);

        shareYourScore = (TextView) findViewById(R.id.scoreText);
        scoreButton = (Button) findViewById(R.id.scoreButton);

        if (getCallingActivity().equals(Geography.class)) {
            Bundle extras = getIntent().getExtras();
            int score = extras.getInt("score");
            shareYourScore.setText(String.valueOf(score));

        }

        if (getCallingActivity().equals(History.class)) {
            Bundle extras = getIntent().getExtras();
            int score = extras.getInt("score");
            shareYourScore.setText(String.valueOf(score));
        }
        if (getCallingActivity().equals(Math.class)) {
            Bundle extras = getIntent().getExtras();
            int score = extras.getInt("score");
            shareYourScore.setText(String.valueOf(score));
        }
        if (getCallingActivity().equals(Movies.class)) {
            Bundle extras = getIntent().getExtras();
            int score = extras.getInt("score");
            shareYourScore.setText(String.valueOf(score));
        }
        if (getCallingActivity().equals(Sports.class)) {
            Bundle extras = getIntent().getExtras();
            int score = extras.getInt("score");
            shareYourScore.setText(String.valueOf(score));
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share_your_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
