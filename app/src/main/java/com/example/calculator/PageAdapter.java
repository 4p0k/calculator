package com.example.calculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    int counttab;
    public PageAdapter(FragmentManager fm, int counttab) {
        super(fm);
        this.counttab = counttab;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0 :
                TabFastSet tabfastset = new TabFastSet();
                return  tabfastset;
            case 1 :
                TabAddSet tabaddset = new TabAddSet();
                return  tabaddset;
            case 2 :
                TabSubSet tabsubset = new TabSubSet();
                return  tabsubset;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
