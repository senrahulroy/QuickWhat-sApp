package com.kiraat.myapplication;

import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Spinner_adpater extends ArrayAdapter<Country_spinner> {


    public Spinner_adpater(@NonNull Context context, ArrayList<Country_spinner> arrayList) {

        super(context, 0 ,arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int pos,View convertView,ViewGroup parent)
    {
     if(convertView==null){
         convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_recycle,parent,false);
     }
        TextView _cntry_Name=convertView.findViewById(R.id.country_v);

        Country_spinner country_spinner=getItem(pos);
        String cnt_name,cnt_dial,cnt_code;
        cnt_name=country_spinner.get_country_name();
        cnt_dial=country_spinner.get_country_dial();
        cnt_code=country_spinner.get_country_code();

        String _main_formate= cnt_dial + " " + cnt_code + " " + cnt_name;
        if(country_spinner!=null){
            _cntry_Name.setText(_main_formate);
        }

        return convertView;
    }
}
