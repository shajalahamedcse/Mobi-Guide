package com.example.mobiletvguide.customlist;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobiletvguide.LoadChannelCategoryImages;
import com.example.mobiletvguide.R;
import com.example.mobiletvguide.StaticValues;

public class NowNextListAdapter extends BaseAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;

	public NowNextListAdapter(Activity activity, ArrayList<HashMap<String, String>> data)
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
			convertView = inflater.inflate(R.layout.now_next_row, null);

		ImageView thumbNail = (ImageView) convertView
				.findViewById(R.id.thumbnail);

		TextView channel = (TextView) convertView.findViewById(R.id.textView1);
		TextView time_now = (TextView) convertView.findViewById(R.id.textView2);
		TextView program_now = (TextView) convertView.findViewById(R.id.textView3);
		TextView time_next = (TextView) convertView.findViewById(R.id.textView4);
		TextView program_next = (TextView) convertView.findViewById(R.id.textView5);

		HashMap<String, String> map = data.get(position);

		thumbNail.setImageBitmap(new LoadChannelCategoryImages().getBitmap(StaticValues.channelFolder, map.get("channel_id") + ".png"));

		channel.setText(map.get("channel_name"));
		time_now.setText(map.get("time"));
		program_now.setText(map.get("program_name"));
		time_next.setText(map.get("time_next"));
		program_next.setText(map.get("program_name_next"));

		return convertView;
	}

}