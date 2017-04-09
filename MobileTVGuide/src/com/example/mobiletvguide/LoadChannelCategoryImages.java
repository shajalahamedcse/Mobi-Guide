package com.example.mobiletvguide;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Button;

public class LoadChannelCategoryImages extends AsyncTask<Object, Object, Object> {

	String requestUrl, folderName, imageName;
	Bitmap bitmap;
	Button btnListener;

	public LoadChannelCategoryImages()
	{

	}

	public LoadChannelCategoryImages(String requestUrl, String folderName, String imageName, Button btnListener)
	{
		// TODO Auto-generated constructor stub

		this.requestUrl = requestUrl;
		this.folderName = folderName;
		this.imageName = imageName;
		this.btnListener = btnListener;

		execute();
	}

	@Override
	protected Object doInBackground(Object... arg0)
	{
		// TODO Auto-generated method stub

		if (checkifImageExists(folderName, imageName)) return null;

		try
		{
			URL url = new URL(requestUrl);
			URLConnection conn = url.openConnection();
			bitmap = BitmapFactory.decodeStream(conn.getInputStream());
		} catch (Exception ex)
		{
			bitmap = null;
		}
		return null;
	}

	@Override
	protected void onPostExecute(Object o)
	{
		if (bitmap != null && !checkifImageExists(folderName, imageName))
		{
			saveToSdCard(bitmap, folderName, imageName);
		}

		btnListener.performClick();
	}

	String saveToSdCard(Bitmap bitmap, String folderName, String imageName)
	{

		String stored = null;

		File sdcard = Environment.getExternalStorageDirectory();

		File folder = new File(sdcard.getAbsoluteFile(), folderName);
		folder.mkdir();

		File file = new File(folder.getAbsoluteFile(), imageName);

		if (file.exists()) return stored;

		try
		{
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
			stored = "success";
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return stored;
	}

	File getImage(String folderName, String imageName)
	{
		File mediaImage = null;

		try
		{
			String root = Environment.getExternalStorageDirectory().toString();
			File myDir = new File(root);

			if (!myDir.exists()) return null;

			mediaImage = new File(myDir.getPath() + "/" + folderName + "/" + imageName);

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mediaImage;
	}

	boolean checkifImageExists(String folderName, String imageName)
	{
		Bitmap bitmap = null;

		File file = getImage(folderName, imageName);
		String path = file.getAbsolutePath();

		if (path != null)
			bitmap = BitmapFactory.decodeFile(path);

		if (bitmap == null || bitmap.equals("")) return false;

		return true;
	}

	public Bitmap getBitmap(String folderName, String imageName)
	{
		Bitmap bitmap = null;

		File file = getImage("/" + folderName, imageName);
		String path = file.getAbsolutePath();

		if (path != null)
		{
			bitmap = BitmapFactory.decodeFile(path);
		}

		return bitmap;
	}

}
