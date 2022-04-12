package com.workclone.foodorderingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workclone.foodorderingapp.DetailActivity;
import com.workclone.foodorderingapp.R;
import com.workclone.foodorderingapp.models.MainModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder>{
    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_mainfood , parent , false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final MainModel model = list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("image" , model.getPic());
                intent.putExtra("name" , model.getName());
                intent.putExtra("price" , model.getPrice());
                intent.putExtra("description" , model.getDescription());
                intent.putExtra("type" , 1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name , price , description;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewsample);
            name = itemView.findViewById(R.id.textviewname);
            price = itemView.findViewById(R.id.textViewprice);
            description = itemView.findViewById(R.id.textViewdescription);
        }
    }
}
