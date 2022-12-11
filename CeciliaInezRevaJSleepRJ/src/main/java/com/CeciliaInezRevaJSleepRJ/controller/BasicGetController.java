package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import com.CeciliaInezRevaJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface BasicGetController <T extends Serializable> {

    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        T object = Algorithm.<T>find(getJsonTable(), pred->pred.id == id);
        return object;
    }


    @GetMapping("/page")
    public default List<T> getPage(@RequestParam int page, @RequestParam int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, pred->true);
    }


    public default JsonTable<T> getJsonTable(){
        return null;
    }
}