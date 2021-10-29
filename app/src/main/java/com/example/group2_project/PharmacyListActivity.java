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

public class PharmacyListActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener {
    private RecyclerView recyclerView;
    private AdapterPharmacies pharmaciesAdapter;
    private Button btnChooseThePlace;
    private TextView tvSelectedPlace;

    private Dialog dialog;
    private ArrayList<String> placesList;

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
        setItemsInRecyclerView();
    }

    public void setItemsInRecyclerView() {

        ArrayList<String> pharmaciesList = new ArrayList<>();

        pharmaciesAdapter = new AdapterPharmacies(pharmaciesList);
        recyclerView.setAdapter(pharmaciesAdapter);

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

        placesList = new ArrayList<>();
        placesList.add("Place 1");
        placesList.add("Place 2");
        placesList.add("Place 3");

        AdapterPlaceChoosing adapter = new AdapterPlaceChoosing(placesList, this);
        recyclerViewPlaces.setAdapter(adapter);

        dialog.show();
    }

    @Override
    public void itemClick(View view, int position) {
        Log.d("itemClick", "itemClick");
        dialog.dismiss();
        tvSelectedPlace.setText("Selected place : " + placesList.get(position));
    }

}
