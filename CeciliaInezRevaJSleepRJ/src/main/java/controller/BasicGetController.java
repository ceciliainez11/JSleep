package com.CeciliaInezRevaJSleepRJ.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import com.CeciliaInezRevaJSleepRJ.Algorithm;
import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;

public interface BasicGetController<T extends Serializable>{

    @GetMapping("/{id}")

    public default T getById(@PathVariable int id){

        T object = (T) Algorithm.<T> find (getJsonTable(), predicate->predicate.id == id );

        return object;
    }
    abstract JsonTable<T> getJsonTable();

    @GetMapping ("/page")
    public default List<T> getPage (@RequestParam int page, @RequestParam int pageSize) {

        return Algorithm. <T>paginate (getJsonTable(), page,  pageSize, predicate -> true);
    }
}
