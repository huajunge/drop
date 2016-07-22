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

public class Edit_email extends Activity {
	String get_work,get_school,getlocation,userid;
	EditText email;
	String getemail;
	ImageView done;
	String editname,editname1,geteditname;
	String editnaame,editnaame1,geteditnaame,getaboutme,getbday,getimage,get_gender;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_email);
		getActionBar().hide();
		email=(EditText)findViewById(R.id.editText1);
		done=(ImageView)findViewById(R.id.imageView2);	
		
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		get_work=i.getStringExtra("work");
		get_school=i.getStringExtra("school");
		getlocation=i.getStringExtra("location");
		getimage=i.getStringExtra("image");
		get_gender=i.getStringExtra("gender");
		//get email
		getemail=i.getStringExtra("email");
		geteditname=i.getStringExtra("editname");
		geteditnaame=i.getStringExtra("editnaame");		
		getaboutme=i.getStringExtra("aboutme");
		getbday=i.getStringExtra("bday");
		
		   SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
			
		
		email.setText(getemail);
		email.setSelection(email.getText().length());
		getemail=email.getText().toString();
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getemail=email.getText().toString();
				if(!(getemail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")))
				{
					email.setError("Enter a Valid Email");
				}
				else
				{
					getemail=email.getText().toString();
					Intent email=new Intent(Edit_email.this,Edit_profile.class);
					email.putExtra("userid",userid);				
					email.putExtra("location",getlocation);				
					email.putExtra("work",get_work);
					email.putExtra("school",get_school);					
					email.putExtra("image", getimage);
					email.putExtra("gender", get_gender);
					email.putExtra("email", getemail);
					email.putExtra("editname",geteditname);
					//email.putExtra("editname1", geteditname);
					email.putExtra("editnaame", geteditnaame);
					//email.putExtra("editnaame1", geteditnaame);
					email.putExtra("aboutme", getaboutme);
					email.putExtra("bday", getbday);
					startActivity(email);
					 finish();
					
				}
				
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