package com.kiraat.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kiraat.myapplication.tab.Direct_sms;
import com.kiraat.myapplication.tab.History;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Direct Whatsapp SMS");

         tabLayout=findViewById(R.id.tablayout);
         viewPager=findViewById(R.id.view_pager);

          View_pagerAdepter_D_H view_pagerAdepter_d_h= new View_pagerAdepter_D_H(getSupportFragmentManager());
          view_pagerAdepter_d_h.addFragment(new Direct_sms(),"Direct Chat");
          view_pagerAdepter_d_h.addFragment(new History(),"History");
          viewPager.setAdapter(view_pagerAdepter_d_h);
          tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"About Us");
        //Group ID,Item ID,Order ID
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==0){
            Intent intent=new Intent(MainActivity.this,Aboutus.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

}

