package com.cog.DropInn;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class Propertytype extends Activity {

	String title1="What city is your Apartment Located in ?";
	String title2="What city is your House Located in ?";
	String title3="What city is your Room Located in ?";
	AlertDialog alertDialogStores;
	ImageView apartment,house,bedroom;
	Button moreoptions;
	public  static String id,type;
	TextView title,moreitems;
	String title4;
	 String Liveurl="";
	 URL Login_Url;
	    String reader;
	    String login_inputline;
	    String login_status;
	    JSONArray login_jsonarray;
	    JSONObject login_jsonobj,login_Error;
	    ObjectItem[] ObjectItemData;
    // Defined Array values to show in ListView
	    String userid,room_type;
	/*String flat="Flat";
	String house1="House";
	String bedroom1="Bed room";*/
	    String prop_type;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_propertytype);
		getActionBar().hide();

		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		room_type=i.getStringExtra("room_type");		
		System.out.println("userid in property type"+userid);
		System.out.println("roomtype in property type page"+room_type);
		title4=i.getStringExtra("title");
		
		
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null);
         
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
         
         
		  if( Build.VERSION.SDK_INT >= 9){
              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
              StrictMode.setThreadPolicy(policy);
       }
		
		
                      
     	System.out.println("title1"+title1);
		
	
		ImageButton back=(ImageButton)findViewById(R.id.imageButton1);
		
		moreitems= (TextView) findViewById(R.id.TextView2);
		title= (TextView) findViewById(R.id.TextView1);
		apartment=(ImageView)findViewById(R.id.imageView3);
		house=(ImageView)findViewById(R.id.imageView4);
		bedroom=(ImageView)findViewById(R.id.ImageView01);
		moreoptions=(Button)findViewById(R.id.ImageView02);
		//title=(TextView)findViewById(R.id.TextView1);		
		
		title.setText(title4);
				
		apartment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent next=new Intent(Propertytype.this,Googlemap.class);
				prop_type="Apartment";
				next.putExtra("title", title1);
				next.putExtra("userid",userid);
				next.putExtra("prop_type",prop_type);
				next.putExtra("room_type", room_type);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Propertytype.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(next,bndlanimation);				
				finish();
			}
		});
		
		house.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent next1=new Intent(Propertytype.this,Googlemap.class);
				prop_type="House";
				next1.putExtra("title", title2);
				next1.putExtra("userid",userid);
				next1.putExtra("prop_type",prop_type);
				next1.putExtra("room_type", room_type);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Propertytype.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(next1,bndlanimation);
				finish();
			}
		});
		
		bedroom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent next2=new Intent(Propertytype.this,Googlemap.class);
				prop_type="Bed and Breakfast";
				next2.putExtra("title", title3);
				next2.putExtra("userid",userid);
				next2.putExtra("prop_type",prop_type);
				next2.putExtra("room_type", room_type);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Propertytype.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(next2,bndlanimation);
				finish();
			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(Propertytype.this,Listspace.class);	
				back.putExtra("userid",userid);
				back.putExtra("room_type", room_type);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Propertytype.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(back,bndlanimation);
				finish();
			}
		});
		
		
		//********************************* more options****************
		moreoptions.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("inside moreoptions");
		       try
                {	      
		        	
		        	System.out.println("Liveurl"+Liveurl);
                   // Login_Url = new URL(Liveurl+"mobile/log?email="+Get_email+"&password="+Get_password);
                	Login_Url = new URL(Liveurl+"rooms/other");
                    
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
                        
                        ObjectItem[] ObjectItemData = new ObjectItem[login_jsonarray.length()];
                     
                         for(int i=0; i <login_jsonarray.length(); i++)
                            {
                            
                            
                        login_jsonobj = login_jsonarray.getJSONObject(i);                                        

                            id =login_jsonobj.getString("id");
                            type=login_jsonobj.getString("type");
                            
                            
                            System.out.println("id"+id);                            
                            System.out.println("type of property type"+type);
                          	              
                            ObjectItemData[i] = new ObjectItem(id,type);                    
                        }
                       
                         // our adapter instance
                         ArrayAdapterItem adapter = new ArrayAdapterItem(getApplicationContext(), R.layout.moreoptions_rowview, ObjectItemData);

                         // create a new ListView, set the adapter and item click listener
                         ListView listViewItems = new ListView(Propertytype.this);
                         listViewItems.setAdapter(adapter);
                         listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());

                         // put the ListView in the pop up
                         alertDialogStores = new AlertDialog.Builder(Propertytype.this)
                             .setView(listViewItems)
                             .setTitle("More options")
                             .show();                
                        
                
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
				
		      
			}
		});
	
}

public class ObjectItem {

    public String itemId;
    public String itemName;

    // constructor
    public ObjectItem(String itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

}


//here's our beautiful adapter
public class ArrayAdapterItem extends ArrayAdapter<ObjectItem> {

 Context mContext;
 int layoutResourceId;
 ObjectItem data[] = null;

 public ArrayAdapterItem(Context mContext, int layoutResourceId, ObjectItem[] data) {

     super(mContext, layoutResourceId, data);

     this.layoutResourceId = layoutResourceId;
     this.mContext = mContext;
     this.data = data;
 }

 @Override
 public View getView(int position, View convertView, ViewGroup parent) {

     /*
      * The convertView argument is essentially a "ScrapView" as described is Lucas post 
      * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
      * It will have a non-null value when ListView is asking you recycle the row layout. 
      * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
      */
     if(convertView==null){
         // inflate the layout
         LayoutInflater inflater = ((Propertytype.this)).getLayoutInflater();
         convertView = inflater.inflate(layoutResourceId, parent, false);
     }

     // object item based on the position
     ObjectItem objectItem = data[position];

     // get the TextView and then set the text (item name) and tag (item ID) values
     TextView TextViewItem = (TextView) convertView.findViewById(R.id.moreoptionsTextView1);
     TextViewItem.setText(objectItem.itemName);
     TextViewItem.setTag(objectItem.itemId);

     return convertView;

 }

}


/*
 * Here you can control what to do next when the user selects an item
 */
public class OnItemClickListenerListViewItem implements OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView TextViewItem = ((TextView) view.findViewById(R.id.moreoptionsTextView1));

        // get the clicked item name
        String listItemText = TextViewItem.getText().toString();

        // get the clicked item ID
        String listItemId = TextViewItem.getTag().toString();

        Intent location=new Intent(Propertytype.this,Googlemap.class);
        location.putExtra("propertyid",listItemId);
        location.putExtra("prop_type",listItemText );
        location.putExtra("userid", userid);
        location.putExtra("room_type", room_type);
        
        startActivity(location);
        // just toast it
       // Toast.makeText(context, "Item: " + listItemText + ", Item ID: " + listItemId, Toast.LENGTH_SHORT).show();

        ((Propertytype) context).alertDialogStores.cancel();
        finish();
    }

}




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
