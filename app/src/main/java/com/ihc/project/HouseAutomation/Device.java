package com.ihc.project.HouseAutomation;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Objects;

public class Device implements Parcelable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return onOff == device.onOff &&
                Objects.equals(nameDevice, device.nameDevice) &&
                Objects.equals(deviceType, device.deviceType) &&
                Objects.equals(nameDivision, device.nameDivision) &&
                Objects.equals(divisionType, device.divisionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameDevice, onOff, deviceType, nameDivision, divisionType);
    }

    protected Device(Parcel in) {
        nameDevice = in.readString();
        onOff = in.readInt();
        deviceType = in.readString();
        nameDivision = in.readString();
        divisionType = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameDevice);
        dest.writeInt(onOff);
        dest.writeString(deviceType);
        dest.writeString(nameDivision);
        dest.writeString(divisionType);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };
}