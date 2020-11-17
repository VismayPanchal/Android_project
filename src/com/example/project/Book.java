package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Book extends Activity {
	//Bitmap imgArr;
	TextView bname,bauth,publi,course;
	ImageView iv;
	Button rd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		Bundle extras = getIntent().getExtras();
		String key=extras.getString("url");
		Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();
		
		bname=(TextView) findViewById(R.id.book_name);
		bauth=(TextView) findViewById(R.id.book_auth);
		course=(TextView) findViewById(R.id.course);
		publi =(TextView) findViewById(R.id.book_publ);
		iv=(ImageView) findViewById(R.id.imageView1);
		rd = (Button) findViewById(R.id.read);
		new FetchBook(key).execute();
		
		rd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String bnm=bname.getText().toString();
				//Toast.makeText(getApplicationContext(), bnm, Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getApplicationContext(),ReadBook.class);
				i.putExtra("name", bnm);
				
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
		return true;
	}



public class FetchBook extends AsyncTask<Object, Object, Object> {
String  search,name,cours,publ,auth,img;
Bitmap imgArr;

	public FetchBook(String a)
	{
		search=a;
	}
	@Override
	protected Void doInBackground(Object... params) {
		// TODO Auto-generated method stub

		String url = "http://10.0.2.2/project_tutor/fetch_img.php";
		//Log.e("url",srch);
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		StringBuilder sb = new StringBuilder();

		List<NameValuePair> nameValues=new ArrayList<NameValuePair>();
		nameValues.add(new BasicNameValuePair("id",search));
		try {

			post.setEntity(new UrlEncodedFormEntity(nameValues));
			HttpResponse response= client.execute(post);

			HttpEntity httpEntity= response.getEntity();
			
			response = client.execute(post);
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			Log.e("niga","nigah");
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			Log.e("Data of fetched::::::>>>>>", sb.toString());
			JSONObject Jsonobject = new JSONObject(sb.toString());
			JSONArray jsonArray = Jsonobject.getJSONArray("products");
			int len = jsonArray.length();
			
			
			

			String url_map = "http://10.0.2.2/project_tutor/";
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject temp = jsonArray.getJSONObject(i);

				Log.e("PRODUCTS::", temp.toString());
				name = temp.getString("book_name");
				img = url_map + temp.getString("book_image");
				Log.e("Image:: for book", img);
				auth = temp.getString("book_auth");
				cours = temp.getString("course_name");
				publ = temp.getString("book_publication");
			
				Log.e("name",name);
				Log.e("Author",auth);
				Log.e("publication",publ);
				Log.e("course",cours);
				
				URL url1=new URL(img);
				Bitmap image1 = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
				imgArr=image1;
				
				
				
				
			}
			Log.e("Image Array::", imgArr+"");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return sb.toString();
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		//Image cus =  new Image(SearchActivity.this, name, auth, course,publ,imgArr);
		
//		lst.setAdapter(cus);
		bname.setText(name);
		bauth.setText(auth);
		course.setText(cours);
		publi.setText(publ);
		iv.setImageBitmap(imgArr);
		
		
		//Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
	}
}
}
