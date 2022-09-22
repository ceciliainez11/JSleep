package CeciliaInezRevaJSleepRJ;

/**
 * Praktikum OOP - CS.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (21 Sept 2022)
 */

public class JSleep
{   
    public static Room createRoom(){
        Price price = new Price (1000000, 5);
        Room room = new Room("hotel", 30, price, Facility.AC);
        return room;
    }
    
    public static void main(String[] args){
        Room test = createRoom();
        System.out.println(test.name);
        System.out.println(test.size);
        System.out.println(test.price.price);
        System.out.println(test.facility);
    }
}

