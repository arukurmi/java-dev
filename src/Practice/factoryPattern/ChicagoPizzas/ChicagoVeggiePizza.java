package Practice.factoryPattern.ChicagoPizzas;

import Practice.factoryPattern.Pizza;

public class ChicagoVeggiePizza extends Pizza {
    public ChicagoVeggiePizza(){
        name = "Chicago style veggie pizza";
        dough = "Chicago style dough";
        sauce = "Chicago sauce";
        toppings.add("Onion");
        toppings.add("Tomatoes");
        toppings.add("brocolli");
        toppings.add("Mushrooms");
        toppings.add("Cheese");
    }

    public void cut(){
        System.out.println("Cutting pizza in square" + name);
    }
}
