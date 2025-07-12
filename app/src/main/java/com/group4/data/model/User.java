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
    private String phone_numb;
    private String gender;       // "Nam" | "Nữ" | ...
    private String status;       // "Active", "Inactive", ...

    /* ==== Constructors ==== */

    // Constructor rỗng (cần cho Firebase, Room…)
    public User() {
    }

    public User(int userId, String fullName, String email, String password,
                String dob, String phone_numb, String gender, String status) {
        this.userId      = userId;
        this.fullName    = fullName;
        this.email       = email;
        this.password    = password;
        this.dob         = dob;
        this.phone_numb = phone_numb;
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

    public String getPhone_numb() {
        return phone_numb;
    }

    public void setPhone_numb(String phone_numb) {
        this.phone_numb = phone_numb;
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
                ", phone_numb='" + phone_numb + '\'' +
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
