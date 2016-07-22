package com.cog.DropInn;


import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.appkraft.parallax.ParallaxScrollView;

import org.apache.commons.lang.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

public class Detail_page extends FragmentActivity {
	public static final String PREFS_NAME = "MyPrefsFile";
	 Bitmap scaled;
	TextView symbol,price1,title1,hometype,name1,icon_guests,icon_rooms,icon_beds,address1,desc1,listingtype,accomodates1,beds1,bedrooms1,bathrooms1;
	TextView currentpolicy,availability,amenities1,more_name,more_country,headtitle,head_address;
	String User_id,profile_image1,addressp,imagep,titlep,imagep1;
	String profile_image12;
	ImageView bg_image,profile_image,more_profile_image,preview_map;
	ImageButton back;
	Button contact,readmore,morehost;
	URL pimage,mapimage,bigimage;
	String Liveurl="";
	URL Login_Url,Login_Url1,Login_Url2,Login_Url3;
	Bitmap mapbitmap,imagebitmap,profilebitmap;
	private ProgressDialog pDialog;	
   	boolean processClick=true;
   private String userid,country,room_type,bedrooms,beds,bathrooms,bed_type,amenities,title,desc,price,capacity,name,week,month,email,Fname1,src,currency_symbol,map,bimage;
   String other_userid,userid1,currency1,currencycode, common_currency_value;
   String id,space,guests_info,interaction,overview,getting_around,other_thing,house_rule,hometype1;
   private String address;
   String checkin[]=new String[50],checkout[]=new String[50];
   private String country_symbol;
	Toast toast;
	int n;
	Dialog dialog1;
	String bedss,bedroomss,bathroomss,bed_typee,capacityy,descriptionn,fnamee,profimage;
	   private List<Movie> movieItems;
	protected static final String TAG = null;
String roomid,cancellation_title,cancellation_content,commoncurrency;
Button book;
RelativeLayout footer;
ParallaxScrollView parallax;
ImageView bg_image1,bg_image2;
TextView Accomodates,Minimum,accomodates12;
TextView location,city,guest,room,bed,additionalprices;
String city1,symbol1;
ImageView share;
Button facebook, twitter;
 String weeklystr;
 String monthlystr;
 Dialog dialog12;
private CaldroidFragment dialogCaldroidFragment;
TextView monthly,weekly;
ImageView close;
String email_id,previewstring;
String dis1,dis2,state,countryy,dis3,dis4,image1,resize,first,second,third,fourth,last,editlist,weeklyedit,monthlyedit,dis,currencycode1,step,check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_page);
		getActionBar().hide();
		
		

		
		// Showing progress dialog before making http request
			pDialog = new ProgressDialog(this);
			pDialog.setCancelable(false);
			pDialog.show();
			pDialog.setContentView(R.layout.progress_dialog);
			
			
		 if( Build.VERSION.SDK_INT >= 9){
             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
             StrictMode.setThreadPolicy(policy);
      }
		 address1=(TextView)findViewById(R.id.textView11);
		book=(Button)findViewById(R.id.buttonfooter);		 
		 footer=(RelativeLayout)findViewById(R.id.footer);		 
		 bg_image=(ImageView)findViewById(R.id.imageView2);
		 bg_image1=(ImageView)findViewById(R.id.imageView2A);
		 bg_image2=(ImageView)findViewById(R.id.imageView2B);
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
		 location=(TextView)findViewById(R.id.s_textView21);
		 city=(TextView)findViewById(R.id.TextVieww);
		 accomodates12=(TextView)findViewById(R.id.s_textView5);
		 //bedrooms1=(TextView)findViewById(R.id.s_textView7);
	//f	 beds1=(TextView)findViewById(R.id.s_textView9);
		 bathrooms1=(TextView)findViewById(R.id.s_textView7);
		 currentpolicy=(TextView)findViewById(R.id.s_textView16);
		 availability=(TextView)findViewById(R.id.s_textView18);
		 //amenities1=(TextView)findViewById(R.id.s1_textView1);
		 more_name=(TextView)findViewById(R.id.s_textView20);
		 more_country=(TextView)findViewById(R.id.s_textView19);
		 headtitle=(TextView)findViewById(R.id.textView16);
		 head_address=(TextView)findViewById(R.id.textView18);		
		 back=(ImageButton)findViewById(R.id.imageButton2);
		 Accomodates=(TextView)findViewById(R.id.s_textView9);
		 Minimum=(TextView)findViewById(R.id.s_textView11);
		 guest=(TextView)findViewById(R.id.textView8);
		 room=(TextView)findViewById(R.id.textView9);
		 bed=(TextView)findViewById(R.id.textView10);
		 share=(ImageView)findViewById(R.id.imageView11);
		additionalprices=(TextView)findViewById(R.id.s_textView14);
		// Accomodates=(TextView)findViewById(R.id.s_textView9);
		// parallax = (ParallaxScrollView) findViewById(R.id.scrollView1);
	//****************************************************************************	
		
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null); 
         currencycode= sharedPreferences.getString("currenycode", null);
         profile_image1=sharedPreferences.getString("profile", null);
         //result=sharedPreferences.getString("result", null);
         currency1= sharedPreferences.getString("currency1", null);
         email_id=sharedPreferences.getString("emailid", null);
         
         name1.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
         
         TextView TextView1=(TextView)findViewById(R.id.textView3);
         TextView TextView2=(TextView)findViewById(R.id.listingtype);
         TextView TextView3=(TextView)findViewById(R.id.s_textView4);
         TextView TextView4=(TextView)findViewById(R.id.s_textView6);
         TextView TextView5=(TextView)findViewById(R.id.s_textView8);
         TextView TextView6=(TextView)findViewById(R.id.s_textView10);
         TextView TextView7=(TextView)findViewById(R.id.s_textView13);
         TextView TextView8=(TextView)findViewById(R.id.s_textView15);
         TextView TextView9=(TextView)findViewById(R.id.s_textView17);
         TextView TextView10=(TextView)findViewById(R.id.s_textView22);
        
         
     	/*Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Thonburi.ttf");
         
     	symbol.setTypeface(typeFace);
     	price1.setTypeface(typeFace);
     	title1.setTypeface(typeFace);
     	hometype.setTypeface(typeFace);
     	name1.setTypeface(typeFace);
     	icon_guests.setTypeface(typeFace);
     	icon_rooms.setTypeface(typeFace);
     	icon_beds.setTypeface(typeFace);
     	address1.setTypeface(typeFace);
     	desc1.setTypeface(typeFace);
     	listingtype.setTypeface(typeFace);
     	//accomodates1.setTypeface(typeFace);
     	//beds1.setTypeface(typeFace);
     	//bedrooms1.setTypeface(typeFace);
     	bathrooms1.setTypeface(typeFace);
     	
     	currentpolicy.setTypeface(typeFace);
        availability.setTypeface(typeFace);
       // amenities1.setTypeface(typeFace);
        more_name.setTypeface(typeFace);
        more_country.setTypeface(typeFace);
        headtitle.setTypeface(typeFace);
        head_address.setTypeface(typeFace);
        Accomodates.setTypeface(typeFace);
        Minimum.setTypeface(typeFace);
        accomodates12.setTypeface(typeFace);
        location.setTypeface(typeFace);
        city.setTypeface(typeFace);
        guest.setTypeface(typeFace);
        room.setTypeface(typeFace);
        bed.setTypeface(typeFace);
        additionalprices.setTypeface(typeFace);
        TextView1.setTypeface(typeFace);
        TextView2.setTypeface(typeFace);
        TextView3.setTypeface(typeFace);
        TextView4.setTypeface(typeFace);
        TextView5.setTypeface(typeFace);
        TextView6.setTypeface(typeFace);
        TextView7.setTypeface(typeFace);
        TextView8.setTypeface(typeFace);
        TextView9.setTypeface(typeFace);
        TextView10.setTypeface(typeFace);     
         */
         if(currency1==null)
 		{
 			currency1="USD";
 		}
		 
		Intent i=getIntent();
		roomid=i.getStringExtra("room_id");
		addressp=i.getStringExtra("address");
		titlep=i.getStringExtra("title");
		imagep=i.getStringExtra("image");
		imagep1=i.getStringExtra("resize1");
		profimage=i.getStringExtra("profimage");
		price= i.getStringExtra("price");
		hometype1= i.getStringExtra("room_type");
		city1 = i.getStringExtra("city");
		symbol1 = i.getStringExtra("symbol");
		previewstring = i.getStringExtra("previewstring");
		if (addressp!=null)
		{
			if (!addressp.equals("null"))
			{
				addressp=addressp.replaceAll("%20","");
			address1.setText(addressp);
			}
		}
		//address1.setText(addressp);
		
		
		if (titlep!=null)
		{
			if (!titlep.equals("null"))
			{
				titlep=titlep.replaceAll("%20"," ");
				title1.setText(titlep);
			}
		}
		
		if (price!=null)
		{
			if (!price.equals("null")&& !price.equals("0"))
			{
				price1.setText(price);
			}
		}
		
	
		
	
		
		if (symbol1!=null)
		{
			if (!symbol1.equals("null"))
			{
				symbol.setText(symbol1);
			}
		}
		
	
		
		//title1.setText(titlep);
	
		//price1.setText(price);
	
		System.out.println("home type" +hometype1);
		//hometype.setText(hometype1);
		
		
		//city.setText(city1);
	
		//symbol.setText(symbol1);
		dis=i.getStringExtra("image");
		dis1=i.getStringExtra("summary");
		dis2=i.getStringExtra("price");
		System.out.println("check price"+dis2);
		
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
		step=i.getStringExtra("step");
		check=i.getStringExtra("check");
		
		if (dis1!=null)
		{
			if (!dis1.equals("null"))
			{
				dis1=dis1.replaceAll("%20"," ");
			
			}
		}
		
		if (dis3!=null)
		{
			if (!dis3.equals("null"))
			{
				dis3=dis3.replaceAll("%20"," ");
			
			}
		}
		
		if (dis4!=null)
		{
			if (!dis4.equals("null"))
			{
				dis4=dis4.replaceAll("%20"," ");
			
			}
		}
		
