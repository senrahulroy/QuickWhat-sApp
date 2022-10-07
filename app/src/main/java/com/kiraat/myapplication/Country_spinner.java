package com.kiraat.myapplication;

import android.widget.ArrayAdapter;

import com.kiraat.myapplication.tab.Direct_sms;

public class Country_spinner {
    private String _country_name;
    private String _country_dial;
    private String _country_code;

    public Country_spinner() {
    }

    public Country_spinner(String _country_name, String _country_dial, String _country_code) {

        this._country_name = _country_name;
        this._country_dial = _country_dial;
        this._country_code = _country_code;
    }

    public String get_country_name() {
        return _country_name;
    }

    public String get_country_dial() {
        return _country_dial;
    }

    public String get_country_code() {
        return _country_code;
    }

    public void set_country_name(String _country_name) {
        this._country_name = _country_name;
    }

    public void set_country_dial(String _country_dial) {
        this._country_dial = _country_dial;
    }

    public void set_country_code(String _country_code) {
        this._country_code = _country_code;
    }
}
