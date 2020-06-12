package com.ssdevelopers.action.online_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Item_list extends AppCompatActivity {

    RecyclerView items;
    Button sort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        items = findViewById(R.id.items);
        sort = findViewById(R.id.sort);
        final ArrayList<Product> pro = Product_List.products;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),2,RecyclerView.VERTICAL,false);
        final itemAdapter adapter = new itemAdapter(getApplicationContext(),pro);
        items.setLayoutManager(manager);
        items.setAdapter(adapter);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Item_list.this);
                builder.setTitle("Sort by:");
                String[] sort_item = {"High to Low","Low to High","Popularity","Newest"};
                builder.setItems(sort_item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                //Sorting High to Low
                                for(int j=0;j<pro.size();j++){
                                    for(int k=0;k<pro.size()-j-1;k++){
                                        if(Integer.parseInt(pro.get(k).price)<Integer.parseInt(pro.get(k+1).price)){
                                            Product p = pro.get(k);
                                            pro.set(k,pro.get(k+1));
                                            pro.set(k+1,p);
                                        }
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                break;
                            case 1:
                                // Sorting Low to High
                                for(int j=0;j<pro.size();j++){
                                    for(int k=0;k<pro.size()-j-1;k++){
                                        if(Integer.parseInt(pro.get(k).price)>Integer.parseInt(pro.get(k+1).price)){
                                            Product p = pro.get(k);
                                            pro.set(k,pro.get(k+1));
                                            pro.set(k+1,p);
                                        }
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }
    class itemAdapter extends RecyclerView.Adapter<itemAdapter.Holder>{

        ArrayList<Product> product ;

        Context com;
        public itemAdapter(Context con,ArrayList<Product> pro){
            com = con;
            product = pro;
        }
        @NonNull
        @Override
        public itemAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(com).inflate(R.layout.product_item,parent,false);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull itemAdapter.Holder holder, final int position) {
            if(product.get(position).img.size()>0) {
                holder.product_img.setImageURI(product.get(position).img.get(0));
            }else{
                holder.product_img.setImageResource(R.drawable.mens);
            }
            holder.product_name.setText(product.get(position).name);
            holder.product_price.setText(product.get(position).price);
            holder.product_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Product pro = product.get(position);
                    startActivity(new Intent(Item_list.this,ItemPage.class).putExtra("pro",pro));
                }
            });
        }

        @Override
        public int getItemCount() {
            return Product_List.products.size();
        }

        public class Holder extends RecyclerView.ViewHolder{
            ImageView product_img;
            TextView product_name,product_price;
            public Holder(@NonNull View itemView) {
                super(itemView);
                product_img = itemView.findViewById(R.id.product);
                product_name = itemView.findViewById(R.id.product_name);
                product_price = itemView.findViewById(R.id.product_price);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
