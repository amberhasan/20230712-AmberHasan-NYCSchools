package com.mobileapp.nycschools.models;

import java.io.Serializable;

public class School implements Serializable {
    public String dbn, school_name, overview_paragraph, location, phone_number, school_email, website, total_students, city;

    public School(String dbn, String school_name, String overview_paragraph, String location, String phone_number, String school_email, String website, String total_students, String city) {
        this.dbn = dbn;
        this.school_name = school_name;
        this.overview_paragraph = overview_paragraph;
        this.location = location;
        this.phone_number = phone_number;
        this.school_email = school_email;
        this.website = website;
        this.total_students = total_students;
        this.city = city;
    }

    public School() {

    }

    public String getSchoolInfo(){
        return "Location: " + location
                +"\nPhone no: " + phone_number
                +"\nEmail: " + school_email
                +"\nTotal Students: " + total_students;
    }

    public String getDetailInfo(){
        return "DBN: " + dbn
                +"\n\nSchool name: " + school_name
                +"\n\nOverview: " + overview_paragraph
                +"\n\nLocation: " + location
                +"\n\nPhone no: " + phone_number
                +"\n\nEmail: " + school_email
                +"\n\nWebsite: " + website
                +"\n\nTotal Students: " + total_students
                +"\n\nCity: " + city;
    }

}
