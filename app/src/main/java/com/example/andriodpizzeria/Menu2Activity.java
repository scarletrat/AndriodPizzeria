package com.example.andriodpizzeria;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu2Activity extends AppCompatActivity {
    private final ArrayList<Topping> available = new ArrayList<>(Arrays.asList(Topping.values()));
    private final ArrayList<Topping> selected = new ArrayList<>();
    private final int MAX_SELECTION_LIMIT = 7;  // Set maximum limit of toppings that can be selected


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        // Get the data passed via Intent
        String selectedItem = getIntent().getStringExtra("item");

        // Display the data
        TextView textView = findViewById(R.id.textView7);
        if (selectedItem != null) {
            textView.setText(selectedItem);
        } else {
            textView.setText("No item selected");
        }
    }
    /**
    private void updateList(){
        listview.setEnabled(false);
        ArrayAdapter<Topping> toppingAdapter = new ArrayAdapter<Topping>(this, android.R.layout.simple_list_item_1, available);
        listview.setAdapter(toppingAdapter);
        ArrayAdapter<Topping> toppingAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selected);
        listview2.setAdapter(toppingAdapter2);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            if (selected.size() >= MAX_SELECTION_LIMIT) {
                Toast.makeText(MenuActivity.this, "Maximum of " + MAX_SELECTION_LIMIT + " toppings can be added!", Toast.LENGTH_SHORT).show();
                return;
            }
            // Get the selected item
            Topping selectedTopping = available.get(position);
            // Remove the item from the original list and add it to the moved list
            available.remove(position);
            selected.add(selectedTopping);
            // Notify the adapters that the data has changed
            toppingAdapter.notifyDataSetChanged();
            toppingAdapter2.notifyDataSetChanged();
            // Show a Toast message
            Toast.makeText(MenuActivity.this, "Added: " + selectedTopping.name(), Toast.LENGTH_SHORT).show();
        });
    }
     */
}
