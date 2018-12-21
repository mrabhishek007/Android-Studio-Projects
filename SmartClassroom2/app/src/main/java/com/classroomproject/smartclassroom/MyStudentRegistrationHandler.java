package com.classroomproject.smartclassroom;

/**
 * Created by Vikash on 12/15/2017.
 */

public class MyStudentRegistrationHandler
{
    String student_name,student_id,student_email,student_password;
    public MyStudentRegistrationHandler()
    {

    }

    public MyStudentRegistrationHandler(String student_id,String student_name,String student_email,String student_password)
    {
        this.student_name = student_name;
        this.student_id  = student_id;
        this.student_email=student_email;
        this.student_password = student_password;

    }


    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public String getStudent_password() {
        return student_password;
    }

    public void setStudent_password(String student_password) {
        this.student_password = student_password;
    }
}
