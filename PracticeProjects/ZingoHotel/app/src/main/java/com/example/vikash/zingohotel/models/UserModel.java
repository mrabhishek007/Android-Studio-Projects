package com.example.vikash.zingohotel.models;

public class UserModel {

    private int TravellerId;

    // When you don't wan't to provide actual name as a variable it can be defined like following below where "FirstName" is the actual name of JSON

    /*  @SerializedName("FirstName")
       private String fName;
    */

    private String FirstName, MiddleName, LastName, Gender, DOB, Email, PhoneNumber, PlaceName;


    public UserModel(String firstName, String middleName, String lastName, String gender, String DOB, String email, String phoneNumber, String placeName) {
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        Gender = gender;
        this.DOB = DOB;
        Email = email;
        PhoneNumber = phoneNumber;
        PlaceName = placeName;
    }

    public int getTravellerId() {
        return TravellerId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getGender() {
        return Gender;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getPlaceName() {
        return PlaceName;
    }
}
