package Practice.StarbuzzCoffee.Beverages;

import Practice.StarbuzzCoffee.Beverage;
import Practice.StarbuzzCoffee.Size;

public class HouseBlend extends Beverage {
    public String HouseBlendDescription = "This is a HouseBlend";
    public double HouseBlendCost = 1.80;

    public HouseBlend(Size size){
        description = HouseBlendDescription;
        setSize(size);
    }

    public double cost() {
        return HouseBlendCost;
    }
}