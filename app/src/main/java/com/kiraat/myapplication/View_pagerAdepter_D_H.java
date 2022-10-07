package com.kiraat.myapplication;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class View_pagerAdepter_D_H extends FragmentStatePagerAdapter {

    ArrayList<Fragment> arrayList=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();

    public View_pagerAdepter_D_H(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return arrayList.get(i);
    }


    public void  addFragment (Fragment fragment,String fragment_name){
        arrayList.add(fragment);
        name.add(fragment_name);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name.get(position);
    }

}

