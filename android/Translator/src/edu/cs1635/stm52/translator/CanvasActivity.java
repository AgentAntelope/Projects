package edu.cs1635.stm52.translator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CanvasActivity extends Activity {
	PaintWidget paint;
	CanvasActivity rel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas);
		rel = this;
		final RelativeLayout rel = (RelativeLayout)findViewById(R.id.rel);
		final Button settings = (Button) findViewById(R.id.Settings);
		final Button clear = (Button) findViewById(R.id.clear);
		final Button transmit = (Button) findViewById(R.id.button1); //TODO: Stop being lazy.


		//TODO: Clean up?
		DisplayMetrics metrics = new DisplayMetrics();
		((WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE))
		  .getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;

		paint = new PaintWidget(this, width, 800);
		rel.addView(paint, 0);

		settings.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent setters = new Intent(arg0.getContext(), SettingsActivity.class);
				startActivityForResult(setters, 1);
			}
		});

		transmit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				transmit.setEnabled(false);
				transmit.setText("Sending..");
				sendData();

			}
		});

		clear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				paint.clear();
			}
		});
	}

	public void sendData(){
		
		new HttpAsyncTask().execute(paint.encode());
		   // Create a new HttpClient and Post Header

	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {

		    try {
		        // Add your data
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://cwritepad.appspot.com/reco/usen");

		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("key", "11773edfd643f813c18d82f56a8104ed"));
		        nameValuePairs.add(new BasicNameValuePair("q", params[0]));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        byte[] buffer = new byte[200];
		        int readBytes = 0;
		        StringBuilder builder = new StringBuilder();
                do{
		        	readBytes = response.getEntity().getContent().read(buffer);
		        	String built = new String(buffer, 0, readBytes);
		        	builder.append(built);
		        }
		        while(readBytes == 200);
		        return builder.toString();
		    } catch (ClientProtocolException e) {
		    } catch (IOException e) {
		    }catch(Exception e){}
		    return "Can't connect with server at this time, please try again later.";

		}

		 protected void onPostExecute(String result) {
		        new AlertDialog.Builder(rel).setTitle("Results!").setMessage(result).setNeutralButton("Close", null).show();
				final Button transmit = (Button) findViewById(R.id.button1); //TODO: Stop being lazy.
				transmit.setEnabled(true);
				transmit.setText("Transmit");

		 }


	}

	protected void onRestoreInstanceState(Bundle notInUSe){
		super.onRestoreInstanceState(notInUSe);

	}
	protected void onResume(){
		super.onResume();

	}

	protected void onPause(){
		super.onPause();
	}

	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_canvas, menu);

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		//Get the data hoes!!
		super.onActivityResult(requestCode, resultCode, data);
		if(data != null && data.getExtras().containsKey("color")){
			int color = data.getIntExtra("color", Color.RED);
	        paint.setColor(color);
		}

	}
}
