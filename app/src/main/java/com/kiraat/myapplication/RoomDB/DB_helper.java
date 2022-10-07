package com.kiraat.myapplication.RoomDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.support.annotation.Nullable;

import androidx.annotation.Nullable;

import com.kiraat.myapplication.tab.Direct_sms;

import java.util.ArrayList;
import java.util.List;

public class DB_helper extends SQLiteOpenHelper {

    public static final String DBNAME = "mydb";
    public static final String TB_NAME = "my_history";
    public static final String ID = "id";
    public static final String COUNTRY_NAME = "country_name";
    public static final String COUNTRY_DIAL_CODE = "country_dial_code";
    public static final String MOBILE_NUMBER = "mobile_number";
    public static final String MY_TIME="my_time";
    public static final int VERSION = 1;

    public DB_helper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TB_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COUNTRY_NAME
                + " TEXT," + COUNTRY_DIAL_CODE + " TEXT," + MOBILE_NUMBER + " TEXT," + MY_TIME+ " TEXT)";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // below code its query for data insert into table
    public void DB_insert(User_history_Data user_history_data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COUNTRY_NAME, user_history_data.get_country_name());
        cv.put(COUNTRY_DIAL_CODE, user_history_data.get_country_dial_code());
        cv.put(MOBILE_NUMBER, user_history_data.get_mobile_number());
        cv.put(MY_TIME,user_history_data.get_my_time());
        db.insert(TB_NAME, ID, cv);
        db.close();
    }
    // below code its query for data get/show in any array adapter

    public List<User_history_Data> DB_Show(){
        ArrayList<User_history_Data> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String Column[] = {ID, COUNTRY_NAME, COUNTRY_DIAL_CODE, MOBILE_NUMBER};
        Cursor cursor = db.query(TB_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String country_name = cursor.getString(1);
            String country_dial_code = cursor.getString(2);
            String mobile_number = cursor.getString(3);
            String  my_number=cursor.getString(4);
            User_history_Data user_D = new User_history_Data();
            user_D.set_id(id);
            user_D.set_country_name(country_name);
            user_D.set_country_dial_code(country_dial_code);
            user_D.set_mobile_number(mobile_number);
            user_D.set_my_time(my_number);
            arrayList.add(user_D);
        }
        return arrayList;
    }


    public void data_Delete (int id ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String where=ID +"="+id;
                db.delete(TB_NAME,where,null);
                db.close();


    }
}
