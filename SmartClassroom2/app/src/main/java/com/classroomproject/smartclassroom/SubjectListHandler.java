package com.classroomproject.smartclassroom;

/**
 * Created by Vikash on 12/19/2017.
 */

public class SubjectListHandler
{
    String subject;
    public SubjectListHandler()
    {

    }

    public SubjectListHandler(String subject)
    {
        this.subject = subject;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
