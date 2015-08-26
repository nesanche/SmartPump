package com.smartpump.bismara.app.patient.ui.activities.mainactivity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.smartpump.bismara.app.patient.R;
import com.smartpump.bismara.app.patient.adapter.TabsPagerLoginAdapter;

/**
 * Activity
 * 
 * @author nesanche
 *
 */
public class MainActivity extends FragmentActivity implements TabListener {

    /** Paginador */
    private ViewPager viewPager;
    /** Adapter que inserta las paginas deseadas en el viewpager */
    private TabsPagerLoginAdapter mAdapter;
    /** Android activity action bar */
    private ActionBar actionBar;
    /** Nombres de las distintas pestañas */
    private String[] tabs = { "Login", "Register" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.pagerLogin);
        actionBar = getActionBar();
        mAdapter = new TabsPagerLoginAdapter(getSupportFragmentManager());

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
    public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
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
