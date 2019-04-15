package com.example.calculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.calculator.MainTabs.FragmentFast;
import com.example.calculator.MainTabs.FragmentSettings;

public class PageAdapter extends FragmentStatePagerAdapter {

   int counttab;
    PageAdapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0 :
                return new FragmentFast();
            case 1 :
                return new FragmentSettings();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
