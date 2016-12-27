package com.example.mobiletvguide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.mobiletvguide.customdialog.LoadingDialog;
import com.example.mobiletvguide.query.AsyncTaskQuery;

public class LoadWeekProgramList {

	public static ArrayList<String> dateList;
	public static ArrayList<String> tabs;

	Button btnListener;
	AsyncTaskQuery objProgramListQuery;
	List<NameValuePair> params;

	LoadingDialog loadingDialog;

	int success;
	public static ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();

	public LoadWeekProgramList(final Context context, final int channelID, int categoryID)
	{
		btnListener = new Button(context);
		loadingDialog = new LoadingDialog(context);

		getDateList();

		params = new ArrayList<NameValuePair>();

		if (categoryID == 0) params.add(new BasicNameValuePair("category_id", "all"));
		else
			params.add(new BasicNameValuePair("category_id", "" + categoryID));

		if (channelID == 0) params.add(new BasicNameValuePair("channel_id", "all"));
		else
			params.add(new BasicNameValuePair("channel_id", "" + channelID));

		params.add(new BasicNameValuePair("start_date", dateList.get(0)));
		params.add(new BasicNameValuePair("end_date", dateList.get(6)));

		loadingDialog.show();

		objProgramListQuery = new AsyncTaskQuery(StaticValues.programListUrl, StaticValues.programListColumns,
				params, btnListener);

		btnListener.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				loadingDialog.dismiss();

				success = objProgramListQuery.success;
				data_list = objProgramListQuery.data_list;

				if (success == 1)
				{
					if (data_list.isEmpty()) StaticValues.showToast(context, "Program List is Empty");
					else
					{

						Bundle bundle = new Bundle();

						if (channelID != 0) bundle.putString("title", data_list.get(0).get("channel_name"));
						else
							bundle.putString("title", data_list.get(0).get("category_name"));

						WeekFragment objWeekFragment = new WeekFragment();
						objWeekFragment.setArguments(bundle);

						FragmentActivity activity = (FragmentActivity) context;

						activity.getSupportFragmentManager().beginTransaction()
								.replace(R.id.frame_container, objWeekFragment)
								.addToBackStack(null)
								.commit();
					}
				}
				else
				{
					//channelTabList.setVisibility(0);
					StaticValues.showToast(context, "Error connecting server.");
				}

			}
		});

	}

	public void getDateList()
	{
		dateList = new ArrayList<String>();
		tabs = new ArrayList<String>();

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd, yyyy"); // Sep 17, 2015
		SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE"); // Friday

		for (int i = 0; i < 7; i++)
		{
			if (i != 0) calendar.add(Calendar.DATE, 1);
			dateList.add(sdfDate.format(calendar.getTime()));

			if (i == 0) tabs.add(sdfDate.format(calendar.getTime()) + " (Today)");
			else if (i == 1) tabs.add(sdfDate.format(calendar.getTime()) + " (Tomorrow)");
			else
				tabs.add(sdfDate.format(calendar.getTime()) + " (" + sdfDay.format(calendar.getTime()) + ")"); // Sep 17, 2015 (Friday)
		}
	}

}
