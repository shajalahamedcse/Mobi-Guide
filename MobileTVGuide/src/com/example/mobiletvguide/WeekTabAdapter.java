package com.example.mobiletvguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class WeekTabAdapter extends FragmentStatePagerAdapter {

	public WeekTabAdapter(FragmentManager fm)
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int i)
	{

		Bundle bundle = new Bundle();
		bundle.putInt("position", i);

		WeekTabFragment objWeekTabFragment = new WeekTabFragment();
		objWeekTabFragment.setArguments(bundle);

		return objWeekTabFragment;
	}

	@Override
	public int getCount()
	{
		return 7;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return LoadWeekProgramList.tabs.get(position);
	}
}