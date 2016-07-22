package com.parse.integratingfacebook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.cog.DropInn.Bookit_page;
import com.cog.DropInn.Detail_page;
import com.cog.DropInn.Discover;
import com.cog.DropInn.MainActivity;
import com.cog.DropInn.R;
import com.cog.DropInn.Signin;
import com.cog.DropInn.Swipe_tabs;


public class UserDetailsActivity extends Activity {

	String tkn;
	ProgressBar bb;
    String bitmap_profile_image;
    String fullname,profileimage,email1,fbtoken;
	private URL Login_Url;
	public String login_inputline;
	private JSONArray login_jsonarray;
	private JSONObject login_jsonobj;
	private String login_status;
	private String User_id;
	String Liveurl="";
	String live,work,phone,desc;
	 String email,roomid,symbol1,image;
		
    public static final String PREFS_NAME = "MyPrefsFile";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i1=getIntent();
		roomid=i1.getStringExtra("room_id");
		symbol1=i1.getStringExtra("symbol");
		image=i1.getStringExtra("image");
		 //String Liveurl="";
	      //  Liveurl+"
	         if( Build.VERSION.SDK_INT >= 9){
	              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	              StrictMode.setThreadPolicy(policy);
	       }
	         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	         Liveurl = sharedPreferences.getString("liveurl", null);        
		setContentView(R.layout.userdetails);
 		bb=(ProgressBar)findViewById(R.id.progressBar1);
		
 		
 		//Toast.makeText(getApplicationContext(), "Session Created and  get", Toast.LENGTH_LONG).show();	
	// Fetch Facebook user info if the session is active
		Session session = ParseFacebookUtils.getSession();
		if (session != null && session.isOpened()) {
			makeMeRequest();
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			
			// Check if the user is currently logged
			// and show any cached content
		} else {
			// If the user is not logged in, go to the
			// activity showing the login view.
			startLoginActivity();
		}
	}

	private void makeMeRequest() {
		Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),new Request.GraphUserCallback() {	

					public void onCompleted(GraphUser user, Response response) {
						if (user != null) {
							// Create a JSON object to hold the profile info
							JSONObject userProfile = new JSONObject();
							try {
								// Populate the JSON object
								tkn=ParseFacebookUtils.getSession().getAccessToken();
								
								System.out.println("token"+tkn);
								userProfile.put("facebookId", user.getId());
								bitmap_profile_image = "https://graph.facebook.com/"+user.getId()+"/picture?type=large";
								//Toast.makeText(getApplicationContext(), bitmap_profile_image,Toast.LENGTH_LONG).show();
                                System.out.println(bitmap_profile_image);
                                String firstname=user.getFirstName();
                                firstname=firstname.replaceAll("//s+","");
                                System.out.println("firstname"+firstname);                                
                                String lastname=user.getLastName();
                                lastname=lastname.replaceAll("//s+","");
                                
                                System.out.println("Lastname"+lastname);
                                
                                if(!user.asMap().containsKey("email"))
                                {
                                	Toast.makeText(getApplicationContext(),"You must have EmailId account to login", Toast.LENGTH_SHORT).show();
                                	userdetails(null,user.getId(),user.getLastName().replaceAll(" ","%20").toString(),user.getFirstName().toString().replaceAll(" ","%20"),bitmap_profile_image,tkn);
                                }
                                else
                                {
                                	userdetails(user.asMap().get("email").toString(),user.getId(),user.getLastName().replaceAll(" ","%20").toString(),user.getFirstName().toString().replaceAll(" ","%20"),bitmap_profile_image,tkn);
                                }
                              /* email=user.asMap().get("email").toString();
                                System.out.println("email in facebook "+email);
								userdetails(user.asMap().get("email").toString(),user.getId(),user.getLastName().replaceAll(" ","%20").toString(),user.getFirstName().toString().replaceAll(" ","%20"),bitmap_profile_image,tkn);*/
						    
								// Show the user info
							} catch (JSONException e) {
								
								Log.d(IntegratingFacebookApplication.TAG,
										"Error parsing returned user data.");
							}

						} else if (response.getError() != null) {
							if ((response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_RETRY)
									|| (response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_REOPEN_SESSION)) {
								Log.d(IntegratingFacebookApplication.TAG,"The facebook session was invalidated.");
															} else {
								Log.d(IntegratingFacebookApplication.TAG,"Some other error: "
												+ response.getError()
														.getErrorMessage());
							}
						}
					}
				});
		request.executeAsync();

	}

	
	protected void userdetails(String email, String id, String lastName,String firstName,String pimg,String token) {
		// TODO Auto-generated method stub
		
		try{
			email1=email;
			
			System.out.print("after url firstname"+firstName);
        				
			//Login_Url=new URL("http://demo.cogzidel.com/sedio/mobile/flogin?user_id="+id+"&first_name="+firstName+"&token="+token+"&last_name="+lastName+"&email="+email+"&pimage="+pimg);
			//Login_Url=new URL("http://demo.cogzidel.com/sedio/mobile/flogin?user_id="+id+"&first_name="+firstName+"&token="+token+"&last_name="+lastName+"&email="+email+"&pimage="+pimg);
			Login_Url=new URL(Liveurl+"user/fb_signup?fname="+firstName+"&lname="+lastName+"&email_id="+email+"&fb_id="+id+"&live=null"+"&work=null&phnum=null&describe=null"+"&src="+pimg);
		
					System.out.print("login===="+Login_Url);
					BufferedReader login_reader;
					String str_login="";
					String br="";
		        	   login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));			
						
		        	   System.out.println("\nlogin"+login_reader);
						
					 
					
					while (( br = login_reader.readLine())!= null) 
					{						
						str_login += br;
														
					}

						login_jsonarray = new JSONArray(str_login);					 
						login_jsonobj = login_jsonarray.getJSONObject(0);
						login_status =	login_jsonobj.getString("status");
						
						System.out.println("facebook status"+login_status);
						
						  if(login_status.matches("Successfully registered"))
	                        {                       

	                            User_id =login_jsonobj.getString("user_id");   
	                           /* fullname=login_jsonobj.getString("fullname");
	                            profileimage=login_jsonobj.getString("profile_image");
	                            email1=login_jsonobj.getString("email");
	                            fbtoken=login_jsonobj.getString("token");*/
	                            
	                            System.out.println("id"+User_id);
	                            
	                            loadSavedPreferences();
	        					System.out.println("Entry to user Details Activity");
	        					if (roomid==null)
	        					{
	        					  Intent Intent_imageview = new Intent(UserDetailsActivity.this,Swipe_tabs.class);
	        					  System.out.print("after url firstname"+firstName);
	        					  
	        					  Intent_imageview.putExtra("userid", User_id);
	        				    	Intent_imageview.putExtra("profileimage",bitmap_profile_image );
	        				    	Intent_imageview.putExtra("firstname",firstName);
	        				    	Intent_imageview.putExtra("email",email);
	        				    	Intent_imageview.putExtra("fb_token", token);
	        					    startActivity(Intent_imageview);
	        					    finish();
	        					}
	        					else if (roomid!=null)
	        					{
	        						 Intent Intent_imageview = new Intent(UserDetailsActivity.this,Bookit_page.class);
		        					  System.out.print("after url firstname"+firstName);
		        					  
		        					  Intent_imageview.putExtra("userid", User_id);
		        				    	Intent_imageview.putExtra("profileimage",bitmap_profile_image );
		        				    	Intent_imageview.putExtra("firstname",firstName);
		        				    	Intent_imageview.putExtra("symbol",symbol1);
		        				    	Intent_imageview.putExtra("image",image);
		        				    	Intent_imageview.putExtra("email",email);
		        				    	Intent_imageview.putExtra("fb_token", token);
		        				    	Intent_imageview.putExtra("room_id", roomid);
		        					    startActivity(Intent_imageview);
		        					    finish();
	        					}
	                                              
	                                                      
	                        }
	                        else if (login_status.matches("Sorry! This email has already been registered"))
	                        {
	                        	
	                        	 User_id =login_jsonobj.getString("user_id");
	                        	 loadSavedPreferences();    
	                        	 
	                        	 if (roomid==null)
		        					{
	                        	 Intent Intent_imageview1 = new Intent(UserDetailsActivity.this, Swipe_tabs.class);
	     				    	
	                        	 System.out.print("after already register firstname"+firstName);
	                        	 Intent_imageview1.putExtra("userid", User_id);
	     				    	Intent_imageview1.putExtra("profileimage",bitmap_profile_image );
	     				    	Intent_imageview1.putExtra("firstname",firstName);
	     				    	Intent_imageview1.putExtra("email",email);
	     				    	Intent_imageview1.putExtra("fb_token", token);
	     					    startActivity(Intent_imageview1);
	     					    finish();
		        					}
	                        	 
	                        	 else if (roomid!=null)
	                        	 {
	                        		 Intent Intent_imageview1 = new Intent(UserDetailsActivity.this, Bookit_page.class);
	 	     				    	
		                        	 System.out.print("after already register firstname"+firstName);
		                        	 Intent_imageview1.putExtra("userid", User_id);
		     				    	Intent_imageview1.putExtra("profileimage",bitmap_profile_image );
		     				    	Intent_imageview1.putExtra("firstname",firstName);
		     				    	Intent_imageview1.putExtra("email",email);
		     				    	Intent_imageview1.putExtra("room_id", roomid);
		     				    	Intent_imageview1.putExtra("fb_token", token);
		     					    startActivity(Intent_imageview1);
		     					    finish();
	                        	 }
	                        	
	                        	
	                        	
	                          /*  //Toast toast=Toast.makeText(getApplicationContext(), "This email has already been registered", Toast.LENGTH_SHORT);
	                            toast.setGravity(Gravity.CENTER, 0, 0);
	                            toast.show();
	                        */
	                        }
							
						

				System.out.println("user id in user deatils"+User_id);
					  
				 /* loadSavedPreferences();
					System.out.println("Entry to user Details Activity");
					  Intent Intent_imageview = new Intent(UserDetailsActivity.this, Swipe_tabs.class);
				    	Intent_imageview.putExtra("userid", User_id);
				    	Intent_imageview.putExtra("profileimage",bitmap_profile_image );
				    	Intent_imageview.putExtra("firstname",fullname);
				    	Intent_imageview.putExtra("email",email1);
				    	Intent_imageview.putExtra("fb_token", fbtoken);
					    startActivity(Intent_imageview);
					    finish();*/
					  										
		}
		catch (Exception e){
			System.out.println("Exception"+e);
						
		}
		
	}

	private void startLoginActivity() {
		
		Intent intent = new Intent(this, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}
	
	
	private void loadSavedPreferences() {  
        //User has successfully logged in, save this information
        // We need an Editor object to make preference changes.
       
		//Toast.makeText(getApplicationContext(), "Stored Shared Peraference", Toast.LENGTH_LONG).show();	
		
       // SharedPreferences settings = getSharedPreferences(UserDetailsActivity.PREFS_NAME, 0);
		  SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        //Set "hasLoggedIn" to true
        editor.putBoolean("hasLoggedIn", true);
        editor.putString ("userid", User_id);
        System.out.println("email in facebook saved preferences==="+email1);
        editor.putString("email", email1);
        editor.putString ("ftkn", tkn);        
        // Commit the edits!
        editor.commit();


    }
	@Override
	public void onBackPressed(){
		
		
		Intent back=new Intent(UserDetailsActivity.this,MainActivity.class);
		startActivity(back);

	}
	
	
}