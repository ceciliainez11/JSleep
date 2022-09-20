package CeciliaInezRevaJSleepRJ;

public class JSleep
{   
    public static void main(String[] args){
        Room test = createRoom();
        System.out.println(test.name);
        System.out.println(test.size);
        System.out.println(test.price.price);
        System.out.println(test.facility);
    }
    
    public static Room createRoom(){
        Price price = new Price (1000000, 10);
        Room room = new Room("kamar", 50, price, Facility.Balcony);
        return room;
    }
}

