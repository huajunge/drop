package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.StringTokenizer;

import net.appkraft.parallax.ParallaxScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class Edit_profile extends Activity {
	TextView nameedit,fname,lname,aboutme,abouttme,school,work,livecity,gendertype,bday,email;
	EditText phone;
	String editname,editname1,geteditname,fn,ln;
	String editnaame,editnaame1,geteditnaame,getlocation,getwork,getschool;	
	String school1,live1,profileimage1,phonenumber1,about1,gender1,language1,work1,Fname1,Lname1,dob1;
	
	public String firstname, lastname, dateofbirth, e_mail, place, gendervalue, about, phoneno, workvalue, profimage, schoolvalue;
	
	public static final String PREFS_NAME = "MyPrefsFile";
	protected static final String TAG = null;
	private String email_save;
	String getemail;
	ParallaxScrollView scroll;
	String getaboutme,getbday;
	ImageButton gender;
	AlertDialog levelDialog;
	ImageView profileimage,camera;
	String userid,email1;
	private int mYear, mMonth, mDay;
	Button save;
	String picturePath;
	private String stry;
	private Bitmap bitmap_profile_image;
	 private String imagepath;
	ImageButton back;
	ProgressDialog pDialog;	
	String get_gender;
	URL web_Url1;
	    String reader;
	    String reset_inputline,web_inputline,web_inputline1;
	    String web_status;
	    JSONArray reset_jsonarray,web_jsonarray,web_jsonarray1;
	    JSONObject login_Error,reset_jsonobj,web_jsonobj,web_jsonobj1;
	    String status,reset_status,web_status1;
	String email_id;
	String image1,resize,resize1,image;
	//String picture=null;
	String picture=null;
	String[] strArray = new String[5];
	URL Login_Url,Login_Url1;

	URL image_Url;
	String  image_inputline,image_status,image_id;
	JSONArray image_jsonarray;
	JSONObject image_jsonobj;
	 String Liveurl="";
		   private JSONArray login_jsonarray1;
	       private JSONObject login_jsonobj1;
    String condition;
    DatePickerDialog	dpd =null;
    int checkeditem=-1;
    String email12;
    
    
    private int year1 = 0,monthOfYear1=0,dayOfMonth1=0;
    		
    private static final int CAM_REQUREST = 1313;
    private static int RESULT_LOAD_IMAGE = 1;
    
    EditText fnamey,lnamey,schooly,locationy,worky;
    String fnameys,lnameys,schoolys,locationys,workys,phonys;
    
    String edfirstname,edlastname,edaboutme, edgender,eddob,edlocation;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_profile);
		getActionBar().hide();		
		//Datepicker
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);		
		back=(ImageButton)findViewById(R.id.imageButton1);		
		nameedit=(TextView)findViewById(R.id.TextView3);
		fname=(TextView)findViewById(R.id.TextView2);
		lname=(TextView)findViewById(R.id.TextView9);
		abouttme=(TextView)findViewById(R.id.TextView5);
		aboutme=(TextView)findViewById(R.id.TextView6);
		gendertype=(TextView)findViewById(R.id.TextView11);
		bday=(TextView)findViewById(R.id.bday);
		email=(TextView)findViewById(R.id.email_arrow); 
		profileimage=(ImageView)findViewById(R.id.imageView3);
		camera=(ImageView)findViewById(R.id.imageView2);
		phone=(EditText)findViewById(R.id.phone_arrow);
		scroll=(ParallaxScrollView)findViewById(R.id.listscroll);
		/*school=(TextView)findViewById(R.id.school_arrow);
		work=(TextView)findViewById(R.id.work_arrow);*/
		save=(Button)findViewById(R.id.save_button);
		
		fnamey=(EditText)findViewById(R.id.TextView345);
		lnamey=(EditText)findViewById(R.id.TextView346);
		locationy=(EditText)findViewById(R.id.TextView111);
		schooly=(EditText)findViewById(R.id.bday1);
		worky=(EditText)findViewById(R.id.email_arrow1);
		
		 fnameys = fnamey.getText().toString(); 
		 lnameys = lnamey.getText().toString(); 
		 locationys = locationy.getText().toString(); 
		 schoolys = schooly.getText().toString(); 
		 workys = worky.getText().toString(); 
		 phonys = phone.getText().toString(); 
		

		
		if( Build.VERSION.SDK_INT >= 9){
	            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy);
		}
		
		   SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	       Liveurl = sharedPreferences.getString("liveurl", null); 
	    
	       System.out.println("url in Edit_profile page"+Liveurl );
	       
	      // SharedPreferences settings = getSharedPreferences(Edit_profile.PREFS_NAME,0);
	         //Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
	     //	boolean trLoggedIn = settings.getBoolean("trLoggedIn", false);
	     	//String trLoggedIn = settings1.getString("trLoggedIn", logging);
	       
	      
	       
	       Intent i=getIntent();		    
		   //userid=i.getStringExtra("userid");	 
		  // email_id=i.getStringExtra("email");
		   edfirstname=i.getStringExtra("firstname");	 
		   edlastname=i.getStringExtra("lastname");
		   edaboutme=i.getStringExtra("aboutme");
		   edgender=i.getStringExtra("gender");
		   eddob=i.getStringExtra("datebirth");
		   edlocation=i.getStringExtra("city");
		    
		   System.out.println("edit profile User ID--"+userid);
		   System.out.println("edit profile Email"+email12);
		   System.out.println("edit profile First NAme"+edfirstname);
		   System.out.println("edit profile Last Name"+edlastname);
		   System.out.println("edit profile About ME"+edaboutme);
		   System.out.println("edit profile Gender"+edgender);
		   System.out.println("edit profile Date of Birth"+eddob);
		   System.out.println("edit profile Date of Birth"+edlocation);
		   
		   SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
			 
			 email12 = sharedPreferences2.getString("email", null);


	       // email_id = settings.getString("email", email_save);
	  /*     fn=settings.getString("firstname", editname);
           ln=settings.getString("lastname", geteditnaame);
*/
	       
	         System.out.println("View _profile page email=== "+email12);
	         System.out.println("View _profile page userid=== "+userid);
	         System.out.println("************************"+fn);
	         System.out.println("************************"+ln);

	     
	       
	    	//final AlertDialog alertDialog = new AlertDialog.Builder(Signin.this).create();	    			
    	    pDialog = new ProgressDialog(Edit_profile.this);
    		// Showing progress dialog before making http request
    	
    		pDialog.setCancelable(false);
    		pDialog.show();
    		pDialog.setContentView(R.layout.progress_dialog);
    		
    		//final String url=Liveurl+"user/view_profile?user_id="+userid+"&email="+email_id;
    		final String url=Liveurl+"user/view_profile?user_id="+userid+"&email="+email12;
    		//final String url=Liveurl+"user/view_profile?user_id="+userid+"&email="+email_id;
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
   								
   							 school1=obj.getString("school");
   					         profileimage1=obj.getString("profile_pic");
   					         work1=obj.getString("work");
   					         phonenumber1= obj.getString("phnum");
   					         about1= obj.getString("about_me");
   					         gender1=   obj.getString("gender");
   					         language1=obj.getString("language");
   					         live1=obj.getString("live");
   					         Fname1=obj.getString("firstname");
   					         Lname1=obj.getString("lastname");
   					         dob1=obj.getString("dob");
   					         
   					       ;
   					      System.out.println("gender" +gender1);
   					  
   					         
   					         
   					   System.out.println("WEB profile User ID--"+userid);
   					   System.out.println("WEB profile Email"+email12);
   					   System.out.println("WEB profile First NAme"+Fname1);
   					   System.out.println("WEB profile Last Name"+Lname1);
   					   System.out.println("WEB profile About ME"+about1);
   					   System.out.println("WEB profile Gender"+gender1);
   					   System.out.println("WEB profile LOCATION"+live1);
   					   System.out.println("WEB profile About ME"+about1);


   					    //  email_id=obj.getString("email");
   					         
   					   
   					         
   					         
   					 			   
   					    if(profileimage1!=null||profileimage1!="null")
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
   				       
   						System.out.println("Display inside image first ");
   						
   						if(!Fname1.isEmpty()&& !Fname1.equals("null"))
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
   						
   						/*if(!about1.equals("null"))
   						{
   							about1.replaceAll("%20"," ");
   							abouttme.setText(about1);
   						}*/
   						
   						
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
   						
   						
   						
   						
   						
   						
   						
   						
   						
   					/* if(image!=null||image!="null")
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
   					*/
   				    
   				     
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
  
	       	    
	    
		

	    
	    
	 /* 
		nameedit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				Intent name=new Intent(Edit_profile.this, Edit_name.class);
				name.putExtra("userid", userid);
				name.putExtra("email", email12);
				name.putExtra("firstname", Fname1);
				name.putExtra("lastname", Lname1);
				name.putExtra("aboutme",about1);
				name.putExtra("gender", gender1);
				name.putExtra("datebirth",dob1);
				name.putExtra("city",live1);

				startActivity(name);
				
				
			}
		});
		*/
		
	
		aboutme.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getaboutme=abouttme.getText().toString();
				System.out.println("inside about me value ==== "+getaboutme);
				Intent aboutme=new Intent(Edit_profile.this,Edit_aboutme.class);
				aboutme.putExtra("userid", userid);
				aboutme.putExtra("email", email12);
				aboutme.putExtra("firstname", Fname1);
				aboutme.putExtra("lastname", Lname1);
				aboutme.putExtra("aboutme", about1);
				aboutme.putExtra("gender", gender1);
				aboutme.putExtra("datebirth",dob1);
				aboutme.putExtra("city",live1);
                startActivity(aboutme);
                finish();
				
			}
		});
		
		//gender on click
		gendertype.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final CharSequence[] items = {"Male","Female","Other"};
	           
                // Creating and Building the Dialog 
                AlertDialog.Builder builder = new AlertDialog.Builder(Edit_profile.this);
                builder.setTitle("Select your Gender");
               
                if(gender1!=null)
                {
                	if(gender1.equals("Male"))
                	{
                		checkeditem=0;
                	}
                	else if(gender1.equals("Female"))
                	{
                		checkeditem=1;
                	}
                	else if(gender1.equals("Other"))
                	{
                		checkeditem=2;
                	}
                }
                builder.setSingleChoiceItems(items, checkeditem, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                   
                    
                    switch(item)
                    {
                        case 0:
                        	checkeditem=0;
                        	gendertype.setText("Male");
                        	gender1=gendertype.getText().toString();
                        	/*gendertype.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        	gender.setVisibility(View.INVISIBLE);*/
                            break;
                        case 1:
                        	checkeditem=1;
                        	gendertype.setText("Female");   
                        	gender1=gendertype.getText().toString();
                        	/*gendertype.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        	gender.setVisibility(View.INVISIBLE);*/
                            break;
                        case 2:
                        	checkeditem=2;
                            // Your code when 3rd option selected
                        	gendertype.setText("Other");
                        	gender1=gendertype.getText().toString();
                        	/*gendertype.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        	gender.setVisibility(View.INVISIBLE);*/
                            break;                        
                        
                    }
                    levelDialog.dismiss();    
                    }
                });
                levelDialog = builder.create();
                levelDialog.show();
			}
		});
		
			
		
	 System.out.println("after getting gender in edit profile page "+get_gender);
		
	//Birth date
		bday.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	        	
				System.out.println("DOBOOOOOOOOOOOOOOOOOOOOOOOOOO"+dob1);
				if(dob1!=null)
				{
					int count=0;
				StringTokenizer st1 = new StringTokenizer(dob1, "-");
				        while(st1.hasMoreTokens()) {
				        	
				        	if(count==0)
				        	{
				        		String dates=st1.nextToken();
				        		dayOfMonth1=Integer.parseInt(dates);
				        		System.out.println("YEAR"+year1);
				        		mDay=dayOfMonth1;
				        	}
				        	else if(count==1)
				        	{
				        		String months=st1.nextToken();
				        		monthOfYear1=Integer.parseInt(months);
				        		System.out.println("YEAR"+monthOfYear1);
				        		mMonth=monthOfYear1-1;
				        	}
				        	else if(count==2)
				        	{
				        		String years=st1.nextToken();
					           	year1=Integer.parseInt(years);
					           	System.out.println("YEAR"+dayOfMonth1);
					           	mYear=year1;
				        	}
				        	else
				        	{
				        		
				        	}
				        	count++;
				        	
				        	
				          //  System.out.println(st1.nextToken());
				            
				        }
				        

				}
			
				
				dpd = new DatePickerDialog(Edit_profile.this,
				        new DatePickerDialog.OnDateSetListener() {
				 
				            @Override
				            public void onDateSet(DatePicker view, int year,
				                    int monthOfYear, int dayOfMonth) {
				            	
								

				                bday.setText(dayOfMonth + "-"
				                        + (monthOfYear + 1) + "-" + year);
				                dob1=bday.getText().toString();
				                System.out.println("get birthday date"+getbday);
				                System.out.println("get DOB**********"+dob1);
				                
				            }
				            
				        }, mYear, mMonth, mDay);
				dpd.show();
				
			}
			
		});
		/*public void updateDisplay() {
			bday.setText(dayOfMonth + "-"
                    + (monthOfYear + 1) + "-" + year);
				new StringBuilder()
				.append(pad(mHour)).append(":")
				.append(pad(mMinute)));
		}
		*/
		//email On Click
	email.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(Edit_profile.this);

          		alertDialog2.setTitle("");

          		//alertDialog2.setMessage("Do you wish to change your email?");
          		alertDialog2.setMessage("You can't able to change this email id");
          		
          		alertDialog2.setPositiveButton("Ok",
          		new DialogInterface.OnClickListener() {
          			public void onClick(DialogInterface dialog, int which) {
          				
          			         
                        dialog.cancel();
          			}

          		});


          		/*alertDialog2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

          			@Override
          			public void onClick(DialogInterface dialog, int which) {
          				// TODO Auto-generated method stub
          				dialog.cancel();
          			}
          		});*/

          		alertDialog2.show();


          	}
          });
		/*location.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getlocation=location.getText().toString();
				//System.out.println("*************** location"+getlocation);
				Intent location=new Intent(Edit_profile.this,Edit_location.class);
				location.putExtra("userid", userid);
				location.putExtra("email", email12);
				location.putExtra("firstname", Fname1);
				location.putExtra("lastname", Lname1);
				location.putExtra("aboutme", about1);
				location.putExtra("gender", gender1);
				location.putExtra("datebirth",dob1);
				location.putExtra("city",live1);

				startActivity(location); 
				
			}
		});*/
