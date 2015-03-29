package com.example.homeautomation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Aboutdeveloper extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about1);
		TextView text = (TextView) findViewById(R.id.text1);
		text.setTextColor(0xff00ff00);
	
	}

	
}
