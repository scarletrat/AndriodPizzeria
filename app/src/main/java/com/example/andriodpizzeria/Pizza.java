package com.example.andriodpizzeria;

import java.util.ArrayList;

/**
 * This abstract class represents a pizza and is a superclass of Deluxe, Meatzza
 * and Build Your Own Pizzas. Has the abstract method price().
 * @author Gordon Lin modified Nov. 17, 2024
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();

    /**
     * No argument type constructor.
     */
    public Pizza() {
        this.crust = null;
        this.size = null;
        this.toppings= new ArrayList<>();
    }

    /**
     * Two argument type constructor.
     * @param crust the crust.
     * @param size the size.
     */
    public Pizza(Crust crust, Size size) {
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    /**
     * One argument type constructor.
     * @param toppings the toppings.
     */
    public Pizza(ArrayList<Topping> toppings) {
        this.crust = null;
        this.size = null;
        this.toppings = toppings;
    }

    /**
     * Three arguments type constructor.
     * @param crust the crust.
     * @param size the size.
     * @param toppings the toppings.
     */
    public Pizza(Crust crust, Size size, ArrayList<Topping> toppings) {
        this.crust = crust;
        this.size = size;
        this.toppings = toppings;
    }

    /**
     * Gets the toppings of the pizza.
     * @return the toppings list.
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Add topping to the pizza.
     * @param topping the toppings list.
     */
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    /**
     * Remove the topping from the pizza.
     * @param topping the toppings list.
     */
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    /**
     * Get the crust of the pizza.
     * @return the crust.
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Set the crust of the pizza.
     * @param crust the crust.
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Get the size of the pizza.
     * @return the size.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Set the size of the pizza.
     * @param size the size.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * This is a string representation of the Pizza object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String top = "";
        for(int i = 0;i < toppings.size(); i++){
            top = top + " " + toppings.get(i) + ",";
        }
        return " - " + crust + "),  " + " Toppings: " + top + " " + size + " $"+this.price();
    }

}
