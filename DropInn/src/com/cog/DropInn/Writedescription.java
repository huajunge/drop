package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;















import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Writedescription extends Activity {
	TextView t01,t02,t03,t04,t05,t06,t08,t09,t2,t3,t9,t8,t10,t11;
	ImageView done;
	String des_space,des_guest,guest_interact,guest_interact1,neig_overview,des_guestint,desc_overview,desc_around,desc_other;
	String reader;
    String login_inputline;
    String login_status;
    JSONArray login_jsonarray;
    JSONObject login_jsonobj,login_Error;
    String userid,diss,dis11,dis22,imaage,address1,city,state,country;
    String Liveurl="";
    String getspace,getguest,guestinteract,guestneighbour,getother,getrules;
    String space1,guests_info,interaction,overview1,getting_around,other_thing,house_rule1,desc_houserule;
    String space,guest,interact,neighbour,around,rules,neig_arround;
	URL Login_Url;
	ProgressDialog pDialog;
	String step,check;
	 
	String latitude1,longitude1;
	protected static final String TAG = null;
	String first,second,third,fourth,last;
	String room_id,guestinfo,overview,getaround,other,house;
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_writedescription);
		getActionBar().hide();
		
		
		t2=(TextView)findViewById(R.id.TextView2);
		t3=(TextView)findViewById(R.id.TextView3);
		t01=(TextView)findViewById(R.id.TextView01);
		t02=(TextView)findViewById(R.id.TextView02);
		t03=(TextView)findViewById(R.id.TextView03);
		t04=(TextView)findViewById(R.id.TextView04);
		t05=(TextView)findViewById(R.id.TextView05);
		t06=(TextView)findViewById(R.id.TextView06);
		t08=(TextView)findViewById(R.id.TextView08);
		t09=(TextView)findViewById(R.id.TextView09);
		t8=(TextView)findViewById(R.id.TextView8);
		t9=(TextView)findViewById(R.id.TextView9);
		t10=(TextView)findViewById(R.id.TextView10);
		t11=(TextView)findViewById(R.id.TextView11);
		done=(ImageView)findViewById(R.id.done);
		
		pDialog = new ProgressDialog(this);
		pDialog.setCancelable(false);
		pDialog.show();
		pDialog.setContentView(R.layout.progress_dialog);
		
		Intent i=getIntent();
		step=i.getStringExtra("step");
		check=i.getStringExtra("check");
		des_space=i.getStringExtra("write_space");
		System.out.println("Get description Space"+des_space);
		des_guest=i.getStringExtra("write_guest");
		System.out.println("Get description Guest"+des_guest);
		des_guestint=i.getStringExtra("write_guestint");
		System.out.println("Get description Guest_Interaction"+des_guestint);
		desc_overview=i.getStringExtra("write_overview");
		System.out.println("Get description Guest_Interaction"+desc_overview);
		desc_around=i.getStringExtra("write_around");
		System.out.println("Get description Guest_Interaction"+desc_around);
		desc_other=i.getStringExtra("write_other");
		System.out.println("Get description Guest_Interaction"+desc_other);
		desc_houserule=i.getStringExtra("write_rules");
		System.out.println("Get description Guest_Interaction"+desc_houserule);
		//userid=i.getStringExtra("userid");
		room_id=i.getStringExtra("room_id");
		imaage=i.getStringExtra("image");
		diss=i.getStringExtra("title");
		dis11=i.getStringExtra("summary");
		dis22=i.getStringExtra("price");			
		address1=i.getStringExtra("addresss");			
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country=i.getStringExtra("country");
		first=i.getStringExtra("first");
		second=i.getStringExtra("second");
		third=i.getStringExtra("third");
		fourth=i.getStringExtra("fourth");
		last=i.getStringExtra("last");
		latitude1=i.getStringExtra("latitude");
	    longitude1=i.getStringExtra("longitude");
				
				
/*	    space1= i.getStringExtra("space");
*/		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Liveurl = sharedPreferences.getString("liveurl", null);
        
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
         
        view_description();

     
 	
        

         
        
         
         
         
       
