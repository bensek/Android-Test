package com.example.airlineschedule.service.model_airports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Airports {

    @SerializedName("Airport")
    @Expose
    private List<Airport> airport;

    public List<Airport> getAirportList() {
        return airport;
    }

    public void setAirportList(List<Airport> airport) {
        this.airport = airport;
    }

}