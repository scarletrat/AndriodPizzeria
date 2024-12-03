package com.example.andriodpizzeria;

/**
 * This class represents a New York style pizza and implements the PizzaFactory interface.
 * @author Gordon Lin modified Nov. 08, 2024
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a New York Style Deluxe Pizza.
     * @return the pizza
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.BROOKLYN);
        return pizza;
    }

    /**
     * Creates a New York Style BBQChicken Pizza.
     * @return the pizza
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.THIN);
        return pizza;
    }

    /**
     * Creates a New York Style Meatzza Pizza.
     * @return the pizza
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.HAND_TOSSED);
        return pizza;
    }

    /**
     * Creates a New York Style Build Your Own Pizza.
     * @return the pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.HAND_TOSSED);
        return pizza;
    }

}
