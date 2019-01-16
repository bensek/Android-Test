package com.example.airlineschedule.service.model_schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {
    @SerializedName("Departure")
    @Expose
    private Departure departure;
    @SerializedName("Arrival")
    @Expose
    private Arrival arrival;

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }
}
