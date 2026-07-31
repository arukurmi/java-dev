package Practice.StarbuzzCoffee.Beverages;

import Practice.StarbuzzCoffee.Beverage;
import Practice.StarbuzzCoffee.Size;

public class Decaf  extends Beverage {
    public String DecafDescription = "This is a Decaf";
    public double DecafCost = 1.40;

    public Decaf(Size size){
        description = DecafDescription;
        setSize(size);
    }

    public double cost() {
        return DecafCost;
    }
}