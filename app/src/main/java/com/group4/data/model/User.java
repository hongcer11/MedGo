package com.group4.data.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model đại diện cho bảng "user" trong SQLite.
 */
public class User implements Serializable {

    private int userId;
    private String fullName;
    private String email;
    private String password;
    private String dob;          // Định dạng: "dd/MM/yyyy"
    private String phoneNumber;
    private String gender;       // "Nam" | "Nữ" | ...
    private String status;       // "Active", "Inactive", ...

    /* ==== Constructors ==== */

    // Constructor rỗng (cần cho Firebase, Room…)
    public User() {
    }

    public User(int userId, String fullName, String email, String password,
                String dob, String phoneNumber, String gender, String status) {
        this.userId      = userId;
        this.fullName    = fullName;
        this.email       = email;
        this.password    = password;
        this.dob         = dob;
        this.phoneNumber = phoneNumber;
        this.gender      = gender;
        this.status      = status;
    }

    /* ==== Getter & Setter ==== */

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    /* ==== Utility Methods ==== */

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
