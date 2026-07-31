package Practice.factoryPattern.PizzaFactories;

import Practice.factoryPattern.NyStylePizza.NyStyleCheesePizza;
import Practice.factoryPattern.NyStylePizza.NyStyleVeggiePizza;
import Practice.factoryPattern.Pizza;
import Practice.factoryPattern.PizzaFactory;

public class NyPizzaFactory extends PizzaFactory {
    public Pizza createPizza(String orderType){
        return switch (orderType) {
            case "cheese" -> new NyStyleCheesePizza();
            case "Veggie" -> new NyStyleVeggiePizza();
//            case "Future cases" -> new FutureCasesPizza();
            default -> new NyStyleCheesePizza();
        };
    }
}
