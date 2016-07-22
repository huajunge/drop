package com.cog.DropInn;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class Edit_list extends Activity {
	TextView t3,t2,t4,t5,t6,t7,t8,t9,preview;
	Button optional;
	String img,dis,dis1,dis2,dis0,roomid,imaage,city,state,country1;
	CheckBox title,summary,price,image,address; 
	ImageView display,display1,display2,display3,display4;
	Button completeprofile;
	ImageButton back;
	protected static final String TAG = null;
	Bitmap edbitmap;
	String editlist="Editlist";
	String image_id,first_address;
	String text,text1,gettext,getsummary;
	  private String address1;
	  String get_firstname,get_email,get_lastname,get_password;
		String Liveurl="";		
		URL Login_Url,pimage,Login_Url1;
		int total;
		public List<Movie> movieList = new ArrayList<Movie>();
	       private String login_status,login_status1;	
	       	ProgressDialog pDialog;
	   	Toast toast; 
	   	String userid,name;
	   	String web_imaage,web_address1,web_dis,web_dis1,web_dis2;
	   	URL list_Url;
	String list_inputline,list_status;
	JSONArray list_jsonarray;
	JSONObject list_jsonobj;	
	String image1,resize,latitude1,longitude1;
    int tit_step=0, sum_step=0, photo_step=0, pri_step=0, add_step=0;/*list_pay, stat;*/
    int chk=0;
    String step,check;
    String listpay;
    ImageView image21,image22,image23,image24,image25;
    TextView edit1,edit2,edit3,edit4;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);                             
		setContentView(R.layout.edit_list);
		
		getActionBar().hide();
		
		
		// Showing progress dialog before making http request
					pDialog = new ProgressDialog(this);
					pDialog.setCancelable(false);
					pDialog.show();
					pDialog.setContentView(R.layout.progress_dialog);
					
					image21=(ImageView)findViewById(R.id.imageView21);
					image22=(ImageView)findViewById(R.id.imageView22);
					image23=(ImageView)findViewById(R.id.imageView23);
					image24=(ImageView)findViewById(R.id.imageView24);
					image25=(ImageView)findViewById(R.id.imageView25);	
		back=(ImageButton)findViewById(R.id.TextView12);
		display=(ImageView)findViewById(R.id.imageView2);
		display1=(ImageView)findViewById(R.id.imageView4);
		display2=(ImageView)findViewById(R.id.ImageView01);
		display3=(ImageView)findViewById(R.id.imageView3);
		display4=(ImageView)findViewById(R.id.ImageView03);
		t2=(TextView)findViewById(R.id.TextView2);
		image=(CheckBox)findViewById(R.id.checkBox1);
		title=(CheckBox)findViewById(R.id.checkBox2);
		summary=(CheckBox)findViewById(R.id.checkBox3);
		price=(CheckBox)findViewById(R.id.checkBox4);
		address=(CheckBox)findViewById(R.id.checkBox5);
		t3=(TextView)findViewById(R.id.TextView3);
		t4=(TextView)findViewById(R.id.TextView4);
		t5=(TextView)findViewById(R.id.TextView5);
		t6=(TextView)findViewById(R.id.TextView6);
		t7=(TextView)findViewById(R.id.TextView7);
		t8=(TextView)findViewById(R.id.TextView8);
		t9=(TextView)findViewById(R.id.TextView9);
		preview=(TextView)findViewById(R.id.TextView13);
		optional=(Button)findViewById(R.id.optionaldetails);
		
		edit1=(TextView)findViewById(R.id.edit1);
		edit2=(TextView)findViewById(R.id.edit2);
		edit3=(TextView)findViewById(R.id.edit3);
		edit4=(TextView)findViewById(R.id.edit4);
		
		 if( Build.VERSION.SDK_INT >= 9){
	            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy);
	     }
		
		
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null);  
         
         SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
      		 userid = sharedPreferences2.getString("userid", null);
		
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		roomid=i.getStringExtra("room_id");
		image1=i.getStringExtra("image1");
		System.out.println("image1" +image1);
		image_id=image1;
		step=i.getStringExtra("step");
		check=i.getStringExtra("check");
		//resize=i.getStringExtra("resize");
		imaage=i.getStringExtra("image");		
		System.out.println("image in edit  ur space  "+imaage);		
		dis=i.getStringExtra("write_title");
		dis1=i.getStringExtra("write_summary");
		dis2=i.getStringExtra("write_price");			
		address1=i.getStringExtra("address");			
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		country1=i.getStringExtra("country");		
		text=i.getStringExtra("text");
		gettext=text;		
		text1=i.getStringExtra("text1");
		getsummary=text1;
		latitude1=i.getStringExtra("latitude");
		longitude1=i.getStringExtra("longitude");
				
		System.out.println("city in Edit list+++"+city);
		System.out.println("state in Edit list++++++"+state);
		System.out.println("country in Edit list+++++++ "+country1);		
		System.out.println("name in  Edit list+++++++++++"+name);
		System.out.println("image in Edit list+++++++++"+imaage);
		System.out.println("title in Edit list on create +=="+dis);
		System.out.println("desc in Edit list+++++"+dis1);
		System.out.println("price in Edit list ++++++"+dis2);
		System.out.println("Address in Edit list==+++++"+address1);
		completeprofile=(Button)findViewById(R.id.completeprofile);
		completeprofile.setEnabled(false);
		//completeprofile.setVisibility(View.INVISIBLE);
		//System.out.println("dis");
		
		System.out.println("room id in Edit list"+roomid);
		System.out.println("user id in Edit list"+userid);
		//System.out.println("dis");
		
		
	
		View_webservice();
		
	    System.out.println("title in Edit list on create =="+dis);
		System.out.println("desc in Edit list"+dis1);
		System.out.println("price in Edit list "+dis2);
		System.out.println("Address in Edit list=="+address1);
		System.out.println("Address in Edit list=="+imaage);
