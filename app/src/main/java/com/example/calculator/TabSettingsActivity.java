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

import com.example.calculator.MainTabs.FragmentAddition;
import com.example.calculator.MainTabs.FragmentDivision;
import com.example.calculator.MainTabs.FragmentFast;
import com.example.calculator.MainTabs.FragmentMultiplication;
import com.example.calculator.MainTabs.FragmentSettings;
import com.example.calculator.MainTabs.FragmentSubtraction;

public class TabSettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentFast(), "Fast");
        adapter.addFragment(new FragmentAddition(), "+");
        adapter.addFragment(new FragmentSubtraction(), "-");
        adapter.addFragment(new FragmentMultiplication(), "*");
        adapter.addFragment(new FragmentDivision(), ":");
        adapter.addFragment(new FragmentSettings(), "Settings");
        viewPager.setAdapter(adapter);
    }


/*        TabLayout tablayout = findViewById(R.id.tabLayout);
        tablayout.addTab(tablayout.newTab().setText("Fast"));
        tablayout.addTab(tablayout.newTab().setText("+"));
        tablayout.addTab(tablayout.newTab().setText("-"));
        tablayout.addTab(tablayout.newTab().setText("*"));
        tablayout.addTab(tablayout.newTab().setText(":"));
        tablayout.addTab(tablayout.newTab().setText("Settings"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new PageAdapter(getSupportFragmentManager(),tablayout.getTabCount());
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
        });*/


    String[] idCheckCod = { "111","122","133","144",
            "211","222","233","244",
            "311","322","333","344",
            "411","412","413","422", };
    String ida = "a";
    String fullidcheck;
    public void startSettings (View v) {

        for (int i = 0; i <= idCheckCod.length; i++) {
            fullidcheck = ida + idCheckCod[i];
            CheckBox sss = findViewById(getResources().getIdentifier(fullidcheck, "id", getPackageName()));


            if (sss.isChecked()) {
                Log.d("som", "check");
            } else {
                Log.d("som", "NOT check");
            }
        }

        //Log.d("som",Integer.toString(100));
        //Intent intent = new Intent(this, TabSettingsActivity.class);
        //startActivity(intent);
    }
}
