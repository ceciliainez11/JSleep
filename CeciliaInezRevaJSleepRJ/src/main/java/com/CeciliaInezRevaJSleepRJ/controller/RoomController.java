package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/room")

public class RoomController implements BasicGetController<Room> {
    public static @JsonAutowired(value = Room.class , filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\JSleep\\JSleep\\src\\json\\account.json") JsonTable<Room> roomTable;

    @Override
    public JsonTable<Room> getJsonTable() {
        return roomTable;
    }

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

    @GetMapping("/getAllRoom")
    List<Room> getAllRoom (@RequestParam int page, @RequestParam int pageSize) {
        return Algorithm.<Room>paginate(roomTable, page, pageSize, Objects::nonNull);
    }

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
