package com.CeciliaInezRevaJSleepRJ;

import java.util.ArrayList;

/**
 * Utility class for filtering a list of prices based on a given value.
 *
 * @author Cecilia Inez Reva
 */
public class Validate {
    /**
     * Filters a list of prices and returns a new list containing only the prices
     * that are less than or greater than the specified value, depending on the
     * value of the `less` parameter.
     *
     * @param list the list of prices to filter
     * @param value the value to compare against
     * @param less if true, only prices less than or equal to the given value
     *             will be included in the result; if false, only prices greater
     *             than the given value will be included in the result
     * @return a list of prices that meet the specified criteria
     */
    public static ArrayList filter (Price[] list, int value, boolean less){
        ArrayList filteredPrice = new ArrayList();
        if(less == true) {
            for (Price iterator : list) {
                if(iterator.price <= value){
                    filteredPrice.add(iterator.price);
                }
            }
        }
        else{
            for (Price iterator : list) {
                if(iterator.price > value){
                    filteredPrice.add(iterator.price);
                }
            }
        }
        return filteredPrice;
    }
}
