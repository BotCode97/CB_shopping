package com.ssdevelopers.action.online_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    Toolbar bar;
    RecyclerView wc,hp,bs,td,utpo,sn,gfpo,aunn,lfs,gd,nl,uonn;
    DrawerLayout layout;
    NavigationView view;
    TextView ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        view = findViewById(R.id.nv);
        layout = findViewById(R.id.drawer);
        ed = findViewById(R.id.search);
        bar = findViewById(R.id.home_toolbar);
        setSupportActionBar(bar);
        wc = findViewById(R.id.trend);
        hp = findViewById(R.id.Category);
        bs = findViewById(R.id.bst_slr);
        td = findViewById(R.id.td);
        utpo = findViewById(R.id.discount);
        sn = findViewById(R.id.shp_now);
        gfpo = findViewById(R.id.high_dsc);
        aunn = findViewById(R.id.under_nn);
        lfs = findViewById(R.id.lfs);
        gd = findViewById(R.id.gd);
        nl = findViewById(R.id.nl);
        uonn = findViewById(R.id.under_onn);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager1 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager4 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager5 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager6 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager7 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager8 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager9 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager10 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager11 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager manager12 = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);


        TrendingAdapter adapter = new TrendingAdapter(getApplicationContext());

        wc.setAdapter(adapter);
        hp.setAdapter(adapter);
        bs.setAdapter(adapter);
        td.setAdapter(adapter);
        utpo.setAdapter(adapter);
        sn.setAdapter(adapter);
        gfpo.setAdapter(adapter);
        aunn.setAdapter(adapter);
        lfs.setAdapter(adapter);
        gd.setAdapter(adapter);
        nl.setAdapter(adapter);
        uonn.setAdapter(adapter);
        wc.setLayoutManager(manager);
        hp.setLayoutManager(manager1);
        bs.setLayoutManager(manager2);
        td.setLayoutManager(manager4);
        utpo.setLayoutManager(manager5);
        sn.setLayoutManager(manager6);
        gfpo.setLayoutManager(manager7);
        aunn.setLayoutManager(manager8);
        lfs.setLayoutManager(manager9);
        gd.setLayoutManager(manager10);
        nl.setLayoutManager(manager11);
        uonn.setLayoutManager(manager12);

        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.orders_option:
                        startActivity(new Intent(Home.this,Orders.class));
                        break;
                    case R.id.option_account:
                        startActivity(new Intent(Home.this,Profile.class));
                        break;
                    case R.id.shop_by_cat:
                        startActivity(new Intent(Home.this,CategoryActivity.class));
                        break;
                }
                return true;
            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,SearchActivity.class));

            }
        });


    }


    class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.VH>{
        ArrayList<Integer> res = new ArrayList<>();

        Context con;
        public TrendingAdapter(Context context){
            con = context;
            res.add(R.drawable.mens);
            res.add(R.drawable.women);
            res.add(R.drawable.women_2);

        }
        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(con).inflate(R.layout.trending_layout,parent,false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.view.setImageResource(res.get(position));
            holder.collection_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Home.this,ItemPage.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        public class VH extends RecyclerView.ViewHolder {
            ImageView view;
            View collection_button;

            public VH(@NonNull View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.cat_shop);
                collection_button = itemView.findViewById(R.id.trend_view);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.cart:
                startActivity(new Intent(Home.this,Cart.class));
                break;
            default:
                break;

        }
        return true;
    }

    public void Open(View view){
        layout.openDrawer(Gravity.LEFT);
    }
    public void Close(View view){
        layout.openDrawer(Gravity.LEFT);
    }

}
