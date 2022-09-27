package CeciliaInezRevaJSleepRJ;


/**
 * Praktikum OOP - Tutam.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (27 Sept 2022)
 */
public class Invoice extends Serializable
{
    // instance variables - replace the example below with your own
    public int buyerId;
    public int renterId;
    public String time;

    /**
     * Constructor for objects of class Invoice
     */
    public Invoice(int id, int buyerId, int renterId, String time)
    {
        // initialise instance variables
        super(id);
        this.time = time;
    }

    public Invoice(int id, Account buyer, Renter renter, String time)
    {
        super(id); 
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
    }
    
    public String print()
    {
        return "Buyer ID:" + this.buyerId + "\n" + "Renter ID:" + this.renterId + "\n" + "time" + this.time + "\n";
    }
}
