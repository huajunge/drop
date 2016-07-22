package com.cog.DropInn;

import info.androidhive.customlistviewvolley.adater.CustomInboxAdapter;
import info.androidhive.customlistviewvolley.adater.CustomInboxHostAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.XListView.IXListViewListener;


public class HostInboxFragment extends Fragment implements IXListViewListener {
	
	private static final String TAG = Swipe_tabs.class.getSimpleName();
	Bitmap edbitmap;
	Bitmap edbitmap1;
	private CustomInboxHostAdapter adapter;
	 String Liveurl="";
	 URL Login_Url;
	 RelativeLayout layout;
	 TextView text1,text3,text4;
	 String country_symbol;
	    String reader,userid;
	    String login_inputline;
	    String login_status;
	    JSONArray login_jsonarray;
	    JSONObject login_jsonobj,login_Error;
	     ListView listViewItems2;
	     ImageView bg_img;
	     ImageButton back;
	     Button explore;
	     ViewPager viewPager;
	String roomid;
	XListView    listView;
	String name="ramesh";
	private ArrayList<String> items = new ArrayList<String>();
	private int start1 = 0;
	private static int refreshCnt = 0;
	private int start=1;
	     private ProgressDialog pDialog;
	 	private List<Movie> movieList = new ArrayList<Movie>();
	 	String currency1;	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View hostinbox = inflater.inflate(R.layout.inbox_listhost, container, false);
        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
     }
		  
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
      Liveurl = sharedPreferences.getString("liveurl", null);
      
      currency1= sharedPreferences.getString("currency1", null);
		 userid = sharedPreferences.getString("userid", null);
		
		//userid=getActivity().getIntent().getStringExtra("userid");
		 //back=(ImageButton)inbox.findViewById(R.id.imageButton1);
		
	
		
		   SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
		   userid = sharedPreferences1.getString("userid",null);
		System.out.println("userid in shared preferences in Inbox page "+userid);
		/*back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent add1=new Intent(getActivity(),Swipe_tabs.class);
				add1.putExtra("userid",userid);
				startActivity(add1);
				getActivity().finish();
			}
		});*/
	       listView = (XListView)hostinbox.findViewById(R.id.listView5);
	      // explore=(Button)hostinbox.findViewById(R.id.button1);
	      // bg_img=(ImageView)hostinbox.findViewById(R.id.imageView1);
	       
	       layout=(RelativeLayout)hostinbox.findViewById(R.id.beforelogin);
	     /*  text3=(TextView)hostinbox.findViewById(R.id.textView3);
	       text4=(TextView)hostinbox.findViewById(R.id.textView4);
	       text1=(TextView)hostinbox.findViewById(R.id.textView1);*/
	       
	       
	       viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

			adapter = new CustomInboxHostAdapter(getActivity(), movieList);
			listView.setAdapter(adapter);
			listView.setItemsCanFocus(false);
			listView.setPullLoadEnable(true);
			listView.setXListViewListener( this);
			listView.hide();
			//listView.setOnItemClickListener(new YourlistClickListener());
			
			System.out.println("after onclick listener");
			
			
		/*	if(userid=="null"||userid==null)
			{
				explore.setText("Log In");
			}
			else
			{
				explore.setText("Start Explore");
			}*/
			adapter.notifyDataSetChanged();
			pDialog = new ProgressDialog(getActivity());
			// Showing progress dialog before making http request
		
			pDialog.setCancelable(false);
			pDialog.show();
			pDialog.setContentView(R.layout.progress_dialog);
			
			
			if(currency1==null)
			{
				currency1="USD";
			}
			
	        //final String url = "http://demo.cogzideltemplates.com/client/gottospot_android/mobile/payment/host_reservation_inbox?user_id=230";
	       final String url=Liveurl+"payment/host_reservation_inbox?userto="+userid+"&start="+start+"&common_currency="+currency1;
	//*******************************************ListView code start*****************************************************
	     System.out.println("url in Inbox page==="+url);
	 
	      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
	        
			// changing action bar color
			getActivity().getActionBar().setBackgroundDrawable(
					new ColorDrawable(Color.parseColor("#ffffffff")));

			// Creating volley request obj
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@SuppressWarnings("deprecation")
						@Override
						public void onResponse(JSONArray response) {
							Log.d(TAG, response.toString());
							hidePDialog();

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {
									

									JSONObject obj = response.getJSONObject(i);
									
									login_status=obj.getString("Status");
									if(login_status.matches("No Reservation Request"))
									{
										hidePDialog();
										/*  explore.setOnClickListener(new View.OnClickListener() {
												
												@Override
												public void onClick(View v) {
													// TODO Auto-generated method stub
													if(explore.getText().equals("Log In"))
													{
													Intent back=new Intent(getActivity(),MainActivity.class);
													getActivity().startActivity(back);
													}
													else
													{
														viewPager.setCurrentItem(0);
													}
													
												}
											});
										*/
										layout.setVisibility(View.VISIBLE);
									}
									else
									{
										  pDialog = new ProgressDialog(getActivity());
							         	   
							         		// Showing progress dialog before making http request
							         	
							         		pDialog.setCancelable(false);
							         		pDialog.show();
							         		pDialog.setContentView(R.layout.progress_dialog);
										//explore.setText("Start Explore");
										layout.setVisibility(View.INVISIBLE);
										/*text1.setVisibility(View.INVISIBLE);
										text3.setVisibility(View.INVISIBLE);
										text4.setVisibility(View.INVISIBLE);*/
																		
									Movie movie = new Movie();						
									
									movie.getuserby(obj.getString("userby"));
									movie.resid(obj.getString("reservation_id"));
									movie.getid(obj.getString("room_id"));
									movie.getid1(obj.getString("id"));
									movie.userto(obj.getString("userto"));
									movie.isread(obj.getString("isread"));
									
									//country_symbol=obj.getString("common_currency_code");
									 
									//movie.setsymbol(country_symbol);
									
								
									/*movie.getaddress(obj.getString("username"));									
									movie.getprice(obj.getString("price"));
									country_symbol=obj.getString("currency_code");
									 //Currency c  = Currency.getInstance(country_symbol);    
									 //country_symbol=c.getSymbol();
									movie.setsymbol(country_symbol);*/
									movie.setTitle(obj.getString("title"));
									movie.setThumbnailUrl(obj.getString("profile_pic")); 
									movie.setstatus(obj.getString("status"));
									movie.setcheckin(obj.getString("checkin"));
									movie.setcheckout(obj.getString("checkout"));
									movie.setcreated(obj.getString("created"));	
									movie.guest(obj.getString("guest"));
									movie.message(obj.getString("message"));
									movie.username(obj.getString("username"));
									movie.getprice(obj.getString("common_currency_value"));
															
									 
									// adding movie to movies array
									movieList.add(movie);
									hidePDialog();

									}} catch (JSONException e) {
								e.printStackTrace();
								hidePDialog();
								}

							}
							System.out.println("roomid in yourlist"+roomid);
							// notifying list adapter about data changes
							// so that it renders the list view with updated data
							hidePDialog();
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
			
			System.out.println("after loop listview view ");
		
