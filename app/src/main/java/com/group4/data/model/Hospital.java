package com.group4.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Hospital implements Parcelable {
    private int hospitalId;
    private String hospitalName;
    private String address;
    private String phone;
    private String map;
    private String description;
    private boolean supportOnline;
    private String imgHospital;

    public Hospital(int hospitalId, String hospitalName, String address, String phone,
                    String map, String description, boolean supportOnline, String imgHospital) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.address = address;
        this.phone = phone;
        this.map = map;
        this.description = description;
        this.supportOnline = supportOnline;
        this.imgHospital = imgHospital;
    }

    protected Hospital(Parcel in) {
        hospitalId = in.readInt();
        hospitalName = in.readString();
        address = in.readString();
        phone = in.readString();
        map = in.readString();
        description = in.readString();
        supportOnline = in.readByte() != 0;
        imgHospital = in.readString();
    }

    public static final Creator<Hospital> CREATOR = new Creator<Hospital>() {
        @Override
        public Hospital createFromParcel(Parcel in) {
            return new Hospital(in);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
        }
    };

    public int getHospitalId() { return hospitalId; }
    public String getHospitalName() { return hospitalName; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getMap() { return map; }
    public String getDescription() { return description; }
    public boolean isSupportOnline() { return supportOnline; }
    public String getImgHospital() { return imgHospital; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(hospitalId);
        dest.writeString(hospitalName);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(map);
        dest.writeString(description);
        dest.writeByte((byte) (supportOnline ? 1 : 0));
        dest.writeString(imgHospital);
    }
}