additionalprices.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 dialog12 = new Dialog(Detail_page.this);
				dialog12.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog12.setContentView(R.layout.additionalprices);
				dialog12.show();
				
				pDialog = new ProgressDialog(Detail_page.this);
				pDialog.setCancelable(false);
				pDialog.show();
				pDialog.setContentView(R.layout.progress_dialog1);
			
				
			 weekly=(TextView)dialog12.findViewById(R.id.textview4);		 
		 monthly=(TextView)dialog12.findViewById(R.id.textview5);
		/* Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Thonburi.ttf");
		   monthly.setTypeface(typeFace);
	        weekly.setTypeface(typeFace);*/
				TextView weeklys=(TextView)dialog12.findViewById(R.id.textview7);	
				TextView monthlys=(TextView)dialog12.findViewById(R.id.textview6);
				/* monthlys.setTypeface(typeFace);
			        weeklys.setTypeface(typeFace);*/
				if (symbol1!=null)
				{
				weeklys.setText(symbol1);
				monthlys.setText(symbol1);
				}
				else if (symbol1==null)
				{
					weeklys.setText("$");
					monthlys.setText("$");
				}
				 close=(ImageView)dialog12.findViewById(R.id.s_imageView7);
				
				close.setOnClickListener(new OnClickListener()
	            {
	                @Override
	                public void onClick(View v)
	                {
	                        dialog12.dismiss();
	                }
	            });
			   
				
				if(currency1==null)
				{
					currency1="USD";
				}
		        //final String url = Liveurl+"search/discover";
				 final String url=Liveurl+"user/longtermconvert?roomid="+roomid+"&common_currency="+currency1;
		       System.out.println("discover url" +url);
				 //final String url=Liveurl+"search/discover";
		//*******************************************ListView code start*****************************************************
		     
		 
		      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
		        
			

				// Creating volley request obj
				
				JsonArrayRequest movieReq = new JsonArrayRequest(url,
						new Response.Listener<JSONArray>() {
							@Override
							public void onResponse(JSONArray response) {
								Log.d(TAG, response.toString());
								
								
							

								// Parsing json
								for (int i = 0; i < response.length(); i++) {
									try {

										
										/*if(i!=0||i!=1||i!=3)
										{*/
										
									
										
										JSONObject obj = response.getJSONObject(i);
											
										weeklystr=obj.getString("week");
										monthlystr=obj.getString("month");
										weekly.setText(" "+weeklystr);
										monthly.setText(" "+monthlystr);
										// adding movie to movies array
										hidePDialog();
									
									//footerView.setVisibility(View.VISIBLE);
										//}
										/*else
										{
											JSONObject obj = response.getJSONObject(i);
											Movie movie = new Movie();
											movie.getid(obj.getString("id"));
											movieList.add(movie);
										}*/
										
									} catch (JSONException e) {	
										e.printStackTrace();
										hidePDialog();
									}

								}

								
								// notifying list adapter about data changes
								// so that it renders the list view with updated data
								//adapter.notifyDataSetChanged();
							}
						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								VolleyLog.d(TAG, "Error: " + error.getMessage());
								hidePDialog();
								

							}
						});

				// Adding request to request queue
				AppController.getInstance().addToRequestQueue(movieReq);
				
				System.out.println("after loop listview view ");
				
				
					
				    }
			
			
});
		

		
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Dialog dialog = new Dialog(Detail_page.this);
				dialog.setContentView(R.layout.sharing);
				dialog.setTitle("Share using");
				
				dialog.show();
				facebook=(Button)dialog.findViewById(R.id.imageButton1);		 
				twitter=(Button)dialog.findViewById(R.id.imageButton2A);
				
				 facebook.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if (imagep1!=null)
							{
							//Uri uri = Uri.parse(imagep1);
							
							
							//Intent share = new Intent(Intent.ACTION_SEND);
							
								/*SharePhoto photo = new SharePhoto.Builder()
									        .setBitmap(imagebitmap)
									        .build();
									
									SharePhotoContent content = new SharePhotoContent.Builder()
							        .addPhoto(photo)
							        .build();*/
							
							 Uri bmpUri = getLocalBitmapUri(bg_image);
									
					Intent sharingIntent = new Intent(Intent.ACTION_SEND);
					sharingIntent.setType("image/*");
		            //sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
		            //sharingIntent.putExtra(Intent.EXTRA_TEXT, "Text...");
		            sharingIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
		            System.out.println("imagep" +imagep1);
		            PackageManager packManager = getPackageManager();
		            List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(sharingIntent,  PackageManager.MATCH_DEFAULT_ONLY);
							
		            boolean resolved = false;
		            for(ResolveInfo resolveInfo: resolvedInfoList){
		                if(resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")){
		                    sharingIntent.setClassName(
		                        resolveInfo.activityInfo.packageName, 
		                        resolveInfo.activityInfo.name );
		                    resolved = true;
		                    break;
		                }
		            
		            }
							
		            if(resolved){
		                startActivity(sharingIntent);
		            }else{

		                 Builder alert  = new AlertDialog.Builder(Detail_page.this);
		                    alert.setTitle("Warning");
		                    alert.setMessage("Facebook App not found");
		                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                        public void onClick(DialogInterface dialog,int which) 
		                        {
		                            dialog.dismiss();

		                        }
		                    });
		                    alert.show();
		            } 
						}
							
							else	if (imagep!=null)
							{
							//Uri uri = Uri.parse(imagep1);
							
							
							//Intent share = new Intent(Intent.ACTION_SEND);
							
								/*SharePhoto photo = new SharePhoto.Builder()
									        .setBitmap(imagebitmap)
									        .build();
									
									SharePhotoContent content = new SharePhotoContent.Builder()
							        .addPhoto(photo)
							        .build();*/
							
							 Uri bmpUri = getLocalBitmapUri(bg_image);
									
					Intent sharingIntent = new Intent(Intent.ACTION_SEND);
					sharingIntent.setType("image/*");
		            //sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
		            //sharingIntent.putExtra(Intent.EXTRA_TEXT, "Text...");
		            sharingIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
		            System.out.println("imagep" +imagep1);
		            PackageManager packManager = getPackageManager();
		            List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(sharingIntent,  PackageManager.MATCH_DEFAULT_ONLY);
							
		            boolean resolved = false;
		            for(ResolveInfo resolveInfo: resolvedInfoList){
		                if(resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")){
		                    sharingIntent.setClassName(
		                        resolveInfo.activityInfo.packageName, 
		                        resolveInfo.activityInfo.name );
		                    resolved = true;
		                    break;
		                }
		            
		            }
							
		            if(resolved){
		                startActivity(sharingIntent);
		            }else{

		                 Builder alert  = new AlertDialog.Builder(Detail_page.this);
		                    alert.setTitle("Warning");
		                    alert.setMessage("Facebook App not found");
		                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                        public void onClick(DialogInterface dialog,int which) 
		                        {
		                            dialog.dismiss();

		                        }
		                    });
		                    alert.show();
		            } 
						}
							
							
						}		
		});
				   
				  twitter.setOnClickListener(new OnClickListener() {
						
						@Override
						
						public void onClick(View v) {
							if (imagep1!=null)
							{
							Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
							   shareIntent.setType("image/*");
							   Uri bmpUri = getLocalBitmapUri(bg_image);
							   //shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
							   //shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) v.getTag(R.drawable.ic_launcher));
							    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);

							   PackageManager pm = getPackageManager();
							   List<ResolveInfo> resolvedInfoList = pm.queryIntentActivities(shareIntent,  PackageManager.MATCH_DEFAULT_ONLY);
							   System.out.println("india");
							     for ( ResolveInfo resolveInfo: resolvedInfoList) 
							      {
							        if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android"))
							          {
							             final ActivityInfo activity = resolveInfo.activityInfo;
							             final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
							             shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
							             shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
							             shareIntent.setComponent(name);
							             v.getContext().startActivity(shareIntent);
							            break;
							          }
							        else
							        {
							        	
							        	Toast.makeText(getApplicationContext(), "Twitter App not found",
							        			   Toast.LENGTH_SHORT).show();
							        	   break;
							          
							        }
							      }
						}
							else if (imagep!=null)
							{
							Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
							   shareIntent.setType("image/*");
							   Uri bmpUri = getLocalBitmapUri(bg_image);
							   //shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
							   //shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) v.getTag(R.drawable.ic_launcher));
							    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);

							   PackageManager pm = getPackageManager();
							   List<ResolveInfo> resolvedInfoList = pm.queryIntentActivities(shareIntent,  PackageManager.MATCH_DEFAULT_ONLY);
							   System.out.println("india");
							     for ( ResolveInfo resolveInfo: resolvedInfoList) 
							      {
							        if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android"))
							          {
							             final ActivityInfo activity = resolveInfo.activityInfo;
							             final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
							             shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
							             shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
							             shareIntent.setComponent(name);
							             v.getContext().startActivity(shareIntent);
							            break;
							          }
							        else
							        {
							        	Toast.makeText(getApplicationContext(), "Twitter App not found",
							        			   Toast.LENGTH_LONG).show();
							        	break;
							        }
							      }
						}
							
						}
		});
				
				
			/*	Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/html");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
				startActivity(Intent.createChooser(sharingIntent,"Share using"));*/
				/* Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				   shareIntent.setType("text/plain");
				   shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
				   shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) v.getTag(R.drawable.ic_launcher));
				   //shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

				   PackageManager pm = v.getContext().getPackageManager();
				   List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
				     for (final ResolveInfo app : activityList) 
				     {
				         if ((app.activityInfo.name).contains("facebook")) 
				         {
				           final ActivityInfo activity = app.activityInfo;
				           final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
				          shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				          shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				          shareIntent.setComponent(name);
				          v.getContext().startActivity(shareIntent);
				          break;
				        }
				      }
			*/
				
			
			}
		});
		
			
		
		 
		
		
		//userid=i.getStringExtra("userid");
		System.out.println("after getting room id Detail page"+roomid);
		System.out.println("after getting user id Detail page"+userid);
		SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		   userid = sharedPreferences1.getString("userid",null);
		System.out.println("userid in shared preferences in swipe_tabs "+userid);
		
		 //SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        // Liveurl = sharedPreferences.getString("liveurl", null);
         //commoncurrency=sharedPreferences2.getString(" currency1", null);
	       System.out.println("**********"+currency1);
		 /*SharedPreferences settings = getSharedPreferences(Detail_page.PREFS_NAME,0);
		 userid = settings.getString("userid", User_id);
		 
		 System.out.println("after getting shared preferencec user id Detail page"+userid);*/
		/*call_webservice();*/
		//view_description();
		view_policy();
		call_webservice3();
		if (previewstring!=null)
		{
		if (previewstring.equals("1")|| previewstring.equals("2") )
		{
			profileimage();
			book.setVisibility(View.INVISIBLE);
		}
		}
		
	
		//view_calendar parsing json functionality Start
		final String url=Liveurl+"payment/bookit_date?room_id="+roomid;
		System.out.println("URL is"+url);
		 // Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						 
						// Parsing json
						n=response.length();
						for (int i = 0; i < response.length(); i++) {
							try {
								//getting the values
								JSONObject obj = response.getJSONObject(i); 
								checkin[i]= obj.getString("checkin");  
					        	  checkout[i]=obj.getString("checkout");
					        	  
					        	  for(int i1=0; i1 <n; i1++)
					              {
					                                
					          //id= login_jsonobj1.getString("id");
					          
					          System.out.println("Checkin"+i1+""+checkin[i1]);
					          System.out.println("checkout"+i1+""+checkout[i1]);           
					              }
							    
							   
							}
							
						    
							catch (JSONException e) {
								e.printStackTrace();
							}
							
						}
					
						
					}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				 

			}
		});

	//Adding request to request queue
	AppController.getInstance().addToRequestQueue(movieReq);
	
	//view_calendar parsing json functionality End
		
		
				
		
		profile_image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*profile_image();*/
			}
		});
		more_profile_image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
