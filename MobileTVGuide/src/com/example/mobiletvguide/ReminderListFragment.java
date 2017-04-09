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
import com.example.mobiletvguide.database.DBOperations;

public class ReminderListFragment extends Fragment {

	int success;
	ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();

	ListView listAlarms;

	public ReminderListFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_reminder_list, container, false);

		listAlarms = (ListView) rootView.findViewById(R.id.listAlarms);

		final ArrayList<HashMap<String, String>> alarm_list = new DBOperations(getActivity()).query();
		WeekTabListAdapter adapter = new WeekTabListAdapter(getActivity(), alarm_list);
		listAlarms.setAdapter(adapter);

		if (alarm_list.isEmpty()) StaticValues.showToast(getActivity(), "Alarm List is Empty");

		listAlarms.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, final int pos, long arg3)
			{
				// TODO Auto-generated method stub

				Intent i = new Intent(getActivity(), ProgramDetailsActivity.class);
				i.putExtra("program_data", alarm_list.get(pos));
				startActivity(i);
			}
		});
		return rootView;
	}

}
