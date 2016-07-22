package com.cog.DropInn;



import info.androidhive.customlistviewvolley.adater.CustomYourlistAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.XListView.IXListViewListener;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;


public class Yourlisting extends Fragment implements IXListViewListener {
	private static final String TAG = Yourlisting.class.getSimpleName();
	Bitmap edbitmap;
	Bitmap edbitmap1;
	private CustomYourlistAdapter adapter;
	 String Liveurl="";
	 URL Login_Url;
	    String reader,userid;
	    String login_inputline;
	    String login_status;
	    JSONArray login_jsonarray;
	    JSONObject login_jsonobj,login_Error;
	     ListView listViewItems2;
	     ImageView bg_img;
	     RelativeLayout layout;
		 TextView text1,text3,text4;
		 Button explore;
	     String roomid;
	     XListView    listView ;
	     int start=1;
	 	private ArrayList<String> items = new ArrayList<String>();
		private int start1 = 0;
		private static int refreshCnt = 0;
	     String name="ramesh";
	     private ProgressDialog pDialog;
	 	private List<Movie> movieList = new ArrayList<Movie>();
	 	Button listyourspace1;
		 int fragVal;
		 ImageView list;
		 
	/*	 static Yourlisting init(int val) {
			 Yourlisting truitonFrag = new Yourlisting();
		 // Supply val input as an argument.
		 Bundle args = new Bundle();
		 args.putInt("val", val);
		 truitonFrag.setArguments(args);
		 return truitonFrag;
		 }*/
	 	 @Override
		    public View onCreateView(LayoutInflater inflater, ViewGroup container,
		            Bundle savedInstanceState) {
		 
		        View yourlist = inflater.inflate(R.layout.yourlist_listview, container, false);
		        
		//getActivity().getActionBar().hide();

		  if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
     }
		  
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
      Liveurl = sharedPreferences.getString("liveurl", null);
		Intent i=getActivity().getIntent();
		//userid=i.getStringExtra("userid");
		//ImageButton add=(ImageButton)yourlist.findViewById(R.id.imageButton1);
		   SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
		   userid = sharedPreferences1.getString("userid",null);
		System.out.println("userid in shared preferences in your listing "+userid);
		
		System.out.println(" your listing  page user id   "+userid);
		
		/*add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent add1=new Intent(getActivity(),Listspace.class);
				add1.putExtra("userid",userid);
				startActivity(add1);
				getActivity().finish();
			}
		});*/
		
