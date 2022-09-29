package CeciliaInezRevaJSleepRJ;

/**
 * Praktikum OOP - CS.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (27 Sept 2022)
 */

public class Account extends Serializable
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
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
