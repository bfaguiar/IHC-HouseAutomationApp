package com.ihc.project.HouseAutomation;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.ihc.project.HouseAutomation.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DevicesFragment extends Fragment {

    FloatingActionButton addDevice;
    EditText nameDevice;
    EditText nameDivision;
    Switch   switchOnOffDevice;
    Spinner  spTipoDevice;
    Spinner  spTipoDivision;
    Device   device;

    ArrayList<Device> devices;

    public DevicesFragment() {
        devices = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_devices, container, false);

        addDevice = view.findViewById(R.id.add_device_buttom);
        addDevice.setOnClickListener( new Button.OnClickListener() {
            @Override public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                final View viewDialog   = getLayoutInflater().inflate(R.layout.dialog_add_device, null);

                nameDevice        = (EditText) viewDialog.findViewById(R.id.name_device);
                switchOnOffDevice = (Switch)   viewDialog.findViewById(R.id.switch_dialog);
                nameDivision      = (EditText) viewDialog.findViewById(R.id.name_division);

                //###########################  Spinner categorias devices #######################################
                spTipoDevice      = (Spinner) viewDialog.findViewById(R.id.spinnerTipoDevice);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.device_cat_items));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoDevice.setAdapter(adapter);

                //###########################  Spinner categorias Divisions #######################################
                spTipoDivision      = (Spinner) viewDialog.findViewById(R.id.spinnerDivisao);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.divisions_cat_items));
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoDivision.setAdapter(adapter2);

                // ################################## BUTTONS ########################################################

                mBuilder.setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        String deviceSTR   = nameDevice.getText().toString();
                        String divisionSTR = nameDivision.getText().toString();
                        String deviceCat   = spTipoDevice.getSelectedItem().toString();
                        String divisionCat = spTipoDivision.getSelectedItem().toString();

                        int onoff;
                        if (switchOnOffDevice.isChecked()) onoff = 1;
                        else onoff = 0;

                        if(!deviceSTR.isEmpty() && !divisionSTR.isEmpty() && !deviceCat.equalsIgnoreCase("Choose a device type...") && !divisionCat.equalsIgnoreCase("Choose a division type...")){
                            Toast.makeText(getContext(), "Device created", Toast.LENGTH_SHORT).show();
                            device = new Device(deviceSTR, onoff, deviceCat, divisionSTR, divisionCat);
                            devices.add(device);
                            dialogInterface.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Device not created", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                        }
                    }
                });

                mBuilder.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setView(viewDialog);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.RED);
            }
        });

        return view;

    }
}