/*
		 public Bitmap getResizedBitmap(Bitmap setImageBitmap,int newWidth,int newHeight) {
			    int width = setImageBitmap.getWidth();
			    int height = setImageBitmap.getHeight();
			    float scaleWidth = ((float) newWidth) / width;
			    float scaleHeight = ((float) newHeight) / height;
			    // CREATE A MATRIX FOR THE MANIPULATION
			    Matrix matrix = new Matrix();
			    // RESIZE THE BIT MAP
			    matrix.postScale(scaleWidth, scaleHeight);

			    // "RECREATE" THE NEW BITMAP
			    Bitmap resizedBitmap = Bitmap.createBitmap(setImageBitmap, 0, 0, width, height, matrix, false);
			    return resizedBitmap;
			}*/
			start=start+10;
		return hostinbox;
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
				
					
					
						
				   final String url=Liveurl+"payment/host_reservation_inbox?userto="+userid+"&start="+start+"&common_currency="+currency1;
					//*******************************************ListView code start*****************************************************
					     System.out.println("url in Inbox page==="+url);
					 
					      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
					        
							// changing action bar color
							getActivity().getActionBar().setBackgroundDrawable(
									new ColorDrawable(Color.parseColor("#ffffffff")));

							// Creating volley request obj
							JsonArrayRequest movieReq = new JsonArrayRequest(url,
									new Response.Listener<JSONArray>() {
										@SuppressWarnings("deprecation")
										@Override
										public void onResponse(JSONArray response) {
											Log.d(TAG, response.toString());
											//hidePDialog();

											// Parsing json
											for (int i = 0; i < response.length(); i++) {
												try {
													

													JSONObject obj = response.getJSONObject(i);
													
													login_status=obj.getString("Status");
													if(login_status.matches("No Reservation Request"))
													{
														
														Toast.makeText(getActivity(), "No more Messages",Toast.LENGTH_SHORT).show();
														listView.stopLoadMore();
														listView.hide();
														 /* explore.setOnClickListener(new View.OnClickListener() {
																
																@Override
																public void onClick(View v) {
																	// TODO Auto-generated method stub
																	
																	if(explore.getText().equals("Log In"))
																	{
																	Intent back=new Intent(getActivity(),MainActivity.class);
																	getActivity().startActivity(back);
																	}
																	else
																	{
																		viewPager.setCurrentItem(0);
																	}
																	
																}
															});
														*/
													}
													else
													{
														//explore.setText("Start Explore");
													/*	layout.setVisibility(View.INVISIBLE);
														text1.setVisibility(View.INVISIBLE);
														text3.setVisibility(View.INVISIBLE);
														text4.setVisibility(View.INVISIBLE);*/
																						
													Movie movie = new Movie();						
													
													movie.getuserby(obj.getString("userby"));
													movie.resid(obj.getString("reservation_id"));
													movie.getid(obj.getString("room_id"));
													movie.getid1(obj.getString("id"));
													movie.userto(obj.getString("userto"));
													movie.isread(obj.getString("isread"));
													
													/*movie.getaddress(obj.getString("username"));									
													movie.getprice(obj.getString("price"));
													country_symbol=obj.getString("currency_code");
													 //Currency c  = Currency.getInstance(country_symbol);    
													 //country_symbol=c.getSymbol();
													movie.setsymbol(country_symbol);*/
													movie.setTitle(obj.getString("title"));
													movie.setThumbnailUrl(obj.getString("profile_pic")); 
													movie.setstatus(obj.getString("status"));
													movie.setcheckin(obj.getString("checkin"));
													movie.setcheckout(obj.getString("checkout"));
													movie.setcreated(obj.getString("created"));	
													movie.guest(obj.getString("guest"));
													movie.message(obj.getString("message"));
													movie.username(obj.getString("username"));
													movie.getprice(obj.getString("common_currency_value"));					
																			
													 
													// adding movie to movies array
													movieList.add(movie);
													listView.stopLoadMore();
													listView.hide();

													}} catch (JSONException e) {
												e.printStackTrace();
												listView.stopLoadMore();
												 Toast.makeText(getActivity(), "No more list found", Toast.LENGTH_SHORT).show();
												 listView.hide();
												}

											}
											System.out.println("roomid in yourlist"+roomid);
											// notifying list adapter about data changes
											// so that it renders the list view with updated data
											adapter.notifyDataSetChanged();
										}
									}, new Response.ErrorListener() {
										@Override
										public void onErrorResponse(VolleyError error) {
											VolleyLog.d(TAG, "Error: " + error.getMessage());
											//hidePDialog();

										}
									});

							// Adding request to request queue
							AppController.getInstance().addToRequestQueue(movieReq);
							
							System.out.println("after loop listview view ");
						
				/*
						 public Bitmap getResizedBitmap(Bitmap setImageBitmap,int newWidth,int newHeight) {
							    int width = setImageBitmap.getWidth();
							    int height = setImageBitmap.getHeight();
							    float scaleWidth = ((float) newWidth) / width;
							    float scaleHeight = ((float) newHeight) / height;
							    // CREATE A MATRIX FOR THE MANIPULATION
							    Matrix matrix = new Matrix();
							    // RESIZE THE BIT MAP
							    matrix.postScale(scaleWidth, scaleHeight);

							    // "RECREATE" THE NEW BITMAP
							    Bitmap resizedBitmap = Bitmap.createBitmap(setImageBitmap, 0, 0, width, height, matrix, false);
							    return resizedBitmap;
							}*/
							start=start+10;
						//onLoad();
					
			
			
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
