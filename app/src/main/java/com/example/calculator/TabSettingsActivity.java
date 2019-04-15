package com.example.calculator;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class TabSettingsActivity extends AppCompatActivity {

    CheckBox[][] checkBoxObjects = new CheckBox[5][4];
    int[][] idCheckBox = {  {R.id.a111,R.id.a122,R.id.a133,R.id.a144},
                            {R.id.a211,R.id.a222,R.id.a233,R.id.a244},
                            {R.id.a311,R.id.a322,R.id.a333,R.id.a344},
                            {R.id.a411,R.id.a412,R.id.a413,R.id.a422},
                            {R.id.a1,R.id.a2,R.id.a3,R.id.a4}  };
    String[] idCheckCod = { "111","122","133","144",
            "211","222","233","244",
            "311","322","333","344",
            "411","412","413","422", };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_settings);

        TabLayout tablayout = findViewById(R.id.tabLayout);
        tablayout.addTab(tablayout.newTab().setText("Fast"));
        tablayout.addTab(tablayout.newTab().setText("Settings"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new PageAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                checkBoxObjects[i][j] = findViewById(idCheckBox[i][j]);
            }
        }


    }


    public void startExtendInfoActivity (View view) {



        Intent intent = new Intent(this, ExtendInfoActivity.class);
        intent.putExtra("type",Integer.parseInt((String) view.getTag()));
        startActivity(intent);
    }
}
