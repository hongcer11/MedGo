package com.group4.models;

public class Appointment {
    private String code;
    private String name;
    private String hospital;
    private String specialty;
    private String service;
    private String status;
    private String date;
    private String time;

    public Appointment(String code, String name, String hospital,
                       String specialty, String service, String status,
                       String date, String time) {
        this.code = code;
        this.name = name;
        this.hospital = hospital;
        this.specialty = specialty;
        this.service = service;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getHospital() { return hospital; }
    public String getSpecialty() { return specialty; }
    public String getService() { return service; }
    public String getStatus() { return status; }
    public String getDate() { return date; }
    public String getTime() { return time; }
}

