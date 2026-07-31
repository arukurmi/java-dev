package Practice.factoryPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    protected String name;
    protected String dough;
    protected String sauce;
    protected List<String> toppings = new ArrayList<>();

    protected void prepare(){
        System.out.println("Preparing pizza: " + this.name + "; Adding toppings: " + toppings);
    }
    protected void bake(){
        System.out.println("Baking pizza: " + this.name);
    }
    protected void cut(){
        System.out.println("Cutting pizza in diagonal: " + this.name);
    }
    protected void box(){
        System.out.println("Packing pizza: " + this.name);
    }
}
