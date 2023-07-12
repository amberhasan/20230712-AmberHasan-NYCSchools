package com.mobileapp.nycschools.models;

import java.io.Serializable;

public class School implements Serializable {
    private String dbn;
    private String schoolName;
    private String overview;
    private String location;
    private String phoneNumber;
    private String schoolEmail;
    private String website;
    private String totalStudents;
    private String city;

    public School(String dbn, String schoolName, String overview, String location, String phoneNumber, String schoolEmail, String website, String totalStudents, String city) {
        this.dbn = dbn;
        this.schoolName = schoolName;
        this.overview = overview;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.schoolEmail = schoolEmail;
        this.website = website;
        this.totalStudents = totalStudents;
        this.city = city;
    }

    public String getDbn() {
        return dbn;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getOverview() {
        return overview;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public String getWebsite() {
        return website;
    }

    public String getTotalStudents() {
        return totalStudents;
    }

    public String getCity() {
        return city;
    }

    public String getSchoolInfo() {
        return "Location: " + location
                + "\nPhone: " + phoneNumber
                + "\nEmail: " + schoolEmail
                + "\nTotal Students: " + totalStudents;
    }

    public String getDetailInfo() {
        return "DBN: " + dbn
                + "\n\nSchool name: " + schoolName
                + "\n\nOverview: " + overview
                + "\n\nLocation: " + location
                + "\nPhone: " + phoneNumber
                + "\nEmail: " + schoolEmail
                + "\nWebsite: " + website
                + "\nTotal Students: " + totalStudents
                + "\nCity: " + city;
    }
}