t8.setOnClickListener(new View.OnClickListener() {
					
					@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent i1=new Intent(Writedescription.this,Desc_space.class);
						
						
					    i1.putExtra("room_id",room_id);
						i1.putExtra("userid",userid);
						i1.putExtra("image", imaage);
						i1.putExtra("title",diss);				
						i1.putExtra("summary", dis11);
						i1.putExtra("price",dis22);
						i1.putExtra("city", city);
						i1.putExtra("state", state);
						i1.putExtra("country", country);
						i1.putExtra("addresss",address1);
						i1.putExtra("latitude", latitude1);
						i1.putExtra("longitude",longitude1);
						i1.putExtra("write_space", space1);
						i1.putExtra("write_guest", guest);
						i1.putExtra("write_guestint",guest_interact);
                        i1.putExtra("write_overview", neig_overview);
	                    i1.putExtra("write_around", neig_arround);
	                    i1.putExtra("write_other", other_thing);
	                    i1.putExtra("write_rules",house_rule1);
	                    i1.putExtra("step",step);
	                    i1.putExtra("check",check);
						Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
						startActivity(i1,bndlanimation);
						finish();
					}
				});
				
				t9.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i2=new Intent(Writedescription.this,Desc_guest.class);
						
						    i2.putExtra("room_id",room_id);
							i2.putExtra("userid",userid);
							i2.putExtra("image", imaage);
							i2.putExtra("title",diss);				
							i2.putExtra("summary", dis11);
							i2.putExtra("price",dis22);
							i2.putExtra("city", city);
							i2.putExtra("state", state);
							i2.putExtra("country", country);
							i2.putExtra("addresss",address1);
							i2.putExtra("latitude", latitude1);
							i2.putExtra("longitude",longitude1);
							i2.putExtra("write_space", space1);
							i2.putExtra("write_guest", guest);
							i2.putExtra("write_guestint",guest_interact);
                            i2.putExtra("write_overview", neig_overview);
		                    i2.putExtra("write_around", neig_arround);
		                    i2.putExtra("write_other", other_thing);
		                    i2.putExtra("write_rules",house_rule1 );
		                    i2.putExtra("step",step);
		                    i2.putExtra("check",check);
						Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
						startActivity(i2,bndlanimation );
						finish();
					}
				});
				
				t02.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				Intent i3=new Intent(Writedescription.this,Desc_guestinteract.class);
				
				
				
				    i3.putExtra("room_id",room_id);
					i3.putExtra("userid",userid);
					i3.putExtra("image", imaage);
					i3.putExtra("title",diss);				
					i3.putExtra("summary", dis11);
					i3.putExtra("price",dis22);
					i3.putExtra("city", city);
					i3.putExtra("state", state);
					i3.putExtra("country", country);
					i3.putExtra("addresss",address1);
					i3.putExtra("longitude",longitude1);
					i3.putExtra("write_guestint",guest_interact);
					i3.putExtra("write_guest", guest);
					i3.putExtra("write_space", space1);
                    i3.putExtra("write_overview", neig_overview);
                    i3.putExtra("write_around", neig_arround);
                    i3.putExtra("write_other", other_thing);
                    i3.putExtra("write_rules",house_rule1 );
                    i3.putExtra("step",step);
                    i3.putExtra("check",check);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(i3,bndlanimation );
				finish();
			}
		});
				
				
				t04.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i4=new Intent(Writedescription.this,Desc_neighbour.class);
						  i4.putExtra("room_id",room_id);
							i4.putExtra("userid",userid);
							i4.putExtra("image", imaage);
							i4.putExtra("title",diss);				
							i4.putExtra("summary", dis11);
							i4.putExtra("price",dis22);
							i4.putExtra("city", city);
							i4.putExtra("state", state);
							i4.putExtra("country", country);
							i4.putExtra("addresss",address1);
							i4.putExtra("latitude", latitude1);
							i4.putExtra("longitude",longitude1);
							i4.putExtra("write_guestint",guest_interact);
							i4.putExtra("write_guest", guest);
							i4.putExtra("write_space", space1);
                            i4.putExtra("write_overview", neig_overview);
		                    i4.putExtra("write_around", neig_arround);
		                    i4.putExtra("write_other", other_thing);
		                    i4.putExtra("write_rules",house_rule1 );
		                    i4.putExtra("step",step);
		                    i4.putExtra("check",check);
						Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
						startActivity(i4,bndlanimation );
						finish();
					}
				});
				
				t06.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i5=new Intent(Writedescription.this,Desc_getaround.class);
						
						  i5.putExtra("room_id",room_id);
							i5.putExtra("userid",userid);
							i5.putExtra("image", imaage);
							i5.putExtra("title",diss);				
							i5.putExtra("summary", dis11);
							i5.putExtra("price",dis22);
							i5.putExtra("city", city);
							i5.putExtra("state", state);
							i5.putExtra("country", country);
							i5.putExtra("addresss",address1);
							i5.putExtra("latitude", latitude1);
							i5.putExtra("longitude",longitude1);
							i5.putExtra("write_guestint",guest_interact);
							i5.putExtra("write_guest", guest);
							i5.putExtra("write_space", space1);
		                    i5.putExtra("write_overview", neig_overview);
		                    i5.putExtra("write_around", neig_arround);
		                    i5.putExtra("write_other", other_thing);
		                    i5.putExtra("write_rules",house_rule1);
		                    i5.putExtra("step",step);
		                    i5.putExtra("check",check);
						Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
						startActivity(i5,bndlanimation );
						finish();
					}
				});
				
				t2.setOnClickListener(new View.OnClickListener() {
					
					@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent i1=new Intent(Writedescription.this,Desc_space.class);
						
						  i1.putExtra("room_id",room_id);
							i1.putExtra("userid",userid);
							i1.putExtra("image", imaage);
							i1.putExtra("title",diss);				
							i1.putExtra("summary", dis11);
							i1.putExtra("price",dis22);
							i1.putExtra("city", city);
							i1.putExtra("state", state);
							i1.putExtra("country", country);
							i1.putExtra("addresss",address1);
							i1.putExtra("latitude", latitude1);
							i1.putExtra("longitude",longitude1);
							i1.putExtra("write_guestint",guest_interact);
							i1.putExtra("write_guest", guest);
							i1.putExtra("write_space", space1);
		                    i1.putExtra("write_overview", neig_overview);
		                    i1.putExtra("write_around", neig_arround);
		                    i1.putExtra("write_other", other_thing);
		                    i1.putExtra("write_rules",house_rule1);
		                    i1.putExtra("step",step);
		                    i1.putExtra("check",check);
						Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
						startActivity(i1,bndlanimation);
						finish();
						
					}
				});
				 

		
		t3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i2=new Intent(Writedescription.this,Desc_guest.class);
				
				  i2.putExtra("room_id",room_id);
					i2.putExtra("userid",userid);
					i2.putExtra("image", imaage);
					i2.putExtra("title",diss);				
					i2.putExtra("summary", dis11);
					i2.putExtra("price",dis22);
					i2.putExtra("city", city);
					i2.putExtra("state", state);
					i2.putExtra("country", country);
					i2.putExtra("addresss",address1);
					i2.putExtra("latitude", latitude1);
					i2.putExtra("longitude",longitude1);
					i2.putExtra("write_guestint",guest_interact);
					i2.putExtra("write_guest", guest);
					i2.putExtra("write_space", space1);
                    i2.putExtra("write_overview", neig_overview);
                    i2.putExtra("write_around", neig_arround);
                    i2.putExtra("write_other", other_thing);
                    i2.putExtra("write_rules",house_rule1);
                    i2.putExtra("step",step);
                    i2.putExtra("check",check);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(i2,bndlanimation );
				finish();
			}
		});
		

		
		t01.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		Intent i3=new Intent(Writedescription.this,Desc_guestinteract.class);
		
		    i3.putExtra("room_id",room_id);
			i3.putExtra("userid",userid);
			i3.putExtra("image", imaage);
			i3.putExtra("title",diss);				
			i3.putExtra("summary", dis11);
			i3.putExtra("price",dis22);
			i3.putExtra("city", city);
			i3.putExtra("state", state);
			i3.putExtra("country", country);
			i3.putExtra("addresss",address1);
			i3.putExtra("latitude", latitude1);
			i3.putExtra("longitude",longitude1);
			i3.putExtra("write_guestint",guest_interact);
			i3.putExtra("write_guest", guest);
			i3.putExtra("write_space", space1);
            i3.putExtra("write_overview", neig_overview);
            i3.putExtra("write_around", neig_arround);
            i3.putExtra("write_other", other_thing);
            i3.putExtra("write_rules",house_rule1);
            i3.putExtra("step",step);
            i3.putExtra("check",check);
		Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
		startActivity(i3,bndlanimation );
		finish();
	}
});
		

	t04.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i4=new Intent(Writedescription.this,Desc_neighbour.class);
			  i4.putExtra("room_id",room_id);
				i4.putExtra("userid",userid);
				i4.putExtra("image", imaage);
				i4.putExtra("title",diss);				
				i4.putExtra("summary", dis11);
				i4.putExtra("price",dis22);
				i4.putExtra("city", city);
				i4.putExtra("state", state);
				i4.putExtra("country", country);
				i4.putExtra("addresss",address1);
				i4.putExtra("latitude", latitude1);
				i4.putExtra("longitude",longitude1);
				i4.putExtra("write_overview", neig_overview);
				i4.putExtra("guest_inter",guest_interact);
				i4.putExtra("write_guest", guest);
				i4.putExtra("write_space", space1);
				i4.putExtra("write_guestint",guest_interact);
				i4.putExtra("write_guest", guest);
				i4.putExtra("write_space", space1);
                i4.putExtra("write_overview", neig_overview);
                i4.putExtra("write_around", neig_arround);
                i4.putExtra("write_other", other_thing);
                i4.putExtra("write_rules",house_rule1);
                i4.putExtra("step",step);
                i4.putExtra("check",check);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			startActivity(i4,bndlanimation );
			finish();
		}
	});
