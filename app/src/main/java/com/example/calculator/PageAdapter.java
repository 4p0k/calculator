package com.example.calculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.calculator.MainTabs.FragmentAddition;
import com.example.calculator.MainTabs.FragmentDivision;
import com.example.calculator.MainTabs.FragmentFast;
import com.example.calculator.MainTabs.FragmentMultiplication;
import com.example.calculator.MainTabs.FragmentSettings;
import com.example.calculator.MainTabs.FragmentSubtraction;


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
                FragmentFast fragmentFast = new FragmentFast();
                return fragmentFast;
            case 1 :
                FragmentAddition fragmentAddition = new FragmentAddition();
                return fragmentAddition;
            case 2 :
                FragmentSubtraction fragmentSubtraction = new FragmentSubtraction();
                return fragmentSubtraction;
            case 3 :
                FragmentMultiplication fragmentMultiplication = new FragmentMultiplication();
                return fragmentMultiplication;
            case 4 :
                FragmentDivision fragmentDivision = new FragmentDivision();
                return fragmentDivision;
            case 5 :
                FragmentSettings fragmentSettings = new FragmentSettings();
                return fragmentSettings;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counttab;
    }
}