/*				profile_image();
*/			}
		});
		morehost.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*profile_image();*/
				Intent back=new Intent(Detail_page.this,View_profile.class);
				back.putExtra("userid", userid);
				startActivity(back);
				 finish();
			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (previewstring!=null)
				{
				if (previewstring.equals("1"))
				{
					Intent back=new Intent(Detail_page.this,Edit_list.class);
					
					back.putExtra("image1",image1);
					//back.putExtra("resize",resize);
					back.putExtra("room_id",roomid);
					back.putExtra("image", dis);
					back.putExtra("write_title", dis4);
					back.putExtra("write_summary", dis1);
					back.putExtra("write_price", dis2);
					back.putExtra("city", city1);
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
					back.putExtra("step",step);
					back.putExtra("check",check);
					startActivity(back);
					finish();
				}
				if (previewstring.equals("2"))
				{
					Intent back=new Intent(Detail_page.this,List_your_space.class);
					
					back.putExtra("image1",image1);
					//back.putExtra("resize",resize);
					back.putExtra("room_id",roomid);
					back.putExtra("image", dis);
					back.putExtra("write_title", dis4);
					back.putExtra("write_summary", dis1);
					back.putExtra("write_price", dis2);
					back.putExtra("city", city1);
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
					back.putExtra("step",step);
					back.putExtra("check",check);
					startActivity(back);
					finish();
				}
				}
				else
				{
				Intent back=new Intent(Detail_page.this,Swipe_tabs.class);
				back.putExtra("userid", userid);
				startActivity(back);
				finish();
				}
			}
			
		});
			
		/****************View Calendar Functionality start************************/
		
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		final CaldroidListener listener1 = new CaldroidListener() {
			
			
			public void onSelectDate(Date date,View view) {
				 
				
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
			//Passing check in and check out using webservices
			
			
			availability.setEnabled(false);
			availability.setClickable(false);
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
				
				
				if(checkin!=null&&checkout!=null)
				{
					
				System.out.println("check in date"+checkin);
				System.out.println("check out date"+checkout);
                
				//Disable dates from Check in and Check out				
				
				 ArrayList<Date> dates = new ArrayList<Date>();
				    DateFormat df1 = new SimpleDateFormat("dd-MM-yy");

				    Date date1 = null;
				    Date date2 = null;
				    for(int i=0;i<n;i++)
				    {
				    	System.out.println("check in inside for"+checkin[i]);
				    	System.out.println("check in inside for"+checkout[i]);
				    	if(checkin[i]!=null&&checkout[i]!=null)
		    			{
	
				    try {
				    	
				    		date1 = df1 .parse(checkin[i]);
					        date2 = df1 .parse(checkout[i]);
					        System.out.println("date1"+date1);
					        System.out.println("date2"+date2);	
				    	
					        availability.setEnabled(true);
							availability.setClickable(true);
				    } catch (ParseException e) {
				        e.printStackTrace();
				    }
		    			}

if(date1!=null&&date2!=null)
{
				    Calendar cal1 = Calendar.getInstance();
				    cal1.setTime(date1);


				    Calendar cal2 = Calendar.getInstance();
				    cal2.setTime(date2);

				    while(!cal1.after(cal2))
				    {
				        dates.add(cal1.getTime());
				        cal1.add(Calendar.DATE, 1);
				    }
				    dialogCaldroidFragment.setDisableDates(dates);
}
				}
				    }
				
				
                //Setup Dialog
				Bundle bundle = new Bundle();
				// Setup dialogTitle
				bundle.putString(CaldroidFragment.DIALOG_TITLE,
						"Availability");
				bundle.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 2);
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
		}	
	/****************View Calendar Functionality end************************/
	
		
		
	
	/*public void call_webservice()
	{
		final String url=Liveurl+"rooms/apartment_detail?roomid="+roomid+"&common_currency=";	
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
						          other_userid =    obj.getString("user_id");
						          country=obj.getString("country");
						          title=obj.getString("title");
						          room_type=obj.getString("room_type");
						         bedrooms= obj.getString("bedrooms");
						         beds= obj.getString("beds");
						         bathrooms=obj.getString("bathrooms");
						          bed_type=obj.getString("bed_type");
						          //amenities=login_jsonobj.getString("amenities");
						          desc=obj.getString("desc");
						         price= obj.getString("price");
						         capacity=obj.getString("capacity");
						        // name= login_jsonobj.getString("name");
						         week= obj.getString("week");
						         month= obj.getString("month");
						         Fname1= obj.getString("Fname");	
						         map=obj.getString("map");
						         bimage=obj.getString("image");         
						          email=obj.getString("email");
						         src= obj.getString("src");
						        currency_symbol= obj.getString("currency_code");
						     	Currency c  = Currency.getInstance(currency_symbol);    
							    currency_symbol=c.getSymbol();
							    common_currency_value=obj.getString("price");
						         
						         
						         try{
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
						 			//parallax.setImageViewToParallax(bg_image);
						 			}
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}
						 		
						 		try{
						 			pimage=new URL(src);
						 			profilebitmap = BitmapFactory.decodeStream(pimage.openStream());
						 			profile_image.setImageBitmap(getRoundedShape(profilebitmap));
						 			profile_image.setScaleType(ScaleType.FIT_XY);
						 			more_profile_image.setImageBitmap(getRoundedShape(profilebitmap));
						 			more_profile_image.setScaleType(ScaleType.FIT_XY);
						 			}
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}
						 		
						 		//Capitalizing String using Wordutils Class
								
								title=WordUtils.capitalizeFully(title);
								
								address=WordUtils.capitalizeFully(address);
								room_type=WordUtils.capitalizeFully(room_type);
								Fname1=WordUtils.capitalizeFully(Fname1);
								desc=WordUtils.capitalizeFully(desc);
								
								//Setting Values to TextView
								headtitle.setText(title);
								head_address.setText(address);
								symbol.setText(currency_symbol);
								price1.setText(common_currency_value);
								title1.setText(title);
								hometype.setText(room_type);
								name1.setText(Fname1);
								icon_guests.setText(capacity);
								icon_rooms.setText(bedrooms);
								icon_beds.setText(beds);	
								address1.setText(address);
								desc1.setText(desc);
								listingtype.setText(room_type);
								accomodates1.setText(capacity);
								bedrooms1.setText(bedrooms);
								beds1.setText(beds);
								bathrooms1.setText(bathrooms);
								currentpolicy.setText("Flexible");
								availability.setText("View calender");
								//amenities1.setText(amenities);
								more_name.setText(Fname1);
								more_country.setText(address);
							
							    System.out.println("other user id"+other_userid);
							  
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
	     }*/
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
						          
						          
						          //setting the values
						          readmore.setOnClickListener(new View.OnClickListener() {
						  			
						  			@Override
						  			public void onClick(View v) {
						  				
						  				// custom dialog
						  				final Dialog dialog = new Dialog(Detail_page.this);
						  				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						  				dialog.setContentView(R.layout.read_more);
						  				
						  				
						  				TextView Description = (TextView) dialog.findViewById(R.id.textView1);
						  				//TextView text2 = (TextView) dialog.findViewById(R.id.textView2);
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
						  			    if(!space.isEmpty())
						  			    {
						  			    	Desc_space.setText(space);
						  			    }
						  			    else if(space.equals("null")||space.equals("0"))
						  			    {
						  			    	spaace.setVisibility(View.GONE);
						  			    	Desc_space.setVisibility(View.GONE);
						  			    }
						  			    if(!guests_info.isEmpty())
						  			    {
						  			    	Desc_guests.setText(guests_info);
						  			    }
						  			    else if(guests_info.equals("null")||guests_info.equals("0"))
						  			    {
						  			    	gueest.setVisibility(View.GONE);
						  			    	Desc_guests.setVisibility(View.GONE);
						  			    }
						  			    if(!interaction.isEmpty())
						  			    {
						  			    	Desc_interaction.setText(interaction);
						  			    }
						  			    else if(interaction.equals("null")||interaction.equals("0"))
						  			    {
						  			    	interract.setVisibility(View.GONE);
						  			    	Desc_interaction.setVisibility(View.GONE);
						  			    }
						  			    if(!overview.isEmpty())
						  			    {
						  			    	Desc_overview.setText(overview);
						  			    }
						  			    else if(overview.equals("null")||overview.equals("0"))
						  			    {
						  			    	oveerview.setVisibility(View.GONE);
						  			    	Desc_overview.setVisibility(View.GONE);
						  			    }
						  			    if(!getting_around.isEmpty())
						  			    {
						  			    	Desc_around.setText(getting_around);
						  			    }
						  			    else if(getting_around.equals("null")||getting_around.equals("0"))
						  			    {
						  			    	arround.setVisibility(View.GONE);
						  			    	Desc_around.setVisibility(View.GONE);
						  			    }
						  			    if(!other_thing.isEmpty())
						  			    {
						  			    	Desc_other.setText(other_thing);
						  			    }
						  			    else if(other_thing.equals("null")||other_thing.equals("0"))
						  			    {
						  			    	otther.setVisibility(View.GONE);
						  			    	Desc_other.setVisibility(View.GONE);
						  			    }
						  			    if(!house_rule.isEmpty())
						  			    {
						  			    	Desc_house.setText(house_rule);
						  			    }
						  			    else if(house_rule.equals("null")||house_rule.equals("0"))
						  			    {
						  			    	hoouse.setVisibility(View.GONE);
						  			    	Desc_house.setVisibility(View.GONE);
						  			    }
						  			    }
						  				
						  			   
						  				dialog.show();
						  				dialog.setCancelable(true);
						  				
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
										
										final Dialog dialog1 = new Dialog(Detail_page.this);
										dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
										dialog1.setContentView(R.layout.cancellation_policy);
										
										TextView cancellation_policy = (TextView) dialog1.findViewById(R.id.textView1);
										TextView type= (TextView) dialog1.findViewById(R.id.textView3);
										TextView content = (TextView) dialog1.findViewById(R.id.textView4);
										
										type.setText(Html.fromHtml(cancellation_title));
										content.setText(Html.fromHtml(cancellation_content));
										
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
						// hidePDialog();
						
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
	


public void onClick(View v) {
	
	if(userid!=null)
	{
	
	if(userid.equals(other_userid))
	{
		Toast.makeText(getApplicationContext(),
				"Host Cant Book their own list",
				Toast.LENGTH_SHORT).show();  
	}
	else
	{
	System.out.println("inside book on click footer");
	Intent book=new Intent(Detail_page.this,Bookit_page.class);
	book.putExtra("room_id",roomid);
	book.putExtra("userid",userid);
	book.putExtra("symbol",symbol1);
	
	if(imagep!=null)
	{
		book.putExtra("image",imagep);
	}
	
	if(imagep1!=null)
	{
		book.putExtra("image",imagep1);
	}
	
	startActivity(book);
	 finish();
	}
	
	}
	else
	{
		Toast.makeText(getApplicationContext(),
				"You must signin to request a reservation ",
				Toast.LENGTH_SHORT).show();  
		Intent book=new Intent(Detail_page.this,Signin_signup1.class);
		book.putExtra("room_id",roomid);
		book.putExtra("userid",userid);
		book.putExtra("symbol",symbol1);
		
		if(imagep!=null)
		{
			book.putExtra("image",imagep);
		}
		
		if(imagep1!=null)
		{
			book.putExtra("image",imagep1);
		}
				startActivity(book);
				 finish();
	}
}
@Override
public void onBackPressed()
{
	
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
@Override
public void onDestroy() {
	super.onDestroy();
	//hidePDialog();
}

private void hidePDialog() {
	if (pDialog != null) {
		pDialog.dismiss();
		pDialog = null;
	}
 			
}

/*public void profile_image()
{
	Intent profile=new Intent(Detail_page.this,Other_profile.class);
	profile.putExtra("roomid",roomid);
	profile.putExtra("other_userid", other_userid);
	profile.putExtra("userid",userid);
	profile.putExtra("other_email",email);
	startActivity(profile);
}*/



public void call_webservice3()
{
	   
	
	      	final String url=Liveurl+"rooms/apartment_detail?roomid="+roomid+"&common_currency="+currency1;
			System.out.println("URL is"+url);
			 // Creating volley request obj
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {
							Log.d(TAG, response.toString());
							//hidePDialog();

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {

									
									/*if(i!=0||i!=1||i!=3)
									{*/
									JSONObject obj = response.getJSONObject(i);
								
									/*country_symbol=obj.getString("common_currency_code");
									Currency c  = Currency.getInstance(country_symbol);  */  
									other_userid = obj.getString("user_id");
									System.out.println("useridddddddddddddddddddddddddd" +other_userid);
									bedss=obj.getString("beds");
									hometype1=obj.getString("room_type");
									bathroomss=obj.getString("bathrooms");
									bedroomss=obj.getString("bedrooms");
									capacityy=obj.getString("capacity");
									descriptionn=obj.getString("desc");
									bed_typee=obj.getString("bed_type");
									fnamee=obj.getString("Fname");
									map=obj.getString("map");
									addressp=obj.getString("address");
									city1=obj.getString("city");
									String country1=obj.getString("country");
									System.out.println("beds" +bedss);
									System.out.println("country1" +country1);
									System.out.println("map" +map);
									
									if (addressp!=null)
									{
										if (!addressp.equals("null"))
										{
											addressp=addressp.replaceAll("%20","");
										address1.setText(addressp);
										}
									}
									if (hometype1!=null)
									{
										if (!hometype1.equals("null"))
										{
											hometype1=WordUtils.capitalizeFully(hometype1);
											hometype.setText(hometype1);
											listingtype.setText(hometype1);
										}
									}
									
									if (city1!=null)
									{
										if (!city1.equals("null"))
										{
											city.setText(city1);
										}
									}
									//bedrooms1.setText(bedroomss);
									//beds1.setText(bedss);
									if (country1!=null)
									{
										if (!country1.equals("null"))
										{
											location.setText(country1);
										}
									}
									
									
									bathrooms1.setText(bathroomss);
									icon_guests.setText(capacityy);
									Minimum.setText(capacityy);
									Accomodates.setText(capacityy);
									desc1.setText(descriptionn);
									icon_rooms.setText(bedroomss);
									accomodates12.setText(bedroomss);
									icon_beds.setText(bedss);
									//listingtype.setText(bed_typee);
									fnamee=WordUtils.capitalizeFully(fnamee);
									name1.setText("Hosted by "+fnamee);
									//accomodates1.setText(capacityy);
									more_name.setText("About "+fnamee);
									// adding movie to movies array
								if (!capacityy.equals("1"))
								{
									guest.setText("guests");
								}
								
								if (!bedroomss.equals("1"))
								{
									room.setText("rooms");
								}
								
								if (!bedss.equals("1"))
								{
									bed.setText("beds");
								}
								if(map!=null)
								{

									try{
										System.out.println("map");
							 			URL bigimage1=new URL(map);
							 			Bitmap imagebitmap1 = BitmapFactory.decodeStream(bigimage1.openStream());
							 			 Display display1 = getWindowManager().getDefaultDisplay();
								         Point size = new Point();
								         display1.getSize(size);
								         int width = size.x;
								         int height = size.y;
								         Log.e("Screen width ", " "+width);
								         Log.e("Screen height ", " "+height);
								         Log.e("img width ", " "+imagebitmap1.getWidth());
								         Log.e("img height ", " "+imagebitmap1.getHeight());
								         float scaleHt =(float) width/imagebitmap1.getWidth();
								         Log.e("Scaled percent ", " "+scaleHt);
								         Bitmap scaled = Bitmap.createScaledBitmap(imagebitmap1,     width, (int) (imagebitmap1.getWidth()*scaleHt), true);
								         preview_map.setImageBitmap(imagebitmap1);
								      
								     
								        
								       
								       
							 			//parallax.setImageViewToParallax(bg_image);
							 			} 
								
							 			catch(Exception e){
							 				e.printStackTrace();
							 			}
								}
								try{
						 			/*pimage=new URL(profimage);
						 			imagebitmap = BitmapFactory.decodeStream(pimage.openStream());
						 			 
						 			profile_image.setImageBitmap(imagebitmap);
						 			profile_image.setScaleType(ScaleType.FIT_XY);;
						 			parallax.setImageViewToParallax(profile_image);*/
									
									StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						            .permitAll().build();
						    StrictMode.setThreadPolicy(policy);
						    String url =profimage;

						    
						    
						    BitmapFactory.Options bmOptions;
						    bmOptions = new BitmapFactory.Options();	
						    bmOptions.inSampleSize = 1;
						    Bitmap bm = loadBitmap(url, bmOptions);
						    profile_image.setImageBitmap(getRoundedShape(bm));
						    profile_image.setScaleType(ScaleType.FIT_XY);
						 		
						 			} 
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}
								
								try{
						 			/*pimage=new URL(profimage);
						 			imagebitmap = BitmapFactory.decodeStream(pimage.openStream());
						 			 
						 			more_profile_image.setImageBitmap(imagebitmap);
						 			more_profile_image.setScaleType(ScaleType.FIT_XY);;
						 			parallax.setImageViewToParallax(more_profile_image);*/
									
									StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						            .permitAll().build();
						    StrictMode.setThreadPolicy(policy);
						    String url =profimage;

						    
						    
						    BitmapFactory.Options bmOptions;
						    bmOptions = new BitmapFactory.Options();	
						    bmOptions.inSampleSize = 1;
						    Bitmap bm = loadBitmap(url, bmOptions);
						    more_profile_image.setImageBitmap(getRoundedShape(bm));
						    more_profile_image.setScaleType(ScaleType.FIT_XY);
						 			} 
						 			catch(Exception e){
						 				e.printStackTrace();
						 			}
								
								
								if(imagep1!=null)
								{

									try{
							 			bigimage=new URL(imagep1);
							 			imagebitmap = BitmapFactory.decodeStream(bigimage.openStream());
							 			 Display display1 = getWindowManager().getDefaultDisplay();
								         Point size = new Point();
								         display1.getSize(size);
								         int width = size.x;
								         int height = size.y;
								         Log.e("Screen width ", " "+width);
								         Log.e("Screen height ", " "+height);
								         Log.e("img width ", " "+imagebitmap.getWidth());
								         Log.e("img height ", " "+imagebitmap.getHeight());
								         float scaleHt =(float) width/imagebitmap.getWidth();
								         Log.e("Scaled percent ", " "+scaleHt);
								         Bitmap scaled = Bitmap.createScaledBitmap(imagebitmap,     width, (int) (imagebitmap.getWidth()*scaleHt), true);
								         bg_image.setImageBitmap(scaled);
								         bg_image1.setImageBitmap(scaled);
								         bg_image2.setImageBitmap(scaled);
								         bg_image.setScaleType(ScaleType.FIT_XY);
								         bg_image1.setScaleType(ScaleType.FIT_XY);
								         bg_image2.setScaleType(ScaleType.FIT_XY);
							 			//parallax.setImageViewToParallax(bg_image);
							 			} 
							 			catch(Exception e){
							 				e.printStackTrace();
							 			}
								}
								
								if(imagep!=null)
								{

									try{
							 			bigimage=new URL(imagep);
							 			imagebitmap = BitmapFactory.decodeStream(bigimage.openStream());
							 			 Display display1 = getWindowManager().getDefaultDisplay();
								         Point size = new Point();
								         display1.getSize(size);
								         int width = size.x;
								         int height = size.y;
								         Log.e("Screen width ", " "+width);
								         Log.e("Screen height ", " "+height);
								         Log.e("img width ", " "+imagebitmap.getWidth());
								         Log.e("img height ", " "+imagebitmap.getHeight());
								         float scaleHt =(float) width/imagebitmap.getWidth();
								         Log.e("Scaled percent ", " "+scaleHt);
								          scaled = Bitmap.createScaledBitmap(imagebitmap,     width, (int) (imagebitmap.getWidth()*scaleHt), true);
								         bg_image.setImageBitmap(scaled);
								         bg_image1.setImageBitmap(scaled);
								         bg_image.setScaleType(ScaleType.FIT_XY);
								         bg_image1.setScaleType(ScaleType.FIT_XY);
								         bg_image2.setImageBitmap(scaled);
								         bg_image2.setScaleType(ScaleType.FIT_XY);
							 			bg_image.setImageBitmap(imagebitmap);
							 			bg_image.setScaleType(ScaleType.FIT_XY);
							 			//parallax.setImageViewToParallax(bg_image);
							 			} 
							 			catch(Exception e){
							 				e.printStackTrace();
							 			}
									
									
								}
								 hidePDialog();
								} catch (JSONException e) {	
									e.printStackTrace();
								}

							}

							
							// notifying list adapter about data changes
							// so that it renders the list view with updated data
						
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							VolleyLog.d(TAG, "Error: " + error.getMessage());
							hidePDialog();

						}
					});

			// Adding request to request queue
			AppController.getInstance().addToRequestQueue(movieReq);
			
			System.out.println("after loop listview view ");
			
			
				
			
}
/*public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
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
	   return targetBitmap;}
*/
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

