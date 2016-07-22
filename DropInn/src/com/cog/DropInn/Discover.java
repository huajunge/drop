package com.cog.DropInn;




import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.XListView.IXListViewListener;


public class Discover extends Fragment implements IXListViewListener {

	private static final String TAG = Swipe_tabs.class.getSimpleName();
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private String country_symbol,image;
	FrameLayout logout;
	String User_id,userid,currency1,currencyvalue;
	private static LruCache<String, Typeface> sTypefaceCache =
            new LruCache<String, Typeface>(12);
	// nav drawer title
	private CharSequence mDrawerTitle;
	ProgressDialog progressDialog;
	// used to store app title
	private CharSequence mTitle;
	private XListViewFooter mFooterView;
	RelativeLayout header;
	TextView text;
	ImageView mic;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	private final int RESULT_OK = -1;
	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
    int start=1;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter2;	
	Button loadmore;
	ProgressBar loadmore1;
	   private int bufferItemCount = 10;
	    private int currentPage = 0;
	    private int itemCount = 0;
	    private boolean isLoading = true;
	   SharedPreferences sharedpreferences;
	// Movies json url
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private ArrayList<String> items = new ArrayList<String>();
	private int start1 = 0;
	private static int refreshCnt = 0;
	private CustomListAdapter adapter;
	//String Liveurl="http://demo.cogzideltemplates.com/client/gottospot_android/mobile/";
	String Liveurl="";
	Handler handler;
	public String userid1;
	public String roomid;
	
	public SwipeRefreshLayout mSwipeRefreshLayout;
	 XListView    listView;
	 View footerView;
	 
	 int fragVal;
	 
/*	 static Discover init(int val) {
		 Discover truitonFrag = new Discover();
	 // Supply val input as an argument.
	 Bundle args = new Bundle();
	 args.putInt("val", val);
	 truitonFrag.setArguments(args);
	 return truitonFrag;
	 }*/
	@SuppressLint("NewApi")
	 @Override
	    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View discover = inflater.inflate(R.layout.activity_discover, container, false);
	   // mSwipeRefreshLayout = (SwipeRefreshLayout) discover.findViewById(R.id.container);
	       // mSwipeRefreshLayout.setOnRefreshListener(this);
	        
	        for (int j=0;j<5;j++)
	        {
	        	Movie movie = new Movie();
	        	movie.getprice("india");
	        	movieList.add(movie);
	        }
	    
		this.getActivity().getActionBar().setTitle("Discover");
		  if( Build.VERSION.SDK_INT >= 9){
              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
              StrictMode.setThreadPolicy(policy);
       }
		  
		SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Liveurl = sharedPreferences1.getString("liveurl", null);
       
		 userid = sharedPreferences1.getString("userid", null);
        currency1= sharedPreferences1.getString("currency1", null);
        System.out.println("******"+currency1);
		  
        Intent i=this.getActivity().getIntent();
		// userid1=i.getStringExtra("userid");
		 roomid=i.getStringExtra("room_id");
		 System.out.println("userid in discover page==== "+userid1);
		 //image=i.getStringExtra("profile");
		
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
         Editor editor = sharedPreferences.edit();
         System.out.println("userid in discover page upload to  shared preferences "+userid1);
        // editor.putString("userid", userid1); 
         editor.putString("liveurl", Liveurl);
         editor.commit();
        /* SharedPreferences settings = getSharedPreferences(Edit_profile.PREFS_NAME,0);
         userid = settings.getString("userid", User_id);
         System.out.println("userid in discover page after getting   shared preferences "+userid);*/
         //header=(RelativeLayout)discover.findViewById(R.id.head);
         text=(TextView)discover.findViewById(R.id.textView1);
         //mic=(ImageView)discover.findViewById(R.id.mic);
        /* text.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent header=new Intent(getActivity(),SearchActivity.class);
			header.putExtra("userid", userid);
			startActivity(header);
			getActivity().finish();
			}
		});
         
         header.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 			Intent header=new Intent(getActivity(),SearchActivity.class);
 			header.putExtra("userid", userid);
 			startActivity(header);
 			getActivity().finish();
 			}
 		});
         mic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				promptSpeechInput();
			}
		});*/
         
        
         listView = (XListView)discover. findViewById(R.id.listView10);
		adapter = new CustomListAdapter(getActivity(), movieList);
		listView.setAdapter(adapter);
		
	
		
		//listView.setSelectionFromTop(3, 0);
		
		listView.setItemsCanFocus(false);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener( this);
		
		listView.hide();
		
		    
		//listView.setOnItemClickListener(new ListDiscover());
				listView.setOnScrollListener(new OnScrollListener() {
			        private int mLastFirstVisibleItem;

			        public void onScrollStateChanged(AbsListView view, int scrollState) {

			        }

			        public void onScroll(AbsListView view, int firstVisibleItem,
			                int visibleItemCount, int totalItemCount) {
			      
			        	
				
			             

			            if(mLastFirstVisibleItem<firstVisibleItem)
			            {
			                Log.i("SCROLLING DOWN","TRUE");
			               // header.setVisibility(View.INVISIBLE);
			               
			            
			            }
			            if(mLastFirstVisibleItem>firstVisibleItem)
			            {
			                Log.i("SCROLLING UP","TRUE");
			               // header.setVisibility(View.VISIBLE);
			                
			                
			            }
			            mLastFirstVisibleItem=firstVisibleItem;
			            
			          
			                //Algorithm to check if the last item is visible or not
			              

			        }
			    });
		//listView.setOnItemClickListener(new ListDiscover());
				
				
		
