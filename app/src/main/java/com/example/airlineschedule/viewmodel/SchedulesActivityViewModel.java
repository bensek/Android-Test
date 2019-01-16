package com.example.airlineschedule.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.example.airlineschedule.service.model_airports.Airport;
import com.example.airlineschedule.service.model_airports.AirportResource;
import com.example.airlineschedule.service.model_airports.Airports;
import com.example.airlineschedule.service.model_schedules.Schedule;
import com.example.airlineschedule.service.model_schedules.ScheduleData;
import com.example.airlineschedule.service.model_schedules.ScheduleResource;
import com.example.airlineschedule.service.repository.ApiInterface;
import com.example.airlineschedule.view.ui.Trip;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchedulesActivityViewModel {
    private final Context context;
    private String origin, dest, date;
    private MutableLiveData<List<Schedule>> scheduleList;
    private List<Schedule> sList;

    public SchedulesActivityViewModel(Context context, String origin, String dest, String date) {
        this.context = context;
        this.origin = origin;
        this.dest = dest;
        this.date = date;

    }


    public List<Schedule> getSchedule(){
        if(sList == null){
            sList = new ArrayList<>();

            fetchSchedules();
        }

        return sList;
    }


    public void fetchSchedules() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<ScheduleData> call = api.getSchedules(origin,dest,date, 8, "false");
        call.enqueue(new Callback<ScheduleData>() {
            @Override
            public void onResponse(Call<ScheduleData> call, Response<ScheduleData> response) {

                if(response.isSuccessful()){
                    ScheduleData scheduleData = response.body();
                    ScheduleResource scheduleResource = scheduleData.getScheduleResource();
                    sList = scheduleResource.getScheduleList();

                    Log.d("Data From Lufthansa",
                            "SCHEDULE LIST "+sList.toString());

                }
                if(response.code() ==401){
                    Toast.makeText(context, "Network Error 401", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ScheduleData> call, Throwable t) {
                Log.v("SCHEDULE DATA :","Failed to fetch Data " +t);

            }


        });
    }
}
