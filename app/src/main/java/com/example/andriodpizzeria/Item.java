package com.example.andriodpizzeria;

/**
 * This class defines the data structure of an item to be displayed in the RecyclerView
 * @author Gordon Lin, modified Dec.05, 2024
 */
public class Item {
    private String name;
    private int imageResource;

    /**
     * Parameterized constructor.
     * @param name the name.
     * @param imageResource the imageResource.
     */
    public Item(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    /**
     * Getter method that returns the item name of an item.
     * @return the item name of an item.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method that returns the image of an item.
     * @return the image of an item.
     */
    public int getImageResource() {
        return imageResource;
    }
}
