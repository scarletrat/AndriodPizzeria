package com.example.andriodpizzeria;

/**
 * This class defines the toppings of the Pizza
 * It is an enum class and so far have 10 type of toppings offered.
 * @author Gordon Lin modified Nove. 08, 2024
 */
public enum Topping {
    BBQ_CHICKEN,
    BEEF,
    CHEDDAR,
    GREEN_PEPPER,
    HAM,
    MUSHROOM,
    ONION,
    PEANUT,
    PEPPERONI,
    PINEAPPLE,
    PROVOLONE,
    SAUSAGE,
    SPINACH;

    /**
     * This is a string representation of Topping.
     * @return a string representation of the enum.
     */
    @Override
    public String toString() {
        if (this.name().equalsIgnoreCase("Green_Pepper")) {
            return "Green Pepper";
        }
        if (this.name().equalsIgnoreCase("BBQ_Chicken")) {
            return "BBQ Chicken";
        }
        return this.name().charAt(0)+ this.name().substring(1).toLowerCase();
    }
}
