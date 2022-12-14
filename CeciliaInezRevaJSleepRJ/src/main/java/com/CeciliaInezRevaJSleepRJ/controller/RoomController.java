package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a controller for a room.
 * It provides methods create and update a room,
 *
 * @author Cecilia Inez Reva
 *
 * @implements BasicGetController<Room>
 */
@RestController
@RequestMapping("/room")
public class RoomController implements BasicGetController<Room> {
    public static @JsonAutowired(value = Room.class , filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\JSleep\\JSleep\\src\\json\\room.json") JsonTable<Room> roomTable;

    /**
     * Returns the table of rooms.
     *
     * @return the table of rooms
     */
    @Override
    public JsonTable<Room> getJsonTable() {
        return roomTable;
    }

    /**
     * Creates a new room with the specified details.
     *
     * @param accountId the ID of the account that owns the room
     * @param name the name of the room
     * @param size the size of the room in square meters
     * @param price the price of the room per night
     * @param facility a list of facilities provided by the room
     * @param city the city in which the room is located
     * @param address the address of the room
     * @param bedType the type of bed in the room
     *
     * @return the newly created room
     */
    @PostMapping("/create")
    public Room create(@RequestParam int accountId, @RequestParam String name, @RequestParam int size,
                       @RequestParam int price, @RequestParam ArrayList<Facility> facility,
                       @RequestParam City city, @RequestParam String address, @RequestParam BedType bedType) {
        if (Algorithm.<Account>exists(AccountController.accountTable, acc -> acc.id == accountId && acc.renter != null)) {
            Room newRoom = new Room(accountId, name, size, new Price(price), facility, city, address, bedType);
            roomTable.add(newRoom);
            return newRoom;
        }
        return null;
    }

    /**
     * Returns a paginated list of all rooms.
     *
     * @param page the page number to return
     * @param pageSize the number of items per page
     *
     * @return a paginated list of all rooms
     */
    @GetMapping("/getAllRoom")
    List<Room> getAllRoom (@RequestParam int page, @RequestParam int pageSize) {
        return Algorithm.<Room>paginate(roomTable, page, pageSize, Objects::nonNull);
    }

    /**
     * Returns a paginated list of rooms rented by the renter with the specified ID.
     *
     * @param id the ID of the renter
     * @param page the page number to return
     * @param pageSize the number of items per page
     *
     * @return a paginated list of rooms rented by the specified renter
     */
    @GetMapping("/{id}/renter")
    List<Room> getRoomByRenter(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize) {
        return Algorithm.paginate(roomTable, page, pageSize, roomTable -> (roomTable.id == id));
    }

    @GetMapping("/filterByPrice")
    public List<Room> filterByPrice(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam int min,
            @RequestParam int max
    ){
        return Algorithm.<Room>paginate(getJsonTable(),page,pageSize,i -> ((i.price.price >= min) && (i.price.price <= max)));
    }
}
