package com.example.mobiletvguide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.lucasr.twowayview.TwoWayView;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.mobiletvguide.customdialog.LoadingDialog;
import com.example.mobiletvguide.customlist.HListAdapter;
import com.example.mobiletvguide.customlist.NowNextListAdapter;
import com.example.mobiletvguide.query.AsyncTaskQuery;

public class NowNextFragment extends Fragment {

	ArrayList<String> timeList;
	ArrayList<String> hourList;

	ListView listView;
	TwoWayView hListView;

	Button btnListener;
	LoadingDialog loadingDialog;

	int success;
	ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();

	AsyncTaskQuery objNowNextQuery;

	public NowNextFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_now_next, container, false);

		listView = (ListView) rootView.findViewById(R.id.listCategories);
		loadingDialog = new LoadingDialog(getActivity());

		getDateList();

		HListAdapter hListAdapter = new HListAdapter(getActivity(), timeList);
		hListView = (TwoWayView) rootView.findViewById(R.id.hListView);
		hListView.setAdapter(hListAdapter);

		setSelectedTime();

		hListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3)
			{
				// TODO Auto-generated method stub

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("queryDate", hourList.get(pos)));

				loadingDialog.show();

				objNowNextQuery = new AsyncTaskQuery(StaticValues.nowNextListUrl, StaticValues.nowNextListColumns,
						params, btnListener);

			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3)
			{
				// TODO Auto-generated method stub

				new LoadWeekProgramList(getActivity(), Integer.parseInt(data_list.get(pos).get("channel_id")), 0);

			}
		});

		btnListener = new Button(getActivity());

		btnListener.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				loadingDialog.dismiss();

				success = objNowNextQuery.success;
				data_list = objNowNextQuery.data_list;

				if (success == 1)
				{
					NowNextListAdapter adapter = new NowNextListAdapter(getActivity(), data_list);
					listView.setAdapter(adapter);

					if (data_list.isEmpty()) StaticValues.showToast(getActivity(), "Program List is Empty");
				}
				else
				{
					listView.setVisibility(0);
					StaticValues.showToast(getActivity(), "Error connecting server.");
				}

			}
		});

		return rootView;
	}

	void setSelectedTime()
	{
		int tmp = 0;
		if (Calendar.getInstance().get(Calendar.MINUTE) >= 30) tmp++;

		final int position = 2 * Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + tmp;

		new Handler().post(new Runnable() {
			@Override
			public void run()
			{
				hListView.requestFocusFromTouch();
				hListView.setSelection(position);

				hListView.performItemClick(
						hListView.getAdapter().getView(position, null, null),
						position,
						hListView.getAdapter().getItemId(position));

			}
		});
	}

	void getDateList()
	{
		hourList = new ArrayList<String>();
		timeList = new ArrayList<String>();

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 2015-10-05 18:30:00
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm aa"); // 12:30 AM

		String curDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		curDate = curDate + " 00:00:00";

		try
		{
			calendar.setTime(sdfDate.parse(curDate));
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 48; i++)
		{
			if (i != 0) calendar.add(Calendar.MINUTE, 30);
			hourList.add(sdfDate.format(calendar.getTime()));
			timeList.add(sdfTime.format(calendar.getTime()));
		}

	}

}
