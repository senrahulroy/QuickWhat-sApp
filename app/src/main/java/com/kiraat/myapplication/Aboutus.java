package com.kiraat.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        getSupportActionBar().hide();
        Button about_btn=findViewById(R.id.about_btn);

        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String about="https://www.kiraattechnology.com/#about";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(about));
                startActivity(intent);

            }
        });
    }
}