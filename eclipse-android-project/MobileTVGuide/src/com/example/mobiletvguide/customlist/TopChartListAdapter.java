package com.example.mobiletvguide.customlist;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.mobiletvguide.R;
import com.example.mobiletvguide.StaticValues;
import com.example.mobiletvguide.util.AppController;

public class TopChartListAdapter extends BaseAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;

	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	String imageUrl_1 = StaticValues.server + "top_chart_1/";
	String imageUrl_2 = StaticValues.server + "top_chart_2/";

	public TopChartListAdapter(Activity activity, ArrayList<HashMap<String, String>> data)
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
			convertView = inflater.inflate(R.layout.top_chart_list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);

		TextView name = (TextView) convertView.findViewById(R.id.name);

		HashMap<String, String> map = data.get(position);

		if (map.get("type").equals("1")) thumbNail.setImageUrl(imageUrl_1 + map.get("rank") + ".png", imageLoader);
		else
			thumbNail.setImageUrl(imageUrl_2 + map.get("rank") + ".png", imageLoader);

		name.setText(map.get("name"));

		return convertView;
	}

}