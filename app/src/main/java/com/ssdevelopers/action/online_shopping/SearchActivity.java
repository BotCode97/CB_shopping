package com.ssdevelopers.action.online_shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ListView search_list;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_list = findViewById(R.id.search_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);
        search_list.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