/*		
	work.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent location=new Intent(Edit_profile.this,Edit_work.class);
				location.putExtra("userid",userid);
				location.putExtra("email", getemail);
				location.putExtra("gender",get_gender);
				location.putExtra("image", resize);
				location.putExtra("editname", editname);
				//location.putExtra("editname1", geteditname);
				location.putExtra("editnaame", editnaame);
				//location.putExtra("editnaame1", geteditnaame);
				location.putExtra("aboutme", getaboutme);
				location.putExtra("bday", getbday);
				location.putExtra("getwork",getwork);
				location.putExtra("getschool",getschool);
				startActivity(location); 
				
			}
		});
	school.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent location=new Intent(Edit_profile.this,Edit_school.class);
			location.putExtra("userid",userid);
			location.putExtra("email", getemail);
			location.putExtra("gender",get_gender);
			location.putExtra("image", resize);
			location.putExtra("editname", editname);
			//location.putExtra("editname1", geteditname);
			location.putExtra("editnaame", editnaame);
			//location.putExtra("editnaame1", geteditnaame);
			location.putExtra("aboutme", getaboutme);
			location.putExtra("bday", getbday);
			location.putExtra("getwork",getwork);
			location.putExtra("getschool",getschool);
			startActivity(location); 
			
		}
	});*/
	
	
	/*editname=fname.getText().toString();
	editnaame=lname.getText().toString();
	getschool=school.getText().toString();
	getwork=work.getText().toString();
	getlocation=location.getText().toString();
	email_id=email.getText().toString();
	getbday=bday.getText().toString();
	get_gender=gendertype.getText().toString();
	getaboutme=abouttme.getText().toString();*/
	
	back.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent location=new Intent(Edit_profile.this,View_profile.class);
			location.putExtra("userid", userid);
			location.putExtra("email", email12);
			location.putExtra("firstname", Fname1);
			location.putExtra("lastname", Lname1);
			location.putExtra("aboutme", about1);
			location.putExtra("gender", gender1);
			location.putExtra("datebirth",dob1);
			location.putExtra("city",live1);
			startActivity(location); 
			 finish();
			
		}
	});
			
		/*back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(!getaboutme.isEmpty())
				{
					getaboutme=getaboutme.replaceAll("","%20");
				}
			if(!editname.isEmpty())
				{
					editname=editname.replaceAll(" ","%20");
				}
				if(!editnaame.isEmpty())
				{
					editnaame=editnaame.replaceAll(" ","%20");
				}
				if(!getlocation.isEmpty())
				{
					getlocation=getlocation.replaceAll(" ","%20");
				}
				System.out.println("before webservice in edit profile fname--"+editname);
			    System.out.println("before webservice in edit profile lname=="+editnaame);
			   System.out.println("before webservice in edit profile work==="+getwork);
			   System.out.println("before webservice in edit profile school=="+getschool);
			   System.out.println("before webservice in edit profile email==="+email_id);
			   System.out.println("before webservicet in edit profile location=="+getlocation);
			   System.out.println("before webservice in edit profile birthday=="+getbday);
			   System.out.println("before webservice in edit profile gender=="+get_gender);
			   System.out.println("before webservice in edit profile gender=="+getaboutme);
				 try
		          { 
	                  	         	             
					// web_Url1 = new URL("http://demo.cogzideltemplates.com/client/gottospot_android/mobile/user/edit_profile?user_id="+userid+"&Fname="+editname+"Lname="+editnaame+"&school="+"&email="+getemail+"&work=IT"+"&phnum=900000"+"&gender="+get_gender+"&dob="+getbday+"&language=english"+"&live=US"+"&describe="+getaboutme);
		             web_Url1 = new URL(Liveurl+"user/edit_profile?user_id="+userid+"&fname="+editname+"&lname="+editnaame+"&school="+getschool+"&email="+email_id+"&about_me="+getaboutme+"&work="+getwork+"&phnum="+"&gender="+get_gender+"&dob="+getbday+"&language=english"+"&live="+getlocation);	  
		             
		             System.out.println("inside if  url1 view==="+ web_Url1);
		              BufferedReader web_reader1;
		              String str_currency1="";            
		             // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
		              
		              web_reader1 = new BufferedReader(new InputStreamReader(web_Url1.openStream()));
		              		             
		              
		              while ((web_inputline1 = web_reader1.readLine())!= null)
		              {
		            	  
		                  str_currency1 += web_inputline1;
		                  
		              }
		      
		              System.out.println("reset"+str_currency1);
		              
		                 web_jsonarray1 = new JSONArray(str_currency1);
		                  
		             
		                   for(int i1=0; i1 <web_jsonarray1.length(); i1++)
		                      {
		                      	 web_jsonobj1=web_jsonarray1.getJSONObject(i1);
		                  web_status1 = web_jsonobj1.getString("reason_message");
		                  
		                      }
		                   System.out.println("web_status1==="+ web_status1);
		                   if(web_status1.matches("Your profiles updated Successfully"))
			                 {
			                     
			               	   System.out.println("successfully updated your profiles");
			                   Intent i1=new Intent(Edit_profile.this,View_profile.class);
			                   i1.putExtra("userid",userid);
			                  
                                

			                   startActivity(i1);
			                                    
			                   
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
		});*/
	
	}

	
	/*	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null){
		if (requestCode == CAM_REQUREST) {
		Bundle extras = data.getExtras();

		if (extras != null) {
		
		
		 
		    bitmap_profile_image = extras.getParcelable("data");
		 bitmap_profile_image = (Bitmap) data.getExtras().get("data");
				
		 profileimage.setImageBitmap(bitmap_profile_image);		 
		 profileimage.setScaleType(ScaleType.FIT_XY);
			new ProgressTask(Edit_profile.this).execute();
	
		
	
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
		
		profileimage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		profileimage.setScaleType(ScaleType.FIT_XY);
		new ProgressTask(Edit_profile.this).execute();
		
	
	
		}
		}
		
	}
*/
	/*	public String ImageWrite(Bitmap bitmap1)
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
	       
	    }*/
		
		
	/*	protected void Imageuploading() {
		// TODO Auto-generated method stub

		try {
		Log.e("image", "dfdf");

		HttpURLConnection connection = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;

		String pathToOurFile = (String) imagepath;

		
		//String urlServer = " http://demo.cogzideltemplates.com/client/gottospot_android/mobile/rooms/image_upload?&user_id=1";
		String urlServer =  Liveurl+"rooms/image_upload?&user_id="+userid;
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

		fileInputStream.close()SharedPreferences settings = getSharedPreferences(Edit_profile.PREFS_NAME,0);
					     Editor editor = settings.edit();
  					     editor.putString("editname", Fname1); 
  					     editor.putString("editnaame", Lname1);
  					     editor.putString("get_gender",gender1);
  					     editor.putString("getbday", dob1);
  					     editor.putString("email_id",email1 );
  					     editor.putString("getlocation", live1);
  					     editor.putString("getaboutme", about1);
  					     editor.commit();
  					     System.out.println("userid in discover page upload to  shared preferences "+Fname1);
  				         System.out.println("userid in discover page upload to  shared preferences "+Lname1);
  				         System.out.println("userid in discover page upload to  shared preferences "+gender1);
  				         System.out.println("userid in discover page upload to  shared preferences "+dob1);
  				         System.out.println("userid in discover page upload to  shared preferences "+email_id);
  				         System.out.println("userid in discover page upload to  shared preferences "+getlocation);
  				         System.out.println("userid in discover page upload to  shared preferences "+getaboutme);
                         System.out.println("email id in Edit_profile page==="+email1 );;
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
		          
		      
		      
		      
		     // String strName = resize1;

		      
		       
		       
		           
		        }  catch (JSONException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		} catch (Exception e) {

			e.printStackTrace();

			}
		
		return_image();
		
		

		}*/
		
	/*private class ProgressTask extends AsyncTask<String, Void, Boolean> 
		 {
		     private ProgressDialog dialog;
		     private Edit_profile activity;

		     public ProgressTask(Edit_profile activity) 
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
		 }*/
		 public void return_image()
		 {
			 try
	         { 	             
	        	             
				// image_Url = new URL("http://demo.cogzideltemplates.com/client/gottospot_android/mobile/rooms/profile_image?email=murugank@gmail.com&resize="+resize);
		         image_Url = new URL(Liveurl+"rooms/profile_image?email="+email12+"&resize="+image1);
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
	                    /* Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
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
			 
			   /*save.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						   Toast.makeText(getApplicationContext(), 
	                               "Saved Successfully !!", Toast.LENGTH_SHORT).show();
					}
				}
			  );  */
			
				
				save.setOnClickListener(new View.OnClickListener() {
					
					
					@Override
					public void onClick(View v) {
						
						
					    pDialog = new ProgressDialog(Edit_profile.this);
			    		// Showing progress dialog before making http request
			    	
			    		pDialog.setCancelable(false);
			    		pDialog.show();
			    		pDialog.setContentView(R.layout.progress_dialog);
						// TODO Auto-generated method stub
						//SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
					     /*SharedPreferences settings = getSharedPreferences(Edit_profile.PREFS_NAME,0);
					     Editor editor = settings.edit();
  					     editor.putString("editname", Fname1); 
  					     editor.putString("editnaame", Lname1);
  					     editor.putString("get_gender",gender1);
  					     editor.putString("getbday", dob1);
  					     editor.putString("email_id",email1 );
  					     editor.putString("getlocation", live1);
  					     editor.putString("getaboutme", about1);
  					     editor.commit();
  					     System.out.println("userid in discover page upload to  shared preferences "+Fname1);
  				         System.out.println("userid in discover page upload to  shared preferences "+Lname1);
  				         System.out.println("userid in discover page upload to  shared preferences "+gender1);
  				         System.out.println("userid in discover page upload to  shared preferences "+dob1);
  				         System.out.println("userid in discover page upload to  shared preferences "+email_id);
  				         System.out.println("userid in discover page upload to  shared preferences "+getlocation);
  				         System.out.println("userid in discover page upload to  shared preferences "+getaboutme);
                         System.out.println("email id in Edit_profile page==="+email1 );*/
						
					/*	Intent name=new Intent(Edit_profile.this, View_profile.class);
						name.putExtra("userid", userid);
						name.putExtra("email", email12);*/
						
			
						
						
						 fnameys = fnamey.getText().toString(); 
						 lnameys = lnamey.getText().toString(); 
						 locationys = locationy.getText().toString(); 
						 schoolys = schooly.getText().toString(); 
						 workys = worky.getText().toString(); 
						 phonys = phone.getText().toString();
						
						
						


						if(!about1.isEmpty())
						{
							about1=about1.replaceAll(" ","%20");
						}
						if(!fnameys.isEmpty())
						{
							fnameys=fnameys.replaceAll(" ","%20");
						}
						if(!lnameys.isEmpty())
						{
							lnameys=lnameys.replaceAll(" ","%20");
						}
						if(!locationys.isEmpty())
						{
							locationys=locationys.replaceAll(" ","%20");
						}
						if(!schoolys.isEmpty())
						{
							schoolys=schoolys.replaceAll(" ","%20");
						}
						if(!workys.isEmpty())
						{
							workys=workys.replaceAll(" ","%20");
						}
						
					  /*System.out.println("before webservice in edit profile fname--"+editname);
					   System.out.println("before webservice in edit profile lname=="+editnaame);
					   System.out.println("before webservice in edit profile work==="+getwork);
					   System.out.println("before webservice in edit profile school=="+getschool);
					   System.out.println("before webservice in edit profile email==="+email_id);
					   System.out.println("before webservicet in edit profile location=="+getlocation);
					   System.out.println("before webservice in edit profile birthday=="+getbday);
					   System.out.println("before webservice in edit profile gender=="+get_gender);
					   System.out.println("before webservice in edit profile gender=="+getaboutme);*/
					   try
				          { 
						   
					
							// Showing progress dialog before making http request
							
							
			                  	         	             
							// web_Url1 = new URL("http://demo.cogzideltemplates.com/client/gottospot_android/mobile/user/edit_profile?user_id="+userid+"&Fname="+editname+"Lname="+editnaame+"&school="+"&email="+getemail+"&work=IT"+"&phnum=900000"+"&gender="+get_gender+"&dob="+getbday+"&language=english"+"&live=US"+"&describe="+getaboutme);
				             web_Url1 = new URL(Liveurl+"user/edit_profile?user_id="+userid+"&fname="+fnameys+"&lname="+lnameys+"&school="+schoolys+"&email="+email12+"&work="+workys+"&phnum="+phonys+"&gender="+gender1+"&dob="+dob1+"&language=english"+"&live="+locationys+"&about_me="+about1);	  
				             
				             System.out.println("inside if  url1 view==="+ web_Url1);
				              BufferedReader web_reader1;
				              String str_currency1="";            
				             // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
				              
				              web_reader1 = new BufferedReader(new InputStreamReader(web_Url1.openStream()));
				              		             
				              
				              while ((web_inputline1 = web_reader1.readLine())!= null)
				              {
				            	  
				                  str_currency1 += web_inputline1;
				                  
				              }
				      
				              System.out.println("reset"+str_currency1);
				              
				                 web_jsonarray1 = new JSONArray(str_currency1);
				                  
				             
				                  for(int i1=0; i1 <web_jsonarray1.length(); i1++)
				                      {
				                  web_jsonobj1=web_jsonarray1.getJSONObject(i1);
				                  web_status1 = web_jsonobj1.getString("reason_message");
				                  
				                      }
				                   System.out.println("web_status1==="+ web_status1);
				                   
				                   if(web_status1.matches("Your profiles updated Successfully"))
					               {
				                	  
					               	   System.out.println("successfully updated your profiles");
					                   Toast.makeText(getApplicationContext(), 
				                               "Saved Successfully !!", Toast.LENGTH_SHORT).show();
					                   Intent i1=new Intent(Edit_profile.this,View_profile.class);
					                   i1.putExtra("userid",userid);
					                   startActivity(i1);
					                   finish();
					                                    
					                  // hidePDialog();  
					               }
				                       
				                  
				          
				          }
				          catch (MalformedURLException e)
				          {
				        	  hidePDialog();
				              // TODO Auto-generated catch block
				              e.printStackTrace();
				          }
				          catch (IOException e)
				          {
				        	  hidePDialog();
				              // TODO Auto-generated catch block
				              e.printStackTrace();
				          }
				          catch (JSONException e)
				          {
				        	  hidePDialog();
				              // TODO Auto-generated catch block
				              e.printStackTrace();
				          }
										
							//startActivity(name);

					}
					
					
				});	
				
				
				
				
}	public boolean isOnline(Context c) {
	ConnectivityManager cm = (ConnectivityManager) c
	.getSystemService(Context.CONNECTIVITY_SERVICE);
	NetworkInfo ni = cm.getActiveNetworkInfo();
	 
	if (ni != null && ni.isConnected())
	  return true;
	else
	  return false;
	}	}