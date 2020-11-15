package com.example.project;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class Image extends ArrayAdapter<String>  {


	final String[] name,auth,course,publ;
	final Context context;
	TextView itemname, itempubl, itemauth,tex1,tex2,tex3;
	ImageView img;
	Bitmap image;
	Bitmap[] imgArray;

	public Image(Context context, String[] name, String[] auth, String[] couse, String[] publ, Bitmap[] imgArr) {
		// TODO Auto-generated constructor stub

		super(context, R.layout.activity_image, name);
	
		this.context = context;
		this.name = name;
		this.auth = auth;
		this.course = couse;
		this.publ = publ;
		this.imgArray=imgArr;

	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_image, null);
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
		itemname = (TextView) convertView.findViewById(R.id.book_name);
		itemauth = (TextView) convertView.findViewById(R.id.book_auth);
		itempubl = (TextView) convertView.findViewById(R.id.book_publ);
		img = (ImageView) convertView.findViewById(R.id.imageView1);
		
		
		itemname.setText("Book name:"+name[position]);
		itemauth.setText("Book Author:"+auth[position]);
		itempubl.setText("Publication:"+publ[position]);
		img.setImageBitmap(imgArray[position]);

		return convertView;
	}

	
}
