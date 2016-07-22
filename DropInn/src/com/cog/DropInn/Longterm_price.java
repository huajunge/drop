package com.cog.DropInn;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;








import com.cog.DropInn.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Longterm_price extends Activity {

	private static final String String = null;
	TextView symbol_weekly,symbol_monthly;
	EditText weekly,monthly;
	String user_id,week,month,currency_symbol;
	String Liveurl="";
	TextView perweek,permonth;
	ImageView done;
	
	String first,second,third,fourth,last, currency1;	
	 URL Login_Url,reset_Url;
	    String reader;
	    String login_inputline,reset_inputline;
	    String login_status;
	    JSONArray login_jsonarray,reset_jsonarray;
	    JSONObject login_jsonobj,login_Error,reset_jsonobj;
	    String status,reset_status;
	    String room_id;
	    ImageButton done1;
	    CheckBox box1,box2;
/*	    String value="ramesh";
*/	    String weeklyedit,monthlyedit;
	    String image1,resize;
	    String userid,dis,dis1,dis2,imaage,address1,city,state,country;
	    String latitude1,longitude1, currenycode,result;
		String step,check;
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.longterm_price);
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
		
		
		//String latitude1,longitude1;

		latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
		
		/*optional.putExtra("latitude", latitude1);
		optional.putExtra("longitude",longitude1);*/
		// currency=i.getStringExtra("currency");
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null);
         currenycode= sharedPreferences.getString("currenycode", null);
         /*
         SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
         Editor editor = sharedPreferences1.edit();
         editor.putString("currencysymbol", currenycode);
         editor.commit();*/
         //result=sharedPreferences.getString("result", null);
         //currency1= sharedPreferences.getString("currency1", null);

        System.out.println("CURRENCYCODE**********..."+currenycode);
        System.out.println("CURRENCY VALUES"+currency1);
		weekly=(EditText)findViewById(R.id.editText1);
		monthly=(EditText)findViewById(R.id.EditText01);
		symbol_weekly=(TextView)findViewById(R.id.TextView6);
		symbol_monthly=(TextView)findViewById(R.id.TextView01);
		perweek=(TextView)findViewById(R.id.TextView4);
		permonth=(TextView)findViewById(R.id.TextView5);
		box1=(CheckBox)findViewById(R.id.checkBox1);
		box2=(CheckBox)findViewById(R.id.checkBox2);		
		done=(ImageView)findViewById(R.id.imageView6);
		//done1=(ImageButton)findViewById(R.id.imageButton1);			
		
		perweek.setVisibility(View.GONE);
		permonth.setVisibility(View.GONE);
		weekly.setVisibility(View.GONE);
		monthly.setVisibility(View.GONE);
		symbol_weekly.setVisibility(View.GONE);
		symbol_monthly.setVisibility(View.GONE);
		
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		
		   try
           {	                    
          
        	//Login_Url = new URL("Liveurl+"user/longterm?roomid=6");
        	Login_Url = new URL(Liveurl+"user/longterm?roomid="+room_id);
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
                   
               
                     
                    for(int i1=0; i1 <login_jsonarray.length(); i1++)
                       {
                       
                       
                   login_jsonobj = login_jsonarray.getJSONObject(i1);
            
                  
                   week=login_jsonobj.getString("week");
                   month=login_jsonobj.getString("month");
                   currency_symbol=login_jsonobj.getString("common_currency");
                   currency_symbol = Html.fromHtml(currency_symbol).toString();
                   country=login_jsonobj.getString("country_symbol");
                   currenycode=login_jsonobj.getString("curreny_code");
                   //currency_symbol=c.getSymbol();
                 //Toast.makeText(getApplicationContext(), country, Toast.LENGTH_SHORT).show();
                 //Toast.makeText(getApplicationContext(), currency_symbol, Toast.LENGTH_SHORT).show();

               
                       }
                                                   
                    
                       System.out.println("id"+user_id);
                       System.out.println("id"+week); 
                       System.out.println("id"+month); 
                       //System.out.println("code"+currenycode);
                              	                  
       	                         	    
                       
                         
                             
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
		
		   System.out.println("currency_symbol in longterm price"+currency_symbol);
		   //weekly.setText(week);
		   weekly.setText(week, TextView.BufferType.EDITABLE);
		   monthly.setText(month, TextView.BufferType.EDITABLE);
		   symbol_weekly.setText(currency_symbol,TextView.BufferType.EDITABLE);
		   symbol_monthly.setText(currency_symbol,TextView.BufferType.EDITABLE);
           
		   if(currency_symbol==null)
		   {
			   symbol_weekly.setText("$");
			   symbol_monthly.setText("$");
		   }
		   
		   if(currenycode==null)
		   {
			   symbol_weekly.setText("$");
			   symbol_monthly.setText("$");
			   currenycode="$";
		   
		   }
		   else
		   {
			   symbol_weekly.setText(currenycode);
			   symbol_monthly.setText(currenycode);
			   //weekly.setText();
			   //monthly.setText();
		   
		   }
	
		   
		   
		   box1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					perweek.setVisibility(View.VISIBLE);
					weekly.setVisibility(View.VISIBLE);
					symbol_weekly.setVisibility(View.VISIBLE);
					
					
				}
			});
			
		box2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					permonth.setVisibility(View.VISIBLE);
					monthly.setVisibility(View.VISIBLE);
					symbol_monthly.setVisibility(View.VISIBLE);
					
					
				}
			});
		   
		   
		weekly.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {				
			
                //search.setVisibility(View.INVISIBLE);	
				if (s.length()>0)
				{
				
				}
				else 
				{
				
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
				
				 String x = s.toString();
					        if(x.startsWith("0"))
					        {
					            //your stuff here
					        	weekly.setText("");
					        }
				
			}
			

		});	
		
	monthly.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {				
			
                //search.setVisibility(View.INVISIBLE);	
				if (s.length()>0)
				{
				
				}
				else 
				{
				
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
				
				 String x = s.toString();
					        if(x.startsWith("0"))
					        {
					            //your stuff here
					        	monthly.setText("");
					        }
				
			}
			

		});	
		   
		   
		 			 
		   done.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("Done button checking the process");  
				 monthlyedit=monthly.getText().toString();
				  weeklyedit=weekly.getText().toString();	

				   System.out.println("weekly after edit"+weeklyedit);
				   System.out.println("monthly after edit"+monthlyedit);
				
					 if(weekly.getText().toString().equals("")|| weekly.getText().toString().equals("0"))
					 {
					  Toast.makeText(getApplicationContext(), "Please enter weekly price",Toast.LENGTH_SHORT).show();
					 }
				   if(monthly.getText().toString().equals("")|| monthly.getText().toString().equals("0"))
					 {
					  Toast.makeText(getApplicationContext(), "Please enter monthly price",Toast.LENGTH_SHORT).show();
					 }
				   
				     if((weeklyedit != null && !weeklyedit.isEmpty()))
				  {
				    	 
				    
					 if(weekly.getText().toString().equals(""))
					 {
					  Toast.makeText(getApplicationContext(), "Please enter weekly price",Toast.LENGTH_SHORT).show();
					 }
					 else 
					  {
						 if(Float.parseFloat(weeklyedit)<70 )
						 {
						// System.out.println("weekly after edit"+Integer.parseInt(weeklyedit));
						   
						
						  Toast.makeText(getApplicationContext(), "Please enter minimum "+currenycode+" 70 in Dollars",Toast.LENGTH_SHORT).show();
					  }
						 /*else{							 
						 
						 update();
					  }*/
						 }
				  
				 
				  if((monthlyedit != null && !monthlyedit.isEmpty()))
				  {
					 if(monthly.getText().toString().equals(""))
					 {
					  Toast.makeText(getApplicationContext(), "Please enter monthly price",Toast.LENGTH_SHORT).show();
					 }
					 else 
					  {
						 if(Float.parseFloat(monthlyedit)<=Float.parseFloat(weeklyedit) )
						 {
						 //System.out.println("monthly after edit"+Integer.parseInt(monthlyedit));
						   						
						  Toast.makeText(getApplicationContext(), "Please enter the monthly price more than weekly price",Toast.LENGTH_SHORT).show();
					  }
						/* else{							 
							 
							 update();
						  }*/
						
					  }
				  }
				  }
				if (!monthly.getText().toString().equals("") && monthlyedit != null && !monthlyedit.isEmpty() && weeklyedit != null && !weeklyedit.isEmpty() && !weekly.getText().toString().equals("") )
				{
					
					if (Float.parseFloat(monthlyedit)>Float.parseFloat(weeklyedit) &&Float.parseFloat(weeklyedit)>=70 )
					{
					 update();
				}
				}
			}
		});

		   
		   
		   
	}
	private java.lang.String URL(java.lang.String string2) {
		// TODO Auto-generated method stub
		return null;
	}
	private void update()
	{
		  try
          { 
               
         	             
          //reset_Url = new URL("Liveurl+"user/edit_longterm?roomid=&week="+weeklyedit+"&month="+monthlyedit+"&currency="+currency_symbol);
			  reset_Url = new URL(Liveurl+"user/edit_longterm?roomid="+room_id+"&week="+weeklyedit+"&month="+monthlyedit+"&currency="+currenycode); 
              System.out.println("update url"+reset_Url);
			  
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
                   
                  if(reset_status.matches("Updated Successfully."))
                  {
                      
                	  System.out.println("inside intent");
                      Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
                      toast1.setGravity(Gravity.CENTER, 0, 0);
                      toast1.show();
             
                      Intent reset=new Intent(Longterm_price.this,Optional_details.class);
                      
                    reset.putExtra("room_id",room_id);
/*                  reset.putExtra("value", value);
*/                  reset.putExtra("room_id",room_id);
      				reset.putExtra("userid",userid);
      				reset.putExtra("image", imaage);      				
      				reset.putExtra("image1",image1);
      				reset.putExtra("resize",resize);
      				reset.putExtra("title",dis);				
      				reset.putExtra("summary", dis1);
      				reset.putExtra("price",dis2);
      				reset.putExtra("city", city);
      				reset.putExtra("state", state);
      				reset.putExtra("country", country);
      				reset.putExtra("addresss",address1);
      				reset.putExtra("latitude", latitude1);
      				reset.putExtra("longitude",longitude1);
      				reset.putExtra("first", first);
      				reset.putExtra("second", second);
      				reset.putExtra("third", third);
      				reset.putExtra("fourth", fourth);
      				reset.putExtra("last", last);
      				reset.putExtra("weeklyedit", weeklyedit);
      				reset.putExtra("monthlyedit", monthlyedit);
      				reset.putExtra("currenycode", currenycode);
      				reset.putExtra("step", step);
      				reset.putExtra("check", check);
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