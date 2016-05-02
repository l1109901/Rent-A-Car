package com.example.gafur.carrental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gafur on 5/1/2016.
 */
public class LogFile extends Activity{

    private String filename="logfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_file_activity);

        List<String> mylist=checkFromFile();
        ArrayAdapter adapter1 = new ArrayAdapter<String>(this, R.layout.activity_listview, mylist);
        final ListView lv = (ListView) findViewById(R.id.all_car_list);
        lv.setAdapter(adapter1);

    }

    public List<String> checkFromFile(){
        // fetch the Password form usernamePassword.txt for respective user name
        //reading text from file

        List<String> car_list=new ArrayList<String>();

        try{

            InputStream instream = openFileInput(filename);

            // if file the available for reading
            if (instream != null) {
                // prepare the file for reading
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);

                String line;

                // read every line of the file into the line-variable, on
                // line at the time
                while (( line = buffreader.readLine()) != null) {
                    // do something with the settings from the file
                    car_list.add(line);
                }
            }
            // close the file again
            instream.close();
        }
        catch(Exception e){
            Toast.makeText(this, "There is some error", Toast.LENGTH_LONG).show();
        }
        return car_list;
    }

    public void gotoMain(View view){
        startActivity(new Intent(this, AdminActivity.class));
    }
    public void logOut(View view){
        startActivity(new Intent(this, MainActivity.class));
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
