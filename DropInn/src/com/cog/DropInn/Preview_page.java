package com.cog.DropInn;


import info.androidhive.customlistviewvolley.app.AppController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import org.apache.commons.lang.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

























import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.R;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("CommitPrefEdits")
public class Preview_page extends FragmentActivity {

	TextView symbol,price1,title1,hometype,name1,icon_guests,icon_rooms,icon_beds,address1,desc1,listingtype,accomodates1,beds1,bedrooms1,bathrooms1;
	TextView currentpolicy,availability,amenities1,more_name,more_country,headtitle,head_address;	
	ImageView bg_image,profile_image,more_profile_image,preview_map;
	ImageButton share,back;
	Button contact,readmore,morehost;
	String editlist,User_id,userimg;
	URL pimage,mapimage,bigimage;
	String first,second,third,fourth,last,Email_id,weeklyedit,monthlyedit,currencycode1;
String image1,resize;
String Liveurl="",cancellation_title,cancellation_content;
String id,space,guests_info,interaction,overview,getting_around,other_thing,house_rule;
String dis,dis1,dis2,dis3,dis4;
URL Login_Url,Login_Url1,Login_Url2,Login_Url3;
int n;
Bitmap mapbitmap,imagebitmap,profilebitmap;
private JSONArray login_jsonarray,login_jsonarray1,login_jsonarray2;
private JSONObject login_jsonobj,login_jsonobj1,login_jsonobj2;
   private String userid,country,room_type,bedrooms,beds,bathrooms,bed_type,amenities,title,desc,price,capacity,name,week,month,email,Fname1,src,currency_symbol,map,bimage,currenycode,currency_symbol1;
 
