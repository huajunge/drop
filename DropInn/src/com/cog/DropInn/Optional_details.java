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

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

@SuppressLint("NewApi")
public class Optional_details extends Activity {
	int selected=0;	
	 AlertDialog alertDialogStores;
	 String Liveurl="";
		protected static final String TAG = null;
		 private String login_status1,checkstatus;
	 String first,second,third,fourth,last;
	 List<ObjectItem> ObjectItemData;	 
	 String symbol[]=new String[50];
	 String id[]=new String[50];
	 String currencycode[]=new String[50];
	 String default1[]=new String[50];
	 String currency1;	
	 ProgressDialog pDialog;
	    URL Login_Url,reset_Url,currency_Url,currency_Url1;
	    String reader;
	    String login_inputline,reset_inputline,currency_inputline,currency_inputline1;
	    String login_status,currency_status;
	    JSONArray login_jsonarray,reset_jsonarray,currency_jsonarray,currency_jsonarray1;
	    JSONObject login_jsonobj,login_Error,reset_jsonobj,currency_jsonobj,currency_jsonobj1;
	    String status,reset_status,currency_status1;
	 
	TextView longterm,currency,amenities,back;
	ImageButton rooms_beds,description;
	String imaage,address1,city,state,country;
	String room_id;
	ImageButton back1;
	Button deactivate;
	String dis,dis1,dis2,dis3,dis4;
	String value=null,value2;
	String userid;
	String guest,space,guest_interact,around,other,house,neighbour,weeklyedit,monthlyedit,currencycode1;
	String image1,resize;
	 ToggleButton tg1;
	String latitude1,longitude1,step,check;
	String currencyy1;
	//ListView listViewItems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.optional_details);
		getActionBar().hide();
		
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		room_id=i.getStringExtra("room_id");
		imaage=i.getStringExtra("image");
		dis=i.getStringExtra("title");
		dis1=i.getStringExtra("summary");
		dis2=i.getStringExtra("price");	
		
		address1=i.getStringExtra("addresss");			
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country=i.getStringExtra("country");
		image1=i.getStringExtra("image1");
		resize=i.getStringExtra("resize");
		step=i.getStringExtra("step");
		check=i.getStringExtra("check");
		first=i.getStringExtra("first");
		second=i.getStringExtra("second");
		third=i.getStringExtra("third");
		fourth=i.getStringExtra("fourth");
		last=i.getStringExtra("last");
		weeklyedit=i.getStringExtra("weeklyedit");
		monthlyedit=i.getStringExtra("monthlyedit");
		currencycode1=i.getStringExtra("currenycode");
	 longterm=(TextView)findViewById(R.id.TextView2);
	 tg1=(ToggleButton)findViewById(R.id.toggle);
	 TextView listing = (TextView)findViewById(R.id.Listing);
	 currency=(TextView)findViewById(R.id.TextView9);
	 System.out.println("weeklyedit"+monthlyedit);
		System.out.println("monthlyedit"+weeklyedit);
		
		if((weeklyedit != null && !weeklyedit.equals(""))|| (monthlyedit != null && !monthlyedit.equals("")))
		{
			//longterm.setText("On");
			
		}
		
		//String latitude1,longitude1;

		latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
		
		/*optional.putExtra("latitude", latitude1);
		optional.putExtra("longitude",longitude1);*/
		
	value=i.getStringExtra("value");
	value2=i.getStringExtra("amenities");
	
	 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
	 userid = sharedPreferences2.getString("userid", null);
	  currencyy1 = sharedPreferences2.getString("currency1", null);
	  
	  if (currencyy1!=null)
	  {
  currency.setText(currencyy1);
}
else
{
  currency.setText("USD");
}

		//week=i.getStringExtra("")
	System.out.println("inside optional details "+imaage);
	System.out.println("check price"+dis);
	System.out.println("check price"+dis1);
	System.out.println("check price"+dis2);
	System.out.println("check price"+address1);
	System.out.println("check price"+city);
	System.out.println("check price"+state);
	System.out.println("check price"+country);
	System.out.println("check price"+image1);
	System.out.println("check price"+userid);
	System.out.println("check price"+room_id);
	
	
	
