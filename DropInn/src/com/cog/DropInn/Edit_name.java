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

public class Edit_name extends Activity {

	String get_work,get_school,getlocation,userid;
	EditText fname,lname;
	String Fname,Lname;
	String editname,editname1,geteditname,bday;
	String editnaame,editnaame1,geteditnaame,getaboutme,getimage,get_gender,getemail;
	ImageView done;
	
	
	String email,firstname,lastname,getgender,getdob;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_name);
		getActionBar().hide();
		fname=(EditText)findViewById(R.id.editText1);
		lname=(EditText)findViewById(R.id.editText2);
		/*SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
         Fname= sharedPreferences1.getString("editname",editname);
         Lname= sharedPreferences1.getString("editnaame",editnaame);
        System.out.println("******"+Fname);*/
		done=(ImageView)findViewById(R.id.imageView2);
		editname=fname.getText().toString();
		editnaame=lname.getText().toString();		
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		email=i.getStringExtra("email");
		firstname=i.getStringExtra("firstname");
		lastname=i.getStringExtra("lastname");	
		getaboutme=i.getStringExtra("aboutme");
		getgender=i.getStringExtra("gender");
		getdob=i.getStringExtra("datebirth");
	    getlocation=i.getStringExtra("live");

	    SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
	 		 userid = sharedPreferences2.getString("userid", null);
		
		fname.setText(firstname);
		lname.setText(lastname);
		/*getimage=i.getStringExtra("image");
		get_gender=i.getStringExtra("gender");
		getemail=i.getStringExtra("email");
		//first name
		editname=i.getStringExtra("editname");
		fname.setText(editname);
		fname.setSelection(fname.getText().length());
		System.out.println("fname value"+fname);
		System.out.println("*********************"+editname);
		editname1=i.getStringExtra("editname1");
		editname=editname1;
		//last name
		editnaame=i.getStringExtra("editnaame");
		lname.setText(editnaame);
		lname.setSelection(lname.getText().length());
		System.out.println("lname value"+lname);
		editnaame1=i.getStringExtra("editnaame1");
		editnaame=editnaame1;
		//about me
		getaboutme=i.getStringExtra("aboutme");
		//bday
		bday=i.getStringExtra("bday");
		System.out.println("get date bday"+bday);*/
		
		
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				firstname=fname.getText().toString();
				lastname=lname.getText().toString();
				
				//first name condition
				/*if(editname.isEmpty())
				{
					System.out.println("inside if"+editname);
				
				}
				else
				{
					System.out.println("inside else if"+editname);
					
					editname1=editname;
					
					
				}
				//last name condition
				if(editnaame.isEmpty())
				{
					System.out.println("inside if"+editnaame);
				
				}
				else
				{
					System.out.println("inside else if"+editnaame);
					
					editnaame1=editnaame;
						
				}*/
				Intent back=new Intent(Edit_name.this,Edit_profile.class);
				back.putExtra("userid", userid);
				back.putExtra("email",email);				
				back.putExtra("firstname",firstname);
				back.putExtra("lastname",lastname);
				back.putExtra("aboutme",getaboutme);
				back.putExtra("gender",getgender);
				back.putExtra("datebirth",getdob);
				back.putExtra("live",getlocation);

				/*back.putExtra("image", getimage);
				back.putExtra("gender", get_gender);
				back.putExtra("email",getemail);
				back.putExtra("editname", editname);
				back.putExtra("editname1", editname1);
				back.putExtra("editnaame", editnaame);
				back.putExtra("editnaame1", editnaame1);
				back.putExtra("aboutme", getaboutme);
				back.putExtra("bday", bday);*/
				startActivity(back);
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