package com.example.mobiletvguide.alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobiletvguide.R;

public class NotificationViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_view);

		TextView program = (TextView) findViewById(R.id.program);
		TextView channel = (TextView) findViewById(R.id.channel);
		TextView time = (TextView) findViewById(R.id.time);
		TextView date = (TextView) findViewById(R.id.date);
		Button btnOk = (Button) findViewById(R.id.btnOk);

		Intent intent = getIntent();

		program.setText(intent.getStringExtra("program_name"));
		channel.setText("On " + intent.getStringExtra("channel_name"));
		time.setText("At " + intent.getStringExtra("time"));
		date.setText(intent.getStringExtra("date") + " (" + intent.getStringExtra("day") + ")");

		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

				finish();
			}
		});

	}
}