		System.out.println("value in optional deatails page "+value);
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Liveurl = sharedPreferences.getString("liveurl", null);	 
           
        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        space = sharedPreferences1.getString("space", null);
        guest = sharedPreferences1.getString("guest", null);
        guest_interact = sharedPreferences1.getString("guest_interact", null);
        neighbour = sharedPreferences1.getString("neighbour", null);
        around = sharedPreferences1.getString("around", null);
        house = sharedPreferences1.getString("house", null);
        other = sharedPreferences.getString("other", null);
        
        
        System.out.println("shared preferences"+space);
        System.out.println("shared preferences"+guest);
        System.out.println("shared preferences"+guest_interact);
        System.out.println("shared preferences"+neighbour);
        System.out.println("shared preferences"+around);
        System.out.println("shared preferences"+house);
        System.out.println("shared preferences"+other);
        
        if(step!=null)
        {
        if(step.equals("0"))
        {
        	tg1.setVisibility(View.VISIBLE);	
        	listing.setVisibility(View.VISIBLE);
        	if(check!=null)
        	{
        	if (check.equals("0"))
        	{
        		tg1.setChecked(false);
        		tg1.setText("Off");
        		
        	}
        	else if (check.equals("1"))
        	{
        		tg1.setChecked(true);
        		tg1.setText("On");
        	}
        	}
        }
        else
        {
        	tg1.setVisibility(View.INVISIBLE);	
        	listing.setVisibility(View.INVISIBLE);
        }
        }

