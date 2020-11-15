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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
EditText uname,pass,email;
Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=(EditText) findViewById(R.id.uname);
        pass=(EditText) findViewById(R.id.pass);
        email=(EditText) findViewById(R.id.email);
        submit=(Button) findViewById(R.id.signin);
        
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String name=uname.getText().toString();
				final String passwd=pass.getText().toString();
				final String mail=email.getText().toString();
				new EnterUser(name,mail,passwd).execute();
				
			}
		});
    }

    public class EnterUser extends AsyncTask<Object, Object, Object>
    {
    	String name,mail,pass;
    	public EnterUser(String a,String b,String c)
    	{
    		name=a;
    		mail=b;
    		pass=c;
    		
    	}
		@Override
		protected Object doInBackground(Object... params) {
			
			// TODO Auto-generated method stub
			String url="http://10.0.2.2/project_tutor/usermaster_ins.php";
//			http://10.0.2.2/
			List<NameValuePair> nameValues=new ArrayList<NameValuePair>();
			nameValues.add(new BasicNameValuePair("name",name));
			nameValues.add(new BasicNameValuePair("mail",mail));
			nameValues.add(new BasicNameValuePair("pass",pass));
			Log.e("name",name);
			Log.e("mail",mail);
			Log.e("pass",pass);
			
			HttpClient client=new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			
			try {
				post.setEntity(new UrlEncodedFormEntity(nameValues));
				HttpResponse response= client.execute(post);
	
				HttpEntity httpEntity= response.getEntity();
				
				Log.e("after","wait");
				
				
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
			Log.e("Try","msg");
			return "inserte";
		}
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
		}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
