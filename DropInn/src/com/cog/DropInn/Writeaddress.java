package com.cog.DropInn;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

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
import android.graphics.BitmapFactory;
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
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Writeaddress extends Activity implements LocationListener,ConnectionCallbacks, OnConnectionFailedListener 
{
/*GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {*/


String first,second,third,fourth,last;
	
	String test,im;
	 private LocationRequest mLocationRequest;
	    GoogleMap mGoogleMap;
	    LinearLayout search;
	    LinearLayout search1;
	    LinearLayout requestlin;
	    //LinearLayout action;
	    //LinearLayout car;  
	   private String addresses1;
	   String getcity,getstate,getcountry;
	 String stry,ad,country1,city1,state1;
	    public static  String city,country,state,region_code,address,zipcode;
	   Button slideback,request;
	   ImageView add,gpsmove;   
	   int n;
	    public static String ShopLat;
	    public static String ShopPlaceId;
	    public static String ShopLong;
	    GoogleApiClient googleApiClient=null;
	    String step,check;
	    // Stores the current  instantiation of the location client in this object
	   // private LocationClient mLocationClient;
	    boolean mUpdatesRequested = false;
	    private TextView markerText;
	    private LatLng center;
	    private LinearLayout markerLayout;
	    private Geocoder geocoder;
	    private List<Address> addresses;
	    private TextView Address,Address1;
	    URL Login_Url,Login_Url1;
	   private JSONArray login_jsonarray,login_jsonarray1;
	       private JSONObject login_jsonobj,login_jsonobj1;
	       private String login_status,login_status1;
	       private String login_userid;
	       private String login_inputline,login_inputline1;
	    TextView title;
	    String price,tit,sum;
	    ImageView back,clear;
	    AutoCompleteTextView atvPlaces;
	    Button next;
	    private String imagepath;
	    
	    String image1,resize;
	 //String picturePath;
	
	//ListView lv=(ListView)findViewById(R.id.listView1);
	PlacesTask placesTask;
	ParserTask parserTask;
	String location,room_id;
	String userid;
	String Liveurl="";
	
	//String splitcountry,splitstate,splitcountry,splitstreet;
	String[] split;
	
	double latitude1;
    double longitude1;
    String latitude11,longitude11;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_writeaddress);
	getActionBar().hide();
	
	if( Build.VERSION.SDK_INT >= 9){
         StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
         StrictMode.setThreadPolicy(policy);
  }
	
	 SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
     Liveurl = sharedPreferences1.getString("liveurl", null);
     
	 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
	 userid = sharedPreferences2.getString("userid", null);
	
	Intent i=getIntent();	
	//userid=i.getStringExtra("userid");
	im=i.getStringExtra("image");
	
	image1=i.getStringExtra("image1");
	resize=i.getStringExtra("resize");
	tit=i.getStringExtra("title");
	sum=i.getStringExtra("summary");
	price=i.getStringExtra("price");
	ad=i.getStringExtra("addresss");
	city1=i.getStringExtra("city");
	state1=i.getStringExtra("state");
	country1=i.getStringExtra("country");
	step=i.getStringExtra("step");
	check=i.getStringExtra("check");
	addresses1=ad;
	getcity=city1;
	getstate=state1;
	getcountry=country1;
	room_id=i.getStringExtra("room_id");
	
	System.out.println("address page"+price );
	
	first=i.getStringExtra("first");
	second=i.getStringExtra("second");
	third=i.getStringExtra("third");
	fourth=i.getStringExtra("fourth");
	last=i.getStringExtra("last");
	
	//String latitude1,longitude1;

	latitude11=i.getStringExtra("latitude");
	longitude11=i.getStringExtra("longitude");
	

	
	/*optional.putExtra("latitude", latitude1);
	optional.putExtra("longitude",longitude1);*/
	  System.out.println("country main"+country);
	    
	        System.out.println("address of i main"+addresses1);
	
	next=(Button)findViewById(R.id.next);
	//back=(ImageView)findViewById(R.id.imageView2);
	//title= (TextView) findViewById(R.id.TextView1);
	//markerText = (TextView) findViewById(R.id.locationMarkertext);
	//Address = (TextView) findViewById(R.id.adressText);
        //Address1= (TextView) findViewById(R.id.adressText1);
        markerLayout = (LinearLayout) findViewById(R.id.locationMarker);        
        //title.setText(title1);
        //*********************BACK*************************************
        /*back.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
	// TODO Auto-generated method stub
	Intent back=new Intent(Writeaddress.this,Propertytype.class);
	startActivity(back);
	finish();
	}
	});*/
        clear=(ImageView)findViewById(R.id.clear);	
	clear.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
	atvPlaces.setText("");
	}
	});
        if(location==null)
     	{System.out.println("Location"+location);
     	location="test";
     	}
	next.setOnClickListener(new View.OnClickListener() {
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onClick(View v) {
	// TODO Auto-generated method stub
	
	if(getcity==null||getcountry==null||addresses1.equals("test"))
	{
	atvPlaces.setError("Enter valid City,State and Country");
	}
	else if(getstate==null)
	{
		getstate=getcity;
		System.out.println("inside state null state"+getstate);
	}
	
	else
	{
	Imageuploading();
	Intent back=new Intent(Writeaddress.this,List_your_space.class);
	Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writeaddress.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
	back.putExtra("address",addresses1);
	back.putExtra("city", getcity);
	back.putExtra("state", getstate);
	back.putExtra("country", getcountry);
	back.putExtra("image",im);
	back.putExtra("image1",image1);
	back.putExtra("resize",resize);
	back.putExtra("write_price", price);
	back.putExtra("write_title", tit);
	back.putExtra("write_summary", sum);
	back.putExtra("room_id", room_id);
	back.putExtra("userid",userid);	
	back.putExtra("first", first);
	back.putExtra("second", second);
	back.putExtra("third", third);
	back.putExtra("fourth", fourth);
	back.putExtra("last", last);
	latitude11=String.valueOf(latitude1);
	longitude11=String.valueOf(longitude1);
	back.putExtra("latitude", latitude11);
	back.putExtra("longitude",longitude11);
	back.putExtra("step",step);
	back.putExtra("check",check);
	startActivity(back,bndlanimation);
	finish();
	}
	}
	});
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
	
	/*String CorrectPlace=atvPlaces.getText().toString();
	System.out.println("Selected Text"+CorrectPlace);*/
	//ListView list = (ListView) findViewById(R.id.listview);
	
	
	
	/* CorrectPlace=atvPlaces.getText().toString();
	System.out.println("Selected Text"+CorrectPlace); 
	
	String Selectionend=atvPlaces.getEditableText().toString();
	System.out.println("getEditableText"+Selectionend); 
	
	String Selectionend1=atvPlaces.getItemClickListener().toString();
	System.out.println("getItemClickListener"+Selectionend1); 
	
	String Selectionend2=atvPlaces.getItemSelectedListener().toString();
	System.out.println("getIemSelectedListener"+Selectionend2); 
*/
	
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
     	           /* mLocationClient = new LocationClient(this, this, this);
     	            mLocationClient.connect();*/
     	            
     	           googleApiClient = new GoogleApiClient.Builder(this)
                   .addApi(LocationServices.API)
                   .addConnectionCallbacks(this)
                   .addOnConnectionFailedListener(this)
                   .build();
                   
                   googleApiClient.connect();
     	            
     	            
     	        }	
     	
	 	
     	
     // **************************  Google Map Code End **********************	
     	
     	
	
	}

	
	// **************************  Google Map Function Start **********************
	
	 @SuppressLint("NewApi")
	private void stupMap() {
	        try {
	            LatLng latLong;
	            // TODO Auto-generated method stub
	            mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(
	                    R.id.map)).getMap();
	             
	           
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
	            
	            
	            
	           /* Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
	            .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
	            .width(5)
	            .color(Color.RED));
	            */
	            
	            
	            // Enabling MyLocation in Google Map
	            mGoogleMap.setMyLocationEnabled(true);
	            
	            
	         System.out.println("Location before call "+location);
	            
	            /*if (mLocationClient.getLastLocation() != null) {*/
	            if (location=="test") {
	            	//Toast.makeText(getApplicationContext(), "Latitude"+mLocationClient.getLastLocation().getLatitude()+"Longitude"+mLocationClient.getLastLocation().getLongitude(),Toast.LENGTH_SHORT).show();

	            	/*
	            	                latLong = new LatLng(mLocationClient.getLastLocation()
	            	                        .getLatitude(), mLocationClient.getLastLocation()
	            	                        .getLongitude());
	            	                
	            	                ShopLat = mLocationClient.getLastLocation().getLatitude() + "";
	            	                ShopLong = mLocationClient.getLastLocation().getLongitude()+ "";*/
	            	                
	            	               /* mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
	        	   	        	    public void onMapLoaded() {
	        	   	        	    	
	        	   	        	    	System.out.println("inside snapshot");
	        	   	        	    	mGoogleMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
	        	   	        	            public void onSnapshotReady(Bitmap bitmap) {
	        	   	        	                // Write image to disk
	        	   	        	               try{
	        	   	        	            
	        	   	        	            	FileOutputStream out = new FileOutputStream("/mnt/sdcard/map.png");
	        	   	        	                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
	        	   	        	                
	        	   	        	               String picturePath="/mnt/sdcard/map.png";
	        	   	        	               imagepath =  ImageWrite(BitmapFactory.decodeFile(picturePath));
	        	   	     	         	//imagepath = ImageWrite(bitmap_profile_image);
	        	   	        	               bitmap=BitmapFactory.decodeFile(picturePath);
	        	   	        	               System.out.println("picturePath"+picturePath);
	        	   	        	               System.out.println("inside try"+out);
	        	   	        	            }
	        	   	        	            catch(Exception e)
	        	   	        	               {
	        	   	        	            	e.printStackTrace();
	        	   	        	              
	        	   	        	               }
	        	   	        	               }
	        	   	        	        });
	        	   	        	    }
	        	   	        	});*/
	               	Location gpslocation=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    latLong = new LatLng(gpslocation.getLatitude(),gpslocation.getLongitude());
	            	               
	            	            }
	            else if(location!="test"){
	            	
	            	System.out.println("insite the if");
	            	
	        	  latLong=getLocationFromAddress(location);
	        	  screenshot();
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
	            	            // Clears all the existing markers
	            	            mGoogleMap.clear();

	            	            mGoogleMap.setOnCameraChangeListener(new OnCameraChangeListener() {

	            	                @Override
	            	                public void onCameraChange(CameraPosition arg0) {
	            	                    // TODO Auto-generated method stub
	            	                    center = mGoogleMap.getCameraPosition().target;

	            	                    mGoogleMap.clear();
	            	                    markerLayout.setVisibility(View.VISIBLE);

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
	                                .position(latLng1)
	                                .title(" Set your Location ")
	                                .snippet("")
	                                .icon(BitmapDescriptorFactory
	                                        .fromResource(R.drawable.redpin)));
	                        m.setDraggable(true);

	                        markerLayout.setVisibility(View.GONE);
	                    	
	                    	getActionBar().hide();
	                    
	                    } catch (Exception e) {
	                    }

	                }
	            });*/

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
	        
   	         
	       	        
	    }

	    
	    public void onDisconnected() {
	        // TODO Auto-generated method stub

	    }

	    private class GetLocationAsync extends AsyncTask<String, Void, String> {

	        // boolean duplicateResponse;
	        double x, y;
	        StringBuilder str;

	        public GetLocationAsync(double latitude, double longitude) {
	            // TODO Auto-generated constructor stub

	            x = latitude;
	            y = longitude;
	           
	            latitude1=latitude;
                longitude1=longitude;
                 
	        }

	        @Override
	        protected void onPreExecute() {
	        	if(location!=null)
	        	{
	        	
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
	                geocoder = new Geocoder(Writeaddress.this, Locale.ENGLISH);
	                addresses = geocoder.getFromLocation(x, y, 1);
	                //System.out.println("GEO CODER Addresses"+addresses);
	                str = new StringBuilder();
	               // System.out.println("GEO CODER Str"+str);
	                if (Geocoder.isPresent()) 
	                {
	                	//if(addresses!= null)
	                	if(addresses.size() > 0)
	                	{
	                	
	                	
	                	 Address returnAddress = addresses.get(0);
	                     city = returnAddress.getLocality();
	                     state=returnAddress.getAdminArea();
	                     country = returnAddress.getCountryName();
	                     region_code = returnAddress.getCountryCode();
	                     zipcode = returnAddress.getPostalCode();
	                    
	                    if(location!=null)
	                    {
	                    //addresses1=city;
	                    	addresses1=location;
	                    	System.out.println("sri" +addresses1);
	                    }
	                    else
	                    {
	                    	System.out.println("inside else");
	                    	addresses1=addresses.get(0).getAddressLine(0)
	                            + addresses.get(0).getAddressLine(1) + " ";
	                    	 //addresses1=city+","+country.replaceAll(" ","+");
	                    	 
	                     	 
	                    }
	                   
                    	System.out.println("check split"+split);
	                    System.out.println("returnAddress==="+returnAddress);
	                    System.out.println("city=="+city);
	                    System.out.println("state==="+state);
	                    System.out.println("country=="+country);
	                    System.out.println("region==="+region_code); 
	                    System.out.println("address of inside==="+addresses1);
	                    getcity=city;
	                    getstate=state;
	                    getcountry=country;
	                    
	                    str.append(city + "");
	                    str.append(country + "" + region_code + "");
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
	            	if(location!=null){
	
	}
	else
	{
	atvPlaces.setText(addresses.get(0).getAddressLine(0)
	                        + addresses.get(0).getAddressLine(1) + " ");
	}
	            	
	                
	              
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            
	        
	            
	        }

	        @Override
	        protected void onProgressUpdate(Void... values) {

	        }
	    }
	
	    public LatLng getLocationFromAddress(String strAddress) {

	        Geocoder coder = new Geocoder(Writeaddress.this);
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
	public void screenshot()
	{
	 mGoogleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
	
	@Override
	public void onMyLocationChange(Location arg0) {
	// TODO Auto-generated method stub
	System.out.println("inside snapshot");
   	        	    	mGoogleMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
   	        	            public void onSnapshotReady(Bitmap bitmap) {
   	        	                // Write image to disk
   	        	               try{
   	        	            
   	        	            	FileOutputStream out = new FileOutputStream("/mnt/sdcard/map.png");
   	        	                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
   	        	                
   	        	             String  picturePath="/mnt/sdcard/map.png";
   	        	               imagepath =  ImageWrite(BitmapFactory.decodeFile(picturePath));
   	        	               
   	        	            
   	        	            }
   	        	            catch(Exception e)
   	        	               {
   	        	            	e.printStackTrace();
   	        	              
   	        	               }
   	        	               }
   	        	        });
	}
	}); 
	            
	
	
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
	parserTask.execute(result);
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
	            	
	                @SuppressWarnings("unchecked")
	HashMap<String, String> hm = (HashMap<String, String>) arg0.getAdapter().getItem(position);
	 
	                /** Getting a reference to the TextView of the layout file activity_main to set Currency */
	                
	 
	                /** Getting currency from the HashMap and setting it to the TextView */
	                //atvPlaces.setText(hm.get("description"));
	                
	                System.out.println("Selected ITem In the List is"+hm.get("description"));
	                System.out.println("Selected ITem In the List is"+atvPlaces.getText().toString());
	                location=hm.get("description");
	                stupMap();
	                
	                
	            }
	        };
	        atvPlaces.setOnItemClickListener(itemClickListener);
	        
	        
	        atvPlaces.setAdapter(adapter);
	        /** Setting the adapter to the listView */
	      //  autoComplete.setAdapter(adapter);
	        adapter.notifyDataSetChanged();
	//atvPlaces.setAdapter(adapter);
	}	
	    }
	
	    public String ImageWrite(Bitmap bitmap1)
	    {
	       
	         String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
	            OutputStream outStream = null;
	            File file = new File(extStorageDirectory, "slectimage.PNG");
	           
	            try
	            {
	       
	                outStream = new FileOutputStream(file);
	                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, outStream);
	                outStream.flush();
	                outStream.close();

	              

	            }
	            catch (FileNotFoundException e)
	            {
	                e.printStackTrace();
	               
	            } catch (IOException e)
	            {
	                e.printStackTrace();
	              
	            }
	            String imageInSD = "/sdcard/slectimage.PNG";       
	            return imageInSD;
	       
	    }
	
	    protected void Imageuploading() {
	// TODO Auto-generated method stub

	try {
	Log.e("image", "dfdf");

	HttpURLConnection connection = null;
	DataOutputStream outputStream = null;
	DataInputStream inputStream = null;

	String pathToOurFile = (String) imagepath;

	// String urlServer =
	// "http://demo.cogzideltemplates.com/client/snapchat-clone/index.php/user/image_upload";
	//String urlServer =  "mobile/user/map_upload?room_id="+room_id+"&user_id="+userid;
	String urlServer =  Liveurl+"user/map_upload?room_id="+room_id+"&user_id="+userid;
	String lineEnd = "\r\n";
	System.out.println("url"+urlServer);
	String twoHyphens = "--";
	String boundary = "*****";

	int bytesRead, bytesAvailable, bufferSize;
	byte[] buffer;
	int maxBufferSize = 1 * 1024 * 1024;
	FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile));

	URL url = new URL(urlServer);
	connection = (HttpURLConnection) url.openConnection();

	// Allow Inputs & Outputs
	connection.setDoInput(true);
	connection.setDoOutput(true);
	connection.setUseCaches(false);

	// Enable POST method
	connection.setRequestMethod("POST");

	connection.setRequestProperty("Connection", "Keep-Alive");
	connection.setRequestProperty("Content-Type",
	"multipart/form-data;boundary=" + boundary);

	outputStream = new DataOutputStream(connection.getOutputStream());
	outputStream.writeBytes(twoHyphens + boundary + lineEnd);
	outputStream
	.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
	+ pathToOurFile + "\"" + lineEnd);
	outputStream.writeBytes(lineEnd);

	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	buffer = new byte[bufferSize];

	// Read file
	bytesRead = fileInputStream.read(buffer, 0, bufferSize);

	while (bytesRead > 0) {
	outputStream.write(buffer, 0, bufferSize);
	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	}

	outputStream.writeBytes(lineEnd);
	outputStream.writeBytes(twoHyphens + boundary + twoHyphens
	+ lineEnd);
	// Responses from the server (code and message)
	int serverResponseCode = connection.getResponseCode();
	String serverResponseMessage = connection.getResponseMessage();

	// Toast.makeText(getApplicationContext(), serverResponseMessage,
	// Toast.LENGTH_LONG).show();
	System.out.println("image" + serverResponseMessage);

	fileInputStream.close();
	outputStream.flush();
	outputStream.close();

	DataInputStream inputStream1 = null;
	inputStream1 = new DataInputStream(connection.getInputStream());
	String str = "";
	String Str1_imageurl = "";

	while ((str = inputStream1.readLine()) != null) {
	Log.e("Debug", "Server Response " + str);

	Str1_imageurl = str;
	Log.e("Debug", "Server Response String imageurl" + str);
	}
	inputStream1.close();
	System.out.println("image url" + Str1_imageurl);
	// Toast.makeText(getApplicationContext(), Str1_imageurl,
	// Toast.LENGTH_LONG).show();

	stry = Str1_imageurl.trim();
	System.out.println("check stry"+stry);
	call_image();
	//msgToSend = stry;
	
	} catch (Exception e) {

	e.printStackTrace();

	}

	//script();

	}
	    public void call_image()
	{
	  try {
	        	
	        	Login_Url= new URL(Liveurl+"user/return_map?room_id="+room_id+"&user_id="+userid+"&map="+stry);
	        	//http://demo.cogzideltemplates.com/client/gottospot_android/mobile/user/return_map?room_id=121&user_id=12&map=mm
	            //Login_Url= new URL(Liveurl+"mobile/signup_second?user_id="+h_id+"&email="+str1+"&password="+str2+"&phone_number="+str3);
	           System.out.println(""+Login_Url);
	           
	           BufferedReader login_reader;
	            String str_login="";

	            login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
	           
	            String s=Login_Url.toString();
	           
	            while ((login_inputline = login_reader.readLine())!= null)
	            {
	               
	                str_login += login_inputline;
	                              
	            }
	           

	            System.out.print("logo"+str_login);
	            
	            
	         /*toast=   Toast.makeText(getApplicationContext(), "Email Already Taken", Toast.LENGTH_LONG);
	         toast.setGravity(Gravity.CENTER, 0, 0);
	         toast.show();*/
	           
	           
	            login_jsonarray = new JSONArray(str_login);        
	                   
	             
	             for(int i=0; i <login_jsonarray.length(); i++)
	                {
	                                  
	            login_jsonobj = login_jsonarray.getJSONObject(i);
	            login_status =    login_jsonobj.getString("status");
	                           
	                }

	      System.out.println("after json"+login_status);
	             
	             if(login_status.matches("Successfully updated your photo")){
	                 

	         	
	         	
	         	  
	            	 
	            	 
	                 System.out.println("updated Successfully");
	                 
	                 
	                
	             }
	             

	       
	           
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	           
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (JSONException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	}

	    public void onBackPressed()
	    {
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
}