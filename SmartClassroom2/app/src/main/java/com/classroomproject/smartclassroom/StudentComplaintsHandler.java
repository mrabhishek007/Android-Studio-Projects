package com.classroomproject.smartclassroom;

/**
 * Created by Vikash on 12/20/2017.
 */

public class StudentComplaintsHandler
{

    String subject_name,submit_date,complaints_details;

    public StudentComplaintsHandler(String subject_name, String submit_date, String complaints_details)
    {
        this.subject_name = subject_name;
        this.submit_date = submit_date;
        this.complaints_details = complaints_details;
    }

    public StudentComplaintsHandler()
    {

    }


    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public String getComplaints_details() {
        return complaints_details;
    }

    public void setComplaints_details(String complaints_details) {
        this.complaints_details = complaints_details;
    }
}
