package com.cog.DropInn;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.adater.CustomSearchAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

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
import com.cog.DropInn.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

public class Unitedkingdom extends Activity {
	private static final String TAG = Swipe_tabs.class.getSimpleName();
	
	   SharedPreferences sharedpreferences;
	// Movies json url
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();
	private String country_symbol;
	private CustomSearchAdapter adapter;
	String userid;
	
	 String Liveurl="";
	 String userid1;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.unitedkingdom);
		//getActionBar().hide();

		  if( Build.VERSION.SDK_INT >= 9){
           StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
           StrictMode.setThreadPolicy(policy);
    }
		  
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
     Liveurl = sharedPreferences.getString("liveurl", null);
     
	 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
	 userid = sharedPreferences2.getString("userid", null);
     Intent i=getIntent();
		// userid1=i.getStringExtra("userid");
		 
		 System.out.println("userid in discover page "+userid1);
		   
    ListView    listView = (ListView) findViewById(R.id.listView_uk);
		adapter = new CustomSearchAdapter(this, movieList);
		listView.setAdapter(adapter);
		listView.setItemsCanFocus(false);
		//listView.setOnItemClickListener(new ListDiscover());
		
		System.out.println("after onclick listener");
		
		adapter.notifyDataSetChanged();
		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
	
		pDialog.setCancelable(false);
		pDialog.show();

		pDialog.setContentView(R.layout.progress_dialog);
		
		
		
     final String url = Liveurl+"search/search_results?location=United%20Kingdom";
     System.out.println("url in united kingdom page "+url);
    //final String url=Liveurl+"search/listing?location=india";
//*******************************************ListView code start*****************************************************
  

   // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
     
		// changing action bar color
		getActionBar().setBackgroundDrawable(
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

								JSONObject obj = response.getJSONObject(i);
								Movie movie = new Movie();
								String status=obj.getString("status");
								
								if(status.matches("No List Found"))
								{
									 Toast toast=Toast.makeText(getApplicationContext(), "No List found", Toast.LENGTH_SHORT);
			                            toast.setGravity(Gravity.CENTER, 0, 0);
			                            toast.show();
								}
								else if(status.matches("List Found"))
								{
								
								movie.getid(obj.getString("id"));
								movie.getaddress(obj.getString("address"));
								//movie.getsymbol(obj.getString("country_symbol"));
								country_symbol=obj.getString("currency_code");
								 Currency c  = Currency.getInstance(country_symbol);    
								 country_symbol=c.getSymbol();
								 movie.setsymbol(country_symbol);
								movie.getprice(obj.getString("price"));
								movie.setTitle(obj.getString("title"));
								movie.setThumbnailUrl(obj.getString("resize1")); 
								movie.setThumbnailUrl1(obj.getString("src"));
								// adding movie to movies array
								movieList.add(movie);
								}

							} 
							
							
							
							catch (JSONException e) {
								e.printStackTrace();
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
		Intent back=new Intent(Unitedkingdom.this,Swipe_tabs.class);
		back.putExtra("userid",userid);
		startActivity(back);
		finish();
		
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



