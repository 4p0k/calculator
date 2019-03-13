package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mSettings;
    String[] idCheckCod = new String[]{ "111","122","133","144",  //findViewById(getResources().getIdentifier("a" + idCheckCod, "id", getPackageName()))
                                        "211","222","233","244",
                                        "311","322","333","344",
                                        "411","421","412","431",
                                        "1","2","3","4"};
    CheckBox[] checkBoxObj = new CheckBox[idCheckCod.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] idCheckBox = new int[]{   R.id.a111,R.id.a122,R.id.a133,R.id.a144,
                                        R.id.a211,R.id.a222,R.id.a233,R.id.a244,
                                        R.id.a311,R.id.a322,R.id.a333,R.id.a344,
                                        R.id.a411,R.id.a421,R.id.a412,R.id.a431,
                                        R.id.a1,R.id.a2,R.id.a3,R.id.a4};

        for(int i = 0;i < idCheckBox.length;i++){
            checkBoxObj[i] = findViewById(idCheckBox[i]);
        }

        mSettings = getSharedPreferences("strCheckBox", Context.MODE_PRIVATE);
        if (mSettings.contains("strCheckBox")) {
            String strlodcheck = mSettings.getString("strCheckBox", "");
            StringTokenizer st = new StringTokenizer(strlodcheck, ",");

            int i = 0;
            while (st.hasMoreTokens()) {
                String y = st.nextToken();
                if (y.equals("1")) {
                    checkBoxObj[i].setChecked(true);
                }
                i++;
            }
        }
    }

    public void startAdvancedSettings (View v) {
        int i = 1;
        Log.d("som",Integer.toString(i % 2));


        /*Intent intent = new Intent(this, advancedsettings.class);
        startActivity(intent);*/
    }


    public void startShit (View v) {
        StringBuilder strfosave = new StringBuilder();
        for (int i = 0; i < idCheckCod.length; i++) {
            if (checkBoxObj[i].isChecked()) {
                strfosave.append("1,");
            } else {
                strfosave.append("0,");
            }

        }
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("strCheckBox", strfosave.toString());
        editor.apply();

        ArrayList<String> arrnum = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            if (checkBoxObj[i].isChecked())
                arrnum.add(idCheckCod[i]);
        }

        ArrayList<Integer> arrsig = new ArrayList<>();
        for (int i = 16; i < 20 ; i++) {
            if (checkBoxObj[i].isChecked())
                arrsig.add(Integer.valueOf(idCheckCod[i]));
        }

        if (arrnum.size() != 0 && arrsig.size() != 0) {
            Intent intent = new Intent(this, calculating.class);
            intent.putStringArrayListExtra("listnum", arrnum);
            intent.putIntegerArrayListExtra("listsig", arrsig);

            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }
}
