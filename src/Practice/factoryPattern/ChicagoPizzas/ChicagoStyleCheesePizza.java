package Practice.factoryPattern.ChicagoPizzas;

import Practice.factoryPattern.Pizza;

public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza(){
        name = "Chicago style cheese pizza";
        dough = "Chicago style dough";
        sauce = "Chicago style marinara sauce";
        toppings.add("Grated Reggiano Cheese");
        toppings.add("Grated Standard Cheese");
    }

    public void cut(){
        System.out.println("Cutting pizza in square");
    }
}