/*		
 * 
	if(!imaage.isEmpty())
		{
			System.out.println("outside try");
			try{
				 System.out.println("geturl"+imaage);
		 		 pimage=new URL(imaage);
		 		 edbitmap = BitmapFactory.decodeStream(pimage.openStream());
		 		 System.out.println("editmap"+pimage);
		         display.setImageBitmap(edbitmap);
		         display.setScaleType(ScaleType.FIT_XY);
		         image.setBackgroundResource(R.drawable.grt);
		 		}
		 		catch(Exception e){
		 		
		 		
		 		
		 			e.printStackTrace();
		 		}

			image.setBackgroundResource(R.drawable.grt);			
		}
	else
	{
		image.setBackgroundResource(R.drawable.gt);
	}*/
			
		
		if(imaage!=null)
		{
			if(!imaage.equals("null")&&!imaage.equals(""))
			{
			System.out.println("outside try");
			try{
				 System.out.println("geturl"+imaage);
		 		 pimage=new URL(imaage);
		 		 edbitmap = BitmapFactory.decodeStream(pimage.openStream());
		 		 System.out.println("editmap"+pimage);
		         display.setImageBitmap(edbitmap);
		         display.setScaleType(ScaleType.FIT_XY);
		         image.setBackgroundResource(R.drawable.tick);
		         photo_step=1;
			
		 		}
		 		catch(Exception e){
		 			e.printStackTrace();
		 		}
            
		image.setBackgroundResource(R.drawable.tick);			
		}
		}	
		
		preview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//new ProgressTask(Edit_list.this).execute();
				// Edit_list.this.pDialog = ProgressDialog.show(Edit_list.this, "", "Loading Data...",true);
	       pDialog=new ProgressDialog(Edit_list.this);
	            pDialog.show();
	            pDialog.setContentView(R.layout.progress_dialog);
				Thread thread=new Thread(new Runnable() {
	                 @Override
	                 public void run() {
	                 try {
	                 Thread.sleep(3000);
	                 } catch (InterruptedException e) {
	                 }
	                 pDialog.dismiss();
	                 }
	                 });
				call_webservice();
		 	 	  
			}
		});
		optional.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent optional=new Intent(Edit_list.this,Optional_details.class);	
				
				optional.putExtra("image1",image1);
				//optional.putExtra("resize",resize);				
				optional.putExtra("room_id",roomid);
				optional.putExtra("userid",userid);
				optional.putExtra("title",dis);
				optional.putExtra("image", imaage);
				optional.putExtra("summary", dis1);
				optional.putExtra("price",dis2);
				optional.putExtra("city", city);
				optional.putExtra("state", state);
				optional.putExtra("country", country1);
				optional.putExtra("addresss",address1);		
				optional.putExtra("step",step);	
				optional.putExtra("check",check);			
				startActivity(optional);
				finish();
			}
		});
		
		display.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent im=new Intent(Edit_list.this,Multiimage.class);
				
				im.putExtra("image1",image_id);
				//im.putExtra("resize",resize);
				im.putExtra("image", imaage);
				im.putExtra("room_id",roomid);
				im.putExtra("title",dis);
				im.putExtra("summary", dis1);
				im.putExtra("price",dis2);
				im.putExtra("city", city);
				im.putExtra("state", state);
				im.putExtra("country", country1);
				im.putExtra("addresss",address1);
				im.putExtra("userid",userid);
				im.putExtra("step",step);	
				im.putExtra("check",check);
			
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Edit_list.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(im,bndlanimation);
				finish();
			}
		});
		display1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent tit=new Intent(Edit_list.this,Writetitle.class);
				
				tit.putExtra("image1",image1);
				//tit.putExtra("resize",resize);
				tit.putExtra("text", gettext);
				System.out.println("text" +dis);
				tit.putExtra("text1", getsummary);
				tit.putExtra("image", imaage);
				tit.putExtra("summary", dis1);
				tit.putExtra("title",dis);
				tit.putExtra("price",dis2);
				tit.putExtra("city", city);
				tit.putExtra("state", state);
				tit.putExtra("country", country1);
				tit.putExtra("addresss",address1);
				tit.putExtra("room_id",roomid);
				tit.putExtra("userid",userid);
				tit.putExtra("step",step);	
				tit.putExtra("check",check);
			
				
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Edit_list.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(tit,bndlanimation);
				finish();
				
			}
		});
		display2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sum=new Intent(Edit_list.this,Writesummary.class);
				
				sum.putExtra("image1",image1);
				sum.putExtra("text1", getsummary);
				sum.putExtra("text", gettext);
				//sum.putExtra("resize",resize);
				sum.putExtra("image", imaage);
				sum.putExtra("title", dis);
				sum.putExtra("summary", dis1);
				sum.putExtra("price",dis2);
				sum.putExtra("city", city);
				sum.putExtra("state", state);
				sum.putExtra("country", country1);
				sum.putExtra("addresss",address1);
				sum.putExtra("room_id",roomid);
				sum.putExtra("userid",userid);
				sum.putExtra("step",step);	
				sum.putExtra("check",check);
			
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Edit_list.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(sum,bndlanimation);
				
				finish();
				
			}
		});
		display3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent pri=new Intent(Edit_list.this,Writeprice.class);
				pri.putExtra("image1",image1);
				//pri.putExtra("resize",resize);
				pri.putExtra("image", imaage);
				pri.putExtra("title", dis);
				pri.putExtra("summary", dis1);
				pri.putExtra("price",dis2);
				pri.putExtra("city", city);
				pri.putExtra("state", state);
				pri.putExtra("country", country1);
				pri.putExtra("addresss",address1);
				pri.putExtra("room_id",roomid);
				pri.putExtra("userid",userid);
				pri.putExtra("step",step);	
				pri.putExtra("check",check);
			
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Edit_list.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(pri,bndlanimation);
				finish();
				
			}
		});
		display4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent add=new Intent(Edit_list.this,Writeaddress.class);
				add.putExtra("image1",image1);
				//add.putExtra("resize",resize);
				add.putExtra("image", imaage);
				add.putExtra("price",dis2);
				add.putExtra("title", dis);
				add.putExtra("summary", dis1);
				add.putExtra("city", city);
				add.putExtra("state", state);
				add.putExtra("country", country1);
				add.putExtra("addresss",address1);
				add.putExtra("room_id",roomid);
				add.putExtra("userid",userid);
				add.putExtra("step",step);	
				add.putExtra("check",check);
				Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Edit_list.this, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				startActivity(add,bndlanimation);
				finish();
				
			}
		});
		completeprofile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dis=dis.replaceAll(" ","%20");
				dis1=dis1.replaceAll(" ","%20");
				if (total==0)
				{
				call_webservice1();
				}
				
			}
		});
	
        
		
		if(address1!=null)
		{
			if(!address1.equals("Null"))
			{
				t8.setVisibility(View.INVISIBLE);
				t9.setVisibility(View.INVISIBLE);
				edit4.setVisibility(View.VISIBLE);
		address1=WordUtils.capitalizeFully(address1);
		edit4.setText(address1);
		
		edit4.setTextColor(Color.parseColor("#666666"));
		address.setBackgroundResource(R.drawable.grt);
		add_step=1;
		}
		}
		else
		{
			t8.setVisibility(View.VISIBLE);
			t9.setVisibility(View.VISIBLE);
			edit4.setVisibility(View.INVISIBLE);
		}
		
		if(dis!=null)
		{
			if(!dis.equals("null"))
			{

			
			if(gettext!=null)
			{
				
				dis=gettext;
				
				t2.setVisibility(View.INVISIBLE);
				t3.setVisibility(View.INVISIBLE);
				edit1.setVisibility(View.VISIBLE);
				dis=WordUtils.capitalizeFully(dis);
				edit1.setText(dis);
				edit1.setTextColor(Color.parseColor("#666666"));
				title.setBackgroundResource(R.drawable.grt);
				tit_step=1;
			}
			
			else if(dis.isEmpty())
			{
				t2.setVisibility(View.VISIBLE);
				t3.setVisibility(View.VISIBLE);
				edit1.setVisibility(View.INVISIBLE);
			//tit_step=0;
			}
				else
			{
					
					t2.setVisibility(View.INVISIBLE);
					t3.setVisibility(View.INVISIBLE);
					edit1.setVisibility(View.VISIBLE);
				dis=WordUtils.capitalizeFully(dis);
				edit1.setText(dis);
				gettext=t3.getText().toString();
			
				edit1.setTextColor(Color.parseColor("#666666"));
				
				title.setBackgroundResource(R.drawable.tick);
			}
			}
		}

		
		
		
		//String regexStr = "^[a-zA-Z]*$";
		//String regexStr1 = "^[0-9]{7,35}$";
		if(dis1!=null)
		{
			if(!dis1.equals("null"))
			{
			if(getsummary!=null)
			{
				dis1=getsummary;
	
				t4.setVisibility(View.INVISIBLE);
				t5.setVisibility(View.INVISIBLE);
				edit2.setVisibility(View.VISIBLE);
				dis1=WordUtils.capitalizeFully(dis1);
				edit2.setText(dis1);
				edit2.setTextColor(Color.parseColor("#666666"));
				summary.setBackgroundResource(R.drawable.tick);
				sum_step=1;
			}
			else if(dis1.isEmpty())
			{
				t4.setVisibility(View.VISIBLE);
				t5.setVisibility(View.VISIBLE);
				edit2.setVisibility(View.INVISIBLE);
				//sum_step=0;

			}
			else
				
				{
			
				t4.setVisibility(View.INVISIBLE);
				t5.setVisibility(View.INVISIBLE);
				edit2.setVisibility(View.VISIBLE);
				dis1=WordUtils.capitalizeFully(dis1);
				edit2.setText(dis1);
				getsummary=t5.getText().toString();
				
				edit2.setTextColor(Color.parseColor("#666666"));
				summary.setBackgroundResource(R.drawable.tick);
				}
		}
		}
		if(dis2!=null)
		{
			if(!dis2.equals("null") && !dis2.equals("0"))
			{
				t6.setVisibility(View.INVISIBLE);
				t7.setVisibility(View.INVISIBLE);
				edit3.setVisibility(View.VISIBLE);
			dis2=WordUtils.capitalizeFully(dis2);
			edit3.setText("$"+dis2);
			edit3.setTextColor(Color.parseColor("#666666"));
			price.setBackgroundResource(R.drawable.tick);
			pri_step=1;
		}
		else
		{
			t6.setVisibility(View.VISIBLE);
			t7.setVisibility(View.VISIBLE);
			edit3.setVisibility(View.INVISIBLE);
		}
		
		}

		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(Edit_list.this,Swipe_tabshost.class);
				back.putExtra("room_id",roomid);
				back.putExtra("userid",userid);
				startActivity(back);
				finish();
			}
		});
		if(dis!=null&&dis1!=null&&dis2!=null&&address1!=null&&imaage!=null)
		{
			if(!dis.isEmpty() && !dis1.isEmpty() && !dis2.isEmpty() && !address1.isEmpty() && !imaage.isEmpty())
			{
		   
			completeprofile.setVisibility(View.VISIBLE);
			
			completeprofile.setText("Complete List");
			completeprofile.setEnabled(true);
			}
			else if (dis.isEmpty() || dis1.isEmpty() || dis2.isEmpty() || address1.isEmpty() || imaage.isEmpty())
			{
				completeprofile.setVisibility(View.VISIBLE);
				completeprofile.setText("5 steps to list");
				completeprofile.setEnabled(true);
			}
		}
		
	/*	if(!dis.equals("") && !dis1.equals("") && !dis2.equals("") && !address1.equals("") && !imaage.equals(""))
		{
		   
			completeprofile.setVisibility(View.VISIBLE);
			
			completeprofile.setText("Complete List");
			completeprofile.setEnabled(true);
		}*/
		
		
		
	}

	
	 public void call_webservice()
	 {
		  
				
			   if(dis!=null)
			   {
				   dis=dis.replaceAll(" ","%20");
					
			   }
			   if(dis1!=null)
			   {
				   dis1=dis1.replaceAll(" ","%20");
			   }
			   if(address1!=null)
			   {
				   address1=address1.replaceAll(" ","%20");
			   }
			   
	           if (total==0)
	           {
	        	   chk=1;
	        	   listpay="1";
	           }
	           else
	           {
	        	   chk=0;
	        	   listpay="0";
	           }
			     
	        	/*final String url=Liveurl+"rooms/add_room?roomid="+roomid+"&user_id="+userid+"&image="+imaage+"&image_id="+image1+"&title="+dis+"&description="+dis1+"&price="+dis2+"&address="+address1+"&city="+city+"&state="+state+"&country="+country+"&title_step="+tit_step+"&summary_step="+sum_step;*/	
			   final String url=Liveurl+"rooms/add_room?roomid="+roomid+"&user_id="+userid+"&image="+imaage+"&image_id="+image1+"&title="+dis+"&description="+dis1+"&price="+dis2+"&address="+address1+"&city="+city+"&state="+state+"&country="+country1+"&latitude="+latitude1+"&longitude="+longitude1+"&title_step="+tit_step+"&summary_step="+sum_step+"&price_step="+pri_step+"&addphoto_step="+photo_step+"&address_step="+add_step+"&check_status_count="+chk+"&listpay="+listpay+"&step_status="+total;	
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

	    
	      
	      Intent preview=new Intent(Edit_list.this,Detail_page.class);	
			preview.putExtra("image1",image1);
			//preview.putExtra("resize",resize);				
			preview.putExtra("room_id",roomid);
			preview.putExtra("title",dis);
			preview.putExtra("image", imaage);
			preview.putExtra("summary", dis1);
			preview.putExtra("price",dis2);
			preview.putExtra("city", city);
			preview.putExtra("state", state);
			preview.putExtra("country", country1);
			preview.putExtra("address",address1);
			preview.putExtra("userid",userid);	
			preview.putExtra("editlist",editlist);
			preview.putExtra("previewstring","1");
			preview.putExtra("step",step);
			preview.putExtra("check",check);
			
			startActivity(preview);
			finish();
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
	/* public void call_webservice1()
	 {
		   
	     
				address1=address1.replaceAll(" ","%20");
		      	city=city.replaceAll(" ","%20");
		      	state=state.replaceAll(" ","%20");
		      	country=state.replaceAll(" ","%20");
		      
		              	
		      	final String url=Liveurl+"rooms/edit_list?roomid="+roomid+"&title="+dis+"desc="+dis1+"&price="+dis2+"&photo="+imaage+"&address="+address1;	
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
	                           
	                

	     
	             
	             if(login_status1.matches("Updated Successfully"))
	             {
	                 

	         		//final AlertDialog alertDialog = new AlertDialog.Builder(Edit_list.this).create();
	                 
	                 Toast.makeText(getApplicationContext(), "List added  Successfully", Toast.LENGTH_SHORT).show();
	                  Intent i1 = new Intent(Edit_list.this, Swipe_tabs.class);
	                  i1.putExtra("userid",userid);
	                  i1.putExtra("roomid",roomid);
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
		   	   	
	  }*/
	 
	 public void View_webservice()
	 {
		 final String url=Liveurl+"rooms/view_listing?roomid="+roomid;	
			System.out.println("URL in edit_list page=="+url);
			 // Creating volley request obj
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {
							 

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {
	               
	        	                                  
									JSONObject obj = response.getJSONObject(i); 
									imaage=obj.getString("image");
						            dis=obj.getString("title");
						            dis1=obj.getString("desc");
						            dis2=obj.getString("price");
						            address1=obj.getString("address");
						           
						           image_id = obj.getString("imageid");
						            
						        	System.out.println("image_id=="+image_id);
						
						            
						        	/*if(!imaage.equals("")|| !imaage.equals("null"))
						    		{
						    			System.out.println("inside image not equl to null ");
						    			try{
						    				 System.out.println("geturl"+imaage);
						    		 		 pimage=new URL(imaage);
						    		 		 edbitmap = BitmapFactory.decodeStream(pimage.openStream());
						    		 		 System.out.println("editmap"+pimage);
						    		         display.setImageBitmap(edbitmap);
						    		         display.setScaleType(ScaleType.FIT_XY);
						    		         image.setBackgroundResource(R.drawable.grt);
						    		 		}
						    		 		catch(Exception e){
						    		 			e.printStackTrace();
						    		 		}

						    			image.setBackgroundResource(R.drawable.grt);			
						    		}
						        	else
						        	{
						        		image.setBackgroundResource(R.drawable.check);
						        		
						        	}
						        	
						        	if(!address1.equals("")||!address1.equals("null"))
						    		{
						        		System.out.println("inside address not equl to null ");
						    		t8.setVisibility(View.INVISIBLE);
						    		address1=WordUtils.capitalizeFully(address1);
						    		t9.setText(address1);
						    		
						    		t9.setTextColor(Color.parseColor("#FF1919"));
						    		address.setBackgroundResource(R.drawable.grt);
						    		}
						    		else
						    		{
						    			t8.setVisibility(View.VISIBLE);
						    			t9.setVisibility(View.VISIBLE);
						    		}
						    		
						    		if(!dis.equals("null"))
						    		{
						    			System.out.println("inside dis not equl to null ");

						    			System.out.println("list yu=="+gettext);
						    			if(gettext!=null)
						    			{
						    				
						    				dis=gettext;
						    				System.out.println("insdie yu=="+dis);
						    				t2.setVisibility(View.INVISIBLE);
						    				dis=WordUtils.capitalizeFully(dis);
						    				t3.setText(dis);
						    				t3.setTextColor(Color.parseColor("#FF1919"));
						    				title.setBackgroundResource(R.drawable.grt);
						    			}
						    			
						    			else if(dis.isEmpty())
						    			{
						    			t2.setVisibility(View.VISIBLE);
						    			t3.setVisibility(View.VISIBLE);
						    			}
						    				else
						    			{
						    					
						    				t2.setVisibility(View.INVISIBLE);
						    				dis=WordUtils.capitalizeFully(dis);
						    				t3.setText(dis);
						    				gettext=t3.getText().toString();
						    				System.out.println("check value in Edit list"+gettext);
						    				t3.setTextColor(Color.parseColor("#FF1919"));
						    				
						    				title.setBackgroundResource(R.drawable.grt);
						    			}

						    		}

						    		
						    		if(!dis1.equals("null"))
						    		{
						    			

						    			if(getsummary!=null)
						    			{
						    				dis1=getsummary;
						    	
						    				t4.setVisibility(View.INVISIBLE);
						    				dis1=WordUtils.capitalizeFully(dis1);
						    				t5.setText(dis1);
						    				t5.setTextColor(Color.parseColor("#FF1919"));
						    				summary.setBackgroundResource(R.drawable.grt);
						    			}
						    			else if(dis1.isEmpty())
						    			{
						    				t4.setVisibility(View.VISIBLE);
						    				t5.setVisibility(View.VISIBLE);
						    			}
						    			else
						    				
						    				{
						    			
						    				t4.setVisibility(View.INVISIBLE);
						    				dis1=WordUtils.capitalizeFully(dis1);
						    				t5.setText(dis1);
						    				getsummary=t5.getText().toString();
						    	
						    				t5.setTextColor(Color.parseColor("#FF1919"));
						    				summary.setBackgroundResource(R.drawable.grt);
						    				}
						    		}
						    	
						    		if(!dis2.equals("0"))
						    		{
						    			System.out.println("inside dis2 not equl to null ");
						    			t6.setVisibility(View.INVISIBLE);
						    			dis2=WordUtils.capitalizeFully(dis2);
						    			t7.setText("$"+dis2);
						    			t7.setTextColor(Color.parseColor("#FF1919"));
						    			price.setBackgroundResource(R.drawable.grt);
						    		}
						    		else
						    		{
						    			t6.setVisibility(View.VISIBLE);
						    		}*/
						            
								//srikri
						            
						        	if (address1!=null && !address1.equals("null") && !address1.equals("") && !address1.equals("Null"))
						    		{
						        		t8.setVisibility(View.INVISIBLE);
						        		t9.setVisibility(View.INVISIBLE);
						        		edit4.setVisibility(View.VISIBLE);
						    		address1=WordUtils.capitalizeFully(address1);
						    		edit4.setText(address1);
						    		
						    		edit4.setTextColor(Color.parseColor("#666666"));
						    		address.setBackgroundResource(R.drawable.tick);
						    		add_step=1;
						    		
						    		}
						        	else if (address1!=null && (address1.equals("null") || address1.equals("") || address1.equals("Null")))
						    		{
						        		t8.setVisibility(View.VISIBLE);
						    			t9.setVisibility(View.VISIBLE);
						    			edit4.setVisibility(View.INVISIBLE);
						    			add_step=0;
						    		}
						    		/*
						    		if(dis!=null)
						    		{
						    			

						    			System.out.println("list yu=="+gettext);
						    			if(gettext!=null)
						    			{
						    				
						    				dis=gettext;
						    				System.out.println("insdie yu=="+dis);
						    				t2.setVisibility(View.INVISIBLE);
						    				dis=WordUtils.capitalizeFully(dis);
						    				t3.setText(dis);
						    				t3.setTextColor(Color.parseColor("#FF1919"));
						    				title.setBackgroundResource(R.drawable.grt);
						    				tit_step=1;
						    			
						    			}
						    			
						    			else if(dis.isEmpty())
						    			{
						    			t2.setVisibility(View.VISIBLE);
						    			t3.setVisibility(View.VISIBLE);
						    			tit_step=0;
						    			}
						    				else
						    			{
						    					
						    				t2.setVisibility(View.INVISIBLE);
						    				dis=WordUtils.capitalizeFully(dis);
						    				t3.setText(dis);
						    				gettext=t3.getText().toString();
						    				System.out.println("check value in list your space"+gettext);
						    				t3.setTextColor(Color.parseColor("#FF1919"));
						    				
						    				title.setBackgroundResource(R.drawable.grt);
						    			}

						    		}
						    */
						    		if (dis!=null && !dis.equals("null") && !dis.equals("") && !dis.equals("Null"))
						    		{
						    			

						    			System.out.println("list yu=="+gettext);
						    			//if(gettext!=null)
						    			//{
						    				
						    				//dis=gettext;
						    				System.out.println("insdie yu=="+dis);
						    				t2.setVisibility(View.INVISIBLE);
						    				t3.setVisibility(View.INVISIBLE);
						    				edit1.setVisibility(View.VISIBLE);
						    				dis=WordUtils.capitalizeFully(dis);
						    				edit1.setText(dis);
						    				edit1.setTextColor(Color.parseColor("#666666"));
						    				title.setBackgroundResource(R.drawable.tick);
						    				tit_step=1;
						    			
						    			}
						    			//if(dis.isEmpty())
						    		else if (dis!=null && (dis.equals("null") || dis.equals("") || dis.equals("Null")))
						    			{
						    			t2.setVisibility(View.VISIBLE);
						    			t3.setVisibility(View.VISIBLE);
						    			edit1.setVisibility(View.INVISIBLE);
						    			tit_step=0;
						    			}
						    			
						    		
						    		if (dis1!=null && !dis1.equals("null") && !dis1.equals("") && !dis1.equals("Null"))
						    		{

						    			//if(getsummary!=null)
						    			//{
						    				//dis1=getsummary;
						    				System.out.println("insdie yu=="+dis1);
						    				t4.setVisibility(View.INVISIBLE);
						    				t5.setVisibility(View.INVISIBLE);
						    				edit2.setVisibility(View.VISIBLE);
						    				dis1=WordUtils.capitalizeFully(dis1);
						    				edit2.setText(dis1);
						    				edit2.setTextColor(Color.parseColor("#666666"));
						    				summary.setBackgroundResource(R.drawable.tick);
						    				sum_step=1;
						    			}
						    		//if(dis1.isEmpty())
						    		else if (dis1!=null && (dis1.equals("null") || dis1.equals("") || dis1.equals("Null")))
						    			{
						    			t4.setVisibility(View.VISIBLE);
										t5.setVisibility(View.VISIBLE);
										edit2.setVisibility(View.INVISIBLE);
						    				sum_step=0;
						    			}
						    			/*else
						    				
						    				{
						    			
						    				t4.setVisibility(View.INVISIBLE);
						    				dis1=WordUtils.capitalizeFully(dis1);
						    				t5.setText(dis1);
						    				getsummary=t5.getText().toString();
						    				System.out.println("check value in list your space"+getsummary);
						    				t5.setTextColor(Color.parseColor("#FF1919"));
						    				summary.setBackgroundResource(R.drawable.grt);
						    				}
						    		}*/
						    	
						    		if(dis2!=null && !dis2.equals("0"))
						    		{
						    			t6.setVisibility(View.INVISIBLE);
						    			t7.setVisibility(View.INVISIBLE);
						    			edit3.setVisibility(View.VISIBLE);
						    			dis2=WordUtils.capitalizeFully(dis2);
						    			edit3.setText("$"+dis2);
						    			edit3.setTextColor(Color.parseColor("#666666"));
						    			price.setBackgroundResource(R.drawable.tick);
						    		    pri_step=1;
						    		}
						    		else if (dis2!=null && dis2.equals("0"))
						    		{
						    			t6.setVisibility(View.VISIBLE);
						    			t7.setVisibility(View.VISIBLE);
						    			edit3.setVisibility(View.INVISIBLE);
						    			pri_step=0;
						    		}
						    		
						    		if(imaage!=null && !imaage.equals(""))
						    		{
						    			System.out.println("outside try");
						    			try{
						    				 System.out.println("geturl"+imaage);
						    		 		 pimage=new URL(imaage);
						    		 		 edbitmap = BitmapFactory.decodeStream(pimage.openStream());
						    		 		 
						    		 		 Display display1 = getWindowManager().getDefaultDisplay();
						                        Point size = new Point();
						                        display1.getSize(size);
						                        int width = size.x;
						                        int height = size.y;
						                        Log.e("Screen width ", " "+width);
						                        Log.e("Screen height ", " "+height);
						                        Log.e("img width ", " "+edbitmap.getWidth());
						                        Log.e("img height ", " "+edbitmap.getHeight());
						                        float scaleHt =(float) width/edbitmap.getWidth();
						                        Log.e("Scaled percent ", " "+scaleHt);
						                        Bitmap scaled = Bitmap.createScaledBitmap(edbitmap,     width, (int) (edbitmap.getWidth()*scaleHt), true);
						                        display.setImageBitmap(scaled);
						                        display.setScaleType(ScaleType.FIT_XY);
						    		 		 
						    		 		 System.out.println("editmap"+pimage);
						    		       /*  display.setImageBitmap(edbitmap);
						    		         */
						    		         image.setBackgroundResource(R.drawable.tick);
						    		         photo_step=1;
						    		         
						    		 		}
						    		 		catch(Exception e){
						    		 			e.printStackTrace();
						    		 		}
						    			

						    		image.setBackgroundResource(R.drawable.tick);			
						    		}
						    		else if (imaage.isEmpty())
						    		{
						    			display.setVisibility(View.VISIBLE);
						    			image.setVisibility(View.VISIBLE);
						    			photo_step=0;
						    		}
						    		 total = 5 -(tit_step+sum_step+photo_step+add_step+pri_step);
						    		System.out.println("total"+total);
						    		
						    		if (total==0)
						    		{
						                completeprofile.setVisibility(View.VISIBLE);
						    			completeprofile.setText("Complete List");
						    			completeprofile.setEnabled(true);
						    			image25.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image24.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image23.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image22.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image21.setBackgroundColor(Color.parseColor("#FF1919"));
						    		}
						    		
						    		if (total==1)
						    		{
						    			completeprofile.setVisibility(View.VISIBLE);
						    			completeprofile.setText("1 step to list");
						    			completeprofile.setEnabled(true);
						    			image25.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image24.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image23.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image22.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image21.setBackgroundColor(Color.parseColor("#FF1919"));
						    		}
						    		
						    		if (total==2)
						    		{
						    			completeprofile.setVisibility(View.VISIBLE);
						    			completeprofile.setText("2 steps to list");
						    			completeprofile.setEnabled(true);
						    			image25.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image24.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image23.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image22.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image21.setBackgroundColor(Color.parseColor("#FF1919"));
						    		}
						    		
						    		if (total==3)
						    		{
						    			completeprofile.setVisibility(View.VISIBLE);
						    			completeprofile.setText("3 steps to list");
						    			completeprofile.setEnabled(true);
						    			image25.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image24.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image23.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image22.setBackgroundColor(Color.parseColor("#FF1919"));
						    			image21.setBackgroundColor(Color.parseColor("#FF1919"));
						    		}
						    		
						    		if (total==4)
						    		{
						    			completeprofile.setVisibility(View.VISIBLE);
						    			completeprofile.setText("4 steps to list");
						    			completeprofile.setEnabled(true);
						    			image25.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image24.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image23.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image22.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image21.setBackgroundColor(Color.parseColor("#FF1919"));
						    		}
						    		
						    		if (total==5)
						    		{
						    			completeprofile.setVisibility(View.VISIBLE);
						    			completeprofile.setText("5 steps to list");
						    			completeprofile.setEnabled(true);
						    			image25.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image24.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image23.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image22.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    			image21.setBackgroundColor(Color.parseColor("#D3D3D3"));
						    		}
								
								//if(dis!=null&&dis1!=null&&dis2!=null&&address1!=null&&imaage!=null)
						    		/*if(!dis.isEmpty() && !dis1.isEmpty() && !dis2.isEmpty() && !address1.isEmpty() && !imaage.isEmpty())
								{
								   
									completeprofile.setVisibility(View.VISIBLE);
									
									completeprofile.setText("Complete List");
									completeprofile.setEnabled(true);
								}*/
								}
	       
								catch (JSONException e) {
									e.printStackTrace();
								}
								
							
								
								
							}
							 hidePDialog();
							
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
	 
	 public void call_webservice1()
	 {
		   
		  if(dis!=null)
		   {
			   dis=dis.replaceAll(" ","%20");
				
		   }
		   if(dis1!=null)
		   {
			   dis1=dis1.replaceAll(" ","%20");
		   }
		   if(address1!=null)
		   {
			   address1=address1.replaceAll(" ","%20");
		   }
		        chk=1;
		        System.out.println("STATUS"+chk);
		      	/*final String url=Liveurl+"rooms/add_room?roomid="+roomid+"&user_id="+userid+"&image="+imaage+"&image_id="+image1+"&title="+dis+"&description="+dis1+"&price="+dis2+"&address="+address1+"&city="+city+"&state="+state+"&country="+country+"&latitude="+latitude1+"&longitude="+longitude1+"&title_step="+tit_step+"&summary_step="+sum_step;*/
		        final String url=Liveurl+"rooms/add_room?roomid="+roomid+"&user_id="+userid+"&image="+imaage+"&image_id="+image1+"&title="+dis+"&description="+dis1+"&price="+dis2+"&address="+address1+"&city="+city+"&state="+state+"&country="+country1+"&latitude="+latitude1+"&longitude="+longitude1+"&title_step="+tit_step+"&summary_step="+sum_step+"&price_step="+pri_step+"&addphoto_step="+photo_step+"&address_step="+add_step+"&check_status_count="+chk;	
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
	                 
	                 Toast.makeText(getApplicationContext(), "List added  Successfully", Toast.LENGTH_SHORT).show();
	                  Intent i1 = new Intent(Edit_list.this, Swipe_tabs.class);
	                  i1.putExtra("userid",userid);
	                  i1.putExtra("roomid",roomid);
	                  
	               /*   System.out.println("TITLE STEP"+tit_step);
					   
				        total = 5 -(tit_step+sum_step+photo_step+add_step+pri_step);
		                System.out.println("totally" +total);
		                String totally = String.valueOf(total);
		                if (total==0)
		                {
		                Movie movie = new Movie();
		                movie.total(obj.getString(totally));
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
	 
	 @Override
	 public void onBackPressed()
	 {
		 Intent back=new Intent(Edit_list.this,Swipe_tabs.class);
			back.putExtra("room_id",roomid);
			back.putExtra("userid",userid);
			startActivity(back);
			finish();
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