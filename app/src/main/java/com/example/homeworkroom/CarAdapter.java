package com.example.homeworkroom;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkroom.databinding.CarItemBinding;

import java.util.ArrayList;

import Database.Car;
import Database.CarViewModel;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    ArrayList<Car> carArrayList;
    CarViewModel carViewModel;

    public CarAdapter(ArrayList<Car> carArrayList, CarViewModel carViewModel) {
        this.carArrayList = carArrayList;
        this.carViewModel = carViewModel;
    }

    public ArrayList<Car> getCar(){
        return carArrayList;
    }

    public void setCar(ArrayList<Car> carSet){
        this.carArrayList=carSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CarItemBinding binding = CarItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        int pos = position;

        final Car c = carArrayList.get(pos);
        holder.CarNameRv.setText(c.getCar_name());
        holder.CarColorRv.setText(c.getCar_color());
        holder.CarYearRv.setText(String.valueOf(c.getCar_year()));
        holder.CarCloseRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is to delete the record from room database
                MainActivity.model.deleteCar(c.getId());
                // this is to delete the record from Array list which is the recycler view data
                carArrayList.remove(pos);
                // update the fresh list of Array list data to recycler view
                notifyDataSetChanged();
            }
        });
        holder.CarPenRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.CarPenRv.getContext(),UpdateActivity.class);
                intent.putExtra("dataId",carArrayList.get(holder.getAdapterPosition()));
                holder.CarPenRv.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carArrayList.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder{
        TextView CarNameRv;
        TextView CarColorRv;
        TextView CarYearRv;
        ImageView CarCloseRv;
        ImageView CarPenRv;

        public CarViewHolder(CarItemBinding binding) {
            super(binding.getRoot());
            CarNameRv = binding.CarName;
            CarColorRv = binding.CarColor;
            CarYearRv = binding.CarYear;
            CarCloseRv = binding.CarImgClose;
            CarPenRv = binding.CarImgPen;
        }
    }
}
