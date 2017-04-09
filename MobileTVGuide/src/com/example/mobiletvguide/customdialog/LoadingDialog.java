package com.example.mobiletvguide.customdialog;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog extends ProgressDialog {

	public LoadingDialog(Context context)
	{
		super(context, ProgressDialog.THEME_HOLO_DARK);
		setMessage("Loading data...");
		setIndeterminate(false);
		setCancelable(false);
	}

}
