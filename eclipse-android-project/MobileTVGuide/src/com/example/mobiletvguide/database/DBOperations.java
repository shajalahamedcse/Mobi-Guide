package com.example.mobiletvguide.database;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBOperations {

	Context context;
	SharedPreferences pref;

	public DBOperations(Context context)
	{
		// TODO Auto-generated constructor stub

		this.context = context;
		pref = context.getSharedPreferences("database_status", 0);

		String status = pref.getString("database_created", "no");

		if (status.equals("no")) //Database not created
		{
			createDatabase();

			SharedPreferences.Editor editor = pref.edit();
			editor.putString("database_created", "yes");
			editor.commit();
		}
	}

	public void createDatabase()
	{
		SQLiteDatabase db = context.openOrCreateDatabase("program_reminder_db", Context.MODE_PRIVATE, null);
		String sql = "CREATE TABLE IF NOT EXISTS program_info (program_id INTEGER, program_name VARCHAR, channel_id INTEGER, channel_name VARCHAR, category_name VARCHAR, date VARCHAR, time VARCHAR, day VARCHAR, alarm_mins INTEGER);";
		db.execSQL(sql);
		db.close();
	}

	public void insert(HashMap<String, String> map)
	{
		SQLiteDatabase db = context.openOrCreateDatabase("program_reminder_db", Context.MODE_PRIVATE, null);

		String sql = "INSERT INTO program_info VALUES(" +
				map.get("program_id") + "," +
				"'" + map.get("program_name") + "'," +
				map.get("channel_id") + "," +
				"'" + map.get("channel_name") + "'," +
				"'" + map.get("category_name") + "'," +
				"'" + map.get("date") + "'," +
				"'" + map.get("time") + "'," +
				"'" + map.get("day") + "'," +
				"'" + map.get("alarm_mins") + "');";

		db.execSQL(sql);
		db.close();
	}

	public HashMap<String, String> singleQuery(int program_id)
	{
		HashMap<String, String> map = new HashMap<String, String>();

		SQLiteDatabase db = context.openOrCreateDatabase("program_reminder_db", Context.MODE_PRIVATE, null);

		String sql = "SELECT * FROM program_info WHERE program_id = " + program_id + ";";

		Cursor cursor = db.rawQuery(sql, null);

		if (cursor.getCount() >= 1)
		{
			cursor.moveToFirst();

			do
			{
				map.put("program_id", cursor.getString(cursor.getColumnIndex("program_id")));
				map.put("program_name", cursor.getString(cursor.getColumnIndex("program_name")));
				map.put("channel_id", cursor.getString(cursor.getColumnIndex("channel_id")));
				map.put("channel_name", cursor.getString(cursor.getColumnIndex("channel_name")));
				map.put("category_name", cursor.getString(cursor.getColumnIndex("category_name")));
				map.put("date", cursor.getString(cursor.getColumnIndex("date")));
				map.put("time", cursor.getString(cursor.getColumnIndex("time")));
				map.put("day", cursor.getString(cursor.getColumnIndex("day")));
				map.put("alarm_mins", cursor.getString(cursor.getColumnIndex("alarm_mins")));

			} while (cursor.moveToNext());
		}

		db.close();

		return map;
	}

	public ArrayList<HashMap<String, String>> query()
	{
		ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();

		SQLiteDatabase db = context.openOrCreateDatabase("program_reminder_db", Context.MODE_PRIVATE, null);

		String sql = "SELECT * FROM program_info;";

		Cursor cursor = db.rawQuery(sql, null);

		if (cursor.getCount() >= 1)
		{
			cursor.moveToFirst();

			do
			{
				HashMap<String, String> map = new HashMap<String, String>();

				map.put("program_id", cursor.getString(cursor.getColumnIndex("program_id")));
				map.put("program_name", cursor.getString(cursor.getColumnIndex("program_name")));
				map.put("channel_id", cursor.getString(cursor.getColumnIndex("channel_id")));
				map.put("channel_name", cursor.getString(cursor.getColumnIndex("channel_name")));
				map.put("category_name", cursor.getString(cursor.getColumnIndex("category_name")));
				map.put("date", cursor.getString(cursor.getColumnIndex("date")));
				map.put("time", cursor.getString(cursor.getColumnIndex("time")));
				map.put("day", cursor.getString(cursor.getColumnIndex("day")));
				map.put("alarm_mins", cursor.getString(cursor.getColumnIndex("alarm_mins")));

				data_list.add(map);

			} while (cursor.moveToNext());
		}

		db.close();

		return data_list;
	}

	public void delete(int program_id)
	{
		SQLiteDatabase db = context.openOrCreateDatabase("program_reminder_db", Context.MODE_PRIVATE, null);

		String sql = "DELETE FROM program_info WHERE program_id = " + program_id + ";";

		db.execSQL(sql);
		db.close();
	}

	public String getAlarmStatus(int program_id)
	{
		String alarm_mins = "NO_ALARM";

		SQLiteDatabase db = context.openOrCreateDatabase("program_reminder_db", Context.MODE_PRIVATE, null);
		String sql = "SELECT alarm_mins FROM program_info WHERE program_id = " + program_id + ";";
		Cursor cursor = db.rawQuery(sql, null);

		if (cursor.getCount() >= 1)
		{
			cursor.moveToFirst();
			alarm_mins = cursor.getString(cursor.getColumnIndex("alarm_mins"));
		}

		db.close();
		return alarm_mins;
	}

}