		   //	explore=(Button)yourlist.findViewById(R.id.button1);
		  listView = (XListView)yourlist.findViewById(R.id.listView3);
		listyourspace1=(Button)yourlist.findViewById(R.id.listyourspace);
	       layout=(RelativeLayout)yourlist.findViewById(R.id.relativeLayout1);
	       //list=(ImageView)yourlist.findViewById(R.id.list);
	      /* text3=(TextView)yourlist.findViewById(R.id.textView3);
	       text4=(TextView)yourlist.findViewById(R.id.textView4);
	       text1=(TextView)yourlist.findViewById(R.id.textView1);*/
	
		
		
	     
			adapter = new CustomYourlistAdapter(getActivity(), movieList);
			listView.setAdapter(adapter);
			listView.setItemsCanFocus(false);
			listView.setPullLoadEnable(true);
			listView.setXListViewListener( this);
			listView.hide();
			//listView.setOnItemClickListener(new YourlistClickListener());
			
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
		                listyourspace1.setVisibility(View.INVISIBLE);
		            
		            }
		            if(mLastFirstVisibleItem>firstVisibleItem)
		            {
		                Log.i("SCROLLING UP","TRUE");
		               // header.setVisibility(View.VISIBLE);
		                listyourspace1.setVisibility(View.VISIBLE);
		                
		            }
		            mLastFirstVisibleItem=firstVisibleItem;
		            
		          
		                //Algorithm to check if the last item is visible or not
		              

		        }
		    });
			
			adapter.notifyDataSetChanged();
			
			  listyourspace1.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				         Intent reset=new Intent(getActivity(),Listspace.class);
		             
		  					
		                     startActivity(reset);
		                     getActivity().finish();
		                   
									
						
						
						
					}
				});
			/*pDialog = new ProgressDialog(getActivity());
			// Showing progress dialog before making http request
			
			pDialog.setCancelable(false);
			pDialog.show();
			pDialog.setContentView(R.layout.progress_dialog);*/
			
			
	       // final String url = "http://demo.cogzideltemplates.com/client/gottospot_android/mobile/rooms/your_listing?user_id=183";
	       final String url=Liveurl+"rooms/your_listing?user_id="+userid+"&start="+start;
	//*******************************************ListView code start*****************************************************
	     System.out.println("url in your list"+url);
	 
	      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
	        
			// changing action bar color
			getActivity().getActionBar().setBackgroundDrawable(
					new ColorDrawable(Color.parseColor("#1b1b1b")));

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
									login_status=obj.getString("status");
									if(login_status.matches("No data"))
									{ 
										listyourspace1.setVisibility(View.INVISIBLE);
										  layout.setOnClickListener(new View.OnClickListener() {
												
												@Override
												public void onClick(View v) {
													// TODO Auto-generated method stub
											         Intent reset=new Intent(getActivity(),Listspace.class);
									             
									  					
									                     startActivity(reset);
									                     getActivity().finish();
									                   
																
													
													
													
												}
											});
									}
									else
									{
										layout.setVisibility(View.INVISIBLE);
										//list.setVisibility(View.INVISIBLE);
										/*layout.setVisibility(View.INVISIBLE);
										text1.setVisibility(View.INVISIBLE);
										text3.setVisibility(View.INVISIBLE);
										text4.setVisibility(View.INVISIBLE);*/
									Movie movie = new Movie();
									movie.getid(obj.getString("id"));
									movie.getaddress(obj.getString("room_type"));
									movie.setsymbol(obj.getString("country"));
									movie.getprice(obj.getString("status"));
									movie.setTitle(obj.getString("title"));
									movie.setThumbnailUrl(obj.getString("image"));  
									//movie.total(obj.getString("list_pay"));
									movie.step(obj.getString("step_status"));
									movie.check(obj.getString("check_status"));
									
									// adding movie to movies array
									movieList.add(movie);

									}} catch (JSONException e) {
									e.printStackTrace();
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
			
			start=start+5;
		
		return yourlist;
		
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
			
				
				
					
			 final String url=Liveurl+"rooms/your_listing?user_id="+userid+"&start="+start;
				//*******************************************ListView code start*****************************************************
				     System.out.println("url in your list"+url);
				 
				      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
				        
						// changing action bar color
						getActivity().getActionBar().setBackgroundDrawable(
								new ColorDrawable(Color.parseColor("#1b1b1b")));

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
												login_status=obj.getString("status");
												if(login_status.matches("No data"))
												{ 
													listView.stopLoadMore();
													 Toast.makeText(getActivity(), "No more list found", Toast.LENGTH_SHORT).show();
													 listView.hide();
												}
												else
												{
													//layout.setVisibility(View.INVISIBLE);
													//list.setVisibility(View.INVISIBLE);
													/*layout.setVisibility(View.INVISIBLE);
													text1.setVisibility(View.INVISIBLE);
													text3.setVisibility(View.INVISIBLE);
													text4.setVisibility(View.INVISIBLE);*/
												Movie movie = new Movie();
												movie.getid(obj.getString("id"));
												movie.getaddress(obj.getString("room_type"));
												movie.setsymbol(obj.getString("country"));
												movie.getprice(obj.getString("status"));
												movie.setTitle(obj.getString("title"));
												movie.setThumbnailUrl(obj.getString("image"));  
												//movie.total(obj.getString("list_pay"));
												movie.step(obj.getString("step_status"));
												movie.check(obj.getString("check_status"));
												
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
					//onLoad();
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
		

