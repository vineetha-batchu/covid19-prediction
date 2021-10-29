package com.example.group2_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterPharmacies extends RecyclerView.Adapter<AdapterPharmacies.MyViewHolder> {

    private ArrayList<String> pharmaciesList;
    private TextView message, time;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pharmacy_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        message.setText(pharmaciesList.get(i));
    }

    @Override
    public int getItemCount() {
        return pharmaciesList != null ? pharmaciesList.size() : 0;
    }

    public void setPharmaciesList(ArrayList<String> pharmacies) {
        this.pharmaciesList = pharmacies;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.tv_pharmacy_item);
        }
    }

}
