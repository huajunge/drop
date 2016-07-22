package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ScrollView;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class View_profile extends Activity {
	String Liveurl="";
	String email1,join;
	private String email_save;
	protected static final String TAG = null;
	public static final String PREFS_NAME = "MyPrefsFile";
	String email_id,picture,image1,resize;
	ScrollView scroll;
	private JSONArray login_jsonarray,login_jsonarray1;
    private JSONObject login_jsonobj,login_jsonobj1;
    private String login_status,login_status1;
    private String login_userid;
    private String login_inputline,login_inputline1;
    ImageView prof_img,camera,ImageView, profileimage;
    
    TextView unwanted,edit,joiny;
    String userid,room_id,other_email,other_userid,eid,email;
	ImageButton back;
	TextView back1,phone_number,email_text,abouttme;
	URL Login_url;
	String picturePath;
	private String stry;
	String school1,live1,profileimage1,phonenumber1,about1,gender1,language1,work1,Fname1,Lname1,dob1;
	TextView school,work,firstname,lastname,about,about_name;
	private Bitmap bitmap_profile_image;
	String condition;
	URL image_Url;
	ProgressDialog pDialog;	
	String  image_inputline,image_status,image_id;
	JSONArray image_jsonarray;
	JSONObject image_jsonobj;
	private String imagepath;
    private static final int CAM_REQUREST = 1313;
    private static int RESULT_LOAD_IMAGE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_profile);
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
		 email = sharedPreferences2.getString("email", null);
		 System.out.println("email address=="+email);
       
   
       System.out.println("View _profile page URL=="+Liveurl);
       
		 edit=(TextView)findViewById(R.id.imageView2);
		// prof_img=(ImageView)findViewById(R.id.imageView3);
		 //profileimage=(ImageView)findViewById(R.id.imageView3);
		 back=(ImageButton)findViewById(R.id.imageButton1);
		 back1=(TextView)findViewById(R.id.TextView1);		 
		// work=(TextView)findViewById(R.id.TextView11);
		// school=(TextView)findViewById(R.id.bday);
		 firstname=(TextView)findViewById(R.id.TextView2);
		 lastname=(TextView)findViewById(R.id.TextView9);
		 about=(TextView)findViewById(R.id.TextView6);
		 about_name=(TextView)findViewById(R.id.TextView10);
		 abouttme=(TextView)findViewById(R.id.TextView5);
		// unwanted=(TextView)findViewById(R.id.TextView3);
		// email_text=(TextView)findViewById(R.id.email_arrow);
		 scroll=(ScrollView)findViewById(R.id.listscroll);
		 camera=(ImageView)findViewById(R.id.imageView35);
		// phone_number=(TextView)findViewById(R.id.phone_arrow);
		school=(TextView)findViewById(R.id.TextView212);
		work=(TextView)findViewById(R.id.TextView214);
		joiny=(TextView)findViewById(R.id.TextView210);
		
		 Intent i=getIntent();
		 //userid=i.getStringExtra("userid");
		 //eid=i.getStringExtra("email");
		// other_userid=i.getStringExtra("other_userid");
		// room_id=i.getStringExtra("roomid");
		 //other_email=i.getStringExtra("other_email");
		 
		/* System.out.println("other email in view profile=="+other_email);
		 System.out.println("other userid in view profile=="+other_userid);
		 System.out.println("other roomid in view profile=="+room_id);*/
		 System.out.println(" userid in view profile==="+userid);
		 
		 
	 	 SharedPreferences settings = getSharedPreferences(View_profile.PREFS_NAME,0);
	         //Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
	     //	boolean trLoggedIn = settings.getBoolean("trLoggedIn", false);
	     	//String trLoggedIn = settings1.getString("trLoggedIn", logging);             
	   
	         //email = settings.getString("email", email_save);	       
	         //System.out.println("View _profile page email=== "+email);
	         email_id = settings.getString("email", email_save);	
	         System.out.println("View _profile page email=== "+email_id);
	         System.out.println("View _profile page About me=== "+about1);
	         //call_webservice();
	         if (isOnline(this)) {
	         final String url=Liveurl+"user/view_profile?user_id="+userid+"&email="+email_id;
	    		//final String url=Liveurl+"user/view_profile?user_id="+userid+"&email="+email_id;
	   		System.out.println("URL is"+url);
	   		 // Creating volley request obj
	   		
	   	   pDialog = new ProgressDialog(View_profile.this);
   		// Showing progress dialog before making http request
   	
   		pDialog.setCancelable(false);
   		pDialog.show();
   		pDialog.setContentView(R.layout.progress_dialog);
	   		JsonArrayRequest movieReq = new JsonArrayRequest(url,
	   				new Response.Listener<JSONArray>() {
	   					@Override
	   					public void onResponse(JSONArray response) {
	   						 
	   						// Parsing json
	   						for (int i = 0; i < response.length(); i++) {
	   							try {
	   								//getting the values
	   							 JSONObject login_jsonobj = response.getJSONObject(i);
	   								
	   						  school1=login_jsonobj.getString("school");
	   				          profileimage1=login_jsonobj.getString("profile_pic");
	   				          work1=login_jsonobj.getString("work");
	   				          phonenumber1= login_jsonobj.getString("phnum");
	   				          about1= login_jsonobj.getString("about_me");
	   				          gender1=   login_jsonobj.getString("gender");
	   				          language1=login_jsonobj.getString("language");
	   				          live1=login_jsonobj.getString("live");
	   				          Fname1=login_jsonobj.getString("firstname");
	   				          Lname1=login_jsonobj.getString("lastname");
	   				          dob1=login_jsonobj.getString("dob");
	   				          //	("email");
	   				          email1=login_jsonobj.getString("email");
	   				          join=login_jsonobj.getString("join_date");
	   					         
	   					       ;
	   					      System.out.println("gender" +gender1);
	   					  
	   					         
	   					         
	   					   System.out.println("WEB profile User ID--"+userid);
	   					   //System.out.println("WEB profile Email"+email12);
	   					   System.out.println("WEB profile First NAme"+Fname1);
	   					   System.out.println("WEB profile Last Name"+Lname1);
	   					   System.out.println("WEB profile About ME"+about1);
	   					   System.out.println("WEB profile Gender"+gender1);
	   					   System.out.println("WEB profile LOCATION"+live1);
	   					   System.out.println("WEB profile About ME"+about1);


	   					    //  email_id=obj.getString("email");
	   					         
	   					
	   					         
	   					         
	   					 			   
	   					 /*   if(profileimage1!=null||profileimage1!="null")
	   						{
	   						try{
	   											
	   						System.out.println("Display inside image first ");
	   						URL pimage=new URL(profileimage1);
	   						 	Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());
	   						
	   						profileimage.setImageBitmap(edbitmap);
	   						profileimage.setScaleType(ScaleType.FIT_XY);
	   						scroll.setImageViewToParallax(profileimage);
	   								
	   						}
	   						
	   						catch(Exception e){
	   						 	e.printStackTrace();
	   						 	}
	   						}
	   				       
	   						System.out.println("Display inside image first ");*/
	   						
	   						
	   						if (school1!=null){
	   					        if(!school1.isEmpty() && !school1.equals("null"))
	   									{
	   										school.setText(school1);
	   									}
	   					        }
	   					        
	   					        if (work1!=null){
	   					            if(!work1.isEmpty() && !work1.equals("null"))
	   					  				{
	   					  					work.setText(work1);
	   					  				}
	   					            }
	   					        
	   					        if (join!=null){
	   					            if(!join.isEmpty() && !join.equals("null"))
	   					  				{
	   					  					joiny.setText("Member Since "+join);
	   					  				}
	   					            }
	   					                       
	   					            
	   					         
	   					         if(profileimage1!=null)
	   								{
	   					      try{
	   								/*pimage=new URL(profimage);
	   								imagebitmap = BitmapFactory.decodeStream(pimage.openStream());
	   								 
	   								profile_image.setImageBitmap(imagebitmap);
	   								profile_image.setScaleType(ScaleType.FIT_XY);;
	   								parallax.setImageViewToParallax(profile_image);
	   								*/
	   								StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	   					          .permitAll().build();
	   					  StrictMode.setThreadPolicy(policy);
	   					  String url =profileimage1;

	   					  
	   					  
	   					  BitmapFactory.Options bmOptions;
	   					  bmOptions = new BitmapFactory.Options();	
	   					  bmOptions.inSampleSize = 1;
	   					  Bitmap bm = loadBitmap(url, bmOptions);
	   					  camera.setImageBitmap(getRoundedShape(bm));
	   					camera.setScaleType(ScaleType.FIT_XY);
	   					getRoundedShape(bm).recycle();
	   							
	   								} 
	   								catch(Exception e){
	   									e.printStackTrace();
	   								}
	   								}
	   					         
	   					         
	   					      if(Fname1!=null&&Fname1!="null")
	   						 {
	   							Fname1=Fname1.replaceAll("%20"," ");
	   							 Fname1=WordUtils.capitalizeFully(Fname1);
	   						    firstname.setText(Fname1);
	   						   
	   							about_name.setText(Fname1);
	   						 }

	   						if(Lname1!=null&&Lname1!="null")
	   						 {
	   							Lname1=Lname1.replaceAll("%20"," ");
	   							Lname1=WordUtils.capitalizeFully(Lname1);
	   							lastname.setText(Lname1);
	   						 }
	   						
	   			/*
	   					 if(!work1.isEmpty()  && work1!=null)
	   					 {
	   						 work1.replaceAll("%20"," ");
	   						 work.setText(work1);
	   					 }
	   					 
	   						if(!school1.isEmpty() && school1!=null)
	   						{
	   							school1.replaceAll("%20"," ");
	   							school.setText(school1);
	   						}
	   						*/
	   					/*	if (title1!=null)
	   						{
	   						if (!title1.equals ("null"))
	   						{
	   						title1=WordUtils.capitalizeFully(title1);
	   						}
	   						}*/
	   						System.out.println("ABOUT MEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE"+about1);
	   								if(!about1.equals("null"))
	   						        {
	   									about1=about1.replaceAll("%20"," ");
	   									
	   									abouttme.setText(about1);
	   						        }
	   							/*	else 
	   								{
	   									abouttme.setVisibility(View.GONE);
	   								}*/
	   						if(email!=null&&email!="null")
	   						{
	   					   //email_text.setText(email);
	   						}
	   						
	   						/*if(!Fname1.isEmpty()&& !Fname1.equals("null"))
	   						 {
	   							Fname1.replaceAll("%20"," ");
	   							fnamey.setText(Fname1);
	   							 //aboutme.setText(Fname1);
	   						 }

	   						if(!Lname1.isEmpty() && !Lname1.equals("null"))
	   						 {
	   							Lname1.replaceAll("%20"," ");
	   							lnamey.setText(Lname1);
	   						 }
	   						if(!gender1.isEmpty() && !gender1.equals("null"))
	   						{
	   							gendertype.setText(gender1);
	   						}
	   						if(!dob1.isEmpty() && !dob1.equals("null"))
	   						{
	   							bday.setText(dob1);
	   						}
	   						if(!phonenumber1.isEmpty()&&!phonenumber1.equals("null"))
	   						{
	   							System.out.println("inside location condition");
	   							phonenumber1.replaceAll("%20", " ");
	   							phone.setText(phonenumber1);
	   						}
	   						
	   						if(!school1.isEmpty()&&!school1.equals("null"))
	   						{
	   							System.out.println("inside location condition");
	   							school1.replaceAll("%20", " ");
	   							schooly.setText(school1);
	   						}
	   						
	   						if(!work1.isEmpty()&&!work1.equals("null"))
	   						{
	   							System.out.println("inside location condition");
	   							work1.replaceAll("%20", " ");
	   							worky.setText(work1);
	   						}
	   						
	   						if(!live1.isEmpty()&&!live1.equals("null"))
	   						{
	   							System.out.println("inside location condition");
	   							live1.replaceAll("%20", " ");
	   							locationy.setText(live1);
	   						}
	   						
	   					 if(!work1.isEmpty()  && work1!=null)
	   					 {
	   						 work1.replaceAll("%20"," ");
	   						 work.setText(work1);
	   					 }
	   					 
	   						if(!school1.isEmpty() && school1!=null)
	   						{
	   							school1.replaceAll("%20"," ");
	   							school.setText(school1);
	   						}
	   						
	   						if(!about1.equals("null"))
	   						{
	   							about1.replaceAll("%20"," ");
	   							abouttme.setText(about1);
	   						}
	   						
	   						
	   						System.out.println("ABOUT ME**************"+about1);
	   						
	   					    if(!about1.isEmpty() && !about1.equals("null"))
	   						{
	   							System.out.println("inside about1 condition");
	   							about1.replaceAll("%20"," ");
	   							abouttme.setText(about1);
	   						}
	   								
	   						if(!email12.isEmpty()&&!email12.equals("null"))
	   						{
	   					       email.setText(email12);
	   					
	   						}
	   						
	   						
	   						
	   						
	   						
	   						
	   						
	   						
	   						
	   					 if(image!=null||image!="null")
	   					{
	   					try{			
	   					
	   						URL pimage=new URL(image);
	   					 	Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());
	   				
	   					 	profileimage.setImageBitmap(edbitmap);
	   					 	profileimage.setScaleType(ScaleType.FIT_XY);
	   					 	resize=image;
	   					
	   					}
	   					
	   					catch(Exception e){
	   					 	e.printStackTrace();
	   					 	}
	   					}
	   					
	   				    
	   				     
	   				    //first name
	   				    if(edfirstname!=null)
	   					{
	   						
	   							Fname1=edfirstname;
	   							System.out.println("insdie yu=="+Fname1);
	   							fname.setText(edfirstname);
	   							fname.setTextColor(Color.parseColor("#666666"));
	   					}
	   				    //last name
	   				    if(edlastname!=null)
	   					{
	   						
	   							Lname1=edlastname;
	   							System.out.println("insdie yu=="+Lname1);
	   							lname.setText(edlastname);
	   							lname.setTextColor(Color.parseColor("#666666"));
	   					}
	   				    
	   				    //about me
	   				    if(edaboutme!=null)
	   				    {
	   				    	about1=edaboutme;
	   						System.out.println("insdie yu=="+about1);
	   				      	abouttme.setText(edaboutme);
	   				        abouttme.setTextColor(Color.parseColor("#666666"));
	   				    	
	   				    }
	   				 if(edgender!=null)
					    {
					    	gender1=edgender;
							System.out.println("insdie yu=="+gender1);
							gendertype.setText(edgender);
							gendertype.setTextColor(Color.parseColor("#666666"));
					    	
					    }
	   				 
	   				if(eddob!=null)
				    {
	   					dob1=eddob;
						System.out.println("insdie yu=="+dob1);
						bday.setText(eddob);
						bday.setTextColor(Color.parseColor("#666666"));
				    	
				    }
	   				if(edlocation!=null)
				    {
	   					live1=edlocation;
						System.out.println("insdie yu=="+live1);
						phone.setText(edlocation);
						phone.setTextColor(Color.parseColor("#666666"));
				    	
				    }
	   				    //email
	   				    if(email12!=null)
	   				    {
	   				    		email.setText(email12);
	   				    		email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
	   				    		
	   				    }
	   				    */
	   					 //hidePDialog();
	   					
	   					 hidePDialog();
	   								
	   							}
								
							    
								catch (JSONException e) {
									e.printStackTrace();
									 hidePDialog();								}
								
							}
							
						}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError  volleyError) {
					VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
					
					 hidePDialog();
					 

				}
				
				
			});
	   
		//Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
		 
	/* else{
		
		 call_webservice_otheruser();
	 }*/
	         System.out.println("india");
	        /* if(profileimage1!=null)
				{
	         try{
	  			pimage=new URL(profimage);
	  			imagebitmap = BitmapFactory.decodeStream(pimage.openStream());
	  			 
	  			profile_image.setImageBitmap(imagebitmap);
	  			profile_image.setScaleType(ScaleType.FIT_XY);;
	  			parallax.setImageViewToParallax(profile_image);
	 			
	 			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	             .permitAll().build();
	     StrictMode.setThreadPolicy(policy);
	     String url =profileimage1;

	     
	     
	     BitmapFactory.Options bmOptions;
	     bmOptions = new BitmapFactory.Options();	
	     bmOptions.inSampleSize = 1;
	     Bitmap bm = loadBitmap(url, bmOptions);
	     prof_img.setImageBitmap(getRoundedShape(bm));
	     prof_img.setScaleType(ScaleType.FIT_XY);
	  		
	  			} 
	  			catch(Exception e){
	  				e.printStackTrace();
	  			}
				}*/
	 		
		 
		/*	if(profileimage1!=null)
			{
			try{
								
			System.out.println("Display inside image first ");
			URL pimage=new URL(profileimage1);
			 	Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());
			
			prof_img.setImageBitmap(edbitmap);
			prof_img.setScaleType(ScaleType.FIT_XY);
			//scroll.setImageViewToParallax(prof_img);
					
			}
			
			catch(Exception e){
			 	e.printStackTrace();
			 	}
			}*/
			
		 
		 
		 
		 edit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent edit=new Intent(View_profile.this,Edit_profile.class);
				edit.putExtra("userid",userid);
				edit.putExtra("email", email1);
				startActivity(edit);
				finish();
			}
		});
       
		 
		 back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(View_profile.this,Swipe_tabs.class);
				back.putExtra("userid",userid);
				
				startActivity(back);
				finish();
			}
		});
		 back1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent back=new Intent(View_profile.this,Swipe_tabs.class);
					back.putExtra("userid",userid);
					
					startActivity(back);
					finish();
				}
			});
       
	         }
	         else
	         {
	        	 Toast.makeText(getApplicationContext(), "Check your internet connection",Toast.LENGTH_SHORT).show();
	         }
	         
	         camera.setOnClickListener(new View.OnClickListener() {
	        	    
	        	    public void onClick(View v) {
	        	       System.out.println("india");
	        	        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(View_profile.this);

	        	        alertDialog2.setTitle("Update profile picture");

	        	        alertDialog2.setMessage("Take a new photo or select from your existing photo library");

	        	       // alertDialog2.setIcon(R.drawable.act_camera);


	        	        alertDialog2.setPositiveButton("CAMERA",
	        					new DialogInterface.OnClickListener() {
	        						public void onClick(DialogInterface dialog, int which) {
	        							picture="camera1";
	        							Intent cameraIntent = new Intent(
	        						          	android.provider.MediaStore.ACTION_IMAGE_CAPTURE);


	        						          	cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
	        						          	MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());


	        						          	try {
	        						          	cameraIntent.putExtra("return-data", true);
	        						          	   
	        						          	startActivityForResult(cameraIntent, CAM_REQUREST);
	        						          	

	        						          	} catch (ActivityNotFoundException e) {

	        						          	}			
	        							
	        							
	        						}
	        					});

	        	                      
	        	                alertDialog2.setNeutralButton("GALLERY",
	        	                new DialogInterface.OnClickListener() {

	        	                    public void onClick(DialogInterface dialog, int which) {
	        	                    	picture="gallery";
	        	                      	
	        	                      	Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	        	                      	startActivityForResult(i, RESULT_LOAD_IMAGE);
	        	                       
	        	                    }
	        	                });
	        	                
	        	                
	        	                alertDialog2.setNegativeButton("CLOSE",new DialogInterface.OnClickListener() {
	        	        			
	        	        			@Override
	        	        			public void onClick(DialogInterface dialog, int which) {
	        	        				// TODO Auto-generated method stub
	        	        				 dialog.cancel();
	        	        			}
	        	        		});	

	        	        alertDialog2.show();

	        	       
	        	    }
	        	});
	}
	
	
	//sri
	
	/*if (school1!=null){
        if(!school1.isEmpty() && !school1.equals("null"))
				{
					school.setText(school1);
				}
        }
        
        if (work1!=null){
            if(!work1.isEmpty() && !work1.equals("null"))
  				{
  					work.setText(work1);
  				}
            }
        
        if (join!=null){
            if(!join.isEmpty() && !join.equals("null"))
  				{
  					joiny.setText("Member Since "+join);
  				}
            }
                       
            
         
         if(profileimage1!=null)
			{
      try{
			pimage=new URL(profimage);
			imagebitmap = BitmapFactory.decodeStream(pimage.openStream());
			 
			profile_image.setImageBitmap(imagebitmap);
			profile_image.setScaleType(ScaleType.FIT_XY);;
			parallax.setImageViewToParallax(profile_image);
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
          .permitAll().build();
  StrictMode.setThreadPolicy(policy);
  String url =profileimage1;

  
  
  BitmapFactory.Options bmOptions;
  bmOptions = new BitmapFactory.Options();	
  bmOptions.inSampleSize = 1;
  Bitmap bm = loadBitmap(url, bmOptions);
  prof_img.setImageBitmap(getRoundedShape(bm));
  prof_img.setScaleType(ScaleType.FIT_XY);
		
			} 
			catch(Exception e){
				e.printStackTrace();
			}
			}
	*/
	public void call_webservice()
	{
		try {
	      	
			
      	//Login_Url= new URL("http://demo.cogzideltemplates.com/client/gottospot_android/mobile/user/view_profile?user_id=1&email=ssd@gmail.com");
			Login_url= new URL(Liveurl+"user/view_profile?user_id="+userid+"&email="+email_id);
         
         System.out.println(""+Login_url);
         
         BufferedReader login_reader;
          String str_login="";

          login_reader = new BufferedReader(new InputStreamReader(Login_url.openStream()));
         
          String s=Login_url.toString();
         
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
          Fname1=login_jsonobj.getString("firstname");
          Lname1=login_jsonobj.getString("lastname");
          dob1=login_jsonobj.getString("dob");
          //	("email");
          email1=login_jsonobj.getString("email");
          join=login_jsonobj.getString("join_date");
          
          
          if (school1!=null){
          if(!school1.isEmpty() && !school1.equals("null"))
				{
					school.setText(school1);
				}
          }
          
          if (work1!=null){
              if(!work1.isEmpty() && !work1.equals("null"))
    				{
    					work.setText(work1);
    				}
              }
          
          if (join!=null){
              if(!join.isEmpty() && !join.equals("null"))
    				{
    					joiny.setText("Member Since "+join);
    				}
              }
                         
              }
           
           if(profileimage1!=null)
			{
        try{
 			/*pimage=new URL(profimage);
 			imagebitmap = BitmapFactory.decodeStream(pimage.openStream());
 			 
 			profile_image.setImageBitmap(imagebitmap);
 			profile_image.setScaleType(ScaleType.FIT_XY);;
 			parallax.setImageViewToParallax(profile_image);*/
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
            .permitAll().build();
    StrictMode.setThreadPolicy(policy);
    String url =profileimage1;

    
    
    BitmapFactory.Options bmOptions;
    bmOptions = new BitmapFactory.Options();	
    bmOptions.inSampleSize = 1;
    Bitmap bm = loadBitmap(url, bmOptions);
    camera.setImageBitmap(getRoundedShape(bm));
    camera.setScaleType(ScaleType.FIT_XY);
 		
 			} 
 			catch(Exception e){
 				e.printStackTrace();
 			}
			}
		

           hidePDialog();
         
      } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
    	  hidePDialog();
          e.printStackTrace();
         
      } catch (IOException e) {
          // TODO Auto-generated catch block
    	  hidePDialog();
          e.printStackTrace();
      } catch (JSONException e) {
          // TODO Auto-generated catch block
    	  hidePDialog();
          e.printStackTrace();
      }
	
	
	/*public void call_webservice_otheruser()
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
          //email1=login_jsonobj.getString("email");
       
                         
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
*/
	

}
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	super.onActivityResult(requestCode, resultCode, data);
	if(data!=null){
	if (requestCode == CAM_REQUREST) {
	Bundle extras = data.getExtras();

	if (extras != null) {
	
	
	 
	 bitmap_profile_image = extras.getParcelable("data");
	 bitmap_profile_image = (Bitmap) data.getExtras().get("data");
			
	/* camera.setImageBitmap(bitmap_profile_image);		 
	 camera.setScaleType(ScaleType.FIT_XY);*/
	 
	  camera.setImageBitmap(getRoundedShape(bitmap_profile_image));
			camera.setScaleType(ScaleType.FIT_XY);
		new ProgressTask(View_profile.this).execute();

	

	}
	  
	
	} else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	//count++;
	System.out.println("TEST"+RESULT_LOAD_IMAGE);

	Uri selectedImage = data.getData();
	String[] filePathColumn = {
	MediaStore.Images.Media.DATA
	};
	

	Cursor cursor = getContentResolver().query(selectedImage,
	filePathColumn, null, null, null);
	cursor.moveToFirst();


	int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	 picturePath = cursor.getString(columnIndex);
	 cursor.close();
	
	camera.setImageBitmap (getRoundedShape(BitmapFactory.decodeFile(picturePath)));
	camera.setScaleType(ScaleType.FIT_XY);
	 
/*	 camera.setImageBitmap(getRoundedShape(bitmap_profile_image));
		camera.setScaleType(ScaleType.FIT_XY);*/
	new ProgressTask(View_profile.this).execute();
	


	}
	}
	
}

	public String ImageWrite(Bitmap bitmap1)
    {
       
         String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            OutputStream outStream = null;
            File file = new File(extStorageDirectory, "slectimage.PNG");
           
            try
            {

                outStream = new FileOutputStream(file);
                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
                outStream.close();

              

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
               
            } catch (IOException e)
            {
                e.printStackTrace();
              
            }
            String imageInSD = "/sdcard/slectimage.PNG";       
            return imageInSD;
       
    }
	
	
	protected void Imageuploading() {
	// TODO Auto-generated method stub

	try {
	Log.e("image", "dfdf");

	HttpURLConnection connection = null;
	DataOutputStream outputStream = null;
	DataInputStream inputStream = null;

	String pathToOurFile = (String) imagepath;

	
	//String urlServer = " http://demo.cogzideltemplates.com/client/gottospot_android/mobile/rooms/image_upload?&user_id=1";
	String urlServer =  Liveurl+"rooms/image_upload?user_id="+userid;
	String lineEnd = "\r\n";
	System.out.println("url"+urlServer);
	String twoHyphens = "--";
	String boundary = "*****";

	int bytesRead, bytesAvailable, bufferSize;
	byte[] buffer;
	int maxBufferSize = 1 * 1024 * 1024;
	FileInputStream fileInputStream = new FileInputStream(new File(
	pathToOurFile));

	URL url = new URL(urlServer);
	connection = (HttpURLConnection) url.openConnection();

	// Allow Inputs & Outputs
	connection.setDoInput(true);
	connection.setDoOutput(true);
	connection.setUseCaches(false);

	// Enable POST method
	connection.setRequestMethod("POST");

	connection.setRequestProperty("Connection", "Keep-Alive");
	connection.setRequestProperty("Content-Type",
	"multipart/form-data;boundary=" + boundary);

	outputStream = new DataOutputStream(connection.getOutputStream());
	outputStream.writeBytes(twoHyphens + boundary + lineEnd);
	outputStream
	.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
	+ pathToOurFile + "\"" + lineEnd);
	outputStream.writeBytes(lineEnd);

	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	buffer = new byte[bufferSize];

	// Read file
	bytesRead = fileInputStream.read(buffer, 0, bufferSize);

	while (bytesRead > 0) {
	outputStream.write(buffer, 0, bufferSize);
	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	}

	outputStream.writeBytes(lineEnd);
	outputStream.writeBytes(twoHyphens + boundary + twoHyphens
	+ lineEnd);
	// Responses from the server (code and message)
	int serverResponseCode = connection.getResponseCode();
	String serverResponseMessage = connection.getResponseMessage();

	// Toast.makeText(getApplicationContext(), serverResponseMessage,
	// Toast.LENGTH_LONG).show();
	System.out.println("image" + serverResponseMessage);

	fileInputStream.close();
	outputStream.flush();
	outputStream.close();

	DataInputStream inputStream1 = null;
	inputStream1 = new DataInputStream(connection.getInputStream());
	System.out.println("before input stream image url" + inputStream1);
	String str = "";
	String Str1_imageurl = "";

	while ((str = inputStream1.readLine()) != null) {
	Log.e("Debug", "Server Response " + str);

	Str1_imageurl = str;
	Log.e("Debug", "Server Response String imageurl" + str);
	}
	inputStream1.close();
	System.out.println("after input stream image url" + inputStream1);
	System.out.println("image url" + Str1_imageurl);
	System.out.println("************ Response"+str);
	
	// Toast.makeText(getApplicationContext(), Str1_imageurl,
	// Toast.LENGTH_LONG).show();

	stry = Str1_imageurl.trim();
	
	//Login_Url1=stry;
	System.out.println("check stry"+stry);
	  
	
	try {         
	            login_jsonarray1 = new JSONArray(stry);        
	                   
	             
	             for(int i=0; i <login_jsonarray1.length(); i++)
	                {
	                                  
	            login_jsonobj1 = login_jsonarray1.getJSONObject(i);
	            image1 =login_jsonobj1.getString("image");
	            resize=login_jsonobj1.getString("resize");
	           
	                           
	                }

	      System.out.println("after image===="+image1);
	      System.out.println("after resize==="+resize);    
	          
	      
	      
	      
	     //String strName = resize1;

	      
	       
	       
	           
	        }  catch (JSONException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	} catch (Exception e) {

		e.printStackTrace();

		}
	
	return_image();
	
	

	}
	
	 private class ProgressTask extends AsyncTask<String, Void, Boolean> 
	 {
	     private ProgressDialog dialog;
	     private View_profile activity;

	     public ProgressTask(View_profile activity) 
	     {
	         this.activity = activity;
	         context = activity;
	         dialog = new ProgressDialog(context);
	     }

	     private Context context;

	     protected void onPreExecute() 
	     {
	         dialog = new ProgressDialog(context);
	         dialog.setMessage("Uploading...");
	         dialog.setIndeterminate(false);
	         dialog.setCancelable(false);
	         dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	         dialog.show();
	     }

	     @Override
	     protected void onPostExecute(final Boolean success) 
	     {
	         if (dialog.isShowing()) 
	                     {
	             dialog.dismiss();
	         }
	         if (success) 
	                     {
	            // Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
	         } 
	                     else 
	                     {
	             //Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show();
	         }
	     }

	     @Override
	     protected Boolean doInBackground(final String... args) 
	     {
	         try {    
	            // ... processing ...
	        	 if(picture.equals("camera1"))
	        	 {
	        		 imagepath = ImageWrite(bitmap_profile_image);
	        	 }
	        	 else if(picture.equals("gallery"))
	        	 {
	        		 imagepath =  ImageWrite(BitmapFactory.decodeFile(picturePath));
	        	 }
	        	 else{
	        		 
	        	 }
         	
	 	 	    Imageuploading();
	        	 
	        	
	             return true;
	         } catch (Exception e){
	             Log.e("Schedule", "UpdateSchedule failed", e);
	             return false;
	         }
	     }
	 }
	 public void return_image()
	 {
		 try
         { 	             
        	             
			// image_Url = new URL("http://demo.cogzideltemplates.com/client/gottospot_android/mobile/rooms/profile_image?email=murugank@gmail.com&resize="+resize);
	            image_Url = new URL(Liveurl+"rooms/profile_image?email="+email_id+"&resize="+image1);
	        
			 System.out.println("return image url "+image_Url);
             BufferedReader image_reader;
             String str_currency="";            
            // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
             
            image_reader = new BufferedReader(new InputStreamReader(image_Url.openStream()));
             		             
             
             while ((image_inputline = image_reader.readLine())!= null)
             {
           	  
                 str_currency += image_inputline;
                 
             }
     
             System.out.print("reset"+str_currency);
             
              image_jsonarray = new JSONArray(str_currency);
                 
            
                  for(int i=0; i <image_jsonarray.length(); i++)
                     {
               	   image_jsonobj=image_jsonarray.getJSONObject(i);
                 image_status = image_jsonobj.getString("reason_message");
                 //image_id=image_jsonobj.getString("image_id");
                 
                     }
                  System.out.print("image_status"+ image_status);
                  
                  if(image_status.matches("Your profiles picture updated Successfully"))
                 {
                     
               	   System.out.println("successfully updated your photo");
                    /*Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
                     toast1.setGravity(Gravity.CENTER, 0, 0);
                     toast1.show();*/
                   
                 }
                  else if(image_status.matches("Upload valid image"))
                  {
                      
                	   System.out.println("Upload valid image");
                     /* Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
                      toast1.setGravity(Gravity.CENTER, 0, 0);
                      toast1.show();*/
                                     
                    
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
	 
	
	 @Override
		public void onBackPressed()
		{
			
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
		public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
			   int targetWidth = 1250;
			   int targetHeight = 1250;
			   Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
			                       targetHeight,Bitmap.Config.ARGB_8888);

			   Canvas canvas = new Canvas(targetBitmap);
			   Paint p=new Paint();
			   p.setAntiAlias(true);
			   p.setFilterBitmap(true);
			   p.setDither(true);
			   Path path = new Path();
			   path.addCircle(((float) targetWidth - 1) / 2,
			       ((float) targetHeight - 1) / 2,
			       (Math.min(((float) targetWidth), 
			       ((float) targetHeight)) / 2),
			       Path.Direction.CCW);

			   canvas.clipPath(path);
			   Bitmap sourceBitmap = scaleBitmapImage;
			   canvas.drawBitmap(sourceBitmap, 
			       new Rect(0, 0, sourceBitmap.getWidth(),
			       sourceBitmap.getHeight()), 
			       new Rect(0, 0, targetWidth, targetHeight), null);
			   return targetBitmap;
			}
		
		public static Bitmap loadBitmap(String URL, BitmapFactory.Options options) {
		    Bitmap bitmap = null;
		    InputStream in = null;
		    try {
		        in = OpenHttpConnection(URL);
		        bitmap = BitmapFactory.decodeStream(in, null, options);
		        in.close();
		    } catch (IOException e1) {
		    }
		    return bitmap;
		}
		private static InputStream OpenHttpConnection(String strURL)
		        throws IOException {
		    InputStream inputStream = null;
		    URL url = new URL(strURL);
		    URLConnection conn = url.openConnection();

		    try {
		        HttpURLConnection httpConn = (HttpURLConnection) conn;
		        httpConn.setRequestMethod("GET");
		        httpConn.connect();

		        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
		            inputStream = httpConn.getInputStream();
		        }
		    } catch (Exception ex) {
		    }
		    return inputStream;
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