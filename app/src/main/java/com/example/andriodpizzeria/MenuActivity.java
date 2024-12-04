package com.example.andriodpizzeria;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.chicagodeluxe, R.drawable.chicagobbq, R.drawable.chicagomeat,
            R.drawable.chicagobyo, R.drawable.nydeluxe, R.drawable.nybbq, R.drawable.nymeat,
            R.drawable.nybyo};

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
