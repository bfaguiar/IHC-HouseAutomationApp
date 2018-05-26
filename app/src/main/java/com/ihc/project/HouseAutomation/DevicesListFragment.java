package com.ihc.project.HouseAutomation;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ihc.project.HouseAutomation.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DevicesListFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private List<Device> deviceList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DevicesAdapter mAdapter;
    private RelativeLayout relativeLayout;
    private int buttonR_ID;

    DataCommunicationHome mCallback;


    public DevicesListFragment() {
        this.buttonR_ID = buttonR_ID;
        // Required empty public constructor
    }

    public void setID(int buttonR_ID) {
        this.buttonR_ID = buttonR_ID;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_devices_list, container, false);

        setHasOptionsMenu(true);//Make sure you have this line of code.

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout);
        deviceList = new ArrayList<>();

        mAdapter = new DevicesAdapter(getContext(), deviceList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Device device = deviceList.get(position);
                if(device.getOnOff() == 1) {
                    device.setOnOff(0);
                    mAdapter.notifyDataSetChanged();
                } else if(device.getOnOff() == 0) {
                    device.setOnOff(1);
                    mAdapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT,this );
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        prepareDeviceData();
        return view;

    }

    private void prepareDeviceData() {

        ArrayList<Device> devicesAuxiliar = new ArrayList<Device>();

        Device d1 = new Device("Termostato Bruno", 1, getString(R.string.thermostats_str),  "Quarto do Bruno", "Bed Room");
        devicesAuxiliar.add(d1);
        Device d2 = new Device("Plugin PC", 1, getString(R.string.smart_plugin_str), "Quarto do Bruno", "Bed Room");
        devicesAuxiliar.add(d2);
        Device d3 = new Device("Máquina de Lavar a Roupa", 1, getString(R.string.appliances_st_r), "Quarto do Bruno", "Bed Room");
        devicesAuxiliar.add(d3);
        Device d4 = new Device("Plugin TV", 1, getString(R.string.smart_plugin_str), "Quarto do Bruno", "Bed Room");
        devicesAuxiliar.add(d4);
        Device d5 = new Device("Frigorífico", 1, getString(R.string.appliances_st_r), "Cozinha Central",  "Kitchen");
        devicesAuxiliar.add(d5);

        if(!mCallback.getArrayListDevices().isEmpty());
            devicesAuxiliar.addAll(mCallback.getArrayListDevices());

            int BotaoID = mCallback.getIDButtonDevices();

        for(Device device : devicesAuxiliar) {
            if (BotaoID == R.id.card_air_conditioners)
                if (device.getDeviceType().equals(getString(R.string.air_conditioners_str)))
                    deviceList.add(device);

            if (BotaoID == R.id.card_security_cameras)
                if (device.getDeviceType().equals(getString(R.string.security_cameras_str)))
                    deviceList.add(device);

            if (BotaoID == R.id.card_smart_lights)
                if (device.getDeviceType().equals(getString(R.string.lights_str)))
                    deviceList.add(device);

            if (BotaoID == R.id.card_smoke_detectors)
                if (device.getDeviceType().equals(getString(R.string.smoke_detector_str)))
                    deviceList.add(device);

            if (BotaoID == R.id.card_smart_plugins)
                if (device.getDeviceType().equals(getString(R.string.smart_plugin_str)))
                    deviceList.add(device);

            if (BotaoID == R.id.card_thermostats)
                if (device.getDeviceType().equals(getString(R.string.thermostats_str)))
                    deviceList.add(device);

            if(BotaoID == R.id.card_Eletronics)
                if(device.getDeviceType().equals(getString(R.string.Electronics_st_r)))
                    deviceList.add(device);

            if(BotaoID == R.id.card_Appliances)
                if(device.getDeviceType().equals(getString(R.string.appliances_st_r)))
                    deviceList.add(device);
            if(BotaoID == 0)
                if(device.getNameDivision().equalsIgnoreCase(mCallback.getArrayListDevices().get(0).getNameDivision()))
                    deviceList.add(device);
        }


        mAdapter.notifyDataSetChanged();

    }

    /**
     * callback when recycler view is swiped
     * item will be removed on swiped
     * undo option will be provided in snackbar to restore the item
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof DevicesAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = deviceList.get(viewHolder.getAdapterPosition()).getNameDevice();

            // backup of removed item for undo purpose
            final Device deletedItem = deviceList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(relativeLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            View view = snackbar.getView();
            CoordinatorLayout.LayoutParams params =(CoordinatorLayout.LayoutParams)view.getLayoutParams();
            params.gravity = Gravity.TOP;
            view.setLayoutParams(params);

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (DataCommunicationHome) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }


}
