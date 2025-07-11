package com.group4.data.model;

public class Doctor {
    private int id;
    private String fullName;
    private String specialization;
    private String title;
    private int hospitalId;
    private int departmentId;
    private String imgDoctor;
    private String hospitalName;
    private String departmentName;

    public Doctor(int id, String fullName, String specialization, String title,
                  int hospitalId, int departmentId, String imgDoctor,
                  String hospitalName, String departmentName) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.title = title;
        this.hospitalId = hospitalId;
        this.departmentId = departmentId;
        this.imgDoctor = imgDoctor;
        this.hospitalName = hospitalName;
        this.departmentName = departmentName;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getSpecialization() { return specialization; }
    public String getTitle() { return title; }
    public int getHospitalId() { return hospitalId; }
    public int getDepartmentId() { return departmentId; }
    public String getImgDoctor() { return imgDoctor; }
    public String getHospitalName() { return hospitalName; }
    public String getDepartmentName() { return departmentName; }
}
