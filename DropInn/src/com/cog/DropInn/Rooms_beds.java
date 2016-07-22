package com.cog.DropInn;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
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
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Rooms_beds extends Activity {

	 ProgressDialog progressdialog;
	
	   String userid,room_type,flat,house,bedroom,Address1,hometype;
	   String prop_id,prop_type,address,city,state,country,screeny;
	   ProgressDialog pDialog;
	 ImageButton plus,minus,bedplus,bedminus,bathplus,bathminus,bedroomminus,bedroomplus;
	int score=1;
	URL image_Url;
	JSONObject image_jsonobj;
	JSONArray image_jsonarray;
	String image_status,image_id;
	double score1=0.5;
	int score2=1;
	String  image_inputline;
	int score3=1;
	String Liveurl="";
	URL Login_Url;
	 Toast toast;
	 String[] strArray = new String[5];
		public static final String PREFS_NAME = "MyPrefsFile";
	   private JSONArray login_jsonarray;
       private JSONObject login_jsonobj;
       private String login_status;
       private String room_id;
       private String login_inputline;
       private String imagepath;
       String image,image1,resize;
       private JSONArray login_jsonarray1;
       private JSONObject login_jsonobj1;
       private String stry;
	TextView maxguest,beds,bathrooms,bedrooms;
	@SuppressLint({ "NewApi", "CommitPrefEdits" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rooms_beds);		
		getActionBar().hide();
		
		  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	         Liveurl = sharedPreferences.getString("liveurl", null);
	         
	         
		Intent i=getIntent();
		//userid=i.getStringExtra("userid");
		room_type=i.getStringExtra("room_type");
		prop_id=i.getStringExtra("propertyid");
		prop_type=i.getStringExtra("prop_type");
		address=i.getStringExtra("address");		
		city=i.getStringExtra("city");
		state=i.getStringExtra("state");
		screeny=i.getStringExtra("map");
        //hometype=i.getStringExtra("");		
		SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = sharedPreferences1.edit();
        editor.putString("first_address",address );
        editor.commit();
		
		System.out.println("rooms_beds page userid"+userid);

		System.out.println("rooms_beds page room_type"+room_type);
		System.out.println("rooms_beds page prop_type"+prop_type);
		System.out.println("rooms_beds page address"+address);
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		   userid = sharedPreferences2.getString("userid",null);
		System.out.println("userid in shared preferences in room_beds page  "+userid);
				
		if(userid==null)
		{
			 Toast.makeText(getApplicationContext(), "You must Login now  ", Toast.LENGTH_SHORT).show();
			 Intent login=new Intent(Rooms_beds.this,MainActivity.class);
			 startActivity(login);
				finish();
		}
		else
		{
		
	 ImageButton back=(ImageButton)findViewById(R.id.imageButton3);
	 Button next=(Button)findViewById(R.id.imageView8);
	 maxguest=(TextView)findViewById(R.id.TextView1);
	 beds=(TextView)findViewById(R.id.TextView2);	 
	 bathrooms=(TextView)findViewById(R.id.TextView3);
	 bedrooms=(TextView)findViewById(R.id.TextView8);
	 plus=(ImageButton)findViewById(R.id.imageView9);
	 minus=(ImageButton)findViewById(R.id.imageView5);
	 bedplus=(ImageButton)findViewById(R.id.imageView10);
	 bedminus=(ImageButton)findViewById(R.id.imageView6);
	 bathplus=(ImageButton)findViewById(R.id.imageView11);
	 bathminus=(ImageButton)findViewById(R.id.imageView7);
	 bedroomminus=(ImageButton)findViewById(R.id.imageButton1);
	 bedroomplus=(ImageButton)findViewById(R.id.imageButton2);
 
	
//************************** plus and minus functionality***********************************
	
	plus.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			minus.setEnabled(true);
			
					
			if(score>0)
			{
				score++;
				plus.setEnabled(true);
				if(score==16)
				{
					plus.setEnabled(false);
					minus.setEnabled(true);
				}
			}			
			
			maxguest.setText(String.valueOf(score));
		}
	});
	
	minus.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			plus.setEnabled(true);
			
			System.out.println("score in minus"+score);
			
			if(score<=16&&score>0)
			{
				minus.setEnabled(true);
				if(score==1)
				{
					minus.setEnabled(false);
					plus.setEnabled(true);
				}
				else{
				score--;}

			}
			
			maxguest.setText(String.valueOf(score));
			
		}
	});
	
	bedplus.setOnClickListener(new View.OnClickListener() {
		
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bedminus.setEnabled(true);
			
			
			if(score2>0)
			{
				score2++;
				bedplus.setEnabled(true);
				if(score2==16)
				{
					bedplus.setEnabled(false);
					bedminus.setEnabled(true);
				}
			}	
			beds.setText(String.valueOf(score2));
		}
	});
	bedminus.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bedplus.setEnabled(true);
			System.out.println("score in minus"+score);
			
			if(score2<=16&&score2>0)
			{
				
				minus.setEnabled(true);
				if(score2==1)
				{
					bedminus.setEnabled(false);
					bedplus.setEnabled(true);
				}
				else{
					score2--;}
			}
			
			beds.setText(String.valueOf(score2));
		}
	});
	
	bathplus.setOnClickListener(new View.OnClickListener() {

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bathminus.setEnabled(true);
						
			if(score1>=0.5)
			{
				score1=score1+0.5;
				bathplus.setEnabled(true);
				if(score1==8.0)
				{
					bathplus.setEnabled(false);
					bathminus.setEnabled(true);
				}
			}	
			bathrooms.setText(String.valueOf(score1));
		}
	});
	bathminus.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			bathplus.setEnabled(true);
			System.out.println("score in minus"+score);
			
			if(score1<=8.0&&score1>=1.0)
			{
				
				bathminus.setEnabled(true);
				if(score1==1.0)
				{
					bathminus.setEnabled(false);
					bathplus.setEnabled(true);
				}
				else{
					score1=score1-0.5;
					}
				
			}
			bathrooms.setText(String.valueOf(score1));
		}
	});
	
	bedroomplus.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bedroomminus.setEnabled(true);
			
			
			if(score3>0)
			{
				score3++;
				bedroomplus.setEnabled(true);
				if(score3==16)
				{
					bedroomplus.setEnabled(false);
					bedroomminus.setEnabled(true);
				}
			}	
			bedrooms.setText(String.valueOf(score3));
		}
	});
	
	bedroomminus.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bedroomplus.setEnabled(true);
			System.out.println("score in minus"+score);
			
			if(score3<=16&&score3>0)
			{
				
				bedroomminus.setEnabled(true);
				if(score3==1)
				{
					bedroomminus.setEnabled(false);
					bedroomplus.setEnabled(true);
				}
				else{
					score3--;
				}
				
			}
			bedrooms.setText(String.valueOf(score3));
		}
	});
	
	
	
	