public void getFbKeyHash(String packageName) {

		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					packageName,
					PackageManager.GET_SIGNATURES);
			for (android.content.pm.Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("YourKeyHash :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
				System.out.println("YourKeyHash: "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (PackageManager.NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}

	}
public Uri getLocalBitmapUri(ImageView imageView) {
    // Extract Bitmap from ImageView drawable
    Drawable drawable = imageView.getDrawable();
    Bitmap bmp = null;
    if (drawable instanceof BitmapDrawable){
       bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
    } else {
       return null;
    }
    // Store image to default external storage directory
    Uri bmpUri = null;
    try {
        File file =  new File(Environment.getExternalStoragePublicDirectory(  
            Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
        file.getParentFile().mkdirs();
        FileOutputStream out = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
        out.close();
        bmpUri = Uri.fromFile(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return bmpUri;
}
public void profileimage()
{
	  final String url=Liveurl+"user/view_profile?user_id="+userid+"&email="+email_id;
		//*******************************************ListView code start*****************************************************
		       System.out.println("url in  swipe tabs== "+url);

				

				// Creating volley request obj
				JsonArrayRequest movieReq = new JsonArrayRequest(url,
						new Response.Listener<JSONArray>() {
							@Override
							public void onResponse(JSONArray response) {
								Log.d(TAG, response.toString());
							

								// Parsing json
								for (int i = 0; i < response.length(); i++) {
									try {

										JSONObject obj = response.getJSONObject(i);
										//NavDrawerItem movie = new NavDrawerItem();
									
										profimage=obj.getString("profile_pic"); 
										
										/* SharedPreferences sharedPreferences5 = PreferenceManager.getDefaultSharedPreferences(Swipe_tabs.this);
								         Editor editor5 = sharedPreferences5.edit();
								         editor5.putString("profpic", profile_image);  
								         System.out.println("profiley" +profile_image);
								         editor5.commit();*/
										
										if(profimage!=null)
										{
										try{	
											StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							                .permitAll().build();
							        StrictMode.setThreadPolicy(policy);
							        clearApplicationData();
							        String url =profimage;

							       
							        
							        BitmapFactory.Options bmOptions;
							        bmOptions = new BitmapFactory.Options();	
							        bmOptions.inSampleSize = 1;
							        Bitmap bm = loadBitmap(url, bmOptions);
							        profile_image.setImageBitmap(getRoundedShape(bm));
							        profile_image.setScaleType(ScaleType.FIT_XY);
							        more_profile_image.setImageBitmap(getRoundedShape(bm));
							        more_profile_image.setScaleType(ScaleType.FIT_XY);
								 	getRoundedShape(bm).recycle();

								 /*	Intent back=new Intent(Swipe_tabs.this,Discover.class);
									back.putExtra("profile", profile_image);*/
								
														
										    //System.out.println("Display inside image first "+getBitmapFromURL(profile_image));
										//URL pimage=new URL(profile_image);
										       
										    //profile_img=(ImageView)findViewById(R.id.imageView);
										 	//Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());
									 	    // Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());


										 	/*profile_img.setImageBitmap(getRoundedShape(myBitmap));
										 	profile_img.setScaleType(ScaleType.FIT_XY);*/
										//System.out.println("Bitmap Images *************"+edbitmap);
												
										}
									
										catch(Exception e){
										 	e.printStackTrace();
										 	}
										
										
										}
									
										// adding movie to movies array

										

									} catch (JSONException e) {
										e.printStackTrace();
									}

								}

								
								// notifying list adapter about data changes
								// so that it renders the list view with updated data
								//adapter2.notifyDataSetChanged();
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
public boolean isOnline(Context c) {
	ConnectivityManager cm = (ConnectivityManager) c
	.getSystemService(Context.CONNECTIVITY_SERVICE);
	NetworkInfo ni = cm.getActiveNetworkInfo();
	 
	if (ni != null && ni.isConnected())
	  return true;
	else
	  return false;
	}
public void clearApplicationData() {
    File cache = getCacheDir();
    if (cache!=null && cache.isDirectory())
    {
    	deleteDir(cache);
    }
   /* File appDir = new File(cache.getParent());
    if (appDir.exists()) {
        String[] children = appDir.list();
        for (String s : children) {
            if (!s.equals("lib")) {
                deleteDir(new File(appDir, s));*/
               /* Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");*/
              /* Intent i=new Intent(Setting_currency.this,Swipe_tabs.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // clearApplicationData();
                startActivity(i);
                finish();*/
           /* }
        }
    }*/
}
public static boolean deleteDir(File dir) {
    if (dir != null && dir.isDirectory()) {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir(new File(dir, children[i]));
            if (!success) {
                return false;
            }
        }
    }

    return dir.delete();
}
}