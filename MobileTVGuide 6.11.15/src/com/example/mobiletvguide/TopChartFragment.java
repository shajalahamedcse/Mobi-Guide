package com.example.mobiletvguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TopChartFragment extends Fragment {

	TopChartTabAdapter adapter;
	ViewPager pager;

	public TopChartFragment()
	{
		// TODO Auto-generated constructor stub
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_top_chart, container, false);

		adapter = new TopChartTabAdapter(getActivity().getSupportFragmentManager());
		pager = (ViewPager) rootView.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		return rootView;
	}
}