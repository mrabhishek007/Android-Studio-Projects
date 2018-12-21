package com.classroomproject.smartclassroom;

/**
 * Created by Vikash on 12/17/2017.
 */




public class SaveDailyRoutineActivity
{
    String date,chapter_name,topic_name;

    public SaveDailyRoutineActivity()
    {

    }


    public SaveDailyRoutineActivity(String date,String chapter_name,String topic_name)
    {
        this.date = date;
        this.chapter_name = chapter_name;
        this.topic_name = topic_name;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
