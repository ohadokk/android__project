package com.example.ohad.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ohad on 7/31/2017.
 */

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//the class is functional but avenchely was not used
// the class can create chooser for a room the can go to
// it send a udp request for the robot to read a chosen txt file
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++

public class Choose_Room extends Activity {

    Spinner spinner;
    Button Addbutton;
    EditText GetValue;

    Joy__udp Joy__udp;
    String[] ListElements = new String[] {
            "",
            "name",
            "nameg"
    };
    boolean Flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_room);

        spinner = (Spinner) findViewById(R.id.spinner);
        Addbutton = (Button) findViewById(R.id.button1);
        GetValue = (EditText)findViewById(R.id.editText1);
        Flag=false;
        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (Choose_Room.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        spinner.setAdapter(adapter);

        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListElementsArrayList.add(GetValue.getText().toString());

                adapter.notifyDataSetChanged();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                if(Flag) {

                    Joy__udp = new Joy__udp("q",1);
                    Joy__udp.start();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Joy__udp = new Joy__udp(arg0.getItemAtPosition(arg2).toString(),1);
                    Joy__udp.start();

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "go to " + arg0.getItemAtPosition(arg2).toString(), duration);
                    toast.show();
                }
                Flag=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
    }
}