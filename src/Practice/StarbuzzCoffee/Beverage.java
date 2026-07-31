package Practice.StarbuzzCoffee;

import lombok.Getter;
import lombok.Setter;

public abstract class Beverage {
    public String description = "This is some description";
    @Getter
    @Setter
    public Size size;

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}
