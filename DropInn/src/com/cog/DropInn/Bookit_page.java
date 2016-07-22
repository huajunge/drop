package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;


public class Bookit_page extends FragmentActivity  {
	
	
	//set the environment for production/sandbox/no netowrk
	
	//paypal Payment
		private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;

	    private static final String CONFIG_CLIENT_ID = "AS5DZoxI8ERxGRbjzrAE4XDWYdQuKlRl-Rb7Vj3SQH9nIDUYWwxecxL9S14U-I33onlHPFjv0R_OvCjw";

	    private static final int REQUEST_PAYPAL_PAYMENT = 1;

	    private static PayPalConfiguration config = new PayPalConfiguration()
	            .environment(CONFIG_ENVIRONMENT)
	            .clientId(CONFIG_CLIENT_ID)
	            .acceptCreditCards(false)
	            // The following are only used in PayPalFuturePaymentActivity.
	            .merchantName("DropInn")
	            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
	            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
	    		Bitmap edbitmap;
	    		URL pimage;


private CaldroidFragment dialogCaldroidFragment;
	
	URL Login_Url1,Login_Url2,Login_Url3;
	
    private JSONObject login_jsonobj1;
 
   String bedroomss,bathroomss;
   String cleaningfees;
    private String login_status2;	
    String capaci;
    String Liveurl="";
    String roomid;
	ImageButton back,minus,plus,pay;
	ImageView image,price_header,adminfee_header,totalfee_header;
	TextView title,room_type,nightscount,guest,symbol,price,showDialogButton,showDialogButton1,noguest,adminfees,totalfees;
	String userid,title1,roomtype1,imaage1,price1,guest1,symbol1,fees;
	float amttopay,total,amttoadmin;
	TextView nightstext;
	TextView price_label,admin_label,total_label,price_symbol,admin_symbol,total_symbol,price_title;
	 String arrive,depart,depart1;
     String ss,ss1;
     String getarrive,getdepart;
     long datedifference;
     int countdays;
     float pricecalculation,pricecalculation1;
     int score=1;
     float tot_beforepay;  
     String arrivedate="Select Date";
	 String departdate="Select Date";
	 String single=" guest";
	 String many=" guests";
	 String userid1,paymentId,userto;
	 String currency="USD";
	 String checkin[]=new String[50],checkout[]=new String[50];
	 String at[]=new String[50];
	 int n;
	 protected static final String TAG = null;
	 ProgressDialog pDialog;
	 //String today1="09/05/2015";
	 TextView bedroo,bathroo,gue;
	 TextView cleaning, cleansymbol,cleanfees,cleaningfee;
	 String currency1;
	 float amttocleaner;
	 String symbol2;
	 TextView guestmaximum;
	 
