package com.cog.DropInn;
import info.androidhive.customlistviewvolley.adater.CustomSearchAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.R;
import com.cog.DropInn.XListView.IXListViewListener;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.roomorama.caldroid.CaldroidGridAdapter;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.ListView;

public class Search_result extends FragmentActivity implements IXListViewListener {
	
	private static final String TAG = Search_result.class.getSimpleName();

	   SharedPreferences sharedpreferences;
	// Movies json url
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private XListView listView;
	private CustomSearchAdapter adapter;
	private String country_symbol;
	String min_bathrooms,min_beds,min_bedrooms,guests,roomtypes;
	String	price_min,price_max;
	String at[]=new String[50];

	//private CaldroidFragment caldroidFragment;
	private CaldroidFragment dialogCaldroidFragment;	
	String userid,roomid,userid1,roomid1;
	URL pimage,pimage1;
	Bitmap edbitmap;
	Bitmap edbitmap1;
	String get_location,dis,speechText;
	int minimum,maximum;
	 String Liveurl="";
	 URL Login_Url;
	    String reader;
	    String login_inputline;
	    String login_status;
	    JSONArray login_jsonarray;
	    JSONObject login_jsonobj,login_Error;
	     ListView listViewItems1;
	     ImageView bg_img;
	     TextView t6,t8,showDialogButton,showDialogButton1,currency_symbol;
	     Button filter;
	     String arrive,depart,depart1;
	     String ss,ss1;
	     String getarrive,getdepart;
	     String loginuserid,listuserid;
	     String arrivedate="Select Date";
		 String departdate="Select Date";
		 TextView head;
		 private ArrayList<String> items = new ArrayList<String>();
			private int start1 = 0;
			private static int refreshCnt = 0;
			int start=1;
			String position;
	     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		getActionBar().hide();
		showDialogButton = (TextView) findViewById(R.id.show_dialog_button);
		showDialogButton1 = (TextView) findViewById(R.id.show_dialog_button1);
		head = (TextView) findViewById(R.id.TextView1);

		
		 if( Build.VERSION.SDK_INT >= 9){
             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
             StrictMode.setThreadPolicy(policy);
      }
		 
		 	SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
			Liveurl = sharedPreferences.getString("liveurl", null);
			
			
			SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
			   userid = sharedPreferences1.getString("userid",null);
			System.out.println("userid in shared preferences in search result page=="+userid);
			Intent im=getIntent();
			
			get_location=im.getStringExtra("location");
			speechText=im.getStringExtra("speech");
			guests=im.getStringExtra("guests");
			roomtypes=im.getStringExtra("roomtypes");
			min_beds=im.getStringExtra("min_beds");
			min_bedrooms=im.getStringExtra("min_bedrooms");
			min_bathrooms=im.getStringExtra("min_bathrooms");
			price_min=im.getStringExtra("price_min");
			price_max=im.getStringExtra("price_max");
			 position=im.getStringExtra("position");
			 
			 if(position!=null)
			 {
			 
			 if(position.equals("0"))
			 {
				head.setText("New York");
			 }
			 else if(position.equals("1"))
			 {
				 head.setText("Paris");
			 }
			 else if(position.equals("2"))
			 {
				 head.setText("Berlin");
			 }
			 else if(position.equals("3"))
			 {
				 head.setText("London");
			 }
			 else if(position.equals("4"))
			 {
				 head.setText("San Fransisco");
			 }
			 }
			 
			if(get_location==null)
			{
				get_location=speechText;
			}
			
			if(get_location!=null)
			{
				get_location=get_location.replaceAll("%20"," ");
				head.setText(get_location);
			}
		
		
			guests=((guests == null) ? "" : guests);
			roomtypes=((roomtypes == null) ? "": roomtypes);
			min_beds=((min_beds == null) ? "": min_beds);
			min_bedrooms=((min_bedrooms == null) ? "": min_bedrooms);
			min_bathrooms=((min_bathrooms == null) ? "": min_bathrooms);
			price_min=((price_min == null) ? "" : price_min);
			price_max=((price_max == null) ? "" : price_max);
			
			
			
			
			
			System.out.println("location  in search result---"+get_location);
			System.out.println("guests in search result=="+guests);
			System.out.println("roomtypes id in search result==="+roomtypes);
			System.out.println("bed in search result=="+min_beds);
			System.out.println("bathrooms in search result===="+min_bathrooms);
			System.out.println("bed roomsin search result==="+min_bedrooms);
			System.out.println("min price in search result=="+price_min);
			System.out.println("max price in search result==="+price_max);
			
//			get_location=get_location.replaceAll(" ","%20");
			//userid=im.getStringExtra("userid");			
			roomid=im.getStringExtra("room_id");
			System.out.println("user id in search result===="+userid);
			System.out.println("room id in search result==="+roomid);
			System.out.println("check arrival value"+arrive);
			
