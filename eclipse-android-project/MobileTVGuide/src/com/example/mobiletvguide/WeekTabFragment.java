package com.example.mobiletvguide;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.mobiletvguide.customlist.WeekTabListAdapter;

public class WeekTabFragment extends Fragment {

	int position;
	ListView weekTabList;
	String date;

	ArrayList<HashMap<String, String>> data_list_day = new ArrayList<HashMap<String, String>>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_week_tab, container, false);

		position = getArguments().getInt("position");

		weekTabList = (ListView) rootView.findViewById(R.id.weekTabList);

		date = LoadWeekProgramList.dateList.get(position);

		for (int i = 0; i < LoadWeekProgramList.data_list.size(); i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map = LoadWeekProgramList.data_list.get(i);

			if (map.get("date").equals(date)) data_list_day.add(map);
		}

		WeekTabListAdapter adapter = new WeekTabListAdapter(getActivity(), data_list_day);
		weekTabList.setAdapter(adapter);

		weekTabList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3)
			{
				// TODO Auto-generated method stub

				Intent i = new Intent(getActivity(), ProgramDetailsActivity.class);
				i.putExtra("program_data", data_list_day.get(pos));
				startActivity(i);

			}
		});

		return rootView;
	}
}
