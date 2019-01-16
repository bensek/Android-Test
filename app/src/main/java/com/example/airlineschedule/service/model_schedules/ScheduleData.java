package com.example.airlineschedule.service.model_schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleData {
        @SerializedName("ScheduleResource")
        @Expose
        private ScheduleResource scheduleResource;

        public ScheduleResource getScheduleResource() {
            return scheduleResource;
        }

        public void setScheduleResource(ScheduleResource scheduleResource) {
            this.scheduleResource = scheduleResource;
        }

}
