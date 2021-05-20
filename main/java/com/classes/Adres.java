package com.classes;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Component
@Entity
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String Country;
    String City;
    String Road;
    int house;
    int flat;

    public Adres(String country, String city, String road, int house, int flat) {
        Country = country;
        City = city;
        Road = road;
        this.house = house;
        this.flat = flat;
    }

    public Adres() {
    }

    @Override
    public String toString() {
        return Country + " " + City + " " + Road + " " + house + "/" + flat;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getRoad() {
        return Road;
    }

    public void setRoad(String road) {
        Road = road;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }
}
