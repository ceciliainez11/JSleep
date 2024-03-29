package com.CeciliaInezRevaJSleepRJ;

/**
 * Praktikum OOP - Tutam.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
<<<<<<< HEAD
=======
 * @version
 */
public class Price{
    // public double rebate;
    // public int discount;
    public double price;
    public double discount;

    /**
     * Constructs a Price object with the given price and no discount.
     *
     * @param price the price of the product
     */
    public Price(double price) {
        this.price = price;
        this.discount = 0;
    }

    /**
     * Constructs a Price object with the given price and discount.
     *
     * @param price the price of the product
     * @param discount the discount applied to the price
     */
    public Price(double price, double discount) {
        this.price = price;
        this.discount = discount;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public String toString()
    {
        return "" + price; //"\nDiscount: " + discount;
    }
    
    /*private double getDiscountedPrice() {
        
        if(this.discount > 100) {
            this.discount = 100;
        } else if (this.discount == 100){
            return 0;
        }

        double diskon = (this.price * this.discount)/100;
        return this.price-diskon;
    }
    
    public Price(double price, int discount) {
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
        this.discount = 0;
    }

    private double getRebatedPrice() {
        if(this.rebate > this.price) {
            this.price = this.rebate;
        } else if (this.rebate == this.price){
            return 0;
        }
        return this.price - this.rebate;
    }*/
}