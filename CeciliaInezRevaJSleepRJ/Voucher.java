package CeciliaInezRevaJSleepRJ;


/**
 * Praktikum OOP - Tutam.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (22 Sept 2022)
 */

public class Voucher
{
    // instance variables
    public Type type;
    public double cut;
    public String name;
    public int code;
    public double minimum;
    private boolean used;

    /**
     * Constructor for objects of class Voucher
     */
    public Voucher(String name, int code, Type type, double minimum, double cut)
    {
        // initialise instance variables
        this.type = type;
        this.cut = cut;
        this.name = name;
        this.code = code;
        this.cut = cut;
        this.minimum = minimum;
    }

    public boolean canApply(Price price)
    {
        if(price.price > this.minimum && this.used == false) {
            return true;
        } else {
            return false;
        }
    }
    
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
    
    public boolean isUsed()
    {
        return this.used;
    }
}
