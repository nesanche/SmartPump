package com.smartpump.bismara.bismaraapp.ui.activities.registeractivity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.smartpump.bismara.bismaraapp.R;
import com.smartpump.bismara.bismaraapp.adapter.TabsRegisterFlowAdapter;

@SuppressWarnings("deprecation")
public class RegisterActivity extends FragmentActivity implements TabListener {
    
    private ViewPager viewPager;
    private TabsRegisterFlowAdapter mAdapter;
    private ActionBar actionBar;
    private String[] tabs = { "Start", "Name", "License" };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_register);
        
        this.getActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.pagerRegister);
        actionBar = getActionBar();
        mAdapter = new TabsRegisterFlowAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });        
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        
    }
    
    public void setCurrentItem(int item) {
        viewPager.setCurrentItem(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    
    
}
