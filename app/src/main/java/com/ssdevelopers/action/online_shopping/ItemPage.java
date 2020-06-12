package com.ssdevelopers.action.online_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemPage extends AppCompatActivity {

    RecyclerView carousel,color,size;
    TextView name,price;
    Button add_to_cart;
    int qty=1;
    String size_item="";
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

         final Product pro = (Product) getIntent().getSerializableExtra("pro");

        carousel = findViewById(R.id.carosel);
        color= findViewById(R.id.colour);
        size = findViewById(R.id.size);
        name = findViewById(R.id.name);
        price = findViewById(R.id.id);
        add_to_cart = findViewById(R.id.add_to_cart);
        img = findViewById(R.id.arrow_back);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager1 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        SizeAdapter sizeAdapter = new SizeAdapter(getApplicationContext(),pro.size);
        ColourAdapter colourAdapter = new ColourAdapter(getApplicationContext(),pro.color);
        CarouselAdapter adapter  = new CarouselAdapter(getApplicationContext());
        carousel.setAdapter(adapter);
        carousel.setLayoutManager(manager);
        color.setAdapter(colourAdapter);
        color.setLayoutManager(manager1);
        size.setLayoutManager(manager2);
        size.setAdapter(sizeAdapter);
        name.setText(pro.name);
        price.setText(pro.price);
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Product_List.cart.add(new User.Orders(pro.name,pro.img.get(0),pro.price,pro.color.get(0),size_item,qty));
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.Holder>{
        ArrayList<String> res;
        int currentpos = -1;


        Context com;
        SizeAdapter(Context context,ArrayList<String> res){
            com = context;
           this.res=res;

        }
        @NonNull
        @Override
        public SizeAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(com).inflate(R.layout.size_layout,parent,false);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull final SizeAdapter.Holder holder, final int position) {
            holder.tv.setText(res.get(position));
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentpos = position;
                    size_item = res.get(position);
                    notifyDataSetChanged();
                }
            });
            Log.e("pos",position+"");

            if(currentpos == position){
                holder.tv.setBackground(getResources().getDrawable(R.drawable.round_clicked,null));
                holder.tv.setTextColor(getResources().getColor(R.color.white,null));

            }else{
                holder.tv.setBackground(getResources().getDrawable(R.drawable.round,null));
                holder.tv.setTextColor(getResources().getColor(R.color.blackTextColor,null));

            }


        }

        @Override
        public int getItemCount() {
            return res.size();
        }
        public class Holder extends RecyclerView.ViewHolder{

            TextView tv;
            public Holder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.size_id);
            }
        }
    }

    class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.Holder>{
        ArrayList<Integer> res = new ArrayList<>();

        Context com;
        CarouselAdapter(Context context){
            com = context;
            res.add(R.drawable.mens);
            res.add(R.drawable.french_style);
            res.add(R.drawable.mini);
        }
        @NonNull
        @Override
        public CarouselAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(com).inflate(R.layout.carosel,parent,false);
            return new CarouselAdapter.Holder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CarouselAdapter.Holder holder, int position) {
                holder.view.setImageResource(res.get(position));
            }

        @Override
        public int getItemCount() {
            return res.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            ImageView view;
            public Holder(@NonNull View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.carousel_item);

            }
        }
    }
    class ColourAdapter extends RecyclerView.Adapter<ColourAdapter.Holder>{
        ArrayList<Integer> res ;

        Context com;
        ColourAdapter(Context context,ArrayList<Integer> res){

            com = context;
            this.res = res;
        }
        @NonNull
        @Override
        public ColourAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(com).inflate(R.layout.colour,parent,false);
            return new ColourAdapter.Holder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ColourAdapter.Holder holder, int position) {
           holder.cv.setCardBackgroundColor(res.get(position));
        }

        @Override
        public int getItemCount() {
            return res.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
        CardView cv;
            public Holder(@NonNull View itemView) {
                super(itemView);
                cv = itemView.findViewById(R.id.color_circle);

            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
