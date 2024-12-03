package com.example.andriodpizzeria;

/**
 * This class defines the size of the Pizza
 * It is an enum class and so far have 3 type of sizes offered.
 * @author Gordon Lin modified Nove. 08, 2024
 */
public enum Size {
    SMALL,
    MEDIUM,
    LARGE;

    /**
     * This is a string representation of Size.
     * @return a string representation of the enum.
     */
    @Override
    public String toString(){
        return this.name().charAt(0)+ this.name().substring(1).toLowerCase();
    }

    /**
     * Get the Enum size from String input.
     * @param input the input.
     * @return the enum.
     */
    public static Size getSize(String input){
        if(input.equalsIgnoreCase("small")){
            return Size.SMALL;
        }else if(input.equalsIgnoreCase("medium")){
            return Size.MEDIUM;
        }else if(input.equalsIgnoreCase("large")){
            return Size.LARGE;
        }else {
            return null;
        }
    }

}
