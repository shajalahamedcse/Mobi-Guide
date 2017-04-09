package com.example.mobiletvguide;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.mobiletvguide.customlist.TopChartListAdapter;

public class TopChartTabFragment extends Fragment {

	int position;
	ListView topChartList;

	ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_top_chart_tab, container, false);

		position = getArguments().getInt("position");

		topChartList = (ListView) rootView.findViewById(R.id.topChartTabList);

		for (int i = 0; i < StaticValues.top_chart_data_list.size(); i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map = StaticValues.top_chart_data_list.get(i);

			if (map.get("type").equals(String.valueOf(position + 1))) data_list.add(map);
		}

		TopChartListAdapter adapter = new TopChartListAdapter(getActivity(), data_list);
		topChartList.setAdapter(adapter);

		topChartList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3)
			{
				// TODO Auto-generated method stub

			}
		});

		return rootView;
	}
}
