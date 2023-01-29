package com.example.homeworkroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.homeworkroom.databinding.ActivityUpdateBinding;

import Database.Car;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        car = (Car) intent.getSerializableExtra("dataId");
        binding.UpdateName.setText(car.car_name);
        binding.UpdateColor.setText(car.car_color);
        binding.UpdateYear.setText(String.valueOf(car.car_year));

        binding.UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(),MainActivity.class);
                car.car_name = binding.UpdateName.getText().toString();
                car.car_color = binding.UpdateColor.getText().toString();
                car.car_year = Integer.valueOf(binding.UpdateYear.getText().toString());
                MainActivity.model.UpdateCar(car);
                startActivity(intent1);
                Toast.makeText(UpdateActivity.this, "Car Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}