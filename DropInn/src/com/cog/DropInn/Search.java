package com.cog.DropInn;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;



public class Search extends Fragment {

	public AutoCompleteTextView atvPlaces;
	Button scancel;
	ImageView search,clear,back;
	//ListView lv=(ListView)findViewById(R.id.listView1);
	PlacesTask placesTask;
	ParserTask parserTask;
	String User_id,fbuserproimg,WhoLogin,checkpassword,location;
	String userid,roomid;
	 int fragVal;
	 ImageView currentlocation;
	 GPSTracker gps;
	 Geocoder geocoder;
	 List<Address> addresses;
	 TextView cancel;
	 TextView TextView2;
	/* static Search init(int val) {
		 Search truitonFrag = new Search();
	 // Supply val input as an argument.
	 Bundle args = new Bundle();
	 args.putInt("val", val);
	 truitonFrag.setArguments(args);
	 return truitonFrag;
	 }*/
	 private SimpleAdapter adapter=null;
	@SuppressLint("NewApi")
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View searchpage = inflater.inflate(R.layout.activity_search1, container, false);
	   // mSwipeRefreshLayout = (SwipeRefreshLayout) discover.findViewById(R.id.container);
	        
	        
	        if( Build.VERSION.SDK_INT >= 9){
	            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy);
	     }
	        
	    /*	getActivity().getActionBar().setBackgroundDrawable(
					new ColorDrawable(Color.parseColor("#1b1b1b")));*/
	        if (isOnline(getActivity())) {
	        
	        atvPlaces = (AutoCompleteTextView)searchpage.findViewById(R.id.atv_places);	
	        currentlocation = (ImageView)searchpage.findViewById(R.id.currentlocation);	
	        cancel = (TextView)searchpage.findViewById(R.id.cancel);
	        TextView2 = (TextView)searchpage.findViewById(R.id.TextView2);
	        //back=(ImageView)searchpage.findViewById(R.id.imageView);
	        clear=(ImageView)searchpage.findViewById(R.id.clear);
	        
			/*Typeface typeFace=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Thonburi.ttf");
			atvPlaces.setTypeface(typeFace);
			atvPlaces.setTextSize(20);
			cancel.setTypeface(typeFace);
			TextView2.setTypeface(typeFace);*/
	        	/*currentlocation.setVisibility(View.GONE);
	        	clear.setVisibility(View.VISIBLE);*/
	        	
	      
	       /*
	        	currentlocation.setVisibility(View.VISIBLE);
	        	clear.setVisibility(View.INVISIBLE);*/
	        
	        currentlocation.setVisibility(View.VISIBLE);
        	clear.setVisibility(View.GONE);
        	TextView2.setVisibility(View.VISIBLE);
	       	       
	        
	        cancel.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	 InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
	            	    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	            }
	        });
	       
	        currentlocation.setOnClickListener(new View.OnClickListener() {
	             
	            @Override
	            public void onClick(View arg0) {        
	                // create class object
	                gps = new GPSTracker(Search.this.getActivity());
	 
	                // check if GPS enabled     
	                if(gps.canGetLocation()){
	                     
	                    double latitude = gps.getLatitude();
	                    double longitude = gps.getLongitude();
	                    geocoder = new Geocoder(getActivity(), Locale.getDefault());

	                    try {
							addresses = geocoder.getFromLocation(latitude, longitude, 1);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // Here 1 represent max location result to returned, by documents it recommended 1 to 5

	                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
	                    String city = addresses.get(0).getLocality();
	                    String state = addresses.get(0).getAdminArea();
	                    String country = addresses.get(0).getCountryName();
	                    /*String postalCode = addresses.get(0).getPostalCode();
	                    String knownName = addresses.get(0).getFeatureName();*/
	                     
	                    // \n is for new line
	                    
	                    Intent can=new Intent(getActivity(),Search_result.class);
		                can.putExtra("location",city);
		            	can.putExtra("room_id",roomid);
						can.putExtra("userid",userid);
		                //can.putExtra("state", state);
		                //can.putExtra("country",country);
		                System.out.println("city"+city);
		              //  System.out.println("state"+state);
		               // System.out.print("country"+country);
		         		
		         		startActivity(can);
		         		getActivity().finish();
	                   
	                }else{
	                    // can't get location
	                    // GPS or Network is not enabled
	                    // Ask user to enable GPS/network in settings
	                    gps.showSettingsAlert();
	                }
	                 
	            }
	        });

	      
	      
	        
	        atvPlaces.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(final View v, final MotionEvent ev) {
	                        v.requestFocusFromTouch();
	               return false;
	        }
	        });
			    
	        atvPlaces.postDelayed(new Runnable() {
	            @Override
	            public void run() {
	            	atvPlaces.requestFocusFromTouch();
	            }
	            }, 400);

			/*atvPlaces.setFocusableInTouchMode(true);
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);*/
			//search=(ImageView)search1.findViewById(R.id.imageView1);
			
	     
			//showHideKeyboard(true);
				
			clear.setOnClickListener(new View.OnClickListener() {		
				@Override
				public void onClick(View v) {
					if (!atvPlaces.equals(""))
							{
						atvPlaces.setText("");
							}
				
				
					
				}
			});
			
			 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
			 userid = sharedPreferences2.getString("userid", null);
			
			atvPlaces.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {				
					placesTask = new PlacesTask();				
					placesTask.execute(s.toString());
	                //search.setVisibility(View.INVISIBLE);	
					if (s.length()>0)
					{
					currentlocation.setVisibility(View.GONE);
		        	clear.setVisibility(View.VISIBLE);
		        	TextView2.setVisibility(View.GONE);
					}
					else 
					{
						currentlocation.setVisibility(View.VISIBLE);
			        	clear.setVisibility(View.GONE);
			        	TextView2.setVisibility(View.VISIBLE);
					}
					}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					
					// TODO Auto-generated method stub
				/*	currentlocation.setVisibility(View.VISIBLE);
		        	clear.setVisibility(View.GONE);
		        	TextView2.setVisibility(View.VISIBLE);*/
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					
					// TODO Auto-generated method stub
					/*currentlocation.setVisibility(View.GONE);
		        	clear.setVisibility(View.VISIBLE);*/
					
				/*	currentlocation.setVisibility(View.VISIBLE);
		        	clear.setVisibility(View.GONE);
		        	TextView2.setVisibility(View.VISIBLE);*/
					
				}
				

			});	
			
			

			String CorrectPlace=atvPlaces.getText().toString();
			System.out.println("Selected Text"+CorrectPlace);
			//ListView list = (ListView) findViewById(R.id.listview);
			
			
			
			CorrectPlace=atvPlaces.getText().toString();
				System.out.println("Selected Text"+CorrectPlace); 
				
				String Selectionend=atvPlaces.getEditableText().toString();
				System.out.println("getEditableText"+Selectionend); 
				
	        }
	        else
	        {
	        	Toast.makeText(getActivity(), "Check your internet connection",Toast.LENGTH_SHORT).show();
	        }
				
			return searchpage;
			
		}

	@Override
	public void setUserVisibleHint(final boolean isVisibleToUser) {
	        super.setUserVisibleHint(isVisibleToUser);
	        if (atvPlaces != null) {
	        	atvPlaces.requestFocusFromTouch();
	        }

	    }
		/* A method to download json data from url */
	   public String downloadUrl(String strUrl) throws IOException{
	        String data = "";
	        InputStream iStream = null;
	        HttpURLConnection urlConnection = null;
	        try{
	                URL url = new URL(strUrl);                

	                // Creating an http connection to communicate with url 
	                urlConnection = (HttpURLConnection) url.openConnection();

	                // Connecting to url 
	                urlConnection.connect();

	                // Reading data from url 
	                iStream = urlConnection.getInputStream();

	                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

	                StringBuffer sb  = new StringBuffer();

	                String line = "";
	                while( ( line = br.readLine())  != null){
	                        sb.append(line);
	                }
	                
	                data = sb.toString();

	                br.close();

	        }catch(Exception e){
	                Log.d("Exception while downloading url", e.toString());
	        }finally{
	                iStream.close();
	                urlConnection.disconnect();
	        }
	        return data;
	     }	
		
		// Fetches all places from GooglePlaces AutoComplete Web Service
		public class PlacesTask extends AsyncTask<String, Void, String>{

			@Override
			protected String doInBackground(String... place) {
				// For storing data from web service
				
			
				String data = "";
				
				// Obtain browser key from https://code.google.com/apis/console
				String key = "key=AIzaSyA5ayiCLO5L2tQktuPsj_ZX1N3TMtumVAc";
				
				String input="";
				try {
					input = "input=" + URLEncoder.encode(place[0], "utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}		
				
				
				// place type to be searched
				String types = "types=geocode";
				
				// Sensor enabled
				String sensor = "sensor=false";			
				
				// Building the parameters to the web service
				String parameters = input+"&"+types+"&"+sensor+"&"+key;
				
				// Output format
				String output = "json";
				
				// Building the url to the web service
				String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;
		
				try{
					// Fetching the data from web service in background
				     if (isOnline(getActivity())) {
					data = downloadUrl(url);
				     }
				     else
				     {
				    		Toast.makeText(getActivity(), "Check your internet connection",Toast.LENGTH_SHORT).show();
				     }
				}catch(Exception e){
	                Log.d("Background Task",e.toString());
				}
			    
			     
			     
				//System.out.println("The Data is"+data);
				return data;	
			     
			}
			
			@Override
			protected void onPostExecute(String result) {			
				super.onPostExecute(result);
				
				// Creating ParserTask
				
				   if(isCancelled() || !isAdded()) {
			            //Fragment is not in a state to meaningfully use the Main thread
			            return;
			        }
					
				parserTask = new ParserTask();
				   if (isOnline(getActivity())) {
					   parserTask.execute(result);
					     }
					     else
					     {
					    		Toast.makeText(getActivity(), "Check your internet connection",Toast.LENGTH_SHORT).show();
					     }
				// Starting Parsing the JSON string returned by Web Service
				
			}		
		}
		
		
		//** A class to parse the Google Places in JSON format *//*
	    public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

	    	JSONObject jObject;
	    	
			@Override
			protected List<HashMap<String, String>> doInBackground(String... jsonData) {			
				
				List<HashMap<String, String>> places = null;
				
	            PlaceJSONParser placeJsonParser = new PlaceJSONParser();
	            
	            try{
	            	jObject = new JSONObject(jsonData[0]);
	            	
	            	// Getting the parsed data as a List construct
	            	places = placeJsonParser.parse(jObject);

	            }catch(Exception e){
	            	Log.d("Exception",e.toString());
	            }
				//System.out.println("The Places is"+places);

	            return places;
			}
			
			@Override
			protected void onPostExecute(List<HashMap<String, String>> result) {			
				
					String[] from = new String[] { "description"};
					int[] to = new int[] { android.R.id.text1 };
			
					
					if(atvPlaces == null) {
					    Log.e("MyApp", "autoComplete is null");
					}
					//Context context=null;
					// Creating a SimpleAdapter for the AutoCompleteTextView			
					  adapter = new SimpleAdapter(getActivity().getBaseContext(),result, android.R.layout.simple_list_item_1, from, to);				
					//SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, R.id.listView1, from, to);				

					// Setting the adapter
					
					atvPlaces.setAdapter(adapter);
					 
					 atvPlaces.setOnItemClickListener(new OnItemClickListener() {

				            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				            	 Log.i("SELECTED TEXT WAS------->", (String) adapter.getItem(arg2));
				            }
				        });
		            	
					// SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.autocomplete_layout, from, to);
					 
				        // Getting a reference to CustomAutoCompleteTextView of activity_main.xml layout file
				       // CustomAutoCompleteTextView autoComplete = ( CustomAutoCompleteTextView) findViewById(R.id.autocomplete);
				 
					 OnItemClickListener itemClickListener = new OnItemClickListener() {
				            @Override
				            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				 
				              
				                @SuppressWarnings("unchecked")
								HashMap<String, String> hm = (HashMap<String, String>) arg0.getAdapter().getItem(position);
				 
				               
				                System.out.println("Selected ITem In the List is"+hm.get("description"));
				                //System.out.println("Selected ITem In the List is"+atvPlaces.getText().toString());
				                String a=atvPlaces.getText().toString();
				                 String[] at = a.split(",");
				                 String city=at[0];
				                 //String state=at[1];
				                // String country=at[2];
				                
				                
				                
				                location=hm.get("description");
				                Intent can=new Intent(getActivity(),Search_result.class);
				                can.putExtra("location",city);
				            	can.putExtra("room_id",roomid);
								can.putExtra("userid",userid);
				                //can.putExtra("state", state);
				                //can.putExtra("country",country);
				                System.out.println("city"+city);
				              //  System.out.println("state"+state);
				               // System.out.print("country"+country);
				         		
				         		startActivity(can);
				         		getActivity().finish();
				        
				                
				            }
				        };
				        atvPlaces.setOnItemClickListener(itemClickListener);
				        
				        
				        atvPlaces.setAdapter(adapter);
				    
				        adapter.notifyDataSetChanged();
         
			
			
		    }
	    }
	   /* private void showHideKeyboard(boolean showHide) {
	        if (showHide) {
	            InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
	                    .getSystemService(Context.INPUT_METHOD_SERVICE);
	            atvPlaces.requestLayout();

	            if (inputMethodManager != null) {
	                inputMethodManager.toggleSoftInputFromWindow(getActivity()
	                        .getCurrentFocus().getApplicationWindowToken(),
	                        InputMethodManager.SHOW_FORCED, 0);
	            }

	        } else {
	            InputMethodManager imm = (InputMethodManager) getActivity()
	                    .getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(atvPlaces.getWindowToken(), 0);

	        }
}*/
	
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
