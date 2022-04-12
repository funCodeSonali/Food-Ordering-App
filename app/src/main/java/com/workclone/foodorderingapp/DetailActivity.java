package com.workclone.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.workclone.foodorderingapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final DBHelper dbHelper = new DBHelper(this);
    if(getIntent().getIntExtra("type" , 0)==1){
        final int pic = getIntent().getIntExtra("image", 0);
        final String name = getIntent().getStringExtra("name");
        final int price = Integer.parseInt(getIntent().getStringExtra("price"));
        final String description = getIntent().getStringExtra("description");

        binding.textviewdetailquantity.setText("1");
        binding.imageViewfooddetail.setImageResource(pic);
        binding.textviewdetailname.setText(name);
        binding.textviewdetaildescription.setText(description);
        binding.textviewdetailprice.setText(String.format("%d", price));

        binding.imageviewdetailadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int add = Integer.parseInt(binding.textviewdetailquantity.getText().toString());
                add++;
                binding.textviewdetailquantity.setText(""+add);
            }
        });
        binding.imageviewdetailminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int minus = Integer.parseInt(binding.textviewdetailquantity.getText().toString());
                minus--;
                if(minus>0){
                    binding.textviewdetailquantity.setText(""+minus);
                }
            }
        });
        binding.buttondetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(binding.textviewdetailquantity.getText().toString());
                boolean isInserted = dbHelper.insertOrder(binding.editTextTextPersonName.getText().toString(),
                        binding.editTextPhone.getText().toString(),
                        price, pic, description, quantity, name
                );
                if (isInserted) {
                    Toast.makeText(DetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
      else{
          int id = getIntent().getIntExtra("id" ,0);
          Cursor cursor = dbHelper.getOrderById(id);
          int image = cursor.getInt(4);
        binding.imageViewfooddetail.setImageResource(image);
        binding.textviewdetailname.setText(cursor.getString(7));
        binding.textviewdetailprice.setText(cursor.getInt(3)+"");
        binding.textviewdetaildescription.setText(cursor.getString(5));
        binding.editTextTextPersonName.setText(cursor.getString(1));
        binding.editTextPhone.setText(cursor.getString(2));
        binding.textviewdetailquantity.setText(cursor.getInt(6)+"");
        binding.imageviewdetailadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int add = Integer.parseInt(binding.textviewdetailquantity.getText().toString());
                add++;
                binding.textviewdetailquantity.setText(""+add);
            }
        });
        binding.imageviewdetailminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int minus = Integer.parseInt(binding.textviewdetailquantity.getText().toString());
                minus--;
                if(minus>0){
                    binding.textviewdetailquantity.setText(""+minus);
                }
            }
        });
        String text = "Update Now";
        binding.buttondetail.setText(text);
        binding.buttondetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = dbHelper.updateOrder(binding.editTextTextPersonName.getText().toString(),
                        binding.editTextPhone.getText().toString(),
                        Integer.parseInt(binding.textviewdetailprice.getText().toString()) ,
                        image , binding.textviewdetaildescription.getText().toString() ,
                        Integer.parseInt(binding.textviewdetailquantity.getText().toString()),
                        binding.textviewdetailname.getText().toString(), id);
                if (isUpdated){
                    Toast.makeText(DetailActivity.this , "Updated" , Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DetailActivity.this , "Error in Updating" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
}