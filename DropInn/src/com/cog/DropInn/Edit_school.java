package com.cog.DropInn;

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
import android.widget.EditText;
import android.widget.ImageView;

public class Edit_school extends Activity {

	
	String userid;
	EditText school1;
	String getlocation,getemail;
	ImageView done;
	String editname,editname1,geteditname;
	String editnaame,editnaame1,geteditnaame,getaboutme,getbday,getimage,get_gender,get_school,get_work;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_school);
		
		school1=(EditText)findViewById(R.id.editText1);
		done=(ImageView)findViewById(R.id.imageView2);
	
		
		Intent i=getIntent();
		//get school1
		//userid=i.getStringExtra("userid");
		get_work=i.getStringExtra("work");
		get_school=i.getStringExtra("school");
		get_gender=i.getStringExtra("gender");
		getimage=i.getStringExtra("image");
		getlocation=i.getStringExtra("location");
		getemail=i.getStringExtra("email");
		geteditname=i.getStringExtra("editname");
		geteditnaame=i.getStringExtra("editnaame");
		getaboutme=i.getStringExtra("aboutme");
		getbday=i.getStringExtra("bday");
		
		school1.setText(get_school);
		school1.setSelection(school1.getText().length());
		get_school=school1.getText().toString();
		
		   SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					Intent school1=new Intent(Edit_school.this,Edit_profile.class);
					school1.putExtra("userid", userid);
					school1.putExtra("location",getlocation);				
					school1.putExtra("work",get_work);
					school1.putExtra("school",get_school);
					school1.putExtra("gender", get_gender);
					school1.putExtra("image",getimage);
					school1.putExtra("email", getemail);
					school1.putExtra("editname", geteditname);
					//school1.putExtra("editname1", geteditname);
					school1.putExtra("editnaame", geteditnaame);
					//school1.putExtra("editnaame1", geteditnaame);
					school1.putExtra("aboutme", getaboutme);
					school1.putExtra("bday", getbday);
					startActivity(school1);
					 finish();
					
				}
				
			
		});
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
