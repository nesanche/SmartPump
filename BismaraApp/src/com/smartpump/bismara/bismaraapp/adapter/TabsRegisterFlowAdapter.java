package com.smartpump.bismara.bismaraapp.adapter;

import com.smartpump.bismara.bismaraapp.ui.activities.registeractivity.fragments.EnterLicenseFragment;
import com.smartpump.bismara.bismaraapp.ui.activities.registeractivity.fragments.EnterNameFragment;
import com.smartpump.bismara.bismaraapp.ui.activities.registeractivity.fragments.EnterUserFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsRegisterFlowAdapter extends FragmentPagerAdapter {

    public TabsRegisterFlowAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch(index) {
        case 0: 
            return new EnterNameFragment();
        case 1:
            return new EnterUserFragment();
        case 2:
            return new EnterLicenseFragment();
        }
        
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
    
    
}
