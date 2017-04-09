package com.example.mobiletvguide.alarm;

import java.util.HashMap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.mobiletvguide.R;
import com.example.mobiletvguide.database.DBOperations;

public class AlarmReceiver extends BroadcastReceiver {

	Context context;
	HashMap<String, String> map;
	int program_id;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub

		this.context = context;
		program_id = intent.getIntExtra("program_id", 0);

		map = new DBOperations(context).singleQuery(program_id);
		new DBOperations(context).delete(program_id);

		showNotification();
	}

	public void showNotification()
	{
		Intent intent = new Intent(context, NotificationViewActivity.class);

		intent.putExtra("program_name", map.get("program_name"));
		intent.putExtra("channel_name", map.get("channel_name"));
		intent.putExtra("category_name", map.get("category_name"));
		intent.putExtra("date", map.get("date"));
		intent.putExtra("time", map.get("time"));
		intent.putExtra("day", map.get("day"));

		PendingIntent pending = PendingIntent.getActivity(context, program_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		String title = map.get("program_name");
		String text = "On " + map.get("channel_name") + " At " + map.get("time");

		Notification notification = new NotificationCompat.Builder(context)

				.setContentTitle(title)
				.setContentText(text)
				.setTicker(context.getString(R.string.app_name))
				.setSmallIcon(R.drawable.ic_notification)
				.setSound(soundUri)
				.setContentIntent(pending)
				.build();

		notification.flags |= Notification.FLAG_AUTO_CANCEL; // hide the notification after it was selected

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(program_id, notification);
	}
}
