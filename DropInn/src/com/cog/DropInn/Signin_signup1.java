package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.parse.integratingfacebook.IntegratingFacebookApplication;


public class Signin_signup1 extends Activity {
	
	TextView login,signup,forget_pwd,title;
	EditText email,password,fname,lname;
	ImageView h_line2,h_line3;
	Button button;
	Button back;
	public  static String User_id,username,profile_pic;
	ProgressDialog pDialog;	
	SharedPreferences appSharedPrefs;
	 SharedPreferences.Editor prefsEditor;
	String Login,Signup;
	String get_email,get_password,get_fname,get_lname;
	private String login_userid,room_id;
	String Liveurl="";
	 URL Login_Url;
	    String reader;
	    String login_inputline;
	    String login_status;
	    Toast toast;
	    RelativeLayout r1;
	    Button facebook_login;
	    TextView agreement;
	protected static final String TAG = "Signin_singup";
	ImageView imageView6;
	Button imageBack,imageButton1,button1,button2;
	ImageButton imageView4,imageView5;
	TextView ftext,etext;
	EditText cText1;
	LinearLayout linearLayout1;
	String symbol1,image;
	String roomid;
	public static final String PREFS_NAME = "MyPrefsFile";

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_signup1);
		getActionBar().hide();
		
		 if( Build.VERSION.SDK_INT >= 9){
             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
             StrictMode.setThreadPolicy(policy);
      }
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	        Liveurl = sharedPreferences.getString("liveurl", null); 
	        
		
	        Intent i=getIntent();
	         roomid=i.getStringExtra("room_id");
		symbol1=i.getStringExtra("symbol");
		image=i.getStringExtra("image");
		imageView6= (ImageView) findViewById(R.id.imageView6);
		imageBack= (Button) findViewById(R.id.imageBack);
		button1= (Button) findViewById(R.id.button1);
		button2= (Button) findViewById(R.id.button2);
		
		imageButton1= (Button) findViewById(R.id.imageButton1);
		imageView4= (ImageButton) findViewById(R.id.imageView4);
		imageView5= (ImageButton) findViewById(R.id.imageView5);
		ftext= (TextView) findViewById(R.id.ftext);
		etext= (TextView) findViewById(R.id.etext);
		cText1= (EditText) findViewById(R.id.cText1);
		linearLayout1= (LinearLayout) findViewById(R.id.linearLayout1);
		email= (EditText) findViewById(R.id.editText1);
		password= (EditText) findViewById(R.id.editText2);
		fname=(EditText)findViewById(R.id.editText3);
		lname=(EditText)findViewById(R.id.editText4);
		
		agreement=(TextView)findViewById(R.id.TextView1);
		String second = "<font color='#FF1919'>Terms of service,Privacy Policy,Guest Refund Policy </font>";
		String first = "<font color='#ffffff'>  By signing up,I agree to DropInn </font>";
		String third = "<font color='#ffffff'>and </font>";
		String fourth = "<font color='#FF1919'>Host Guaretee Terms.</font>";
		agreement.setText(Html.fromHtml(first + second + third +fourth));
		
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Thonburi.ttf");
		agreement.setTypeface(typeFace);
		ftext.setTypeface(typeFace);
		etext.setTypeface(typeFace);
		
		
		cText1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			 public void onClick(View v) {
				
				imageBack.setVisibility(View.GONE);
				imageButton1.setVisibility(View.GONE);
				imageView4.setVisibility(View.GONE);
				ftext.setVisibility(View.GONE);
				etext.setVisibility(View.GONE);
				cText1.setVisibility(View.GONE);
				cText1.setVisibility(View.GONE);
				linearLayout1.setVisibility(View.VISIBLE);
				button1.setVisibility(View.VISIBLE);
				button2.setVisibility(View.VISIBLE);
				imageView5.setVisibility(View.VISIBLE);
			          
			   }   
			
		});
		
		imageView5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			 public void onClick(View v) {
				
				imageBack.setVisibility(View.VISIBLE);
				imageButton1.setVisibility(View.VISIBLE);
				imageView4.setVisibility(View.VISIBLE);
				ftext.setVisibility(View.VISIBLE);
				etext.setVisibility(View.VISIBLE);
				cText1.setVisibility(View.VISIBLE);
				cText1.setVisibility(View.VISIBLE);
				linearLayout1.setVisibility(View.GONE);
				button1.setVisibility(View.GONE);
				imageView5.setVisibility(View.GONE);
				button2.setVisibility(View.GONE);     
			   }   
			
		});
		
	imageView4.setOnClickListener(new View.OnClickListener() {
		 @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @SuppressLint("NewApi")
			@Override
			 public void onClick(View v) {
				
				 
	          
	              
	                Intent i = new Intent(Signin_signup1.this,MainActivity.class);
	                i.putExtra("room_id",roomid);
	              Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
	                startActivity(i, bndlanimation);
	                
	                finish();
	                
			          
			   }   
			
		});
	
	imageButton1.setOnClickListener(new OnClickListener() {
        
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @SuppressLint("NewApi") public void onClick(View v) {
          
            Intent i = new Intent(Signin_signup1.this,IntegratingFacebookApplication.class);
            i.putExtra("room_id",roomid);
            i.putExtra("symbol",symbol1);
            i.putExtra("image",image);
          Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
            startActivity(i, bndlanimation);
           
            finish();
            
          
        }
    });
		
	button1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
				
			 get_email=email.getText().toString();
			 get_password=password.getText().toString();
			 		 	
			 
			 System.out.println("inside login");
			 
            if(email.getText().toString().equals(""))
            {
            	Toast.makeText(getApplicationContext(), "Please enter your Email",Toast.LENGTH_SHORT).show();
                
            } else if(!isValidEmail(email.getText().toString())&& !get_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
            {
            	Toast.makeText(getApplicationContext(), "Please enter a valid Email Id",Toast.LENGTH_SHORT).show();
            }
            
            else if(password.getText().toString().equals(""))
            {
            	Toast.makeText(getApplicationContext(), "Please enter a Password",Toast.LENGTH_SHORT).show();
            }
            else  if (get_password.length()<8 ) 
            {
		    	 Toast.makeText(getApplicationContext(), "Password minimum 8 characters",Toast.LENGTH_SHORT).show();
			} 
             	            
            
        else if(isValidEmail(email.getText().toString()))
        {
        	Signinviajson();
        }
           
		
		}
	
	
	
});
		
	imageBack.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent back=new Intent(Signin_signup1.this,Swipe_tabs.class);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
            startActivity(back,bndlanimation);
            finish();
			
		}
	});
	
	button2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent forgot=new Intent(Signin_signup1.this,Forgotpassword.class);
			startActivity(forgot);
			finish();
		}
	});
	}
	public int login_Progress()
    {
        loadSavedPreferences1();
          if(roomid!=null)
          {
        	  Intent Intent_imageview = new Intent(Signin_signup1.this,Bookit_page.class);
              Intent_imageview.putExtra("userid", User_id); 
              Intent_imageview.putExtra("room_id",roomid);
              Intent_imageview.putExtra("symbol",symbol1);
              Intent_imageview.putExtra("image",image);
              startActivity(Intent_imageview);
              finish();
          }
          
          else
          {
        Intent Intent_imageview = new Intent(Signin_signup1.this,Swipe_tabs.class);
        Intent_imageview.putExtra("userid", User_id);
        Intent_imageview.putExtra("email",get_email );
        Intent_imageview.putExtra("firstname",username );
        Intent_imageview.putExtra("profileimage",profile_pic );
        startActivity(Intent_imageview);
        finish();
          }
        return 100;
    }
   private void loadSavedPreferences() {    
         //User has successfully logged in, save this information
         // We need an Editor object to make preference changes.
        
	   SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
         SharedPreferences.Editor editor = settings.edit();
 
         //Set "hasLoggedIn" to true
         editor.putBoolean("hasLoggedIn", true);
         editor.putString ("userid", login_userid);
         System.out.println("email in signin saved preferences==="+get_email);
         System.out.println("email in signin saved preferences1==="+login_userid);
         editor.putString("email",get_email);
         // Commit the edits!
         editor.commit();
   
        }
   private void loadSavedPreferences1() {   
       //User has successfully logged in, save this information
       // We need an Editor object to make preference changes.
     
	   SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
       SharedPreferences.Editor editor = settings.edit();

       //Set "hasLoggedIn" to true
       editor.putString ("userid", User_id);
       System.out.println("email in signup saved preferences==="+get_email);
       editor.putString("email",get_email);
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
			
			private void Signinviajson()
			{
				System.out.println("inside else if");
                
            	
            	//final AlertDialog alertDialog = new AlertDialog.Builder(Signin.this).create();	    			
         	    pDialog = new ProgressDialog(Signin_signup1.this);
         		// Showing progress dialog before making http request
         	
         		pDialog.setCancelable(false);
         		pDialog.show();
         		pDialog.setContentView(R.layout.progress_dialog);
         		
         		final String url=Liveurl+"user/login?email_id="+get_email+"&password="+get_password;
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
        		                            System.out.println("id"+User_id);
        		                            
        		                            login_Progress();	                        
        		                            
        		                          
        		                        }
        		                        else if (login_status.matches("Sorry! The password is invalid."))
        		                        {
        		                            Toast toast=Toast.makeText(getApplicationContext(), "password is invalid", Toast.LENGTH_SHORT);
        		                            toast.setGravity(Gravity.CENTER, 0, 0);
        		                            toast.show();
        		                           
        		                        }
        		                        else if (login_status.matches("Sorry! The Emailid is invalid."))
        		                        {
        		                            Toast toast=Toast.makeText(getApplicationContext(), "Sorry! email-id or password is invalid", Toast.LENGTH_SHORT);
        		                            toast.setGravity(Gravity.CENTER, 0, 0);
        		                            toast.show();
        		                           
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

        	// Adding request to request queue
        	AppController.getInstance().addToRequestQueue(movieReq);	
			}
			private void Signupviajson() {
				// TODO Auto-generated method stub
				 
				
				  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			       Date dateobj = new Date();
			       String joindate=df.format(dateobj);
			       System.out.println(df.format(dateobj));
			       
				pDialog = new ProgressDialog(Signin_signup1.this);
		  		// Showing progress dialog before making http request
		  	
		  		pDialog.setCancelable(false);
		  		pDialog.show();
		  		pDialog.setContentView(R.layout.progress_dialog);
		 		final String url=Liveurl+"user/signup?firstname="+get_fname+"&lastname="+get_lname+"&email_id="+get_email+"&password="+get_password+"&join_date="+joindate;
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
										
										if(login_status.matches("Welcome to DropInn.")){		
											
							         	   pDialog = new ProgressDialog(Signin_signup1.this);
							         	   
							         		// Showing progress dialog before making http request
							         	
							         		pDialog.setCancelable(false);
							         		pDialog.show();
							         		pDialog.setContentView(R.layout.progress_dialog);
							            	 
							                 login_userid=  obj.getString("user_id");
							                 
							                 loadSavedPreferences();
							                 
							                 Toast.makeText(getApplicationContext(), "Signup successfully", Toast.LENGTH_SHORT).show();
							                 if (roomid==null)
							                 {
							                  Intent i1 = new Intent(Signin_signup1.this, Swipe_tabs.class);
							                  i1.putExtra("userid", login_userid);
							                    i1.putExtra("firstname",get_fname);
							                    i1.putExtra("lastname",get_lname);
							                    i1.putExtra("email", get_email);
							                    //i1.putExtra("room_id", roomid);
							                   // i.putExtra("password", get_password);
							                   // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
							                    startActivity(i1);
							                    finish();  
							                 }
							                 else if (roomid!=null)
							                 {
							                     Intent i1 = new Intent(Signin_signup1.this, Detail_page.class);
								                  i1.putExtra("userid", login_userid);
								                    i1.putExtra("firstname",get_fname);
								                    i1.putExtra("lastname",get_lname);
								                    i1.putExtra("email", get_email);
								                    i1.putExtra("room_id", roomid);
								                   // i.putExtra("password", get_password);
								                   // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
								                    startActivity(i1);
								                    finish();
							                 }
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
