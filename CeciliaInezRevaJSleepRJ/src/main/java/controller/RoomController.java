package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.controller.AccountController;
import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController implements BasicGetController<Room> {

    public static @JsonAutowired(value = Room.class , filepath = "src\\json\\room.json") JsonTable<Room> roomTable;


    public JsonTable<Room> getJsonTable() {
        return roomTable;
    }

    @GetMapping("/{id}/renter")
    List<Room> getRoomByRenter(
            @PathVariable int id,
            @RequestParam int page,
            @RequestParam int pageSize
    )
    {
        return Algorithm.paginate(roomTable, page, pageSize, roomTable -> (roomTable.id == id));
    }
    @PostMapping("/create")
    public Room Create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int size,
            @RequestParam int price,
            @RequestParam Facility facility,
            @RequestParam City city,
            @RequestParam String address
    )
    {

        boolean tempAccount = Algorithm.<Account>exists(AccountController.accountTable, temp -> temp.id == accountId && temp.renter!= null);
        System.out.println(tempAccount);
        if(!tempAccount){
            return null;
        }
        Room tempRoom = new Room(accountId, name, size, new Price(price), facility, city, address);
        roomTable.add(tempRoom);
        return tempRoom;
//        roomTable.add(new Room(accountId, name, size, new Price(price), facility, city, address));
//        return new Room(accountId, name, size, new Price(price), facility, city, address);
    }
}
