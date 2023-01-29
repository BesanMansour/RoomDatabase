package com.example.homeworkroom;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.homeworkroom.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import Database.Car;
import Database.CarViewModel;

public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;
    ArrayList<Car> carArrayList;
    CarAdapter carAdapter;
    static CarViewModel model;
    ActivityResultLauncher<Intent> arl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(CarViewModel.class);
        carArrayList = new ArrayList<>();

        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Car car = (Car) result.getData().getSerializableExtra("CarKeySave");
                            model.InsertCar(car);
                            carAdapter.notifyDataSetChanged();
                        }
                    }
                });
        // عرضت البيانات في الريسايكلر
        carAdapter = new CarAdapter(carArrayList, model);
        binding.mainRV.setAdapter(carAdapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this,
                RecyclerView.VERTICAL, false);
        binding.mainRV.setLayoutManager(lm);
        model.allCar().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars){
                carAdapter.setCar((ArrayList<Car>) cars);
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SaveActivity.class);
                arl.launch(intent);
            }
        });
    }
}