package com.ihc.project.HouseAutomation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.MyViewHolder> {

    private Context context;
    private List<Device> devicesList;
    private Device device;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout viewBackground, viewForeground;
        public TextView deviceName, deviceType, divisionName, divisionType, deviceKwh;
        public View     viewLine;
        public TextView deviceOn;


        public MyViewHolder(View view) {
            super(view);
            deviceName   = (TextView) view.findViewById(R.id.device_name);
            deviceType   = (TextView) view.findViewById(R.id.device_type);
            divisionName = (TextView) view.findViewById(R.id.div_name);
            divisionType = (TextView) view.findViewById(R.id.div_type);
            deviceOn     =            view.findViewById(R.id.device_on);
            viewBackground  = view.findViewById(R.id.view_background);
            viewForeground  = view.findViewById(R.id.view_foreground);
            viewLine        = view.findViewById(R.id.view_sep);
            deviceKwh       = view.findViewById(R.id.device_kwh);
        }
    }

    public  DevicesAdapter(Context context, List<Device> devicesList) {

        this.context = context;
        this.devicesList = devicesList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        device = devicesList.get(position);
        holder.deviceName.setText( device.getNameDevice());
        holder.deviceType.setText(device.getDeviceType());
        holder.divisionName.setText(device.getNameDivision());
        holder.divisionType.setText(device.getDivisionType());
        holder.viewLine.setBackgroundColor(Color.parseColor("#e0e0e0"));;

        if(device.getOnOff() == 1) {
            holder.deviceOn.setText("Device is on");
            holder.deviceKwh.setText("Power Consumption: " + Integer.toString((int) (Math.random()*200)+10) + " W/h");
            holder.deviceOn.setTextColor(Color.parseColor("#136017"));}
        else{

            holder.deviceKwh.setText("Power Consumption: 0" +" W/h");
            holder.deviceOn.setText("Device is off");
        holder.deviceOn.setTextColor(Color.RED);}


    }

    @Override
    public int getItemCount() {
        return devicesList.size();
    }

    public void removeItem(int position) {

        devicesList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Device device, int position) {
        devicesList.add(position, device);
        // notify item added by position
        notifyItemInserted(position);
    }


}
