package com.CeciliaInezRevaJSleepRJ;

import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;

import java.util.Date;
import java.util.ArrayList;

/**
 * A class representing a room in a hotel. The `Room` class implements the
 * `Serializable` interface so that instances of the class can be serialized
 * and deserialized.
 *
 * @author Cecilia Inez Reva
 */
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

    /**
     * Constructs a new `Room` with the specified details.
     *
     * @param accountId the ID of the account that owns the room
     * @param name the name of the room
     * @param size the size of the room, in square feet
     * @param price the price of the room
     * @param facility a list of facilities offered by the room
     * @param city the city where the room is located
     * @param address the address of the room
     * @param bedType the type of bed in the room
     */
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

    /**
     * Overrides the `toString()` method of the `Object` class to return a
     * string representation of the `Room` object.
     *
     * @return a string representation of the `Room` object
     */
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