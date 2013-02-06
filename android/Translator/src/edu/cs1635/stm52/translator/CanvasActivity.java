package edu.cs1635.stm52.translator;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CanvasActivity extends Activity {
	PaintWidget paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_canvas);
		paint = new PaintWidget(this);

		final RelativeLayout rel = (RelativeLayout)findViewById(R.id.rel);
		final Button settings = (Button) findViewById(R.id.Settings);
		final Button clear = (Button) findViewById(R.id.clear);
		rel.addView(paint, 0);
		
		settings.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent setters = new Intent(arg0.getContext(), SettingsActivity.class);
				startActivityForResult(setters, 1);
			}
		});
		
		clear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				paint.clear();
			}
		});
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
		if(data.getExtras().containsKey("color")){
			int color = data.getIntExtra("color", Color.RED);
	        paint.setColor(color);
		}

	}
}
