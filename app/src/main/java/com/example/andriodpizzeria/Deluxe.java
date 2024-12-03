package com.example.andriodpizzeria;

/**
 * This class represents a Deluxe pizza and is a subclass of Pizza object.
 * @author Gordon Lin modified Nov. 08, 2024
 */
public class Deluxe extends Pizza{

    /**
     * No argument type constructor.
     */
    public Deluxe(){
        super();
        this.addTopping(Topping.SAUSAGE);
        this.addTopping(Topping.PEPPERONI);
        this.addTopping(Topping.GREEN_PEPPER);
        this.addTopping(Topping.ONION);
        this.addTopping(Topping.MUSHROOM);
    }

    /**
     * This is the price of the Deluxe Pizza given the size of the pizza.
     * @return the price.
     */
    @Override
    public double price() {
        if(this.getSize() == null){
            return -1;
        }
        if(this.getSize().name().equalsIgnoreCase("SMALL")){
            return 16.99;
        }else if(this.getSize().name().equalsIgnoreCase("MEDIUM")){
            return 18.99;
        }else if(this.getSize().name().equalsIgnoreCase("LARGE")){
            return 20.99;
        }else{
            return -1;
        }
    }

    /**
     * This is a string representation of Deluxe Pizza.
     * @return a string representation of the object.
     */
    @Override
    public String toString(){
        String type;
        if(this.getCrust().equals(Crust.DEEP_DISH)){
            type = "Chicago Style";
        }else{
            type = "New York Style";
        }
        return "Deluxe(" + type +  super.toString();
    }

}
