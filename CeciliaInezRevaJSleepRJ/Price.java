package CeciliaInezRevaJSleepRJ;

public class Price{
    public double price;
    public double rebate;
    public int discount;
    
    public Price(double price) {
        this.price = price;
        this.discount = 0;
        this.rebate = 0;
    }

    public Price(double price, int discount) {
        this.price = price;
        this.discount = discount;
        this.rebate = 0;
    }
    
    public Price(double price, double rebate) {
        this.price = price;
        this.discount = discount;
        this.discount = 0;
    }
    
    private double getDiscountedPrice() {
        if(this.discount >= 100) {
            this.discount = 100;
        }
        if(this.discount == 100){
            return 0;
        }else {
            return this.price - (this.price * this.discount);
        }
    }

    private double getRebatedPrice() {
        if(this.rebate >= price) {
            return price;
        } else {
            return this.price - (this.price * this.rebate);
        }
    }
}