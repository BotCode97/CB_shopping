package com.ssdevelopers.action.online_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Orders extends AppCompatActivity {

    RecyclerView orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        orders = findViewById(R.id.order);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(orders.getContext(),
                manager.getOrientation());
        OrderAdapter orderAdapter = new OrderAdapter(getApplicationContext());
        orders.setAdapter(orderAdapter);
        orders.setLayoutManager(manager);
        orders.addItemDecoration(dividerItemDecoration);


    }
    class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Holder>{

        Context con;
        public OrderAdapter(Context context){
            con = context;
        }
        @NonNull
        @Override
        public OrderAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(con).inflate(R.layout.order,parent,false);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderAdapter.Holder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 2;
        }

        public class Holder extends RecyclerView.ViewHolder{

            public Holder(@NonNull View itemView) {
                super(itemView);
            }
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
