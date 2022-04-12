package com.workclone.foodorderingapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workclone.foodorderingapp.DBHelper;
import com.workclone.foodorderingapp.DetailActivity;
import com.workclone.foodorderingapp.R;
import com.workclone.foodorderingapp.models.OrderModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder>{
ArrayList<OrderModel> listOrder;
Context context;

    public OrderAdapter(ArrayList<OrderModel> listOrder, Context context) {
        this.listOrder = listOrder;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler_order,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        OrderModel model = listOrder.get(position);
        holder.imageOrder.setImageResource(model.getOrderPic());
        holder.orderTvName.setText(model.getOrderName());
        holder.orderTvPrice.setText(model.getOrderPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("id" ,Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type" , 2);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context).setTitle("Delete Order")
                        .setIcon(R.drawable.delete)
                        .setMessage("Delete the order?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper dbHelper = new DBHelper(context);
                                if(dbHelper.deleteOrder(Integer.parseInt(model.getOrderNumber()))>0){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(context, "Error in Deleting", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }



    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imageOrder;
        TextView orderTvName , orderTvPrice;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageOrder = itemView.findViewById(R.id.textvieworderimage);
            orderTvName = itemView.findViewById(R.id.textViewordername);
            orderTvPrice = itemView.findViewById(R.id.textvieworderprice);
        }
    }

}
