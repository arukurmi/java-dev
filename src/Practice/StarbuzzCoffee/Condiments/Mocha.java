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

    public double cost(){
        if(getSize() == Size.TALL) MochaCost = 0.20;
        if(getSize() == Size.GRAND) MochaCost = 0.40;
        if(getSize() == Size.VENTI) MochaCost = 0.50;
        return this.beverage.cost() + MochaCost;
    }
}

