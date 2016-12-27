package com.example.mobiletvguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TopChartTabAdapter extends FragmentStatePagerAdapter {

	public TopChartTabAdapter(FragmentManager fm)
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int i)
	{

		Bundle bundle = new Bundle();
		bundle.putInt("position", i);

		TopChartTabFragment objTopChartTabFragment = new TopChartTabFragment();
		objTopChartTabFragment.setArguments(bundle);

		return objTopChartTabFragment;
	}

	@Override
	public int getCount()
	{
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		if (position == 0) return "Hollywood";
		else
			return "Bollywood";
	}
}