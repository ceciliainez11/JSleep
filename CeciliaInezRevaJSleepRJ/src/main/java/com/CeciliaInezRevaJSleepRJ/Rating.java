package com.CeciliaInezRevaJSleepRJ;

public class Rating {
    private long total;
    private long count;

    public long getTotal() {
        return this.total;
    }

    public double getAverage() {
        if(this.count == 0){
            return 0;
        }
        return this.total/this.count;
    }

    public long getCount() {
        return this.count;
    }

    public String toString()
    {
        return "Count: " + count + "\nTotal: " + total;
    }

    public void insert(int rating) {
        this.total += rating;
        this.count++;
    }

    public Rating() {
        this.total = 0;
        this.count = 0;
    }
}