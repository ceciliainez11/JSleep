package com.CeciliaInezRevaJSleepRJ;

import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;

/**
 * A class that represents a complaint made by a customer.
 *
 * @author Cecilia Inez Reva
 */
public class Complaint extends Serializable
{
    // instance variables - replace the example below with your own
    public String desc;
    public String date;

    /**
     * Constructor for objects of class Complaint.
     *
     * @param id The ID of the complaint.
     * @param date The date the complaint was made.
     * @param desc The description of the complaint.
     */
    public Complaint(int id, String date, String desc)
    {
        super();
        this.date = date;
        this.desc = desc;
    }

    /**
     * Returns a string representation of the complaint.
     *
     * @return A string representation of the complaint.
     */
    public String toString()
    {
        return "\n\nID: " + id + "\nDate: " + date + "\nDesc: " + desc;
    }
}
