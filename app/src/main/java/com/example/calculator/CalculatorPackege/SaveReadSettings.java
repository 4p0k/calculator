package com.example.calculator.CalculatorPackege;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SaveReadSettings {

    public void saveSetCheckBoxArray(String nameArrayCheckBox, Boolean[] arrayCheckBox, Context mContext){
        SharedPreferences prefs = mContext.getSharedPreferences(nameArrayCheckBox, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(nameArrayCheckBox +"_size", arrayCheckBox.length);

        for(int i = 0; i < arrayCheckBox.length; i++){
            editor.putBoolean(nameArrayCheckBox + "_" + i, arrayCheckBox[i]);
        }
        editor.apply();
    }

    public Boolean[] loadSetCheckBoxArray(String nameArrayCheckBox, int sizeArray, Context mContext) {

        Boolean array[] = new Boolean[sizeArray];
        SharedPreferences prefs = mContext.getSharedPreferences(nameArrayCheckBox, 0);

        if (!prefs.contains(nameArrayCheckBox)) {
            int size = prefs.getInt(nameArrayCheckBox + "_size", 0);

            if (size == sizeArray) {
                for (int i = 0; i < size; i++) {
                    array[i] = prefs.getBoolean(nameArrayCheckBox + "_" + i, false);
                }
                return array;
            }
        }
        for (int i = 0; i < sizeArray; i++) {
            array[i] = false;
        }
        return array;
    }



}
