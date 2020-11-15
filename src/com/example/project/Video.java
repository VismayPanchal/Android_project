package com.example.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Video extends ArrayAdapter<String> {

	final String[] name,course;
	final Context context;
	TextView itemname, itemcourse;
	ImageView img;
	Bitmap image;
	Bitmap[] imgArray;

	public Video(Context context, String[] name, String[] couse, Bitmap[] imgArr) {
		// TODO Auto-generated constructor stub

		super(context, R.layout.activity_video, name);
	
		this.context = context;
		this.name = name;
		
		this.course = couse;
		
		this.imgArray=imgArr;

	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_video, null);
		}
	/*	URL url;
		try {
			url = new URL(p_img[position]);
			image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		itemname = (TextView) convertView.findViewById(R.id.video_name);
		itemcourse = (TextView) convertView.findViewById(R.id.course);
		//itempubl = (TextView) convertView.findViewById(R.id.book_publ);
		img = (ImageView) convertView.findViewById(R.id.imageView1);
		
		
		itemname.setText("Book name:"+name[position]);
		itemcourse.setText("Book Author:"+course[position]);
		
		img.setImageBitmap(imgArray[position]);

		return convertView;
	}

	
}