t03.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i4=new Intent(Writedescription.this,Desc_neighbour.class);
			
			    i4.putExtra("room_id",room_id);
				i4.putExtra("userid",userid);
				i4.putExtra("image", imaage);
				i4.putExtra("title",diss);				
				i4.putExtra("summary", dis11);
				i4.putExtra("price",dis22);
				i4.putExtra("city", city);
				i4.putExtra("state", state);
				i4.putExtra("country", country);
				i4.putExtra("addresss",address1);
				i4.putExtra("latitude", latitude1);
				i4.putExtra("longitude",longitude1);
				i4.putExtra("write_guestint",guest_interact);
				i4.putExtra("write_guest", guest);
				i4.putExtra("write_space", space1);
                i4.putExtra("write_overview", neig_overview);
                i4.putExtra("write_around", neig_arround);
                i4.putExtra("write_other", other_thing);
                i4.putExtra("write_rules",house_rule1);
                i4.putExtra("step",step);
                i4.putExtra("check",check);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			startActivity(i4,bndlanimation );
			finish();
		}
	});


	
t05.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i5=new Intent(Writedescription.this,Desc_getaround.class);
			
			    i5.putExtra("room_id",room_id);
				i5.putExtra("userid",userid);
				i5.putExtra("image", imaage);
				i5.putExtra("title",diss);				
				i5.putExtra("summary", dis11);
				i5.putExtra("price",dis22);
				i5.putExtra("city", city);
				i5.putExtra("state", state);
				i5.putExtra("country", country);
				i5.putExtra("addresss",address1);
				i5.putExtra("latitude", latitude1);
				i5.putExtra("longitude",longitude1);
				i5.putExtra("write_guestint",guest_interact);
				i5.putExtra("write_guest", guest);
				i5.putExtra("write_space", space1);
                i5.putExtra("write_overview", neig_overview);
                i5.putExtra("write_around", neig_arround);
                i5.putExtra("write_other", other_thing);
                i5.putExtra("write_rules",house_rule1);
                i5.putExtra("step",step);
                i5.putExtra("check",check);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			startActivity(i5,bndlanimation );
			finish();
		}
	});

	t09.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i6=new Intent(Writedescription.this,Desc_other.class);
			
			i6.putExtra("room_id",room_id);
			i6.putExtra("userid",userid);
			i6.putExtra("image", imaage);
			i6.putExtra("title",diss);				
			i6.putExtra("summary", dis11);
			i6.putExtra("price",dis22);
			i6.putExtra("city", city);
			i6.putExtra("state", state);
			i6.putExtra("country", country);
			i6.putExtra("addresss",address1);
			i6.putExtra("latitude", latitude1);
			i6.putExtra("longitude",longitude1);
			i6.putExtra("write_guestint",guest_interact);
			i6.putExtra("write_guest", guest);
			i6.putExtra("write_space", space1);
            i6.putExtra("write_overview", neig_overview);
            i6.putExtra("write_around", neig_arround);
            i6.putExtra("write_other", other_thing);
            i6.putExtra("write_rules",house_rule1);
            i6.putExtra("step",step);
            i6.putExtra("check",check);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			startActivity(i6,bndlanimation );
			finish();
		}
	});
	t08.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i6=new Intent(Writedescription.this,Desc_other.class);
			
			    i6.putExtra("room_id",room_id);
				i6.putExtra("userid",userid);
				i6.putExtra("image", imaage);
				i6.putExtra("title",diss);				
				i6.putExtra("summary", dis11);
				i6.putExtra("price",dis22);
				i6.putExtra("city", city);
				i6.putExtra("state", state);
				i6.putExtra("country", country);
				i6.putExtra("addresss",address1);
				i6.putExtra("latitude", latitude1);
				i6.putExtra("longitude",longitude1);
				i6.putExtra("write_guestint",guest_interact);
				i6.putExtra("write_guest", guest);
				i6.putExtra("write_space", space1);
                i6.putExtra("write_overview", neig_overview);
                i6.putExtra("write_around", neig_arround);
                i6.putExtra("write_other", other_thing);
                i6.putExtra("write_rules",house_rule1);
                i6.putExtra("step",step);
                i6.putExtra("check",check);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			startActivity(i6,bndlanimation );
			finish();
		}
	});
	

	t11.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i7=new Intent(Writedescription.this,Desc_house.class);
			
			    i7.putExtra("room_id",room_id);
				i7.putExtra("userid",userid);
				i7.putExtra("image", imaage);
				i7.putExtra("title",diss);				
				i7.putExtra("summary", dis11);
				i7.putExtra("price",dis22);
				i7.putExtra("city", city);
				i7.putExtra("state", state);
				i7.putExtra("country", country);
				i7.putExtra("addresss",address1);
				i7.putExtra("latitude", latitude1);
				i7.putExtra("longitude",longitude1);
				i7.putExtra("write_guestint",guest_interact);
				i7.putExtra("write_guest", guest);
				i7.putExtra("write_space", space1);
                i7.putExtra("write_overview", neig_overview);
                i7.putExtra("write_around", neig_arround);
                i7.putExtra("write_other", other_thing);
                i7.putExtra("write_rules",house_rule1);
                i7.putExtra("step",step);
                i7.putExtra("check",check);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			startActivity(i7,bndlanimation );
			finish();
		}
	});
