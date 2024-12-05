package com.example.andriodpizzeria;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private Button cancelButton;
    private EditText orderTotal;
    private ListView orderListView;
    private Spinner orderNumSpinner;
    ShareResource resource = ShareResource.getInstance();
    ArrayList<Order> orderlist = resource.getOrderlist();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        getID();
        if(!orderlist.isEmpty()){
            operation();
        }else {
            standby();
        }
    }

    /**
     * When there's no orders in orderlist.
     */
    private void standby(){
        ArrayList<String> temp = new ArrayList<>();
        temp.add("No item to display");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
        orderListView.setAdapter(adapter);
    }

    /**
     * Normal operation when the orderlist is not empty.
     */
    private void operation(){
        fillSpinner();
        updateListView();
        cancelClick();
    }

    /**
     * Cancel an order button
     */
    private void cancelClick(){
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(OrderActivity.this);
                alert.setTitle("Confirmation");
                alert.setMessage("Cancel Order.");
                alert.setNegativeButton("Yes", (dialog, which) -> {
                    int selected = Integer.parseInt(orderNumSpinner.getSelectedItem().toString());
                    removeOrder(selected);
                    fillSpinner();
                    if(orderlist.isEmpty()){
                        standby();
                    }
                    Toast.makeText(getApplicationContext(), "Order Canceled", Toast.LENGTH_SHORT).show();
                }).setPositiveButton("No", (dialog, which) -> {
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Remove the selected order from orderList
     * @param selected the selected order number.
     */
    private void removeOrder(int selected){
        orderlist.removeIf(order -> order.getOrderNumber() == selected);
    }

    /**
     * Updates the listView to the spinner selection.
     */
    private void updateListView(){
        ArrayAdapter<Pizza> adapter = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, orderlist.get(0).getPizzas());
        orderListView.setAdapter(adapter);
        orderNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                int number = Integer.parseInt(selectedOption);
                setListView(number);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Set the list view based on the order number selected.
     * @param number the order number.
     */
    private void setListView(int number){
        ArrayList<Pizza> pizzas = new ArrayList<>();
        for(Order order: orderlist){
            if(order.getOrderNumber() == number){
                pizzas = order.getPizzas();
                break;
            }
        }
        ArrayAdapter<Pizza> adapter = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, pizzas);
        orderListView.setAdapter(adapter);
    }

    /**
     * Fill the spinner with order numbers.
     */
    private void fillSpinner(){
        ArrayList<String> numbers = new ArrayList<>();
        for (Order order : orderlist) {
            numbers.add(String.valueOf(order.getOrderNumber()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                numbers
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumSpinner.setAdapter(adapter);
        updatePrice();
    }

    /**
     * Get the total price of the order number from the spinner.
     */
    private void updatePrice(){
        int selected = Integer.parseInt(orderNumSpinner.getSelectedItem().toString());
        ArrayList<Pizza> temp = new ArrayList<>();
        for(Order order: orderlist){
            if(order.getOrderNumber() == selected){
                temp = order.getPizzas();
                break;
            }
        }
        double price =0.0;
        for(Pizza pizza: temp){
            price = price + pizza.price();
        }
        price = Math.round(price * 100.0) / 100.0;
        double tax = price * 6.625 / 100;
        tax = Math.round(tax * 100.0) / 100.0;
        price = tax+ price;
        price = Math.round(price*100.0)/100.0;
        String output = String.valueOf(price);
        output = "$" + output;
        orderTotal.setText(output);
    }

    /**
     * Get/Set the ID of the values in view.
     */
    private void getID(){
        cancelButton = findViewById(R.id.cancelOrder);
        orderTotal = findViewById(R.id.editorderTotal);
        orderTotal.setFocusable(false);
        orderTotal.setFocusableInTouchMode(false);
        orderListView = findViewById(R.id.orderListView);
        orderNumSpinner = findViewById(R.id.orderNumSpinner);
    }

}
