package CeciliaInezRevaJSleepRJ;

public class Renter extends Serializable
{
    // instance variables - replace the example below with your own
    public int phoneNumber = 0;
    public String address = "";
    public String username;

    /**
     * Constructor for objects of class Renter
     */
    public Renter(int id, String username)
    {
        // initialise instance variables
        super(id);
        this.address = null;
    }
    
    public Renter(int id, String username, String address)
    {
        super(id);
        this.username = username;
        this.address = address;
    }
    
    public Renter(int id, String username, int phoneNumber)
    {
        super(id);
        this.username = username;
        this.phoneNumber = 0;
    }
    
    public Renter(int id, String username, int phoneNumber, String address)
    {
        super(id);
        this.username = username;
        this.address = address;
        this.phoneNumber = 0;
    }
}
