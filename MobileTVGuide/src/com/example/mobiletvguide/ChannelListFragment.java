package com.example.mobiletvguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.mobiletvguide.customlist.ChannelListAdapter;

public class ChannelListFragment extends Fragment {

	ListView listView;

	public ChannelListFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_channel_list, container, false);

		listView = (ListView) rootView.findViewById(R.id.listChannels);

		ChannelListAdapter adapter = new ChannelListAdapter(getActivity(), StaticValues.channel_data_list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3)
			{
				// TODO Auto-generated method stub

				int channel_id = Integer.parseInt(StaticValues.channel_data_list.get(pos).get("channel_id"));

				new LoadWeekProgramList(getActivity(), channel_id, 0);

			}
		});

		return rootView;
	}

}
