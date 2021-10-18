package com.example.group2_project;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PharmacyListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterPlaces adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_list);


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PharmacyListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        setItemsInRecyclerView();
    }

    public void setItemsInRecyclerView() {

        ArrayList<String> places = new ArrayList<>();
        places.add("Walmart");
        places.add("Hyvee");
        places.add("CVS");

        adapter = new AdapterPlaces(places);
        recyclerView.setAdapter(adapter);

    }
}
