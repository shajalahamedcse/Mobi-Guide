package com.example.mobiletvguide.customlist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobiletvguide.R;

public class HListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	ArrayList<String> data;

	public HListAdapter(Activity activity, ArrayList<String> data)
	{
		this.activity = activity;
		this.data = data;
	}

	@Override
	public int getCount()
	{
		return data.size();
	}

	@Override
	public Object getItem(int location)
	{
		return data.get(location);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.hlist_row, null);

		TextView txtTime = (TextView) convertView.findViewById(R.id.txtTime);

		txtTime.setText(data.get(position).toString());

		return convertView;
	}

}