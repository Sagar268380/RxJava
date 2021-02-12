package com.elysino.rxjava;

import android.app.NotificationManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elysino.rxjava.model.Food;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    Context context;
    List<Food> foodList;

    public DetailsAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    void setData(List<Food> foodList)
    {
        this.foodList=foodList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater=LayoutInflater.from(context).inflate(R.layout.each_row,parent,false);
        return new ViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Food food=foodList.get(position);
        holder.txtName.setText(food.getName());
        holder.txtPrice.setText(food.getPrice());
        Glide.with(context).load(food.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtName,txtPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            txtName=itemView.findViewById(R.id.name);
            txtPrice=itemView.findViewById(R.id.price);
        }
    }
}
