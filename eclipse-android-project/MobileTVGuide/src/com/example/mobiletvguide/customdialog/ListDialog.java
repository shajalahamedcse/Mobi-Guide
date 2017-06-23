package com.example.mobiletvguide.customdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.mobiletvguide.StaticValues;

public class ListDialog {

	public int selectedPos;

	public ListDialog(Context context, final Button btnListener)
	{
		// TODO Auto-generated constructor stub

		AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
		builderSingle.setTitle("Set Alarm");

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, StaticValues.alarm_list);

		builderSingle.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.dismiss();
					}
				});

		builderSingle.setAdapter(arrayAdapter,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						selectedPos = which;
						btnListener.performClick();
					}
				});
		builderSingle.show();

	}
}
