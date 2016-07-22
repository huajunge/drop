package com.cog.DropInn;
 
import info.androidhive.customlistviewvolley.app.AppController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.Optional_amenities.ArrayAdapterItem;
import com.cog.DropInn.Optional_amenities.ObjectItem;
import com.cog.DropInn.Optional_amenities.OnItemClickListenerListViewItem;
 
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class Optional_amenities1 extends Activity {
	private static final Class<?> Optional_amenities = null;
	MyCustomAdapter dataAdapter = null;
	ArrayList<ObjectItem> ObjectItemData;
	int k=100;
	public String[] name = new String[k];
	public String[] id = new String[k];
	private TextView cCheckedItems;
	 AlertDialog alertDialogStores;
	  List<String> infos = new ArrayList<String>();
	 String Liveurl="";
	 URL Login_Url;
	 String namy,namy1;
	 int len;
	 int count=0;
	 protected static final String TAG = null;
	   	ProgressDialog pDialog;
	 String c,c1,login_status1;
	 ListView listView;
	    String reader;
	    Button back;
	    ImageButton back1;
	    String login_inputline;
	    String login_status;
	    String imaage,address1,city,state,ObjectItem,image1,resize,userid,dis,dis1,dis2;
	    JSONArray login_jsonarray;
	    JSONObject login_jsonobj,login_Error;
	    ListView listViewItems1;
	    //ObjectItem[] ObjectItemData;
	//TextView longterm,currency,rooms_beds,description,amenities;
	String room_id;
	String latitude1,longitude1;
	String step,check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().hide();
		Intent i1=getIntent();
		room_id=i1.getStringExtra("room_id");
		//userid=i1.getStringExtra("userid");
		imaage=i1.getStringExtra("image");
		dis=i1.getStringExtra("title");
		dis1=i1.getStringExtra("summary");
		dis2=i1.getStringExtra("price");			
		address1=i1.getStringExtra("addresss");			
		city=i1.getStringExtra("city");
		state=i1.getStringExtra("state");
		ObjectItem=i1.getStringExtra("ObjectItem");
		image1=i1.getStringExtra("image1");
		resize=i1.getStringExtra("resize");
		step=i1.getStringExtra("step");
		check=i1.getStringExtra("check");
		
		//String latitude1,longitude1;

				latitude1=i1.getStringExtra("latitude");
				longitude1=i1.getStringExtra("longitude");
				
				/*optional.putExtra("latitude", latitude1);
				optional.putExtra("longitude",longitude1);*/
		System.out.println("aminities "+room_id);
		System.out.println("aminities "+userid);
		System.out.println("aminities "+imaage);
		System.out.println("aminities "+dis);
		System.out.println("aminities "+dis1);
		System.out.println("aminities "+dis2);
		System.out.println("aminities "+address1);
		System.out.println("aminities "+image1);
		System.out.println("aminities "+state);
		System.out.println("aminities "+ObjectItem);
		System.out.println("aminities "+city);
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		
		setContentView(R.layout.main);
		 if( Build.VERSION.SDK_INT >= 9){
             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
             StrictMode.setThreadPolicy(policy);
      }
		  
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Liveurl = sharedPreferences.getString("liveurl", null);
  displayListView();
 
  checkButtonClick();
 
 }
 
 private void displayListView() {
 
  //Array list of countries
  ArrayList<ObjectItem> countryList = new ArrayList<ObjectItem>();
  try
  {	      
  	
		System.out.println("Liveurl"+Liveurl);
          Login_Url = new URL(Liveurl+"rooms/display_amnities");
      	//Login_Url = new URL("Liveurl+"rooms/display_amnities");
          
      	System.out.println("inside try"+Login_Url);
          BufferedReader login_reader;
          String str_login="";            
         login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
          
          
          while ((login_inputline = login_reader.readLine())!= null)
          {
              
              str_login += login_inputline;
              
          }
  
          System.out.print("login"+str_login);
          
              login_jsonarray = new JSONArray(str_login);
              
  
      ObjectItemData = new ArrayList<ObjectItem>();
      
      k= login_jsonarray.length();
      
       
           for(int i=0; i <login_jsonarray.length(); i++)
              {
              
              
          login_jsonobj = login_jsonarray.getJSONObject(i);                                        

          name[i]=login_jsonobj.getString("name");
          id[i]=login_jsonobj.getString("id");
              
              System.out.println("id"+id);                            
              System.out.println("type of property type"+name);
              
              boolean selected = false;          
              ObjectItem item = new ObjectItem(name[i],id[i], selected);
              ObjectItemData.add(item);
          }
         
           // our adapter instance
           dataAdapter = new MyCustomAdapter(this,
        		    R.layout.country_info, ObjectItemData);
        		   listView = (ListView) findViewById(R.id.listView1);
        		  // Assign adapter to ListView
        		  listView.setAdapter(dataAdapter);
  
  }
  catch (MalformedURLException e)
  {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
  catch (IOException e)
  {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
  catch (JSONException e)
  {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
	

 
  //create an ArrayAdaptar from the String Array
 
 
 
  listView.setOnItemClickListener(new OnItemClickListener() {
   public void onItemClick(AdapterView<?> parent, View view,
     int position, long id) {
    // When clicked, show a toast with the TextView text
    ObjectItem ObjectItem = (ObjectItem) parent.getItemAtPosition(position);
    Toast.makeText(getApplicationContext(),
      "Clicked on Row: " + ObjectItem.getName(), 
      Toast.LENGTH_LONG).show();
   }
  });
 
 }
 
 private class MyCustomAdapter extends ArrayAdapter<ObjectItem> {
 

	  private ArrayList<ObjectItem> ObjectItemData;


public MyCustomAdapter(Context context, int textViewResourceId, 
    ArrayList<ObjectItem> ObjectItemData) {
   super(context, textViewResourceId, ObjectItemData);
   this.ObjectItemData = new ArrayList<ObjectItem>();
   this.ObjectItemData.addAll(ObjectItemData);
  }
 
  private class ViewHolder {
   TextView code;
   CheckBox name;
  }
 
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
 
   ViewHolder holder = null;
   Log.v("ConvertView", String.valueOf(position));
 
   if (convertView == null) {
   LayoutInflater vi = (LayoutInflater)getSystemService(
     Context.LAYOUT_INFLATER_SERVICE);
   convertView = vi.inflate(R.layout.country_info, null);
 
   holder = new ViewHolder();
   holder.code = (TextView) convertView.findViewById(R.id.code);
   holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
   convertView.setTag(holder);
 
    holder.name.setOnClickListener( new View.OnClickListener() {  
     public void onClick(View v) {  
      CheckBox cb = (CheckBox) v ;  
      ObjectItem ObjectItem = (ObjectItem) cb.getTag();  
      Toast.makeText(getApplicationContext(),
       "Clicked on Checkbox: " + cb.getText() +
       " is " + cb.isChecked(), 
       Toast.LENGTH_LONG).show();
      ObjectItem.setSelected(cb.isChecked());
     }  
    });  
   } 
   else {
    holder = (ViewHolder) convertView.getTag();
   }
 
   ObjectItem ObjectItem = ObjectItemData.get(position);
   holder.code.setText(" (" +  ObjectItem.getCode() + ")");
   holder.name.setText(ObjectItem.getName());
   holder.name.setChecked(ObjectItem.isSelected());
   holder.name.setTag(ObjectItem);
 
   return convertView;
 
  }
 
 }
 
 private void checkButtonClick() {
 
 
  Button myButton = (Button) findViewById(R.id.findSelected);
  myButton.setOnClickListener(new OnClickListener() {
 
   @Override
   public void onClick(View v) {
 
    StringBuffer responseText = new StringBuffer();
   /* responseText.append("The following were selected...\n");*/
 
    ArrayList<ObjectItem> countryList = dataAdapter.ObjectItemData;
    for(int i=0;i<countryList.size();i++){
     ObjectItem ObjectItem = countryList.get(i);
     if(ObjectItem.isSelected()){
      responseText.append(ObjectItem.getCode());
      System.out.println("checked values" +ObjectItem.getCode());
       namy = ObjectItem.getCode();
       if ( i != countryList.size()-2){
      	   responseText.append(",");
           }
       }
    
    }
    System.out.println("checked response" +responseText.toString());
    namy1=responseText.toString();
    call_webservice1();
    Toast.makeText(getApplicationContext(),
      responseText, Toast.LENGTH_LONG).show();
 
   }
  });
 
 }
 public class ObjectItem {
	  
	 String name = null;
	 String id = null;
	 boolean selected = false;
	  
	 public ObjectItem (String name, String id, boolean selected) {
	  super();
	  this.name = name;
	  this.id = id;
	  this.selected = selected;
	 }
	  
	 public String getCode() {
	  return name;
	 }
	 public void setCode(String code) {
	  this.name = code;
	 }
	 public String getName() {
	  return id;
	 }
	 public void setName(String name) {
	  this.id = name;
	 }
	 
	 public boolean isSelected() {
	  return selected;
	 }
	 public void setSelected(boolean selected) {
	  this.selected = selected;
	 }
	  
	}
 public void call_webservice1()
 {
	   
	
final String url=Liveurl+"rooms/india?roomid="+room_id+"&user_id="+namy1;
	      
	      	
	        System.out.println("URL is"+url);
			 // Creating volley request obj
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {
							 

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {
                   
            	                                  
									JSONObject obj = response.getJSONObject(i); 
									login_status1 =  obj.getString("reason_message");
                           
                

      System.out.println("after json"+login_status);
             
             if(login_status1.matches("List updated successfully"))
             {
                 

         		//final AlertDialog alertDialog = new AlertDialog.Builder(List_your_space.this).create();
                  
                 /*Toast.makeText(getApplicationContext(), "List added  Successfully", Toast.LENGTH_SHORT).show();*/
                  Intent i1 = new Intent(Optional_amenities1.this, List_your_space.class);
                  i1.putExtra("userid",userid);
                  i1.putExtra("roomid",room_id);
              /*   
                  total = 5 -(tit_step+sum_step+photo_step+add_step+pri_step);
	                System.out.println("totally" +total);
	                if (total==0)
	                {
	                Movie movie = new Movie();
	               // movie.total(String.valueOf(total));
	                System.out.println("india" +String.valueOf(total));
	                movieList.add(movie);
	                }*/
                   // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                    startActivity(i1);
                    finish();   
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