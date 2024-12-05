package com.example.andriodpizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the controller for activity_main.xml, the main activity.
 * Navigate to shopping cart, menu, and order list.
 * @author Gordon Lin, modified Dec. 05, 2024
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    /**
     * Navigate to menu.
     * @param view the view.
     */
    public void menuActivity(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Navigate to shopping cart.
     * @param view the view.
     */
    public void cartActivity(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    /**
     * Navigate to order list.
     * @param view the view.
     */
    public void orderActivity(View view){
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
}