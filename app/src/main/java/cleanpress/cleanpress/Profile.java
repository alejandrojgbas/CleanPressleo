package cleanpress.cleanpress;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Profile extends ActionBarActivity {


    LinearLayout L1,L2,L3,L4;
    Animation fadeInLeft,fadeOutLeft,fadeInRight,fadeOutRight;
    Button btn1,btn2,btn3;
    int pos;
    Typeface segoe;
    String sL1 = "Your Information",sL2 = "Credit Card Info",sL3 = "Addresses Directory",sL4 = "Preferences";
    private static char currentProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);


        segoe = Typeface.createFromAsset(getAssets(),"fonts/segoeui.ttf");

        addMenu();

        btn1 = (Button) findViewById(R.id.boton1);
        btn2 = (Button) findViewById(R.id.boton2);
        btn3 = (Button) findViewById(R.id.boton3);

        btn1.setText(sL2);
        btn2.setText(sL3);
        btn3.setText(sL4);

        L1 = (LinearLayout) findViewById(R.id.Linear1);
        L2 = (LinearLayout) findViewById(R.id.Linear2);
        L3 = (LinearLayout) findViewById(R.id.Linear3);
        L4 = (LinearLayout) findViewById(R.id.Linear4);


        fadeInLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_left);
        fadeOutLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_left);
        fadeInRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_right);
        fadeOutRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_right);

    }

    public void Button1 (View view){
        if (btn1.getText().toString().equals(sL2) && L1.isShown()){
            pos = 1;
            pickAnimations();
        }
        if (btn1.getText().toString().equals(sL1) && L2.isShown()){
            pos = 2;
            pickAnimations();
        }
        if (btn1.getText().toString().equals(sL1) && L3.isShown()){
            pos = 3;
            pickAnimations();
        }
        if (btn1.getText().toString().equals(sL1) && L4.isShown()){
            pos = 4;
            pickAnimations();
        }
    }

    public void Button2 (View view) {
        if (btn2.getText().toString().equals(sL3) && L1.isShown()){
            pos = 5;
            pickAnimations();
        }
        if (btn2.getText().toString().equals(sL3) && L2.isShown()){
            pos = 6;
            pickAnimations();
        }
        if (btn2.getText().toString().equals(sL2) && L3.isShown()){
            pos = 7;
            pickAnimations();
        }
        if (btn2.getText().toString().equals(sL2) && L4.isShown()){
            pos = 8;
            pickAnimations();
        }
    }

    public void Button3 (View view){
        if (btn3.getText().toString().equals(sL4) && L1.isShown()){
            pos = 9;
            pickAnimations();
        }
        if (btn3.getText().toString().equals(sL4) && L2.isShown()){
            pos = 10;
            pickAnimations();
        }
        if (btn3.getText().toString().equals(sL4) && L3.isShown()){
            pos = 11;
            pickAnimations();
        }
        if (btn3.getText().toString().equals(sL3) && L4.isShown()){
            pos = 12;
            pickAnimations();
        }
    }

    public void pickAnimations (){
        switch(pos){

            case 1:
                L1.startAnimation(fadeOutLeft);
                fadeOutLeft.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L1.setVisibility(View.GONE);
                        L2.setVisibility(View.VISIBLE);
                        L2.startAnimation(fadeInLeft);
                        btn1.setText(sL1);
                        btn2.setText(sL3);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 2:
                L2.startAnimation(fadeOutRight);
                fadeOutRight.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L2.setVisibility(View.GONE);
                        L1.setVisibility(View.VISIBLE);
                        L1.startAnimation(fadeInRight);
                        btn1.setText(sL2);
                        btn2.setText(sL3);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 3:
                L3.startAnimation(fadeOutRight);
                fadeOutRight.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L3.setVisibility(View.GONE);
                        L1.setVisibility(View.VISIBLE);
                        L1.startAnimation(fadeInRight);
                        btn1.setText(sL2);
                        btn2.setText(sL3);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 4:
                L4.startAnimation(fadeOutRight);
                fadeOutRight.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L4.setVisibility(View.GONE);
                        L1.setVisibility(View.VISIBLE);
                        L1.startAnimation(fadeInRight);
                        btn1.setText(sL2);
                        btn2.setText(sL3);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 5:
                L1.startAnimation(fadeOutLeft);
                fadeOutLeft.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L1.setVisibility(View.GONE);
                        L3.setVisibility(View.VISIBLE);
                        L3.startAnimation(fadeInLeft);
                        btn1.setText(sL1);
                        btn2.setText(sL2);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 6:
                L2.startAnimation(fadeOutLeft);
                fadeOutLeft.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L2.setVisibility(View.GONE);
                        L3.setVisibility(View.VISIBLE);
                        L3.startAnimation(fadeInLeft);
                        btn1.setText(sL1);
                        btn2.setText(sL2);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 7:
                L3.startAnimation(fadeOutRight);
                fadeOutRight.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L3.setVisibility(View.GONE);
                        L2.setVisibility(View.VISIBLE);
                        L2.startAnimation(fadeInRight);
                        btn1.setText(sL1);
                        btn2.setText(sL3);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 8:
                L4.startAnimation(fadeOutRight);
                fadeOutRight.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L4.setVisibility(View.GONE);
                        L2.setVisibility(View.VISIBLE);
                        L2.startAnimation(fadeInRight);
                        btn1.setText(sL1);
                        btn2.setText(sL3);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 9:
                L1.startAnimation(fadeOutLeft);
                fadeOutLeft.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L1.setVisibility(View.GONE);
                        L4.setVisibility(View.VISIBLE);
                        L4.startAnimation(fadeInLeft);
                        btn1.setText(sL1);
                        btn2.setText(sL2);
                        btn3.setText(sL3);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 10:
                L2.startAnimation(fadeOutLeft);
                fadeOutLeft.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L2.setVisibility(View.GONE);
                        L4.setVisibility(View.VISIBLE);
                        L4.startAnimation(fadeInLeft);
                        btn1.setText(sL1);
                        btn2.setText(sL2);
                        btn3.setText(sL3);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 11:
                L3.startAnimation(fadeOutLeft);
                fadeOutLeft.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L3.setVisibility(View.GONE);
                        L4.setVisibility(View.VISIBLE);
                        L4.startAnimation(fadeInLeft);
                        btn1.setText(sL1);
                        btn2.setText(sL2);
                        btn3.setText(sL3);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case 12:
                L4.startAnimation(fadeOutRight);
                fadeOutRight.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        L4.setVisibility(View.GONE);
                        L3.setVisibility(View.VISIBLE);
                        L3.startAnimation(fadeInRight);
                        btn1.setText(sL1);
                        btn2.setText(sL2);
                        btn3.setText(sL4);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;

        }

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
        textView.setText("Profile");
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03e1ed")));
    }
}
