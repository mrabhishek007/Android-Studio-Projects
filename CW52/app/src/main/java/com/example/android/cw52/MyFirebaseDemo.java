package com.example.android.cw52;

/**
 * Created by Vikash on 11/26/2017.
 */

public class MyFirebaseDemo
{
    public String name,id,salary;

    public MyFirebaseDemo()
    {
        //Default Constructer
    }


   public MyFirebaseDemo(String id,String name,String salary)
   {
       this.id=id;
       this.name=name;
       this.salary=salary;
   }


    public String getSalary() {
        return salary;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }




}
