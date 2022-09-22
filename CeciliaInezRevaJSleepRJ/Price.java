package CeciliaInezRevaJSleepRJ;

public class Price{
    public double price;
    public double rebate;
    public int discount;
    
    public Price(double price) {
        this.price = price;
        this.rebate = 0;
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
    
    private double getDiscountedPrice() {
        
        if(this.discount > 100) {
            this.discount = 100;
        } else if (this.discount == 100){
            return 0;
        }

        double diskon = (this.price * this.discount)/100;
        return this.price-diskon;
    }

    private double getRebatedPrice() {
        if(this.rebate > this.price) {
            this.price = this.rebate;
        } else if (this.rebate == this.price){
            return 0;
        }
        return this.price - this.rebate;
    }
}