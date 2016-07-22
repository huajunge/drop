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

public class Edit_work extends Activity {

	String userid;
	EditText work1;
	String getlocation,getemail;
	ImageView done;
	String editname,editname1,geteditname;
	String editnaame,editnaame1,geteditnaame,getaboutme,getbday,getimage,get_gender,get_school,get_work;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_work);
		
		work1=(EditText)findViewById(R.id.editText1);
		done=(ImageView)findViewById(R.id.imageView2);
			
		Intent i=getIntent();
		//get work1
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
		
		work1.setText(get_work);
		work1.setSelection(work1.getText().length());
		get_work=work1.getText().toString();
		
		   SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("edit work page work=="+get_work);
					Intent work1=new Intent(Edit_work.this,Edit_profile.class);
					work1.putExtra("location",getlocation);
					work1.putExtra("userid",userid);
					work1.putExtra("work",get_work);
					work1.putExtra("school",get_school);
					work1.putExtra("gender", get_gender);
					work1.putExtra("image",getimage);
					work1.putExtra("email", getemail);
					work1.putExtra("editname", geteditname);
					//work1.putExtra("editname1", geteditname);
					work1.putExtra("editnaame", geteditnaame);
					//work1.putExtra("editnaame1", geteditnaame);
					work1.putExtra("aboutme", getaboutme);
					work1.putExtra("bday", getbday);
					startActivity(work1);
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
