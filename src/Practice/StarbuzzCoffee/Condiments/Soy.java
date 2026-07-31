package Practice.StarbuzzCoffee.Condiments;

import Practice.StarbuzzCoffee.Beverage;
import Practice.StarbuzzCoffee.CondimentsDecorator;
import Practice.StarbuzzCoffee.Size;

public class Soy extends CondimentsDecorator {
    public double SoyCost = 0.10;
    public Soy(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription(){
        return beverage.getDescription() + ", Soy";
    }
}
