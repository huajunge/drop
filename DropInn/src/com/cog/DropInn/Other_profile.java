package com.cog.DropInn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cog.DropInn.R;
import com.cog.DropInn.Edit_profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Other_profile extends Activity {
	String Liveurl="";
	private String email_save;
	public static final String PREFS_NAME = "MyPrefsFile";
	String email,email1;
	private JSONArray login_jsonarray,login_jsonarray1;
    private JSONObject login_jsonobj,login_jsonobj1;
    private String login_status,login_status1;
    private String login_userid;
    private String login_inputline,login_inputline1;
    ImageView edit,prof_img;
    TextView unwanted,email_text,abouttme;
    String userid,room_id,other_email,other_userid;
	ImageButton back;
	TextView back1,phone_number;
	URL Login_Url;
	String school1,live1,profileimage1,phonenumber1,about1,gender1,language1,work1,Fname1,Lname1,dob1;
	TextView school,work,firstname,lastname,about,about_name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_profile);
		getActionBar().hide();
		
		if( Build.VERSION.SDK_INT >= 9)
		{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
			}
	
	
	   SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       Liveurl = sharedPreferences.getString("liveurl", null); 
       
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
       
   
       System.out.println("View _profile page URL=="+Liveurl);
       
       edit=(ImageView)findViewById(R.id.imageView2);
		 prof_img=(ImageView)findViewById(R.id.imageView3);
		 back=(ImageButton)findViewById(R.id.imageButton1);
		 back1=(TextView)findViewById(R.id.TextView1);		 
		// work=(TextView)findViewById(R.id.TextView11);
		 //school=(TextView)findViewById(R.id.bday);
		 firstname=(TextView)findViewById(R.id.TextView2);
		 lastname=(TextView)findViewById(R.id.TextView9);
		 
		 abouttme=(TextView)findViewById(R.id.TextView5);
		 about_name=(TextView)findViewById(R.id.TextView10);
		 email_text=(TextView)findViewById(R.id.email_arrow);
		
		// phone_number=(TextView)findViewById(R.id.phone_arrow);
		
		 edit.setVisibility(View.INVISIBLE);
		 
		 Intent i=getIntent();
		 //userid=i.getStringExtra("userid");
		 other_userid=i.getStringExtra("other_userid");
		 room_id=i.getStringExtra("roomid");
		 other_email=i.getStringExtra("other_email");
		 
		 System.out.println("other email in view profile=="+other_email);
		 System.out.println("other userid in view profile=="+other_userid);
		 System.out.println("other roomid in view profile=="+room_id);
		 System.out.println(" userid in view profile==="+userid);
		 
		 
	 if(userid.equals(other_userid))
		 {
		 
		 System.out.println("insdie userid and other userid equals  ");
			 edit.setVisibility(View.VISIBLE);
			 SharedPreferences settings = getSharedPreferences(Other_profile.PREFS_NAME,0);
	         //Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
	     //	boolean trLoggedIn = settings.getBoolean("trLoggedIn", false);
	     	//String trLoggedIn = settings1.getString("trLoggedIn", logging);	             
	   
	         email = settings.getString("email", email_save);
	       
	         System.out.println("View _profile page email=== "+email);
	         call_webservice();
	         
	         edit.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent edit=new Intent(Other_profile.this,Edit_profile.class);
						edit.putExtra("userid",userid);
						startActivity(edit);
						 finish();
					}
				});
		 }
	 else{
		
		 call_webservice_otheruser();
	 }
		 
			if(profileimage1!=null)
			{
			try{
								
			System.out.println("Display inside image first ");
			URL pimage=new URL(profileimage1);
			 	Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());
			
			prof_img.setImageBitmap(edbitmap);
			prof_img.setScaleType(ScaleType.FIT_XY);
					
			}
			
			catch(Exception e){
			 	e.printStackTrace();
			 	}
			}
			if(!Fname1.isEmpty()&& !Fname1.equals("null"))
			 {
				Fname1=Fname1.replaceAll("%20"," ");
				 firstname.setText(Fname1);
				 about_name.setText(Fname1);
			 }

			if(!Lname1.isEmpty() && !Lname1.equals("null"))
			 {
				Lname1=Lname1.replaceAll("%20"," ");
				 lastname.setText(Lname1);
			 }
			

		/* if(!work1.isEmpty()  && work1!=null)
		 {
			 work1.replaceAll("%20"," ");
			 work.setText(work1);
		 }
		 
			if(!school1.isEmpty() && school1!=null)
			{
				school1.replaceAll("%20"," ");
				school.setText(school1);
			}*/
			
					if(!about1.isEmpty() && !about1.equals("null"))
			{
						about1=about1.replaceAll("%20"," ");
		   abouttme.setText(about1);
			}
			if(!email1.isEmpty()&&!email1.equals("null"))
			{
		   email_text.setText(email1);
			}
		 	
					      
		 
		 back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(Other_profile.this,Swipe_tabs.class);
				back.putExtra("userid",userid);
				
				startActivity(back);
				 finish();
			}
		});
		 back1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent back=new Intent(Other_profile.this,Swipe_tabs.class);
					back.putExtra("userid",userid);
					startActivity(back);
					 finish();
				}
			});
                     
	}
	public void call_webservice()
	{
		try {
	      	
			
      	//Login_Url= new URL("http://demo.cogzideltemplates.com/client/gottospot_android/mobile/user/view_profile?user_id=1&email=ssd@gmail.com");
			Login_Url= new URL(Liveurl+"user/view_profile?user_id="+userid+"&email="+email);
         
         System.out.println(""+Login_Url);
         
         BufferedReader login_reader;
          String str_login="";

          login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
         
          String s=Login_Url.toString();
         
          while ((login_inputline = login_reader.readLine())!= null)
          {
             
              str_login += login_inputline;
                            
          }
         

          System.out.print("logo"+str_login);                  
             
         
          login_jsonarray = new JSONArray(str_login);        
                 
           
           for(int i=0; i <login_jsonarray.length(); i++)
              {
                                
          login_jsonobj = login_jsonarray.getJSONObject(i);
                
       
          school1=login_jsonobj.getString("school");
          profileimage1=login_jsonobj.getString("profile_pic");
          work1=login_jsonobj.getString("work");
         phonenumber1= login_jsonobj.getString("phnum");
         about1= login_jsonobj.getString("about_me");
         gender1=   login_jsonobj.getString("gender");
          language1=login_jsonobj.getString("language");
          live1=login_jsonobj.getString("live");
          Fname1=login_jsonobj.getString("Fname");
          Lname1=login_jsonobj.getString("Lname");
          dob1=login_jsonobj.getString("dob");
          email1=login_jsonobj.getString("email");
       
                         
              }

     
         
      } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
         
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
	}
	public void call_webservice_otheruser()
	{
		try {
	      	
			
      	//Login_Url= new URL("http://demo.cogzideltemplates.com/client/gottospot_android/mobile/user/view_profile?user_id=1&email=ssd@gmail.com");
			Login_Url= new URL(Liveurl+"user/view_profile?user_id="+other_userid+"&email="+other_email);
         
         System.out.println(""+Login_Url);
         
         BufferedReader login_reader;
          String str_login="";

          login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
         
          String s=Login_Url.toString();
         
          while ((login_inputline = login_reader.readLine())!= null)
          {
             
              str_login += login_inputline;
                            
          }
         

          System.out.print("logo"+str_login);                  
             
         
          login_jsonarray = new JSONArray(str_login);        
                 
           
           for(int i=0; i <login_jsonarray.length(); i++)
              {
                                
          login_jsonobj = login_jsonarray.getJSONObject(i);
                
       
          school1=login_jsonobj.getString("school");
          profileimage1=login_jsonobj.getString("profile_pic");
          work1=login_jsonobj.getString("work");
         phonenumber1= login_jsonobj.getString("phnum");
         about1= login_jsonobj.getString("about_me");
         gender1=   login_jsonobj.getString("gender");
          language1=login_jsonobj.getString("language");
          live1=login_jsonobj.getString("live");
          Fname1=login_jsonobj.getString("Fname");
          Lname1=login_jsonobj.getString("Lname");
          dob1=login_jsonobj.getString("dob");
          email1=login_jsonobj.getString("email");
       
                         
              }

     
         
      } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
         
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
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

