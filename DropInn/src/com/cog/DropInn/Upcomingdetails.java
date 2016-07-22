package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import net.appkraft.parallax.ParallaxScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;

@SuppressLint("NewApi")
public class Upcomingdetails extends Activity {

	protected static final String TAG = null;
	
	ProgressDialog pDialog;
	String Liveurl="";
	String userid,reservation_id,userby;
	ImageButton back;
	String post_status,comment;
	String username1,price1,symbol1,title1,status1,guest1,room_type1,checkin1,checkout1,prof_img1,country_symbol;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	ImageView prof_img;
	TextView username,price,symbol,title,status,guest,room_type,checkin,checkout,Cguest;
	String roomid,profimage,price12,symbol12,titlep,checkin12,checkout12,nguest,roomtype,status12,login_status1,statusy;
	ParallaxScrollView parallax;
	Button respond;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upcoming_details);
		getActionBar().hide();

		  if( Build.VERSION.SDK_INT >= 9){
          StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
          StrictMode.setThreadPolicy(policy);
   }
			pDialog = new ProgressDialog(this);
			pDialog.setCancelable(false);
			pDialog.show();
			pDialog.setContentView(R.layout.progress_dialog);
			
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		 Liveurl = sharedPreferences.getString("liveurl", null);
		 
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		reservation_id=i.getStringExtra("resid");
		userby=i.getStringExtra("userby");
		
		roomid=i.getStringExtra("room_id");
	    profimage=i.getStringExtra("profimage");
	    price12= i.getStringExtra("price");
	    titlep=i.getStringExtra("title");

	
		
		System.out.println("inbox details page userid--"+userid);
		System.out.println("inbox details page reservationid--"+reservation_id);
		System.out.println("inbox details page userby--"+userby);
		prof_img=(ImageView)findViewById(R.id.imageView2);
		//username=(TextView)findViewById(R.id.TextView2);
		price=(TextView)findViewById(R.id.TextView21);
		symbol=(TextView)findViewById(R.id.TextView20);
		title=(TextView)findViewById(R.id.TextView4);
		status=(TextView)findViewById(R.id.TextView2);
		guest=(TextView)findViewById(R.id.TextView16);
		room_type=(TextView)findViewById(R.id.TextView18);
		checkin=(TextView)findViewById(R.id.TextView10);
		checkout=(TextView)findViewById(R.id.TextView11);
		respond=(Button)findViewById(R.id.buttonfooter);
		back=(ImageButton)findViewById(R.id.imageButton1);	
		Cguest=(TextView)findViewById(R.id.TextView17);
		respond.setVisibility(View.INVISIBLE);
		
		symbol12 = i.getStringExtra("symbol");
		symbol.setText(symbol12);
		title.setText(titlep);
		price.setText(price12);
		checkout12 = i.getStringExtra("checkout");
		checkin12 = i.getStringExtra("checkin");
		checkin.setText(checkin12);
		checkout.setText(checkout12);
		nguest = i.getStringExtra("guest");
		 guest.setText(nguest);
		System.out.println("guest" +guest);
		roomtype = i.getStringExtra("room_type");
		room_type.setText(roomtype);
		status12 = i.getStringExtra("status");
		System.out.println("status" +status12);
		
		if (!nguest.equals("1"))
		{
			Cguest.setText("Guests");
		}
		try{
 			/*URL pimage=new URL(profimage);
 			Bitmap imagebitmap = BitmapFactory.decodeStream(pimage.openStream());
 			 
 			prof_img.setImageBitmap(imagebitmap);
 			prof_img.setScaleType(ScaleType.FIT_XY);;
 			parallax.setImageViewToParallax(prof_img);*/
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
            .permitAll().build();
    StrictMode.setThreadPolicy(policy);
    String url =profimage;

    
    
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
		
	
		
		
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(Upcomingdetails.this,Swipe_tabs.class);
				//back.putExtra("userid",userid);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Upcomingdetails.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(back);
				finish();
				
			}
		});
		hidePDialog();
		
		if (status12.equals("0") )
        {
			status.setText("List Status: Payment Pending");
            		respond.setVisibility(View.VISIBLE); } 
		else if (status12.equals("1")) {
			status.setText( "List Status: Pending");
            		respond.setVisibility(View.VISIBLE);} 
		else if (status12.equals("2")) {
			status.setText("List Status: Expired");
            		respond.setVisibility(View.INVISIBLE); } 
		else if (status12.equals("3")) {
			status.setText("List Status: Accepted");
            		respond.setVisibility(View.VISIBLE);} 
		else if (status12.equals("4"))  {
			status.setText("List Status: Declined");
            		respond.setVisibility(View.VISIBLE); } 
		else if (status12.equals("5")) {
			status.setText( "List Status: Before checkin canceled by Host");
            		respond.setVisibility(View.INVISIBLE); } 
		else if (status12.equals("6")) {
			status.setText( "List Status: Before checkin canceled by Guest");
            		respond.setVisibility(View.INVISIBLE); } 
		else if (status12.equals("7")) {
			status.setText("List Status: Checkin");
            		respond.setVisibility(View.VISIBLE);} 
		else if (status12.equals("8")) {
			status.setText( "List Status: Awaiting Host Review");
            		respond.setVisibility(View.INVISIBLE); } 
		else if (status12.equals("9")) {
			status.setText( "List Status: Awaiting Traveler Review");
            		respond.setVisibility(View.INVISIBLE);} 
		else if (status12.equals("10")) {
			status.setText( "List Status: Completed");
            		respond.setVisibility(View.INVISIBLE); }  
		else if (status12.equals("11")) {
			status.setText("List Status: After Checkin canceled by Host");
            		respond.setVisibility(View.INVISIBLE); } 
		else if (status12.equals("12")) {
			status.setText( "List Status: After Checkin canceled by Guest");
            		respond.setVisibility(View.INVISIBLE); } 
		else if (status12.equals("13")) {
			status.setText("List Status: Pending Reservation Canceled");
            		respond.setVisibility(View.INVISIBLE); }
		
		
		
	}
	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	 			
	}
	
	 
	    public void onClick(View v) {
	        // handle click
	    	final Context context = this;
	    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

	    	// set title
	    	alertDialogBuilder.setTitle("Cancel Reservation");

	    	// set dialog message
	    	alertDialogBuilder
	    		.setMessage("Are you sure you want to cancel your reservation?")
	    		.setCancelable(false)
	    		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog,int id) {
	    				// if this button is clicked, close
	    				// current activity
	    				//MainActivity.this.finish();
	    				if (status12.equals("1"))
	    				{
	    					statusy="13";
	    					call_webservice1();
	    					
	    				}
	    				
	    				if (status12.equals("3"))
	    				{
	    					statusy="6";
	    					call_webservice1();
	    					
	    				}
	    				
	    				if (status12.equals("7"))
	    				{
	    					statusy="12";
	    					call_webservice1();
	    					
	    				}
	    			}
	    		})
	    		.setNegativeButton("No",new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog,int id) {
	    				// if this button is clicked, just close
	    				// the dialog box and do nothing
	    				dialog.cancel();
	    			}
	    	});

	    	// create alert dialog
	    	AlertDialog alertDialog = alertDialogBuilder.create();

	    	// show it
	    	alertDialog.show();
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
	 public void call_webservice1()
	 {
			final String url=Liveurl+"rooms/cancel?id="+roomid+"&status="+statusy;
			   System.out.println("url" +url);
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {
							 

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {
                   
            	                                  
									JSONObject obj = response.getJSONObject(i); 
									login_status1 =  obj.getString("status");
									
									  System.out.println("after json"+login_status1);
							             
							             if(login_status1.matches("Updated Successfully"))
							             {
							                 

							         		//final AlertDialog alertDialog = new AlertDialog.Builder(List_your_space.this).create();
							                  
							                 
							                  Intent i1 = new Intent(Upcomingdetails.this, Swipe_tabs.class);
							                
							                  startActivity(i1);
							                    finish();
							              /*   
							                  total = 5 -(tit_step+sum_step+photo_step+add_step+pri_step);
								                System.out.println("totally" +total);
								                if (total==0)
								                {
								                Movie movie = new Movie();
								               // movie.total(String.valueOf(total));
								                System.out.println("india" +String.valueOf(total));
								                movieList.add(movie);
								                }*/
							                   // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
							                   /* startActivity(i1);
							                    finish();   */
							             }
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