package com.example.homeworkroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.homeworkroom.databinding.ActivitySaveBinding;

import Database.Car;

public class SaveActivity extends AppCompatActivity {
    ActivitySaveBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.SaveName.getText().toString();
                String color = binding.SaveColor.getText().toString();
                String year = binding.SaveYear.getText().toString();

                Car car = new Car(name,color,Integer.parseInt(year));
                Intent intent = new Intent();
                intent.putExtra("CarKeySave",car);
                setResult(RESULT_OK,intent);

                binding.SaveName.setText(" ");
                binding.SaveColor.setText(" ");
                binding.SaveYear.setText(" ");
                Toast.makeText(SaveActivity.this, "Car Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}