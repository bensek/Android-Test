package com.example.airlineschedule.view.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.airlineschedule.R;
import com.example.airlineschedule.service.repository.RetrofitClientInstance;
import com.example.airlineschedule.viewmodel.MainActivityViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView origin;
    private Button btn_origin, btn_dest, btn_getSchedules;
    private AlertDialog.Builder builderSingle1;
    private AlertDialog.Builder builderSingle2;
    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }



    public void init(){
        btn_origin = findViewById(R.id.btn_origin);
        btn_dest = findViewById(R.id.btn_destination);
        btn_getSchedules = findViewById(R.id.btn_get_schedules);
        trip = new Trip();
        builderSingle1 = new AlertDialog.Builder(MainActivity.this);
        builderSingle1.setTitle("Choose Origin Airport:-");
        builderSingle2 = new AlertDialog.Builder(MainActivity.this);
        builderSingle2.setTitle("Choose Destination Code:-");
        MainActivityViewModel model = new MainActivityViewModel(MainActivity.this);
        List<String> airports = model.getData();

        final ArrayAdapter<String> originAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.select_dialog_singlechoice, airports);

        builderSingle1.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle1.setAdapter(originAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = originAdapter.getItem(which);
                trip.setNewOrigin(strName);
                btn_origin.setText(strName);
            }
        });

        final ArrayAdapter<String> destAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.select_dialog_singlechoice, airports);

        builderSingle2.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderSingle2.setAdapter(originAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = originAdapter.getItem(which);
                trip.setNewDest(strName);
                btn_dest.setText(strName);
            }
        });
    }

    public ArrayList<String> getAirports(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString("AIRPORT CODES", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json,type);
    }
    public void displayAirports(View view) {

        if(view.getId() == R.id.btn_origin){
            builderSingle1.show();
        }else {
            builderSingle2.show();
        }
    }

    public void fetchSchedules(View view) {
        trip.setDate("2019-01-16");
        Intent i = new Intent(MainActivity.this, SchedulesActivity.class);
        i.putExtra("Trip", trip);
        startActivity(i);

    }


}
