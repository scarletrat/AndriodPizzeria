package com.example.andriodpizzeria;

/**
 * This class represents a Chicago style pizza and implements the PizzaFactory interface.
 * @author Gordon Lin modified Nov. 08, 2024
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Chicago Style Deluxe Pizza.
     * @return the pizza
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.DEEP_DISH);
        return pizza;
    }

    /**
     * Creates a Chicago Style BBQChicken Pizza.
     * @return the pizza
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.PAN);
        return pizza;
    }

    /**
     * Creates a Chicago Style Meatzza Pizza.
     * @return the pizza
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.STUFFED);
        return pizza;
    }

    /**
     * Creates a Chicago Style Build Your Own Pizza.
     * @return the pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.PAN);
        return pizza;
    }

}
