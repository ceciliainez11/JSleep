package CeciliaInezRevaJSleepRJ;


/**
 * Praktikum CS OOP Modul 4
 *
 * @author (Cecilia Inez Reva M. - 2106636994)
 */

import java.util.ArrayList;

public class Validate
{
    public static ArrayList filter(Price[]list, int value, boolean less)
    {
        ArrayList<Price> harga = new ArrayList <Price>();
        if(less){
            for(Price i : list){
                if(i.price <= value){
                    harga.add(i);
                }
            }
        }
        else
        {
            for(Price i : list){
                if(i.price > value){
                    harga.add(i);
                }
            }
        }
        return harga;
    }
}
