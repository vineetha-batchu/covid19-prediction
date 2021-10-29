package com.example.group2_project;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPlaceChoosing extends RecyclerView.Adapter<AdapterPlaceChoosing.MyViewHolder> {
    private ItemClickListener onItemClickListener;
    private List<String> placesList;

    private TextView message, time;

    public AdapterPlaceChoosing(List<String> placesList, ItemClickListener onItemClickListener) {
        this.placesList = placesList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AdapterPlaceChoosing.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.places_list_item, viewGroup, false);
        return new AdapterPlaceChoosing.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPlaceChoosing.MyViewHolder myViewHolder, int i) {
        message.setText(placesList.get(i));
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.itemClick(itemView, getAdapterPosition());
                }
            });

            message = itemView.findViewById(R.id.tv_place_item);
        }
    }
}
