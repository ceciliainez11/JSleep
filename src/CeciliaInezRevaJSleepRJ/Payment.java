package CeciliaInezRevaJSleepRJ;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Payment extends Invoice
{
    public Date to;
    public Date from;
    private int roomId;
    
    public static boolean makeBooking(Date from, Date to, Room room)
    {
        SimpleDateFormat SDFormat = new SimpleDateFormat("dd MMMM yyyy");
        String formattedFrom = SDFormat.format(from.getTime());
        String formattedTo = SDFormat.format(to.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(from);
        long fromInt = from.getTime();
        long toInt = to.getTime();
        long timeDiff = Math.abs(fromInt - toInt);
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        
        if(availability(from, to, room)){
            for(int i = 0; i < daysDiff; i++){
                c.add(Calendar.DATE, i+1);
                from = c.getTime();
                room.booked.add(from);
            }
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean availability(Date from, Date to, Room room)
    {
        if(from.after(to)){
            return false;
        }
        for(Date i : room.booked){
            if(from.compareTo(i) == 0){
                return false;
            }
        }
        return true;
    }
    
    public String getTime()
    {
        SimpleDateFormat SDFormat = new SimpleDateFormat("'Formatted Date = 'dd MMMM yyyy");
        String formattedFrom = SDFormat.format(from.getTime());
        return formattedFrom;
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
