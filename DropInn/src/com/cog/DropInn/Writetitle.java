package com.cog.DropInn;
import android.text.Editable;
import android.text.TextWatcher;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Writetitle extends Activity {
	
	String wt,wt1,dis,dis1,dis2,dis3,city,state,country;
	private EditText writetitle;
	private TextView counttext;
	static int MAX_COUNT = 35;
	String userid;
	String room_id;
	String first,second,third,fourth,last;
	ImageView done;
	String image1,text,resize,text1;
	String latitude1,longitude1;
	String step,check;
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_writetitle);
		getActionBar().hide();
		writetitle=(EditText)findViewById(R.id.editText1);
		
		done=(ImageView)findViewById(R.id.imageView2);
		counttext=(TextView)findViewById(R.id.TextView4);
		wt=writetitle.getText().toString();
		
		Intent i=getIntent();
		text=i.getStringExtra("text");
		writetitle.setText(text);
		writetitle.setSelection(writetitle.getText().length());
		System.out.println("text value"+text);
		text1=i.getStringExtra("text1");
		wt=text1;
		step=i.getStringExtra("step");
		check=i.getStringExtra("check");
		//userid=i.getStringExtra("userid");
		dis=i.getStringExtra("image");
		image1=i.getStringExtra("image1");
		resize=i.getStringExtra("resize");
		dis1=i.getStringExtra("summary");
		dis2=i.getStringExtra("price");
		dis3=i.getStringExtra("addresss");
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country=i.getStringExtra("country");
		room_id=i.getStringExtra("room_id");
		wt1=i.getStringExtra("title");
		
		first=i.getStringExtra("first");
		second=i.getStringExtra("second");
		third=i.getStringExtra("third");
		fourth=i.getStringExtra("fourth");
		last=i.getStringExtra("last");
		
		if (wt1!=null)
		{
			writetitle.setText(wt1);
		}
		 
		//String latitude1,longitude1;
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);

		latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
		
		/*optional.putExtra("latitude", latitude1);
		optional.putExtra("longitude",longitude1);*/
		 //System.out.println("wt"+wt);
		// Init Widget GUI
	       
	 
	        // Attached Listener to Edit Text Widget
	        writetitle.addTextChangedListener(new TextWatcher() {
	 
	            public void onTextChanged(CharSequence s, int start, int before, int count) {
	                // TODO Auto-generated method stub
	 
	            }
	 
	            public void beforeTextChanged(CharSequence s, int start, int count,
	                    int after) {
	                // TODO Auto-generated method stub
	 
	            }
	 
	            public void afterTextChanged(Editable s) {
	 
	                // Display Remaining Character with respective color
	            	
	                int count = MAX_COUNT - s.length();
	                counttext.setText(Integer.toString(count));
	                counttext.setTextColor(Color.parseColor("#FF1919"));
	                
	            }
	        });
	 
	 
	
	
		done.setOnClickListener(new View.OnClickListener() {
			 
			@SuppressLint("NewApi") @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				wt=writetitle.getText().toString();
				System.out.println("inside done in title=="+wt);
				if(wt.isEmpty())
				{
					System.out.println("inside if"+wt);
				
				}
				else
				{
					System.out.println("inside else if"+wt);
					
					text=wt;
					
					
				}
			
			System.out.println("wt"+wt);
			System.out.println("wt"+text);
			Intent back=new Intent(Writetitle.this,List_your_space.class);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writetitle.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			back.putExtra("write_title", wt);
			//back.putExtra("text", text);
			back.putExtra("text1", text1);
			back.putExtra("image", dis);
			back.putExtra("image1",image1);
			back.putExtra("resize",resize);
			back.putExtra("write_summary", dis1);
			back.putExtra("write_price", dis2);
			back.putExtra("address", dis3);
			back.putExtra("city", city);
			back.putExtra("state", state);
			back.putExtra("country", country);
			back.putExtra("room_id", room_id);
			back.putExtra("userid", userid);
			back.putExtra("first", first);
			back.putExtra("second", second);
			back.putExtra("third", third);
			back.putExtra("fourth", fourth);
			back.putExtra("last", last);
			back.putExtra("latitude", latitude1);
			back.putExtra("longitude",longitude1);
			back.putExtra("step",step);
			back.putExtra("check",check);
			 //System.out.println("inside"+wt);
			startActivity(back,bndlanimation);
			finish();
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