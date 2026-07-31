package Practice.StarbuzzCoffee;

import java.util.*;
import Practice.StarbuzzCoffee.Beverages.Decaf;
import Practice.StarbuzzCoffee.Condiments.Mocha;
import Practice.StarbuzzCoffee.Condiments.Soy;

public class StarbuzzCoffee {
    public static void main(String args[]){
        Beverage order = new Decaf(Size.VENTI);
        Beverage condimentsDecorator = new Mocha(new Soy(order));
        double cost = condimentsDecorator.cost();

        System.out.print(condimentsDecorator.getDescription() + ", Costs: " + cost);
    }
}
