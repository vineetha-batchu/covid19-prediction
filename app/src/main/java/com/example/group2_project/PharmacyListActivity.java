package com.example.group2_project;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PharmacyListActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener {
    private RecyclerView recyclerView;
    private AdapterPharmacies pharmaciesAdapter;
    private Button btnChooseThePlace;
    private TextView tvSelectedPlace;

    private Dialog dialog;
    HashMap pharmaciesMap = new HashMap<String, ArrayList<String>>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_list);

        btnChooseThePlace = findViewById(R.id.btn_choose_place);
        tvSelectedPlace = findViewById(R.id.tv_selected_place);
        btnChooseThePlace.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PharmacyListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        pharmaciesAdapter = new AdapterPharmacies();
        pharmaciesAdapter.setPharmaciesList(new ArrayList<String>());
        recyclerView.setAdapter(pharmaciesAdapter);
        makePharmaciesMap();
    }

    @Override
    public void onClick(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_choose_place);
        Window window = dialog.getWindow();
        window.setLayout(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerViewPlaces = dialog.findViewById(R.id.recyclerView_places);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(dialog.getContext());
        recyclerViewPlaces.setLayoutManager(linearLayoutManager);

        AdapterPlaceChoosing adapter = new AdapterPlaceChoosing(getPlacesList(), this);
        recyclerViewPlaces.setAdapter(adapter);

        dialog.show();
    }

    @Override
    public void itemClick(View view, int position) {
        Log.d("itemClick", "itemClick");
        dialog.dismiss();
        tvSelectedPlace.setText("Selected place : " + getPlacesList().get(position));

        setPharmaciesItemsInRecyclerView(position);
    }

    private ArrayList<String> getPlacesList() {
        ArrayList<String> placesList = new ArrayList<>();
        placesList.add("Place 1");
        placesList.add("Place 2");
        placesList.add("Place 3");

        return placesList;
    }

    public void setPharmaciesItemsInRecyclerView(int position) {
        ArrayList<String> pharmacies = (ArrayList<String>) pharmaciesMap.get(getPlacesList().get(position));
        pharmaciesAdapter.setPharmaciesList(pharmacies);
        pharmaciesAdapter.notifyDataSetChanged();
    }

    private void makePharmaciesMap() {
        ArrayList<String> pharmaciesPlace1 = new ArrayList<>();
        pharmaciesPlace1.add("phaermacy - 1");
        pharmaciesPlace1.add("phaermacy - 2");
        pharmaciesPlace1.add("phaermacy - 3");
        pharmaciesMap.put("Place 1", pharmaciesPlace1);
    }
}
