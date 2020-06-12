package com.ssdevelopers.action.online_shopping;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;

public class Signin extends AppCompatActivity {

    EditText name,email,pass;
    TextView dob;
    Button submit;
    RelativeLayout layout_progress;
    String date = "",type;
    CardView cv;
    Thread t;
    Uri uri,default_uri;
    ImageView upload_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        type = getIntent().getStringExtra("Type");
        submit = findViewById(R.id.submit);
        name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        cv = findViewById(R.id.upload_profile);
        dob = findViewById(R.id.dob);
        layout_progress = findViewById(R.id.layout_progress);
        upload_img = findViewById(R.id.roundedimage);
        default_uri = Uri.parse("android.resource://"+this.getPackageName()+"/"+R.drawable.profile1);
        dob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final DatePickerDialog dob_dialog = new DatePickerDialog(Signin.this);
                dob_dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                       date = i+" / "+i1+" / "+i2;
                        dob.setText(date);
                    }
                });
                dob_dialog.show();
            }
        });

        final Upload_ProfilePhoto photo = new Upload_ProfilePhoto();
        final Upload_Data data = new Upload_Data();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty()&& !pass.getText().toString().isEmpty()&& !date.isEmpty()) {
              layout_progress.setVisibility(View.VISIBLE);
              data.execute(name.getText().toString(), email.getText().toString(), pass.getText().toString(), date);
          }
            }
        });
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });
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
            if(data.getData()!=null){
                uri = data.getData( );
                upload_img.setImageURI(uri);

            }
        }
    }
    public static String getPath(Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }
    class Upload_ProfilePhoto extends AsyncTask<String,Void,Void>{


        @Override
        protected Void doInBackground(String... strings) {

                File  profile_path = new File(strings[0]);
                ServerConnect connect = new ServerConnect();
                connect.connectToFTPServer(profile_path);
               // connect.uploadFile(profile_path);


            return null;
        }
    }

    class Upload_Data extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                URL url = new URL("https://citybasket.in/ss-user.php?name="+strings[0]+"&username="+strings[1]+"&password="+strings[2]+"&dob="+strings[3]);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int response = connection.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    Log.e("TAG",sb.toString());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            startActivity(new Intent(Signin.this,Home.class));
            finish();
            layout_progress.setVisibility(View.GONE);
        }
    }
        @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
