package com.cog.DropInn;

import com.cog.DropInn.R;
import com.cog.DropInn.Edit_profile;

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
import android.widget.Toast;

public class Edit_location extends Activity {

	String get_work,get_school,userid;
	
	EditText loaction1;
	String getemail;
	ImageView done;
	String editname,editname1,geteditname;
	String editnaame,editnaame1,geteditnaame,getbday,getimage,get_gender;
	String email_id,edfirstname,edlastname,edaboutme,edgender,eddob,edlocation;      
	String firstname,lastname,getaboutme,gender1,getdob,getlocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_location);
		getActionBar().hide();
		loaction1=(EditText)findViewById(R.id.editText1);
		done=(ImageView)findViewById(R.id.imageView2);		
		Intent i=getIntent();		
		//userid=i.getStringExtra("userid");	 
	    email_id=i.getStringExtra("email");
	    edfirstname=i.getStringExtra("firstname");	 
	    edlastname=i.getStringExtra("lastname");
	    edaboutme=i.getStringExtra("aboutme");
	    edgender=i.getStringExtra("gender");
	    eddob=i.getStringExtra("datebirth");
	    edlocation=i.getStringExtra("city");
	    
	    loaction1.setText(edlocation);
	    
	    SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
	 		 userid = sharedPreferences2.getString("userid", null);
		
	    
	    if(edlocation.equals("null"))
        {
	    	edlocation=edlocation.replaceAll("%20"," ");
	    	loaction1.setText("");
        }
	    
	    
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(loaction1.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(),"Enter your location", Toast.LENGTH_SHORT).show();
				}
				else
				{
					edlocation=loaction1.getText().toString();
					Intent location1=new Intent(Edit_location.this,Edit_profile.class);
					location1.putExtra("userid", userid);
					location1.putExtra("email", email_id);
					location1.putExtra("firstname", edfirstname);
					location1.putExtra("lastname", edlastname);
					location1.putExtra("aboutme", edaboutme);
					location1.putExtra("gender", edgender);
					location1.putExtra("datebirth",eddob);
					location1.putExtra("city",edlocation );
					startActivity(location1);
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
		}	}

	

