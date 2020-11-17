package com.example.project;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
//import android.media.MediaRouter.Callback;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class ReadBook extends Activity {
WebView webview;
ProgressBar progressbar;

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_book);

		Bundle extras = getIntent().getExtras();
		String key=extras.getString("name");
		
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        
       webview=(WebView)findViewById(R.id.webView);
         
        webview.setWebViewClient(new Callback());

        
        //webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptEnabled(true);
        //webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.setWebChromeClient(new WebChromeClient());

        webview.setWebViewClient(new Callback());
        String URLPATH="";
        //String URLPATH = "https://www.tutorialspoint.com/css/css_tutorial.pdf";
        //String URLPATH = "http://10.0.2.2/project_tutor/Big_data.pdf";
        String Big_data= "https://drive.google.com/file/d/17R2QsbBDf3gbxWGiAflw08cUsbKOKqmn/view";
        String SE="https://drive.google.com/file/d/11AfLdVTQXUsh39mdfceNbZ1QsU0UwaKr/view?usp=sharing";
        String CG="https://drive.google.com/file/d/1l5FhWbNuaDoRX4iLTPQjPdo0oiwNfDsr/view?usp=sharing";
        String DCN="https://drive.google.com/file/d/1iFN00V065YhA-zaTLxcv_VCyxXeRVkRV/view?usp=sharing";
        String ECOM="https://drive.google.com/file/d/1HK8T5epUlcDMV3bk4tl84bbR4rORUpJR/view?usp=sharing";
        
        if(key.equalsIgnoreCase("data mining concepts"))
        	URLPATH=Big_data;
        else if(key.equalsIgnoreCase("Software engineering"))
        	URLPATH=SE;
        else if(key.equalsIgnoreCase("Computer graphics c ver"))
        	URLPATH=CG;
        else if(key.equalsIgnoreCase("Data communication"))
        	URLPATH=DCN;
        else if(key.equalsIgnoreCase("Electronic commerce"))
        	URLPATH=ECOM;
        
        //String URLPATH="";
        //sURLPATH=SE;
      //  String url="";
		//url = "http://docs.google.com/viewer?url="+Big_data;
		
      
        webview.loadUrl(URLPATH);
        
        
        
        //String url ="http://10.0.2.2/project_tutor/android_reference.pdf";
        //String url = Uri.encode("http://192.168.43.160/project_tutor/android_reference.pdf");
        //String finalUrl = "http://docs.google.com/viewer?url=" + url + "&embedded=true";
        
        //webView.loadUrl("https://docs.google.com/viewer?url=" + url + "&embedded=true");
		//webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + URLEncoder.encode(url, "UTF-8"));
        
        //webview.loadUrl("https://docs.google.com/gview?embedded=true&url=" + filename);

        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressbar.setVisibility(View.GONE);
            }
        });
	}
       public class Callback extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view, String url) {
                return(false);
            }
        }
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_book, menu);
		return true;
	}

}
