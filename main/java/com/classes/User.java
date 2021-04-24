package com.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class User {

    @Id
    private String Login;
    private String PassHash;
    private String Name;
    private String Surename;
    private Date BirthDate;
    private String Email;
    private String Photo;

    @Override
    public String toString() {
        return "com.classes.User{" +
                "Name='" + Name + '\'' +
                ", Surename='" + Surename + '\'' +
                ", BirthDate=" + BirthDate +
                ", Email='" + Email + '\'' +
                ", Photo='" + Photo + '\'' +
                '}';
    }

    public User(String name, String surename, Date birthDate, String email, String photo) {
        Name = name;
        Surename = surename;
        BirthDate = birthDate;
        Email = email;
        Photo = photo;
    }

    public String getPassHash() {
        return PassHash;
    }

    public void setPassHash(String passHash) {
        PassHash = passHash;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getName() {
        return Name;
    }

    public String getSurename() {
        return Surename;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurename(String surename) {
        Surename = surename;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
