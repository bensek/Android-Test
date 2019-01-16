package com.example.airlineschedule.service.model_airports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralData {
    @SerializedName("AirportResource")
    @Expose
    private AirportResource airportResource;

    public AirportResource getAirportResource() {
        return airportResource;
    }

    public void setAirportResource(AirportResource airportResource) {
        this.airportResource = airportResource;
    }


}
