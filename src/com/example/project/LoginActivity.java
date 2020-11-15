package com.example.project;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
EditText email,passwd;
Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		email=(EditText) findViewById(R.id.email);
		passwd=(EditText) findViewById(R.id.pass);
		login=(Button) findViewById(R.id.login);
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String mail=email.getText().toString();
				final String pass=passwd.getText().toString();
				new FetchUser(mail,pass).execute();
			}
		});
	}
	
	public class FetchUser extends AsyncTask<Object, Object, Object>
	{
		String mail,pass;
		
		public FetchUser(String a,String b)
		{
			mail=a;
			pass=b;
		}
		
		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			String url="http://10.0.2.2/project_tutor/login.php";
			List<NameValuePair> nameValues=new ArrayList<NameValuePair>();
			
			nameValues.add(new BasicNameValuePair("mail",mail));
			nameValues.add(new BasicNameValuePair("pass",pass));
			HttpClient client=new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			
			try {
				post.setEntity(new UrlEncodedFormEntity(nameValues));
				HttpResponse response= client.execute(post);
	
				HttpEntity httpEntity= response.getEntity();
				
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "return";
		}
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(), (CharSequence) result, Toast.LENGTH_LONG).show();
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
