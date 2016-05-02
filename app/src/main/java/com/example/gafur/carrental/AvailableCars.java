package com.example.gafur.carrental;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by gafur on 4/29/2016.
 */
public class AvailableCars extends Activity {

    String filename="cars.txt";
    String city,type,date,hour,minute,city2,date2,hour2,minute2,selectedFromList;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_cars);

        Bundle extras=getIntent().getExtras();
        city=extras.getString("city");
        type=extras.getString("type");
        date=extras.getString("date");
        hour=extras.getString("hour");
        minute=extras.getString("minute");

        city2=extras.getString("city2");
        date2=extras.getString("date2");
        hour2=extras.getString("hour2");
        minute2=extras.getString("minute2");

        List<String> mylist=checkFromFile();
        List<String> carandcost=new ArrayList<>();
        String carline=null;

        for(int i=0;i<mylist.size();i++){
            String columns[]=mylist.get(i).split(" ");
            if(columns[1].equals(city)&&columns[2].equals(type)){//o sehirde o tipde araba varsa ekle
                carline=columns[0]+"\t"+columns[3];//columns[0]-araba modeli, columns[3]-fiyati
                carandcost.add(carline);
            }

        }

        ArrayAdapter adapter1 = new ArrayAdapter<String>(this, R.layout.activity_listview, carandcost);

        final ListView lv = (ListView) findViewById(R.id.all_car_list);

        lv.setAdapter(adapter1);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFromList =lv.getItemAtPosition(position).toString();
                lv.getChildAt(position).setBackgroundColor(Color.BLUE);
                count++;
            }
        });
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
            Toast.makeText(AvailableCars.this, "There is some error", Toast.LENGTH_LONG).show();
        }
        return car_list;
    }

    public void gotoNext(View view){
        if(count!=0){
            String columns[]=selectedFromList.split("\t");//columns[0]=model,columns[1]=cost
            String date_and_time1=date+" "+hour+":"+minute;
            String date_and_time2=date2+" "+hour2+":"+minute2;

            Intent i=new Intent(this,LastPage.class);

            i.putExtra("model",columns[0]);
            i.putExtra("city",city);
            i.putExtra("type",type);
            i.putExtra("cost",columns[1]);
            i.putExtra("date_and_time1",date_and_time1);
            i.putExtra("date_and_time2",date_and_time2);

            startActivity(i);
        }
        else{
            Toast.makeText(AvailableCars.this, "Please select one item!", Toast.LENGTH_LONG).show();
        }

    }

    public void gotoMain(View view){
        startActivity(new Intent(this, MainActivityPage.class));
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
