package com.cog.DropInn;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Writesummary extends Activity {
	String ws,tit,im,dis,dis1,dis2,city,state,country,ws1;
	EditText writesummary;
	TextView counttext;
	ImageView done1;
	static int MAX_COUNT = 35;
	String room_id;
	String userid,text;
	String image1,resize;
	String first,second,third,fourth,last,text1;
	String latitude1,longitude1;
	String step,check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_writesummary);
		getActionBar().hide();
		writesummary=(EditText)findViewById(R.id.editText1);
		counttext=(TextView)findViewById(R.id.TextView4);
		done1=(ImageView)findViewById(R.id.imageView2);
		ws=writesummary.getText().toString();
		Intent i=getIntent();
		text1=i.getStringExtra("text1");
		text=i.getStringExtra("text");
		ws=text;
		writesummary.setText(text1);
		writesummary.setSelection(writesummary.getText().length());
		System.out.println("text value"+text1);
		step=i.getStringExtra("step");
		check=i.getStringExtra("check");
		//userid=i.getStringExtra("userid");
		im=i.getStringExtra("image");
		image1=i.getStringExtra("image1");
		resize=i.getStringExtra("resize");
		tit=i.getStringExtra("title");
		ws1=i.getStringExtra("summary");
		dis=i.getStringExtra("price");
		
		dis1=i.getStringExtra("addresss");
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country=i.getStringExtra("country");
		room_id=i.getStringExtra("room_id");
		
		first=i.getStringExtra("first");
		second=i.getStringExtra("second");
		third=i.getStringExtra("third");
		fourth=i.getStringExtra("fourth");
		last=i.getStringExtra("last");
		
		if (ws1!=null)
		{
			writesummary.setText(ws1);
		}
		 
		
		//writesummary.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		
		//String latitude1,longitude1;
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);

		latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
		
		/*optional.putExtra("latitude", latitude1);
		optional.putExtra("longitude",longitude1);*/
		System.out.println("summary"+tit);
		writesummary.addTextChangedListener(new TextWatcher() {
			 
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
		done1.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi") @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			//ws=writesummary.getText().toString();
			ws=writesummary.getText().toString();
			System.out.println("inside done in title=="+ws);
			if(ws.isEmpty())
			{
				System.out.println("inside if"+ws);
			
			}
			else
			{
				System.out.println("inside else if"+ws);
				
				text1=ws;
				
				
			}
			Intent back1=new Intent(Writesummary.this,List_your_space.class);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writesummary.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			back1.putExtra("write_summary", ws);
			System.out.println("sri" +ws);
			//back1.putExtra("text1", text1);
			back1.putExtra("text", text);
			back1.putExtra("image", im);
			back1.putExtra("image1",image1);
			back1.putExtra("resize",resize);
			back1.putExtra("write_title", tit);
			back1.putExtra("write_price", dis);
			back1.putExtra("address", dis1);
			back1.putExtra("room_id", room_id);
			back1.putExtra("userid",userid);
			back1.putExtra("city", city);
			back1.putExtra("state", state);
			back1.putExtra("country", country);
			back1.putExtra("first", first);
			back1.putExtra("second", second);
			back1.putExtra("third", third);
			back1.putExtra("fourth", fourth);
			back1.putExtra("last", last);
			back1.putExtra("latitude", latitude1);
			back1.putExtra("longitude",longitude1);
			back1.putExtra("step",step);
			back1.putExtra("check",check);
			startActivity(back1,bndlanimation);
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