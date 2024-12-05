package com.example.andriodpizzeria;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu2Activity extends AppCompatActivity {
    private final ArrayList<Topping> available = new ArrayList<>(Arrays.asList(Topping.values()));
    private final ArrayList<Topping> selected = new ArrayList<>();
    private final int MAX_SELECTION_LIMIT = 7;  // Set maximum limit of toppings that can be selected
    private ListView listView;
    private ListView listView2;
    private EditText editCrust;
    private EditText priceText;
    private TextView textView;
    private RadioGroup sizeGroup;
    private Button addButton;
    private Pizza pizza = null;
    private String selectedItem;
    private double price = 0.0;

    ShareResource resource = ShareResource.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        // Get the data passed via Intent
        selectedItem = getIntent().getStringExtra("item");
        getID();
        if (selectedItem != null) {
            textView.setText(selectedItem);
            String output = "You have selected " + selectedItem +" !";
            textView.setText(output);
            String[] parts = selectedItem.split(" ");
            System.out.println(parts[2]);
            getOptions();
        } else {
            textView.setText("No item selected");
        }
        pizza.setSize(Size.SMALL);
        addClick();
        sizeClick();
    }
    private void sizeClick(){
        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) { // Make sure a valid option is selected
                RadioButton selectedRadioButton = findViewById(checkedId);
                String selectedText = selectedRadioButton.getText().toString();
                if(selectedText.equalsIgnoreCase("small")){
                    pizza.setSize(Size.SMALL);
                } else if (selectedText.equalsIgnoreCase("medium")) {
                    pizza.setSize(Size.MEDIUM);
                }else{
                    pizza.setSize(Size.LARGE);
                }
                price = pizza.price();
                double roundedNumber = Math.round(price * 100.0) / 100.0;
                String output = "$: "+ roundedNumber;
                priceText.setText(output);
                Toast.makeText(Menu2Activity.this, "Selected: " + selectedText, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addClick(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Menu2Activity.this);
                alert.setTitle("Alert");
                alert.setMessage("Adding " +pizza+" to Cart!");
                //anonymous inner class to handle the onClick event of YES or NO.
                alert.setPositiveButton("Yes", (dialog,which)-> {
                    Toast.makeText(getApplicationContext(), "you clicked YES", Toast.LENGTH_LONG).show();
                }).setNegativeButton("No", (dialog, which) -> {
                    Toast.makeText(getApplicationContext(), "you clicked NO", Toast.LENGTH_LONG).show();
                });
                AlertDialog dialog = alert.create();
                dialog.show();
                resource.addCart(pizza);
            }
        });
    }
    private void updateCrust(){
        String pizzaType = getType();
        String[] parts = pizzaType.split(" ");
        String style = parts[0];
        String type = parts[1];
        String output;
        if(style.equalsIgnoreCase("Chicago")){
            if(type.equalsIgnoreCase("deluxe")){
                output = "Deep Dish";
                editCrust.setText(output);
            } else if (type.equalsIgnoreCase("meatzza")) {
                output = "Stuffed";
                editCrust.setText(output);
            }else{
                output = "Pan";
                editCrust.setText(output);
            }
        }else {
            if(type.equalsIgnoreCase("deluxe")){
                output="Brooklyn";
                editCrust.setText(output);
            } else if (type.equalsIgnoreCase("bbq")) {
                output = "Thin";
                editCrust.setText(output);
            }else{
                output="Hand-tossed";
                editCrust.setText(output);
            }
        }
    }
    private void getOptions(){
        updatePrice();
        String pizzaType = getType();
        String[] parts = pizzaType.split(" ");
        String style = parts[0];
        String type = parts[1];
        updateCrust();
        if(type.equalsIgnoreCase("build")){
            updateList(style);
        } else if (type.equalsIgnoreCase("deluxe")) {
            updateDeluxe(style);
        } else if (type.equalsIgnoreCase("bbq")) {
            updateBBQ(style);
        }else{
            updateMeat(style);
        }
    }
    private void updateDeluxe(String style){
        if(style.equalsIgnoreCase("Chicago")){
            PizzaFactory temp = new ChicagoPizza();
            pizza = temp.createDeluxe();
        }else{
            PizzaFactory temp = new NYPizza();
            pizza = temp.createDeluxe();
        }
        ArrayAdapter<Topping> toppingAdapter = new ArrayAdapter<Topping>(this, android.R.layout.simple_list_item_1, available);
        for(int i = 0; i<pizza.getToppings().size();i++){
            available.remove(pizza.getToppings().get(i));
            selected.add(pizza.getToppings().get(i));
        }
        listView.setAdapter(toppingAdapter);
        ArrayAdapter<Topping> toppingAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selected);
        listView2.setAdapter(toppingAdapter2);
    }
    private void updateBBQ(String style){
        if(style.equalsIgnoreCase("Chicago")){
            PizzaFactory temp = new ChicagoPizza();
            pizza = temp.createBBQChicken();
        }else{
            PizzaFactory temp = new NYPizza();
            pizza = temp.createBBQChicken();
        }
        ArrayAdapter<Topping> toppingAdapter = new ArrayAdapter<Topping>(this, android.R.layout.simple_list_item_1, available);
        for(int i = 0; i<pizza.getToppings().size();i++){
            available.remove(pizza.getToppings().get(i));
            selected.add(pizza.getToppings().get(i));
        }
        listView.setAdapter(toppingAdapter);
        ArrayAdapter<Topping> toppingAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selected);
        listView2.setAdapter(toppingAdapter2);
    }
    private void updateMeat(String style){
        if(style.equalsIgnoreCase("Chicago")){
            PizzaFactory temp = new ChicagoPizza();
            pizza = temp.createMeatzza();
        }else{
            PizzaFactory temp = new NYPizza();
            pizza = temp.createMeatzza();
        }
        ArrayAdapter<Topping> toppingAdapter = new ArrayAdapter<Topping>(this, android.R.layout.simple_list_item_1, available);
        for(int i = 0; i<pizza.getToppings().size();i++){
            available.remove(pizza.getToppings().get(i));
            selected.add(pizza.getToppings().get(i));
        }
        listView.setAdapter(toppingAdapter);
        ArrayAdapter<Topping> toppingAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selected);
        listView2.setAdapter(toppingAdapter2);
    }
    private void getID(){
        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);
        listView.setBackgroundColor(Color.LTGRAY);  // Set the background color to light gray
        listView2.setBackgroundColor(Color.LTGRAY);  // Set the background color to light gray
        editCrust = findViewById(R.id.editCrust);
        priceText = findViewById(R.id.priceText);
        textView = findViewById(R.id.textView7);
        priceText.setFocusable(false);
        priceText.setFocusableInTouchMode(false);
        editCrust.setFocusable(false);
        editCrust.setFocusableInTouchMode(false);
        sizeGroup = findViewById(R.id.sizeGroup);
        sizeGroup.check(R.id.smallButton);
        addButton = findViewById(R.id.addButton);
    }
    private void updateList(String style){
        if(style.equalsIgnoreCase("Chicago")){
            PizzaFactory temp = new ChicagoPizza();
            pizza = temp.createBuildYourOwn();
        }else{
            PizzaFactory temp = new NYPizza();
            pizza = temp.createBuildYourOwn();
        }
        pizza.setSize(Size.SMALL);
        ArrayAdapter<Topping> toppingAdapter = new ArrayAdapter<Topping>(this, android.R.layout.simple_list_item_1, available);
        listView.setAdapter(toppingAdapter);
        ArrayAdapter<Topping> toppingAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selected);
        listView2.setAdapter(toppingAdapter2);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (selected.size() >= MAX_SELECTION_LIMIT) {
                Toast.makeText(Menu2Activity.this, "Maximum of " + MAX_SELECTION_LIMIT + " toppings can be added!", Toast.LENGTH_SHORT).show();
                return;
            }
            Topping selectedTopping = available.get(position);
            available.remove(position);
            selected.add(selectedTopping);
            pizza.addTopping(selectedTopping);
            toppingAdapter.notifyDataSetChanged();
            toppingAdapter2.notifyDataSetChanged();
            updateToppingPrice(true);
            Toast.makeText(Menu2Activity.this, "Added: " + selectedTopping.name(), Toast.LENGTH_SHORT).show();
        });
        listView2.setOnItemClickListener((parent, view, position, id) -> {
            Topping selectedTopping = selected.get(position);
            available.add(selectedTopping);
            selected.remove(position);
            pizza.removeTopping(selectedTopping);
            toppingAdapter.notifyDataSetChanged();
            toppingAdapter2.notifyDataSetChanged();
            updateToppingPrice(false);
            Toast.makeText(Menu2Activity.this, "Removed: " + selectedTopping.name(), Toast.LENGTH_SHORT).show();
        });
    }
    private void updateToppingPrice(boolean add){
        if(add){
            price = price + 1.69;
        }else{
            price = price -1.69;
        }
        double roundedNumber = Math.round(price * 100.0) / 100.0;
        String output = "$: "+ roundedNumber;
        priceText.setText(output);
    }
    private void updatePrice(){
        String pizzaType = getType();
        String []parts = pizzaType.split(" ");
        String type = parts[1];
        if(type.equalsIgnoreCase("build")){
            price = 8.99;
        } else if (type.equalsIgnoreCase("bbq")) {
            price = 14.99;
        } else if (type.equalsIgnoreCase("meatzza")) {
            price = 17.99;
        } else if (type.equalsIgnoreCase("deluxe")) {
            price = 16.99;
        }
        double roundedNumber = Math.round(price * 100.0) / 100.0;
        String output = "$: "+ roundedNumber;
        priceText.setText(output);
    }
    private String getType(){
        String[] parts = selectedItem.split(" ");
        String style;
        if(parts[0].equalsIgnoreCase("Chicago")){
            style = "Chicago";
        }else{
            style = "NY";
        }
        String type = parts[2];
        if (type.equalsIgnoreCase("build")) {
            return style+ " Build Your Own";
        }
        if (type.equalsIgnoreCase("deluxe")) {
            return style+ " Deluxe";
        }
        if (type.equalsIgnoreCase("bbq")) {
            return style+ " BBQ Chicken";
        }
        return style +" Meatzza";
    }

}
