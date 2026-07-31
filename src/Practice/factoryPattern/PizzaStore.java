package Practice.factoryPattern;

import Practice.factoryPattern.NyStylePizza.NyStyleCheesePizza;
import Practice.factoryPattern.PizzaFactories.ChicagoPizzaFactory;
import Practice.factoryPattern.PizzaFactories.NyPizzaFactory;

public class PizzaStore {
    public static void main(String args[]){
        PizzaFactory nyPizzaStore = new NyPizzaFactory();
        PizzaFactory chicagoPizzaStore = new ChicagoPizzaFactory();
        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        Pizza pizza2 = chicagoPizzaStore.orderPizza("Veggie");
    }
}
