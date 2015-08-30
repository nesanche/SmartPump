package com.smartpump.bismara.app.patient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments.EnterDoctorFragment;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments.EnterNameFragment;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments.EnterPhoneFragment;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments.EnterPinFragment;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments.EnterUserFragment;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments.RegisterPumpFragment;

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
        switch (index) {
        case 0:
            return new EnterNameFragment();
        case 1:
            return new EnterPhoneFragment();
        case 2:
            return new EnterUserFragment();
        case 3:
            return new EnterDoctorFragment();
        case 4:
            return new RegisterPumpFragment();
        case 5:
            return new EnterPinFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }

}
