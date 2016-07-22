package com.cog.DropInn;

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

import android.app.Activity;
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
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Optional_amenities extends Activity {
	private static final Class<?> Optional_amenities = null;
	List<ObjectItem> ObjectItemData;
	int k=100;
	public String[] name = new String[k];
	public String[] id = new String[k];
	private TextView cCheckedItems;
	 AlertDialog alertDialogStores;
	  List<String> infos = new ArrayList<String>();
	 String Liveurl="";
	 URL Login_Url;
	 int len;
	 int count=0;
	 String c,c1;
	    String reader;
	    Button back;
	    ImageButton back1;
	    String login_inputline;
	    String login_status;
	    String imaage,address1,city,state,country,image1,resize,userid,dis,dis1,dis2;
	    JSONArray login_jsonarray;
	    JSONObject login_jsonobj,login_Error;
	    ListView listViewItems1;
	    //ObjectItem[] ObjectItemData;
	//TextView longterm,currency,rooms_beds,description,amenities;
	String room_id;
	String latitude1,longitude1,step,check;
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
		country=i1.getStringExtra("country");
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
		System.out.println("aminities "+country);
		System.out.println("aminities "+city);
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		
		setContentView(R.layout.optional_amenities);
		 if( Build.VERSION.SDK_INT >= 9){
             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
             StrictMode.setThreadPolicy(policy);
      }
		  
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Liveurl = sharedPreferences.getString("liveurl", null);
        
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
                    
                  	              
                    ObjectItem item = new ObjectItem(name[i],id[i]);
                    ObjectItemData.add(item);
                }
               
                 // our adapter instance
                 ArrayAdapterItem adapter = new ArrayAdapterItem(getApplicationContext(), R.layout.display_amenities, ObjectItemData);

                 // create a new ListView, set the adapter and item click listener
                 listViewItems1 = (ListView)findViewById(R.id.listView10);
                 listViewItems1.setAdapter(adapter);
                 adapter.notifyDataSetChanged();
                 listViewItems1.setOnItemClickListener(new OnItemClickListenerListViewItem());
        
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

	public class ObjectItem {

		public String  name,id;
	    // constructor
	    public ObjectItem(String name,String id) {
	        
	    	this.name=name;
	    	this.id=id;
	    	 }
	    

	}


	//here's our beautiful adapter
	public class ArrayAdapterItem extends ArrayAdapter<ObjectItem> {
		private ArrayList<ObjectItem> ObjectItemData;
	 Context mContext;
	 int layoutResourceId;
	 //ObjectItem data[] = null;

	 public ArrayAdapterItem(Context mContext, int layoutResourceId, List<ObjectItem> ObjectItemData) {

	     super(mContext, layoutResourceId, ObjectItemData);
	     this.ObjectItemData = new ArrayList<ObjectItem>();
	     this.ObjectItemData.addAll(ObjectItemData);
	     //this.layoutResourceId = layoutResourceId;
	     this.mContext = mContext;
	     
	 }

	 private class ViewHolder {
	        
	        TextView name;
	        CheckBox id;
	        
	        
	        

	        
	    }
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {

		 
		 ViewHolder holder = null;
		   
		  ObjectItem rowitem = getItem(position);
		/*   len = listViewItems1.getCount();
		  final SparseBooleanArray checked = listViewItems1.getCheckedItemPositions();*/
			
		 LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	     if(convertView==null){
	         // inflate the layout
	    	 convertView = inflater.inflate(R.layout.display_amenities, null);
	         holder = new ViewHolder();
	         holder.name = (TextView) convertView.findViewById(R.id.amenities);
	         holder.id=(CheckBox)convertView.findViewById(R.id.check);
	         convertView.setTag(holder);
	     }else
	     {
	    	 holder = (ViewHolder) convertView.getTag();
	     }

	    
	     holder.name.setText(rowitem.name);
	     
	    holder.id.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
		
						
						
							
					if (((CheckBox) v).isChecked()) {
						count++;
						System.out.println("count is"+count);
						StringBuilder sb=new StringBuilder();
						sb.append("");
						sb.append(count);
						long[] i=listViewItems1.getCheckedItemIds();
						
					  	System.out.println("Count of Total List is "+i);
					
						//sb.append(name);
					
						c=sb.toString();
					
						System.out.println("Check if String"+c);
						//Intent i=new Intent(Optional_amenities.this,Optional_details.class);
						///i.putExtra("amenities", c);	
						//startActivity(i);
					}else
					{
						count--;
						System.out.println("Count IS"+count);

					}
				}
			});
	     // object item based on the position

	    back=(Button)findViewById(R.id.button1);
	    back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Optional_amenities.this,Optional_details.class);
				i.putExtra("amenities", c);	
				i.putExtra("room_id", room_id);			
				i.putExtra("userid",userid);
				i.putExtra("image", imaage);
				i.putExtra("image1", image1);
				i.putExtra("resize", resize);
				i.putExtra("title",dis);				
				i.putExtra("summary", dis1);
				i.putExtra("price",dis2);
				i.putExtra("city", city);
				i.putExtra("state", state);
				i.putExtra("country", country);
				i.putExtra("addresss",address1);
				i.putExtra("latitude", latitude1);
				i.putExtra("longitude",longitude1);
				i.putExtra("step", step);
				i.putExtra("check",check);
				startActivity(i);
				finish();
				
			}
		});
	    back1=(ImageButton)findViewById(R.id.imageButton2);
	    back1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Optional_amenities.this,Optional_details.class);
				i.putExtra("amenities", c);	
				i.putExtra("room_id", room_id);			
				i.putExtra("userid",userid);
				i.putExtra("image", imaage);
				i.putExtra("image1", image1);
				i.putExtra("resize", resize);
				i.putExtra("title",dis);				
				i.putExtra("summary", dis1);
				i.putExtra("price",dis2);
				i.putExtra("city", city);
				i.putExtra("state", state);
				i.putExtra("country", country);
				i.putExtra("addresss",address1);
				i.putExtra("latitude", latitude1);
				i.putExtra("longitude",longitude1);
				i.putExtra("step", step);
				i.putExtra("check",check);
				startActivity(i);
				finish();
				
			}
		});
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
	    	listViewItems1.setItemsCanFocus(true);
	    
	    	  
	    	long[] i=listViewItems1.getCheckedItemIds();
	    	System.out.println("Count of Total List is "+i);
	    	
	    	
	    		
	    CheckBox check=(CheckBox)view.findViewById(R.id.check);
	    	 
	    		    	
	    	 Toast toast = Toast.makeText(getApplicationContext(),
	    	            "Item " + (position + 1) + ": " + ObjectItemData.get(position),
	    	            Toast.LENGTH_SHORT);
	    	 System.out.println("checktoast"+ObjectItemData.get(position));
	    	        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
	    	        toast.show();
	    	        
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