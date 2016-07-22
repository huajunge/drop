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

public class Desc_getaround extends Activity {
	String ga,sp,gu,gi,no,dis4,dis5;
	EditText getting;
	ImageView done;
	String latitude1,longitude1;
	String userid,dis,dis1,dis2,imaage,address1,city,state,country,room_id,image1,resize,getaround,around,other,rules;
	String step,check;
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_desc_getaround);
		getActionBar().hide();
		getting=(EditText)findViewById(R.id.editText1);		
		done=(ImageView)findViewById(R.id.imageView2);
		Intent i=getIntent();
		sp=i.getStringExtra("write_space");
		gu=i.getStringExtra("write_guest");
		gi=i.getStringExtra("write_guestint");
		no=i.getStringExtra("write_overview");
		other=i.getStringExtra("write_other");
		rules=i.getStringExtra("write_rules");
		getaround=i.getStringExtra("write_around");
		getting.setText(getaround);
		/*getting.setSelection(getting.getText().length());*/
		
		 step=i.getStringExtra("step");
	        check=i.getStringExtra("check");
		System.out.println("value"+getaround);
		
		   SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
		//userid=i.getStringExtra("userid");
		room_id=i.getStringExtra("room_id");
		imaage=i.getStringExtra("image");
		image1=i.getStringExtra("image1");
		resize=i.getStringExtra("resize");
		
		dis=i.getStringExtra("title");
		dis1=i.getStringExtra("summary");
		dis2=i.getStringExtra("price");			
		address1=i.getStringExtra("addresss");			
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country=i.getStringExtra("country");
		
		

				latitude1=i.getStringExtra("latitude");
				longitude1=i.getStringExtra("longitude");
			
				
		done.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getaround=getting.getText().toString();
				
				Intent back=new Intent(Desc_getaround.this,Writedescription.class);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Desc_getaround.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				back.putExtra("write_space", sp);
				back.putExtra("write_around", getaround);
				back.putExtra("write_guest", gu);
				back.putExtra("write_guestint", gi);
				back.putExtra("write_overview", no);
				back.putExtra("write_other", other);
				back.putExtra("write_rules", rules);
                back.putExtra("room_id",room_id);
				back.putExtra("userid",userid);
				back.putExtra("image", imaage);
				back.putExtra("image1", image1);
				back.putExtra("resize", resize);
				back.putExtra("title",dis);				
				back.putExtra("summary", dis1);
				back.putExtra("price",dis2);
				back.putExtra("city", city);
				back.putExtra("state", state);
				back.putExtra("country", country);
				back.putExtra("addresss",address1);
				 back.putExtra("step",step);
				  back.putExtra("check",check);
				
				back.putExtra("latitude", latitude1);
				back.putExtra("longitude",longitude1);
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
