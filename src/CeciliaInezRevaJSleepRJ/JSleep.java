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


    public static List<Room> filterByCity(List <Room> list , String search, int page, int pageSize) {
        return Algorithm.paginate(list, page, pageSize, room -> room.city.toString().toLowerCase().contains(search.toLowerCase()));
    }

    public static List<Room> filterByPrice(List<Room> priceList, double minPrice, double maxPrice){
        if(maxPrice == 0){
            return Algorithm.<Room>collect(priceList, temp -> (temp.price.price >= minPrice));
        } return Algorithm.<Room>collect(priceList, temp -> (temp.price.price <= maxPrice) && (temp.price.price >= minPrice));
    }

    public static List<Room> filterByAccountId(List<Room> list, int accountId, int page, int pageSize){
        List<Room> accList = Algorithm.<Room>paginate(list, page, pageSize, a -> a.accountId == accountId);
            return accList;
    }
}



