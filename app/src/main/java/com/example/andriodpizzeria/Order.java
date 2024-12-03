package com.example.andriodpizzeria;

import java.util.ArrayList;

/**
 * This class represents an order which is a list of pizzas associated with an order number
 *  @author Gordon Lin, Christopher Lee modified Nov. 08, 2024
 */
public class Order {
    private int number;
    private ArrayList<Pizza> pizzas;

    /**
     * no argument constructor
     */
    public Order(){
        this.pizzas = new ArrayList<>();
    }

    /**
     * constructor that takes pizza array list
     * @param pizzas array list of pizzas
     */
    public Order(ArrayList<Pizza> pizzas){
        this.pizzas = pizzas;
    }

    /**
     * sets the order number of the order
     * @param number order number
     */
    public void setOrderNumber(int number){
        this.number = number;
    }

    /**
     * gives the order number of the order
     * @return order number
     */
    public int getOrderNumber(){
        return this.number;
    }

    /**
     * returns array list of pizzas
     * @return pizza array list
     */
    public ArrayList<Pizza> getPizzas(){
        return this.pizzas;
    }

    /**
     * adds pizza to pizza array list
     * @param pizza pizza to add to array list
     */
    public void add(Pizza pizza){
        this.pizzas.add(pizza);
    }

    /**
     * removes pizza from pizza array list
     * @param pizza pizza to be removed
     */
    public void remove(Pizza pizza){
        this.pizzas.remove(pizza);
    }

}
