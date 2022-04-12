package com.workclone.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.workclone.foodorderingapp.adapters.OrderAdapter;
import com.workclone.foodorderingapp.databinding.ActivityOrderBinding;
import com.workclone.foodorderingapp.models.OrderModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
  ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBHelper dbHelper = new DBHelper(this);
        ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();
        orderModelArrayList = dbHelper.getOrders();
        OrderAdapter orderAdapter = new OrderAdapter(orderModelArrayList , this);
        binding.recyclervieworder.setAdapter(orderAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclervieworder.setLayoutManager(layoutManager);
    }
}