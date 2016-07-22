package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends Activity {
	
	protected static final String TAG = null;

	ProgressDialog pDialog;
	Button signup;
	TextView login;
	EditText firstname,password,lastname,email;
	String get_firstname,get_email,get_lastname,get_password;
	String Liveurl="";
	URL Login_Url;
	   private JSONArray login_jsonarray;
       private JSONObject login_jsonobj;
       private String login_status;
       private String login_userid;
       private String login_inputline;
       
       Toast toast;
	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		   if( Build.VERSION.SDK_INT >= 9){
	              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	              StrictMode.setThreadPolicy(policy);
	       }
	         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	         Liveurl = sharedPreferences.getString("liveurl", null); 
		
		
		
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		setContentView(R.layout.activity_signup);
		email= (EditText) findViewById(R.id.editText1);
		password= (EditText) findViewById(R.id.editText2);
		firstname= (EditText) findViewById(R.id.editText3);
		lastname= (EditText) findViewById(R.id.editText4);
		login=(TextView)findViewById(R.id.TextView2);
		//back=(ImageView)findViewById(R.id.imageView2);
		signup=(Button)findViewById(R.id.imageButton4);
		
		
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent login=new Intent(Signup.this,Signin.class);
				startActivity(login);
				finish();
			}
		});
		
		
		signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 get_email=email.getText().toString();
				  get_password =password.getText().toString();
				  get_firstname=firstname.getText().toString();
				  get_lastname=lastname.getText().toString();
				  			 
				if(email.getText().toString().equals("")&&password.getText().toString().equals("")&&firstname.getText().toString().equals("")&&lastname.getText().toString().equals(""))
			     {
					Toast.makeText(getApplicationContext(),"Please enter all fields",Toast.LENGTH_SHORT).show();
			        
			     }
				else if(password.getText().toString().equals(""))
		            {
					Toast.makeText(getApplicationContext(),"Please enter a password ",Toast.LENGTH_SHORT).show();
		                
		            }else if(firstname.getText().toString().equals(""))
		            {
		            	Toast.makeText(getApplicationContext(),"Please enter a firstname ",Toast.LENGTH_SHORT).show();
		            }
				else if(firstname.getText().toString().length()<3){
		            
					Toast.makeText(getApplicationContext(), "Required minimum 3 characters",Toast.LENGTH_SHORT).show();

		        }
				else if (!isValidEmail(email.getText().toString()) && !get_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
			    	
					Toast.makeText(getApplicationContext(), "Enter valid Email",Toast.LENGTH_SHORT).show();
					}  
				else if(get_password.length()<8){
					
					  Toast.makeText(getApplicationContext(), "Password Minimum 8 characters",Toast.LENGTH_SHORT).show();
					     
			     }
				 else
			     {
			    	 registermeviajson();
			   	     
			    	// final String url = "http://demo.cogzidel.com/dropinn/mobile/user/signup?username=murugan123&password=murugan123&repassword=murugan123&email_id=mmm@gmail.com";			    	 
			      }
			}
		});
              
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

	private void registermeviajson() {
		// TODO Auto-generated method stub
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	       Date dateobj = new Date();
	       String joindate=df.format(dateobj);
	       System.out.println(df.format(dateobj));

		pDialog = new ProgressDialog(Signup.this);
  		// Showing progress dialog before making http request
  	
  		pDialog.setCancelable(false);
  		pDialog.show();
  		pDialog.setContentView(R.layout.progress_dialog);
 		final String url=Liveurl+"user/signup?firstname="+get_firstname+"&lastname="+get_lastname+"&email_id="+get_email+"&password="+get_password+"&join_date="+joindate;
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
								
								if(login_status.matches("Success")){		
									
					         	   pDialog = new ProgressDialog(Signup.this);
					         	   
					         		// Showing progress dialog before making http request
					         	
					         		pDialog.setCancelable(false);
					         		pDialog.show();
					         		pDialog.setContentView(R.layout.progress_dialog);
					            	 
					                 login_userid=  obj.getString("user_id");
					                 
					                 loadSavedPreferences();
					                 Toast.makeText(getApplicationContext(), "Signup successfully", Toast.LENGTH_SHORT).show();
					                  Intent i1 = new Intent(Signup.this, Swipe_tabs.class);
					                  i1.putExtra("userid", login_userid);
					                    i1.putExtra("firstname",get_firstname);
					                    i1.putExtra("lastname",get_lastname);
					                    i1.putExtra("email", get_email);
					                   // i.putExtra("password", get_password);
					                   // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
					                    startActivity(i1);
					                    finish();   
					             }
					             

					             else if(login_status.matches("Sorry! This email has already been registered."))
					             { 
					            toast= Toast.makeText(getApplicationContext(), "Email Already registered", Toast.LENGTH_SHORT);
					             toast.setGravity(Gravity.CENTER, 0, 0);
					             toast.show();
					             //edt1.setError("Email Already Taken");

					                 }
					             else
					             {
					            	 
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
			   private void loadSavedPreferences() {   
		             //User has successfully logged in, save this information
		             // We need an Editor object to make preference changes.
		           
				   SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		             SharedPreferences.Editor editor = settings.edit();
		     
		             //Set "hasLoggedIn" to true
		             editor.putString ("userid", login_userid);
		             System.out.println("email in signup saved preferences==="+get_email);
		             System.out.println("email in signup saved preferences==="+login_userid);
		             editor.putString("email",get_email);
		             // Commit the edits!
		             editor.commit();
		            
		      
		            }

}