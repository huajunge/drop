package com.cog.DropInn;


import com.cog.DropInn.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class Listspace extends Activity{

	

String userid,summary;

String entire_room="Entire Home";
String private_room="Private room";
String shared_room="Shared room";

	String title1="Nice ! What type of place is your private room Located in ?";
	String title2="Nice ! What type of place is your shared room Located in ?";
	String title3="Nice ! What type of place is your entire home Located in ?";
	
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listspace);
		getActionBar().hide();
		
		
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		/*summary=i.getStringExtra("writesum");*/
		System.out.println("listspace userid"+userid);
		
		ImageButton back=(ImageButton)findViewById(R.id.imageButton1);	
		ImageView entireroom=(ImageView)findViewById(R.id.imageView3);
		ImageView privateroom=(ImageView)findViewById(R.id.imageView4);
		ImageView shareroom=(ImageView)findViewById(R.id.ImageView01);
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		
		
		//*************************************************more options*********************************
		
				
		entireroom.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent room=new Intent(Listspace.this,Propertytype.class);
				room.putExtra("title",title3);
				room.putExtra("userid",userid);
				room.putExtra("room_type", entire_room);
				//room.putExtra("room_type", summary);

				 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Listspace.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(room,bndlanimation);
				finish();
			}
		});
		
		privateroom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent room1=new Intent(Listspace.this,Propertytype.class);
				room1.putExtra("title", title1);
				room1.putExtra("userid",userid);
				room1.putExtra("room_type",private_room);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Listspace.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(room1,bndlanimation);
				finish();
			}
		});
		
		shareroom.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent room2=new Intent(Listspace.this,Propertytype.class);
				room2.putExtra(" ", title2);
				room2.putExtra("userid",userid);
				room2.putExtra("room_type",shared_room);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Listspace.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(room2,bndlanimation);
				finish();
			}
		});
		
		
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back1=new Intent(Listspace.this,Swipe_tabs.class);
				back1.putExtra("userid",userid);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Listspace.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(back1,bndlanimation);
				finish();
			}
		});
		
	}
	/*@Override
	public void onBackPressed()
	{
		Intent back1=new Intent(Listspace.this,Swipe_tabs.class);
		back1.putExtra("userid",userid);
		Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Listspace.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
		startActivity(back1,bndlanimation);
		finish();
		
	}*/
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
