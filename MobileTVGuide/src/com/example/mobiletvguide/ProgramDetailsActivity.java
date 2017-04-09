package com.example.mobiletvguide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.mobiletvguide.alarm.AlarmController;
import com.example.mobiletvguide.customdialog.ListDialog;
import com.example.mobiletvguide.database.DBOperations;
import com.example.mobiletvguide.util.AppController;

public class ProgramDetailsActivity extends Activity {

	String imageUrl = StaticValues.server + "program_images/";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_program_details);

		@SuppressWarnings("unchecked")
		final HashMap<String, String> map = (HashMap<String, String>) getIntent().getSerializableExtra("program_data");

		String cur_alarm_mins = new DBOperations(this).getAlarmStatus(Integer.parseInt(map.get("program_id")));

		TextView programName = (TextView) findViewById(R.id.txtProgram);
		TextView category = (TextView) findViewById(R.id.txtCategory);
		TextView channelName = (TextView) findViewById(R.id.txtChannel);
		TextView time = (TextView) findViewById(R.id.txtTime);
		TextView date = (TextView) findViewById(R.id.txtDate);
		final TextView alarmValue = (TextView) findViewById(R.id.txtAlarm);
		final ImageView imgAlarm = (ImageView) findViewById(R.id.imgAlarm);

		ImageLoader imageLoader = AppController.getInstance().getImageLoader();
		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail);
		thumbNail.setImageUrl(imageUrl + map.get("program_id") + ".png", imageLoader);

		alarmValue.setPaintFlags(alarmValue.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

		programName.setText(map.get("program_name"));
		category.setText(map.get("category_name"));
		channelName.setText("On " + map.get("channel_name"));
		time.setText("At " + map.get("time"));
		date.setText(map.get("date") + " (" + map.get("day") + ")");

		if (cur_alarm_mins.equals("NO_ALARM"))
		{
			imgAlarm.setImageResource(R.drawable.ic_alarm_off);
			alarmValue.setText("Alarm OFF");
		}
		else
		{
			imgAlarm.setImageResource(R.drawable.ic_alarm_on);
			alarmValue.setText("Alarm before " + cur_alarm_mins + " Minutes");
		}

		alarmValue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				final int program_id = Integer.parseInt(map.get("program_id"));

				Button btnListener = new Button(ProgramDetailsActivity.this); // fake button to listen alarm time selection

				final ListDialog objListDialog = new ListDialog(ProgramDetailsActivity.this, btnListener);

				btnListener.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v)
					{
						// TODO Auto-generated method stub
						int selectedPos = objListDialog.selectedPos;

						if (selectedPos == 0) // alarm off
						{
							imgAlarm.setImageResource(R.drawable.ic_alarm_off);
							alarmValue.setText("Alarm OFF");

							new AlarmController(ProgramDetailsActivity.this, program_id).cancelAlarm();
							StaticValues.showToast(ProgramDetailsActivity.this, "Alarm OFF");

							new DBOperations(ProgramDetailsActivity.this).delete(program_id);
						}
						else
						{
							int alarm_mins = StaticValues.alarm_value_mins[objListDialog.selectedPos];

							map.put("alarm_mins", String.valueOf(alarm_mins));

							String date = map.get("date") + " " + map.get("time");
							SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm a"); // Sep 17, 2015 02:44 PM

							Calendar calendar = Calendar.getInstance();

							try
							{
								calendar.setTime(sdf.parse(date));
							} catch (ParseException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							calendar.add(Calendar.MINUTE, -alarm_mins);

							imgAlarm.setImageResource(R.drawable.ic_alarm_on);
							alarmValue.setText("Alarm before " + alarm_mins + " Minutes");

							new AlarmController(ProgramDetailsActivity.this, program_id).setAlarm(calendar);
							StaticValues.showToast(ProgramDetailsActivity.this, "Alarm ON " + sdf.format(calendar.getTime()));

							new DBOperations(ProgramDetailsActivity.this).delete(program_id);
							new DBOperations(ProgramDetailsActivity.this).insert(map);
						}
					}
				});

			}
		});
	}
}
