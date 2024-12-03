package com.example.andriodpizzeria;

/**
 * This class represents a BBQChicken pizza and is a subclass of Pizza object.
 * @author Gordon Lin modified Nov. 08, 2024
 */
public class BBQChicken extends Pizza{

    /**
     * No argument constructor of a BBQChicken Pizza.
     */
    public BBQChicken(){
        super();
        this.addTopping(Topping.BBQ_CHICKEN);
        this.addTopping(Topping.GREEN_PEPPER);
        this.addTopping(Topping.PROVOLONE);
        this.addTopping(Topping.CHEDDAR);
    }

    /**
     * This is the price of the BBQChicken Pizza given the size of the pizza.
     * @return the price.
     */
    @Override
    public double price() {
        if(this.getSize() == null){
            return -1;
        }
        if(this.getSize().name().equalsIgnoreCase("SMALL")){
            return 14.99;
        }else if(this.getSize().name().equalsIgnoreCase("MEDIUM")){
            return 16.99;
        }else if(this.getSize().name().equalsIgnoreCase("LARGE")){
            return 19.99;
        }else{
            return -1;
        }
    }

    /**
     * This is a string representation of BBQChicken Pizza.
     * @return a string representation of the object.
     */
    @Override
    public String toString(){
        String type;
        if(this.getCrust().equals(Crust.PAN)){
            type = "Chicago Style";
        }else{
            type = "New York Style";
        }
        return "BBQChicken(" + type +  super.toString();
    }

}
