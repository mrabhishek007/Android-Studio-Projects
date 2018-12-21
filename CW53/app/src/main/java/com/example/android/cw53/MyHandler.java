package com.example.android.cw53;

/**
 * Created by Vikash on 11/26/2017.
 */

public class MyHandler
{
    public String age,name,cno,pfee,tfee;

    public MyHandler()
    {

    }

    public MyHandler(String name, String age,String cno)
    {

        this.age=age;
        this.name=name;
        this.cno=cno;
        this.pfee=pfee;
    }

    public MyHandler(String pfee,String tfee)
    {
       this.pfee=pfee;
       this.tfee=tfee;

    }

    public String getTfee()
    {
        return tfee;
    }

    public void setTfee(String tfee) {
        this.tfee = tfee;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getPfee() {
        return pfee;
    }

    public void setPfee(String pfee) {
        this.pfee = pfee;
    }






}
