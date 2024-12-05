package com.example.andriodpizzeria;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This is the controller for activity_cart.xml. View your pizzas on shopping cart
 * And remove pizza and placed the order.
 * @author Gordon Lin, Christopher Lee modified Dec. 05, 2024
 */

public class CartActivity extends AppCompatActivity {

    private EditText editSubtotal;
    private EditText editTax;
    private EditText editTotal;
    private ListView cartList;
    private Button removePizza;
    private Button addOrder;
    private int selectedPosition = -1; // Default to no selection
    ShareResource resource = ShareResource.getInstance();
    ArrayList<Pizza> pizzas = resource.getPizzas();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        getId();
        updateCost();
        if(!pizzas.isEmpty()){
            normal();
        }else {
            notNormal();
        }
    }

    /**
     * Set the ids
     */
    private void getId(){
        editSubtotal = findViewById(R.id.editSubtotal);
        editTax = findViewById(R.id.editTax);
        editTotal = findViewById(R.id.editTotal);
        cartList = findViewById(R.id.cartList);
        removePizza = findViewById(R.id.removePizza);
        addOrder = findViewById(R.id.addOrder);
        editSubtotal.setFocusable(false);
        editSubtotal.setFocusableInTouchMode(false);
        editTax.setFocusable(false);
        editTax.setFocusableInTouchMode(false);
        editTotal.setFocusable(false);
        editTotal.setFocusableInTouchMode(false);
    }

    /**
     * Update the cost of the order.
     */
    private void updateCost(){
        double subtotal = 0.0;
        if(!pizzas.isEmpty()) {
            for (int i = 0; i < pizzas.size(); i++) {
                subtotal += pizzas.get(i).price();
            }
            if (subtotal == 0.0) {
                return;
            }
            double roundedSub = Math.round(subtotal * 100.0) / 100.0;
            String output = "$" + roundedSub;
            editSubtotal.setText(output);
            double tax = subtotal * 6.625 / 100;
            tax = Math.round(tax * 100.0) / 100.0;
            output = "$" + tax;
            editTax.setText(output);
            double total = roundedSub+tax;
            total = Math.round(total*100.0)/100.0;
            output="$" + total;
            editTotal.setText(output);
        }
        else {
            editSubtotal.setText(null);
            editTax.setText(null);
            editTotal.setText(null);
        }
    }

    /**
     * The remove button is clicked when there's items on the list.
     * Remove pizza from the last clicked on the list.
     * If last pizza is removed go to notNormal()
     */
    private void removeClick(){
        ArrayAdapter<Pizza> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzas);
        cartList.setAdapter(adapter);
        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
            }
        });
        removePizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    Pizza pizza = pizzas.get(selectedPosition);
                    AlertDialog.Builder alert = new AlertDialog.Builder(CartActivity.this);
                    alert.setTitle("Confirmation");
                    alert.setMessage("Remove" + pizza + " From Cart.");
                    alert.setNegativeButton("Yes", (dialog, which) -> {
                        pizzas.remove(pizza);
                        adapter.notifyDataSetChanged();
                        selectedPosition = -1; // Reset selection
                        updateCost();
                        if(pizzas.isEmpty()){
                            notNormal();
                        }
                        Toast.makeText(CartActivity.this, "Removed Pizza.", Toast.LENGTH_SHORT).show();
                    }).setPositiveButton("No", (dialog, which) -> {
                        Toast.makeText(CartActivity.this, "Clicked NO", Toast.LENGTH_SHORT).show();
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                } else {
                    Toast.makeText(CartActivity.this, "No item selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Normal operation where there's item on the list.
     */
    private void normal(){
        ArrayAdapter<Pizza> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzas);
        removeClick();
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CartActivity.this);
                alert.setTitle("Confirmation");
                alert.setMessage("Placing order!");
                alert.setNegativeButton("Yes", (dialog, which) -> {
                    Toast.makeText(getApplicationContext(), "Order Placed.", Toast.LENGTH_SHORT).show();
                    resource.placeOrder();
                    pizzas = new ArrayList<>();
                    adapter.notifyDataSetChanged();
                    updateCost();
                    notNormal();
                }).setPositiveButton("No", (dialog, which) -> {
                    Toast.makeText(getApplicationContext(), "Clicked NO", Toast.LENGTH_SHORT).show();
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Not normal operation when there's no items on the list.
     */
    public void notNormal(){
        ArrayList<String> temp = new ArrayList<>();
        temp.add("No item to display");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, temp);
        cartList.setAdapter(adapter);
        removePizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Cart Empty!", Toast.LENGTH_SHORT).show();
            }
        });
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Cart Empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * When presed back button, go to parent activity.
     */
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        // Navigate to the parent activity explicitly
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

        // Call super if you want the default behavior
        // In this case, we're overriding the behavior, so don't call super
        finish(); // Ensure this activity is removed from the back stack
    }


}



