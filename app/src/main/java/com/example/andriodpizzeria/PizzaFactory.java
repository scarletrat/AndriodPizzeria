package com.example.andriodpizzeria;

/**
 * This is the Pizza Factory Interface.
 */
public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}
