package com.example.timer.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ignacy on 09.02.18.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			return new StatisticsFragment();
		}
		if (position == 1) {
			return new CounterFragment();
		}
		return new ScoreFragment();

	}

	@Override
	public int getCount() {
		return 3;
	}
}
