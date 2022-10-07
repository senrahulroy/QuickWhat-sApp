package com.kiraat.myapplication.tab;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.kiraat.myapplication.Country_spinner;
import com.kiraat.myapplication.R;
import com.kiraat.myapplication.RoomDB.DB_helper;
import com.kiraat.myapplication.RoomDB.History_rv_adapter;
import com.kiraat.myapplication.RoomDB.User_history_Data;
import com.kiraat.myapplication.Spinner_adpater;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Direct_sms extends Fragment implements View.OnClickListener {

    Context context;
    String whatsapp = "https://wa.me/";
    ArrayList<Country_spinner> arrayList;
    String TAG = "Countries";
    Spinner_adpater spinner_adpater;
    TextView textView,sp ;
    String Code;
    Dialog dialog;
    EditText message;

    Long start_Time=System.currentTimeMillis()/1000;
//    Long end_Time=System.currentTimeMillis();
//    Long Seconds=(start_Time - end_Time)/1000;
    int indt=R.anim.layout_recycle_animation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_direct_sms, container, false);
        textView = v.findViewById(R.id.cn_code);
        EditText phone = v.findViewById(R.id.sender_no);
        Button sent = v.findViewById(R.id.btn_sent);
        sp = v.findViewById(R.id.country_spd);
        arrayList = new ArrayList<>();
        jsonToArralist();
        sp.setOnClickListener(this);
        message=v.findViewById(R.id.message);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cnt_name=sp.getText().toString();
                Code = textView.getText().toString();
                String s1 = phone.getText().toString();

                if (s1.equals("")) {
                    phone.setError("Please enter no");
                } else {
                    try {
                        String MyText = "?text=";
                        String mymessage = message.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_DEFAULT);
                        intent.setData(Uri.parse(whatsapp + Code + s1 + MyText + mymessage));
                        startActivity(intent);
                        // this is for data travel between database to table
                        try {

                            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
                            Date currentLocalTime = cal.getTime();
                            DateFormat date = new SimpleDateFormat("HH:mm a");

                            String localTime = date.format(currentLocalTime);
                            Log.e("TIME",localTime);

                            User_history_Data uhd = new User_history_Data();
                            uhd.set_country_name(cnt_name);
                            uhd.set_country_dial_code(Code);
                            uhd.set_mobile_number(s1);
                            uhd.set_my_time(localTime);
                            DB_helper db_helper = new DB_helper(getActivity());
                            db_helper.DB_insert(uhd);
                            Log.e("Data successfully ", "Ineret in table ");
                            History.refresh();
                        }catch (Exception e){
                            Log.e("Data NOt ", "NOt in table ",e);
                        }

                    }catch (Exception e){  }
                }
            }
        });
        return v;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("CountryCodes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void jsonToArralist() {
        try {
            String result = loadJSONFromAsset();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray array = jsonObject.getJSONArray(TAG);

            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject1 = array.getJSONObject(i);
                String _cnt_Name = jsonObject1.getString("name");
                String _cnt_Dial = jsonObject1.getString("dial_code");
                String _cnt_Code = jsonObject1.getString("code");

                Country_spinner country_spinner = new Country_spinner();
                country_spinner.set_country_code(_cnt_Code);
                country_spinner.set_country_dial(_cnt_Dial);
                country_spinner.set_country_name(_cnt_Name);
                arrayList.add(country_spinner);
            }
        } catch (Exception e) {

        }

    }

    @Override
    public void onClick(View v) {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.spinner_layout);
//        dialog.getWindow().setLayout(975, 2000);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        dialog.show();
        SearchView editText = dialog.findViewById(R.id.search_edit);
        ListView listView = dialog.findViewById(R.id.recyclerView);
        TextView button = dialog.findViewById(R.id.closebtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Country_spinner> countries=new ArrayList<>();
                for (Country_spinner country : arrayList) {
                    if (country.get_country_name().toLowerCase().contains(newText.toLowerCase())) {
                        countries.add(country);
                    }
                }
                spinner_adpater = new Spinner_adpater(getActivity(),countries);
                LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getActivity(),indt);
                listView.setLayoutAnimation(layoutAnimationController);
                listView.setAdapter(spinner_adpater);
                return false;
            }
        });
        spinner_adpater = new Spinner_adpater(getActivity(),arrayList);
        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(getActivity(),indt);
        listView.setLayoutAnimation(layoutAnimationController);
        listView.setAdapter(spinner_adpater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country_spinner clickedItem = (Country_spinner) parent.getItemAtPosition(position);
                String dialCode = clickedItem.get_country_dial();
                String name = clickedItem.get_country_name();
                textView.setText(dialCode);
                sp.setText(name);
                dialog.dismiss();
                Toast.makeText(getActivity(), "Selected : " + name , Toast.LENGTH_SHORT).show();
            }
        });
    }
}


