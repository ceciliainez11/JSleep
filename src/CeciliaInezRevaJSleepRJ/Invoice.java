package CeciliaInezRevaJSleepRJ;


/**
 * Praktikum PostTest Modul 4
 *
 * @author (Cecilia Inez Reva M. - 2106636994)
 */

import java.util.Date;

public class Invoice extends Serializable
{
    // instance variables - replace the example below with your own
    public RoomRating rating;
    public PaymentStatus status;
    public int buyerId;
    public Date time;
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
    
    protected Invoice(int id, int buyerId, int renterId)
    {
        // initialise instance variables
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Date();
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public Invoice(int id, Account buyer, Renter renter)
    {
        super(id); 
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Date();
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String print()
    {
        return "\nTime= " + time + "\nBuyerId=" + buyerId + "\nRenterId=" + renterId;
    }
}
