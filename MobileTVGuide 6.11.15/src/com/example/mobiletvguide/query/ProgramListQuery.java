package com.example.mobiletvguide.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;

import com.example.mobiletvguide.StaticValues;
import com.example.mobiletvguide.customdialog.LoadingDialog;
import com.example.mobiletvguide.database.DBOperations;
import com.example.mobiletvguide.util.JSONParser;

public class ProgramListQuery extends AsyncTask<String, String, String> {

	String url = StaticValues.server + "program_list.php";

	public int success;
	public ArrayList<HashMap<String, String>> data_list = new ArrayList<HashMap<String, String>>();

	Button btnListener;
	LoadingDialog loadingDialog;

	List<NameValuePair> params;

	Context context;

	public ProgramListQuery(Context context, Button btnListener, List<NameValuePair> params)
	{
		this.btnListener = btnListener;
		this.params = params;
		this.context = context;
		loadingDialog = new LoadingDialog(context);
		execute();
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		loadingDialog.show();
	}

	protected String doInBackground(String... args)
	{
		JSONParser jParser = new JSONParser();
		JSONArray data = null;

		try
		{
			JSONObject json = jParser.makeHttpRequest(url, "GET", params);

			success = json.getInt("success");

			if (success == 1)
			{
				data = json.getJSONArray("data");

				for (int i = 0; i < data.length(); i++)
				{
					JSONObject obj = data.getJSONObject(i);

					HashMap<String, String> map = new HashMap<String, String>();

					map.put("program_id", obj.getString("program_id"));
					map.put("program_name", obj.getString("program_name"));
					map.put("channel_id", obj.getString("channel_id"));
					map.put("channel_name", obj.getString("channel_name"));
					map.put("category_id", obj.getString("category_id"));
					map.put("category_name", obj.getString("category_name"));
					map.put("date", obj.getString("date"));
					map.put("time", obj.getString("time"));
					map.put("day", obj.getString("day"));

					String alarm_mins = new DBOperations(context).getAlarmStatus(Integer.parseInt(map.get("program_id")));
					map.put("alarm_mins", alarm_mins);

					data_list.add(map);

				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			success = 0;
		}

		return null;
	}

	protected void onPostExecute(String file_url)
	{
		loadingDialog.dismiss();
		btnListener.performClick();
	}

}
