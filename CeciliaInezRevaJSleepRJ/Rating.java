package CeciliaInezRevaJSleepRJ;

public class Rating {
    public long total;
    public long count;
    
    public Rating() {
        this.total = 0;
        this.count = 0;
    }

    public void insert(int rating) {
        rating += total;
        count ++;
    }
    
    public double getAverage() {
        return total/count;
    }
    
    public long getCount() {
        return count;
    }

    public long getTotal() {
        return total;
    }
}