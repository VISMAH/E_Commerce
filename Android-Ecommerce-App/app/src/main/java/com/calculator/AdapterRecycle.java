package com.calculator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder>{

    ArrayList<Model> list;
    Context context;

    public AdapterRecycle(ArrayList<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Model model = list.get(position);

        Picasso.get().load(model.getProductImage()).placeholder(R.drawable.upload).into(holder.itemImage);

        holder.itemHeadLine.setText(model.getHeadline());
        holder.itemDescription.setText(model.getDescription());
        holder.itemPrice.setText(model.getPrice());
        holder.itemBrand.setText(model.getBrand());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleProductActivity.class);

                intent.putExtra("singleImage", model.getProductImage());
                intent.putExtra("singleHeadLine", model.getHeadline());
                intent.putExtra("singlePrice", model.getPrice());
                intent.putExtra("singleBrand", model.getBrand());
                intent.putExtra("singleProductType", model.getType());
                intent.putExtra("singleAboutProduct", model.getAbout());
                intent.putExtra("singleOrigin", model.getOrigin());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemHeadLine,  itemDescription, itemPrice, itemBrand;
        ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemHeadLine = itemView.findViewById(R.id.itemHeadLine);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemBrand = itemView.findViewById(R.id.itemBrand);

            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }
}
