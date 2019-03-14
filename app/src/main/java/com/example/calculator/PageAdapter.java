package com.example.calculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
                TabFastSet tabFastSet = new TabFastSet();
                return  tabFastSet;
            case 1 :
                TabAddSet tabAddSet = new TabAddSet();
                return  tabAddSet;
            case 2 :
                TabSubSet tabSubSet = new TabSubSet();
                return  tabSubSet;
            case 3 :
                TabMulSet tabMulSet = new TabMulSet();
                return  tabMulSet;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
