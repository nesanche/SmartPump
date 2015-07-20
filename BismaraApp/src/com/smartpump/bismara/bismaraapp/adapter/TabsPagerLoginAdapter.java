package com.smartpump.bismara.bismaraapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smartpump.bismara.bismaraapp.ui.activities.mainactivity.fragments.EnterMailFragment;
import com.smartpump.bismara.bismaraapp.ui.activities.mainactivity.fragments.LoginFragment;


/**
 * Adapter that holds the information for the different pages in the login viewpager
 * 
 * @author nesanche
 *
 */
public class TabsPagerLoginAdapter extends FragmentPagerAdapter {

	public TabsPagerLoginAdapter(FragmentManager fm) {
		super(fm);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
	 */
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new LoginFragment();
		case 1:
			return new EnterMailFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
