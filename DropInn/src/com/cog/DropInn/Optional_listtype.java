package com.cog.DropInn;



import com.cog.DropInn.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Optional_listtype extends Activity {

	String home1="Entire Room";
	String privateroom1="Private Room";
	String sharedroom1="Shared Room";
	String userid;
	
	ImageView optional_home,optional_privateroom,optional_sharedroom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.optional_listtype);
		
		optional_home=(ImageView)findViewById(R.id.imageView3);
		optional_privateroom=(ImageView)findViewById(R.id.imageView4);
		optional_sharedroom=(ImageView)findViewById(R.id.ImageView01);
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		
		optional_home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent home=new Intent(Optional_listtype.this,Optional_rooms_beds.class);
				home.putExtra("home",home1 );
				startActivity(home);
				finish();
			}
		});
		
optional_privateroom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent privateroom=new Intent(Optional_listtype.this,Optional_rooms_beds.class);
				privateroom.putExtra("privateroom",privateroom1 );
				startActivity(privateroom);
				finish();
			}
		});
		
		
optional_sharedroom.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent sharedroom=new Intent(Optional_listtype.this,Optional_rooms_beds.class);
		sharedroom.putExtra("sharedroom",sharedroom1 );
		startActivity(sharedroom);
		finish();
	}
});

	}

	@Override
	public void onBackPressed()
	{
		
	}	
	public boolean isOnline(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c
		.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		 
		if (ni != null && ni.isConnected())
		  return true;
		else
		  return false;
		}	
}
