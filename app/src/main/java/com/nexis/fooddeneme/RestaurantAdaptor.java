package com.nexis.fooddeneme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdaptor extends RecyclerView.Adapter<RestaurantAdaptor.Myholder> {
    private ArrayList<Restaurant> restaurants;
    buttonClickListener buttonClickListener;

    public RestaurantAdaptor(ArrayList<Restaurant> restaurants, buttonClickListener buttonClickListener) {
        this.restaurants=restaurants;
        this.buttonClickListener= buttonClickListener;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item,parent,false);

        return new Myholder(view, buttonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        holder.button.setText(restaurants.get(position).getYemek_adi());
        holder.resadi.setText(restaurants.get(position).getRestaurant_adi());
        holder.logo.setImageResource(restaurants.get(position).getLogo());


    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView resadi;
        buttonClickListener buttonClickListener;
        Button button;
        ImageView logo;

        public Myholder(@NonNull View itemView, buttonClickListener buttonClickListener) {
            super(itemView);
            logo = itemView.findViewById(R.id.image_logo);
            resadi = itemView.findViewById(R.id.txtresadi);
            button = itemView.findViewById(R.id.btnsiparisver);

            this.buttonClickListener= buttonClickListener;
            button.setOnClickListener(this);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (buttonClickListener != null) {
                buttonClickListener.onButtonClick(getAdapterPosition());
            }
            //buttonClickListener.onButtonClick(getAdapterPosition());

        }
    }

    public interface buttonClickListener{
        void onButtonClick(int position);
    }
}
