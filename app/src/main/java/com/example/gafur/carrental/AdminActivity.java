package com.example.gafur.carrental;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gafur on 4/30/2016.
 */
public class AdminActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Button btn_update;
    private String filename = "cars.txt";

    Spinner model;
    Spinner city;
    Spinner type;
    Spinner cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);

        model=(Spinner)findViewById(R.id.spinner_car_model);
        city=(Spinner)findViewById(R.id.spinner_car_city);
        type=(Spinner)findViewById(R.id.spinner_car_type);
        cost=(Spinner)findViewById(R.id.spinner_car_cost);

        model.setOnItemSelectedListener(this);
        city.setOnItemSelectedListener(this);
        type.setOnItemSelectedListener(this);
        cost.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> models = new ArrayList<String>();
        models.add("BMW");
        models.add("MERCEDES");
        models.add("FERRARI");

        List<String> cities = new ArrayList<String>();
        cities.add("Istanbul");
        cities.add("Ankara");
        cities.add("Bursa");

        List<String> types = new ArrayList<>();
        types.add("Economy");
        types.add("Comfort");
        types.add("Sports");

        List<String> costs = new ArrayList<String>();
        costs.add("250TL");
        costs.add("300TL");
        costs.add("350TL");

        // Creating adapter for spinner
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, models);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        ArrayAdapter<String> costAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, costs);

        model.setAdapter(modelAdapter);
        city.setAdapter(cityAdapter);
        type.setAdapter(typeAdapter);
        cost.setAdapter(costAdapter);

        btn_update=(Button)findViewById(R.id.btn_update);
    }

    public void addCar(View view){

        // Save the Data in Textfile

        String model_str=model.getSelectedItem().toString();
        String city_str=city.getSelectedItem().toString();
        String type_str=type.getSelectedItem().toString();
        String cost_str=cost.getSelectedItem().toString();

        String line=model_str+" "+city_str+" "+type_str+" "+cost_str+"\n";

        // try to write the content
        try {
            // open txtfile for writing
            FileOutputStream out =
                    openFileOutput(filename, Context.MODE_APPEND);
            // write the contents on mySettings to the file
            out.write(line.getBytes());

            // close the file
            out.close();
        } catch (java.io.IOException e) {
            //do something if an IOException occurs.
            e.printStackTrace();
        }

        Toast.makeText(this, "Successfull Adding", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void logOut(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void logFile(View view){
        startActivity(new Intent(this, LogFile.class));
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
