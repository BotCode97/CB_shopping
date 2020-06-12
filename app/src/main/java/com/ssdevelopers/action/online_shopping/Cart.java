package com.ssdevelopers.action.online_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    RecyclerView cart;
    TextView price,total,total_price;
    LinearLayout price_desc;
    int tprice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cart = findViewById(R.id.items);
        price = findViewById(R.id.price);
        total = findViewById(R.id.total);
        total_price = findViewById(R.id.final_total);
        price_desc = findViewById(R.id.price_desc);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
      //  CartAdapter cartAdapter = new CartAdapter(getApplicationContext());
     //   cart.setAdapter(cartAdapter);
        cart.setLayoutManager(manager);
      /*
        ArrayList<User.Orders> product = Product_List.cart;
        for(int i=0;i<product.size();i++){
            tprice +=Integer.parseInt(product.get(i).price);
        }
        if (product.size()==0){
            price_desc.setVisibility(View.GONE);
        }else{
            price_desc.setVisibility(View.VISIBLE);
        }
        price.setText("₹ "+tprice);
        total.setText("₹ "+tprice);
        total_price.setText("₹ "+tprice);
*/
    }
/*
    class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder>{
        ArrayList<User.Orders> products = Product_List.cart;
        Context con;
        public CartAdapter(Context context){
            con = context;
        }
        @NonNull
        @Override
        public CartAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(con).inflate(R.layout.cart_item,parent,false);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CartAdapter.Holder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return products.size();
        }
        public class Holder extends RecyclerView.ViewHolder{

            public Holder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
    */

}
