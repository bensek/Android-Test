package com.example.airlineschedule.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.airlineschedule.service.model_airports.Airport;
import com.example.airlineschedule.service.model_airports.AirportResource;
import com.example.airlineschedule.service.model_airports.Airports;
import com.example.airlineschedule.service.model_airports.GeneralData;
import com.example.airlineschedule.service.repository.ApiInterface;
import com.example.airlineschedule.view.ui.MainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends ViewModel {
    private final Context context;
    private List<String> airportCodes;

    public MainActivityViewModel(Context c) {
        this.context = c;
    }


    public List<String> getData(){
        if(airportCodes == null){
            airportCodes = new ArrayList<>();

            fetchGeneralData();
        }

        return airportCodes;
    }


    public void fetchGeneralData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<GeneralData> call = api.getGeneralData(50,0,"False","en");
        call.enqueue(new Callback<GeneralData>() {
            @Override
            public void onResponse(Call<GeneralData> call, Response<GeneralData> response) {

                if(response.isSuccessful()){
                    GeneralData generalData = response.body();
                    AirportResource airportResource = generalData.getAirportResource();
                    Airports airports = airportResource.getAirports();
                    List<Airport> airportList = airports.getAirportList();
                    Log.d("Data From Lufthansa",
                            "AIRPORTS LIST "+airportList.size());
                    //Create a String array to store all Airport codes
                    for(Airport a : airportList){
                        airportCodes.add(a.getAirportCode());
                    }
                }
                if(response.code() ==401){
                    Toast.makeText(context, "Network Error 401", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<GeneralData> call, Throwable t) {
                Log.v("GENERAL DATA :","Failed to fetch Data " +t);
            }
        });
    }
}
