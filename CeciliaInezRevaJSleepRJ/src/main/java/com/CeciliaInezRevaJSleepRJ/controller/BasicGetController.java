package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import com.CeciliaInezRevaJSleepRJ.dbjson.*;
import com.CeciliaInezRevaJSleepRJ.*;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

public interface BasicGetController <T extends Serializable> {
    @GetMapping("/page")
    public default List<T> getPage(@RequestParam int page, @RequestParam int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, pred->true);
    }
    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        T object = Algorithm.<T>find(getJsonTable(), pred->pred.id == id);
        return object;
    }

    public default JsonTable<T> getJsonTable(){
        return null;
    }
}