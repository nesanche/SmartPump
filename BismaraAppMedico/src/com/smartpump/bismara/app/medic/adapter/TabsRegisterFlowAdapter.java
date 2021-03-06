package com.smartpump.bismara.app.medic.adapter;

import com.smartpump.bismara.app.medic.ui.activities.registeractivity.fragments.EnterLicenseFragment;
import com.smartpump.bismara.app.medic.ui.activities.registeractivity.fragments.EnterNameFragment;
import com.smartpump.bismara.app.medic.ui.activities.registeractivity.fragments.EnterUserFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Adapter that holds the flow of the register adapter activity
 * 
 * @author nesanche
 *
 */
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
