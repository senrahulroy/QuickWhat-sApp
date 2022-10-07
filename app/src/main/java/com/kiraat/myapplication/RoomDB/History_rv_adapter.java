package com.kiraat.myapplication.RoomDB;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kiraat.myapplication.R;
import com.kiraat.myapplication.tab.History;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class History_rv_adapter extends RecyclerView.Adapter<History_rv_adapter.MyHistory_holder> {

    Context context;
    ArrayList<User_history_Data> myarrayList;
    History history;

    TextView _mobile_NO,_time_history;
    FloatingActionButton _delete_data,_sent_again;

    public History_rv_adapter(Context context, ArrayList<User_history_Data> arrayList){
        this.context=context;
        this.myarrayList=arrayList;
    }
    @NonNull
    @Override
    public MyHistory_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Myview= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_history_design,parent,false);
        return new MyHistory_holder(Myview);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHistory_holder holder, @SuppressLint("RecyclerView") int position) {

        _mobile_NO.setText(myarrayList.get(position).get_mobile_number());

        _delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DB_helper db_helper=new DB_helper(context);
                 int deleted_id=myarrayList.get(position).get_id();
                 db_helper.data_Delete(deleted_id);
                 Toast.makeText(context, "Deleted ", Toast.LENGTH_SHORT).show();
                 history=new History();
                 history.refresh();
            }
        });
        _sent_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog= new Dialog(context);
                dialog.setContentView(R.layout.sent_dialog);
                //dialog.getWindow().setLayout(975, 2000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(true);
                dialog.show();
                EditText message=dialog.findViewById(R.id.message);
                Button _cncl=dialog.findViewById(R.id.cancel);
                Button _snd=dialog.findViewById(R.id.send);

                _cncl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                _snd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String whatsapp = "https://wa.me/";

                            String MyText = "?text=";
                            String my_cnt =myarrayList.get(position).get_country_name();
                            String my_code=myarrayList.get(position).get_country_dial_code();
                            String my_number=myarrayList.get(position).get_mobile_number();
                            String mymessage = message.getText().toString();

                            Intent intent = new Intent(Intent.ACTION_DEFAULT);
                            intent.setData(Uri.parse(whatsapp + my_code +my_number+ MyText + mymessage));
                            context.startActivity(intent);

                        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
                        Date currentLocalTime = cal.getTime();
                        DateFormat date = new SimpleDateFormat("HH:mm a");

                        String localTime = date.format(currentLocalTime);
                        Log.e("TIME",localTime);
                            User_history_Data uhd = new User_history_Data();
                        uhd.set_country_name(my_cnt);
                        uhd.set_country_dial_code(my_code);
                        uhd.set_mobile_number(my_number);
                        uhd.set_my_time(localTime);
                        DB_helper db_helper = new DB_helper(context);
                        db_helper.DB_insert(uhd);
                        Log.e("Data successfully ", "Ineret in table ");
                        History.refresh();

                    }
                });

            }
        });
//        User_history_Data user_history_data=new User_history_Data();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");

        String localTime = date.format(currentLocalTime);
        Log.e("TIME",localTime);
        int hours = 0;
        int min=0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");

            Date date1 = simpleDateFormat.parse(myarrayList.get(position).get_my_time());
            Date date2 = simpleDateFormat.parse(localTime);

            long second = TimeUnit.MILLISECONDS.toSeconds(date2.getTime() - date1.getTime());
            long minutes= TimeUnit.MILLISECONDS.toMinutes(date2.getTime() - date1.getTime());
            long hourNN = TimeUnit.MILLISECONDS.toHours(date2.getTime() - date1.getTime());
            long daysn   = TimeUnit.MILLISECONDS.toDays(date2.getTime() - date1.getTime());

            if(second<60)
            {  _time_history.setText(second+" seconds ago");  }
            else if(minutes<60)
            {  _time_history.setText(minutes+" minutes ago"); }
            else if(hourNN<24)
            {  _time_history.setText(hourNN + " hours ago");  }
            else
            {  _time_history.setText(daysn+" days ago"); }

            Log.i("======= Hours", " :: " + hours);
        }
        catch (Exception e) { }
    }

    @Override
    public int getItemCount() {
        return myarrayList.size();
    }

    class MyHistory_holder extends RecyclerView.ViewHolder{

        public MyHistory_holder(@NonNull View itemView) {
            super(itemView);
            _mobile_NO=itemView.findViewById(R.id.mobile_no);
            _time_history=itemView.findViewById(R.id.time_history);
            _delete_data=itemView.findViewById(R.id.delete_data);
            _sent_again=itemView.findViewById(R.id.btn_sent);



        }
    }
}
