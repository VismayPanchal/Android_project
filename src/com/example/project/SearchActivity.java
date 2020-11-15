package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
public class SearchActivity extends Activity {

	ListView lst;
	String[] name, auth, publ, course,img;
	EditText searchtxt;
	SearchView Sv;
	String srch="";
	Button video,book,srh;
	
	Bitmap imgArr[];
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		lst = (ListView) findViewById(R.id.listView1);
		video = (Button) findViewById(R.id.video);
		searchtxt= (EditText) findViewById(R.id.searchTxt);
		book = (Button) findViewById(R.id.book);
		srh= (Button) findViewById(R.id.search);
		final String URL="http://10.0.2.2/project_tutor/";
		lst.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				String fetched="";
				
				fetched= (String) arg0.getAdapter().getItem(arg2);
				
//				fetched=lst.getSelectedItem().toString();
				Toast.makeText(getApplicationContext(), fetched, Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),VideoPlay.class);
//				intent=getIntent(SearchActivity.this,VideoPlay.class);
				intent.putExtra("url", URL+fetched);
				
				startActivity(intent);
			}

			
		});
		srh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 srch=searchtxt.getText().toString();
				
				new FetchBookDetails(srch).execute();
				
			}
		});
		
		video.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				srch=searchtxt.getText().toString();
				
				new FetchVideoDetails(srch).execute();
				
			}
		});
	}
	public class FetchVideoDetails extends AsyncTask<Object, Object, Object> {
		String  search;
			public FetchVideoDetails(String a)
			{
				search=a;
			}
			@Override
			protected Object doInBackground(Object... params) {
				// TODO Auto-generated method stub

				String url = "http://10.0.2.2/project_tutor/fetch_video.php";
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
					Log.e("try","try");
					
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					Log.e("Data::::::>>>>>", sb.toString());
					JSONObject Jsonobject = new JSONObject(sb.toString());
					JSONArray jsonArray = Jsonobject.getJSONArray("products");
					int len = jsonArray.length();
					name = new String[len];
					img = new String[len];
					course = new String[len];
					
					imgArr=new Bitmap[len];

					String url_map = "http://10.0.2.2/project_tutor/";
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject temp = jsonArray.getJSONObject(i);

						Log.e("PRODUCTS::", temp.toString());
						name[i] = temp.getString("video_name");
						img[i] = url_map + temp.getString("video_thumb");
						Log.e("Image::", img[i]);

						course[i] = temp.getString("course_name");

						
						URL url1=new URL(img[i]);
						Bitmap image1 = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
						imgArr[i]=image1;
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
				return sb.toString();
			}

			@Override
			protected void onPostExecute(Object result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				Video cus =  new Video(SearchActivity.this, name, course,imgArr);
				
				lst.setAdapter(cus);
				
				
				//Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
			}
		}
	


	public class FetchBookDetails extends AsyncTask<Object, Object, Object> {
	String  search;
		public FetchBookDetails(String a)
		{
			search=a;
		}
		@Override
		protected Object doInBackground(Object... params) {
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
				Log.e("try","try");
				
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				Log.e("Data::::::>>>>>", sb.toString());
				JSONObject Jsonobject = new JSONObject(sb.toString());
				JSONArray jsonArray = Jsonobject.getJSONArray("products");
				int len = jsonArray.length();
				name = new String[len];
				img = new String[len];
				course = new String[len];
				auth = new String[len];
				publ = new String[len];
				
				imgArr=new Bitmap[len];

				String url_map = "http://10.0.2.2/project_tutor/";
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject temp = jsonArray.getJSONObject(i);

					Log.e("PRODUCTS::", temp.toString());
					name[i] = temp.getString("book_name");
					img[i] = url_map + temp.getString("book_image");
					Log.e("Image::", img[i]);
					auth[i] = temp.getString("book_auth");
					course[i] = temp.getString("course_name");
					publ[i] = temp.getString("book_publication");
					
					URL url1=new URL(img[i]);
					Bitmap image1 = BitmapFactory.decodeStream(url1.openConnection().getInputStream());
					imgArr[i]=image1;
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
			return sb.toString();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Image cus =  new Image(SearchActivity.this, name, auth, course,publ,imgArr);
			
			lst.setAdapter(cus);
			
			
			//Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
