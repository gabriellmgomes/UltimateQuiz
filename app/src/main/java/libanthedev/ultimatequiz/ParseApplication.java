package libanthedev.ultimatequiz;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by libanmohamed on 9/5/14.
 */
public class ParseApplication extends Application {

    public static final String TAG = "MyApp" ;

    @Override
    public void onCreate() {
        super.onCreate();

        //Parse id

     Parse.initialize(this, "mksaaR5uqrj9TifpLBew74dzX1ABXNPI1OTMEQBV", "e0Ii3d8l1riRnyoY3Z70C3WW42ee6aAJt2ig14kX");

        //Facebook id
        ParseObject.registerSubclass(ParseUser.class);

        ParseFacebookUtils.initialize(getString(R.string.app_id));

    }
}
