package com.example.andriodpizzeria;

/**
 * This class represents a Meatzza pizza and is a subclass of Pizza object.
 * @author Gordon Lin, Christopher Lee modified Nov. 08, 2024
 */
public class Meatzza extends Pizza{

    /**
     * No argument type constructor.
     */
    public Meatzza(){
        super();
        this.addTopping(Topping.SAUSAGE);
        this.addTopping(Topping.PEPPERONI);
        this.addTopping(Topping.BEEF);
        this.addTopping(Topping.HAM);
    }

    /**
     * This is the price of the Meatzza Pizza given the size of the pizza.
     * @return the price.
     */
    @Override
    public double price() {
        if(this.getSize() == null){
            return -1;
        }
        if(this.getSize().name().equalsIgnoreCase("SMALL")){
            return 17.99;
        }else if(this.getSize().name().equalsIgnoreCase("MEDIUM")){
            return 19.99;
        }else if(this.getSize().name().equalsIgnoreCase("LARGE")){
            return 21.99;
        }else{
            return -1;
        }
    }

    /**
     * This is a string representation of Meatzza Pizza.
     * @return a string representation of the object.
     */
    @Override
    public String toString(){
        String type;
        if(this.getCrust().equals(Crust.STUFFED)){
            type = "Chicago Style";
        }else{
            type = "New York Style";
        }
        return "Meatzza(" + type +  super.toString();
    }

}
