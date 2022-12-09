package com.CeciliaInezRevaJSleepRJ;

import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;

import java.util.Date;
import java.util.ArrayList;

public class Room extends Serializable {
    public int accountId;
    public int size;
    public String name;
    public String address;
    public ArrayList<Facility> facility = new ArrayList<>();
    public Price price;
    public BedType bedType;
    public City city;
    public ArrayList<Date> booked = new ArrayList<Date>();

    public Room(int accountId, String name, int size, Price price,
                ArrayList<Facility> facility, City city, String address, BedType bedType) {
        this.accountId = accountId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;
        this.address = address;
        this.city = city;
        this.bedType = bedType;
    }

    @Override
    public String toString() {
        return "Room \n" +
                "name='" + name + '\'' +
                ", bedType=" + bedType +
                ", size=" + size +
                ", price=" + price.price +
                ", discount=" + price.discount +
                ", facility=" + facility +
                ", city=" + city +
                ", address='" + address + '\'' +
                ", id=" + accountId;
    }
}