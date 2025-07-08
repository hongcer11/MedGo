package com.group4.data.model;

public class Doctor {
    private int id;
    private String fullName;
    private String specialization;
    private String title;
    private int hospitalId;
    private int departmentId;
    private String imgDoctor;

    public Doctor(int id, String fullName, String specialization, String title,
                  int hospitalId, int departmentId, String imgDoctor) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
        this.title = title;
        this.hospitalId = hospitalId;
        this.departmentId = departmentId;
        this.imgDoctor = imgDoctor;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getSpecialization() { return specialization; }
    public String getTitle() { return title; }
    public int getHospitalId() { return hospitalId; }
    public int getDepartmentId() { return departmentId; }
    public String getImgDoctor() { return imgDoctor; }
}
