package com.cog.DropInn;

import com.cog.DropInn.Search.PlacesTask;

import android.text.Editable;
import android.text.TextWatcher;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Writeprice extends Activity {
    String wp,tit,sum,ad,im,city,state,country,price1;
    EditText writeprice;
    ImageView done2;
    String room_id;
    String userid;
    String image1,resize;
    String first,second,third,fourth,last;
    String latitude1,longitude1;
    String step,check;
    String currency1;
    TextView cur;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_writeprice);
		getActionBar().hide();
		writeprice=(EditText)findViewById(R.id.editText1);
		done2=(ImageView)findViewById(R.id.imageView3);
		cur=(TextView)findViewById(R.id.imageView2);
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		im=i.getStringExtra("image");
		step=i.getStringExtra("step");
		check=i.getStringExtra("check");
		image1=i.getStringExtra("image1");
		resize=i.getStringExtra("resize");
		tit=i.getStringExtra("title");
		sum=i.getStringExtra("summary");
		ad=i.getStringExtra("addresss");
		price1=i.getStringExtra("price");
		wp=price1;
		writeprice.setText(wp);
		writeprice.setSelection(writeprice.getText().length());
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country=i.getStringExtra("country");
		room_id=i.getStringExtra("room_id");
		first=i.getStringExtra("first");
		second=i.getStringExtra("second");
		third=i.getStringExtra("third");
		fourth=i.getStringExtra("fourth");
		last=i.getStringExtra("last");
		
		if (price1!=null)
		{
			writeprice.setText(price1);
		}
		
		//String latitude1,longitude1;
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		 currency1= sharedPreferences2.getString("currency1", null);
		 
		 if (currency1==null)
		 {
			 cur.setText("$");
			 currency1="$";
		 }
		 else{
			 cur.setText(currency1);
		 }

		latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
		
		/*optional.putExtra("latitude", latitude1);
		optional.putExtra("longitude",longitude1);*/
		System.out.println("price page "+ad);
		System.out.println("price page "+city);
		System.out.println("price page "+state);
		System.out.println("price page "+country);
		
		writeprice.addTextChangedListener(new TextWatcher() {
			
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
					        	writeprice.setText("");
					        }
				
			}
			

		});	
		done2.setOnClickListener(new View.OnClickListener() {
		
		
		
			@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			wp=writeprice.getText().toString();
			
			System.out.println("wp" +wp);
			String euro="\u00a3";
			/*if(writeprice.getText().toString().equals(""))
			{
				//Toast.makeText(getApplicationContext(), "You must enter a price", Toast.LENGTH_LONG).show();
			}
			else{*/
			if(wp.equals(""))
			{
			Intent back2=new Intent(Writeprice.this,List_your_space.class);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writeprice.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			back2.putExtra("write_price", wp);
			back2.putExtra("image", im);
			back2.putExtra("image1",image1);
			back2.putExtra("resize",resize);
			back2.putExtra("write_title", tit);
			back2.putExtra("write_summary", sum);
			back2.putExtra("address", ad);
			back2.putExtra("room_id", room_id);
			back2.putExtra("userid",userid);
			back2.putExtra("city", city);
			back2.putExtra("state",state);
			back2.putExtra("country", country);
			back2.putExtra("first", first);
			back2.putExtra("second", second);
			back2.putExtra("third", third);
			back2.putExtra("fourth", fourth);
			back2.putExtra("last", last);
			back2.putExtra("latitude", latitude1);
			back2.putExtra("longitude",longitude1);
			back2.putExtra("step",step);
			back2.putExtra("check",check);
			startActivity(back2,bndlanimation);
			finish();
			}
			if(!wp.equals(""))
			{
			float finalValue=Float.parseFloat(wp);
			
			
			 if(finalValue<10)
				{
					Toast.makeText(getApplicationContext(), "Your minimum price is "+currency1+"10"+" The maximum is "+currency1+" 6494", Toast.LENGTH_LONG).show();
					
				}
			
			 else if(finalValue<6495 && finalValue>=10)
			{
			Intent back2=new Intent(Writeprice.this,List_your_space.class);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writeprice.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			back2.putExtra("write_price", wp);
			back2.putExtra("image", im);
			back2.putExtra("image1",image1);
			back2.putExtra("resize",resize);
			back2.putExtra("write_title", tit);
			back2.putExtra("write_summary", sum);
			back2.putExtra("address", ad);
			back2.putExtra("room_id", room_id);
			back2.putExtra("userid",userid);
			back2.putExtra("city", city);
			back2.putExtra("state",state);
			back2.putExtra("country", country);
			back2.putExtra("first", first);
			back2.putExtra("second", second);
			back2.putExtra("third", third);
			back2.putExtra("fourth", fourth);
			back2.putExtra("last", last);
			back2.putExtra("latitude", latitude1);
			back2.putExtra("longitude",longitude1);
			back2.putExtra("step",step);
			back2.putExtra("check",check);
			startActivity(back2,bndlanimation);
			finish();
			}
		
			
			else
				{
				Toast.makeText(getApplicationContext(), "Your price is too high "+euro+"The maximum is"+euro+" 6494", Toast.LENGTH_LONG).show();
				
                
				}
			}
			}
			});
	}

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