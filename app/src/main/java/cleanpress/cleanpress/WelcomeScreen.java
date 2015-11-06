package cleanpress.cleanpress;

import android.Stripe;
import android.content.Intent;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.*;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class WelcomeScreen extends FragmentActivity {

    CallbackManager callbackManager;
    Button LogIn,LoginFB,Sign;
    ViewPager viewPager;
    List<Fragment> fragmentList;
    PageAdapter pageAdapter;
    Typeface segoe;
    RadioButton tut1,tut2,tut3,tut4;
    LinearLayout Layout;
    public String fbid,fbname,fbmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        Layout = (LinearLayout) findViewById(R.id.Layout);

        LogIn = (Button) findViewById(R.id.Login);
        Sign = (Button) findViewById(R.id.Sign);
        //LoginFB = (Button) findViewById(R.id.FBLogin);

        segoe = Typeface.createFromAsset(getAssets(),"fonts/segoeui.ttf");

        LogIn.setTypeface(segoe);
        Sign.setTypeface(segoe);
//        LoginFB.setTypeface(segoe);

        fragmentList = new Vector<Fragment>();
        fragmentList.add(Fragment.instantiate(this, Tutorial1.class.getName()));
        fragmentList.add(Fragment.instantiate(this, Tutorial2.class.getName()));
        fragmentList.add(Fragment.instantiate(this, Tutorial3.class.getName()));
        fragmentList.add(Fragment.instantiate(this, Tutorial4.class.getName()));

        tut1 = (RadioButton) findViewById(R.id.tut1);
        tut2 = (RadioButton) findViewById(R.id.tut2);
        tut3 = (RadioButton) findViewById(R.id.tut3);
        tut4 = (RadioButton) findViewById(R.id.tut4);

        Layout.setBackgroundResource(R.drawable.background_welcome_tut1);

        pageAdapter = new PageAdapter(this.getSupportFragmentManager(),fragmentList);
        viewPager = (ViewPager) findViewById(R.id.tutorialPages);
        viewPager.setAdapter(pageAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (viewPager.getCurrentItem() == 0) {
                    tut1.setChecked(true);
                    Layout.setBackgroundResource(R.drawable.background_welcome_tut1);
                }
                if (viewPager.getCurrentItem() == 1) {
                    tut2.setChecked(true);
                    Layout.setBackgroundResource(R.drawable.background_welcome_tut2);
                }
                if (viewPager.getCurrentItem() == 2) {
                    tut3.setChecked(true);
                    Layout.setBackgroundResource(R.drawable.background_welcome_tut3);
                }
                if (viewPager.getCurrentItem() == 3) {
                    tut4.setChecked(true);
                    Layout.setBackgroundResource(R.drawable.background_welcome_tut4);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        final  LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));
        FacebookSdk.setApplicationId("481634788674524");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code

                        /*GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject me, GraphResponse response) {
                                        if (response.getError() != null) {
                                            // handle error
                                        } else {
                                            String email = me.optString("email");
                                            String id = me.optString("id");
                                            Log.e("Email: ",email);
                                            // send email and id to your web server


                                        }
                                    }
                                });*/

                        AccessToken accessToken = loginResult.getAccessToken();
                        GraphRequest request = GraphRequest.newMeRequest(
                                accessToken,
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        // Insert your code here
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,first_name,last_name,email");
                        request.setParameters(parameters);
                        request.executeAsync();

                        /*AccessToken token = loginResult.getAccessToken();
                        com.facebook.Profile profile = Profile.getCurrentProfile();
                        String sName = profile.getFirstName();
                        String sLast =  profile.getLastName();


                        final String sEmail =profile. ;
                        final String sPass = profile.getId();
                        final ParseUser user = new ParseUser();

                        user.setUsername(sName);
                        user.setPassword(sPass);
                        user.setEmail(sEmail);

                        final ParseObject User = new ParseObject("User");

                        user.put("Username", sName);
                        user.put("FirstName", sName);
                        user.put("LastName", sLast);

                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e==null){
                                    User.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e==null){
                                                Intent i = new Intent(WelcomeScreen.this, SignPayment.class);
                                                startActivity(i);
                                                Toast.makeText(getApplicationContext(), "Welcome ", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Log.e("Error in class User: ", e.getMessage());
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(getApplicationContext(),"The email " + sEmail + "is already taken", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                        startActivity(new Intent(WelcomeScreen.this, FbLogin.class));*/
                        Toast.makeText(getApplicationContext(), "LOGGED", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCancel () {
                                        // App code
                                        Toast.makeText(getApplicationContext(), "Canceled, Check Your Connection", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError (FacebookException exception){
                                        // App code
                                        Toast.makeText(getApplicationContext(), "An Error Occurred", Toast.LENGTH_SHORT).show();
                                    }
                                });
            }
        });


    }

    private String constructWelcomeMessage(Profile profile) {
        StringBuffer stringBuffer = new StringBuffer();
        if (profile != null) {
            stringBuffer.append("Welcome " + profile.getName());
        }
        return stringBuffer.toString();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void showPage (View view){
        if (tut1.isChecked()){
            viewPager.setCurrentItem(0);
        }
        if (tut2.isChecked()){
            viewPager.setCurrentItem(1);
        }
        if (tut3.isChecked()){
            viewPager.setCurrentItem(2);
        }
        if (tut4.isChecked()){
            viewPager.setCurrentItem(3);
        }
    }

    public void Login (View view){
        Intent i = new Intent(this,Login.class);
        startActivity(i);

    }
    public void Sign (View view){
        Intent i = new Intent(this,Sign.class);
        startActivity(i);

    }

    /*@Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }*/

}