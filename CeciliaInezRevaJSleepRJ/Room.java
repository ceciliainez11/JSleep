package CeciliaInezRevaJSleepRJ;

public class Room extends Serializable
{
    public Price price;
    public String address;
    public int size;
    public Facility facility;
    public BedType bedType;
    public City city;
    public String name;
    
    public Room(int id, String name, int size, Price price, Facility facility, City city, String address) {
        super(id);
        this.name = name;
        this.size = size;
        this.price = price;
        this.address = address;
        this.bedType = bedType;
        this.city = city;
        this.facility = facility;
        this.bedType = BedType.SINGLE;
    }
    
    public String toString()
    {
        return "\n\nID: " + id + "\nName: " + name + "\nSize: " + size + "\nPrice: " + price +
        "\nFacility: " + facility + "\nBed Type: " + bedType + "\nCity: " +
        city + "\nAddress: " + address;
    }
}