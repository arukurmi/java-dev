package Practice.StarbuzzCoffee.Beverages;

import Practice.StarbuzzCoffee.Beverage;
import Practice.StarbuzzCoffee.Size;

public class DarkRoast extends Beverage {
    public String DarkRoastDescription = "This is a DarkRoast";
    public double DarkRoastCost = 1.80;

    public DarkRoast(Size size){
        description = DarkRoastDescription;
        setSize(size);
    }

    public double cost() {
        return DarkRoastCost;
    }
}
