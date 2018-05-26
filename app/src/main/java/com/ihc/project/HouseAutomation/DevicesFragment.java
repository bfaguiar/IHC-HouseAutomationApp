package com.ihc.project.HouseAutomation;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ihc.project.HouseAutomation.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DevicesFragment extends Fragment  {

    FloatingActionButton addDevice;

   /*
    EditText nameDevice;
    EditText nameDivision;
    Switch   switchOnOffDevice;
    Spinner  spTipoDevice;
    Spinner  spTipoDivision;
    Device   device;

    */

     DataCommunicationHome mCallback;


    CardView smartPlugins, smokeDetectors, smartLights, airConditioners, securityCameras, thermostats, electronis, appliances;
    ImageView smartPluginsIMG, smokeDetectorsIMG, smartLightsIMG, airConditionersIMG, securityCamerasIMG, thermostatsIMG, electronisIMG, appliancesIMG;
    static ArrayList<Device> devices = new ArrayList<>();





    public DevicesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_devices, container, false);


        /************************ CARDS **************************************/

        smartPlugins    = view.findViewById(R.id.card_smart_plugins);
        smokeDetectors  = view.findViewById(R.id.card_smoke_detectors);
        smartLights     = view.findViewById(R.id.card_smart_lights);
        airConditioners = view.findViewById(R.id.card_air_conditioners);
        securityCameras = view.findViewById(R.id.card_security_cameras);
        thermostats     = view.findViewById(R.id.card_thermostats);
        electronis      = view.findViewById(R.id.card_Eletronics);
        appliances      = view.findViewById(R.id.card_Appliances);
        smartPlugins.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    showDevicesListFragment(R.id.card_smart_plugins);
                }
                });

        smokeDetectors.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                showDevicesListFragment(R.id.card_smoke_detectors);
            }
        });

        airConditioners.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                showDevicesListFragment(R.id.card_air_conditioners);
            }
        });

        smartLights.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                showDevicesListFragment(R.id.card_smart_lights);
            }
        });

       securityCameras .setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                showDevicesListFragment(R.id.card_security_cameras);
            }
        });

        thermostats.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                showDevicesListFragment(R.id.card_thermostats);
            }
        });

        electronis.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                showDevicesListFragment(R.id.card_Eletronics);
            }
        });

        appliances.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                showDevicesListFragment(R.id.card_Appliances);
            }
        });



        /****************** IMAGEVIEWS *******************************************/

        smartPluginsIMG    = view.findViewById(R.id.smart_plugin_ic);
        smokeDetectorsIMG  = view.findViewById(R.id.smoke_detector_ic);
        smartLightsIMG     = view.findViewById(R.id.lights_ic);
        airConditionersIMG = view.findViewById(R.id.air_conditioner_ic);
        securityCamerasIMG = view.findViewById(R.id.security_cameras_ic);
        thermostatsIMG     = view.findViewById(R.id.thermostat_ic);
        electronisIMG      = view.findViewById(R.id.eletronics_ic);
        appliancesIMG     = view.findViewById(R.id.Appliances_ic);

        Glide.with(this).load(R.drawable.smart_puglin_png).into(smartPluginsIMG);
        Glide.with(this).load(R.drawable.fire_icon_png).into(smokeDetectorsIMG);
        Glide.with(this).load(R.drawable.light_icon_png).into(smartLightsIMG);
        Glide.with(this).load(R.drawable.air_conditioner_png).into(airConditionersIMG);
        Glide.with(this).load(R.drawable.security_camera_png).into(securityCamerasIMG);
        Glide.with(this).load(R.drawable.thermostat_icon_png).into(thermostatsIMG);
         Glide.with(this).load(R.drawable.devices1).into(electronisIMG);
         Glide.with(this).load(R.drawable.appliancespng).into(appliancesIMG);



        return view;

    }



    public void showDevicesListFragment(int rID)
    {
        mCallback.setIDButtonDevices(rID);

        Fragment fr = new DevicesListFragment();

        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);

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
