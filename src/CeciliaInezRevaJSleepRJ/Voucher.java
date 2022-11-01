package CeciliaInezRevaJSleepRJ;


/**
 * Praktikum OOP - Tutam.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (27 Sept 2022)
 */
public class Voucher extends Serializable implements FileParser
{
    // instance variables
    public String name;
    private boolean used;
    public int code;
    public Type type;
    public double minimum;
    public double cut;

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
    
    public boolean isUsed()
    {
        return used;
    }

    public boolean canApply(Price price)
    {
        if(price.price > minimum && used == false) {
            return true;
        } else
            return false;
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
    
    public boolean read(String Content){
        return false;
    }

    public Object write(){
        return null;
    }
}
