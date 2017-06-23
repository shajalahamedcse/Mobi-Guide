package com.example.mobiletvguide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class StaticValues {

	public static String server = "http://localhost:8080/tv_program_server/";

	public static String programListUrl = server + "program_list.php";
	public static String channelListUrl = server + "channel_list.php";
	public static String categoryListUrl = server + "category_list.php";
	public static String topChartListUrl = server + "top_chart_list.php";
	public static String nowNextListUrl = server + "now_next_list.php";

	public static String rootFolder = ".MobileTVGuide";
	public static String channelFolder = ".MobileTVGuide/channel_images";
	public static String categoryFolder = ".MobileTVGuide/category_images";

	public static String[] channel_list = { "Select Date: All", "Today", "Tomorrow" };
	public static String[] alarm_list = { "None", "30 mins", "1 hour", "2 hours", "3 hours", "6 hours" };
	public static int[] alarm_value_mins = { -1, 30, 60, 120, 180, 360 };

	public static ArrayList<String> channelListColumns = new ArrayList<String>(Arrays.asList("channel_id", "channel_name"));
	public static ArrayList<String> categoryListColumns = new ArrayList<String>(Arrays.asList("category_id", "category_name"));
	public static ArrayList<String> topChartListColumns = new ArrayList<String>(Arrays.asList("type", "rank", "name"));

	public static ArrayList<String> programListColumns = new ArrayList<String>(
			Arrays.asList("program_id", "program_name", "channel_id", "channel_name",
					"category_id", "category_name", "date", "time", "day"));

	public static ArrayList<String> nowNextListColumns = new ArrayList<String>(
			Arrays.asList("program_id", "program_name", "channel_id", "channel_name",
					"category_id", "category_name", "date", "time", "day",
					"program_id_next", "program_name_next", "category_id_next", "category_name_next",
					"date_next", "time_next", "day_next"));

	public static ArrayList<HashMap<String, String>> channel_data_list;
	public static ArrayList<HashMap<String, String>> category_data_list;
	public static ArrayList<HashMap<String, String>> top_chart_data_list;

	public static void showToast(Context context, String msg)
	{
		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();

	}

}
