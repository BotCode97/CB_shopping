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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterActivity extends AppCompatActivity {

    ListView filter_type,filter_section;
    ArrayList<String> filter_list = new ArrayList<>(Arrays.asList("Price"));
    ArrayList<String> selected = new ArrayList<>();
    ArrayList<String> one_section = new ArrayList<>();
    ArrayList<ArrayList<String>> section = new ArrayList<>();
    Button apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        filter_type = findViewById(R.id.filter_type);
        apply = findViewById(R.id.apply);
        filter_section = findViewById(R.id.section);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,filter_list);
        final SectionAdapter adapter1 = new SectionAdapter(getApplicationContext(),R.layout.section_list_layout,one_section);
        filter_type.setAdapter(adapter);
        filter_section.setAdapter(adapter1);
        section.add(new ArrayList<String>(Arrays.asList("200-400","400-600","600-800","800-1000","1000-2000","2000-3000")));
        filter_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                one_section.clear();
                one_section.addAll(section.get(i));
                adapter1.notifyDataSetChanged();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG",selected+"");
            }
        });

    }
    class SectionAdapter extends ArrayAdapter{
        ArrayList<String> l1;
        Context con;
        CheckBox ch1;
        public SectionAdapter(@NonNull Context context, int resource,ArrayList<String> l1) {
            super(context, resource);
            con = context;
            this.l1=l1;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                convertView = LayoutInflater.from(con).inflate(R.layout.section_list_layout,parent,false);
            }
            ch1 = convertView.findViewById(R.id.checkbox_section);
            ch1.setText(l1.get(position));
            ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        selected.add(l1.get(position));
                    }else{
                        selected.remove(l1.get(position));
                    }
                }
            });
            return convertView;
        }

        @Override
        public int getCount() {
            return l1.size();
        }
    }

    public ArrayList filterPrice(int start,int end){
        return null;
    }

}
