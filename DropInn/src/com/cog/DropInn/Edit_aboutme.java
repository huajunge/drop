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

public class Edit_aboutme extends Activity {
	String get_work,get_school,getlocation;
	EditText aboutme;
	String getaboutme;
	String userid;
	ImageView done;
	String editname,editname1;
	String editnaame,editnaame1,bday,getimage,get_gender,getdob;
	
	
	String email_id,firstname,lastname,gender1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_aboutme);
		getActionBar().hide();
		aboutme=(EditText)findViewById(R.id.editText1);
		done=(ImageView)findViewById(R.id.imageView2);
		
		
		
		
		Intent i=getIntent();
		   // userid=i.getStringExtra("userid");	 
		    email_id=i.getStringExtra("email");
		    firstname=i.getStringExtra("firstname");	 
		    lastname=i.getStringExtra("lastname");
		    getaboutme=i.getStringExtra("aboutme");
		    gender1=i.getStringExtra("gender");
			getdob=i.getStringExtra("datebirth");
		    getlocation=i.getStringExtra("live");

		    System.out.println("EDIT ABOUT**********"+getaboutme);
		    aboutme.setText(getaboutme);
		   
		    
		    SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
		    
		    if(getaboutme.equals("null"))
	        {
				getaboutme=getaboutme.replaceAll("%20"," ");
			    aboutme.setText("");
	        }
		
		    done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getaboutme=aboutme.getText().toString();
				Intent aboutme=new Intent(Edit_aboutme.this,Edit_profile.class);
				aboutme.putExtra("userid", userid);
				aboutme.putExtra("email", email_id);
				aboutme.putExtra("firstname", firstname);
				aboutme.putExtra("lastname", lastname);
				aboutme.putExtra("aboutme", getaboutme);
				aboutme.putExtra("gender", gender1);
				aboutme.putExtra("datebirth",getdob);
				aboutme.putExtra("live",getlocation);

								
				startActivity(aboutme);
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