package com.ssdevelopers.action.online_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VendorActivity extends AppCompatActivity {

    RecyclerView vendor_order;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor2);
        vendor_order=  findViewById(R.id.vendor_orders);
        fab = findViewById(R.id.add);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        Vendor_Cart vc = new Vendor_Cart();
        vendor_order.setAdapter(vc);
        vendor_order.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VendorActivity.this,UploadActivity.class));
            }
        });
        Product_List pro = new Product_List();
        Toast.makeText(getApplicationContext(),+pro.getSize()+"",Toast.LENGTH_LONG).show();
    }
    class Vendor_Cart extends RecyclerView.Adapter<Vendor_Cart.VH>{

        @NonNull
        @Override
        public Vendor_Cart.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_cart,parent,false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Vendor_Cart.VH holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 1;
        }

        public class VH extends RecyclerView.ViewHolder{

            public VH(@NonNull View itemView) {
                super(itemView);
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VendorActivity.this,MainActivity.class));
        finish();
    }
}
