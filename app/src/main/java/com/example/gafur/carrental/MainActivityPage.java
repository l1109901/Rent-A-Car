package com.example.gafur.carrental;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by gafur on 4/29/2016.
 */
public class MainActivityPage extends Activity implements OnItemSelectedListener{

    Calendar myCalendar = Calendar.getInstance();
    EditText et_date,date_of_return;

    Spinner spinner;
    Spinner return_spinner;
    Spinner cartype;
    Spinner hour;
    Spinner minute;
    Spinner returnhour;
    Spinner returnminute;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel2();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);


        // Spinner element
        spinner=(Spinner)findViewById(R.id.spinner_cities);
        return_spinner=(Spinner)findViewById(R.id.spinner_cities_return);
        cartype=(Spinner)findViewById(R.id.spinner_types);
        hour=(Spinner)findViewById(R.id.hour);
        minute=(Spinner)findViewById(R.id.minute);
        returnhour=(Spinner)findViewById(R.id.hourofreturn);
        returnminute=(Spinner)findViewById(R.id.minuteofreturn);

        //Spinner click listener
        spinner.setOnItemSelectedListener(this);
        return_spinner.setOnItemSelectedListener(this);
        cartype.setOnItemSelectedListener(this);
        hour.setOnItemSelectedListener(this);
        minute.setOnItemSelectedListener(this);
        returnhour.setOnItemSelectedListener(this);
        returnminute.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> cities = new ArrayList<String>();
        cities.add("Istanbul");
        cities.add("Ankara");
        cities.add("Bursa");

        List<String> types = new ArrayList<>();
        types.add("Economy");
        types.add("Comfort");
        types.add("Sports");

        List<String> hours = new ArrayList<String>();
        hours.add("00");
        hours.add("01");
        hours.add("02");
        hours.add("03");
        hours.add("04");
        hours.add("05");
        hours.add("06");
        hours.add("07");
        hours.add("08");
        hours.add("09");
        hours.add("10");
        hours.add("11");
        hours.add("12");
        hours.add("13");
        hours.add("14");
        hours.add("15");
        hours.add("16");
        hours.add("17");
        hours.add("18");
        hours.add("19");
        hours.add("20");
        hours.add("21");
        hours.add("22");
        hours.add("23");

        List<String> minutes= new ArrayList<String>();
        minutes.add("00");
        minutes.add("15");
        minutes.add("30");
        minutes.add("45");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        ArrayAdapter<String> hourAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hours);
        ArrayAdapter<String> minuteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, minutes);

        // attaching data adapter to spinners
        spinner.setAdapter(dataAdapter);
        return_spinner.setAdapter(dataAdapter);
        cartype.setAdapter(typeAdapter);
        hour.setAdapter(hourAdapter);
        minute.setAdapter(minuteAdapter);
        returnhour.setAdapter(hourAdapter);
        returnminute.setAdapter(minuteAdapter);

        et_date=(EditText)findViewById(R.id.dateofrent);
        et_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivityPage.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        date_of_return=(EditText)findViewById(R.id.dateofreturn);
        date_of_return.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivityPage.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel2() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date_of_return.setText(sdf.format(myCalendar.getTime()));
    }

    public void logOut(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void searchAvailableCars(View view){

        String spinner_str=spinner.getSelectedItem().toString();
        String cartype_str=cartype.getSelectedItem().toString();
        String date_str=et_date.getText().toString();
        String hour_str=hour.getSelectedItem().toString();
        String minute_str=minute.getSelectedItem().toString();

        String return_spinner_str=return_spinner.getSelectedItem().toString();
        String date_of_return_str=date_of_return.getText().toString();
        String returnhour_str=returnhour.getSelectedItem().toString();
        String returnminute_str=returnminute.getSelectedItem().toString();

        Intent i=new Intent(this,AvailableCars.class);
        i.putExtra("city",spinner_str);
        i.putExtra("type",cartype_str);
        i.putExtra("date",date_str);
        i.putExtra("hour",hour_str);
        i.putExtra("minute",minute_str);

        i.putExtra("city2",return_spinner_str);
        i.putExtra("date2",date_of_return_str);
        i.putExtra("hour2",returnhour_str);
        i.putExtra("minute2",returnminute_str);

        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}