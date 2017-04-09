package com.example.mobiletvguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.mobiletvguide.customlist.CategoryListAdapter;

public class CategoryListFragment extends Fragment {

	ListView listView;

	public CategoryListFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_category_list, container, false);

		listView = (ListView) rootView.findViewById(R.id.listCategories);

		CategoryListAdapter adapter = new CategoryListAdapter(getActivity(), StaticValues.category_data_list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3)
			{
				// TODO Auto-generated method stub

				int category_id = Integer.parseInt(StaticValues.category_data_list.get(pos).get("category_id"));

				new LoadWeekProgramList(getActivity(), 0, category_id);

			}
		});

		return rootView;
	}

}
