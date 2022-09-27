package CeciliaInezRevaJSleepRJ;

public class Payment extends Invoice
{
    // instance variables - replace the example below with your own
    public String to;
    public String from;
    private int roomId;

    public Payment(int id, int buyerId, int renterId, String time, int roomId, String from, String to)
    {
        super(id, buyerId, renterId, time);
        this.to = to;
        this.from = from;
        this.roomId = roomId;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int roomId, String from, String to)
    {
        super(id, buyer, renter, time);
        this.to = to;
        this.from = from;
        this.roomId = roomId;
    }
    
    public String print()
    {
        return "From:" + this.from + "\n" + "To:" + this.to + "\n" + "Room ID:" + this.roomId;
    }
    
    public int getRoomId()
    {
        return roomId;
    }
}
