package CeciliaInezRevaJSleepRJ;


/**
 * Write a description of class Complaint here.
 *
 * @author (Cecilia Inez Reva - 2106636994)
 * @version TUTAM MODUL 3
 */
public class Complaint extends Serializable
{
    // instance variables - replace the example below with your own
    public String desc;
    public String date;

    /**
     * Constructor for objects of class Complaint
     */
    public Complaint(int id, String date, String desc)
    {
        super(id);
        this.date = date;
        this.desc = desc;
    }

    public String toString()
    {
        return "\n\nID: " + id + "\nDate: " + date + "\nDesc: " + desc;
    }
}
