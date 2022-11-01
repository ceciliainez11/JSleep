package CeciliaInezRevaJSleepRJ;

/**
 * Praktikum OOP - TUTAM.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
<<<<<<< HEAD
=======
 * @version
>>>>>>> c35a68e67ac992291ad6100c80e686811cbbc6b2
 */

public class Account extends Serializable implements FileParser
{
    // instance variables
    public String name;
    public String email;
    public String password;

    /**
     * Constructor for objects of class Account
     */
    public Account(int id, String name, String email, String password)
    {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String toString()
    {
        return "\n\nID: " + id + "\nName: " + name + "\nEmail: " + email + "\nPassword: " + password;
    }

    public boolean read(String Content){
        return false;
    }

    public Object write(){
        return null;
    }
}