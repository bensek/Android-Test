package com.example.airlineschedule.service.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.airlineschedule.service.model_airports.Airport;
import com.example.airlineschedule.service.model_airports.AirportResource;
import com.example.airlineschedule.service.model_airports.Airports;
import com.example.airlineschedule.service.model_airports.GeneralData;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static final String BASE_URL = "https://api.lufthansa.com/v1/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.lufthansa.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiInterface api = retrofit.create(ApiInterface.class);

    public void getGeneralData(final Context context) {
        Call<GeneralData> call = api.getGeneralData(20,0,"False","en");
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
//                    List<String> airportCodes = new ArrayList<String>();
//
//                    for(Airport a : airportList){
//                        airportCodes.add(a.getAirportCode());
//                    }
                    //Save airports codes in SharedPreferences
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();
                    Gson gson = new Gson();
                    String jsonAirportCodes = gson.toJson(airportList);
                    editor.putString("AIRPORT CODES", jsonAirportCodes);
                    editor.apply();
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
