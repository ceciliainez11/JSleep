package CeciliaInezRevaJSleepRJ;

import java.util.Calendar;
import java.text.*;

public class Payment extends Invoice
{
    // instance variables - replace the example below with your own
    public Calendar to;
    public Calendar from;
    private int roomId;

    public Payment(int id, int buyerId, int renterId, int roomId)
    {
        super(id, buyerId, renterId);
        this.to = Calendar.getInstance();
        to.add(Calendar.DATE,2);
        this.from = Calendar.getInstance();
        this.roomId = roomId;
    }
    
    public Payment(int id, Account buyer, Renter renter, int roomId)
    {
        super(id, buyer, renter);
        this.to = Calendar.getInstance();
        to.add(Calendar.DATE,2);
        this.from = Calendar.getInstance();
        this.roomId = roomId;
    }
    
    public int getRoomId()
    {
        return roomId;
    }
    
    public String getTime()
    {
        SimpleDateFormat formattedTime = new SimpleDateFormat("dd MMMM yyyy");
        String strTime = formattedTime.format(this.time.getTime());
        return strTime + "\n\nFormatted Date: " + strTime;
    }
    
    public String getDuration()
    {
        SimpleDateFormat formattedTime = new SimpleDateFormat("dd MMMMM yyyy");
        String strFrom = formattedTime.format(this.from.getTime());
        String strTo = formattedTime.format(this.to.getTime());
        return strFrom + " - " + strTo;
    }
    
    public String print()
    {
        return "Room ID: " + roomId + "\nFrom: " + from + "\nto: " + to;
    }
}