		  if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
     }
			
			 try
	          { 
                  	         	             
				 //currency_Url1 = new URL(Liveurl+"user/view_currency?roomid=2");
	             currency_Url1 = new URL(Liveurl+"user/view_currency?roomid="+room_id);	  
	             
	             System.out.print("inside if currency url1 view==="+ currency_Url1);
	              BufferedReader currency_reader1;
	              String str_currency1="";            
	             // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
	              
	              currency_reader1 = new BufferedReader(new InputStreamReader(currency_Url1.openStream()));
	              		             
	              
	              while ((currency_inputline1 = currency_reader1.readLine())!= null)
	              {
	            	  
	                  str_currency1 += currency_inputline1;
	                  
	              }
	      
	              System.out.print("reset"+str_currency1);
	              
	                 currency_jsonarray1 = new JSONArray(str_currency1);
	                  
	             
	                   for(int i1=0; i1 <currency_jsonarray1.length(); i1++)
	                      {
	                      	 currency_jsonobj1=currency_jsonarray1.getJSONObject(i1);
	                  currency_status1 = currency_jsonobj1.getString("currency");
	                  
	                      }
	                   System.out.print("currency_status1"+ currency_status1);
	                   
	                                        	                        
	          
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
		 
		  		  
		
		
		 rooms_beds=(ImageButton)findViewById(R.id.imageButton3);
		 amenities=(TextView)findViewById(R.id.TextView10);
		 back=(TextView)findViewById(R.id.TextView11);
		 back1=(ImageButton)findViewById(R.id.imageButton1);
		 deactivate=(Button)findViewById(R.id.button1);
		 description=(ImageButton)findViewById(R.id.imageButton2);
		 
		 
		 
		 
		 currency.setText(currency_status1);
		 
		 System.out.print("currency_status1 view currency"+ currency_status1);
		 
		 if(currency_status1.equals("")||currency_status1.equals("null")||currency_status1=="null"||currency_status1==null)
		 {
			 currency.setText("None"); 
			 
			 System.out.print("inside if condition view currency"+ currency_status1);
		 }

		//currency1=currency.toString();
		 System.out.print("after if condition view currency"+ currency1);
		
		  amenities.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Optional_details.this,Optional_amenities.class);
		
				i.putExtra("room_id",room_id);
				i.putExtra("userid",userid);
				i.putExtra("image", imaage);
				i.putExtra("image1",image1);
				i.putExtra("resize",resize);
				i.putExtra("title",dis);				
				i.putExtra("summary", dis1);
				i.putExtra("price",dis2);
				i.putExtra("city", city);
				i.putExtra("state", state);
				i.putExtra("country", country);
				i.putExtra("addresss",address1);
				i.putExtra("latitude", latitude1);
				i.putExtra("longitude",longitude1);
				i.putExtra("step",step);
				i.putExtra("check",check);
				startActivity(i);
				 finish();
			}
		});
		  if(value2!=null)
			{
				//amenities.setText(value2);
			}
		 
		 
		 
		/* if(value!=null)
		  {
			  longterm.setText("On");
		  }else{
			  longterm.setText("Off");
		  }*/
		
		 longterm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent longterm_price=new Intent(Optional_details.this,Longterm_price.class);
				longterm_price.putExtra("room_id",room_id);
				longterm_price.putExtra("userid",userid);
				longterm_price.putExtra("image", imaage);
				longterm_price.putExtra("image1",image1);
				longterm_price.putExtra("resize",resize);
				longterm_price.putExtra("title",dis);				
				longterm_price.putExtra("summary", dis1);
				longterm_price.putExtra("price",dis2);
				longterm_price.putExtra("city", city);
				longterm_price.putExtra("state", state);
				longterm_price.putExtra("country", country);
				longterm_price.putExtra("addresss",address1);
				
				longterm_price.putExtra("first", first);
				longterm_price.putExtra("second", second);
				longterm_price.putExtra("third", third);
				longterm_price.putExtra("fourth", fourth);
				longterm_price.putExtra("last", last);
				
				longterm_price.putExtra("latitude", latitude1);
				longterm_price.putExtra("longitude",longitude1);
				longterm_price.putExtra("step",step);
				longterm_price.putExtra("check",check);
				startActivity(longterm_price);
				finish();
			}
		});

		 rooms_beds.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent rooms=new Intent(Optional_details.this,Optional_rooms_beds.class);
				rooms.putExtra("room_id",room_id);
				rooms.putExtra("userid",userid);
				rooms.putExtra("image", imaage);
				rooms.putExtra("image1",image1);
				rooms.putExtra("resize",resize);
				rooms.putExtra("title",dis);				
				rooms.putExtra("summary", dis1);
				rooms.putExtra("price",dis2);
				rooms.putExtra("city", city);
				rooms.putExtra("state", state);
				rooms.putExtra("country", country);
				rooms.putExtra("addresss",address1);
				rooms.putExtra("latitude", latitude1);
				rooms.putExtra("longitude",longitude1);
				
				
				rooms.putExtra("first", first);
				rooms.putExtra("second", second);
				rooms.putExtra("third", third);
				rooms.putExtra("fourth", fourth);
				rooms.putExtra("last", last);
				rooms.putExtra("step", step);
				rooms.putExtra("check", check);
				startActivity(rooms);
				finish();
				
			}
		});
		 
		 
		 description.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent desc=new Intent(Optional_details.this,Writedescription.class);
				desc.putExtra("room_id",room_id);
				desc.putExtra("userid",userid);
				desc.putExtra("image", imaage);
				desc.putExtra("image1",image1);
				desc.putExtra("resize",resize);
				
				desc.putExtra("title",dis);				
				desc.putExtra("summary", dis1);
				desc.putExtra("price",dis2);
				desc.putExtra("city", city);
				desc.putExtra("state", state);
				desc.putExtra("country", country);
				desc.putExtra("addresss",address1);
				
				desc.putExtra("first", first);
				desc.putExtra("second", second);
				desc.putExtra("third", third);
				desc.putExtra("fourth", fourth);
				desc.putExtra("last", last);
				
				desc.putExtra("space", space);
				desc.putExtra("guest", guest);
				desc.putExtra("guest_interact", guest_interact);
				desc.putExtra("neighbour", neighbour);
				desc.putExtra("around", around);
				desc.putExtra("other", other);
				desc.putExtra("house", house);
				
				desc.putExtra("latitude", latitude1);
				desc.putExtra("longitude",longitude1);
				desc.putExtra("step",step);
				desc.putExtra("check",check);
				startActivity(desc);
				finish();
			}
		});
		 
			back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent back=new Intent(Optional_details.this,List_your_space.class);
					back.putExtra("room_id",room_id);
					back.putExtra("userid",userid);
					back.putExtra("image", imaage);
					back.putExtra("image1",image1);
					back.putExtra("resize",resize);
					back.putExtra("write_title", dis);
					back.putExtra("write_summary", dis1);
					back.putExtra("write_price", dis2);
					back.putExtra("address", address1);
					back.putExtra("city",city);
					back.putExtra("state",state);
					back.putExtra("country",country);
					back.putExtra("latitude", latitude1);
					back.putExtra("longitude",longitude1);
					back.putExtra("first", first);
					back.putExtra("second", second);
					back.putExtra("third", third);
					back.putExtra("fourth", fourth);
					back.putExtra("last", last);
					back.putExtra("weeklyedit", weeklyedit);
					back.putExtra("monthlyedit", monthlyedit);
					back.putExtra("currenycode", currencycode1);
					back.putExtra("step", step);
					back.putExtra("check", check);
					
				
					startActivity(back);
					finish();
							
					
				}
			});
			back1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			         Intent reset=new Intent(Optional_details.this,List_your_space.class);
	                  	reset.putExtra("room_id",room_id);
						reset.putExtra("userid",userid);
						reset.putExtra("image", imaage);
						reset.putExtra("image1",image1);
						reset.putExtra("resize",resize);
						reset.putExtra("write_title", dis);
						reset.putExtra("write_summary", dis1);
						reset.putExtra("write_price", dis2);
						reset.putExtra("address", address1);
						reset.putExtra("city",city);
						reset.putExtra("state",state);
						reset.putExtra("country",country);
						reset.putExtra("latitude", latitude1);
						reset.putExtra("longitude",longitude1);
						
						reset.putExtra("first", first);
						reset.putExtra("second", second);
						reset.putExtra("third", third);
						reset.putExtra("fourth", fourth);
						reset.putExtra("last", last);
						reset.putExtra("weeklyedit", weeklyedit);
						reset.putExtra("monthlyedit", monthlyedit);
						reset.putExtra("currenycode", currencycode1);
						reset.putExtra("step", step);
						reset.putExtra("check", check);
				
	  					
	                     startActivity(reset);
	                      
	                                      finish();
								
					
					
					
				}
			});
				 
		 
		 
			 currency.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						  /* final   Dialog d1 = new Dialog(Optional_details.this);
		                   d1.setContentView(R.layout.select_currency);
		                   d1.setTitle("Select currency");
		                   d1.show();
		                   */
		                  try
		                   {	      
		   		        	
		   		        	System.out.println("Liveurl "+Liveurl);
		                 
		           	Login_Url = new URL(Liveurl+"user/currency"+"?roomid="+room_id);
		             //	Login_Url = new URL(Liveurl+"user/currency");         
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
		                        
		                            for(int i=0; i <login_jsonarray.length(); i++)
		                               {
		                               
		                               
		                           login_jsonobj = login_jsonarray.getJSONObject(i);                                        

		                               id[i] =login_jsonobj.getString("id");
		                               currencycode[i]=login_jsonobj.getString("currency_code");
		                               
		                               symbol[i]=login_jsonobj.getString("currency_symbol");
		                                 
		                               
		                               symbol[i] = Html.fromHtml(symbol[i]).toString();
		                             		                               
		                               default1[i]=login_jsonobj.getString("default");
		                               System.out.println("id"+id[i]);                            
		                               System.out.println("type of currency code"+currencycode[i]);
		                               System.out.println("type of currency symbol"+symbol[i]);
		                             	      
		                               
		                               ObjectItem item=new ObjectItem(id[i],currencycode[i],symbol[i],default1[i]);
		                               ObjectItemData.add(item);
		                                                
		                           }
		                          
		                            // our adapter instance
		                            // ListView listViewItems = (ListView)findViewById(R.id.currency_listview);

		                            // create a new ListView, set the adapter and item click listener
		                                           
		                        ArrayAdapterItem adapter = new ArrayAdapterItem(Optional_details.this, R.layout.select_currency, ObjectItemData);
		                                                 
		                        ListView listViewItems=new ListView(Optional_details.this);
		                        listViewItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		                       listViewItems.getCheckedItemCount();
		                       
		                       System.out.println("cheked item count in currency "+listViewItems.getCheckedItemCount());
		                       
		                        listViewItems.clearFocus();
		                        listViewItems.setAdapter(adapter);                          
		                      listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());                    
		                                alertDialogStores = new AlertDialog.Builder(Optional_details.this)
		                                .setView(listViewItems)
		                                .setTitle("Select Currency")
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
		   
         deactivate.setOnClickListener(new View.OnClickListener() {
				
				@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 try
			          { 

			                         
			             System.out.println("The URL IS "+reset_Url);
			             
			             reset_Url = new URL(Liveurl+"user/deactive?roomid="+room_id);
			             // Login_Url = new URL(Liveurl+"user/deactive?roomid="+room_id);
			              
			              BufferedReader reset_reader;
			              String str_reset="";            
			             // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
			              
			              reset_reader = new BufferedReader(new InputStreamReader(reset_Url.openStream()));
			              
			             
			              
			              while ((reset_inputline = reset_reader.readLine())!= null)
			              {
			            	  
			                  str_reset += reset_inputline;
			                  
			              }
			      
			              System.out.print("reset"+str_reset);
			              
			                  reset_jsonarray = new JSONArray(str_reset);
			                  
			             
			                   for(int i=0; i <reset_jsonarray.length(); i++)
			                      {
			                      	                      
			                  reset_jsonobj = reset_jsonarray.getJSONObject(i);
			                  reset_status = reset_jsonobj.getString("reason_message");
			                  
			                      }
			                   System.out.print("reset_status"+ reset_status);
			                   
			                   if(reset_status.matches("List Deleted Successfully"))
			                  {
			                      

			                      Toast toast=Toast.makeText(getApplicationContext(), "List Deleted Successfully", Toast.LENGTH_SHORT);
			                      toast.setGravity(Gravity.CENTER, 0, 0);
			                      toast.show();
			                      //wait(5000);
			                     // reset.setEnabled(false);
			                      Intent reset=new Intent(getApplicationContext(),List_your_space.class);
			                      reset.putExtra("room_id",room_id);
									reset.putExtra("userid",userid);
									reset.putExtra("image", imaage);
									reset.putExtra("image1",image1);
									reset.putExtra("resize",resize);
									reset.putExtra("write_title", dis);
									reset.putExtra("write_summary", dis1);
									reset.putExtra("write_price", dis2);
									reset.putExtra("address", address1);
									reset.putExtra("city",city);
									reset.putExtra("state",state);
									reset.putExtra("country",country);									
									reset.putExtra("first", first);
									reset.putExtra("second", second);
									reset.putExtra("third", third);
									reset.putExtra("fourth", fourth);
									reset.putExtra("last", last);
									reset.putExtra("step", step);
									reset.putExtra("check", check);
									reset.putExtra("latitude", latitude1);
									reset.putExtra("longitude",longitude1);
									
			                      
			                      //overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
			                    // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.abc_fade_in,R.anim.abc_fade_out).toBundle();
			                      startActivity(reset);
			                      finish();
			                                
			                    
			                  }
			          
			                      
			                        
			          
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

	    public String id;
	    public String currencycode;
	    public String symbol;
	    public String default1;
	    

	    // constructor
	    public ObjectItem(String id, String currencycode,String symbol,String default1) {
	        this.id = id;
	        this.currencycode = currencycode;
	        this.symbol = symbol;
	        this.default1 = default1;
	    }

	}


	//here's our beautiful adapter
	public class ArrayAdapterItem extends ArrayAdapter<ObjectItem> {
		int selectedIndex = -1;
	 Context mContext;
	 int layoutResourceId;
	 //ObjectItem data[] = null;

	 public ArrayAdapterItem(Context mContext, int layoutResourceId,  List<ObjectItem> ObjectItemData) {

	     super(mContext, layoutResourceId, ObjectItemData);

	
	     this.mContext = mContext;
	  
	 }
	 public void setSelectedIndex(int index){
		    selectedIndex = index;
		}
	 private class ViewHolder {
		 
	        TextView currencycode,symbol;
	        RadioButton radiobutton;
	        
	        
	    }
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {

		 ViewHolder holder = null;
		   
		 ObjectItem rowitem = getItem(position);
			
		 LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	     if(convertView==null){
	    
 
	         convertView = inflater.inflate(R.layout.select_currency, null);
	
	         holder = new ViewHolder();
	         
	            holder.currencycode = (TextView) convertView.findViewById(R.id.TextView1);
	            holder.symbol = (TextView) convertView.findViewById(R.id.TextView2);
	            holder.radiobutton = (RadioButton) convertView.findViewById(R.id.radioButton1);
	     }
 

	     TextView TextViewItem = (TextView) convertView.findViewById(R.id.currencyTextView1);
	     TextView TextViewItem1 = (TextView) convertView.findViewById(R.id.currencyTextView2);
	     
	     RadioButton radiobutton=(RadioButton)convertView.findViewById(R.id.radioButton1);	
	     
	     
	     
	     System.out.println("radio button "+position );
	     
	     if(selectedIndex == position){
	    	 
		     System.out.println("inside if condition ");
	    	 
		     
	    	
	    	    }
	    	    else{
	    	    	
	    	    	System.out.println("inside else condition ");
	    	    radiobutton.setChecked(false);
	    	    
	    	    SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(mContext);
	    		 //userid = sharedPreferences2.getString("userid", null);
	    		//currencycodee =  sharedPreferences2.getString("currenycode", null);
	    	      currencyy1 = sharedPreferences2.getString("currency1", null);
	    	    
	    	    System.out.println("after code  "+default1[position]);
	    	    if(currencycode[position].equals(currencyy1))
	    	    {
	    	    	radiobutton.setChecked(true);
	    	    }
	    	    
	    	    }
	     
	     TextViewItem.setText(rowitem.currencycode);
	     TextViewItem1.setText(rowitem.symbol);

	
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

	        TextView TextViewItem = ((TextView) view.findViewById(R.id.currencyTextView1));
	        TextView TextViewItem1 = ((TextView) view.findViewById(R.id.currencyTextView2));

	        RadioButton radiobutton = (RadioButton)view.findViewById(R.id.radioButton1);

	     String currency1 =TextViewItem.getText().toString();
	     String  currencycode =TextViewItem1.getText().toString();
	     
	     SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         Editor editor = sharedPreferences.edit();
         editor.putString("currenycode", currencycode);
         editor.putString("currency1", currency1);
         editor.commit();
         System.out.println("********"+currency1);
         //System.out.println("********"+currencycode);
         
	     
	     
	        radiobutton.setChecked(true);
	        currency.setText(currency1);
	        try
	          { 
                 System.out.println("currency in before url"+currency1);
	         	             
				// currency_Url = new URL("Liveurl+"user/edit_currency?roomid=2&currency=USD");
		            currency_Url = new URL(Liveurl+"user/edit_currency?roomid="+room_id+"&currency="+currency1);
				 System.out.println("currency in after url"+currency_Url);
	              BufferedReader currency_reader;
	              String str_currency="";            
	             // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
	              
	              currency_reader = new BufferedReader(new InputStreamReader(currency_Url.openStream()));
	              		             
	              
	              while ((currency_inputline = currency_reader.readLine())!= null)
	              {
	            	  
	                  str_currency += currency_inputline;
	                  
	              }
	      
	              System.out.print("reset"+str_currency);
	              
	                 currency_jsonarray = new JSONArray(str_currency);
	                  
	             
	                   for(int i=0; i <currency_jsonarray.length(); i++)
	                      {
	                	   currency_jsonobj=currency_jsonarray.getJSONObject(i);
	                  currency_status = currency_jsonobj.getString("reason_message");
	                  
	                      }
	                   System.out.print("currency_status"+ currency_status);
	                   
	                   if(currency_status.matches("Updated Successfully"))
	                  {
	                      
	                	   System.out.println("inside intent");
	                      /*Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
	                      toast1.setGravity(Gravity.CENTER, 0, 0);
	                    toast1.show();*/
                 	                                
	                    
	                  }
	          
	                    	                        
	          
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

        // get the clicked item ID
	    /*    String listItemId = TextViewItem.getTag().toString();
	        
	        String listItemText1 = TextViewItem1.getText().toString();
	        Intent location=new Intent(Optional_details.this,Optional_details.class);
	 	        startActivity(location);*/
	        // just toast it
	        

	        ((Optional_details) context).alertDialogStores.cancel();

	}
	
	}
	@Override
	public void onBackPressed()
	{
		/*Intent back=new Intent(Optional_details.this,List_your_space.class);
		back.putExtra("room_id",room_id);
		back.putExtra("userid",userid);
		startActivity(back);
		finish();*/
	}
	 public void call_webservice1()
	 {
			final String url=Liveurl+"rooms/setcheckstatus?roomid="+room_id+"&check_status_count="+checkstatus;
			   System.out.println("url" +url);
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {
							 

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {
                   
            	                                  
									JSONObject obj = response.getJSONObject(i); 
									login_status1 =  obj.getString("reason_message");
									
									  System.out.println("after json"+login_status1);
							             
							             if(login_status1.matches("List and Price updated successfully"))
							             {
							                 

							         		//final AlertDialog alertDialog = new AlertDialog.Builder(List_your_space.this).create();
							                  
							                 
							                  Intent i1 = new Intent(Optional_details.this, Swipe_tabs.class);
							                  i1.putExtra("userid",userid);
							                  i1.putExtra("roomid",room_id);
							                  startActivity(i1);
							                    finish();
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
							                   /* startActivity(i1);
							                    finish();   */
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
							 public void onToggleClicked(View view) {
								    // Is the toggle on?
								    boolean on = ((ToggleButton) view).isChecked();

								    if (tg1.isChecked()) {
								    	checkstatus="1";
								        // Enable vibrate
								    	Toast.makeText(getApplicationContext(), "Your space is listed and can be seen publicly", Toast.LENGTH_SHORT).show();
								    	tg1.setText("On");
								    	call_webservice1();
								    } else {
								    	checkstatus="0";
								    	tg1.setText("Off");
								    	Toast.makeText(getApplicationContext(), "Your space is unlisted and cannot be seen publicly", Toast.LENGTH_SHORT).show();
								    	call_webservice1();
								        // Disable vibrate
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