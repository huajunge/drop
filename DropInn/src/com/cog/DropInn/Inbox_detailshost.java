package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Currency;

import org.apache.commons.lang.WordUtils;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

@SuppressLint("NewApi")
public class Inbox_detailshost extends Activity {

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
	String currency1,price21;
	Button respond;
	String simplestring;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inbox_details);
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
		 currency1 = sharedPreferences.getString("currenycode",null);
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		reservation_id=i.getStringExtra("reservation_id");
		userby=i.getStringExtra("userby");		
		price21=i.getStringExtra("price");	
		
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
		
		
		View_webservice();
		
		
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(Inbox_detailshost.this,Inbox1.class);
				//back.putExtra("userid",userid);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Inbox_detailshost.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(back);
				 finish();
				
			}
		});
		
	}
	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	 			
	}
	
	public void View_webservice()
	{
		  //Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
       // final String url = "http://demo.cogzideltemplates.com/client/gottospot_android/mobile/payment/guest_reservation_details?userto=191";
	   	final String url=Liveurl+"payment/guest_reservation_details?userby="+userby+"&reservation_id="+reservation_id+"&userto="+userid;
	   	System.out.println("url in Inbox_details page ==="+url);
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
							
								
								username1=obj.getString("username");
								prof_img1=obj.getString("profile_pic");
								checkin1=obj.getString("checkin");
								checkout1=obj.getString("checkout");
								title1=obj.getString("title");
								price1=obj.getString("price");
								status1=obj.getString("status");								
								guest1=obj.getString("guest");
								room_type1=obj.getString("room_type");
								country_symbol=obj.getString("currency_code");
								System.out.println("symbol" +country_symbol);
								if (country_symbol!=null)
								{
								if  (!country_symbol.equals("[]"))
								{
									if (!country_symbol.equals("INR"))
											{
								 Currency c  = Currency.getInstance(country_symbol);    
								 country_symbol=c.getSymbol();
							}
								}
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
						    String url =prof_img1;

						    
						    
						    BitmapFactory.Options bmOptions;
						    bmOptions = new BitmapFactory.Options();	
						    bmOptions.inSampleSize = 1;
						    Bitmap bm = loadBitmap(url, bmOptions);
						    prof_img.setImageBitmap(getRoundedShape(bm));
						    prof_img.setScaleType(ScaleType.FIT_XY);
						    hidePDialog();
						 			} 
						 			catch(Exception e){
						 				e.printStackTrace();
						 				hidePDialog();
						 			}
								

							} catch (JSONException e) {
								e.printStackTrace();
								hidePDialog();
							}

						}
System.out.println("inbox detail page username-=-"+username1);
System.out.println("inbox detail page prof_img1=="+prof_img1);
System.out.println("inbox detail page checkin==-"+checkin1);
System.out.println("inbox detail page checkout--"+checkout1);
System.out.println("inbox detail page title---"+title1);
System.out.println("inbox detail page status=="+status1);
System.out.println("inbox detail page symbol=="+symbol1);
System.out.println("inbox detail page guest=="+guest1);
System.out.println("inbox detail page room_type=="+room_type1);

if (currency1==null)
{
	currency1="$";
}


if (!guest1.equals("1"))
{
	Cguest.setText("Guests");
}

//username.setText(username1);
if (title1!=null)
{
if (!title1.equals ("null"))
{
title1=WordUtils.capitalizeFully(title1);
}
}

if (checkout1!=null)
{
if (!checkout1.equals ("null"))
{
	checkout.setText(checkout1);
}
}

if (checkin1!=null)
{
if (!checkin1.equals ("null"))
{
	checkin.setText(checkin1);
}
}

if (country_symbol!=null)
{
if (!country_symbol.equals ("null") && (!country_symbol.equals ("[]")))
{
	//symbol.setText(country_symbol);
}
}

if (guest1!=null)
{
if (!guest1.equals ("null"))
{
	guest.setText(guest1);
}
}

if (status1!=null)
{
if (!status1.equals ("null"))
{
	status.setText(status1);
}
}

if (price21!=null)
{
if (!price21.equals ("null"))
{
	price.setText(currency1+price21);
}
}

if (title1!=null)
{
if (!title1.equals ("null"))
{
	title.setText(title1);
}
}

if (room_type1!=null)
{
if (!room_type1.equals ("null"))
{
	room_type1=WordUtils.capitalizeFully(room_type1);
	room_type.setText(room_type1);
}
}






















if (status1!=null)
{

if(status1.equals("Pending")||status1.equals("Payment Pending"))
{
	System.out.println("inside not equals pending");
	respond.setVisibility(View.VISIBLE);
}
else
{
	respond.setVisibility(View.INVISIBLE);
	
}
					}}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();
						

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
		
		
	}
	public void onClick(View v) {
		
		   //AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.ThemeDialogCustom));
		   AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
           alertDialog2.setTitle("Respond Now");

           alertDialog2.setMessage("Would you like to accept a guest request ?");

          // alertDialog2.setIcon(R.drawable.act_camera);


           alertDialog2.setPositiveButton("ACCEPT",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							
							post_status="completed";
						
							upload_webservice();
						}
					});

                         
                   alertDialog2.setNeutralButton("DECLINE",
                   new DialogInterface.OnClickListener() {

                       public void onClick(DialogInterface dialog, int which) {
                       
                          post_status="Declined";
                          upload_webservice();
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
	public void upload_webservice()
	{
		
	  	//final String url = "http://demo.cogzideltemplates.com/client/gottospot_android/mobile/payment/host_reservation_response?reservation_id=45&status=completed&comment=accepted";
	  	
	  	final String url=Liveurl+ "payment/host_reservation_response?reservation_id="+reservation_id+"&status="+post_status+"&comment="+post_status;
	   	
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
	  							String resu= obj.getString("Status"); 
	  							Log.d("OUTPUT IS",resu);
	  							if(resu.matches("Your reservation request accept process successfully completed."))
	  							{	
	  									Toast.makeText(Inbox_detailshost.this,"Your reservation request accept process successfully completed", Toast.LENGTH_SHORT).show();	  						 
	  								Intent login=new Intent(Inbox_detailshost.this,Swipe_tabshost.class);
	  								login.putExtra("userid",userid);
	  								startActivity(login);
	  								 finish();
	  								
	  							}
	  							else if(resu.matches("Your reservation request accept process not completed."))
	  							{	
	  								Toast.makeText(Inbox_detailshost.this,"Your reservation request Declined process successfully completed", Toast.LENGTH_SHORT).show();	  						 
	  								Intent login=new Intent(Inbox_detailshost.this,Swipe_tabshost.class);
	  								login.putExtra("userid",userid);
	  								startActivity(login);
	  								 finish();
	  							}
	  								
	  							
	  							

	  							// adding movie to movies array
	  						 

	  						} catch (JSONException e) {
	  							e.printStackTrace();
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
	  					 

	  				}
	  			});

	  	// Adding request to request queue
	  	AppController.getInstance().addToRequestQueue(movieReq);
		
		
		
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
