package CeciliaInezRevaJSleepRJ;


/**
 * Write a description of class JSleep here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JSleep
{
    public static void main(String[] args){
        
    }
    
    public static int getHotelId(){
        return 0;
    }
    
    public static String getHotelName(){
        return "hotel";
    }
    
    public static boolean isDiscount(){
        return true;
    }
    
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        if (beforeDiscount > afterDiscount) {
            return (((beforeDiscount - afterDiscount) / beforeDiscount) *100);
        }
        else
            return 0;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        if (discountPercentage > 100) {
            return 0;
        }
        return (int) (((100 - discountPercentage) / 100) * price);
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        return (discountedPrice * (100 / (100 - discountPercentage)));
    }
    
    public float getAdminFeePercentage(){
        return 0.05f;
    }
    
    public int getAdminFee(int price){
        return price * getAdminFeePercentage();
    }
    
    public static int getTotalPrice(int price, int numberOfNight){
        int total = numberOfNight * price;
        return (itotal + (total * getAdminFeePercentage()));
    }
}