package com.classroomproject.smartclassroom;

/**
 * Created by Vikash on 12/19/2017.
 */

public class SaveSubjectHandler
{
    String subject_name,subject_date,chapter_name,topic_name;

    public  SaveSubjectHandler()
    {

    }

    public SaveSubjectHandler(String subject_name, String subject_date, String chapter_name, String topic_name)
    {
        this.subject_name = subject_name;
        this.subject_date = subject_date;
        this.chapter_name = chapter_name;
        this.topic_name = topic_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_date() {
        return subject_date;
    }

    public void setSubject_date(String subject_date) {
        this.subject_date = subject_date;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }
}
