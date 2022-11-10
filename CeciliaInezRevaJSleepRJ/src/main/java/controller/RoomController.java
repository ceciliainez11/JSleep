package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.controller.BasicGetController;
import com.CeciliaInezRevaJSleepRJ.Room;
import com.CeciliaInezRevaJSleepRJ.Facility;
import com.CeciliaInezRevaJSleepRJ.City;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonAutowired;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/room")

public class RoomController implements BasicGetController<Room>{
    @JsonAutowired(value = Room.class, filepath = "C:\\Users\\cecil\\Documents\\kuliah\\semester 3\\oop\\JSleep\\JSleep\\CeciliaInezRevaJSleepRJ\\src\\room.json")
    public static JsonTable<Room> roomTable;

    @Override
    public JsonTable<Room> getJsonTable() {
        return roomTable;
    }

    @GetMapping("/{id}/renter")
    List<Room> getRoomByRenter(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
        return null;
    }

    @PostMapping("/create")
    public Room create(@RequestParam int accountId, @RequestParam String name, @RequestParam int size, @RequestParam int price,
                       @RequestParam Facility facility, @RequestParam City city, @RequestParam String address) {
        return null;
    }
}