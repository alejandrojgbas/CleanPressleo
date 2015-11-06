package cleanpress.cleanpress;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

public class Tutorials extends AppCompatActivity {

    ViewPager viewPager;
    List<Fragment> fragmentList;
    PageAdapter pageAdapter;
    RadioButton tut1,tut2,tut3,tut4;
    LinearLayout Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorials_screen);
        addMenu();

        Layout = (LinearLayout) findViewById(R.id.Layout);

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

        pageAdapter = new PageAdapter(this.getSupportFragmentManager(), fragmentList);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorials, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        textView.setText("About");
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03e1ed")));
    }
}
