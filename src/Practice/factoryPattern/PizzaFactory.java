package Practice.factoryPattern;

public abstract class PizzaFactory {
    public Pizza orderPizza(String orderType){
        Pizza pizza = createPizza(orderType);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String orderType);
}
