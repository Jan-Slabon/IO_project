import java.sql.Date;

public class User {
    private String  Name;
    private String  Surename;
    private Date BirthDate;
    private String Email;
    private String Photo;
    private int UserID;

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Surename='" + Surename + '\'' +
                ", BirthDate=" + BirthDate +
                ", Email='" + Email + '\'' +
                ", Photo='" + Photo + '\'' +
                ", UserID=" + UserID +
                '}';
    }

    public User(String name, String surename, Date birthDate, String email, String photo, int userID) {
        Name = name;
        Surename = surename;
        BirthDate = birthDate;
        Email = email;
        Photo = photo;
        UserID = userID;
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

    public int getUserID() {
        return UserID;
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

    public void setUserID(int userID) {
        UserID = userID;
    }
}
