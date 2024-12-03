package com.example.andriodpizzeria;

/**
 * This class represents a Build Your Own type pizza and is a subclass of Pizza object.
 * @author Gordon Lin, Christopher Lee modified Nov. 17, 2024
 */
public class BuildYourOwn extends Pizza{

    /**
     * No argument type constructor.
     */
    public BuildYourOwn(){
        super();
    }

    /**
     * This is the price of the Build Your Own Pizza given the size of the pizza
     * and the amount of toppings added.
     * @return the price.
     */
    @Override
    public double price() {
        double price;
        if(this.getSize() == null){
            return -1;
        }
        if(this.getSize().name().equalsIgnoreCase("SMALL")){
            price = 8.99;
        }else if(this.getSize().name().equalsIgnoreCase("MEDIUM")){
            price = 10.99;
        }else if(this.getSize().name().equalsIgnoreCase("LARGE")){
            price = 12.99;
        }else{
            return -1;
        }
        price += this.getToppings().size() * 1.69;
        price = Math.round(price * 100.0) / 100.0;
        return price;
    }

    /**
     * This is a string representation of Build Your Own Pizza.
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
        return "Build Your Own(" + type +  super.toString();
    }

}
