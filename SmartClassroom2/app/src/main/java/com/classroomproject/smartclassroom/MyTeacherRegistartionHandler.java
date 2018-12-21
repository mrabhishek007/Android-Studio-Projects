package com.classroomproject.smartclassroom;

/**
 * Created by Vikash on 12/15/2017.
 */

public class MyTeacherRegistartionHandler
{

    String teacher_name,teacher_id,teacher_email,teacher_password,teacher_subject;

    public MyTeacherRegistartionHandler()
    {

    }

    public MyTeacherRegistartionHandler(String teacher_name,String teacher_id, String teacher_email,String teacher_password,String teacher_subject)
    {
        this.teacher_name = teacher_name;
        this.teacher_id = teacher_id;
        this.teacher_email  = teacher_email;
        this.teacher_password = teacher_password;
        this.teacher_subject = teacher_subject;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_subject() {
        return teacher_subject;
    }

    public void setTeacher_subject(String teacher_subject) {
        this.teacher_subject = teacher_subject;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_email() {
        return teacher_email;
    }

    public void setTeacher_email(String teacher_email) {
        this.teacher_email = teacher_email;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public void setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
    }
}
