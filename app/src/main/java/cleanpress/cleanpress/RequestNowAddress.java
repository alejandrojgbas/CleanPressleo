package cleanpress.cleanpress;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class RequestNowAddress extends ActionBarActivity {

    Typeface segoe;
    String pAddress,pZip,pCity,pDate,pHour,dAddress,dZip,dCity,dDate,dHour,dNotes;
    EditText address,city,zip,specialInstr;
    TextView title1,title2,spinnerTitle,date,day;
    Spinner hours_select,location;
    ImageView clear;
    CheckBox confirm;
    Button sendOrder;
    String[] items;
    MySpinnerAdapter adapter;
    ArrayList<String> Home,Work,Other;
    MyHoursSpinnerAdapter HourAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_now_second);
        addMenu();

        Home = new ArrayList<String>();
        Work = new ArrayList<String>();
        Other = new ArrayList<String>();

        ParseQuery<ParseObject> home = new ParseQuery<ParseObject>("Addresses");
        home.whereEqualTo("username", ParseUser.getCurrentUser().getUsername()).whereEqualTo("Location","Home");
        home.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                for (ParseObject result : list){
                    String sAddress = result.getString("Address");
                    String sCity = result.getString("CityState");
                    String sZip = result.getString("ZipCode");

                    Home.add(0,sAddress);
                    Home.add(1,sCity);
                    Home.add(2,sZip);
                }
            }

        });

        ParseQuery<ParseObject> work = new ParseQuery<ParseObject>("Addresses");
        work.whereEqualTo("username", ParseUser.getCurrentUser().getUsername()).whereEqualTo("Location","Work");
        work.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                for (ParseObject result : list) {
                    String sAddress = result.getString("Address");
                    String sCity = result.getString("CityState");
                    String sZip = result.getString("ZipCode");

                    Work.add(0, sAddress);
                    Work.add(1, sCity);
                    Work.add(2, sZip);
                }
            }

        });

        ParseQuery<ParseObject> other = new ParseQuery<ParseObject>("Addresses");
        other.whereEqualTo("username", ParseUser.getCurrentUser().getUsername()).whereEqualTo("Location", "Other");
        other.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                for (ParseObject result : list){
                    String sAddress = result.getString("Address");
                    String sCity = result.getString("CityState");
                    String sZip = result.getString("ZipCode");

                    Other.add(0,sAddress);
                    Other.add(1,sCity);
                    Other.add(2,sZip);
                }
            }

        });


        segoe = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");

        pAddress = getIntent().getExtras().getString("pAddress");
        pZip = getIntent().getExtras().getString("pZip");
        pCity = getIntent().getExtras().getString("pCity");
        pDate = getIntent().getExtras().getString("pDate");
        pHour = getIntent().getExtras().getString("pHour");

        title1 = (TextView) findViewById(R.id.title1);
        address = (EditText) findViewById(R.id.dAddress);
        zip = (EditText) findViewById(R.id.dZip);
        city = (EditText) findViewById(R.id.dCity);
        specialInstr = (EditText) findViewById(R.id.specialNotes);
        clear = (ImageView) findViewById(R.id.clear);
        spinnerTitle = (TextView) findViewById(R.id.spinnerText);
        location = (Spinner) findViewById(R.id.select_loc);
        confirm = (CheckBox) findViewById(R.id.Confirm);
        title2 = (TextView) findViewById(R.id.title2);
        date = (TextView) findViewById(R.id.Date);
        day = (TextView) findViewById(R.id.Day);
        hours_select = (Spinner) findViewById(R.id.Hour);
        sendOrder = (Button) findViewById(R.id.button);

        title1.setTypeface(segoe);
        title2.setTypeface(segoe);
        address.setTypeface(segoe);
        zip.setTypeface(segoe);
        city.setTypeface(segoe);
        specialInstr.setTypeface(segoe);
        spinnerTitle.setTypeface(segoe);
        confirm.setTypeface(segoe);
        date.setTypeface(segoe);
        day.setTypeface(segoe);
        sendOrder.setTypeface(segoe);

        address.setText(pAddress);
        zip.setText(pZip);
        city.setText(pCity);
        disableEdit(city);

        items = new String[]{"Don't Save","Home","Work","Other"};
        adapter = new MySpinnerAdapter(getApplicationContext(),R.layout.titles_spinner,items);
        location.setAdapter(adapter);


        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        enableEdit(address);
                        enableEdit(city);
                        enableEdit(zip);

                        clear.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        if (Home.size() == 3) {
                            address.setText(Home.get(0));
                            city.setText(Home.get(1));
                            zip.setText(Home.get(2));

                            disableEdit(address);
                            disableEdit(zip);
                            disableEdit(city);
                            clear.setVisibility(View.GONE);
                        }
                        break;
                    case 2:
                        if (Work.size() == 3) {
                            address.setText(Work.get(0));
                            city.setText(Work.get(1));
                            zip.setText(Work.get(2));

                            disableEdit(address);
                            disableEdit(zip);
                            disableEdit(city);
                            clear.setVisibility(View.GONE);
                        }
                        break;
                    case 3:
                        if (Other.size() == 3) {
                            address.setText(Other.get(0));
                            city.setText(Other.get(1));
                            zip.setText(Other.get(2));

                            disableEdit(address);
                            disableEdit(zip);
                            disableEdit(city);
                            clear.setVisibility(View.GONE);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ParseQuery<ParseObject> spinnerHours = new ParseQuery<ParseObject>("Hours");
        spinnerHours.orderByAscending("createdAt");
        spinnerHours.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ArrayList<String> ch = new ArrayList<String>();
                    for (ParseObject object : list) {
                        String hour = object.getString("PM");
                        ch.add(hour);
                    }

                    HourAdapter = new MyHoursSpinnerAdapter(getApplicationContext(), R.layout.titles_spinner, ch);
                    hours_select.setAdapter(HourAdapter);
                } else {
                    Log.e("Error: ", e.getMessage());
                }
            }
        });


        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ad = address.getText().toString();
                if (ad.length() >= 4){
                    clear.setVisibility(View.VISIBLE);
                } else {
                    clear.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }

    private void disableEdit (EditText editText) {

        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setTextColor(Color.rgb(193, 194, 199));
        editText.setCursorVisible(false);
        editText.setEnabled(false);
    }

    private void enableEdit (EditText editText) {

        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setTextColor(Color.rgb(68, 68, 68));
        editText.setCursorVisible(true);
        editText.setEnabled(true);

    }

    private void addMenu() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.login_title, null);
        TextView textView = (TextView) view.findViewById(R.id.mytext);
        textView.setWidth(getWindow().getWindowManager().getDefaultDisplay().getWidth());
        textView.setPadding(0, 0, 50, 0);
        textView.setGravity(Gravity.CENTER);

        Typeface segoeui = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");
        textView.setTypeface(segoeui);
        textView.setText("Your Order");
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03e1ed")));
    }

    public void placeOrder (View view){

        if (date.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please select a date",Toast.LENGTH_SHORT).show();
        } else {
            dDate = date.getText().toString();

            Date today = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy",Locale.ENGLISH);
            try {
                Date selectedDate = simpleDateFormat.parse(dDate);
                int comp = today.compareTo(selectedDate);
                if (comp <= 0){
                    Toast.makeText(getApplicationContext(),"Invalid delivery Date",Toast.LENGTH_SHORT).show();
                } else {
                    dHour = hours_select.getSelectedItem().toString();
                    dCity = pCity;
                    dAddress = address.getText().toString();
                    dZip = zip.getText().toString();
                    dNotes = specialInstr.getText().toString();

                    ParseQuery<ParseObject> order_number = new ParseQuery<ParseObject>("Order");
                    order_number.orderByDescending("OrderNumber");
                    order_number.getFirstInBackground(new GetCallback<ParseObject>() {

                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            if (e == null) {
                                Log.e("Order Number: ", parseObject.getInt("OrderNumber") + "");
                                Log.e("Object ID: ", parseObject.getObjectId());

                                //Get the first object on the column arranged by Descending
                                int cont = parseObject.getInt("OrderNumber") + 1;

                                //Now that we have an order number, we can proccess a new order.
                                // Set the data required for send it to the database.
                                ParseObject submit_order = new ParseObject("Order");
                                submit_order.put("PickupAddress", pAddress);
                                submit_order.put("PickCity", pCity);
                                submit_order.put("PickupZipcode", pZip);
                                submit_order.put("Pickupdate", pDate);
                                submit_order.put("PickHour", pHour);
                                submit_order.put("username", ParseUser.getCurrentUser().getUsername());
                                submit_order.put("DeliveryAddress", dAddress);
                                submit_order.put("DeliveryCity", dCity);
                                submit_order.put("DeliveryZipcode", dZip);
                                submit_order.put("DeliverySpecialNotes", dNotes);
                                submit_order.put("Dropoffdate", dDate);
                                submit_order.put("PM", dHour);
                                submit_order.put("OrderNumber", cont);
                                submit_order.put("Verify", false);
                                submit_order.put("Paid", false);
                                submit_order.put("Done", false);
                                submit_order.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null){
                                            Toast.makeText(getApplicationContext(),"Check your new order in your Orders Screen",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(RequestNowAddress.this, Dashboard.class));
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }


                }
    }

    public void confirmOrder (View view) {

        if (confirm.isChecked()){
            sendOrder.setEnabled(true);
            colorButton();
        } else {
            sendOrder.setEnabled(false);
            colorButton();
        }
    }

    private void colorButton () {
        if (sendOrder.isEnabled()) {
            sendOrder.setBackgroundColor(Color.rgb(127,230,139));
        } else {
            sendOrder.setBackgroundColor(Color.rgb(193,194,199));
        }
    }

    public void clearText (View view){
        address.setText("");
        zip.setText("");
        specialInstr.setText("");
    }

    public void setDate (View view) {
        Calendar cal = Calendar.getInstance();

        DatePickerDialog datePicker = new DatePickerDialog(this, datePickerListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));
        //Create a cancel button and set the title of the dialog.
        datePicker.setCancelable(false);
        datePicker.setTitle("Select the date");
        datePicker.show();
    }

    private static class MyHoursSpinnerAdapter extends ArrayAdapter<String> {

        public MyHoursSpinnerAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
        }

        Typeface segoeuit = Typeface.createFromAsset(getContext().getAssets(),"fonts/segoeui.ttf");
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(segoeuit);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(segoeuit);
            return view;
        }

    }
    private static class MySpinnerAdapter extends ArrayAdapter<String> {

        public MySpinnerAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }

        Typeface segoeuit = Typeface.createFromAsset(getContext().getAssets(),"fonts/segoeui.ttf");
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(segoeuit);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(segoeuit);
            return view;
        }

    }

    //PickerDialog for the DeliveryPicker
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is called, below method will be called.
        // The arguments will be working to get the Day of Week to show it in a special TextView for it.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            date.setText(month1 + "/" + day1 + "/" + year1);
            day.setText(DateFormat.format("EEEE", new Date(selectedYear, selectedMonth, selectedDay - 1)).toString());
        }
    };

}
