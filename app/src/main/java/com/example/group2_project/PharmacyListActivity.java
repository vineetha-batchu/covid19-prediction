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
        placesList.add("Corning,IA");
        placesList.add("Brownville, NE");
        placesList.add("Clearfield, IA");
        placesList.add("Elwood, KS");
        placesList.add("Saint Joseph, MO");
        placesList.add("Union Star, MO");
        placesList.add("Rock Port, MO");
        placesList.add("Oregon, MO");
        placesList.add("Denver, MO");
        placesList.add("Blockton, IA");
        placesList.add("Craig, MO");
        placesList.add("Mound City, MO");
        placesList.add("Gentry, MO");
        placesList.add("Savannah, MO");
        placesList.add("Fillmore, MO");


        return placesList;
    }

    public void setPharmaciesItemsInRecyclerView(int position) {
        ArrayList<String> pharmacies = (ArrayList<String>) pharmaciesMap.get(getPlacesList().get(position));
        pharmaciesAdapter.setPharmaciesList(pharmacies);
        pharmaciesAdapter.notifyDataSetChanged();
    }

    private void makePharmaciesMap() {
        ArrayList<String> pharmaciesPlace1 = new ArrayList<>();
        pharmaciesPlace1.add("Hy-Vee");
        ArrayList<String> pharmaciesPlace2 = new ArrayList<>();
        pharmaciesPlace2.add("Walgreens");
        pharmaciesPlace2.add("Family Value");
        pharmaciesPlace2.add("RebekahE.Gebhards");
        pharmaciesPlace2.add("Stoner Drug");
        pharmaciesPlace2.add("Code's Usave");

        ArrayList<String> pharmaciesPlace3 = new ArrayList<>();
        pharmaciesPlace3.add("NuCara");
        pharmaciesPlace3.add("Antiques");
        pharmaciesPlace3.add("Hy-Vee");


        ArrayList<String> pharmaciesPlace4 = new ArrayList<>();
        pharmaciesPlace4.add("Walgreens");
        pharmaciesPlace4.add("Walmart");
        pharmaciesPlace4.add("Stevenson Family");
        pharmaciesPlace4.add("NorthEnd Family");
        pharmaciesPlace4.add("Genoa Healthcare");

        ArrayList<String> pharmaciesPlace5 = new ArrayList<>();
        pharmaciesPlace5.add("Rogers");
        pharmaciesPlace5.add("CVS");
        pharmaciesPlace5.add("Stevenson Family");
        pharmaciesPlace5.add("Walmart");
        pharmaciesPlace5.add("NorthEnd Family");

        ArrayList<String> pharmaciesPlace6 = new ArrayList<>();
        pharmaciesPlace6.add("Rogers");
        pharmaciesPlace6.add("CVS");
        pharmaciesPlace6.add("Stevenson Family");
        pharmaciesPlace6.add("Walmart");
        pharmaciesPlace6.add("NorthEnd Family");

        ArrayList<String> pharmaciesPlace7 = new ArrayList<>();
        pharmaciesPlace7.add("Walgreens");
        pharmaciesPlace7.add("Stoner Drug Co");
        pharmaciesPlace7.add("Christopher J.Roup PharmsD");

        ArrayList<String> pharmaciesPlace8 = new ArrayList<>();
        pharmaciesPlace8.add("Walgreens");
        pharmaciesPlace8.add("Rogers");
        pharmaciesPlace8.add("Genoa");
        pharmaciesPlace8.add("Rogers");
        pharmaciesPlace8.add("Medicine Center");

        ArrayList<String> pharmaciesPlace9 = new ArrayList<>();
        pharmaciesPlace9.add("Hy-Vee");
        pharmaciesPlace9.add("Family Health");
        pharmaciesPlace9.add("Medicine Center");
        pharmaciesPlace9.add("Central Admixture");

        ArrayList<String> pharmaciesPlace10 = new ArrayList<>();
        pharmaciesPlace10.add("Hy-Vee");
        pharmaciesPlace10.add("Bedford");
        pharmaciesPlace10.add("Decatur Family");
        pharmaciesPlace10.add("Clinic");
        pharmaciesPlace10.add("Medicine Associates");

        ArrayList<String> pharmaciesPlace11 = new ArrayList<>();
        pharmaciesPlace11.add("Walgreens");
        pharmaciesPlace11.add("Rogers");
        pharmaciesPlace11.add("Craig Country");
        pharmaciesPlace11.add("Stoner Drug");
        pharmaciesPlace11.add("Holly M");
        ArrayList<String> pharmaciesPlace12 = new ArrayList<>();
        pharmaciesPlace12.add("Rogers");
        pharmaciesPlace12.add("Walgreens");

        ArrayList<String> pharmaciesPlace13 = new ArrayList<>();
        pharmaciesPlace13.add("Hy-Vee");
        pharmaciesPlace13.add("Walmart");
        pharmaciesPlace13.add("Stanberry");
        pharmaciesPlace13.add("Rogers");
        pharmaciesPlace13.add("Sherri's");

        ArrayList<String> pharmaciesPlace14 = new ArrayList<>();
        pharmaciesPlace14.add("Countryside");
        pharmaciesPlace14.add("Walgreens");
        pharmaciesPlace14.add("Savannah Family");
        pharmaciesPlace14.add("Countryside");

        ArrayList<String> pharmaciesPlace15 = new ArrayList<>();
        pharmaciesPlace15.add("Walgreens");
        pharmaciesPlace15.add("Hy-Vee");
        pharmaciesPlace15.add("Brothers Market");
        pharmaciesPlace15.add("Savannah");
        pharmaciesPlace15.add("Albers Medical");


        pharmaciesMap.put("Corning,IA", pharmaciesPlace1);
        pharmaciesMap.put("Brownville, NE", pharmaciesPlace2);
        pharmaciesMap.put("Clearfield, IA", pharmaciesPlace3);
        pharmaciesMap.put("Elwood, KS", pharmaciesPlace4);
        pharmaciesMap.put("Saint Joseph, MO", pharmaciesPlace5);
        pharmaciesMap.put("Union Star, MO",pharmaciesPlace6);
        pharmaciesMap.put("Rock Port, MO",pharmaciesPlace7);
        pharmaciesMap.put("Oregon, MO",pharmaciesPlace8);
        pharmaciesMap.put("Denver, MO",pharmaciesPlace9);
        pharmaciesMap.put("Blockton, IA",pharmaciesPlace10);
        pharmaciesMap.put("Craig, MO",pharmaciesPlace11);
        pharmaciesMap.put("Mound City, MO",pharmaciesPlace12);
        pharmaciesMap.put("Gentry, MO",pharmaciesPlace13);
        pharmaciesMap.put("Savannah, MO",pharmaciesPlace14);
        pharmaciesMap.put("Fillmore, MO",pharmaciesPlace15);



    }
}
