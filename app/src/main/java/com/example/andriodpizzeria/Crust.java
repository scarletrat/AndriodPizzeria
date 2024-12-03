package com.example.andriodpizzeria;

/**
 * This class defines the crust of the Pizza
 * It is an enum class and so far have 6 type of crusts offered.
 * @author Gordon Lin modified Nove. 08, 2024
 */
public enum Crust {
    DEEP_DISH,
    PAN,
    STUFFED,
    BROOKLYN,
    THIN,
    HAND_TOSSED;

    /**
     * This is a string representation of Crust.
     * @return a string representation of the enum.
     */
    @Override
    public String toString(){
        if(this.name().equalsIgnoreCase("DEEP_DISH")){
            return "DeepDish";
        } else if (this.name().equalsIgnoreCase("HAND_TOSSED")) {
            return "HandTossed";
        }
        return this.name().charAt(0)+ this.name().substring(1).toLowerCase();
    }

}