			System.out.println("check getarrive value"+getarrive);
			System.out.println("check getdepart value"+getdepart);
			
		
			//Calendar Functionality Start
			final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 
		
			
			// Setup listener
			final CaldroidListener listener = new CaldroidListener() {
				
				@Override
				public void onSelectDate(Date date,View view) {
					
					arrive=formatter.format(date).toString();
					showDialogButton.setText(arrive);
					showDialogButton1.setText("Select Date"); 
					if(arrive!=null)
					{
						System.out.println("check depart value if arrive exists"+depart);
						showDialogButton1.setText("Select Date");
						depart=null;	
						dialogCaldroidFragment.dismiss();
					}	
				}
			};
			
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
								"Select date");
					} else {
						
						// Setup arguments
						
						Calendar cal = Calendar.getInstance();
						
						// Disable Dates before today
				 		cal.add(Calendar.DATE, 0);
				 		System.out.println("cal min value"+cal);
						Date minDate = cal.getTime();
						System.out.println("Min date"+minDate);
						dialogCaldroidFragment.setMinDate(minDate);
						
						//Highlight Arrival and Departure Dates
						
						if(arrive!=null&&depart!=null)
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
						}else if(depart==null)
						{
							dialogCaldroidFragment.clearSelectedDates();
						}
						
						
						// Setup dialog
						Bundle bundle = new Bundle();
						bundle.putString(CaldroidFragment.DIALOG_TITLE,
								"Select Arrival date");
						bundle.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
						bundle.putBoolean(CaldroidFragment.SHOW_NAVIGATION_ARROWS, true);
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
						
						     depart=formatter.format(date).toString();
							arrive=showDialogButton.getText().toString();
							System.out.println("arrive in depart"+arrive);
							showDialogButton1.setText(depart);
							depart=showDialogButton1.getText().toString();
							System.out.println("depart in depart"+depart);
						 
							if(arrive!=null&&depart!=null)
							{
								 showDialogButton.setText(arrive);
						    	 showDialogButton1.setText(depart);
						    	 dialogCaldroidFragment.dismiss();							
							}
						 
					}
			};
			
			
			
			final Bundle state1 = savedInstanceState;
			if(showDialogButton!=null)
			{
			showDialogButton1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					if(arrive!=null)
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
						// Setup arguments
						
						
						
						if(arrive!=null)
						{
						DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
						Date date1=null;
						try {
					        date1 = df1 .parse(arrive);
					    } catch (ParseException e) {
					        e.printStackTrace();
					    }
						System.out.println("date"+date1);
						Calendar cal1 = Calendar.getInstance();
					    cal1.setTime(date1);
					    cal1.add(Calendar.DATE, 1);
					    date1=cal1.getTime();
					    //dialogCaldroidFragment.moveToDate(date1);
					    dialogCaldroidFragment.setMinDate(date1);
					    
					    
					     
					    
						//dialogCaldroidFragment.setMinDateFromString(arrive,df1);
						}
						if(arrive!=null&&depart!=null)
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
						else
						{
						dialogCaldroidFragment.clearSelectedDates();
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
											
						Bundle bundle = new Bundle();
						// Setup dialogTitle
						bundle.putString(CaldroidFragment.DIALOG_TITLE,
								"Select Departure date");
						bundle.putInt(CaldroidFragment.MONTH, m); // October
						bundle.putInt(CaldroidFragment.YEAR, y);
						bundle.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
						bundle.putBoolean(CaldroidFragment.SHOW_NAVIGATION_ARROWS, true);
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
					}
				}
			});
			}
			
			//Calendar Functionality End
			Button filter=(Button)findViewById(R.id.button);
			filter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(Search_result.this,MoreFilters.class);
					i.putExtra("userid",userid);
					i.putExtra("room_id",roomid);
					i.putExtra("location", get_location);
					startActivity(i);
					finish();
					
				}
			});
			
			ImageView back=(ImageView)findViewById(R.id.back);
			back.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent back=new Intent(Search_result.this,Swipe_tabs.class);
					back.putExtra("room_id",roomid);
					back.putExtra("userid",userid);
					startActivity(back);
					finish();
					
				}
			});
		
		System.out.println("getting location"+get_location);
		System.out.println("inside Search_result try");
		listView = (XListView) findViewById(R.id.listView10);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener( this);
		listView.hide();
		adapter = new CustomSearchAdapter(this, movieList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new Discover());
		System.out.println("after list view onclick in main");

			pDialog = new ProgressDialog(this);
			// Showing progress dialog before making http request
			
			 if(get_location!=null)
			   {
				 get_location=get_location.replaceAll(" ","%20");
			   }
			
			pDialog.setCancelable(false);
			pDialog.show();
			pDialog.setContentView(R.layout.progress_dialog);
			final String url=Liveurl+"search/search_results?guests="+guests+"&room_types="+roomtypes+"&price_min="+price_min+"&price_max="+price_max+"&min_beds="+min_beds+"&min_bedrooms="+min_bedrooms+"&min_bathrooms="+min_bathrooms+"&location="+get_location+"&start="+start;
			
			System.out.println("url in search result page=="+url);
	        //final String url = "Liveurl+"search/listing?location="+get_location;
	        //final String url=Liveurl+"search/listing?location="+get_location;
	    	getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));
	    	 
			// Creating volley request obj
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@SuppressWarnings("deprecation")
						@Override
						public void onResponse(JSONArray response) {
							Log.d(TAG, response.toString());
							hidePDialog();
							
							
							 //System.out.println("check login"+login_status);
							// Parsing json
							for (int i = 0; i < response.length(); i++) {
																
								try {
									
									JSONObject obj = response.getJSONObject(i);
									login_status=obj.getString("status");
									if(login_status.matches("No List Found"))
									{
										//Toast.makeText(getApplicationContext(), "No List Found", Toast.LENGTH_SHORT).show();
										AlertDialog alertDialog = new AlertDialog.Builder(
						                        Search_result.this).create();
						 
						        // Setting Dialog Title
						        alertDialog.setTitle("No Results so far!");						 
						        // Setting Dialog Message
						        alertDialog.setMessage("Remove filters or expand the area of your search and your're bound to find a space that suits you.");
						         // Setting OK Button
						        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						                public void onClick(DialogInterface dialog, int which) {
						                // Write your code here to execute after dialog closed
						               Intent i=new Intent(Search_result.this,Swipe_tabs.class);
						           	i.putExtra("room_id",roomid);
									i.putExtra("userid",userid);
						               startActivity(i);
						               finish();
						                }
						        });
						 
						        // Showing Alert Message
						        alertDialog.show();
										
									}
									else if(login_status.matches("List Found"))
									{
										//Toast.makeText(getApplicationContext(), "List Found", Toast.LENGTH_SHORT).show();
									Movie movie = new Movie();
									roomid1=obj.getString("id");
									userid1=obj.getString("user_id");
									movie.setTitle(obj.getString("title")); 
									movie.getid(obj.getString("id")); 
									movie.getprice(obj.getString("price")); 
									movie.roomtype(obj.getString("room_type")); 
									movie.getaddress(obj.getString("country")); 
									movie.setThumbnailUrl(obj.getString("image")); 
									movie.setThumbnailUrl1(obj.getString("src"));
									movie.getcity(obj.getString("city"));
									country_symbol=obj.getString("currency_code");
									 Currency c  = Currency.getInstance(country_symbol);    
									 country_symbol=c.getSymbol();
									 movie.setsymbol(country_symbol);
									// adding movie to movies array
									movieList.add(movie);
									
									}
									else
									{
										
									}
								} catch (JSONException e) {
									e.printStackTrace();
								}

							}
							System.out.println("roomid in search result"+roomid1);
							System.out.println("login_st"+login_status);
							System.out.println("userid in search result"+userid1);
							System.out.println("country symbol in search result"+country_symbol);
							
							// notifying list adapter about data changes
							// so that it renders the list view with updated data
							adapter.notifyDataSetChanged();
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
			
			start=start+5;
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
		
		
		/*public class Discover implements ListView.OnItemClickListener {
			*/
		public class Discover implements ListView.OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,	long id) 
			{
				System.out.println("inside search list");
				
				Toast.makeText(getApplicationContext(), "CLICKED in list discover ", Toast.LENGTH_SHORT).show(); 
				 Intent i=new Intent(Search_result.this,Detail_page.class);
				 i.putExtra("room_id", roomid);
				 System.out.println("room" +roomid);
				 i.putExtra("userid", userid);
			}
			}

	     @Override
	     public void onBackPressed()
	     {


	         
	     }
		
	     private void onLoad() {
	 		listView.stopRefresh();
	 		listView.stopLoadMore();
	 		listView.setRefreshTime("5");
	 	}
	 	
	 	public void onRefresh() {
	 		System.out.println("refresh");
	 	
	 				start = ++refreshCnt;
	 				items.clear();
	 				
	 			
	 			         
	 				onLoad();
	 			}
	 	
	 	
	 	

	 	public void onLoadMore() {
	 		
	 			
	 			
	 				
	 		pDialog = new ProgressDialog(this);
			// Showing progress dialog before making http request
			
			 if(get_location!=null)
			   {
				 get_location=get_location.replaceAll(" ","%20");
			   }
			
			pDialog.setCancelable(false);
			pDialog.show();
			pDialog.setContentView(R.layout.progress_dialog);
			final String url=Liveurl+"search/search_results?guests="+guests+"&room_types="+roomtypes+"&price_min="+price_min+"&price_max="+price_max+"&min_beds="+min_beds+"&min_bedrooms="+min_bedrooms+"&min_bathrooms="+min_bathrooms+"&location="+get_location+"&start="+start;
			
			System.out.println("url in search result page=="+url);
	        //final String url = "Liveurl+"search/listing?location="+get_location;
	        //final String url=Liveurl+"search/listing?location="+get_location;
	    	getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));
	    	 
			// Creating volley request obj
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@SuppressWarnings("deprecation")
						@Override
						public void onResponse(JSONArray response) {
							Log.d(TAG, response.toString());
							hidePDialog();
							
							
							 //System.out.println("check login"+login_status);
							// Parsing json
							for (int i = 0; i < response.length(); i++) {
																
								try {
									
									JSONObject obj = response.getJSONObject(i);
									login_status=obj.getString("status");
									if(login_status.matches("No List Found"))
									{
										
										listView.stopLoadMore();
										listView.hide();
										//Toast.makeText(getApplicationContext(), "No List Found", Toast.LENGTH_SHORT).show();
										/*AlertDialog alertDialog = new AlertDialog.Builder(
						                        Search_result.this).create();
						 
						        // Setting Dialog Title
						        alertDialog.setTitle("No Results so far!");						 
						        // Setting Dialog Message
						        alertDialog.setMessage("Remove filters or expand the area of your search and your're bound to find a space that suits you.");
						         // Setting OK Button
						        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						                public void onClick(DialogInterface dialog, int which) {
						                // Write your code here to execute after dialog closed
						               Intent i=new Intent(Search_result.this,SearchActivity.class);
						           	i.putExtra("room_id",roomid);
									i.putExtra("userid",userid);
						               startActivity(i);
						               finish();
						                }
						        });
						 
						        // Showing Alert Message
						        alertDialog.show();*/
										
									}
									else if(login_status.matches("List Found"))
									{
										//Toast.makeText(getApplicationContext(), "List Found", Toast.LENGTH_SHORT).show();
									Movie movie = new Movie();
									roomid1=obj.getString("id");
									userid1=obj.getString("user_id");
									movie.setTitle(obj.getString("title")); 
									movie.getid(obj.getString("id")); 
									movie.getprice(obj.getString("price")); 
									movie.roomtype(obj.getString("room_type")); 
									movie.getaddress(obj.getString("country")); 
									movie.setThumbnailUrl(obj.getString("image")); 
									movie.setThumbnailUrl1(obj.getString("src"));
									country_symbol=obj.getString("currency_code");
									movie.getcity(obj.getString("city"));
									 Currency c  = Currency.getInstance(country_symbol);    
									 country_symbol=c.getSymbol();
									 movie.setsymbol(country_symbol);
									// adding movie to movies array
									movieList.add(movie);
									listView.stopLoadMore();
									listView.hide();
									}
									else
									{
										listView.stopLoadMore();
										listView.hide();
									}
								} catch (JSONException e) {
									e.printStackTrace();
									listView.stopLoadMore();
									 Toast.makeText(getApplicationContext(), "No more list found", Toast.LENGTH_SHORT).show();
									 listView.hide();
								}

							}
							System.out.println("roomid in search result"+roomid1);
							System.out.println("login_st"+login_status);
							System.out.println("userid in search result"+userid1);
							System.out.println("country symbol in search result"+country_symbol);
							
							// notifying list adapter about data changes
							// so that it renders the list view with updated data
							adapter.notifyDataSetChanged();
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							VolleyLog.d(TAG, "Error: " + error.getMessage());
							hidePDialog();
							listView.stopLoadMore();
							listView.hide();

						}
					});

			// Adding request to request queue
			AppController.getInstance().addToRequestQueue(movieReq);
			
			start=start+5;
	 			
	 	
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