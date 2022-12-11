package com.CeciliaInezRevaJSleepRJ;

import com.CeciliaInezRevaJSleepRJ.dbjson.Serializable;

/**

 The Voucher class represents a voucher that can be applied to a price to reduce it.

 @author Cecilia Inez Reva

 @version 1.0
 */
public class Voucher extends Serializable
{
    // instance variables
    public String name;
    private boolean used;
    public int code;
    public Type type;
    public double minimum;
    public double cut;

    /**

     Constructs a new Voucher with the specified id, name, code, type, used status, minimum price and cut.
     @param id the id of the voucher
     @param name the name of the voucher
     @param code the code of the voucher
     @param type the type of the voucher
     @param used the used status of the voucher
     @param minimum the minimum price required for the voucher to be applied
     @param cut the amount to cut from the price when the voucher is applied
     */
    public Voucher(int id, String name, int code, Type type, boolean used, double minimum, double cut)
    {
        // initialise instance variables
        super();
        this.type = type;
        this.cut = cut;
        this.name = name;
        this.used = used;
        this.code = code;
        this.minimum = minimum;
    }

    /**

     Returns the used status of this voucher.
     @return the used status of this voucher
     */
    public boolean isUsed()
    {
        return used;
    }

    /**

     Determines if this voucher can be applied to the specified price.
     @param price the price to check
     @return true if the voucher can be applied to the price, false otherwise
     */
    public boolean canApply(Price price)
    {
        if(price.price > minimum && used == false) {
            return true;
        } else
            return false;
    }

    /**

     Applies this voucher to the specified price and returns the new price.

     @param price the price to apply the voucher to

     @return the new price after the voucher has been applied
     */
    public double apply(Price price)
    {
        this.used = true;
        
        if(this.type == Type.DISCOUNT) {
            if (cut > 100){
                cut = 100;
            }
            return price.price * (100-cut)/100;
        } else {
            if (cut > price.price){
                cut = price.price;
            }
            return price.price-cut;
        }
    }

    /**

     Applies this voucher to the specified price and returns the new price.

     @param price the price to apply the voucher to

     @return the new price after the voucher has been applied
     */
    public boolean read(String Content){
        return false;
    }

    /**

     Writes the content of this voucher to an Object.
     @return the Object containing the voucher's content
     */
    public Object write(){
        return null;
    }
}
