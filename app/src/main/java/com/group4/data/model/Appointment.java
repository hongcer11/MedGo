package com.group4.data.model;

public class Appointment {
    private String code;
    private String name;
    private String hospital;
    private String department;
    private String service;
    private String status;
    private String date;
    private String time;

    public Appointment(String code, String name, String hospital,
                       String department, String service,
                       String status, String date, String time) {
        this.code = code;
        this.name = name;
        this.hospital = hospital;
        this.department = department;
        this.service = service;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getHospital() { return hospital; }
    public String getDepartment() { return department; }
    public String getService() { return service; }
    public String getStatus() { return status; }
    public String getDate() { return date; }
    public String getTime() { return time; }
}

