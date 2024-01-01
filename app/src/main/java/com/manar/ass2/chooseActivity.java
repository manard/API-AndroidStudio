package com.manar.ass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class chooseActivity extends AppCompatActivity {//this Activity to choose between two web services.
   private Button btn1;//food Button
   private Button btn2;//product Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        Intent intent=getIntent();
         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {//to go to url1 Activity
                 Intent intent=new Intent(chooseActivity.this,url1Activity.class);
                 startActivity(intent);
                 Toast.makeText(getApplicationContext(), "url 1 selected!", Toast.LENGTH_SHORT).show();
             }
         });
         btn2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {//to go to url2 Activity
                 Intent intent=new Intent(chooseActivity.this,url2Activity.class);
                 startActivity(intent);
                 Toast.makeText(getApplicationContext(), "url 2 selected!", Toast.LENGTH_SHORT).show();
             }
         });
    }
}