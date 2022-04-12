package com.workclone.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.workclone.foodorderingapp.adapters.MainAdapter;
import com.workclone.foodorderingapp.adapters.OrderAdapter;
import com.workclone.foodorderingapp.databinding.ActivityMainBinding;
import com.workclone.foodorderingapp.models.MainModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger , "Burger" , "2" ,"Burger is a junk food."));
        list.add(new MainModel(R.drawable.pizza , "Pizza" , "5" ,"Pizza is cheesy."));

        MainAdapter adapter = new MainAdapter(list , this);
        binding.recyclerviewmain.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerviewmain.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menushopicon, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.ordersShop:
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}