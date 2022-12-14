package com.CeciliaInezRevaJSleepRJ;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a payment made by a buyer to a renter for a specific room and time period.
 */
public class Payment extends Invoice {
    public Date to;
    public Date from;
    private int roomId;

    /**
     * Constructs a Payment object with the given buyer and renter IDs, room ID, and date range.
     *
     * @param buyerId the ID of the buyer
     * @param renterId the ID of the renter
     * @param roomId the ID of the room
     * @param from the start date of the time period
     * @param to the end date of the time period
     */
    public Payment(int buyerId, int renterId, int roomId, Date from, Date to){
        super( buyerId, renterId);
        this.roomId = roomId;
        this.from = from;
        this.to = to;
        //this.from = new Date();
        //this.to = new Date();
        //this.to.add(Calendar.DATE, 2);
    }


    /**
     * Constructs a Payment object with the given buyer and renter accounts, room ID, and date range.
     *
     * @param buyer the buyer's account
     * @param renter the renter's account
     * @param roomId the ID of the room
     * @param from the start date of the time period
     * @param to the end date of the time period
     */
    public Payment(Account buyer, Renter renter,  int roomId, Date from, Date to){
        super(buyer, renter);
        this.roomId = roomId;
        this.from = from;
        this.to = to;
        //this.from = new Date();
        //this.to = new Date();
        //this.to.add(Calendar.DATE, 2);
    }

    /**
     * Check the availability of a room for a given time period.
     *
     * @param from the start date of the time period
     * @param to the end date of the time period
     * @param room the room to check availability for
     * @return true if the room is available for the given time period, false otherwise
     */
    public static boolean availability(Date from, Date to, Room room){
        if(from.after(to) || from.equals(to)){
            return false;
        }
        // Check if the room is already booked for any of the dates in the given time period
        for (Date i : room.booked) {
            if (from.equals(i)) {
                return false;
            } else if(from.before(i)){
                if(from.before(i) && to.after(i)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Make a booking for a room for a given time period.
     *
     * @param from the start date of the time period
     * @param to the end date of the time period
     * @param room the room to make the booking for
     * @return true if the booking was successful, false if the room is not available for the given time period
     */
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






