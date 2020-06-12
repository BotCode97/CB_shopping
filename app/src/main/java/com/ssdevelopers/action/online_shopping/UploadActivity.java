package com.ssdevelopers.action.online_shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumDialog;

import java.util.ArrayList;
import java.util.Arrays;

public class UploadActivity extends AppCompatActivity {

    EditText name,price;
    ImageView add_color;
    TextView size_s,size_m,size_l,size_xl,size_xxl,upload_img;
    ArrayList<Integer> colors_list = new ArrayList<>();
    ArrayList<String> size = new ArrayList<>(Arrays.asList("S","M","L","XL","XXL"));
    ArrayList<Uri> images = new ArrayList<>();
    Button upload;
    int img ;
    RecyclerView color_upload;
    boolean[] size_val = {false,false,false,false,false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        name = findViewById(R.id.pro_name);
        price = findViewById(R.id.pro_price);
        size_s = findViewById(R.id.size_s);
        size_m = findViewById(R.id.size_m);
        size_l = findViewById(R.id.size_l);
        size_xl = findViewById(R.id.size_xl);
        size_xxl = findViewById(R.id.size_xxl);
        add_color = findViewById(R.id.add_color);
        upload = findViewById(R.id.upload_item);
        color_upload = findViewById(R.id.colour_upload);
        upload_img = findViewById(R.id.upload_product_img);
        img = R.drawable.mens;
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        final Colors colors = new Colors(getApplicationContext(),colors_list);
        color_upload.setAdapter(colors);
        color_upload.setLayoutManager(manager);
        size_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size_val[0]){
                    size_s.setBackground(getResources().getDrawable(R.drawable.round,null));
                    size_s.setTextColor(Color.parseColor("#000000"));
                    size_val[0] = false;
                }else{
                    size_s.setBackground(getResources().getDrawable(R.drawable.round_clicked,null));
                    size_s.setTextColor(Color.parseColor("#FFFFFF"));
                    size_val[0] = true;
                }
            }
        });
        size_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size_val[1]){
                    size_m.setBackground(getResources().getDrawable(R.drawable.round,null));
                    size_m.setTextColor(Color.parseColor("#000000"));
                    size_val[1] = false;
                }else{
                    size_m.setBackground(getResources().getDrawable(R.drawable.round_clicked,null));
                    size_m.setTextColor(Color.parseColor("#FFFFFF"));
                    size_val[1] = true;
                }
            }
        });
        size_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size_val[2]){
                    size_l.setBackground(getResources().getDrawable(R.drawable.round,null));
                    size_l.setTextColor(Color.parseColor("#000000"));
                    size_val[2] = false;
                }else{
                    size_l.setBackground(getResources().getDrawable(R.drawable.round_clicked,null));
                    size_l.setTextColor(Color.parseColor("#FFFFFF"));
                    size_val[2] = true;
                }
            }
        });
        size_xl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size_val[3]){
                    size_xl.setBackground(getResources().getDrawable(R.drawable.round,null));
                    size_xl.setTextColor(Color.parseColor("#000000"));
                    size_val[3] = false;
                }else{
                    size_xl.setBackground(getResources().getDrawable(R.drawable.round_clicked,null));
                    size_xl.setTextColor(Color.parseColor("#FFFFFF"));
                    size_val[3] = true;
                }
            }
        });
        size_xxl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(size_val[4]){
                    size_xxl.setBackground(getResources().getDrawable(R.drawable.round,null));
                    size_xxl.setTextColor(Color.parseColor("#000000"));
                    size_val[4] = false;
                }else{
                    size_xxl.setBackground(getResources().getDrawable(R.drawable.round_clicked,null));
                    size_xxl.setTextColor(Color.parseColor("#FFFFFF"));
                    size_val[4] = true;
                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<size.size();i++){
                    if(size_val[i]==false){
                        size.remove(i);
                    }
                }

                    Product product_item = new Product();
                    product_item.setImg(images);
                    product_item.setName(name.getText().toString());
                    product_item.setPrice(price.getText().toString());
                    product_item.setSize(size);
                    product_item.setColor(colors_list);
                    Product_List pro = new Product_List();
                    pro.add(product_item);
                    Toast.makeText(getApplicationContext(), pro.getSize() + "", Toast.LENGTH_LONG).show();
                    finish();
                }
        });
        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });
        add_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpectrumDialog.Builder builder = new SpectrumDialog.Builder(UploadActivity.this);
                builder.setColors(R.array.color_array);
                builder.setSelectedColor(getResources().getColor(R.color.selected,null));
                builder.setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(boolean positiveResult, int color) {
                            colors_list.add(color);
                            colors.notifyDataSetChanged();
                    }
                });
                builder.build().show(getSupportFragmentManager(),"tag");
            }
        });

    }




    class Colors extends RecyclerView.Adapter<Colors.VH>{
        ArrayList<Integer> color;
        Context con;
        public Colors(Context context, ArrayList<Integer> color){
            this.color = color;
            con = context;
        }

        @NonNull
        @Override
        public Colors.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View v = LayoutInflater.from(con).inflate(R.layout.colour,parent,false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Colors.VH holder, int position) {
        holder.cv.setCardBackgroundColor(color.get(position));
        }

        @Override
        public int getItemCount() {
            return color.size();
        }
        public class VH extends RecyclerView.ViewHolder{
            CardView cv;
            public VH(@NonNull View itemView) {
                super(itemView);
                cv = itemView.findViewById(R.id.color_circle);
            }
        }
    }
    private void pickFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode ==RESULT_OK){
            if(data.getClipData()!=null){
                int count = data.getClipData().getItemCount();
                for(int i=0;i<count;i++){
                    images.add(data.getClipData().getItemAt(i).getUri());
                }
            }else if(data.getData()!=null){
                images.add(data.getData());
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
