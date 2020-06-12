package com.ssdevelopers.action.online_shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    ListView categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        categories = findViewById(R.id.categories);
       // Log.e("TAG",getResources().getStringArray(R.array.categories).toString());
        CatAdapter adapter = new CatAdapter(getApplicationContext(),R.layout.category_list,getResources().getStringArray(R.array.categories));
        categories.setAdapter(adapter);
    }
    class CatAdapter extends ArrayAdapter{
        Context con;
        String[] arr;
        TextView cat_name;

        public CatAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
            super(context, resource, objects);
            con = context;
            arr = (String[] )objects;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView==null){
                convertView = LayoutInflater.from(con).inflate(R.layout.category_list,parent,false);
            }
            cat_name = convertView.findViewById(R.id.cat_name);
            cat_name.setText(arr[position]);
            return convertView;
        }

        @Override
        public int getCount() {
            return arr.length;
        }
    }
}
