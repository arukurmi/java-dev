package Practice.StarbuzzCoffee;

public abstract class CondimentsDecorator extends Beverage{
    public Beverage beverage;
    public abstract double cost();
}
