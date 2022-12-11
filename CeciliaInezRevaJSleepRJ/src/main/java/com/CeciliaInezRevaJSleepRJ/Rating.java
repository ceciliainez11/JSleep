package com.CeciliaInezRevaJSleepRJ;

/**
 * A class for keeping track of ratings and calculating the average rating.
 *
 * @author Cecilia Inez Reva
 */
public class Rating {
    /** The sum of all ratings. */
    private long total;
    /** The number of ratings. */
    private long count;
    /**
     * Returns the total of all ratings.
     *
     * @return the total of all ratings
     */
    public long getTotal() {
        return this.total;
    }
    /**
     * Calculates and returns the average rating.
     *
     * @return the average rating
     */
    public double getAverage() {
        if(this.count == 0){
            return 0;
        }
        return this.total/this.count;
    }

    /**
     * Constructs a new `Rating` object with no ratings.
     */
    public Rating() {
        this.total = 0;
        this.count = 0;
    }

    /**
     * Returns the number of ratings.
     *
     * @return the number of ratings
     */
    public long getCount() {
        return this.count;
    }

    /**
     * Returns a string representation of the `Rating` object.
     *
     * @return a string representation of the `Rating` object
     */
    public String toString()
    {
        return "Count: " + count + "\nTotal: " + total;
    }

    /**
     * Inserts a new rating.
     *
     * @param rating the rating to insert
     */
    public void insert(int rating) {
        this.total += rating;
        this.count++;
    }

}