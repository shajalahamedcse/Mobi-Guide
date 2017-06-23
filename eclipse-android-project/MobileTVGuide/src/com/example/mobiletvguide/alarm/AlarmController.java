package com.example.mobiletvguide.alarm;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmController {

	AlarmManager manager;
	PendingIntent pending;
	Context context;

	public AlarmController(Context context, int program_id)
	{
		this.context = context;

		manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		Intent intent = new Intent(context, AlarmReceiver.class);
		intent.putExtra("program_id", program_id);

		pending = PendingIntent.getBroadcast(context, program_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public void setAlarm(Calendar calendar)
	{
		manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
	}

	public void cancelAlarm()
	{
		manager.cancel(pending);
	}

}