	 Date today;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookit_page);
		getActionBar().hide();
		pay=(ImageButton)findViewById(R.id.imageButton4);
		// Showing progress dialog before making http request
		pDialog = new ProgressDialog(this);
		pDialog.setCancelable(false);
		pDialog.show();
		pDialog.setContentView(R.layout.progress_dialog);
		
		//paypal service		
		Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        
  
		
        
		image=(ImageView)findViewById(R.id.imageView2);
		
		back=(ImageButton)findViewById(R.id.imageButton1);
		minus=(ImageButton)findViewById(R.id.imageButton2);
		plus=(ImageButton)findViewById(R.id.imageButton3);
	/*	price_header=(ImageView)findViewById(R.id.imageView7);
		adminfee_header=(ImageView)findViewById(R.id.imageView8);
		totalfee_header=(ImageView)findViewById(R.id.imageView9);*/
		price_title=(TextView)findViewById(R.id.TextView12);
		price_label=(TextView)findViewById(R.id.TextView13);
		price_symbol=(TextView)findViewById(R.id.TextView15);
		admin_label=(TextView)findViewById(R.id.TextView18);
		admin_symbol=(TextView)findViewById(R.id.TextView20);
		total_label=(TextView)findViewById(R.id.TextView21);
		total_symbol=(TextView)findViewById(R.id.TextView23);
		bedroo=(TextView)findViewById(R.id.TextView32);
		bathroo=(TextView)findViewById(R.id.TextView31);
		gue=(TextView)findViewById(R.id.TextView27);
		guestmaximum=(TextView)findViewById(R.id.TextView90);
		
		
		title=(TextView)findViewById(R.id.TextView2);
		room_type=(TextView)findViewById(R.id.TextView3);
		nightscount=(TextView)findViewById(R.id.TextView7);
		nightstext=(TextView)findViewById(R.id.TextView11);
		guest=(TextView)findViewById(R.id.TextView9);
		noguest=(TextView)findViewById(R.id.TextView10);
		//symbol=(TextView)findViewById(R.id.TextView15);
		price=(TextView)findViewById(R.id.TextView14);
		adminfees=(TextView)findViewById(R.id.TextView19);
		totalfees=(TextView)findViewById(R.id.TextView51);
		cleaningfee=(TextView)findViewById(R.id.TextView22);
		
		cleaning=(TextView)findViewById(R.id.TextView50);
		cleansymbol=(TextView)findViewById(R.id.TextView52);
		cleanfees=(TextView)findViewById(R.id.TextView51);
		
	/* price_header.setVisibility(View.GONE);
   	 totalfee_header.setVisibility(View.GONE);
   	 adminfee_header.setVisibility(View.GONE);*/
   	 price.setVisibility(View.GONE);
   	 totalfees.setVisibility(View.GONE);
   	 adminfees.setVisibility(View.GONE);
   	 price.setVisibility(View.GONE);
   	 price_title.setVisibility(View.GONE); 
   	 price_label.setVisibility(View.GONE);
   	 admin_label.setVisibility(View.GONE);
   	 total_label.setVisibility(View.GONE);
   	 price_symbol.setVisibility(View.GONE);
   	 admin_symbol.setVisibility(View.GONE);
   	 total_symbol.setVisibility(View.GONE);
   	 cleaning.setVisibility(View.GONE);
   	 cleaningfee.setVisibility(View.GONE);
   	cleansymbol.setVisibility(View.GONE);
   	cleanfees.setVisibility(View.GONE);
   	 nightscount.setVisibility(View.INVISIBLE);
     nightstext.setVisibility(View.INVISIBLE);
		
		
		 if( Build.VERSION.SDK_INT >= 9){
	            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy);
	     }
		
		
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
      Liveurl = sharedPreferences.getString("liveurl", null);
      userid = sharedPreferences.getString("userid", null);
      currency1= sharedPreferences.getString("currency1", null);
     // userid1=sharedPreferences.getString("userid", null);
		
      
      if(currency1==null)
		{
			currency1="USD";
		}
		Intent i=getIntent();
		roomid=i.getStringExtra("room_id");  
		imaage1=i.getStringExtra("image");
		System.out.println("imagey"+imaage1);
		symbol2=i.getStringExtra("symbol");
		//userid=i.getStringExtra("userid");
		
		if(symbol2==null)
		{
			symbol2="$";
		}
		System.out.println("userid in sharedpreferences"+userid);
		if(userid==null)
		{
			 Toast.makeText(getApplicationContext(), "You must Sign in now  ", Toast.LENGTH_SHORT).show();
			 Intent login=new Intent(Bookit_page.this,MainActivity.class);
			 startActivity(login);
			 finish();
		}
		else
		{
			 if (isOnline(this)) {
		view_webservice();
			 }
			 else
			 {
				 Toast.makeText(getApplicationContext(), "Check your internet connection",Toast.LENGTH_SHORT).show();
			 }
		
		
		
		/*********************plus and minus functionality************************/
		plus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				minus.setEnabled(true);
				
						
				if(score>0 && Integer.valueOf(guest1)!=1)
				{
					score++;
					
					System.out.println("score" +score);
					plus.setEnabled(true);
					if(score==Integer.valueOf(guest1))
					{
						plus.setEnabled(false);
						minus.setEnabled(true);
					}
					
				}			
				
				
				guest.setText(String.valueOf(score));
				
				
			}
		});
		
		minus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				plus.setEnabled(true);
				
				System.out.println("score in minus"+score);
				
				if(score<=Integer.valueOf(guest1)&&score>0)
				{
					minus.setEnabled(true);
					if(score==1)
					{
						minus.setEnabled(false);
						//plus.setEnabled(true);
					}
					else{
					score--;}

				}
				

				
				guest.setText(String.valueOf(score));
				
				
			}
		});
		 if (isOnline(this)) {
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
		 else
		 {
			 Toast.makeText(this, "Check your internet connection",Toast.LENGTH_SHORT).show();
		 }
	
	//view_calendar parsing json functionality End
	
	/****************Calendar Functionality Start********************/
		
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 
			// Setup listener
			final CaldroidListener listener = new CaldroidListener() {
				
				@Override
				public void onSelectDate(Date date, View view) {
					arrive=formatter.format(date).toString();
					showDialogButton.setText(arrive);
					showDialogButton1.setText("Select Date");
					price.setText(symbol2+price1);
					totalfees.setText(String.valueOf(tot_beforepay));
					nightscount.setVisibility(View.INVISIBLE);
			    	 nightstext.setVisibility(View.INVISIBLE);
			    	 
					if(arrive!=null)
					{
						showDialogButton1.setText("Select Date");
						showDialogButton1.setText(departdate);
						depart=departdate;
						dialogCaldroidFragment.dismiss();
					}
											
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

				
			};
			showDialogButton = (TextView) findViewById(R.id.show_dialog_button);

			final Bundle state = savedInstanceState;
			
			
			showDialogButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					
					// Setup caldroid to use as dialog
					dialogCaldroidFragment = new CaldroidFragment();
					dialogCaldroidFragment.setCaldroidListener(listener);

					// If activity is recovered from rotation
					final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
					if (state != null) {
						dialogCaldroidFragment.restoreDialogStatesFromKey(
								getSupportFragmentManager(), state,
								"DIALOG_CALDROID_SAVED_STATE", dialogTag);
						Bundle args = dialogCaldroidFragment.getArguments();
						if (args == null) {
							args = new Bundle();
							dialogCaldroidFragment.setArguments(args);
						}
						args.putString(CaldroidFragment.DIALOG_TITLE,
								"Select a date");
					} else {
						
						//set up arguments
						Bundle bundle = new Bundle();
						
						Calendar cal = Calendar.getInstance();
				 		
				 		// Min date is last 7 days
				 		cal.add(Calendar.DATE, 0);
						Date minDate = cal.getTime();
						System.out.println("Min date"+minDate);
						dialogCaldroidFragment.setMinDate(minDate);
						
						
						//Highlight Arrival and Departure Dates
						if(arrive!=null&&depart!=null)
						{
						if(!arrive.matches(arrivedate)&&!depart.matches(departdate))
						{
							System.out.println("arrive selected"+arrive);
							System.out.println("depart selected"+depart);
							
						String formatter1 = "dd-MM-yyyy";
						try {
							dialogCaldroidFragment.setSelectedDateStrings(arrive, depart, formatter1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}else if(depart.matches(departdate))
						{
							dialogCaldroidFragment.clearSelectedDates();
						}
						}
						
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
						
						// Setup dialogTitle
						bundle.putString(CaldroidFragment.DIALOG_TITLE,
								"Select Arrival date");
						bundle.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, false);
						dialogCaldroidFragment.setArguments(bundle);
					}

					dialogCaldroidFragment.show(getSupportFragmentManager(),
							dialogTag);
				}
			});
				final CaldroidListener listener1 = new CaldroidListener() {
				
				@Override
				public void onSelectDate(Date date, View view) {
					
					depart=formatter.format(date).toString();
					 
					System.out.println("arrival date before selection"+arrive);	
					 
					System.out.println("arrive inside depar"+arrive);
					
					 if(arrive==null)
						{
							Toast.makeText(getApplicationContext(),
									"Select arrival Date first",
									Toast.LENGTH_SHORT).show();
							showDialogButton1.setText(departdate);
							
							
							
						}
					 else
					 {
						 //calculating and setting arrival and departure dates
						 
					    depart=formatter.format(date).toString();
						arrive=showDialogButton.getText().toString();
						System.out.println("arrive in depart"+arrive);
						showDialogButton1.setText(depart);
						depart=showDialogButton1.getText().toString();
						System.out.println("depart in depart"+depart);
						
						
						if(arrive!=null&&depart!=null)
						{
							if(!arrive.matches(arrivedate)&&!depart.matches(departdate))
							{
						try{

						     	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
						    
						     	String arrive1=arrive;
						     	Date date1 = formatter1.parse(arrive1);
						     	System.out.println("date 1"+date1);
						     

						     
						     	Date date2 = formatter1.parse(depart);
						     	System.out.println("date 2"+date2);
						     
						     
						    	 showDialogButton.setText(arrive);
						    	 showDialogButton1.setText(depart);
						    	 //Calculating no of Days between arrival and departure
						    	 System.out.println("Days= "+(date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
						    	 datedifference=(date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24);
						    	 countdays=(int)datedifference;
						    	 System.out.println("after converting to int"+countdays);
						    	 pricecalculation1=countdays*amttopay;
						    	 pricecalculation=(countdays*amttopay)+amttoadmin+amttocleaner;
						    	 System.out.println("amt to pay"+pricecalculation);
						    	 price.setText(symbol2+String.valueOf(pricecalculation1));
						    	 //totalfees.setText(symbol2+String.valueOf(pricecalculation));
						    	 
						    		DecimalFormat money = new DecimalFormat("00.00");
									money.setRoundingMode(RoundingMode.UP);
									final String india2 = money.format(new Double(String.valueOf(pricecalculation)));
									totalfees.setText(symbol2+india2);
						    	 
						    	 nightscount.setVisibility(View.VISIBLE);
						    	 //nightstext.setVisibility(View.VISIBLE);
						    	/* price_header.setVisibility(View.VISIBLE);
						    	 totalfee_header.setVisibility(View.VISIBLE);
						    	 adminfee_header.setVisibility(View.VISIBLE);*/
						    	 price.setVisibility(View.VISIBLE);
						    	 totalfees.setVisibility(View.VISIBLE);
						    	 price.setVisibility(View.VISIBLE);
						    	 adminfees.setVisibility(View.VISIBLE);
						    	 price_title.setVisibility(View.VISIBLE);
						    	 price_label.setVisibility(View.VISIBLE);
						    	 admin_label.setVisibility(View.VISIBLE);
						    	 total_label.setVisibility(View.VISIBLE);
						    	// price_symbol.setVisibility(View.VISIBLE);
						    	 //admin_symbol.setVisibility(View.VISIBLE);
						    	 //total_symbol.setVisibility(View.VISIBLE);
						    	 cleaning.setVisibility(View.VISIBLE);
						    	   	//cleansymbol.setVisibility(View.VISIBLE);
						    	   	cleanfees.setVisibility(View.VISIBLE);
						    	   	cleaningfee.setVisibility(View.VISIBLE);
						    	 nightscount.setText(String.valueOf(countdays)+" nights");
						    	 dialogCaldroidFragment.dismiss();
						    }catch (ParseException e1){
						        e1.printStackTrace();
						    }
						
						//Check already booked dates with arrive and depart dates
					    
						for(int i=0;i<n;i++)
					    {
					    	
					    	if(checkin[i]!=null&&checkout[i]!=null)
					    	{
					    		
					    		ArrayList<Date> dates = new ArrayList<Date>();
							    DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");

							    Date date1 = null;
							    Date date2 = null;

							    try {
							        date1 = df1 .parse(arrive);
							        date2 = df1 .parse(depart);
							    } catch (ParseException e) {
							        e.printStackTrace();
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
							        cal1.add(Calendar.DATE,1);
							        
							    }
}
							    
							    for(Date d:dates)
							    {
							    	String s=df1.format(d);
							    	if(s.matches(checkin[i])||s.matches(checkout[i]))
							    	{
							    		 Toast.makeText(getApplicationContext(), "Those Dates are not available", Toast.LENGTH_SHORT).show();
								    	 showDialogButton.setText(arrivedate);
								    	 arrive=arrivedate;
								    	 depart=departdate;
								    	 showDialogButton1.setText(departdate);
								    	 nightscount.setVisibility(View.INVISIBLE);
								    	 nightstext.setVisibility(View.INVISIBLE);
								    	 price_header.setVisibility(View.GONE);
								    	 totalfee_header.setVisibility(View.GONE);
								    	 adminfee_header.setVisibility(View.GONE);
								    	 price.setVisibility(View.GONE);
								    	 totalfees.setVisibility(View.GONE);
								    	 price.setVisibility(View.GONE);
								    	 //price.setText(price1);
								 		 //totalfees.setText(String.valueOf(tot_beforepay));
								 		 //adminfees.setText(fees);
								    	 adminfees.setVisibility(View.GONE);
								    	 price_title.setVisibility(View.GONE);
								    	 price_label.setVisibility(View.GONE);
								    	 admin_label.setVisibility(View.GONE);
								    	 total_label.setVisibility(View.GONE);
								    	 price_symbol.setVisibility(View.GONE);
								    	 admin_symbol.setVisibility(View.GONE);
								    	 total_symbol.setVisibility(View.GONE);
								 		 System.out.println("arrive"+arrive);
								 		 System.out.println("depart"+depart);
								 		
							    		
							    	}
							    	System.out.println("for loop"+s);
							    }
							    							    
						    	
					    	}
					    }
						}
					 }
					 }
				}

				@Override
				public void onChangeMonth(int month, int year) {
					String text = "month: " + month + " year: " + year;
				}

				

			};
			showDialogButton1 = (TextView) findViewById(R.id.show_dialog_button1);

			final Bundle state1 = savedInstanceState;
			
			showDialogButton1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					if(arrive!=null)
					{
						System.out.println("arrive value"+arrive);
						if(!arrive.matches(arrivedate))
						{
							
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
						
						
						//Disable Dates on and before arrival 
						Calendar cal = Calendar.getInstance();
				 		
						if(arrive!=null)
						{
						if(!arrive.matches(arrivedate))
						{
						DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
						Date date1=null;
						try {
					        date1 = df1 .parse(arrive);
					        
					    } catch (ParseException e) {
					        e.printStackTrace();
					    }
						Calendar cal1 = Calendar.getInstance();
					    cal1.setTime(date1);
					    cal1.add(Calendar.DATE, 1);
					    date1=cal1.getTime();
					    System.out.println("date 1 value"+date1);
					    dialogCaldroidFragment.setMinDate(date1);
						//dialogCaldroidFragment.setMinDateFromString(arrive,df1);
						}
						}
						
						//Highlight Arrival and Departure dates
						
						if(arrive!=null&&depart!=null)
						{
						if(arrive.matches(arrivedate)&&depart.matches(departdate))
						{
							
							dialogCaldroidFragment.clearSelectedDates();	
						}
						else if(depart.matches(departdate))
						{
							dialogCaldroidFragment.clearSelectedDates();
						}
						else
						{
							System.out.println("arrive selected"+arrive);
							System.out.println("depart selected"+depart);
							
							String formatter1 = "dd-MM-yyyy";
							
							try {
								dialogCaldroidFragment.setSelectedDateStrings(arrive, depart, formatter1);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						}
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
						
						
						 at = arrive.split("-");
					     String dat=at[0];
		                 String month=at[1];
		                 String year=at[2];
		                 System.out.println("date in string"+dat);
		                 System.out.println("month in string"+month);
		                 System.out.println("year in string"+year);
		                 
		                 int m=Integer.parseInt(month);
		                 int y=Integer.parseInt(year);
		                 System.out.println("month in int"+m);
		                 System.out.println("year in int"+y);
						//set up arguments
						Bundle bundle = new Bundle();
						
						// Setup dialogTitle
						bundle.putString(CaldroidFragment.DIALOG_TITLE,
								"Select Departure date");
						bundle.putInt(CaldroidFragment.MONTH, m); // October
						bundle.putInt(CaldroidFragment.YEAR, y);
						bundle.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, false);
						dialogCaldroidFragment.setArguments(bundle);
					}

					dialogCaldroidFragment.show(getSupportFragmentManager(),
									dialogTag);
				}else
				{
					Toast.makeText(getApplicationContext(),
							"Select Arrival Date" ,
							Toast.LENGTH_SHORT).show();	
					System.out.println("check depart date"+depart);
				}
					}else
				{
					Toast.makeText(getApplicationContext(),
							"Select Arrival Date" ,
							Toast.LENGTH_SHORT).show();	
					System.out.println("check depart date"+depart);
				}
			}
				
			});
			
			/****************Calendar Functionality Start********************/
			
		
		back.setClickable(true);
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("user id clicking back"+userid);
				Intent i=new Intent(Bookit_page.this,Swipe_tabs.class);
				i.putExtra("userid", userid);
				i.putExtra("roomid", roomid);
				startActivity(i);
				 finish();
			}
		});
		//paypal on click
		pay.setOnClickListener(new View.OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				arrive=showDialogButton.getText().toString();
				depart=showDialogButton1.getText().toString();
				
				System.out.println("arrive date"+arrive);
				System.out.println("depart date"+depart);
				guest1=guest.getText().toString();
				
				if(arrive.matches(arrivedate)&&depart.matches(departdate))
				{
					Toast.makeText(getApplicationContext(),
							"select Arrival and Departure Dates",
							Toast.LENGTH_SHORT).show();	
				}
				else if(depart.matches(departdate))
				{
					Toast.makeText(getApplicationContext(),
							"select Departure Dates",
							Toast.LENGTH_SHORT).show();	
				}
				else
				{

					System.out.println("transaction id paypal"+paymentId);
				   	System.out.println("list id paypal"+roomid);
				   	System.out.println("userby in paypal"+userid);
					System.out.println("userto in paypal"+userto);
					System.out.println("checkin in paypal"+arrive);
					System.out.println("checkout in paypal"+depart);
					System.out.println("no of guests"+guest1);
					System.out.println("currency code"+currency);
					System.out.println("price in paypal"+pricecalculation);
					System.out.println("to pay"+pricecalculation1);
					//System.out.println("today date"+today1);
					System.out.println("admin commission"+amttoadmin);
					System.out.println("guest  to pay"+pricecalculation1);
					
				PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(pricecalculation),"USD", "Total Payment",
			            PayPalPayment.PAYMENT_INTENT_SALE);

			        Intent intent = new Intent(Bookit_page.this, PaymentActivity.class);

			        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

			        startActivityForResult(intent, REQUEST_PAYPAL_PAYMENT);	
			}
			}
		});
		
		}
		
	}
	
	 //papypal request
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_PAYPAL_PAYMENT) {
	            if (resultCode == Activity.RESULT_OK) {
	                PaymentConfirmation confirm = data
	                        .getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
	                if (confirm != null) {
	                    try {
	                    	System.out.println("Responseeee"+confirm);
	                        Log.i("paymentExample", confirm.toJSONObject().toString());

	                      
	                        JSONObject jsonObj=new JSONObject(confirm.toJSONObject().toString());
	                        
	                        paymentId=jsonObj.getJSONObject("response").getString("id");
	                        System.out.println("payment id:-=="+paymentId);
	                        //String amount=jsonObj.getJSONObject("payment").getString("amount");
	                        //System.out.println("amount:-=="+amount);
	                        /*Toast.makeText(getApplicationContext(), "Payment succesfully completed", Toast.LENGTH_LONG).show();*/  
	                        view_payment();
	                    } catch (JSONException e) {
	                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
	                    }
	                }
	            } else if (resultCode == Activity.RESULT_CANCELED) {
	                Log.i("paymentExample", "The user canceled.");
	            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
	            	/*Toast.makeText(getApplicationContext(),
							"select Arrival and Departure Dates",
							Toast.LENGTH_SHORT).show();	*/
	                Log.i("paymentExample", "An invalid Payment was submitted. Please see the docs.");
	            }
	        } 
	        
	         
	  }



	
	 public void view_webservice()
	 {
		
			
		 final String url=Liveurl+"payment/bookit_page?room_id="+roomid+"&common_currency="+currency1;
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
									 userto=obj.getString("user_id");
							          //imaage1=obj.getString("image");
							          title1=obj.getString("title");
							          roomtype1=obj.getString("room_type");
							          price1=obj.getString("common_price");
							          guest1=obj.getString("guest");
							          //symbol1=obj.getString("currency_code");          
							          fees=obj.getString("common_admin_fees");
							          cleaningfees=obj.getString("cleaning_fees");
							          bathroomss=obj.getString("bathrooms");
								      bedroomss=obj.getString("bedrooms");
								      // capaci=obj.getString("capacity");
							           
						        	System.out.println("cleaning" +price1);
							         
							  		
							  		try{
							  			pimage=new URL(imaage1);
							  			 edbitmap = BitmapFactory.decodeStream(pimage.openStream());
							  			 image.setImageBitmap(edbitmap);
							  			 image.setScaleType(ScaleType.FIT_XY);
							  			}
							  			catch(Exception e){
							  				e.printStackTrace();
							  			}
							  		
							  		
							  		title.setText(title1);
							  		//room_type.setText(roomtype1);
							  		if (symbol!=null){
							  		symbol.setText(currency1);
							  		}
							  		price.setText(symbol2+price1);
							  		adminfees.setText(symbol2+fees);
							  		bedroo.setText(bedroomss);
							  		bathroo.setText(bathroomss);
							  		gue.setText(guest1);
							  		
							  		if (guest1!=null)
							  		{
							  			if (!guest1.equals("1"))
							  			{
							  				guestmaximum.setText("guests maximum");
							  			}
							  		}
							  		cleaningfee.setText(symbol2+cleaningfees);
							  		tot_beforepay=amttopay+amttoadmin+amttocleaner;
							  		totalfees.setText(symbol2+String.valueOf(tot_beforepay));
							  		amttopay=Float.parseFloat(price1);
							  		amttoadmin=Float.parseFloat(fees);
							  		amttocleaner=Float.parseFloat(cleaningfees);
							  		
							  		System.out.println("check admin commission"+fees);
							  		System.out.println("check total commission"+total);
						        	
							  		/*Currency c  = Currency.getInstance(symbol1);    
							    	 symbol1=c.getSymbol();*/
							    	 
							    	 System.out.println("after json"+login_jsonobj1);
							    	 
							         System.out.println("title"+title1);
							         System.out.println("roomtype"+roomtype1);
							         System.out.println("price"+price1);
							         System.out.println("guest"+guest1);
							         System.out.println("image"+imaage1);
							         System.out.println("symbol"+symbol1);
							         System.out.println("admin_fees"+fees);
							         System.out.println("userid"+userto);
							         
								    
								   
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

public void view_payment()
{
	    System.out.println("transaction id paypal"+paymentId);
	   	System.out.println("list id paypal"+roomid);
	   	System.out.println("userby in paypal"+userid);
		System.out.println("userto in paypal"+userto);
		System.out.println("checkin in paypal"+arrive);
		System.out.println("checkout in paypal"+depart);
		System.out.println("no of guests"+guest1);
		System.out.println("currency code"+currency);
		System.out.println("price in paypal"+pricecalculation);
		System.out.println("to pay"+pricecalculation1);
		//System.out.println("today date"+today1);
		System.out.println("admin commission"+amttoadmin);
		System.out.println("guest  to pay"+pricecalculation1);
		
		final String url=Liveurl+"payment/guest_reservation_request?transaction_id="+paymentId+"&list_id="+roomid+"&userby="+userid+"&userto="+userto+"&checkin="+arrive+"&checkout="+depart+"&no_quest="+guest1+"&currency="+currency+"&price="+pricecalculation+"&topay="+pricecalculation1+"&admin_commission="+amttoadmin+"&guest_topay="+pricecalculation1;
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
								 login_status2 =obj.getString("reason_message");
								 
								 if(login_status2.matches("Payment completed successfully."))
						            {
						        		//final AlertDialog alertDialog = new AlertDialog.Builder(List_your_space.this).create();
						                
						                Toast.makeText(getApplicationContext(), "Payment Completed Successfully", Toast.LENGTH_SHORT).show();
						                 Intent i1 = new Intent(Bookit_page.this, Swipe_tabs.class);
						                 i1.putExtra("userid",userid);
						                 i1.putExtra("roomid",roomid);
						                  // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
						                   startActivity(i1);
						                   finish();   
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

@Override
public void onBackPressed()
{


    
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
