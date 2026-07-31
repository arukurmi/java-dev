package Practice.factoryPattern.NyStylePizza;

import Practice.factoryPattern.Pizza;

public class NyStyleVeggiePizza extends Pizza {
    public NyStyleVeggiePizza(){
        name = "Ny style veggie pizza";
        dough = "Ny style dough";
        sauce = "Ny style marinara sauce";
        toppings.add("Onion");
        toppings.add("Tomatoes");
        toppings.add("Cheese");
    }

}
