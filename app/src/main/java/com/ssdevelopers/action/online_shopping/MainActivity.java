package com.ssdevelopers.action.online_shopping;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1,bt2,login;
    RelativeLayout rl1,act1;
    TextView tv1,register;
    int height = 0;
    int move = 0;
    boolean up = false;
    String type = "Customer";
    EditText email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt1 = findViewById(R.id.owner);
        bt2 = findViewById(R.id.vendor);
        rl1 = findViewById(R.id.rl1);
        login = findViewById(R.id.login_in);
        tv1 = findViewById(R.id.name);
        register = findViewById(R.id.register);
        act1 = findViewById(R.id.act_1);
        email = findViewById(R.id.email_login);
        pass = findViewById(R.id.pass_login);
        //Toast.makeText(getApplicationContext(), Product_List.user.size()+"", Toast.LENGTH_LONG).show();
        height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        final ValueAnimator animator = ValueAnimator.ofInt(0,height-500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (int) valueAnimator.getAnimatedValue();
                rl1.getLayoutParams().height = val;
                rl1.requestLayout();
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Customer";
                int val = tv1.getTop()*-1;
                tv1.animate().translationY(val+16);
                animator.start();
                up = true;
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Vendor";
                int val = tv1.getTop()*-1;
                tv1.animate().translationY(val+16);
                move = val+16;
                animator.start();
                up = true;
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Signin.class).putExtra("Type",type));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<Product_List.user.size();i++){
                    User us = Product_List.user.get(i);
                    if(us.email.equals(email.getText().toString())){
                        if(us.password.equals(pass.getText().toString())){
                            if(us.type.equals(type) && type.equals("Customer")) {
                                startActivity(new Intent(MainActivity.this, Home.class));
                                finish();
                            }
                            if(us.type.equals(type) && type.equals("Vendor")){
                                startActivity(new Intent(MainActivity.this, VendorActivity.class));
                                finish();
                            }
                            break;
                        }
                    }

                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(up){
            final ValueAnimator animator4 = ValueAnimator.ofInt(height-500,0);
            animator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (int) valueAnimator.getAnimatedValue();
                    rl1.getLayoutParams().height = val;
                    rl1.requestLayout();
                }
            });
            animator4.start();
            tv1.animate().translationY(move);

        }else{
            //finish();
        }
    }
}
