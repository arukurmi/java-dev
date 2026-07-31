package Practice.factoryPattern.NyStylePizza;

import Practice.factoryPattern.Pizza;

public class NyStyleCheesePizza extends Pizza {
    public NyStyleCheesePizza(){
        name = "Ny style cheese pizza";
        dough = "Ny style dough";
        sauce = "Ny style marinara sauce";
        toppings.add("Onion sauce");
        toppings.add("Grated mozz Cheese");
        toppings.add("Grated blue Cheese");
    }
}
