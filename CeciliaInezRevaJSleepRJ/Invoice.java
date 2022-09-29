package CeciliaInezRevaJSleepRJ;


/**
 * Praktikum OOP - Tutam.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version
 */
public class Invoice extends Serializable
{
    // instance variables - replace the example below with your own
    public PaymentStatus status;
    public int renterId;
    public RoomRating rating;
    public int buyerId;
    public String time;
   // public int renterId;

    public enum RoomRating
    {
        NONE, BAD, NEUTRAL, GOOD
    }
    
    public enum PaymentStatus
    {
        FAILED, WAITING, SUCCESS
    }
    
    protected Invoice(int id, int buyerId, int renterId, String time)
    {
        // initialise instance variables
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    public Invoice(int id, Account buyer, Renter renter, String time)
    {
        super(id); 
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String print()
    {
        return "\nTime= " + time + "\nBuyerId=" + buyerId + "\nRenterId=" + renterId;
    }
}
