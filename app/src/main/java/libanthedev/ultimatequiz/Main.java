package libanthedev.ultimatequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.android.Facebook;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;

import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


public class Main extends Activity {

    ListView listView;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().hide();

        Session session = ParseFacebookUtils.getSession();
        if (session!=null && session.isOpened()) {
            makeRequest();


        }


        listView = (ListView) findViewById(R.id.listView);



        logout = (Button) findViewById(R.id.logoutButton);

        String [] values = new String[] {
                "Math", "History", "Geography", "Movies", "Sports",
                "Share your score"

        };



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i==0) {
                    startActivity(new Intent(getApplication(),Math.class));
                }
                if (i==1) {
                    startActivity(new Intent(getApplication(),History.class));
                }
                if (i==2) {
                    startActivity(new Intent(getApplication(),Geography.class));
                }
                if (i==3) {
                    startActivity(new Intent(getApplication(),Movies.class));
                }
                if (i==4) {

                    startActivity(new Intent(getApplication(),Sports.class));
                }
                if (i==5) {
                    startActivity(new Intent(getApplicationContext(), ShareYourScore.class));
                }




            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        ParseUser.logOut();
                       Session.getActiveSession().closeAndClearTokenInformation();
                        Intent intent = new Intent(getApplication(), LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

        });

    }

    private void makeRequest() {


        Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        if (user != null) {

                            JSONObject userProfile = new JSONObject();

                            try {
                                userProfile.put("facebookId", user.getId());
                                userProfile.put("name", user.getName());
                                userProfile.put("firstName", user.getFirstName());

                            //    if (user.getLocation().getProperty("name") != null) {
                               //     userProfile.put("location",user.getLocation().getProperty("name"));
                                //}

                                if (user.getProperty("gender") != null) {
                                    userProfile.put("gender",user.getProperty("gender"));
                                }

                                if (user.getProperty("email") != null) {
                                    userProfile.put("email",user.getProperty("email"));
                                }

                                if (user.getBirthday() != null) {
                                    userProfile.put("birthday",user.getBirthday());
                                }

                                if (user.getProperty("relationship_status") != null) {
                                    userProfile.put("relationship_status",user.getProperty("relationship_status"));
                                }

                                ParseUser currentUser = ParseUser.getCurrentUser();

                                currentUser.put("profile", userProfile);
                                currentUser.saveInBackground();
                                String name= (String)currentUser.getJSONObject("profile").get("name");
                               final Toast toast = Toast.makeText(Main.this,"Welcome "+ name,Toast.LENGTH_LONG);
                                toast.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 1000);




                            } catch (JSONException e) {
                                Log.d(ParseApplication.TAG, "Error parsing returned user data.");
                            }

                        } else if (response.getError() != null) {

                            if ((response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_RETRY)
                                    || (response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_REOPEN_SESSION)) {

                                Log.d(ParseApplication.TAG,  "The facebook session was invalidated.");

                            } else {
                                Log.d(ParseApplication.TAG, "Some other error: " + response.getError() .getErrorMessage());
                            }
                        }
                    }
                });

        request.executeAsync();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