//***************************************************************end on plus minus functionality*************************	
	
	
	next.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			/*final ProgressDialog progressdialog = ProgressDialog.show(Rooms_beds.this, "Prepared!","5 more steps to complete list ..", true);
             progressdialog.setCancelable(true);
         
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     // TODO Auto-generated method stub
                     try {
                         // put the thread to sleep for 2 seconds
                         Thread.sleep(2000);
                     }
                     catch (Exception e) 
                     {
                     }
                     progressdialog.dismiss();
                 }
             }).start();*/
     			 
			System.out.println("address"+address);
			if(address==null){
				
				 Toast.makeText(getApplicationContext(), "You must enter the address  ", Toast.LENGTH_SHORT).show();
			}
			else
			{
				 //alert dialog
 				final Dialog dialog1 = new Dialog(Rooms_beds.this);
				dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog1.setContentView(R.layout.prepared);
				
				TextView heading = (TextView) dialog1.findViewById(R.id.TextView1);
				TextView steps= (TextView) dialog1.findViewById(R.id.TextView2);
				ImageView tick= (ImageView) dialog1.findViewById(R.id.imageView1);
				
				dialog1.show();
				// Hide after some seconds
				final Handler handler  = new Handler();
				final Runnable runnable = new Runnable() {
				    @Override
				    public void run() {
				        if (dialog1.isShowing()) {
				        	dialog1.dismiss();
				        }
				    }
				};

				dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
				    @Override
				    public void onDismiss(DialogInterface dialog) {
				        handler.removeCallbacks(runnable);
				    }
				});

				handler.postDelayed(runnable, 10000);
				dialog1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,370);
				call_webservice();
				new ProgressTask(Rooms_beds.this).execute();

			}
			  
										
			
			/*Intent back=new Intent(Rooms_beds.this,List_your_space.class);
				
			startActivity(back);*/
			
		}
	});
	
	
	back.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent back1=new Intent(Rooms_beds.this,Googlemap.class);
			back1.putExtra("userid", userid);
			back1.putExtra("room_type", room_type);
			back1.putExtra("propertyid", prop_id);
			back1.putExtra("prop_type", prop_type);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Rooms_beds.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				
			startActivity(back1);
			finish();
			
		}
	});

	
		}
	
	}
	public void call_webservice()
	{
		try {
			  
			  address=address.replaceAll(" ","%20");
			  prop_type=prop_type.replaceAll(" ","%20");
				room_type=room_type.replaceAll(" ","%20");  
	        	
	        	//Login_Url= new URL(Liveurl+"rooms/add?user_id="+userid+"&address="+address+"&property_type="+prop_type+"&room_type="+room_type+"&bedrooms="+String.valueOf(score3)+"&beds="+String.valueOf(score2)+"&bathrooms="+String.valueOf(score1)+"&capacity="+String.valueOf(score));
	        	Login_Url= new URL(Liveurl+"rooms/add?user_id="+userid+"&address="+address+"&property_id="+prop_type+"&room_type="+room_type+"&bedrooms="+String.valueOf(score3)+"&beds="+String.valueOf(score2)+"&bathrooms="+String.valueOf(score1)+"&bed_type=nice&capacity="+String.valueOf(score)+"&home_type="+room_type);
	           //Login_Url= new URL("Liveurl+"rooms/add?user_id=158&address=17/a1,%20NathamMeenachipuram%20&property_type=null&room_type=Shared%20room&bedrooms=2&beds=6&bathrooms=4.5&capacity=5&city=Natham&state=India");
	           System.out.println("rooms_beds page url"+Login_Url);
	           
	           BufferedReader login_reader;
	      
	            String str_login="";
	            login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
	           
	            String s=Login_Url.toString();
	            
	            
	            
	           
	            while ((login_inputline = login_reader.readLine())!= null)
	            {
	               
	                str_login += login_inputline;
	               
	                              
	            }
	           
		            
	            
	         /*toast=   Toast.makeText(getApplicationContext(), "Email Already Taken", Toast.LENGTH_LONG);
	         toast.setGravity(Gravity.CENTER, 0, 0);
	         toast.show();*/
	           
	           
	            login_jsonarray = new JSONArray(str_login);        
	                   
	             
	             for(int i=0; i <login_jsonarray.length(); i++)
	                {
	                                  
	            login_jsonobj = login_jsonarray.getJSONObject(i);
	            login_status =    login_jsonobj.getString("reason_message");
	                           
	                }

	      System.out.println("after json"+login_status);
	             
	             if(login_status.matches("List added successfully."))
	             
	             {
	            	         	             	 
	            	 
	         		room_id=  login_jsonobj.getString("room_id");
	         		
	         		 System.out.println("after room_id got"+room_id);
	         		 
	         				                        
	                //Toast.makeText(getApplicationContext(), "Prepared! Just 5 more steps to list ", Toast.LENGTH_SHORT).show();
	                  Intent i = new Intent(Rooms_beds.this, List_your_space.class);
	                  i.putExtra("room_id", room_id);
	                  i.putExtra("userid", userid);
	                  
	                 /*   i.putExtra("firstname",get_firstname);
	                    i.putExtra("lastname",get_lastname);
	                    i.putExtra("email", get_email);*/
	                   // i.putExtra("password", get_password);
	                   // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
	                  startActivity(i);
	                  finish();   
	             }
	             

	             else
	             {
	            	 Toast.makeText(getApplicationContext(), "List not created ", Toast.LENGTH_SHORT).show();
	             }
	       
	           
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	           
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (JSONException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
/*	public void onClick(View v)
	{
	boolean showText = false;

	switch(v.getId())
	{	
	case R.id.Plus: score++; showText = true; break;
	case R.id.Minus: score–; showText = true; break;
	case R.id.Ok: showText = true; break;
	}
	if(showText)
	Value.setText(String.valueOf(score));
	}*/
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
	 private class ProgressTask extends AsyncTask<String, Void, Boolean> 
	 {
	     private ProgressDialog dialog;
	     private Rooms_beds activity;

	     public ProgressTask(Rooms_beds activity) 
	     {
	         this.activity = activity;
	         context = activity;
	         dialog = new ProgressDialog(context);
	     }

	     private Context context;

	     protected void onPreExecute() 
	     {
	       /*  dialog = new ProgressDialog(context);
	         dialog.setMessage("Uploading...");
	         dialog.setIndeterminate(false);
	         dialog.setCancelable(false);
	         dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	         dialog.show();*/
	     }

	     @Override
	     protected void onPostExecute(final Boolean success) 
	     {
	         /*if (dialog.isShowing()) 
	                     {
	             dialog.dismiss();
	         }
	         if (success) 
	                     {
	            // Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
	         } 
	                     else 
	                     {
	             //Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show();
	         }*/
	     }

	     @Override
	     protected Boolean doInBackground(final String... args) 
	     {
	         try {    
	            // ... processing ...
	        
	        		 //imagepath =  ImageWrite(BitmapFactory.decodeFile(screeny));
	        	
         	
	 	 	    Imageuploading();
	        	 
	        	
	             return true;
	         } catch (Exception e){
	             Log.e("Schedule", "UpdateSchedule failed", e);
	             return false;
	         }
	     }
	 }
	 protected void Imageuploading() {
			// TODO Auto-generated method stub

			try {
			Log.e("image", "dfdf");

			HttpURLConnection connection = null;
			DataOutputStream outputStream = null;
			DataInputStream inputStream = null;

			String pathToOurFile = (String) screeny;

			System.out.println("screeeeeeeeeeeny" +screeny);
			//String urlServer = " http://demo.cogzideltemplates.com/client/gottospot_android/mobile/user/post_upload?room_id=1&user_id=12";
			String urlServer =  Liveurl+"user/map_upload?room_id="+room_id;
			String lineEnd = "\r\n";
			System.out.println("url"+urlServer);
			String twoHyphens = "--";
			String boundary = "*****";

			int bytesRead, bytesAvailable, bufferSize;
			byte[] buffer;
			int maxBufferSize = 1 * 1024 * 1024;
			FileInputStream fileInputStream = new FileInputStream(new File(
			pathToOurFile));

			URL url = new URL(urlServer);
			connection = (HttpURLConnection) url.openConnection();

			// Allow Inputs & Outputs
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// Enable POST method
			connection.setRequestMethod("POST");

			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type",
			"multipart/form-data;boundary=" + boundary);

			outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream
			.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
			+ pathToOurFile + "\"" + lineEnd);
			outputStream.writeBytes(lineEnd);

			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			// Read file
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {
			outputStream.write(buffer, 0, bufferSize);
			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}

			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(twoHyphens + boundary + twoHyphens
			+ lineEnd);
			// Responses from the server (code and message)
			int serverResponseCode = connection.getResponseCode();
			String serverResponseMessage = connection.getResponseMessage();

			// Toast.makeText(getApplicationContext(), serverResponseMessage,
			// Toast.LENGTH_LONG).show();
			System.out.println("image" + serverResponseMessage);

			fileInputStream.close();
			outputStream.flush();
			outputStream.close();

			DataInputStream inputStream1 = null;
			inputStream1 = new DataInputStream(connection.getInputStream());
			System.out.println("before input stream image url" + inputStream1);
			String str = "";
			String Str1_imageurl = "";

			while ((str = inputStream1.readLine()) != null) {
			Log.e("Debug", "Server Response " + str);

			Str1_imageurl = str;
			Log.e("Debug", "Server Response String imageurl" + str);
			}
			inputStream1.close();
			System.out.println("after input stream image url" + inputStream1);
			System.out.println("image url" + Str1_imageurl);
			// Toast.makeText(getApplicationContext(), Str1_imageurl,
			// Toast.LENGTH_LONG).show();

			stry = Str1_imageurl.trim();
			
			//Login_Url1=stry;
			System.out.println("check stry"+stry);
			  
			
			try {         
			            login_jsonarray1 = new JSONArray(stry);        
			                   
			             
			             for(int i=0; i <login_jsonarray1.length(); i++)
			                {
			                                  
			            login_jsonobj1 = login_jsonarray1.getJSONObject(i);
			            image1 =    login_jsonobj1.getString("image");
			            resize=login_jsonobj1.getString("resize");
			            image=login_jsonobj1.getString("resize1");
			                           
			                }

			      System.out.println("after image===="+image1);
			      System.out.println("after resize==="+resize);
			      System.out.println("after resize1===="+image);
			      System.out.println("");
			          
			      
			      
			      
			     // String strName = resize1;
		  
			       
			       
			           
			        }  catch (JSONException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        }
			} catch (Exception e) {

				e.printStackTrace();

				}
			
			
			return_image();
			
			//call_image();
			//msgToSend = stry;
			//************************* five images stored in array*****************************
			/*  strArray[count-1]=resize1;
		      System.out.println("images===="+ strArray[0]);
		      System.out.println("images===="+ strArray[1]);
		      System.out.println("images===="+ strArray[2]);
		      System.out.println("images===="+ strArray[3]);
		      System.out.println("images===="+ strArray[4]);
		             
		      first=strArray[0];
		      second=strArray[1];
		      third=strArray[2];
		      fourth=strArray[3];
		      last=strArray[4];
			
		      SharedPreferences sharedpreferences1=PreferenceManager.getDefaultSharedPreferences(this);
		      Editor editor = sharedpreferences1.edit();
		          
		      editor.putString("firstimage",first);
		      editor.putString("secondimage",second);
		      editor.putString("thirdimage",third);
		      editor.putString("fourthimage",fourth);
		      editor.putString("lastimage",last);
		      editor.commit();*/
		//********************************************************************************************
			//script();

			}
	 
	 public void return_image()
	 {
		 try
         { 
             
        	             
			// currency_Url = new URL("Liveurl+"user/edit_currency?roomid=2&currency=USD");
	            image_Url = new URL(Liveurl+"user/return_map?room_id="+room_id+"&user_id="+userid+"&map="+image);
			 System.out.println("return image url "+image_Url);
             BufferedReader image_reader;
             String str_currency="";            
            // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
             
            image_reader = new BufferedReader(new InputStreamReader(image_Url.openStream()));
             		             
             
             while ((image_inputline = image_reader.readLine())!= null)
             {
           	  
                 str_currency += image_inputline;
                 
             }
     
             System.out.print("reset"+str_currency);
             
              image_jsonarray = new JSONArray(str_currency);
                 
            
                  for(int i=0; i <image_jsonarray.length(); i++)
                     {
               	   image_jsonobj=image_jsonarray.getJSONObject(i);
                 image_status = image_jsonobj.getString("status");
                // image_id=image_jsonobj.getString("image_id");
                
                 
                     }
                  System.out.print("image_status"+ image_status);
                  System.out.print("image_status"+ image_id);
                  if(image_status.matches("Successfully updated your photo"))
                 {
                     
               	   System.out.println("successfully updated your photo");
                    /* Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
                     toast1.setGravity(Gravity.CENTER, 0, 0);
                     toast1.show();*/
                                    
                   
                 }
                  else if(image_status.matches("Upload valid image"))
                  {
                      
                	   System.out.println("Upload valid image");
                     /* Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
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
		
	 }
}
