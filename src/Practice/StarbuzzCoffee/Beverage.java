package Practice.StarbuzzCoffee;

import lombok.Getter;
import lombok.Setter;

public abstract class Beverage {
    public String description = "This is some description";
    @Getter
    @Setter
    public Size size;
}
