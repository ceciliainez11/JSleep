package CeciliaInezRevaJSleepRJ;
import java.sql.*;

/**
 * Praktikum OOP - CS.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (21 Sept 2022)
 */

public class JSleep
{
     public static Room createRoom(){
        Price price = new Price(200000, 10);
        Room room = new Room(1, "hotel", 50, price, Facility.AC, City.DEPOK, "JL. Margonda Raya");
        return room;
    }
    
    public static void main(String[] args){
        Room RoomA = JSleep.createRoom();
        Room RoomB = JSleep.createRoom();
        System.out.println("Membuat booking dari tanggal 15 hingga 18");
        Date start = Date.valueOf("2022-8-15");
        Date end = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start, end,RoomA));
        System.out.println("Membuat booking dari tanggal 15 hingga 18");
        Date start2 = Date.valueOf("2022-8-18");
        Date end2 = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start2, end2,RoomA));
        System.out.println("Membuat booking dari tanggal 15 hingga 18 untuk kamar berbeda");
        Date start3 = Date.valueOf("2022-8-18");
        Date end3 = Date.valueOf("2022-8-20");
        System.out.println(Payment.makeBooking(start3, end3,RoomB));
        
        //error handling
        //System.out.println("Membuat booking dari tanggal 20 hingga 15");
        //Date start = Date.valueOf("2022-8-20");
        //Date end = Date.valueOf("2022-8-15");
        //System.out.println(Payment.makeBooking(start, end,RoomA));


        /*Room theroom = createRoom();
        
        System.out.println("Make a room");
        System.out.println(theroom.name);
        System.out.println(theroom.size);
        System.out.println(theroom.price.price);
        System.out.println(theroom.facility);*/
        
        /*Payment testRoom = new Payment(1, 1, 1, "", 1, "", "");
        Invoice testInvoice = new Invoice(2, 2, 2, "");
        System.out.println(testRoom.print());
        System.out.println(testInvoice.print());*/
        
        /*Complaint testComplain = new Complaint(1, "23 Agustus 2022", "Bad Quality");
        Price testPrice = new Price(100000, 20000);
        Room testRoom = new Room(1, "Presidential Suite", 5, testPrice, Facility.FitnessCenter, City.DEPOK, "JL. Margonda Raya");
        Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
        Rating testRating = new Rating();
        System.out.println(testComplain.toString());
        System.out.println(testRoom.toString());
        System.out.println(testAccount.toString());
        System.out.println(testPrice.toString());
        System.out.println(testRating.toString());*/
        
        /*Payment testPayment = new Payment(2, 2, 2,2);
        System.out.println(testPayment.getTime());
        System.out.println(testPayment.getDuration());
        Price[] unfilteredArray = new Price[5];
        for(int i=0;i < unfilteredArray.length;i++){
            int j = 5000;
            unfilteredArray[i] = new Price((i+1)*j);
        }
        System.out.println("Price List");
        for(int i=0;i < unfilteredArray.length;i++){
            System.out.println(unfilteredArray[i].price);
        }
        System.out.println("Below 12000.0");
        System.out.println(Validate.filter(unfilteredArray, 12000,true));
        System.out.println("Above 10000.0");
        System.out.println(Validate.filter(unfilteredArray, 10000,false));*/        
    }
    /*public static void main (String[] args){
    Payment testPayment = new Payment(2, 2, 2,2);
    System.out.println(testPayment.getTime());
    System.out.println(testPayment.getDuration());
    Price[] unfilteredArray = new Price[5];
    for(int i=0;i < unfilteredArray.length;i++){
        int j = 5000;
         unfilteredArray[i] = new Price((i+1)*j);
    }
    System.out.println("Price List");
    for(int i=0;i < unfilteredArray.length;i++){
        System.out.println(unfilteredArray[i].price);
    }
    System.out.println("Below 12000.0");
    System.out.println(Validate.filter(unfilteredArray, 12000,true));
    System.out.println("Above 10000.0");
    System.out.println(Validate.filter(unfilteredArray, 10000,false));
    }*/
    /*
    public static void main (String[] args){
    Payment testRoom = new Payment(1, 1, 1, "", 1, "", "");
    Invoice testInvoice = new Invoice(2,2,2, "");
    System.out.println(testRoom.print());
    System.out.println(testInvoice.print());
    }
    */
    
    /*
    public static int getHotelId (){
        return 0;
    }
    public static String getHotelName(){
        return "hotel";
    }
    public static boolean isDiscount(){
        return true;
    }
    public static float getDiscountPercentage (int beforeDiscount, int afterDiscount){
        if (beforeDiscount < afterDiscount){
            return 0;
        }
        if (beforeDiscount == 0){
            return 0;
        }
        
            float percentage = (((float)beforeDiscount - (float)afterDiscount)*100)/(float)beforeDiscount;
            return percentage;
        
    }
    
    public static int getDiscountedPrice (int price, float discountPercentage){
        if (discountPercentage >= 100) {
            return 0;
        }
        else {
            float DiscountedPrice = (price - ((price * discountPercentage)/100));
            return (int) DiscountedPrice;
            
        }
        
    }
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
      
        float originalPrice = (float)discountedPrice/(1-(discountPercentage/100));
        return (int) originalPrice;
    }
    public static float getAdminFeePercentage(){
        return 0.05f;
    }
    public static int getAdminFee(int price){
        float adminFee = price * getAdminFeePercentage();
        return (int) adminFee;
    }
    public static int getTotalPrice(int price, int numberOfNight){
        int totalPrice = (price* numberOfNight) + getAdminFee(price * numberOfNight);
        return totalPrice;
    }
    public static Room createRoom(){
        Price price = new Price (100000, 5);
        Room room = new Room("hotel",30,price, Facility.AC);
        return room;
        
    }
    public static void main (String[] args){
       System.out.println(getHotelId());
       System.out.println(getHotelName());
       System.out.println(isDiscount());

       System.out.println(getDiscountPercentage(1000,900));
       System.out.println(getDiscountPercentage(1000,0));
       System.out.println(getDiscountPercentage(0,0));
       System.out.println(getDiscountPercentage(0,1));

       System.out.println(getDiscountedPrice(1000, 10.0f));
       System.out.println(getDiscountedPrice(1000,100.0f));
       System.out.println(getDiscountedPrice(1000,120.0f));
       System.out.println(getDiscountedPrice(0,0f));

       System.out.println(getOriginalPrice(900, 10.0f));
       System.out.println(getOriginalPrice(1000, 0.0f));
       System.out.println(getOriginalPrice(0, 100.0f));
       System.out.println(getOriginalPrice(0, 120.0f));
       Room room = JSleep.createRoom();
       System.out.println(room.name);
       System.out.println(room.size);
       System.out.println(room.price.price);
       System.out.println(room.facility);
      
    }*/

}

