package CeciliaInezRevaJSleepRJ;
import java.util.Date;
import java.util.ArrayList;

public class Room extends Serializable implements FileParser
{
    public String address;
    public City city;
    public ArrayList<Date> booked;
    public int size;
    public Price price;
    public BedType bedType;
    public Facility facility;
    public String name;
    
    public Object write() {
        return null;
    }
    
    public boolean read(String content) {
        return false;
    }
    
    public String toString(){
        return "\nName: " + this.name + "\nAddress: " + this.address + "\nSize: " + this.size + "\nFacility: " + this.facility + "\nBed Type: " + this.bedType + "\nCity: " + this.city  + this.price;    
    }
    
    public Room (int id, String name, int size, Price price, Facility facility, City city, String address){
       super(id);
       this.name = name;
       this.size = size;
       this.price = price;
       this.facility = facility;
       this.city = city;
       this.address = address;
       this.bedType = BedType.SINGLE;
       this.booked = new ArrayList<Date>();
       
    }
}