package com.ihc.project.HouseAutomation;

import java.util.ArrayList;

public interface DataCommunicationHome {


    public ArrayList<Device> getArrayListDevices();
    public void setArrayListDevices(ArrayList<Device> devices);
    public int getIDButtonDevices();
    public void setIDButtonDevices(int IDButtonDevicesList);

}
