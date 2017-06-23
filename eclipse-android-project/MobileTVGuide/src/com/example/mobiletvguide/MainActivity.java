package com.example.mobiletvguide;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobiletvguide.query.AsyncTaskQuery;

public class MainActivity extends Activity {

	int img_count;

	Button btnListenerChannel;
	Button btnListenerCategory;
	Button btnListenerTopChart;

	AsyncTaskQuery objChannelListQuery;
	AsyncTaskQuery objCategoryListQuery;
	AsyncTaskQuery objTopChartListQuery;

	private ProgressBar mProgress;
	double progressStatus, stepSize;

	Boolean isSDPresent;

	TextView txtLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().hide();

		txtLoading = (TextView) findViewById(R.id.textView3);

		mProgress = (ProgressBar) findViewById(R.id.progressBar1);

		isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

		if (isConnectedToInternet()) loadChannelCategoryData();
		else
			showConnectionAlert();

	}

	void loadChannelCategoryData()
	{
		btnListenerChannel = new Button(this);
		btnListenerCategory = new Button(this);
		btnListenerTopChart = new Button(this);

		mProgress.setProgress(10);
		txtLoading.setText("Loading Channels...");

		objChannelListQuery = new AsyncTaskQuery(StaticValues.channelListUrl, StaticValues.channelListColumns,
				new ArrayList<NameValuePair>(), btnListenerChannel);

		btnListenerChannel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				int success = objChannelListQuery.success;
				StaticValues.channel_data_list = objChannelListQuery.data_list;

				if (success == 1)
				{
					mProgress.setProgress(30);
					txtLoading.setText("Loading Categories...");
					objCategoryListQuery = new AsyncTaskQuery(StaticValues.categoryListUrl, StaticValues.categoryListColumns,
							new ArrayList<NameValuePair>(), btnListenerCategory);
				}
				else
				{
					showConnectionAlert();
				}

			}
		});

		btnListenerCategory.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				int success = objCategoryListQuery.success;
				StaticValues.category_data_list = objCategoryListQuery.data_list;

				if (success == 1)
				{
					mProgress.setProgress(50);
					txtLoading.setText("Loading Top Charts...");
					objTopChartListQuery = new AsyncTaskQuery(StaticValues.topChartListUrl, StaticValues.topChartListColumns,
							new ArrayList<NameValuePair>(), btnListenerTopChart);

				}
				else
				{
					showConnectionAlert();
				}

			}
		});

		btnListenerTopChart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				int success = objTopChartListQuery.success;
				StaticValues.top_chart_data_list = objTopChartListQuery.data_list;

				if (success == 1)
				{
					if (isSDPresent) loadImage();
					else
						showSDAlert();
				}
				else
				{
					showConnectionAlert();
				}
			}
		});
	}

	void loadImage()
	{
		mProgress.setProgress(60);
		txtLoading.setText("Loading Images...");

		File folder = new File(Environment.getExternalStorageDirectory() + "/" + StaticValues.rootFolder);
		if (!folder.exists()) folder.mkdir();

		img_count = StaticValues.channel_data_list.size() + StaticValues.category_data_list.size();

		progressStatus = 60.0;
		stepSize = 40.0 / img_count;

		Button btnListener = new Button(MainActivity.this);

		for (int i = 0; i < StaticValues.channel_data_list.size(); i++)
		{
			String id = StaticValues.channel_data_list.get(i).get("channel_id");
			String url = StaticValues.server + "channel_images/" + id + ".png";

			String folderName = StaticValues.channelFolder;
			String imageName = id + ".png";

			new LoadChannelCategoryImages(url, folderName, imageName, btnListener);
		}

		for (int i = 0; i < StaticValues.category_data_list.size(); i++)
		{
			String id = StaticValues.category_data_list.get(i).get("category_id");
			String url = StaticValues.server + "category_images/" + id + ".png";

			String folderName = StaticValues.categoryFolder;
			String imageName = id + ".png";

			new LoadChannelCategoryImages(url, folderName, imageName, btnListener);
		}

		btnListener.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				img_count--;

				progressStatus += stepSize;
				mProgress.setProgress((int) progressStatus);

				if (img_count == 0) doIntent();
			}
		});

	}

	public void doIntent()
	{
		if (isConnectedToInternet())
		{
			Intent i = new Intent(MainActivity.this, NavDrawerActivity.class);
			startActivity(i);
			finish();
		}
		else
			showConnectionAlert();

	}

	public boolean isConnectedToInternet()
	{
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null)
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
			{
				for (int i = 0; i < info.length; i++)
				{
					if (info[i].getState() == NetworkInfo.State.CONNECTED) return true;
				}
			}
		}
		return false;
	}

	public void showConnectionAlert()
	{
		new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK)
				.setTitle("Connection error")
				.setMessage(R.string.connection_error_alert)
				.setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.dismiss();
						MainActivity.this.finish();
					}
				})
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();
	}

	public void showSDAlert()
	{
		new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK)
				.setTitle("External Storage Error")
				.setMessage(R.string.sd_error_alert)
				.setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.dismiss();
						doIntent();
					}
				})
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();
	}

}
