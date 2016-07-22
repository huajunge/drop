package com.cog.DropInn;

import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_currency extends Activity {
	int selected=0;	
	 int selectItem=-1;
	 AlertDialog alertDialogStores;
	 String Liveurl="";
	 private ProgressDialog progressDialog;
	 String first,second,third,fourth,last;
	 List<ObjectItem> ObjectItemData;	 
	 String symbol[]=new String[50];
	 String id[]=new String[50];
	 String currencycode[]=new String[50];
	 ProgressDialog pdialog;
	 String default1[]=new String[50];
	 String currency1;	
	 ListView listViewItems;
	    URL Login_Url,reset_Url,currency_Url,currency_Url1;
	    String reader;
	    String login_inputline,reset_inputline,currency_inputline,currency_inputline1;
	    String login_status,currency_status;
	    JSONArray login_jsonarray,reset_jsonarray,currency_jsonarray,currency_jsonarray1;
	    JSONObject login_jsonobj,login_Error,reset_jsonobj,currency_jsonobj,currency_jsonobj1;
	    String status,reset_status,currency_status1;
	    String  currencycode1;
	TextView longterm,currency,amenities,back;
	ImageButton rooms_beds,description;
	String imaage,address1,city,state,country;
	String room_id;
	Button back1;
	Button deactivate;
	String dis,dis1,dis2,dis3,dis4;
	String value=null,value2;
	String userid;
	String guest,space,guest_interact,around,other,house,neighbour;
	String image1,resize;
	TextView logout;
	RadioButton radiobutton;
	private ProgressDialog pDialog;
	String latitude1,longitude1;
	String currencycodee,currencyy1;
	ImageView imageView10;
	TextView TextView;
	Long position1;
	ImageView close;
	TextView select;
	private ArrayList<NavDrawerItem> navDrawerItems;
	
	/*public Setting_currency() {
		// TODO Auto-generated constructor stub
	}*/

	/** Called when the activity is first created. */
	ObjectItem rowitem;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.setting_currency);
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
		
		first=i.getStringExtra("first");
		second=i.getStringExtra("second");
		third=i.getStringExtra("third");
		fourth=i.getStringExtra("fourth");
		last=i.getStringExtra("last");
		
		//String latitude1,longitude1;

		latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
		
		/*optional.putExtra("latitude", latitude1);
		optional.putExtra("longitude",longitude1);*/
		
	value=i.getStringExtra("value");
	value2=i.getStringExtra("amenities");

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
	
	 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
	 userid = sharedPreferences2.getString("userid", null);
	currencycodee =  sharedPreferences2.getString("currenycode", null);
      currencyy1 = sharedPreferences2.getString("currency1", null);
     // position1 = sharedPreferences2.getLong("position", 0);
      System.out.println("llllllllllllllllllll" + currencyy1);
      currency=(TextView)findViewById(R.id.TextView9);
      //imageView10 = (ImageView)findViewById(R.id.imageView10);
      TextView = (TextView)findViewById(R.id.TextView3);
      if ((currencycodee!=null)&& (currencyy1!=null))
    		  {
    	  currency.setText(currencyy1 + "(" + currencycodee + ")");
      }
      else
      {
    	  currency.setText("USD($)");
      }
	
	
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
        currency1= sharedPreferences1.getString("currency1", null);
        
        System.out.println("shared preferences"+space);
        System.out.println("shared preferences"+guest);
        System.out.println("shared preferences"+guest_interact);
        System.out.println("shared preferences"+neighbour);
        System.out.println("shared preferences"+around);
        System.out.println("shared preferences"+house);
        System.out.println("shared preferences"+other);
        System.out.println("********************"+currency1);
        logout=(TextView)findViewById(R.id.TextView4);
    	if(userid==null)
     	{
    		logout.setText("LogIn or SignUp");
     	}
    	else if(userid!=null)
    	{
    		logout.setText("Log Out");
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
		 
	    //longterm=(TextView)findViewById(R.id.TextView8);
		
		 //rooms_beds=(ImageButton)findViewById(R.id.imageButton3);
		 //amenities=(TextView)findViewById(R.id.TextView10);
		 //back=(TextView)findViewById(R.id.TextView11);
		 back1=(Button)findViewById(R.id.imageBack);
		
		 
		 logout.setOnClickListener(new View.OnClickListener() {
			 @Override
				public void onClick(View v) {
				 switch(logout.getText().toString())
					{
					case "LogIn or SignUp":
						Intent i=new Intent(Setting_currency.this,MainActivity.class);
						startActivity(i);
						finish();
						break;
					
					case "Log Out":
						
						final Dialog dialog1 = new Dialog(Setting_currency.this);
						dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog1.setContentView(R.layout.logout);

						TextView logout= (TextView) dialog1.findViewById(R.id.textView2);
						TextView cancel = (TextView) dialog1.findViewById(R.id.textView3);
						
						logout.setOnClickListener(new View.OnClickListener() {
	
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								SharedPreferences sp = PreferenceManager
										.getDefaultSharedPreferences(Setting_currency.this);
								Editor edit = sp.edit();
								edit.clear();
								edit.commit();
								clearApplicationData();
								loadSavedPreferences();
								finish();
								System.exit(0);
								finish();  // Call finish here.
							}
							});
						cancel.setOnClickListener(new View.OnClickListener() {
	
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog1.cancel();
							}
						});
						dialog1.show();

						Window window = dialog1.getWindow();
						window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

					break;
					}
			 }
		 });
		 
		 //deactivate=(Button)findViewById(R.id.button1);
		 //description=(ImageButton)findViewById(R.id.imageButton2);
		 
         /*currency.setText(currency_status1);
		 
		 System.out.print("currency_status1 view currency"+ currency_status1);
		 
		 if(currency_status1.equals("")||currency_status1.equals("null")||currency_status1=="null"||currency_status1==null)
		 {
			 currency.setText("None"); 
			 
			 System.out.print("inside if condition view currency"+ currency_status1);
		 }

		//currency1=currency.toString();
		 System.out.print("after if condition view currency"+ currency1);*/
		 //currency.setText(currency1 + "(" + currencycode + ")");
		 
		 
	    back1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		         Intent reset=new Intent(Setting_currency.this,Swipe_tabs.class);
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
			
  					
                     startActivity(reset);
                     finish();
							
				
				
				
			}
		});
	    
	   /* if(currency1==null)
		{
			currency.setText("USD");
		}
	    else
	    {
	    	currency.setText(currency1);
	    }*/
	  
	    
TextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				  /* final   Dialog d1 = new Dialog(Optional_details.this);
                   d1.setContentView(R.layout.select_currency);
                   d1.setTitle("Select currency");
                   d1.show();
                   */
				
				 boolean processClick=true;
				 
				   if (isOnline(Setting_currency.this)) {
					   TextView.setEnabled(false);
						TextView.setClickable(false);
				//setcurrency();
				(new TestAsync()).execute();
					   }
					 
				   
				 
					   else
				     {
			    		Toast.makeText(Setting_currency.this, "Check your internet connection",Toast.LENGTH_SHORT).show();
			     }
				  
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
	
				
				
		
				 
				   if (isOnline(Setting_currency.this)) {
					   currency.setEnabled(false);
						currency.setClickable(false);
				//setcurrency();
				(new TestAsync()).execute();
					   }
					 
				   
				 
					   else
				     {
			    		Toast.makeText(Setting_currency.this, "Check your internet connection",Toast.LENGTH_SHORT).show();
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
	 public class ViewHolder {
		 
	        TextView currencycode,symbol;
	        RadioButton radiobutton;
	    	/*ImageView close;
	    	TextView select;*/
	        
	        
	    }
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {

		 ViewHolder holder = null;
		   
		  rowitem = getItem(position);
			
		 LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	     if(convertView==null){
	    
 
	         convertView = inflater.inflate(R.layout.select_currency, null);
	       
	
	         holder = new ViewHolder();
	         
	            holder.currencycode = (TextView) convertView.findViewById(R.id.TextView1);
	            holder.symbol = (TextView) convertView.findViewById(R.id.TextView2);
	            holder.radiobutton = (RadioButton) convertView.findViewById(R.id.radioButton1);
	            /*holder.close = (ImageView) convertView.findViewById(R.id.s_imageView1B);
	            holder.select = (TextView) convertView.findViewById(R.id.currencyTextViewT);*/
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
	    	    
	    	      	    
	    	    
	    	    System.out.println("after code  "+default1[position]);
	    	    
	    	    SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(mContext);
	    		 userid = sharedPreferences2.getString("userid", null);
	    		currencycodee =  sharedPreferences2.getString("currenycode", null);
	    	      currencyy1 = sharedPreferences2.getString("currency1", null);
	    	    if(currencycode[position].equals(currencyy1))
	    	    {
	    	    	radiobutton.setChecked(true);
	    	    }
	    	    
	    	    String input = currency.getText().toString();
	    	    
	    	    System.out.println("input" +input);
	    	    
	    	  
	    	    
	    	   
	    	
	    	    }
	     TextViewItem.setText(rowitem.currencycode);
	     TextViewItem1.setText(rowitem.symbol);
	     
	     if(currencyy1==null)
			{
				currency.setText("USD($)");
				String input = currency.getText().toString();
				  if(input.equals("USD($)"))
		     	    {
		     	    	if(default1[position].equals("1"))
		     	    	{
		     	    	radiobutton.setChecked(true);
		     	    	}
		     	    }
			}
		    else
		    {
		    	//currency.setText(currency1 + "(" + currencycode + ")");
		    }

	   
 	    
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
	         
	         radiobutton = (RadioButton)view.findViewById(R.id.radioButton1);
	         
	        
	         
	         int pos = parent.getPositionForView(view);
	         
	         
	         System.out.println("kkkkkkkkkkkkkkkkkkk" +pos);
	       
	     String currency1 =TextViewItem.getText().toString();
	     String  currencycode =TextViewItem1.getText().toString();
	     
	
	     
	     SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         Editor editor = sharedPreferences.edit();
         editor.putString("currenycode", currencycode);
         editor.putString("currency1", currency1);
     
         editor.commit();
         System.out.println("********"+currency1);
         System.out.println("********"+currencycode);
         
	     
	       
	        radiobutton.setChecked(false);
/*	        currency.setText(currency1);*/
	      
	        currency.setText(currency1 + "(" + currencycode + ")");
	        System.out.println("symbol" +symbol);
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
	        

	        ((Setting_currency) context).alertDialogStores.cancel();

	}
	
	}
	@Override
	public void onBackPressed()
	{
		
	}
	public void clearApplicationData() {
	    File cache = getCacheDir();
	    File appDir = new File(cache.getParent());
	    if (appDir.exists()) {
	        String[] children = appDir.list();
	        for (String s : children) {
	            if (!s.equals("lib")) {
	                deleteDir(new File(appDir, s));
	                Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
	                Intent i=new Intent(Setting_currency.this,Swipe_tabs.class);
	                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	               // clearApplicationData();
	                startActivity(i);
	                finish();
	            }
	        }
	    }
	}
	public static boolean deleteDir(File dir) {
	    if (dir != null && dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return dir.delete();
	}
	private void loadSavedPreferences() {    
	    //User has successfully logged in, save this information
	    // We need an Editor object to make preference changes.
	   
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
	    SharedPreferences.Editor editor = sp.edit();
	    editor.clear();
	    editor.commit();

		}
	public void setcurrency()
	{
		
		
		//progressDialog.show();
		
    /*	progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	progressDialog.setProgress(0);
    	progressDialog.show();*/
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
                                
             ArrayAdapterItem adapter = new ArrayAdapterItem(Setting_currency.this, R.layout.select_currency, ObjectItemData);
                                      
             ListView listViewItems=new ListView(Setting_currency.this);
             listViewItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listViewItems.getCheckedItemCount();
            
            System.out.println("cheked item count in currency "+listViewItems.getCheckedItemCount());
           /* 
            if (currency1!=null)
            {
            	listViewItems.(currency1);
            }
            */
         
            
             listViewItems.clearFocus();
             listViewItems.setAdapter(adapter);                          
           listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());                    
                     alertDialogStores = new AlertDialog.Builder(Setting_currency.this)
                     .setView(listViewItems)
                     
                     .show();  
                     
                     
                  
                     

                   /*  final AlertDialog.Builder builder = new AlertDialog.Builder(Setting_currency.this);

                     builder.setTitle("Choose a country");
                     builder.setSingleChoiceItems(items, selectedindex, new DialogInterface.OnClickListener()

                             {
                                 public void onClick(DialogInterface dialog, int item) {
                                     inputCountry.setText(items[item]);
                                     selectItem = item;
                                     dialog.cancel();

                                 }

                             });*/
                    
        
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

	
	
	class TestAsync extends AsyncTask<Void, Integer, String>
	{
	    protected void onPreExecute (){
	        Log.d("PreExceute","On pre Exceute......");
	         pdialog=new ProgressDialog(Setting_currency.this);
            pdialog.setCancelable(true);
            pdialog.setMessage("Loading ....");
            pdialog.show();
	    }

	    protected String doInBackground(Void...arg0) {
	    	 
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
	                     System.out.println("type of currency symbol"+Arrays.asList(currencycode).indexOf(currencyy1));
	                     System.out.println("type of currency symbol"+currencyy1);
	                   	      
	                   ;
	                     
	                     ObjectItem item=new ObjectItem(id[i],currencycode[i],symbol[i],default1[i]);
	                     ObjectItemData.add(item);
	                                      
	                     }
	                  
	                 
	                  /*int a = Arrays.asList(currencycode).indexOf(currencyy1);
	                     System.out.println ("aaaaaaaaaa" +a);
	                     
	                     if (Arrays.asList(currencycode).equals(currencyy1))
	                    		 {
	                    	 radiobutton.setChecked(true);
	                    		 }*/
	                  
	                 
	                    
	                  // our adapter instance
	                  // ListView listViewItems = (ListView)findViewById(R.id.currency_listview);

	                  // create a new ListView, set the adapter and item click listener
	    		 
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
	    	
     
	        return "You are at PostExecute";
	    }

	 

	    protected void onPostExecute(String result) {
	    	
	    	
	    	pdialog.dismiss();
	    	
	        ArrayAdapterItem adapter = new ArrayAdapterItem(Setting_currency.this, R.layout.select_currency, ObjectItemData);
	      
             listViewItems=new ListView(Setting_currency.this);
             View header = getLayoutInflater().inflate(R.layout.select_header, listViewItems, false);
        
             
             
             listViewItems.addHeaderView(header, null, false);
             ImageView close = (ImageView) header.findViewById(R.id.s_imageView1B);
             
          
             
             
            listViewItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
           listViewItems.getCheckedItemCount();
           
           System.out.println("cheked item count in currency "+listViewItems.getCheckedItemCount());
          /* 
           if (currency1!=null)
           {
           	listViewItems.(currency1);
           }
           */
        
           
            //listViewItems.clearFocus();
            listViewItems.setAdapter(adapter);                          
          listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());                    
                    alertDialogStores = new AlertDialog.Builder(Setting_currency.this)
                  
                    .setView(listViewItems)
                    
                    .show();    
                    
                    close.setOnClickListener(new OnClickListener()
    	            {
    	                @Override
    	                public void onClick(View v)
    	                {
    	                	alertDialogStores.dismiss();
    	                }
    	            });
                    currency.setEnabled(true);
        	 		currency.setClickable(true);  
        	 	   TextView.setEnabled(true);
        	 	  TextView.setClickable(true); 
	    	  }             
           
	                /*  ((Builder) alertDialogStores).setSingleChoiceItems(currencycode, selectItem, new DialogInterface.OnClickListener()

	                          {
	                              public void onClick(DialogInterface dialog, int item) {
	                                  inputCountry.setText(items[item]);
	                                  selectItem = item;
	                                  dialog.cancel();

	                              }

	                          });*/
                   
	   
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


