package cleanpress.cleanpress;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class Sign extends ActionBarActivity {

    Typeface segoe;
    EditText Name,Last,Email,cEmail,Pass,cPass;
    String sName,sLast,sEmail,s_cEmail,sPass,s_cPass;
    Button bSignUp;
    CheckBox Terms;
    TextView linkTerms,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_screen);
        addMenu();

        segoe = Typeface.createFromAsset(getAssets(),"fonts/segoeui.ttf");

        Name = (EditText) findViewById(R.id.SignName);
        Last = (EditText) findViewById(R.id.SignLastname);
        Email = (EditText) findViewById(R.id.SignEmail);
        cEmail = (EditText) findViewById(R.id.cSignEmail);
        Pass = (EditText) findViewById(R.id.SignPass);
        cPass = (EditText) findViewById(R.id.cSignPass);
        bSignUp = (Button) findViewById(R.id.bSign);
        Terms = (CheckBox) findViewById(R.id.Terms);
        linkTerms = (TextView) findViewById(R.id.TermsLink);
        title = (TextView) findViewById(R.id.titleSign);

        Name.setTypeface(segoe);
        Last.setTypeface(segoe);
        Email.setTypeface(segoe);
        cEmail.setTypeface(segoe);
        Pass.setTypeface(segoe);
        cPass.setTypeface(segoe);
        Terms.setTypeface(segoe);
        bSignUp.setTypeface(segoe);
        linkTerms.setTypeface(segoe);
        title.setTypeface(segoe);

        cPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bSignUp.setEnabled(
                        !Name.getText().toString().trim().isEmpty()
                        && !Last.getText().toString().trim().isEmpty()
                        && !Email.getText().toString().trim().isEmpty()
                        && !cEmail.getText().toString().trim().isEmpty()
                        && !Pass.getText().toString().trim().isEmpty()
                        && !cPass.getText().toString().trim().isEmpty()
                );
                if(bSignUp.isEnabled()){
                    bSignUp.setBackgroundColor(Color.rgb(127,230,139));
                }
                else {
                    bSignUp.setBackgroundColor(Color.rgb(193,194,199));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void SignUp(View view){
        if (!Terms.isChecked()){
            Toast.makeText(getApplicationContext(),"Must agree our Terms and Conditions to continue",Toast.LENGTH_SHORT).show();
        } else {

            sName = Name.getText().toString();
            sLast = Last.getText().toString();
            sEmail = Email.getText().toString();
            s_cEmail = cEmail.getText().toString();
            sPass = Pass.getText().toString();
            s_cPass = cPass.getText().toString();


            if (!s_cEmail.equals(sEmail)){
                if (!s_cPass.equals(sPass)){
                    Toast.makeText(getApplicationContext(),"Passwords doesn't match",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"E-mails doesn't match",Toast.LENGTH_SHORT).show();
            } else {
                ParseUser user = new ParseUser();
                user.setUsername(sEmail);
                user.setPassword(sPass);
                user.setEmail(sEmail);

                final ParseObject User = new ParseObject("User");
                User.put("Username",sEmail);
                User.put("FirstName",sName);
                User.put("LastName",sLast);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            User.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e==null){
                                        Intent i = new Intent(Sign.this,SignPayment.class);
                                        startActivity(i);
                                        Toast.makeText(getApplicationContext(),"Welcome!",Toast.LENGTH_SHORT).show();

                                    } else {
                                        Log.e("Error in class User: ",e.getMessage());
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(),"The email "+sEmail+" is already taken",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    }

    public void showTerms(View view){


    }

    private void addMenu() {

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.login_title, null);
        TextView textView = (TextView) view.findViewById(R.id.mytext);
        textView.setWidth(getWindow().getWindowManager().getDefaultDisplay().getWidth());
        textView.setPadding(0, 0, 50, 0);
        textView.setGravity(Gravity.CENTER);

        Typeface segoeui = Typeface.createFromAsset(getAssets(),"fonts/segoeui.ttf");
        textView.setTypeface(segoeui);
        textView.setText("Sign up");
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03e1ed")));
    }
}
