package com.group4.data.model;

public class Hospital {
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

    public int getHospitalId() { return hospitalId; }
    public String getHospitalName() { return hospitalName; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getMap() { return map; }
    public String getDescription() { return description; }
    public boolean isSupportOnline() { return supportOnline; }
    public String getImgHospital() { return imgHospital; }
}
