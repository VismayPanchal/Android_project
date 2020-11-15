package com.example.project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlay extends Activity {
	ProgressDialog pDialog;
    VideoView videoview;
   String vid_url;//="https://10.0.2.2/project_tutor/face.3gp";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_play);
		Bundle extras=getIntent().getExtras();
		vid_url=extras.getString("url");
		
		Intent intent = getIntent();
	       // String vid_url = intent.getStringExtra("video_url");
	        videoview = (VideoView) findViewById(R.id.videoView);
	        pDialog = new ProgressDialog(this);
	        pDialog.setTitle("Video Stream");
	        pDialog.setMessage("Buffering...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(true);
	        pDialog.show();
	        try {
	            MediaController mediacontroller = new MediaController(this);
	            mediacontroller.setAnchorView(videoview);
	            Uri video = Uri.parse(vid_url);
	            videoview.setMediaController(mediacontroller);
	            videoview.setVideoURI(video);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        videoview.requestFocus();
	        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
	            public void onPrepared(MediaPlayer mp) {
	                VideoPlay.this.pDialog.dismiss();
	                VideoPlay.this.videoview.start();
	            }
	        });
	        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
	            @Override
	            public void onCompletion(MediaPlayer mp) {
	               finish();
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video_play, menu);
		return true;
	}

}
