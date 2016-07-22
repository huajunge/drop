package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

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









import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;


public class Signin extends Activity {
	
	public  static String User_id,username,profile_pic;
	ProgressDialog pDialog;	
	SharedPreferences appSharedPrefs;
	 SharedPreferences.Editor prefsEditor;
	 Button login;
	 EditText Email,Password;
	 String Get_email,Get_password;
	 TextView signup,forgotpswd;
	 String Liveurl="";
	 URL Login_Url;
	    String reader;
	    String login_inputline;
	    String login_status;
	    JSONArray login_jsonarray;
	    JSONObject login_jsonobj,login_Error;
	    String status;
	    protected static final String TAG = null;
	    
	public static final String PREFS_NAME = "MyPrefsFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		 getActionBar().hide();	 	 		 
		 
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null);
         
		 Email=(EditText)findViewById(R.id.email);
		 Password=(EditText)findViewById(R.id.password);
		 login=(Button)findViewById(R.id.imageButton1);
		 
		 signup=(TextView)findViewById(R.id.TextView2);
		 forgotpswd=(TextView)findViewById(R.id.TextView1);
		 
		 
		 forgotpswd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(),Forgotpassword.class);
				startActivity(i);
				finish();
			}
		});
		
		
		 
		 signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent signuppage=new Intent(Signin.this,Signup.class);
				startActivity(signuppage);
				finish();
			}
		});
		 
		 
		 login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 Get_email=Email.getText().toString();
				 Get_password=Password.getText().toString();
				 		 	
				 
				 System.out.println("inside login");
	            if(Email.getText().toString().equals(""))
	            {
	            	Toast.makeText(getApplicationContext(), "Please enter the email field",Toast.LENGTH_SHORT).show();
	                
	            } else if(!isValidEmail(Email.getText().toString())&& !Get_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
	            {
	            	Toast.makeText(getApplicationContext(), "Please enter valid email id",Toast.LENGTH_SHORT).show();
	            }
	            
	            else if(Password.getText().toString().equals(""))
	            {
	            	Toast.makeText(getApplicationContext(), "Please enter the password field",Toast.LENGTH_SHORT).show();
	            }
	            else  if (Get_password.length()<8 ) {
			    	 Toast.makeText(getApplicationContext(), "Required minimum 8 characters",Toast.LENGTH_SHORT).show();
					} 
	             	            
	            else if(isValidEmail(Email.getText().toString()))
	            {
			     
	            	System.out.println("inside else if");
	                
	            	
	            	//final AlertDialog alertDialog = new AlertDialog.Builder(Signin.this).create();	    			
	         	    pDialog = new ProgressDialog(Signin.this);
	         		// Showing progress dialog before making http request
	         	
	         		pDialog.setCancelable(false);
	         		pDialog.show();
	         		pDialog.setContentView(R.layout.progress_dialog);
	         		
	         		final String url=Liveurl+"user/login?email_id="+Get_email+"&password="+Get_password;
	        		System.out.println("URL is"+url);
	        		 // Creating volley request obj
	        		JsonArrayRequest movieReq = new JsonArrayRequest(url,
	        				new Response.Listener<JSONArray>() {
	        					@Override
	        					public void onResponse(JSONArray response) {
	        						 
	        						// Parsing json
	        						for (int i = 0; i < response.length(); i++) {
	        							try {
	        								//getting the values
	        								JSONObject obj = response.getJSONObject(i); 
	        								login_status= obj.getString("status");
	        								
	        								if(login_status.matches("Successfully logged in."))
	        		                        {                       

	        		                            User_id =obj.getString("user_id");
	        		                            username=obj.getString("username");
	        		                            profile_pic=obj.getString("profile_pic");
	        		                            System.out.println("sign in page user id "+User_id);
	        		                            
	        		                            login_Progress();	                        
	        		                            
	        		                          
	        		                        }
	        		                        else if (login_status.matches("Sorry! The password is invalid."))
	        		                        {
	        		                            Toast toast=Toast.makeText(getApplicationContext(), "The password is invalid", Toast.LENGTH_SHORT);
	        		                            toast.setGravity(Gravity.CENTER, 0, 0);
	        		                            toast.show();
	        		                            pDialog.dismiss();
	        		                        }
	        		                        else if (login_status.matches("Sorry! The Emailid is invalid."))
	        		                        {
	        		                            Toast toast=Toast.makeText(getApplicationContext(), "Sorry! The Emailid  and password is invalid", Toast.LENGTH_SHORT);
	        		                            toast.setGravity(Gravity.CENTER, 0, 0);
	        		                            toast.show();
	        		                            
	        		                            pDialog.dismiss();
	        		                        }
	        		                        
	        		                        
	        		                        else{
	        		                        	  
	        		          	                Toast toast=Toast.makeText(getApplicationContext(), "Should Enter A Valid E-Mail ID", Toast.LENGTH_SHORT);
	        		          	                toast.setGravity(Gravity.CENTER, 0, 0);
	        		          	                toast.show();
	        		          	                 pDialog.dismiss();
	        		                        }
	        		                        
	        							}
	        							
	        						    
	        							catch (JSONException e) {
	        								e.printStackTrace();
	        							}
	        							
	        						}
	        						 hidePDialog();
	        						
	        					}
	        		}, new Response.ErrorListener() {
	        			@Override
	        			public void onErrorResponse(VolleyError error) {
	        				VolleyLog.d(TAG, "Error: " + error.getMessage());
	        				 

	        			}
	        		});

	        	//Adding request to request queue
	        	AppController.getInstance().addToRequestQueue(movieReq);	
	            }
	            }
		});
		 
		
		 
	}
	 public int login_Progress()
	    {
	        loadSavedPreferences();
	          
	        Intent Intent_imageview = new Intent(Signin.this,Swipe_tabs.class);
	        System.out.println("sign in page user id load saverd preferences "+User_id);
	        Intent_imageview.putExtra("userid", User_id);
	        Intent_imageview.putExtra("email",Get_email );
	        Intent_imageview.putExtra("firstname",username );
	        Intent_imageview.putExtra("profileimage",profile_pic );
	        startActivity(Intent_imageview);
	        finish();
	        return 100;
	    }
	   private void loadSavedPreferences() {    
	         //User has successfully logged in, save this information
	         // We need an Editor object to make preference changes.
	        
		   SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
	         SharedPreferences.Editor editor = settings.edit();
	 
	         //Set "hasLoggedIn" to true
	         editor.putBoolean("hasLoggedIn", true);
	         editor.putString ("userid", User_id);
	         System.out.println("email in signin saved preferences==="+Get_email);
	         editor.putString("email",Get_email);
	         // Commit the edits!
	         editor.commit();
	   
	        }
	// validating email id
				private boolean isValidEmail(String Get_email) {
					String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
					
					Pattern pattern = Pattern.compile(EMAIL_PATTERN);
					Matcher matcher = pattern.matcher(Get_email);
					return matcher.matches();
					
				}
				public void onDestroy() {
					super.onDestroy();
					hidePDialog();
				}

				private void hidePDialog() {
					if (pDialog != null) {
						pDialog.dismiss();
						pDialog = null;
					}
				 }
				
	
}