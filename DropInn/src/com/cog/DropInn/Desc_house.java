package com.cog.DropInn;

import com.cog.DropInn.R;

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

public class Desc_house extends Activity {
	String dh,sp,gu,gi,no,ga,ot;
	EditText house;
	ImageView done;
	String userid,dis,dis11,dis22,imaage,address1,city,state,country,room_id,image1,resize,getrules,ruls;
	String latitude1,longitude1;
	String step,check;
	@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_desc_house);
		getActionBar().hide();
		house=(EditText)findViewById(R.id.editText1);		
		done=(ImageView)findViewById(R.id.imageView2);
		dh=house.getText().toString();
		Intent i=getIntent();
		sp=i.getStringExtra("write_space");
		gu=i.getStringExtra("write_guest");
		gi=i.getStringExtra("write_guestint");
		no=i.getStringExtra("write_overview");
		ga=i.getStringExtra("write_around");
		ot=i.getStringExtra("write_other");
		getrules=i.getStringExtra("write_rules");
		house.setText(getrules);
		house.setSelection(house.getText().length());
		System.out.println("value"+house);
		ruls=i.getStringExtra("ruls");
		dh=ruls;
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
		 step=i.getStringExtra("step");
	        check=i.getStringExtra("check");
		//String latitude1,longitude1;

				latitude1=i.getStringExtra("latitude");
				longitude1=i.getStringExtra("longitude");
				
				/*optional.putExtra("latitude", latitude1);
				optional.putExtra("longitude",longitude1);*/
				
				   SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
					 userid = sharedPreferences2.getString("userid", null);
				
		done.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getrules=house.getText().toString();
				System.out.println("inside done in space=="+getrules);
				/*if(dh.isEmpty())
				{
					System.out.println("inside if"+dh);
				
				}
				else
				{
					System.out.println("inside else if"+dh);
					
					getrules=dh;
					
					
				}*/
				Intent back=new Intent(Desc_house.this,Writedescription.class);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Desc_house.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				back.putExtra("write_space", sp);
				back.putExtra("write_guest", gu);
				back.putExtra("write_guestint", gi);
				back.putExtra("write_overview", no);
			    back.putExtra("write_other", ot);
				back.putExtra("write_around", ga);
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
