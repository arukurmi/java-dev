package Practice.StarbuzzCoffee.Condiments;

import Practice.StarbuzzCoffee.Beverage;
import Practice.StarbuzzCoffee.CondimentsDecorator;
import Practice.StarbuzzCoffee.Size;

public class Mocha extends CondimentsDecorator {
    public double MochaCost = 0.40;
    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription(){
        return beverage.getDescription() + ", Mocha";
    }
}
