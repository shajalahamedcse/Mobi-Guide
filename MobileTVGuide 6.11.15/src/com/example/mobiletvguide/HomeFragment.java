package com.example.mobiletvguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class HomeFragment extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);

		rootView.findViewById(R.id.btn_now_next).setOnClickListener(this);
		rootView.findViewById(R.id.btn_top_chart).setOnClickListener(this);
		rootView.findViewById(R.id.btn_channel).setOnClickListener(this);
		rootView.findViewById(R.id.btn_catagory).setOnClickListener(this);
		rootView.findViewById(R.id.btn_reminder).setOnClickListener(this);
		rootView.findViewById(R.id.btn_about).setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub

		switch (v.getId())
		{
		case R.id.btn_now_next:
			clickDrawer(1);
			break;

		case R.id.btn_top_chart:
			clickDrawer(2);
			break;

		case R.id.btn_channel:
			clickDrawer(3);
			break;

		case R.id.btn_catagory:
			clickDrawer(4);
			break;

		case R.id.btn_reminder:
			clickDrawer(5);
			break;

		case R.id.btn_about:
			clickDrawer(6);
			break;

		default:
			break;
		}

	}

	void clickDrawer(int pos)
	{
		NavDrawerActivity.mDrawerList.requestFocusFromTouch();
		NavDrawerActivity.mDrawerList.setSelection(pos);

		NavDrawerActivity.mDrawerList.performItemClick(
				NavDrawerActivity.mDrawerList.getAdapter().getView(pos, null, null),
				pos,
				NavDrawerActivity.mDrawerList.getAdapter().getItemId(pos));
	}

}
