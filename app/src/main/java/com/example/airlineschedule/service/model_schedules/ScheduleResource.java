package com.example.airlineschedule.service.model_schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResource {
    @SerializedName("Schedule")
    @Expose
    private List<Schedule> schedule = null;

    public List<Schedule> getScheduleList() {
        return schedule;
    }

    public void setScheduleList(List<Schedule> schedule) {
        this.schedule = schedule;
    }

}
