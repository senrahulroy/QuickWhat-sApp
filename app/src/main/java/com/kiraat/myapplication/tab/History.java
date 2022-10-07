package com.kiraat.myapplication.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiraat.myapplication.R;
import com.kiraat.myapplication.RoomDB.DB_helper;
import com.kiraat.myapplication.RoomDB.History_rv_adapter;
import com.kiraat.myapplication.RoomDB.User_history_Data;

import java.util.ArrayList;

public class History extends Fragment {

    static ArrayList<User_history_Data> arrayList;
    static RecyclerView rv;
    static DB_helper db_helper;
    static History_rv_adapter history_rv_adapter;
    static Context context;
    static View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_history, container, false);
        context = getActivity();
        arrayList = new ArrayList<>();
        db_helper = new DB_helper(getContext());
        arrayList = (ArrayList<User_history_Data>) db_helper.DB_Show();

        rv = v.findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        history_rv_adapter = new History_rv_adapter(getActivity().getApplicationContext(), arrayList);
        rv.setAdapter(history_rv_adapter);
        refresh();
        return v;
    }

    public static void refresh() {
        db_helper = new DB_helper(context);
        arrayList = (ArrayList<User_history_Data>) db_helper.DB_Show();
        rv = v.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv.setLayoutManager(linearLayoutManager);
        history_rv_adapter = new History_rv_adapter(context, arrayList);
        rv.setAdapter(history_rv_adapter);

    }
}