t10.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i7=new Intent(Writedescription.this,Desc_house.class);
			
			    i7.putExtra("room_id",room_id);
				i7.putExtra("userid",userid);
				i7.putExtra("image", imaage);
				i7.putExtra("title",diss);				
				i7.putExtra("summary", dis11);
				i7.putExtra("price",dis22);
				i7.putExtra("city", city);
				i7.putExtra("state", state);
				i7.putExtra("country", country);
				i7.putExtra("addresss",address1);
				i7.putExtra("latitude", latitude1);
				i7.putExtra("longitude",longitude1);
				i7.putExtra("write_guestint",guest_interact);
				i7.putExtra("write_guest", guest);
				i7.putExtra("write_space", space1);
                i7.putExtra("write_overview", neig_overview);
                i7.putExtra("write_around", neig_arround);
                i7.putExtra("write_other", other_thing);
                i7.putExtra("write_rules",house_rule1 );
                i7.putExtra("step",step);
                i7.putExtra("check",check);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Writedescription.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			startActivity(i7,bndlanimation );
			finish();
		}
	});







	



done.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			
	        
			if(!space1.isEmpty())
			{
				space1=space1.replaceAll(" ","%20");
			}
			if(!guest.isEmpty())
			{
				guest=guest.replaceAll(" ","%20");
			}
			if(!guest_interact.isEmpty())
			{
				guest_interact=guest_interact.replaceAll(" ","%20");
			}	
			if(!neig_overview.isEmpty())
			{
				neig_overview=neig_overview.replaceAll(" ","%20");
			}	
			if(!neig_arround.isEmpty())
			{
				neig_arround=neig_arround.replaceAll(" ","%20");
			}
			if(!other_thing.isEmpty())
			{
				other_thing=other_thing.replaceAll(" ","%20");
			}
			if(!house_rule1.isEmpty())
			{
				house_rule1=house_rule1.replaceAll(" ","%20");
			}
			add_description();
	        	
		}	
		
	});
	}
	
	
	
	
	public void add_description()
	{
		
		
		final String url=Liveurl+"rooms/add_description?roomid="+room_id+"&space="+space1+"&guests_info="+guest+"&interaction="+guest_interact+"&overview="+neig_overview+"&getting_around="+neig_arround+"&othert_thing="+other_thing+"&house_rule="+house_rule1;
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
  							login_status =  obj.getString("reason_message");
	                           
  			                

  					      System.out.println("after json"+login_status);
  					             
  					             if(login_status.matches("Description added Successfully"))
  					             {
  					                Toast.makeText(getApplicationContext(), "Description added  Successfully", Toast.LENGTH_SHORT).show();
  					                finish();   
  					             }
  							
  							System.out.println("roomid"+room_id);                            
  		                    System.out.println("space"+space);
  		         
  							
  						} catch (JSONException e) {
  							e.printStackTrace();
  						}
  						Intent i1=new Intent(Writedescription.this,Optional_details.class);
  			           
  			            i1.putExtra("room_id",room_id);
  						i1.putExtra("userid",userid);
  						i1.putExtra("image", imaage);
  						i1.putExtra("title",diss);				
  						i1.putExtra("summary", dis11);
  						i1.putExtra("price",dis22);
  						i1.putExtra("city", city);
  						i1.putExtra("state", state);
  						i1.putExtra("country", country);
  						i1.putExtra("addresss",address1);
  						i1.putExtra("first", first);
  						i1.putExtra("second", second);
  						i1.putExtra("third", third);
  						i1.putExtra("fourth", fourth);
  						i1.putExtra("last", last);
  						i1.putExtra("latitude", latitude1);
  						i1.putExtra("longitude",longitude1);
  						i1.putExtra("write_guestint",guest_interact);
  						i1.putExtra("write_guest", guest);
  						i1.putExtra("write_space", space1);
  		                i1.putExtra("write_overview", neig_overview);
  		                i1.putExtra("write_around", neig_arround);
  		                i1.putExtra("write_other", other_thing);
  		                i1.putExtra("write_rules",house_rule1 );
  		              i1.putExtra("step",step);
	                    i1.putExtra("check",check);

  						startActivity(i1);
  						finish();
  						
  					}

  					hidePDialog();

  					
  				}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				 
			}
		});

