package Practice.factoryPattern;

public abstract class PizzaFactory {
    public Pizza orderPizza(String orderType){
        Pizza pizza = createPizza(orderType);
        pizza.prepare();
        pizza.bake();
}
}
