package CeciliaInezRevaJSleepRJ;


/**
 * Praktikum CS OOP Modul 4
 *
 * @author (Cecilia Inez Reva M. - 2106636994)
 */

import java.util.ArrayList;

public class Validate
{
    public static ArrayList filter (Price[] list, int value, boolean less)
    {
        ArrayList<Double> filterResults = new ArrayList<Double>();
        if(less == true) {
            for (int i = 0; i < list.length ; i++){
                if(list[i].price <= value){
                    filterResults.add(list[i].price);
                }
            }
        }else{
            for (int i = 0; i < list.length ; i++){
                if (list[i].price > value){
                    filterResults.add(list[i].price);
                }
            }
        }
        return filterResults;
    }
    
    public Validate()
    {
        //
    }
}
