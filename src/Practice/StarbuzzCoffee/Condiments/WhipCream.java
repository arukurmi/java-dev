package Practice.StarbuzzCoffee.Condiments;

import Practice.StarbuzzCoffee.Beverage;
import Practice.StarbuzzCoffee.CondimentsDecorator;
import Practice.StarbuzzCoffee.Size;

public class WhipCream extends CondimentsDecorator {
    public double WhipCreamCost = 0.20;
    public WhipCream(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription(){
        return beverage.getDescription() + ", WhipCream";
    }
}
