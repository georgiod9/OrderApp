package com.example.orderapp.models;

public class UserModel {
    public final int Id;
    public final String LastName;
    public final String FirstName;
    public final String AccountName;
    public final String Password;

    public UserModel(int id, String lastName, String firstName, String accountName, String password){
        this.Id = id;
        this.LastName = lastName;
        this.FirstName = firstName;
        this.AccountName = accountName;
        this.Password = password;
    }
    public UserModel(){
        this.Id = 0;
        this.LastName = null;
        this.FirstName = null;
        this.AccountName = null;
        this.Password = null;
    }

    public String getUName(){
        String completeName = this.FirstName.concat(this.LastName);
        return completeName;
    }

    public String getUAccount(){
        return this.AccountName;
    }

    public String getPassword(){
        return this.Password;
    }

    public int getUId(){
        return this.Id;
    }

}
