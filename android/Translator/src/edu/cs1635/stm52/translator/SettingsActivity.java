package edu.cs1635.stm52.translator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingsActivity extends Activity{
	
	int red, blue, green;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);
		final SeekBar redSeek = (SeekBar) this.findViewById(R.id.RedSeekBar);
		final SeekBar greenSeek = (SeekBar) this.findViewById(R.id.GreenSeekBar);
		final SeekBar blueSeek = (SeekBar) this.findViewById(R.id.BlueSeekBar);
		final Button coloredButton = (Button) this.findViewById(R.id.button1);
		final Button saveColor = (Button) this.findViewById(R.id.button2);
		
		saveColor.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent parent = getIntent();
				parent.putExtra("color", Color.rgb(red, green, blue));
				setResult(RESULT_OK, parent);
				finish();
			}
			
		});
		redSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				red = arg1;
				coloredButton.setBackgroundColor(Color.rgb(red, green, blue));
			}

			public void onStartTrackingTouch(SeekBar arg0) {}
			public void onStopTrackingTouch(SeekBar arg0) {}	
		});
		
		greenSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				green = arg1;
				coloredButton.setBackgroundColor(Color.rgb(red, green, blue));

			}

			public void onStartTrackingTouch(SeekBar arg0) {}
			public void onStopTrackingTouch(SeekBar arg0) {}	
		});
		
		blueSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				blue = arg1;
				coloredButton.setBackgroundColor(Color.rgb(red, green, blue));

			}

			public void onStartTrackingTouch(SeekBar arg0) {}
			public void onStopTrackingTouch(SeekBar arg0) {}	
		});
	}
}
