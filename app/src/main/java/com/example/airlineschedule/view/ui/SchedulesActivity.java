package com.example.airlineschedule.view.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.airlineschedule.R;
import com.example.airlineschedule.service.model_schedules.Schedule;
import com.example.airlineschedule.view.adapter.RecyclerAdapter;
import com.example.airlineschedule.viewmodel.SchedulesActivityViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SchedulesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    List<Schedule> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);
        initRecyclerView();
        Intent tripIntent = getIntent();


    }

    public void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        schedules = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Intent tripIntent = getIntent();
        Trip t = (Trip) tripIntent.getSerializableExtra("Trip");
        Log.v("INTENT DATA", t.newOrigin + " to " +t.newDest);
        SchedulesActivityViewModel viewModel = new SchedulesActivityViewModel(this, t.getNewOrigin(),t.newDest,t.getDate());
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), viewModel.getSchedule());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setSchedulesList(viewModel.getSchedule());

    }

}
