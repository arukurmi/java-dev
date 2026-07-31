package Practice.factoryPattern.PizzaFactories;

import Practice.factoryPattern.ChicagoPizzas.ChicagoStyleCheesePizza;
import Practice.factoryPattern.ChicagoPizzas.ChicagoVeggiePizza;
import Practice.factoryPattern.Pizza;
import Practice.factoryPattern.PizzaFactory;

public class ChicagoPizzaFactory extends PizzaFactory {
    public Pizza createPizza(String orderType){
        return switch (orderType) {
            case "cheese" -> new ChicagoStyleCheesePizza();
            case "Veggie" -> new ChicagoVeggiePizza();
            default -> new ChicagoStyleCheesePizza();
        };
    }
}
