package com.CeciliaInezRevaJSleepRJ;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Payment extends Invoice
{
    public Date to;
    public Date from;
    private int roomId;

    public static boolean makeBooking(Date from, Date to, Room room){
        SimpleDateFormat SDFormat = new SimpleDateFormat("dd MMMM yyyy");
        String formattedFrom = SDFormat.format(from.getTime());
        String formattedTo = SDFormat.format(to.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(from);

        if(availability(from, to, room)) {
            while(from.before(to)) {
                room.booked.add(from);
                Calendar cal = Calendar.getInstance();
                cal.setTime(from);
                cal.add(Calendar.DATE,1);
                from = cal.getTime();
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean availability(Date from, Date to, Room room)
    {
        if(from.after(to)||from.equals(to)) {
            return false;
        }
        for(Date i : room.booked){
            if(from.equals(i)){
                return false;
            }else if(from.before(i)){
                if(from.before(i)&&to.after(i)){
                    return false;
                }
            }
        }
        return true;
    }

    public String print()
    {
        return ("Room ID: " + this.roomId + "\n"+ "Payment from: " + this.from + "\n" + "Payment to: " + this.to + "\n");
    }

    public int getRoomId()
    {
        return roomId;
    }

    public Payment(int id, Account buyer, Renter renter, int roomId, Date from, Date to)
    {
        super(id, buyer, renter);
        this.from = from;
        this.to = to;
        this.roomId = roomId;
    }

    public Payment(int id, int buyerId, int renterId, int roomId, Date from, Date to)
    {
        super(id, buyerId, renterId);
        this.from = from;
        this.to = to;
        this.roomId = roomId;
    }
}