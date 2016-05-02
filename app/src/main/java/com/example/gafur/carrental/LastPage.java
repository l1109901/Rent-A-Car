package com.example.gafur.carrental;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gafur on 5/1/2016.
 */
public class LastPage extends Activity{

    private String model,city,type,cost,date_time1,date_time2;
    private TextView tvmodel,tvcity,tvtype,tvcost,tvdate1,tvdate2;
    private Spinner banknames;
    private EditText etid,etname,etsurname,etcreditcard;
    private String filename1 = "logfile.txt";
    private String filename2 = "cars.txt";
    private File  file=new File(filename2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_layout);

        Bundle extras=getIntent().getExtras();
        model=extras.getString("model");
        city=extras.getString("city");
        type=extras.getString("type");
        cost=extras.getString("cost");
        date_time1=extras.getString("date_and_time1");
        date_time2=extras.getString("date_and_time2");

        tvmodel=(TextView)findViewById(R.id.tv_model);
        tvcity=(TextView)findViewById(R.id.tv_city);
        tvtype=(TextView)findViewById(R.id.tv_type);
        tvcost=(TextView)findViewById(R.id.tv_cost);
        tvdate1=(TextView)findViewById(R.id.tv_rent_date);
        tvdate2=(TextView)findViewById(R.id.tv_return_date);

        tvmodel.setText(model);
        tvcity.setText(city);
        tvtype.setText(type);
        tvcost.setText(cost);
        tvdate1.setText(date_time1);
        tvdate2.setText(date_time2);

        banknames=(Spinner)findViewById(R.id.spinner_bank_names);
        // Spinner Drop down elements
        List<String> banks = new ArrayList<String>();
        banks.add("ISBANK");
        banks.add("ZIRAAT");
        banks.add("FINANSBANK");

        // Creating adapter for spinner
        ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, banks);
        banknames.setAdapter(bankAdapter);

        etid=(EditText)findViewById(R.id.et_id);
        etname=(EditText)findViewById(R.id.et_name);
        etsurname=(EditText)findViewById(R.id.et_surname);
        etcreditcard=(EditText)findViewById(R.id.et_credit_card_number);
    }

    public void logOut(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void gotoMain(View view){
        startActivity(new Intent(this, MainActivityPage.class));
    }

    public void gotoMainConfirm(View view){
        String id=etid.getText().toString();
        String name=etname.getText().toString();
        String surname=etsurname.getText().toString();
        String cardnumber=etcreditcard.getText().toString();
        String bankname=banknames.getSelectedItem().toString();
        String yenisatir=" ";

        // check if any of the fields are vaccant
        if(id.equals("")||name.equals("")||surname.equals("")||cardnumber.equals(""))
        {
            Toast.makeText(getApplicationContext(), "One or more of field is empty!", Toast.LENGTH_LONG).show();
        }
        else{
            //do anything

            // try to write the content
            try {
                // open txtfile for writing
                FileOutputStream out =
                        openFileOutput(filename1, Context.MODE_APPEND);
                // write the contents on mySettings to the file
                out.write("#".getBytes());//yeni kayıt baslangici
                out.write(id.getBytes());//id
                out.write(yenisatir.getBytes());
                out.write(name.getBytes());//name
                out.write(yenisatir.getBytes());
                out.write(surname.getBytes());//surname
                out.write(yenisatir.getBytes());
                out.write(cardnumber.getBytes());//card number
                out.write(yenisatir.getBytes());
                out.write(bankname.getBytes());//bankname
                out.write(yenisatir.getBytes());
                out.write(model.getBytes());//model
                out.write(yenisatir.getBytes());
                out.write(city.getBytes());//city
                out.write(yenisatir.getBytes());
                out.write(type.getBytes());//type
                out.write(yenisatir.getBytes());
                out.write(cost.getBytes());//cost
                out.write(yenisatir.getBytes());
                out.write(date_time1.getBytes());//rent date
                out.write(yenisatir.getBytes());
                out.write(date_time2.getBytes());//return date
                out.write("\n".getBytes());

                // close the file
                out.close();
            } catch (java.io.IOException e) {
                //do something if an IOException occurs.
                e.printStackTrace();
            }

            Toast.makeText(getApplicationContext(), "Logfile Successfully Saved ", Toast.LENGTH_LONG).show();

            //reading cars.txt

            List<String> records=new ArrayList<>();

            try{

                InputStream instream = openFileInput(filename2);

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
                        String[] columns = line.split(" ");
                        if(model.equals(columns[0])&&city.equals(columns[1])&&type.equals(columns[2])&&cost.equals(columns[3])){

                        }else{
                            records.add(line);
                        }
                    }
                }
                // close the file again
                instream.close();
                file.delete();
            }
            catch(Exception e){
                Toast.makeText(this, "There is some error", Toast.LENGTH_LONG).show();
            }

            // try to write the content
            try {
                // open txtfile for writing
                FileOutputStream out =
                        openFileOutput(filename2, Context.MODE_PRIVATE);
                // write the contents on mySettings to the file
                for(String s:records){
                    out.write(s.getBytes());
                    out.write(yenisatir.getBytes());
                }

                // close the file
                out.close();
            } catch (java.io.IOException e) {
                //do something if an IOException occurs.
                e.printStackTrace();
            }

            Toast.makeText(getApplicationContext(), "Available Car File Updated", Toast.LENGTH_LONG).show();

            startActivity(new Intent(this, MainActivityPage.class));
        }
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
