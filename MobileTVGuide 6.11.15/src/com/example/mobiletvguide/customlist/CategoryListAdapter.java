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

public class CategoryListAdapter extends BaseAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;

	public CategoryListAdapter(Activity activity, ArrayList<HashMap<String, String>> data)
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
			convertView = inflater.inflate(R.layout.category_list_row, null);

		ImageView thumbNail = (ImageView) convertView
				.findViewById(R.id.thumbnail);

		TextView category = (TextView) convertView.findViewById(R.id.category);

		HashMap<String, String> map = data.get(position);

		thumbNail.setImageBitmap(new LoadChannelCategoryImages().getBitmap(StaticValues.categoryFolder, map.get("category_id") + ".png"));

		category.setText(map.get("category_name"));

		return convertView;
	}
}