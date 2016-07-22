package com.cog.DropInn;


import java.io.BufferedReader;
import java.io.FileOutputStream;
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
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN) public class Googlemap extends Activity implements LocationListener,ConnectionCallbacks, OnConnectionFailedListener 
{
//GooglePlayServicesClient.ConnectionCallbacks,
//GooglePlayServicesClient.OnConnectionFailedListener {

	String location;
	PlacesTask placesTask;
	ParserTask parserTask;
	 private LocationRequest mLocationRequest;
	    GoogleMap mGoogleMap;
	    LinearLayout search;
	    LinearLayout search1;
	    LinearLayout requestlin;
	    LinearLayout action;
	    LinearLayout car;   
	    String prop_id,prop_type1;
	  public static  String localityString,city,region_code,address,zipcode;
	   Button slideback,request;
	   ImageView add,gpsmove;   
	   ImageButton back1;
	   int n;
	    public static String ShopLat;
	    String prop_type;
	    public static String ShopPlaceId;
	    public static String ShopLong;
	  public static  Address returnAddress;
	    // Stores the current  instantiation of the location client in this object
	   // private LocationClient mLocationClient;
	  GoogleApiClient googleApiClient=null;
	    boolean mUpdatesRequested = false;
	    private TextView markerText;
	    private LatLng center;
	    private LinearLayout markerLayout;
	    private Geocoder geocoder;
	    private List<Address> addresses;
	    public TextView Address;
	    AutoCompleteTextView atvPlaces;
	    TextView title;
	    String title1,userid,room_type,flat,house,bedroom,Address1;
	    Button back,next;
	   private String addresses1;
		ImageView clear;
		RelativeLayout screen;
		String screeny;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_googlemap);
		getActionBar().hide();
			
		//buildAlertMessageNoGps();
        System.out.println("city main"+city);
        System.out.println("locality main"+localityString);
        System.out.println("region main"+region_code);
        System.out.println("address of google main page  main"+addresses1);
        
        
		Intent i1=getIntent();	
		//userid=i1.getStringExtra("userid");
		room_type=i1.getStringExtra("room_type");
		prop_type=i1.getStringExtra("prop_type");
		title1=i1.getStringExtra("title");		
		prop_id=i1.getStringExtra("propertyid");
				
		System.out.println("googlemap page room_type"+room_type);
		//System.out.println("googlemap page flat"+flat);
		//System.out.println("googlemap page house"+house);
		//System.out.println("googlemap page bedroom"+bedroom);
		System.out.println("googlemap page prop_type"+prop_type);	
		System.out.println("googlemap page userid"+userid);		
		System.out.println("title1"+title1);		
		
		next=(Button)findViewById(R.id.imageView3);
		back=(Button)findViewById(R.id.imageView2);
		back1=(ImageButton)findViewById(R.id.imageButton1);
		//title= (TextView) findViewById(R.id.TextView2);
		markerText = (TextView) findViewById(R.id.locationMarkertext);
		//Address = (TextView) findViewById(R.id.adressText);
		//Address1=Address.getText().toString();
        //Address1= (TextView) findViewById(R.id.adressText1);
        markerLayout = (LinearLayout) findViewById(R.id.locationMarker);
        
        SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
        
        //title.setText(prop_type);
        //*********************BACK*************************************
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back1=new Intent(Googlemap.this,Propertytype.class);
				back1.putExtra("userid", userid);
				back1.putExtra("room_type", room_type);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Googlemap.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(back1,bndlanimation);
				finish();
			}
		});
        
        clear=(ImageView)findViewById(R.id.clear);	
		clear.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				atvPlaces.setText("");
			}
		});
		
        if(location==null)
     	{
        	System.out.println("Location"+location);
     	location="test";
     	
		
		atvPlaces = (AutoCompleteTextView) findViewById(R.id.atv_places);
		atvPlaces.setThreshold(1);		
			
		
		atvPlaces.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {				
				placesTask = new PlacesTask();				
				placesTask.execute(s.toString());
				
				}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				// TODO Auto-generated method stub
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				

				// TODO Auto-generated method stub				
			}
			

		});	
	    back1.setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent back1=new Intent(Googlemap.this,Propertytype.class);
					back1.putExtra("userid", userid);
					back1.putExtra("room_type", room_type);
					back1.putExtra("title", title1);
					startActivity(back1);
					finish();
				}
			});
    	
     // **************************  Google Map Code Start **********************
     	
		
     	
     	        // Getting Google Play availability status
     	        int status = GooglePlayServicesUtil
     	                .isGooglePlayServicesAvailable(getBaseContext());

     	        if (status != ConnectionResult.SUCCESS) { // Google Play Services are
     	                                                    // not available

     	            int requestCode = 10;
     	            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
     	                    requestCode);
     	            dialog.show();

     	        } else { // Google Play Services are available

     	            // Getting reference to the SupportMapFragment
     	            // Create a new global location parameters object
     	            mLocationRequest = LocationRequest.create();

     	            /*
     	             * Set the update interval
     	             */
     	            mLocationRequest.setInterval(GData.UPDATE_INTERVAL_IN_MILLISECONDS);

     	            // Use high accuracy
     	            mLocationRequest
     	                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

     	            // Set the interval ceiling to one minute
     	            mLocationRequest
     	                    .setFastestInterval(GData.FAST_INTERVAL_CEILING_IN_MILLISECONDS);

     	            // Note that location updates are off until the user turns them on
     	            mUpdatesRequested = false;

     	            /*
     	             * Create a new location client, using the enclosing class to handle
     	             * callbacks.
     	             */
     	          /*  mLocationClient = new LocationClient(this, this, this);
     	            mLocationClient.connect();*/
     	           googleApiClient = new GoogleApiClient.Builder(this)
                   .addApi(LocationServices.API)
                   .addConnectionCallbacks(this)
                   .addOnConnectionFailedListener(this)
                   .build();
                   
                   googleApiClient.connect();
     	        }	
     	
     	}
     	
     // **************************  Google Map Code End **********************	
        next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 
				captureMapScreen();
				}
	
        });
}
		
	


	
	// **************************  Google Map Function Start **********************
	
		 @SuppressLint("NewApi")
		private void stupMap() {
		        try {
		        	
		            LatLng latLong;
		            // TODO Auto-generated method stub
		            mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(
		                    R.id.map)).getMap();
		            
		          /*  mGoogleMap.addMarker(new MarkerOptions()
	                .position(new LatLng(37.7750, 122.4183))
	                .title("San Francisco").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));*/
		          
		            mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(9.925201, 78.119775))
                 
                    .icon(BitmapDescriptorFactory
                     .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

		           /// Zoom Control Position
		            View zoomControls = ((MapFragment) getFragmentManager().findFragmentById(
		                    R.id.map)).getView().findViewById(0x1);
		            
		            if (zoomControls != null && zoomControls.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
		                // ZoomControl is inside of RelativeLayout
		                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) zoomControls.getLayoutParams();

		                // Align it to - parent top|left
		                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		                // Update margins, set to 10dp
		                final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
		                        getResources().getDisplayMetrics());
		                params.setMargins(margin+450, margin+500, margin, margin);
      }
		            
		            
		            
		          /*  Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
		            .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
		            .width(5)
		            .color(Color.RED));*/
		            
		            
		            
		            // Enabling MyLocation in Google Map
		            mGoogleMap.setMyLocationEnabled(true);
		            
		         
		            
		            if (location=="test") {
		            	
		            	//Toast.makeText(getApplicationContext(), "Latitude"+mLocationClient.getLastLocation().getLatitude()+"Longitude"+mLocationClient.getLastLocation().getLongitude(),Toast.LENGTH_SHORT).show();

		            		            	
		            		             /*   latLong = new LatLng(mLocationClient.getLastLocation()
		            		                        .getLatitude(), mLocationClient.getLastLocation()
		            		                        .getLongitude());
		            		                ShopLat = mLocationClient.getLastLocation().getLatitude() + "";
		            		                ShopLong = mLocationClient.getLastLocation().getLongitude()+ "";*/
		            	Location gpslocation=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
	                    latLong = new LatLng(gpslocation.getLatitude(),gpslocation.getLongitude());
		            		                
		            		               
		            		            }
		            else if(location!="test"){
		            	System.out.println("insite the if");

		            	latLong=getLocationFromAddress(location);
		            	 

				            mGoogleMap.addMarker(new MarkerOptions()
		                            .position(latLong)
		                         
		                            .icon(BitmapDescriptorFactory
		                             .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
				            
				            Circle circle = mGoogleMap.addCircle(new CircleOptions()
				            .center(latLong)
				            .radius(1500)
				            .strokeColor(Color.WHITE)
				            .fillColor(Color.parseColor("#CC7997a1")));
				            
				          
			        	 
		            }
			        	  else {
		            		                latLong = new LatLng(12.9667, 77.5667);
		            		               // System.out.println("Inside Else Latitude and Longitude"+latLong);

		            		            }
		            		            CameraPosition cameraPosition = new CameraPosition.Builder()
		            		                    .target(latLong).zoom(12f).tilt(0).build();

		            		            mGoogleMap.setMyLocationEnabled(true);
		            		           mGoogleMap.animateCamera(CameraUpdateFactory
		            		                    .newCameraPosition(cameraPosition));
		            		           // mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLong(location.getLatitude(), location.getLongitude()), 12.0f));
		            		            
		            		            // Clears all the existing markers
		            		           // mGoogleMap.clear();

		            		            mGoogleMap.setOnCameraChangeListener(new OnCameraChangeListener() {

		            		                @Override
		            		                public void onCameraChange(CameraPosition arg0) {
		            		                    // TODO Auto-generated method stub
		            		                    center = mGoogleMap.getCameraPosition().target;

		            		                   // mGoogleMap.clear();
		            		                   // markerLayout.setVisibility(View.VISIBLE);
		            		                  
		            		                    try {
		            		                        new GetLocationAsync(center.latitude, center.longitude)
		            		                                .execute();
		            		                        
		            		    	               // System.out.println("Center Latitude and Center Longitude"+center.latitude+"long"+center.longitude);

		            		                    } catch (Exception e) {
		            		                    }
		            		                }
		            		            });
		        
		 

		           // markerLayout.setOnClickListener(new OnClickListener() {
		           /* markerText.setOnClickListener(new OnClickListener() {
		                @Override
		                public void onClick(View v) {
		                    // TODO Auto-generated method stub

		                    try {
		                    	Intent searchAddress = new  Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q="+location));
		                    	startActivity(searchAddress);

	                      LatLng latLng1 = new LatLng(center.latitude,
		                                center.longitude);

		                        Marker m = mGoogleMap.addMarker(new MarkerOptions()
		                                .position(latLng1));
		                                .title(" Set your Location ")
		                                .snippet("")
		                                .icon(BitmapDescriptorFactory
		                                        .fromResource(R.drawable.redpin)));
		                        m.setDraggable(true);

		                        markerLayout.setVisibility(View.GONE);
		                    	
		                    
		                    } catch (Exception e) {
		                    }

		                }
		            });
*/
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    public void onLocationChanged(Location location) {
		        // TODO Auto-generated method stub

		    	
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {
		        // TODO Auto-generated method stub

		    }

		    public void onProviderEnabled(String provider) {
		        // TODO Auto-generated method stub

		    }

		    public void onProviderDisabled(String provider) {
		        // TODO Auto-generated method stub

		    }

		    public void onConnectionFailed(ConnectionResult arg0) {
		        // TODO Auto-generated method stub

		    }

		    public void onConnected(Bundle arg0) {
		        // TODO Auto-generated method stub
		        stupMap();
		        //Driverdetails();
		        
		    }

		    
		    public void onDisconnected() {
		        // TODO Auto-generated method stub

		    }

		  public class GetLocationAsync extends AsyncTask<String, Void, String> {

		    	
				// boolean duplicateResponse;
		        double x, y;
		        StringBuilder str;

		        public GetLocationAsync(double latitude, double longitude) {
		            // TODO Auto-generated constructor stub

		            x = latitude;
		            y = longitude;
		           
		        }
		      
		        public GetLocationAsync() {
					// TODO Auto-generated constructor stub
				}

				@Override
		        protected void onPreExecute() {
					if(location!=null){
						
					}
					else
					{
					atvPlaces.setText(" Getting location ");
					}
					
		       }

		        @SuppressLint("NewApi")
		@Override
		        protected String doInBackground(String... params) {

		            try {
		                geocoder = new Geocoder(Googlemap.this, Locale.ENGLISH);
		                addresses = geocoder.getFromLocation(x, y, 1);
		                //System.out.println("GEO CODER Addresses"+addresses);
		                str = new StringBuilder();
		               // System.out.println("GEO CODER Str"+str);
		                if (Geocoder.isPresent()) 
		                {
		                	//if(addresses!= null)
		                	if(addresses.size() > 0)
		                	{
		                     returnAddress = addresses.get(0);
		                     localityString = returnAddress.getLocality();
		                    city = returnAddress.getCountryName();
		                     region_code = returnAddress.getCountryCode();
		                    zipcode = returnAddress.getPostalCode();
		                    if(location!=null)
		                    {
		                    	addresses1=location;	
		                    }
		                    else
		                    {
		                    addresses1=addresses.get(0).getAddressLine(0)
		                            + addresses.get(0).getAddressLine(1) + " ";
		                    }
		                    System.out.println("returnAddress====="+returnAddress);
		                    System.out.println("city"+city);
		                    System.out.println("locality"+localityString);
		                    System.out.println("region"+region_code);
		                    System.out.println("address of inside"+addresses1);
		                    

		                    str.append(localityString + "");
		                    str.append(city + "" + region_code + "");
		                    str.append(zipcode + "");

		                }
		                	else
		                	{
		                	}
		                }else {
		                }
		            } catch (IOException e) {
		                Log.e("tag", e.getMessage());
		            }
		            return null;

		        }

		        @Override
		        protected void onPostExecute(String result) {
		            try {
		            	
		            	if(location!=null)
		            	{
		            		
		            	}
		            	else
		            	
		            	atvPlaces.setText(addresses.get(0).getAddressLine(0)
		                        + addresses.get(0).getAddressLine(1) + " ");
		            	
		              
		                
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }

		        @Override
		        protected void onProgressUpdate(Void... values) {

		        }
		    }
		
		    public LatLng getLocationFromAddress(String strAddress) {

		        Geocoder coder = new Geocoder(Googlemap.this);
		        List<Address> address;
		        LatLng p1 = null;

		        try {
		            address = coder.getFromLocationName(strAddress, 5);
		            if (address == null) {
		                return null;
		            }
		            Address location = address.get(0);
		            location.getLatitude();
		            location.getLongitude();

		            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

		        } catch (Exception ex) {

		            ex.printStackTrace();
		        }

		        return p1;
		    }
		    private void buildAlertMessageNoGps() {
			    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			    builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
			            .setCancelable(false)
			            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
			                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
			                }
			            })
			            .setNegativeButton("No", new DialogInterface.OnClickListener() {
			                public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
			                    dialog.cancel();
			                }
			            });
			    final AlertDialog alert = builder.create();
			    alert.show();
			}
			// **************************  Google Map  Function End **********************
		    private String downloadUrl(String strUrl) throws IOException{
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
		    private class PlacesTask extends AsyncTask<String, Void, String>{

				@Override
				protected String doInBackground(String... place) {
					// For storing data from web service
					String data = "";
					
					// Obtain browser key from https://code.google.com/apis/console
					/*String key = "key=AIzaSyA5ayiCLO5L2tQktuPsj_ZX1N3TMtumVAc";*/
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
						data = downloadUrl(url);
					}catch(Exception e){
		                Log.d("Background Task",e.toString());
					}
					//System.out.println("The Data is"+data);
					return data;		
				}
				protected void onPostExecute(String result) {			
					super.onPostExecute(result);
					
					// Creating ParserTask
					parserTask = new ParserTask();
					
					// Starting Parsing the JSON string returned by Web Service
					if (isOnline(Googlemap.this)) {
					parserTask.execute(result);
					 
					}
				     else
				     {
				    		Toast.makeText(getApplicationContext(), "Check your internet connection",Toast.LENGTH_SHORT).show();
				    		
				     }
				}		
			}
		    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

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
				protected void onPostExecute(List<HashMap<String, String>> result) {			
					
					String[] from = new String[] { "description"};
					int[] to = new int[] { android.R.id.text1 };
					
					// Creating a SimpleAdapter for the AutoCompleteTextView			
					final SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, android.R.layout.simple_list_item_1, from, to);				
					//SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, R.id.listView1, from, to);				

					// Setting the adapter
					
					//atvPlaces.setAdapter(adapter);
					 
					/* atvPlaces.setOnItemClickListener(new OnItemClickListener() {

				            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				            	 Log.i("SELECTED TEXT WAS------->", adapter.getItem(arg2));
				            }
				        });
		            	System.out.println("The End Selected Item in the List is "+CorrectPlace);*/
					// SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.autocomplete_layout, from, to);
					 
				        // Getting a reference to CustomAutoCompleteTextView of activity_main.xml layout file
				       // CustomAutoCompleteTextView autoComplete = ( CustomAutoCompleteTextView) findViewById(R.id.autocomplete);
				 
					 OnItemClickListener itemClickListener = new OnItemClickListener() {
						 
						 
				            @Override
				            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				 
				                /** Each item in the adapter is a HashMap object.
				                *  So this statement creates the currently clicked hashmap object
				                * */
				            	hide_keyboard(Googlemap.this);
				                @SuppressWarnings("unchecked")
								HashMap<String, String> hm = (HashMap<String, String>) arg0.getAdapter().getItem(position);
				 
				                /** Getting a reference to the TextView of the layout file activity_main to set Currency */
				             
				 
				                /** Getting currency from the HashMap and setting it to the TextView */
				                //atvPlaces.setText(hm.get("description"));
				                
				                System.out.println("Selected ITem In the List is"+hm.get("description"));
				                System.out.println("Selected ITem In the List is"+atvPlaces.getText().toString());
				                location=hm.get("description");
				                mGoogleMap.clear();
				                stupMap();
				              
				                
				            }
				        };
				        atvPlaces.setOnItemClickListener(itemClickListener);
				        
				        
				        atvPlaces.setAdapter(adapter);
				        //sri
				        adapter.notifyDataSetChanged();
				        /** Setting the adapter to the listView */
				      //  autoComplete.setAdapter(adapter);

					//atvPlaces.setAdapter(adapter);
			}			
		    }
		
		
		// **************************  Google Map  Function End **********************
		    @SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
		    public void onBackPressed()
		    {
		    	Intent back1=new Intent(Googlemap.this,Propertytype.class);
				back1.putExtra("userid", userid);
				back1.putExtra("room_type", room_type);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Googlemap.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(back1,bndlanimation);
				finish();
		    	
		    }


			@Override
			public void onConnectionSuspended(int arg0) {
				// TODO Auto-generated method stub
				
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
			@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.JELLY_BEAN) public void captureMapScreen() {
		        SnapshotReadyCallback callback = new SnapshotReadyCallback() {

		            @Override
		            public void onSnapshotReady(Bitmap snapshot) {
		                try {
		                	/*screen=(RelativeLayout)findViewById(R.id.screen);
		                	screen.setDrawingCacheEnabled(true);
		                    Bitmap backBitmap = screen.getDrawingCache();
		                    Bitmap bmOverlay = Bitmap.createBitmap(
		                            backBitmap.getWidth(), backBitmap.getHeight(),
		                            backBitmap.getConfig());
		                    Canvas canvas = new Canvas(bmOverlay);
		                    canvas.drawBitmap(snapshot, new Matrix(), null);
		                    canvas.drawBitmap(backBitmap, 0, 0, null);*/
		                	 screeny= Environment.getExternalStorageDirectory().toString()
			                            + "/MapScreenShot" + System.currentTimeMillis() + ".png";
		                    FileOutputStream out = new FileOutputStream(
		                            Environment.getExternalStorageDirectory()
		                                    + "/MapScreenShot"
		                                    + System.currentTimeMillis() + ".png");
		                    
                           System.out.println("screeeeeeeeeeeeeenshot" +screeny);
                           snapshot.compress(Bitmap.CompressFormat.PNG, 90, out);
                           out.flush();
                           out.close();
                           
                           if (isOnline(Googlemap.this)) {
           					
           					
           			
           				if(addresses1==null||addresses1.matches("test"))
           				{
           					addresses1="Madurai,Tamil Nadu,India";
           				}
           				else
           				{
           				 System.out.println("address in googlepage"+addresses1);
           				 System.out.println("city in googlepage"+city);
                            System.out.println("locality in googlepage"+localityString);
                            System.out.println("region in googlepage"+region_code);
           				Intent back=new Intent(Googlemap.this,Rooms_beds.class);
           				back.putExtra("userid", userid);
           				back.putExtra("room_type", room_type);
           				back.putExtra("map", screeny);
           				System.out.println("screeeeeeeeeeeeeensho" +screeny);
           				back.putExtra("propertyid", prop_id);
           				back.putExtra("prop_type", prop_type);
           				back.putExtra("city", localityString);
           				back.putExtra("state", city);
           				back.putExtra("address", addresses1);
           				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Googlemap.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
           				startActivity(back,bndlanimation);
           			
           				finish();
           			
           				}
           			}
           			     else
           			     {
           			    		Toast.makeText(getApplicationContext(), "Check your internet connection",Toast.LENGTH_SHORT).show();
           			    		
           			     }
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        };

		        mGoogleMap.snapshot(callback);
		        


		    }
			public static void hide_keyboard(Activity activity) {
			    InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
			    //Find the currently focused view, so we can grab the correct window token from it.
			    View view = activity.getCurrentFocus();
			    //If no view currently has focus, create a new one, just so we can grab a window token from it
			    if(view == null) {
			        view = new View(activity);
			    }
			    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
}