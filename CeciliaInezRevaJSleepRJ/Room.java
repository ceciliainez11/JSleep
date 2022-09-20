package CeciliaInezRevaJSleepRJ;

public class Room {
    public Facility facility;
    public String name;
    public int size;
    public Price price;
    
    public Room(String name, int size, Price price, Facility facility) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;
    }
}