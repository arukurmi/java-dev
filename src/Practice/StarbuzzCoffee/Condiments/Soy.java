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

    public double cost(){
        if(getSize() == Size.TALL) SoyCost = 0.20;
        if(getSize() == Size.GRAND) SoyCost = 0.40;
        if(getSize() == Size.VENTI) SoyCost = 0.50;
        return this.beverage.cost() + SoyCost;
    }
}
