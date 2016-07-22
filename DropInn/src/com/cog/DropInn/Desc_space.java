package com.cog.DropInn;

import android.annotation.SuppressLint;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Desc_space extends Activity {
	String dis,dis1,dis2,dis3,dis4,dis5,sp,gguest;
	String wspace,wguest,wguestint,woverview,waround,wother,wrules;
	EditText space;
	ImageView done;
	String latitude1,longitude1;
	String userid,diss,dis11,dis22,imaage,address1,city,state,country,room_id,image1,resize,getspace;
	String step,check;
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_desc_space);
		getActionBar().hide();
		
		
		space=(EditText)findViewById(R.id.editText1);		
		done=(ImageView)findViewById(R.id.imageView2);
		
		
		
		Intent i =getIntent();
		wspace=i.getStringExtra("write_space");
	    space.setText(wspace);
	    wguest=i.getStringExtra("write_guest");
	    wguestint=i.getStringExtra("write_guestint");
	    woverview=i.getStringExtra("write_overview");
	    waround=i.getStringExtra("write_around");
	    wother=i.getStringExtra("write_other");
        wrules=i.getStringExtra("write_rules");
        step=i.getStringExtra("step");
        check=i.getStringExtra("check");
	    
	   // userid=i.getStringExtra("userid");
		room_id=i.getStringExtra("room_id");
		imaage=i.getStringExtra("image");
		image1=i.getStringExtra("image1");
		resize=i.getStringExtra("resize");
		diss=i.getStringExtra("title");
		dis11=i.getStringExtra("summary");
		dis22=i.getStringExtra("price");			
		address1=i.getStringExtra("addresss");			
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country=i.getStringExtra("country");
	    latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
		
		   SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);

		
		done.setOnClickListener(new View.OnClickListener() {
			
		@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
			public void onClick(View v) {
			wspace=space.getText().toString();
			
			
				Intent back=new Intent(Desc_space.this,Writedescription.class);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Desc_space.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				    
			
				    back.putExtra("room_id",room_id);
					back.putExtra("userid",userid);
					back.putExtra("image", imaage);
					back.putExtra("image1", image1);
					back.putExtra("resize", resize);
					back.putExtra("title",diss);				
					back.putExtra("summary", dis11);
					back.putExtra("price",dis22);
					back.putExtra("city", city);
					back.putExtra("state", state);
					back.putExtra("country", country);
					back.putExtra("addresss",address1);
					back.putExtra("latitude", latitude1);
					back.putExtra("longitude",longitude1);
					back.putExtra("write_guest", wguest);
					back.putExtra("write_space", wspace);
					back.putExtra("write_guestint",wguestint );
					back.putExtra("write_overview",woverview );
					back.putExtra("write_around",waround);
					back.putExtra("write_other",wother);
					back.putExtra("write_rules",wrules);
					back.putExtra("step",step);
					back.putExtra("check",check);
				startActivity(back,bndlanimation);
				finish();
			}
		});
		
	}
	public void onBackPressed()
	{
	/*	Intent i=new Intent(Desc_space.this,Writedescription.class);
		i.putExtra("userid", userid);
		i.putExtra("room_id", room_id);
	    startActivity(i);*/
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
