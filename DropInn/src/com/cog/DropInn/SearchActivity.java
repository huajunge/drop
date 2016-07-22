package com.cog.DropInn;



import com.cog.DropInn.*;

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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchActivity extends Activity {

	AutoCompleteTextView atvPlaces;
	Button scancel;
	ImageView search,clear,back;
	//ListView lv=(ListView)findViewById(R.id.listView1);
	PlacesTask placesTask;
	ParserTask parserTask;
	String User_id,fbuserproimg,WhoLogin,checkpassword,location;
	String userid,roomid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		getActionBar().hide();
		atvPlaces = (AutoCompleteTextView) findViewById(R.id.atv_places);
		atvPlaces.setThreshold(1);
		 
		search=(ImageView)findViewById(R.id.imageView1);
		back=(ImageView)findViewById(R.id.imageView);
		clear=(ImageView)findViewById(R.id.clear);	
		clear.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				atvPlaces.setText("");
				
			}
		});
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		Intent i=getIntent();
	//userid=i.getStringExtra("userid");
		roomid=i.getStringExtra("room_id");
		System.out.println("user id in search Activity"+userid);
		System.out.println("room id in search Activity"+roomid);
		//scancel=(Button)findViewById(R.id.scancel);
		/*scancel.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent slide=new Intent(getApplicationContext(),Discover.class);
			slide.putExtra("userid", User_id);
			slide.putExtra("fbuserproimg",fbuserproimg);
			System.out.println("Slide Main Activity Fb Profile imag"+fbuserproimg);
			slide.putExtra("whologin",WhoLogin);
			slide.putExtra("password", checkpassword);
			System.out.println("Slide Main Activity WhoLogin"+WhoLogin);
			startActivity(slide);
			finish();
			}
		});*/
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(SearchActivity.this,Swipe_tabs.class);
				i.putExtra("room_id",roomid);
				i.putExtra("userid",userid);
				startActivity(i);
				finish();
				
			}
		});
		atvPlaces.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {				
				placesTask = new PlacesTask();				
				placesTask.execute(s.toString());
                search.setVisibility(View.INVISIBLE);						
				
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

		String CorrectPlace=atvPlaces.getText().toString();
		System.out.println("Selected Text"+CorrectPlace);
		//ListView list = (ListView) findViewById(R.id.listview);
		
		
		
		CorrectPlace=atvPlaces.getText().toString();
			System.out.println("Selected Text"+CorrectPlace); 
			
			String Selectionend=atvPlaces.getEditableText().toString();
			System.out.println("getEditableText"+Selectionend); 
			
			/*String Selectionend1=atvPlaces.getItemClickListener().toString();
			System.out.println("getItemClickListener"+Selectionend1); 
			
			String Selectionend2=atvPlaces.getItemSelectedListener().toString();
		System.out.println("getIemSelectedListener"+Selectionend2); */

	}
	
	/* A method to download json data from url */
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
	
	// Fetches all places from GooglePlaces AutoComplete Web Service
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
		
		@Override
		protected void onPostExecute(String result) {			
			super.onPostExecute(result);
			
			// Creating ParserTask
			parserTask = new ParserTask();
			
			// Starting Parsing the JSON string returned by Web Service
			parserTask.execute(result);
		}		
	}
	
	
	/** A class to parse the Google Places in JSON format */
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
		
		@Override
		protected void onPostExecute(List<HashMap<String, String>> result) {			
			
				String[] from = new String[] { "description"};
				int[] to = new int[] { android.R.id.text1 };
				
				// Creating a SimpleAdapter for the AutoCompleteTextView			
				final SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, android.R.layout.simple_list_item_1, from, to);				
				//SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, R.id.listView1, from, to);				

				// Setting the adapter
				
				//atvPlaces.setAdapter(adapter);
				 
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
			 
			                /** Each item in the adapter is a HashMap object.
			                *  So this statement creates the currently clicked hashmap object
			                * */
			                @SuppressWarnings("unchecked")
							HashMap<String, String> hm = (HashMap<String, String>) arg0.getAdapter().getItem(position);
			 
			                /** Getting a reference to the TextView of the layout file activity_main to set Currency */
			                
			 
			                /** Getting currency from the HashMap and setting it to the TextView */
			                //atvPlaces.setText(hm.get("description"));
			                System.out.println("Selected ITem In the List is"+hm.get("description"));
			                //System.out.println("Selected ITem In the List is"+atvPlaces.getText().toString());
			                String a=atvPlaces.getText().toString();
			                 String[] at = a.split(",");
			                 String city=at[0];
			                 //String state=at[1];
			                // String country=at[2];
			                
			                
			                
			                location=hm.get("description");
			                Intent can=new Intent(getApplicationContext(),Search_result.class);
			                can.putExtra("location",city);
			            	can.putExtra("room_id",roomid);
							can.putExtra("userid",userid);
			                //can.putExtra("state", state);
			                //can.putExtra("country",country);
			                System.out.println("city"+city);
			              //  System.out.println("state"+state);
			               // System.out.print("country"+country);
			         		
			         		startActivity(can);
			         	    finish();
			                
			            }
			        };
			        atvPlaces.setOnItemClickListener(itemClickListener);
			        
			        
			        atvPlaces.setAdapter(adapter);
			        adapter.notifyDataSetChanged();
			    	
			        /** Setting the adapter to the listView */
			      //  autoComplete.setAdapter(adapter);

				//atvPlaces.setAdapter(adapter);
		}			
    }   
 /*  
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")
	public void onBackPressed()
 	{
 		Intent can=new Intent(getApplicationContext(),SlideMainActivity.class);
 		can.putExtra("userid", User_id);
 		can.putExtra("fbuserproimg",fbuserproimg);
 		can.putExtra("whologin",WhoLogin);
		can.putExtra("password",checkpassword);
		can.putExtra("location",location);
 		//Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
 	    startActivity(can);
 	    finish();
 	}*/

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
   
    
