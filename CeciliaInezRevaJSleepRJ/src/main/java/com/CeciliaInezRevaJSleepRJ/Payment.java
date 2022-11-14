package com.CeciliaInezRevaJSleepRJ;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Invoice {
    public Date to;
    public Date from;
    private int roomId;


    public Payment(int buyerId, int renterId, int roomId, Date from, Date to){
        super( buyerId, renterId);
        this.roomId = roomId;
        this.from = from;
        this.to = to;
        //this.from = new Date();
        //this.to = new Date();
        //this.to.add(Calendar.DATE, 2);
    }

    public Payment(Account buyer, Renter renter,  int roomId, Date from, Date to){
        super(buyer, renter);
        this.roomId = roomId;
        this.from = from;
        this.to = to;
        //this.from = new Date();
        //this.to = new Date();
        //this.to.add(Calendar.DATE, 2);
    }

    //New

    public static boolean availability(Date from, Date to, Room room){
        //to.before(from) kayanya gaperlu
        if(from.after(to) || from.equals(to)){
            return false;
        }

        for (Date i : room.booked) {

            if (from.equals(i)) {
                return false;
            } else if(from.before(i)){
                //from.before(i) gaperlu karena udh di atas
                if(from.before(i) && to.after(i)){
                    return false;
                }
            }
        }

        return true;

    }

    public static boolean makeBooking(Date from, Date to, Room room){

        if(availability(from, to, room)){

            // Looping input seluruh nilai antara from sama to ke array
            while (from.before(to)){
                room.booked.add(from); // Assign ke array
                Calendar tempVar = Calendar.getInstance();
                tempVar.setTime(from);
                tempVar.add(Calendar.DATE, 1); //Increment 1
                from = tempVar.getTime();
            }
            return true;
        }
        else{
            return false;
        }
        // SimpleDateFormat SDFormat = new SimpleDateFormat("dd MMMMM YYYY ");
        // //M ada 4 atau 5
        // return(SDFormat.format(this.from.getTime()) + "- " +  SDFormat.format(this.to.getTime()));
    }



    public String print(){

        return "To : "  + "\nFrom : "  + "\nRoom ID : " + this.roomId;
    }

    public int getRoomId(){
        return roomId;
    }
}






