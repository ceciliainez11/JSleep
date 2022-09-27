package CeciliaInezRevaJSleepRJ;

public class Room extends Serializable
{
    public int size;
    public Price price;
    public Facility facility;
    public String name;
    
    public Room(int id, String name, int size, Price price, Facility facility) {
        super(id);
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;
    }
}