// Adding request to request queue
AppController.getInstance().addToRequestQueue(movieReq);
	}
	

	
	
	
	public void view_description()
	{
		final String url=Liveurl+"rooms/view_description?room_id="+room_id;
		System.out.println("URL is"+url);
  	 // Creating volley request obj
/*		findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);
*/  	JsonArrayRequest movieReq = new JsonArrayRequest(url,
  			new Response.Listener<JSONArray>() {
  				@Override
  				public void onResponse(JSONArray response) {
  					 

  					// Parsing json
  					for (int i = 0; i < response.length(); i++) {
  						try {

  							JSONObject obj = response.getJSONObject(i);
  							space1 = obj.getString("space");
  				            guest=obj.getString("guests_info");
  				            guest_interact=obj.getString("interaction");
  				            neig_overview=obj.getString("overview");
  				            neig_arround=obj.getString("getting_around");
  				            other_thing=obj.getString("other_thing");
  				            house_rule1=obj.getString("house_rule");
  							if(!space1.isEmpty()&& !space1.equals("null"))
  	   						 {
  	   							space1.replaceAll("%20"," ");
  	   							t8.setText(space1);
  								t8.setTextColor(Color.parseColor("#666666"));
                                
  	   						 }
  							if(!guest.isEmpty()&& !guest.equals("null"))
 	   						 {
 	   							guest.replaceAll("%20"," ");
 	   							t9.setText(guest);
 								t9.setTextColor(Color.parseColor("#666666"));

 	   						 }
  							
  							if(!guest_interact.isEmpty()&& !guest_interact.equals("null"))
	   						 {
	   							guest_interact.replaceAll("%20"," ");
	   							t02.setText(guest_interact);
								t02.setTextColor(Color.parseColor("#666666"));

	   						 }
  							if(!neig_overview.isEmpty()&& !neig_overview.equals("null"))
	   						 {
  								neig_overview.replaceAll("%20"," ");
	   							t04.setText(neig_overview);
								t04.setTextColor(Color.parseColor("#666666"));

	   						 }
  							if(!neig_arround.isEmpty()&& !neig_arround.equals("null"))
	   						 {
  								neig_arround.replaceAll("%20"," ");
  								t06.setText(neig_arround);
  								t06.setTextColor(Color.parseColor("#666666"));

	   						 }
  							if(!other_thing.isEmpty()&& !other_thing.equals("null"))
	   						 {
  								other_thing.replaceAll("%20"," ");
 								t09.setText(other_thing);
 								t09.setTextColor(Color.parseColor("#666666"));

	   						 }
  							if(!house_rule1.isEmpty()&& !house_rule1.equals("null"))
	   						 {
 								house_rule1.replaceAll("%20"," ");
 								t11.setText(house_rule1);
 								t11.setTextColor(Color.parseColor("#666666"));

	   						 }
  							
  							if(des_space!=null)
  						    {
  			   					space1=des_space;
  								System.out.println("insdie yu=="+space1);
  								t8.setText(des_space);
  								t8.setTextColor(Color.parseColor("#666666"));

  						    }
  							if(des_guest!=null)
  							{
  								guest=des_guest;
  								System.out.println("insdie yu=="+guest);
  								t9.setText(des_guest);
  								t9.setTextColor(Color.parseColor("#666666"));
  						    	
  							}
  							if(des_guestint!=null)
  							{
  								guest_interact=des_guestint;
  								System.out.println("insdie yu=="+guest_interact);
  								t02.setText(guest_interact);
  								t02.setTextColor(Color.parseColor("#666666"));
  						    	
  							}
  							if(desc_overview!=null)
  							{
  								neig_overview=desc_overview;
  								System.out.println("insdie yu=="+neig_overview);
  								t04.setText(desc_overview);
  								t04.setTextColor(Color.parseColor("#666666"));
  						    	
  							}
  							
  							if(desc_around!=null)
  							{
  								neig_arround=desc_around;
  								System.out.println("insdie yu=="+neig_arround);
  								t06.setText(desc_around);
  								t06.setTextColor(Color.parseColor("#666666"));
  						    	
  							}

  							if(desc_other!=null)
  							{
  								other_thing=desc_other;
  								System.out.println("insdie yu=="+neig_arround);
  								t09.setText(desc_other);
  								t09.setTextColor(Color.parseColor("#666666"));
  						    	
  							}
  							if(desc_houserule!=null)
  							{
  								house_rule1=desc_houserule;
  								System.out.println("insdie yu=="+house_rule1);
  								t11.setText(desc_houserule);
  								t11.setTextColor(Color.parseColor("#666666"));
  						    	
  							}
  						  	/*findViewById(R.id.progressBar1).setVisibility(View.INVISIBLE);*/

  							
  						} catch (JSONException e) {
  							e.printStackTrace();
  						}

  					}
  					System.out.println("space1=="+space1);
  					System.out.println("space1=="+guest);
          
  					
  					hidePDialog();
  				}
  				
  				
		}
  	, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				 

			}
		});

// Adding request to request queue
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