package com.example.airlineschedule.service.repository;

import com.example.airlineschedule.service.model_airports.GeneralData;
import com.example.airlineschedule.service.model_schedules.Schedule;
import com.example.airlineschedule.service.model_schedules.ScheduleData;
//import com.example.airlineschedule.service.model.Schedule;
//
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    public final String BASE_URL = "https://api.lufthansa.com/v1/";


    @Headers({"Authorization: Bearer fprmcdtpfnkxkxfeqzzrfn3f", "Accept: application/json"})
    @GET("operations/schedules/{origin}/{destination}/{fromDateTime}")
    Call<ScheduleData> getSchedules(
            @Path("origin") String origin,
            @Path("destination") String destination,
            @Path("fromDateTime") String fromDateTime,
            @Query("limit") int limit,
            @Query("directFlights") String directFlights
    );


    @Headers({"Authorization: Bearer fprmcdtpfnkxkxfeqzzrfn3f", "Accept: application/json"})
    @GET("references/airports")
    Call<GeneralData> getGeneralData(
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Query("LHoperated") String lhOperated,
            @Query("lang") String lang
    );
}