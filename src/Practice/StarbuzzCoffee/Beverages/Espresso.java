package Practice.StarbuzzCoffee.Beverages;

import Practice.StarbuzzCoffee.Beverage;
import Practice.StarbuzzCoffee.Size;

public class Espresso extends Beverage {
    public String EspressoDescription = "This is an Espresso";
    public double EspressoCost = 1.40;

    public Espresso(Size size){
        description = EspressoDescription;
        setSize(size);
    }

    public double cost() {
        return EspressoCost;
    }
}
