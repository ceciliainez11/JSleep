package CeciliaInezRevaJSleepRJ;
import java.sql.*;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;

/**
 * Praktikum OOP - CS.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (11 Oktober 2022)
 */

public class JSleep
{
    public static Room createRoom(){
        Price price = new Price(100000, 0.5);
        Room room = new Room(1, "Hotel", 30, price, Facility.AC, City.DEPOK, "Jalan Margonda Raya");
        return room;
    }

    class Country {
        public String name;
        public int population;
        public List<String> listOfStates;
    }
    
    public static void main(String[] args) {
        Renter testRegex = new Renter("Netlab_", "081234567890", "Jl Margonda Raya");
        Renter testRegexFail = new Renter("netlab", "081", "Jalan");
        System.out.println(testRegex.validate());
        System.out.println(testRegexFail.validate());

        try {
            String filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\oop\\JSleep\\JSleep\\json\\randomRoomList.json";
            JsonTable<Room> tableRoom = new JsonTable<>(Room.class, filepath);
            List<Room> filterTableRoom = filterByCity(tableRoom, "medan", 0, 5);
            filterTableRoom.forEach(room -> System.out.println(room.toString()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    public static List<Room> filterByCity(List<Room> list, String search, int page, int pageSize){
            List<Room> cityList = Algorithm.<Room>paginate(list, page, pageSize, i -> i.city == City.valueOf(search.toUpperCase()));
            return cityList;
        }

    public static List<Room> filterByPrice(List<Room> list, double minPrice, double maxPrice){
            List<Room> accList = Algorithm.<Room>collect(list, p -> (p.price.price <= maxPrice) && (p.price.price >= minPrice));
            return accList;
        }

    public static List<Room> filterByAccountId(List<Room> list, int accountId, int page, int pageSize){
        List<Room> accList = Algorithm.<Room>paginate(list, page, pageSize, a -> a.accountId == accountId);
            return accList;
        }
}
//        String filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\oop\\JSleep\\JSleep\\city.json";
//        Gson gson = new Gson();
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(filepath));
//            Country input = gson.fromJson(br, Country.class);
//            System.out.println("name : " + input.name);
//            System.out.println("population: " + input.population);
//            System.out.println("states : ");
//            input.listOfStates.forEach(state -> System.out.println(state));
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }

//        ArrayList<Room> RoomSerialized = new ArrayList<Room>();
//        for(int i = 0; i<5; i++){
//            RoomSerialized.add(i, JSleep.createRoom());
//            System.out.println(RoomSerialized.get(i).toString());
//        }



