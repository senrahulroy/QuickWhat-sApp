package com.kiraat.myapplication.RoomDB;

public class User_history_Data {
    int _id;
    String _country_name,_country_dial_code,_mobile_number,_my_time;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_country_name() {
        return _country_name;
    }

    public void set_country_name(String _country_name) {
        this._country_name = _country_name;
    }

    public String get_country_dial_code() {
        return _country_dial_code;
    }

    public void set_country_dial_code(String _country_dial_code) {
        this._country_dial_code = _country_dial_code;
    }

    public String get_mobile_number() {
        return _mobile_number;
    }

    public void set_mobile_number(String _mobile_number) {
        this._mobile_number = _mobile_number;
    }

    public String get_my_time() {
        return _my_time;
    }

    public void set_my_time(String _my_time) {
        this._my_time = _my_time;
    }
}
