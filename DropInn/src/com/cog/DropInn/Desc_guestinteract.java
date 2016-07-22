package com.cog.DropInn;

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

public class Desc_guestinteract extends Activity {
EditText guestint;
ImageView done;
String gi,sp,gu,dis1,dis2,dis3,dis4,dis5,image1,resize;
String userid,dis,dis11,dis22,imaage,address1,city,state,country,room_id;
String guestinteract,guinteract,getoverview,getaround,getother,getrules;
String latitude1,longitude1;
String step,check;
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_desc_guestinteract);
		getActionBar().hide();
		guestint=(EditText)findViewById(R.id.editText1);		
		done=(ImageView)findViewById(R.id.imageView2);
		
		Intent i=getIntent();
		sp=i.getStringExtra("write_space");
		gu=i.getStringExtra("write_guest");
		guestinteract=i.getStringExtra("write_guestint");
		guestint.setText(guestinteract);
		getoverview=i.getStringExtra("write_overview");
		getaround=i.getStringExtra("write_around");
		getother=i.getStringExtra("write_other");
		getrules=i.getStringExtra("write_rules");
		//userid=i.getStringExtra("userid");
		room_id=i.getStringExtra("room_id");
		imaage=i.getStringExtra("image");
		image1=i.getStringExtra("image1");
		resize=i.getStringExtra("resize");
		dis=i.getStringExtra("title");
		dis11=i.getStringExtra("summary");
		dis22=i.getStringExtra("price");			
		address1=i.getStringExtra("addresss");			
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country=i.getStringExtra("country");
	    latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
		 step=i.getStringExtra("step");
	        check=i.getStringExtra("check");
		
				
		   SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
			 
		done.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				guestinteract=guestint.getText().toString();
				
				Intent back=new Intent(Desc_guestinteract.this,Writedescription.class);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Desc_guestinteract.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				back.putExtra("write_space", sp);
				back.putExtra("desc_guest", gu);
				back.putExtra("write_guestint", guestinteract);
				back.putExtra("write_overview", getoverview);
				back.putExtra("write_around", getaround);
				back.putExtra("write_other", getother);
				back.putExtra("write_rules", getrules);
				back.putExtra("room_id",room_id);
				back.putExtra("userid",userid);
				back.putExtra("image", imaage);
				back.putExtra("image1", image1);
				back.putExtra("resize", resize);
				back.putExtra("title",dis);				
				back.putExtra("summary", dis11);
				back.putExtra("price",dis22);
				back.putExtra("city", city);
				back.putExtra("state", state);
				back.putExtra("country", country);
				back.putExtra("addresss",address1);
				back.putExtra("latitude", latitude1);
				back.putExtra("longitude",longitude1);
				  back.putExtra("step",step);
				  back.putExtra("check",check);
				startActivity(back,bndlanimation);
				finish();
			}
		});
	}
	public void onBackPressed()
	{
		
     /*   Intent i=new Intent(Desc_guestinteract.this,Writedescription.class);
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
