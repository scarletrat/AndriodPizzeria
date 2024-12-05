package com.example.andriodpizzeria;

import java.util.ArrayList;

public class ShareResource {
    private static ShareResource resource;
    private ArrayList<Order> orderlist = new ArrayList<>();
    private ArrayList<Pizza> pizzas = new ArrayList<>();

    /**
     * Creates instance of resource
     * @return the reference of the only instance of this class
     */
    public static synchronized ShareResource getInstance() {
        if (resource == null)
            resource = new ShareResource();
        return resource;
    }

    /**
     * Get the current cart list of pizzas.
     * @return pizzas list.
     */
    public ArrayList<Pizza> getPizzas(){
        return pizzas;
    }

    /**
     * Get current order list of orders of pizzas
     * @return orderlist
     */
    public ArrayList<Order> getOrderlist() {
        return orderlist;
    }

    /**
     * Add a pizza to the cart/a list of pizzas.
     * @param pizza the pizza to be added.
     */
    public void addCart(Pizza pizza){
        pizzas.add(pizza);
    }

    /**
     * Placed an order after clicking place order button.
     */
    public void placeOrder(){
        ArrayList<Pizza> temp = new ArrayList<>(pizzas);
        Order order = new Order(temp);
        if(orderlist.isEmpty()){
            order.setOrderNumber(1);
        }else {
            int size = orderlist.size()-1;
            order.setOrderNumber(orderlist.get(size).getOrderNumber()+1);
        }
        orderlist.add(order);
        pizzas = new ArrayList<>();
    }
}
