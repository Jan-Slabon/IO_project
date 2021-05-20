package com.classes;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int EventId;
    @ManyToOne(cascade = {CascadeType.ALL})
    Adres Adres;
    String Name;
    String Description;
    Date date;
    @ManyToMany(cascade = {CascadeType.ALL})
    List<User> Owners = new ArrayList<User>();
    @ManyToMany(mappedBy = "Invited")
    List<User> Guests = new ArrayList<User>();

    public Event(int eventId, com.classes.Adres adres, String name, String description, ArrayList<User> owners, ArrayList<User> guests) {
        EventId = eventId;
        Adres = adres;
        Name = name;
        Description = description;
        Owners = owners;
        Guests = guests;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" +
                "EventId=" + EventId +
                ", Adres=" + Adres +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", date=" + date +
                '}';
    }

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }

    public com.classes.Adres getAdres() {
        return Adres;
    }

    public void setAdres(com.classes.Adres adres) {
        Adres = adres;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<User> getOwners() {
        return Owners;
    }

    public void setOwners(LinkedList<User> owners) {
        Owners = owners;
    }

    public List<User> getGuests() {
        return Guests;
    }

    public void setGuests(LinkedList<User> guests) {
        Guests = guests;
    }

    public Date getData() {
        return date;
    }

    public void setData(Date data) {
        this.date = data;
    }
}
