package CeciliaInezRevaJSleepRJ;

public class Renter extends Serializable
{
    // instance variables - replace the example below with your own
    public int phoneNumber;
    public String address;
    public String username;

    /**
     * Constructor for objects of class Renter
     */
    public Renter(int id, String username, int phoneNumber)
    {
        super(id);
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter(int id, String username)
    {
        // initialise instance variables
        super(id);
        this.username = username;
        this.phoneNumber = 0;
        this.address = "";
    }
    
    public Renter(int id, String username, String address)
    {
        super(id);
        this.username = username;
        this.phoneNumber = 0;
        this.address = address;
    }
    
    public Renter(int id, String username, int phoneNumber, String address)
    {
        super(id);
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
