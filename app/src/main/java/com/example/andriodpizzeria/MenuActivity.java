package com.example.andriodpizzeria;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is the controller for activity_menu.xml. Choose a pizza type and style
 * And go to activity_menu2.xml to verify the choice to add to cart.
 * @author Gordon Lin, modified Dec. 05, 2024
 */
public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.chicagodeluxe, R.drawable.chicagobbq, R.drawable.chicagomeat,
            R.drawable.chicagobyo, R.drawable.nydeluxe, R.drawable.nybbq, R.drawable.nymeat,
            R.drawable.nybyo};

    /**
     * initializes UI and is called when the activity is created
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        recyclerView = findViewById(R.id.recyclerView);
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i],itemImages[i]));
        }
        ItemsAdapter adapter = new ItemsAdapter(this, items); //create the adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
