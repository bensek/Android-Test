package com.example.airlineschedule.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.airlineschedule.R;
import com.example.airlineschedule.service.model_schedules.Schedule;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<Schedule> schedules;

    public RecyclerAdapter(Context context, List<Schedule> scheduleList) {
        this.context = context;
        this.schedules = scheduleList;
    }

    public void setSchedulesList(List<Schedule> scheduleList) {
        this.schedules = scheduleList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {
//        holder.flightName.setText(schedules.get(position).getFlight().get);
        holder.departureAirport.setText(schedules.get(position).getFlight().getDeparture().getAirportCode());
        holder.arrivalAirport.setText(schedules.get(position).getFlight().getArrival().getAirportCode());
        holder.durationTime.setText(schedules.get(position).getFlight().toString());
//        holder.durationTime.setText(schedules.get(position).getFlight().);

    }

    @Override
    public int getItemCount() {
        if(schedules != null){
            return schedules.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView flightName;
        TextView departureAirport;
        TextView arrivalAirport;
        TextView durationTime;

        public MyviewHolder(View itemView) {
            super(itemView);
            flightName = (TextView)itemView.findViewById(R.id.flight_text_view);
            departureAirport = (TextView)itemView.findViewById(R.id.departure_text_view);
            arrivalAirport = (TextView)itemView.findViewById(R.id.arrival_text_view);
            durationTime = (TextView)itemView.findViewById(R.id.duration_text_view);

        }
    }
}