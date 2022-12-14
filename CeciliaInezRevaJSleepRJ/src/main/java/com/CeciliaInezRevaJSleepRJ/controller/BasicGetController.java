package com.CeciliaInezRevaJSleepRJ.controller;

import com.CeciliaInezRevaJSleepRJ.*;
import com.CeciliaInezRevaJSleepRJ.dbjson.JsonTable;
import com.CeciliaInezRevaJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * A basic interface for controllers that handle GET requests.
 * The interface provides two default methods for getting objects by ID and paginating a list of objects.
 * The type of the objects is determined by the type parameter `T`, which must be serializable.
 *
 * @param <T> the type of the objects that the controller handles
 */
public interface BasicGetController <T extends Serializable> {

    /**
     * Returns the object with the given ID.
     *
     * @param id the ID of the object to return
     * @return the object with the given ID, or null if no such object exists
     */
    @GetMapping("/{id}")
    public default T getById (@PathVariable int id){
        T object = Algorithm.<T>find(getJsonTable(), pred->pred.id == id);
        return object;
    }

    /**
     * Returns a paginated list of objects.
     *
     * @param page the index of the page to return
     * @param pageSize the number of objects per page
     * @return a list of objects in the specified page
     */
    @GetMapping("/page")
    public default List<T> getPage(@RequestParam int page, @RequestParam int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, pred->true);
    }

    /**
     * Returns the JSON table containing the objects that the controller handles.
     *
     * @return the JSON table containing the objects
     */
    public default JsonTable<T> getJsonTable(){
        return null;
    }
}