package com.ihc.project.HouseAutomation;

public class Device {

    private String nameDevice;
    private int onOff;
    private String deviceType;
    private String nameDivision;
    private String divisionType;


    public Device(String nameDevice, int onOff, String deviceType, String nameDivision, String divisionType) {
        this.nameDevice = nameDevice;
        this.onOff = onOff;
        this.deviceType = deviceType;
        this.nameDivision = nameDivision;
        this.divisionType = divisionType;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public int getOnOff() {
        return onOff;
    }

    public void setOnOff(int onOff) {
        this.onOff = onOff;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getNameDivision() {
        return nameDivision;
    }

    public void setNameDivision(String nameDivision) {
        this.nameDivision = nameDivision;
    }

    public String getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType;
    }
}
