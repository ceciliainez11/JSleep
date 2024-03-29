package com.CeciliaInezRevaJSleepRJ;


/**
 * Praktikum PostTest Modul 4
 *
 * @author (Cecilia Inez Reva M. - 2106636994)
 */

import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;

public class Invoice extends Serializable
{
    // instance variables - replace the example below with your own
    public RoomRating rating;
    public PaymentStatus status;
    public int buyerId;
    //public Calendar time;
    public int renterId;
    //public int renterId;

    public enum RoomRating
    {
        NONE, BAD, NEUTRAL, GOOD
    }

    public enum PaymentStatus
    {
        FAILED, WAITING, SUCCESS
    }

    /**
     * Constructs a new invoice with the given buyer and renter ID.
     *
     * @param buyerId The ID of the buyer
     * @param renterId The ID of the renter
     */
    protected Invoice(int buyerId, int renterId)
    {
        // initialise instance variables
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    /**
     * Constructs a new invoice with the given buyer and renter accounts.
     *
     * @param buyer The buyer's account
     * @param renter The renter's account
     */
    public Invoice(Account buyer, Renter renter)
    {
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public String print()
    {
        return "\nBuyerId=" + buyerId + "\nRenterId=" + renterId;
    }
}