   private String login_inputline,login_inputline1,login_inputline2,address;
	ProgressDialog pDialog;
	Toast toast;
String roomid,city,state,countryy,currencycode;
protected static final String TAG = null;

private CaldroidFragment dialogCaldroidFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preview_page);
		getActionBar().hide();
		
		 if( Build.VERSION.SDK_INT >= 9){
             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
             StrictMode.setThreadPolicy(policy);
      }
		bg_image=(ImageView)findViewById(R.id.imageView2);
		profile_image=(ImageView)findViewById(R.id.imageView3);
		preview_map=(ImageView)findViewById(R.id.imageView8);
		more_profile_image=(ImageView)findViewById(R.id.s_imageView8);		
		contact=(Button)findViewById(R.id.button1);
		readmore=(Button)findViewById(R.id.button2);
		morehost=(Button)findViewById(R.id.moreabout);		
		symbol=(TextView)findViewById(R.id.textView14);
		price1=(TextView)findViewById(R.id.textView17);
		title1=(TextView)findViewById(R.id.textView1);
		hometype=(TextView)findViewById(R.id.textView2);
		name1=(TextView)findViewById(R.id.textView4);
		icon_guests=(TextView)findViewById(R.id.textView5);
		icon_rooms=(TextView)findViewById(R.id.textView6);
		icon_beds=(TextView)findViewById(R.id.textView7);
		address1=(TextView)findViewById(R.id.textView11);
		desc1=(TextView)findViewById(R.id.textView13);
		listingtype=(TextView)findViewById(R.id.listingtype1);
		accomodates1=(TextView)findViewById(R.id.s_textView5);
		bedrooms1=(TextView)findViewById(R.id.s_textView7);
		beds1=(TextView)findViewById(R.id.s_textView9);
		bathrooms1=(TextView)findViewById(R.id.s_textView11);
		currentpolicy=(TextView)findViewById(R.id.s_textView14);
		availability=(TextView)findViewById(R.id.s_textView16);
		//amenities1=(TextView)findViewById(R.id.s1_textView1);
		more_name=(TextView)findViewById(R.id.s_textView18);
		more_country=(TextView)findViewById(R.id.s_textView19);
		headtitle=(TextView)findViewById(R.id.textView16);
		head_address=(TextView)findViewById(R.id.textView18);		
		share=(ImageButton)findViewById(R.id.imageButton1);
		back=(ImageButton)findViewById(R.id.imageButton2);
	  //****************************************************************************	
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null); 
         currenycode= sharedPreferences.getString("currenycode", null);
         Email_id= sharedPreferences.getString("email", null);
         User_id=sharedPreferences.getString("userid", null);
         
         
        System.out.println("<><><><><>CURRENCY"+currenycode);
        System.out.println("<><><><><>EMAIL"+Email_id);
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		roomid=i.getStringExtra("room_id");
		System.out.println("after getting room id preview page"+roomid);
		dis=i.getStringExtra("image");
		dis1=i.getStringExtra("summary");
		dis2=i.getStringExtra("price");
		System.out.println("check price"+dis2);
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		countryy=i.getStringExtra("country");
		dis3=i.getStringExtra("addresss");
		dis4=i.getStringExtra("title");		
		image1=i.getStringExtra("image1");
		resize=i.getStringExtra("resize");		
		first=i.getStringExtra("first");
		second=i.getStringExtra("second");
		third=i.getStringExtra("third");
		fourth=i.getStringExtra("fourth");
		last=i.getStringExtra("last");
		editlist=i.getStringExtra("editlist");
		weeklyedit=i.getStringExtra("weeklyedit");
		monthlyedit=i.getStringExtra("monthlyedit");
		currencycode1=i.getStringExtra("currenycode");
		
		   SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
	         Editor editor = sharedPreferences1.edit();
	         editor.putString("editlist", editlist);
		
		//new ProgressTask(Preview_page.this).execute();
		editlist=sharedPreferences.getString("editlist",null);
		call_webservice();
		profile();
		view_description();
		view_policy();
		
		SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		currenycode = sharedPreferences.getString("currenycode", null);
		 
		 userid = sharedPreferences2.getString("userid", null);
		 System.out.println("---------"+currenycode);
		 
		 pDialog=new ProgressDialog(Preview_page.this);
			pDialog.show();
			pDialog.setContentView(R.layout.progress_dialog);
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//new ProgressTask(Preview_page.this).execute();
				
				/*pDialog=new ProgressDialog(Preview_page.this);
				pDialog.show();
				pDialog.setContentView(R.layout.progress_dialog);*/
	             Thread thread=new Thread(new Runnable() {
	                 @Override
	                 public void run() {
	                 try {
	                 Thread.sleep(3000);
	                 } catch (InterruptedException e) {
	                 }
	                 pDialog.dismiss();
	                 }
	                 });
	             if(dis4!=null)
				   {
					   dis4=dis4.replaceAll("%20"," ");
						
				   }
				   if(dis1!=null)
				   {
					   dis1=dis1.replaceAll("%20"," ");
				   }
				   if(dis3!=null)
				   {
					   dis3=dis3.replaceAll("%20"," ");
				   }
	             
				   System.out.println("eidtlist String in preview page==="+editlist);
				if(editlist!=null)
				{
	        	if(editlist.equals("Editlist"))
	        	{	
	        		System.out.println("inside edit list intent in preview page ");
		        	Intent back=new Intent(Preview_page.this,Edit_list.class);
					back.putExtra("image1",image1);
					//back.putExtra("resize",resize);
					back.putExtra("room_id",roomid);
					back.putExtra("image", dis);
					back.putExtra("write_title", dis4);
					back.putExtra("write_summary", dis1);
					back.putExtra("write_price", dis2);
					back.putExtra("city", city);
					back.putExtra("state", state);
					back.putExtra("country", countryy);
					back.putExtra("address", dis3);
					back.putExtra("userid", userid);
					back.putExtra("first", first);
					back.putExtra("second", second);
					back.putExtra("third", third);
					back.putExtra("fourth", fourth);
					back.putExtra("last", last);
					back.putExtra("weeklyedit", weeklyedit);
					back.putExtra("monthlyedit", monthlyedit);
					back.putExtra("currenycode", currencycode1);
					startActivity(back);
					finish();
	        		
	        	}}
	        	else{
	        		System.out.println("inside  list your space  intent in preview page ");
	        	Intent back=new Intent(Preview_page.this,List_your_space.class);
				back.putExtra("image1",image1);
				//back.putExtra("resize",resize);
				back.putExtra("room_id",roomid);
				back.putExtra("image", dis);
				back.putExtra("write_title", dis4);
				back.putExtra("write_summary", dis1);
				back.putExtra("write_price", dis2);
				back.putExtra("city", city);
				back.putExtra("state", state);
				back.putExtra("country", countryy);
				back.putExtra("address", dis3);
				back.putExtra("userid", userid);
				back.putExtra("first", first);
				back.putExtra("second", second);
				back.putExtra("third", third);
				back.putExtra("fourth", fourth);
				back.putExtra("last", last);
				back.putExtra("weeklyedit", weeklyedit);
				back.putExtra("monthlyedit", monthlyedit);
				back.putExtra("currenycode", currencycode1);
				startActivity(back);
				finish();
	       	 
	        	}
			}
		});
		
		
	/****************View Calendar Functionality start************************/
		
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		final CaldroidListener listener1 = new CaldroidListener() {
			
			@Override
			public void onSelectDate(Date date, View view) {
				
	

				 
			}

		@Override
		public void onChangeMonth(int month, int year) {
			String text = "month: " + month + " year: " + year;
		}

		@Override
		public void onLongClickDate(Date date, View view) {
			Toast.makeText(getApplicationContext(),
					"Long click " + formatter.format(date),
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCaldroidViewCreated() {
			if (dialogCaldroidFragment.getLeftArrowButton() != null) {
				
						
			}
		}

	};
	
	
	
	final Bundle state1 = savedInstanceState;
	if(availability!=null)
	{
		availability.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			// Setup caldroid to use as dialog
			dialogCaldroidFragment = new CaldroidFragment();
			dialogCaldroidFragment.setCaldroidListener(listener1);

			// If activity is recovered from rotation
			final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
			if (state1 != null) {
				dialogCaldroidFragment.restoreDialogStatesFromKey(
						getSupportFragmentManager(), state1,
						"DIALOG_CALDROID_SAVED_STATE", dialogTag);
				Bundle args = dialogCaldroidFragment.getArguments();
				if (args == null) {
					args = new Bundle();
					dialogCaldroidFragment.setArguments(args);
				}
				args.putString(CaldroidFragment.DIALOG_TITLE,
						"Select a date");
			} else {
				// Setup arguments
				
				Calendar cal = Calendar.getInstance();
				// Min date is last 7 days
		 		cal.add(Calendar.DATE, 0);
		 		System.out.println("cal min value"+cal);
				Date minDate = cal.getTime();
				System.out.println("Min date"+minDate);
				dialogCaldroidFragment.setMinDate(minDate);
				
				
								
                //Setup Dialog
				Bundle bundle = new Bundle();
				// Setup dialogTitle
				bundle.putString(CaldroidFragment.DIALOG_TITLE,
						"Availability");
				bundle.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
				bundle.putBoolean(CaldroidFragment.SHOW_NAVIGATION_ARROWS, true);
				bundle.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, false);
				
				dialogCaldroidFragment.setArguments(bundle);
			}

			dialogCaldroidFragment.show(getSupportFragmentManager(),
					dialogTag);
		}
	});
	}
	
	/****************View Calendar Functionality end************************/



	
		
	}
	public void call_webservice()
	{
		//final String url=Liveurl+"rooms/preview?roomid="+roomid+"&image_id="+image1;	
		final String url=Liveurl+"rooms/preview?roomid="+roomid;	

		System.out.println("URL is"+url);
		 // Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						 

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i); 
								 address = obj.getString("address");
						          userid =    obj.getString("user_id");
						          country=obj.getString("country");
						          title=obj.getString("title");
						          room_type=obj.getString("room_type");
						         bedrooms= obj.getString("bedrooms");
						         beds= obj.getString("beds");
						         bathrooms=   obj.getString("bathrooms");
						          bed_type=obj.getString("bed_type");
						          amenities=obj.getString("amenities");
						          desc=obj.getString("desc");
						         price= obj.getString("price");         
						         capacity=obj.getString("capacity");
						        // name= login_jsonobj.getString("name");
						         week= obj.getString("week");
						         month= obj.getString("month");
						         Fname1= obj.getString("Fname");
						         bimage=obj.getString("image");
						         //map=obj.getString("map");         
						          email=obj.getString("email");
						         //src= obj.getString("src");
						         currency_symbol= obj.getString("currency_code");
						        // currenycode=obj.getString("country_symbol");
						         
									Currency c  = Currency.getInstance(currency_symbol);    
									currency_symbol=c.getSymbol();
						         
						       /* try{
						 			mapimage=new URL(map);
						 			mapbitmap = BitmapFactory.decodeStream(mapimage.openStream());
						 			preview_map.setImageBitmap(mapbitmap);
						 			preview_map.setScaleType(ScaleType.FIT_XY);
						 			}
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}*/
						 		
						 		try{
						 			bigimage=new URL(bimage);
						 			imagebitmap = BitmapFactory.decodeStream(bigimage.openStream());
						 			bg_image.setImageBitmap(imagebitmap);
						 			bg_image.setScaleType(ScaleType.FIT_XY);
						 			}
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}
						 		
						 		/*try{
						 			pimage=new URL(src);
						 			profilebitmap = BitmapFactory.decodeStream(pimage.openStream());
						 			profile_image.setImageBitmap(getRoundedShape(profilebitmap));
						 			profile_image.setScaleType(ScaleType.FIT_XY);
						 			more_profile_image.setImageBitmap(getRoundedShape(profilebitmap));
						 			more_profile_image.setScaleType(ScaleType.FIT_XY);
						 			}
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}*/
						 		
						 		System.out.println("title preview page "+title);
						 		System.out.println("address "+address);
						 		System.out.println("title "+Fname1);
						 		
						 		if(title.equals("null")||title.equals("Null"))
						 		{
						 			headtitle.setText("");
						 			
						 		}
						 		else{
						 			headtitle.setText(title);
						 			title1.setText(title);
						 		}
						 		
						 		 if(address.equals("null"))
						 		{
						 			head_address.setText("");
						 			address1.setText("");
						 		}
						 		 else{
						 			 address1.setText(address);
						 			 head_address.setText(address);
						 			}
						 		 if(Fname1.equals("null"))
						 		{
						 			name1.setText("");
						 			more_name.setText("");
						 		}else
						 			{
						 			more_name.setText(Fname1);
						 			name1.setText(Fname1);
						 			}
						 				
						 		 if(desc.equals("null"))	
						 		{
						 			desc1.setText("");
						 			
						 		}
						 		else
						 		{
						 			desc1.setText(desc);
						 		}
						 		 
						 		 int price2=Integer.parseInt(price);
						 			if(price2==0){
						 				price1.setText("10");
						 			}
						 			else
						 			{
						 				price1.setText(price);
						 			}
						 		   // Currency c  = Currency.getInstance(currency_symbol);    
						 		    
						 		    //currency_symbol=c.getSymbol();
                                 //Currency c  = Currency.getInstance(currency_symbol);
                                 //currency_symbol=c.getSymbol();
                                		 
						 		    
						 		    //currency_symbol=c.getSymbol()
						 		if(currenycode==null)	
                                  {
                                		symbol.setText("$");

                                  }
                                 else
                                 {
                                		symbol.setText(currenycode);
                                 }
						 		
						 		//symbol.setText(currency_symbol);		
						 		hometype.setText(room_type);	
						 		icon_guests.setText(capacity);
						 		icon_rooms.setText(bedrooms);
						 		icon_beds.setText(beds);		
						 		desc1.setText(desc);
						 		listingtype.setText(room_type);
						 		accomodates1.setText(capacity);
						 		bedrooms1.setText(bedrooms);
						 		beds1.setText(beds);
						 		bathrooms1.setText(bathrooms);
						 		currentpolicy.setText("Flexible");
						 		availability.setText("View calender");
						 		//amenities1.setText(amenities);		
						 		more_country.setText(address);
						 		
							}
							
						    
							catch (JSONException e) {
								e.printStackTrace();
							}
							
						}
						 //hidePDialog();
						
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
	public void profile()
	{
		//final String url=Liveurl+"rooms/preview?roomid="+roomid+"&image_id="+image1;	
		final String url=Liveurl+"user/view_profile?user_id="+User_id+"&email="+Email_id;

		System.out.println("URL is"+url);
		 // Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						 

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i); 
								userimg=obj.getString("profile_pic");
								 /*address = obj.getString("address");
						          userid =    obj.getString("user_id");
						          country=obj.getString("country");
						          title=obj.getString("title");
						          room_type=obj.getString("room_type");
						         bedrooms= obj.getString("bedrooms");
						         beds= obj.getString("beds");
						         bathrooms=   obj.getString("bathrooms");
						          bed_type=obj.getString("bed_type");
						          amenities=obj.getString("amenities");
						          desc=obj.getString("desc");
						         price= obj.getString("price");         
						         capacity=obj.getString("capacity");
						        // name= login_jsonobj.getString("name");
						         week= obj.getString("week");
						         month= obj.getString("month");
						         Fname1= obj.getString("Fname");
						         bimage=obj.getString("image");
						         //map=obj.getString("map");         
						          email=obj.getString("email");
						         //src= obj.getString("src");
						         currency_symbol= obj.getString("currency_code");
						        // currenycode=obj.getString("country_symbol");
						         
									Currency c  = Currency.getInstance(currency_symbol);    
									currency_symbol=c.getSymbol();*/
						         
						        /*try{
						 			mapimage=new URL(map);
						 			mapbitmap = BitmapFactory.decodeStream(mapimage.openStream());
						 			preview_map.setImageBitmap(mapbitmap);
						 			preview_map.setScaleType(ScaleType.FIT_XY);
						 			}
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}
						 		
						 		try{
						 			bigimage=new URL(bimage);
						 			imagebitmap = BitmapFactory.decodeStream(bigimage.openStream());
						 			bg_image.setImageBitmap(imagebitmap);
						 			bg_image.setScaleType(ScaleType.FIT_XY);
						 			}
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}*/
						 		
						 		try{
						 			pimage=new URL(userimg);
						 			profilebitmap = BitmapFactory.decodeStream(pimage.openStream());
						 			profile_image.setImageBitmap(getRoundedShape(profilebitmap));
						 			profile_image.setScaleType(ScaleType.FIT_XY);
						 			more_profile_image.setImageBitmap(getRoundedShape(profilebitmap));
						 			more_profile_image.setScaleType(ScaleType.FIT_XY);
						 			}
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}
						 		
						 		/*System.out.println("title preview page "+title);
						 		System.out.println("address "+address);
						 		System.out.println("title "+Fname1);
						 		
						 		if(title.equals("null")||title.equals("Null"))
						 		{
						 			headtitle.setText("");
						 			
						 		}
						 		else{
						 			headtitle.setText(title);
						 			title1.setText(title);
						 		}
						 		
						 		 if(address.equals("null"))
						 		{
						 			head_address.setText("");
						 			address1.setText("");
						 		}
						 		 else{
						 			 address1.setText(address);
						 			 head_address.setText(address);
						 			}
						 		 if(Fname1.equals("null"))
						 		{
						 			name1.setText("");
						 			more_name.setText("");
						 		}else
						 			{
						 			more_name.setText(Fname1);
						 			name1.setText(Fname1);
						 			}
						 				
						 		 if(desc.equals("null"))	
						 		{
						 			desc1.setText("");
						 			
						 		}
						 		else
						 		{
						 			desc1.setText(desc);
						 		}
						 		 
						 		 int price2=Integer.parseInt(price);
						 			if(price2==0){
						 				price1.setText("10");
						 			}
						 			else
						 			{
						 				price1.setText(price);
						 			}
						 		   // Currency c  = Currency.getInstance(currency_symbol);    
						 		    
						 		    //currency_symbol=c.getSymbol();
                                 //Currency c  = Currency.getInstance(currency_symbol);
                                 //currency_symbol=c.getSymbol();
                                	if(currenycode==null)	
                                	{
                                		symbol.setText("$");

                                	}
                                	else
                                	{
                                		symbol.setText(currenycode);
                                	}
						 		    //currency_symbol=c.getSymbol()
						 		
						 		//symbol.setText(currency_symbol);		
						 		hometype.setText(room_type);	
						 		icon_guests.setText(capacity);
						 		icon_rooms.setText(bedrooms);
						 		icon_beds.setText(beds);		
						 		desc1.setText(desc);
						 		listingtype.setText(room_type);
						 		accomodates1.setText(capacity);
						 		bedrooms1.setText(bedrooms);
						 		beds1.setText(beds);
						 		bathrooms1.setText(bathrooms);
						 		currentpolicy.setText("Flexible");
						 		availability.setText("View calender");
						 		//amenities1.setText(amenities);		
						 		more_country.setText(address);*/
						 		
							}
							
						    
							catch (JSONException e) {
								e.printStackTrace();
							}
							
						}
						 //hidePDialog();
						
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
	public void view_description()
	{
		final String url=Liveurl+"rooms/view_description?room_id="+roomid;	
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
						          space = obj.getString("space");
						          guests_info=obj.getString("guests_info");
						          interaction=obj.getString("interaction");
						          overview=obj.getString("overview");
						          getting_around=obj.getString("getting_around");
						          other_thing=obj.getString("other_thing");
						          house_rule=obj.getString("house_rule");
						          
						          readmore.setOnClickListener(new View.OnClickListener() {
						  			
						  			@Override
						  			public void onClick(View v) {
						  				
						  				// custom dialog
						  				final Dialog dialog = new Dialog(Preview_page.this);
						  				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						  				dialog.setContentView(R.layout.read_more);
						  				
						  				TextView Description = (TextView) dialog.findViewById(R.id.textView1);
						  				//TextView text2 = (TextView) dialog.findViewById(R.id.TextView2);
						  				TextView spaace= (TextView) dialog.findViewById(R.id.textView3);
						  				TextView Desc_space = (TextView) dialog.findViewById(R.id.textView4);
						  				TextView gueest= (TextView) dialog.findViewById(R.id.textView5);
						  				TextView Desc_guests= (TextView) dialog.findViewById(R.id.textView6);
						  				TextView interract= (TextView) dialog.findViewById(R.id.textView7);
						  				TextView Desc_interaction = (TextView) dialog.findViewById(R.id.textView8);
						  				TextView oveerview= (TextView) dialog.findViewById(R.id.textView9);
						  				TextView Desc_overview= (TextView) dialog.findViewById(R.id.textView10);
						  				TextView arround= (TextView) dialog.findViewById(R.id.textView11);
						  				TextView Desc_around= (TextView) dialog.findViewById(R.id.textView12);
						  				TextView otther= (TextView) dialog.findViewById(R.id.textView13);
						  				TextView Desc_other= (TextView) dialog.findViewById(R.id.textView14);
						  				TextView hoouse= (TextView) dialog.findViewById(R.id.textView15);
						  				TextView Desc_house= (TextView) dialog.findViewById(R.id.textView16);
						  				System.out.println("description space"+space);
						  			    System.out.println("description guests_info"+guests_info);
						  			    System.out.println("description interaction"+interaction);
						  			    System.out.println("description overview"+overview);
						  			    System.out.println("description getting_around"+getting_around);
						  			    System.out.println("description other_thing"+other_thing);
						  			    System.out.println("description house_rule"+house_rule);
						  				
						  			    if(space.isEmpty()&&guests_info.isEmpty()&&interaction.isEmpty()&&overview.isEmpty()&&getting_around.isEmpty()&&other_thing.isEmpty()&&house_rule.isEmpty())
						  			    {
						  			    	spaace.setText("No More Description to Display");
						  			    	spaace.setTextColor(Color.parseColor("#666666"));
						  			    	spaace.setTextSize(17);
						  			    	spaace.setTypeface(null, Typeface.BOLD);
						  			    	Desc_space.setVisibility(View.GONE);
						  			    	gueest.setVisibility(View.GONE);
						  			    	Desc_guests.setVisibility(View.GONE);
						  			    	interract.setVisibility(View.GONE);
						  			    	Desc_interaction.setVisibility(View.GONE);
						  			    	oveerview.setVisibility(View.GONE);
						  			    	Desc_overview.setVisibility(View.GONE);
						  			    	arround.setVisibility(View.GONE);
						  			    	Desc_around.setVisibility(View.GONE);
						  			    	otther.setVisibility(View.GONE);
						  			    	Desc_other.setVisibility(View.GONE);
						  			    	hoouse.setVisibility(View.GONE);
						  			    	Desc_house.setVisibility(View.GONE);
						  			    }
						  			    else
						  			    {
						  			    if(space.equals("null"))
						  			    {
						  			    	spaace.setVisibility(View.GONE);
						  			    	Desc_space.setVisibility(View.GONE);
						  			    }
						  			    else
						  			    {
						  			    	Desc_space.setText(space);
						  			    }
						  			    if(guests_info.equals("null"))
						  			    {
						  			    	gueest.setVisibility(View.GONE);
						  			    	Desc_guests.setVisibility(View.GONE);
						  			    }
						  			    else
						  			    {
						  			    	Desc_guests.setText(guests_info);
						  			    }
						  			    if(interaction.equals("null"))
						  			    {
						  			    	interract.setVisibility(View.GONE);
						  			    	Desc_interaction.setVisibility(View.GONE);
						  			    	
						  			    }
						  			    else
						  			    {
						  			    	Desc_interaction.setText(interaction);
						  			    }
						  			    if(overview.equals("null"))
						  			    {
						  			    	
						  			    	oveerview.setVisibility(View.GONE);
						  			    	Desc_overview.setVisibility(View.GONE);
						  			    }
						  			    else
						  			    {
						  			    	Desc_overview.setText(overview);
						  			    }
						  			    if(getting_around.equals("null"))
						  			    {
						  			    	arround.setVisibility(View.GONE);
						  			    	Desc_around.setVisibility(View.GONE);
						  			    }
						  			    else
						  			    {
						  			    	
						  			    	Desc_around.setText(getting_around);
						  			    }
						  			    if(other_thing.equals("null"))
						  			    {
						  			    	otther.setVisibility(View.GONE);
						  			    	Desc_other.setVisibility(View.GONE);
						  			    }
						  			    else
						  			    {
						  			    	
						  			    	Desc_other.setText(other_thing);
						  			    }
						  			    if(house_rule.equals("null"))
						  			    {
						  			    	
						  			    	hoouse.setVisibility(View.GONE);
						  			    	Desc_house.setVisibility(View.GONE);
						  			    }
						  			    else
						  			    {
						  			    	Desc_house.setText(house_rule);
						  			    }
						  			    }
						  				
						  				dialog.show();
						  				
						  				dialog.setCanceledOnTouchOutside(true);
						  				Window window = dialog.getWindow();
						  				window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						  				//dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,800);
						  						
						  			}
						         
						  				
						  			
						  		});
						         
							}
							
						    
							catch (JSONException e) {
								e.printStackTrace();
							}
							
						}
						 //hidePDialog();
						
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

	public void view_policy()
	{
		final String url=Liveurl+"payment/cancellation_policy1?";
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
								cancellation_title= obj.getString("cancellation_title");
							    cancellation_content=obj.getString("cancellation_content");
							    
							    
							    //setting the values
							    currentpolicy.setOnClickListener(new View.OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										
										final Dialog dialog1 = new Dialog(Preview_page.this);
										dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
										dialog1.setContentView(R.layout.cancellation_policy);
										
										TextView cancellation_policy = (TextView) dialog1.findViewById(R.id.textView1);
										TextView type= (TextView) dialog1.findViewById(R.id.textView3);
										TextView content = (TextView) dialog1.findViewById(R.id.textView4);
										
										type.setText(cancellation_title);
										content.setText(cancellation_content);
										
										dialog1.show();
										
										Window window = dialog1.getWindow();
										window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
										dialog1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,820);
										}
								});
								
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
	


@Override
public void onBackPressed()
{
	
}
private class ProgressTask extends AsyncTask<String, Void, Boolean> 
{
    private ProgressDialog dialog;
    private Preview_page activity;

    public ProgressTask(Preview_page activity) 
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
       	 
    	
        	
       	
            return true;
        } catch (Exception e){
            Log.e("Schedule", "UpdateSchedule failed", e);
            return false;
        }
    }
}

@Override
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