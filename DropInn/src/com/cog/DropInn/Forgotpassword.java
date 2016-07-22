package com.cog.DropInn;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Forgotpassword extends Activity {

	
	 Button reset,imageBack;
	 ImageButton back;
	
	  ImageView img;
	  EditText Email;
	  String Get_email;
	  JSONArray login_jsonarray;
	  JSONObject login_jsonobj,login_Error;
	  URL Login_Url;
	  ProgressDialog mDialog;
	  
	  URL reset_Url;
	    String reader;
	    String reset_inputline;
	    String reset_status;
	    String reset_success;
	    JSONArray reset_jsonarray;
	    JSONObject reset_jsonobj,reset_Error;
	    String status;
	    String success;
	    String LoginUserName,LoginPhoneNumber,LoginImageurl;
	    String Liveurl="";
	    public    static String User_id, userid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgotpassword);
		
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	        Liveurl = sharedPreferences.getString("liveurl", null);
	        
	        SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
	       getActionBar().hide();		 
			
			Email = (EditText)findViewById(R.id.reemail);
		
			back=(ImageButton)findViewById(R.id.imageView5);
			reset=(Button)findViewById(R.id.sendmail);
			imageBack=(Button)findViewById(R.id.imageBack);
			
			
			 back.setOnClickListener(new OnClickListener() {
	          
	          @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	          @SuppressLint("NewApi")
	          @Override
	          public void onClick(View v) {
	              
	              Intent i=new Intent(getApplicationContext(),Signin_signup1.class);
	              //overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	             // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
	              startActivity(i);
	              finish();
	              
	          }
	      });
			 

			 imageBack.setOnClickListener(new OnClickListener() {
	          
	          @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	          @SuppressLint("NewApi")
	          @Override
	          public void onClick(View v) {
	              
	              Intent i=new Intent(getApplicationContext(),Swipe_tabs.class);
	              //overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
	             // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
	              startActivity(i);
	              finish();
	              
	          }
	      });
	    
	      reset.setOnClickListener( new OnClickListener()
	      {   
	 
	          
	          @SuppressLint("NewApi")
			public void onClick(View v)
	          {                        

	              
	        Get_email = Email.getText().toString();
	              
	      if(Email.getText().toString().equals(""))
	      {
	          Email.setError("Required");
	         // Toast.makeText(getApplicationContext(), "The Email-Id U R Enter"+Get_email1,10000).show();
	          
	      }
	      else  if (!isValidEmail(Email.getText().toString()) && !Get_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
	      {
	    	  Toast.makeText(getApplicationContext(), "Please enter valid email",Toast.LENGTH_SHORT).show();
			}
	      
	      
	      else if(Get_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && Get_email.length() > 0)
	      {
	    	  		    		 
	    	  
	    	  
	    	  try
	          { 

	              reset_Url=new URL(Liveurl+"user/forgot_password?email="+Get_email);
	              
	             System.out.println("The URL IS "+reset_Url);
	             
	             // Login_Url = new URL(Liveurl+"mobile/login?email="+Get_email+"&password="+Get_Password);
	              
	              BufferedReader reset_reader;
	              String str_reset="";            
	             // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
	              
	              reset_reader = new BufferedReader(new InputStreamReader(reset_Url.openStream()));
	              
	             
	              
	              while ((reset_inputline = reset_reader.readLine())!= null)
	              {
	            	  
	                  str_reset += reset_inputline;
	                  
	              }
	      
	              System.out.print("reset"+str_reset);
	              
	                  reset_jsonarray = new JSONArray(str_reset);
	                  
	             
	                   for(int i=0; i <reset_jsonarray.length(); i++)
	                      {
	                      	                      
	                  reset_jsonobj = reset_jsonarray.getJSONObject(i);
	                  reset_status = reset_jsonobj.getString("status");
	                  
	                      }
	                   System.out.print("reset_status"+ reset_status);
	                   
	                   if(reset_status.matches("Mail successfully sent."))
	                  {
	                      

	                      Toast toast=Toast.makeText(getApplicationContext(), "Successfully sent reset password link to your mail", Toast.LENGTH_SHORT);
	                      toast.setGravity(Gravity.CENTER, 0, 0);
	                      toast.show();
	              
	                      Intent reset=new Intent(getApplicationContext(),Signin_signup1.class);
	                      reset.putExtra("login","Login");
	                       startActivity(reset);
	                      finish();
	                                
	                    
	                  }
	                   if(reset_status.matches("no data"))
	                  {
	                      Toast toast=Toast.makeText(getApplicationContext(), "Sorry! The Emailid is invalid.", Toast.LENGTH_SHORT);
	                      toast.setGravity(Gravity.CENTER, 0, 0);
	                      toast.show();
	                  
	                  }     
	                  else
	                  {
	                	  /*
	                	  Toast toast=Toast.makeText(getApplicationContext(), "Check You email ", Toast.LENGTH_SHORT);
	                      toast.setGravity(Gravity.CENTER, 0, 0);
	                      toast.show();*/
	                  }
	                  
	                      
	                        
	          
	          }
	          catch (MalformedURLException e)
	          {
	              // TODO Auto-generated catch block
	              e.printStackTrace();
	          }
	          catch (IOException e)
	          {
	              // TODO Auto-generated catch block
	              e.printStackTrace();
	          }
	          catch (JSONException e)
	          {
	              // TODO Auto-generated catch block
	              e.printStackTrace();
	          } 
	      }
	      else
	      {
	          Toast toast=Toast.makeText(getApplicationContext(), "Please enter a valid E-mail id", Toast.LENGTH_LONG);
	          toast.setGravity(Gravity.CENTER, 0, 0);
	          toast.show();
	      }
	      
	      
	          }
	      });
		
		
	}
	// validating email id
	private boolean isValidEmail(String Get_email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(Get_email);
		return matcher.matches();
		
	}
	@Override
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