		System.out.println("after onclick listener");
		
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
        //final String url = Liveurl+"search/discover";
		   if (isOnline(getActivity())) {
       final String url=Liveurl+"search/discover?common_currency="+currency1+"&start="+start;
       System.out.println("discover url" +url);
		 //final String url=Liveurl+"search/discover";
//*******************************************ListView code start*****************************************************
     
 
      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
        
		// changing action bar color
		this.getActivity().getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));

		// Creating volley request obj
		
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();
						
					

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								
								/*if(i!=0||i!=1||i!=3)
								{*/
								
							
								
								JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								movie.getid(obj.getString("id"));
								movie.getuserid(obj.getString("user_id"));
									movie.getaddress(obj.getString("address"));	
								country_symbol=obj.getString("common_currency_code");
								Currency c  = Currency.getInstance(country_symbol);    
								country_symbol=c.getSymbol();
								movie.getprice(obj.getString("price")); 
								movie.roomtype(obj.getString("room_type")); 
								//csymbol=obj.getString("currency_symbol");
								//currencyvalue=obj.getString("common_currency_value");
								//country_symbol = Html.fromHtml(country_symbol).toString();
								movie.setsymbol(country_symbol);
								movie.getprice(obj.getString("common_currency_value"));
								movie.setTitle(obj.getString("title"));
								movie.setThumbnailUrl(obj.getString("resize1")); 
								movie.setThumbnailUrl1(obj.getString("profile_pic"));
								movie.getcity(obj.getString("city"));
								// adding movie to movies array
								
								movieList.add(movie);
							//footerView.setVisibility(View.VISIBLE);
								//}
								/*else
								{
									JSONObject obj = response.getJSONObject(i);
									Movie movie = new Movie();
									movie.getid(obj.getString("id"));
									movieList.add(movie);
								}*/
								hidePDialog();
								
							} catch (JSONException e) {	
								e.printStackTrace();
								hidePDialog();
							}

						}

						
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
		
		System.out.println("after loop listview view ");
		
		start=start+5;
		   }
		     else
		     {
		    		Toast.makeText(getActivity(), "Check your internet connection",Toast.LENGTH_SHORT).show();
		    		hidePDialog();
		     }
         
			return discover;
			
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
	/**
	 * Slide menu item click listener
	 * */
	

	/*** Showing google speech input dialog ***/
	private void promptSpeechInput() {
	    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
	            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
	    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
	            getString(R.string.speech_prompt));
	    try {
	        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
	    } catch (ActivityNotFoundException a) {
	        Toast.makeText(getActivity().getApplicationContext(),
	                getString(R.string.speech_not_supported),
	                Toast.LENGTH_SHORT).show();
	    }
	}
	/*** Receiving speech input***/
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    switch (requestCode) {
	    case REQ_CODE_SPEECH_INPUT: {
	        if (resultCode == RESULT_OK && null != data) {

	            ArrayList<String> result = data
	                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	            Intent i=new Intent(getActivity(),Search_result.class);
	            i.putExtra("speech", result.get(0));
	            getActivity().startActivity(i);
	            getActivity().finish();
	        }
	        break;
	    }

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
		
			
		 if (isOnline(getActivity())) {
				
				 final String url=Liveurl+"search/discover?common_currency="+currency1+"&start="+start;
			       System.out.println("discover url" +url);
					 //final String url=Liveurl+"search/discover";
			//*******************************************ListView code start*****************************************************
			     
			 
			      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
			        
					// changing action bar color
					

					// Creating volley request obj
					
					JsonArrayRequest movieReq = new JsonArrayRequest(url,
							new Response.Listener<JSONArray>() {
								@Override
								public void onResponse(JSONArray response) {
									Log.d(TAG, response.toString());
									hidePDialog();
									
									System.out.println("3 images" +response.length());
									
									
									// Parsing json
									for (int i = 0; i < response.length(); i++) {
										try {

											
											/*if(i!=0||i!=1||i!=3)
											{*/
											JSONObject obj = response.getJSONObject(i);
											Movie movie = new Movie();
										
											movie.getid(obj.getString("id"));
											movie.getuserid(obj.getString("user_id"));
												movie.getaddress(obj.getString("address"));	
											country_symbol=obj.getString("common_currency_code");
											Currency c  = Currency.getInstance(country_symbol);    
											country_symbol=c.getSymbol();
											movie.getprice(obj.getString("price")); 
											movie.roomtype(obj.getString("room_type")); 
											//csymbol=obj.getString("currency_symbol");
											//currencyvalue=obj.getString("common_currency_value");
											//country_symbol = Html.fromHtml(country_symbol).toString();
											movie.setsymbol(country_symbol);
											movie.getprice(obj.getString("common_currency_value"));
											movie.setTitle(obj.getString("title"));
											movie.setThumbnailUrl(obj.getString("resize1")); 
											movie.setThumbnailUrl1(obj.getString("profile_pic"));
											movie.getcity(obj.getString("city"));
											// adding movie to movies array
										
											movieList.add(movie);
										//footerView.setVisibility(View.VISIBLE);
											//}
											/*else
											{
												JSONObject obj = response.getJSONObject(i);
												Movie movie = new Movie();
												movie.getid(obj.getString("id"));
												movieList.add(movie);
											}*/
											listView.stopLoadMore();
											listView.hide();
										} catch (JSONException e) {	
											e.printStackTrace();
											listView.stopLoadMore();
											 Toast.makeText(getActivity(), "No more list found", Toast.LENGTH_SHORT).show();
											 listView.hide();
										}

									}

									
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
					
					System.out.println("after loop listview view ");
					
					start=start+5;
				//onLoad();
		 }
	     else
	     {
	    		Toast.makeText(getActivity(), "Check your internet connection",Toast.LENGTH_SHORT).show();
	     }
	
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



	
	
