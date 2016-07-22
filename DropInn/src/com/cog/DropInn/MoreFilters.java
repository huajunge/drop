package com.cog.DropInn;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cog.DropInn.SeekBarWithTwoThumb.SeekBarChangeListener;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;


public class MoreFilters extends FragmentActivity implements SeekBarChangeListener {
	protected static final String TAG = null;
	String Liveurl="";
	String roomtype,location;
	int value,value1;
	TextView tv1,tv2;
	String min,max;
	String roomtype1,roomtype2;
	SeekBarWithTwoThumb swtt;
	Button amenities,filters;
	private CaldroidFragment caldroidFragment;
	private CaldroidFragment dialogCaldroidFragment;
	TextView showDialogButton,showDialogButton1;
	String min_bathrooms,min_beds,min_bedrooms,guests,roomtypes;
	String	price_min,price_max;
	int score=1;
	double score1=0.5;
	int score2=1;
	int score3=1;
	ImageView img_private,img_shared,img_entire;
	Button back,reset;
	String userid,roomid;
	String arrive,depart;
	String at[]=new String[50];
	private boolean flag = true;
	private boolean flag1 = true;
	private boolean flag2 = true;
	ImageButton plus,minus,bedroomplus,bedroomminus,bedplus,bedminus,bathplus,bathminus;
	TextView maxguest,bedrooms,beds,bathrooms,txt_private,txt_shared,txt_entire;
	TextView bedsy,bedroomsy,bathroomsy;
	private void setCustomResourceForDates() {
		
 		Calendar cal = Calendar.getInstance();

 		// Min date is last 7 days
 		cal.add(Calendar.DATE, -18);
 		Date blueDate = cal.getTime();

 		// Max date is next 7 days
 		cal = Calendar.getInstance();
 		cal.add(Calendar.DATE, 16);
 		Date greenDate = cal.getTime();

 		if (caldroidFragment != null) {
 			caldroidFragment.setBackgroundResourceForDate(R.color.blue,
 					blueDate);
 			caldroidFragment.setBackgroundResourceForDate(R.color.green,
 					greenDate);
 			caldroidFragment.setTextColorForDate(R.color.white, blueDate);
 			caldroidFragment.setTextColorForDate(R.color.white, greenDate);
 		}
 	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_filters);
		getActionBar().hide();
		tv1 = (TextView) findViewById(R.id.TextView11);
        tv2 = (TextView) findViewById(R.id.TextView13);
        swtt = (SeekBarWithTwoThumb) findViewById(R.id.seekBar1);
        swtt.setSeekBarChangeListener(this);
         back=(Button)findViewById(R.id.back_Button);
        img_private=(ImageView)findViewById(R.id.imageView3);
        img_shared=(ImageView)findViewById(R.id.imageView4);
        img_entire=(ImageView)findViewById(R.id.imageView6);
        showDialogButton = (TextView) findViewById(R.id.TextView2);
		showDialogButton1 = (TextView) findViewById(R.id.TextView4);
		txt_private=(TextView)findViewById(R.id.TextView5);
		txt_shared=(TextView)findViewById(R.id.TextView6);
		txt_entire=(TextView)findViewById(R.id.TextView8);
		reset=(Button)findViewById(R.id.reset_Button);
        	
		 if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            
          
     }
		 
		 	  
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		 Liveurl = sharedPreferences.getString("liveurl", null);
		//Calendar Functionality Start
		 
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
			final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 
		
			
			// Setup listener
			final CaldroidListener listener = new CaldroidListener() {
				
				@Override
				public void onSelectDate(Date date,View view) {
					
					arrive=formatter.format(date).toString();
					showDialogButton.setText(arrive);
					showDialogButton1.setText("Select Date");
					
			    	 
					if(arrive!=null)
					{
						System.out.println("check depart value if arrive exists"+depart);
						showDialogButton1.setText("Select Date");
						depart=null;	
						dialogCaldroidFragment.dismiss();
					}	
				}
			};
			
			final Bundle state = savedInstanceState;
			showDialogButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// Setup caldroid to use as dialog
					dialogCaldroidFragment = new CaldroidFragment();
					dialogCaldroidFragment.setCaldroidListener(listener);

					// If activity is recovered from rotation
					final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
					if (state != null) {
						dialogCaldroidFragment.restoreDialogStatesFromKey(
								getSupportFragmentManager(), state,
								"DIALOG_CALDROID_SAVED_STATE", dialogTag);
						Bundle args = dialogCaldroidFragment.getArguments();
						if (args == null) {
							args = new Bundle();
							dialogCaldroidFragment.setArguments(args);
						}
						args.putString(CaldroidFragment.DIALOG_TITLE,
								"Select date");
					} else {
						
						// Setup arguments
						
						Calendar cal = Calendar.getInstance();
						
						// Disable Dates before today
				 		cal.add(Calendar.DATE, 0);
				 		System.out.println("cal min value"+cal);
						Date minDate = cal.getTime();
						System.out.println("Min date"+minDate);
						dialogCaldroidFragment.setMinDate(minDate);
						
						//Highlight Arrival and Departure Dates
						
						if(arrive!=null&&depart!=null)
						{
							System.out.println("arrive selected"+arrive);
							System.out.println("depart selected"+depart);
							
						String formatter1 = "dd-MM-yyyy";
						try {
							dialogCaldroidFragment.setSelectedDateStrings(arrive, depart, formatter1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}else if(depart==null)
						{
							dialogCaldroidFragment.clearSelectedDates();
						}
						
						
						// Setup dialog
						Bundle bundle = new Bundle();
						bundle.putString(CaldroidFragment.DIALOG_TITLE,
								"Select Arrival date");
						bundle.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
						bundle.putBoolean(CaldroidFragment.SHOW_NAVIGATION_ARROWS, true);
						bundle.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, false);
						dialogCaldroidFragment.setArguments(bundle);
					}

					dialogCaldroidFragment.show(getSupportFragmentManager(),
							dialogTag);
				}
			});
				final CaldroidListener listener1 = new CaldroidListener() {
				
					@Override
					public void onSelectDate(Date date, View view) {
						
						depart=formatter.format(date).toString();
						 
						System.out.println("arrival date before selection"+arrive);	
						 
						System.out.println("arrive inside depar"+arrive);
						
						     depart=formatter.format(date).toString();
							arrive=showDialogButton.getText().toString();
							System.out.println("arrive in depart"+arrive);
							showDialogButton1.setText(depart);
							depart=showDialogButton1.getText().toString();
							System.out.println("depart in depart"+depart);
						 
							if(arrive!=null&&depart!=null)
							{
								 showDialogButton.setText(arrive);
						    	 showDialogButton1.setText(depart);
						    	 dialogCaldroidFragment.dismiss();							
							}
						 
					}
			};
			
			
			
			final Bundle state1 = savedInstanceState;
			if(showDialogButton!=null)
			{
			showDialogButton1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					if(arrive!=null)
					{
					// Setup caldroid to use as dialog
					dialogCaldroidFragment = new CaldroidFragment();
					dialogCaldroidFragment.setCaldroidListener(listener1);

					// If activity is recovered from rotation
					final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
					if (state1 != null) {
						dialogCaldroidFragment.restoreDialogStatesFromKey(
								getSupportFragmentManager(), state1,
								"DIALOG_CALDROID_SAVED_STATE", dialogTag);
						Bundle args = dialogCaldroidFragment.getArguments();
						if (args == null) {
							args = new Bundle();
							dialogCaldroidFragment.setArguments(args);
						}
						args.putString(CaldroidFragment.DIALOG_TITLE,
								"Select a date");
					} else {
						// Setup arguments
						
						
						
						if(arrive!=null)
						{
						DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
						Date date1=null;
						try {
					        date1 = df1 .parse(arrive);
					    } catch (ParseException e) {
					        e.printStackTrace();
					    }
						System.out.println("date"+date1);
						Calendar cal1 = Calendar.getInstance();
					    cal1.setTime(date1);
					    cal1.add(Calendar.DATE, 1);
					    date1=cal1.getTime();
					    //dialogCaldroidFragment.moveToDate(date1);
					    dialogCaldroidFragment.setMinDate(date1);
					    
					    
					     
					    
						//dialogCaldroidFragment.setMinDateFromString(arrive,df1);
						}
						if(arrive!=null&&depart!=null)
						{
							System.out.println("arrive selected"+arrive);
							System.out.println("depart selected"+depart);
							
						String formatter1 = "dd-MM-yyyy";
						
						try {
							dialogCaldroidFragment.setSelectedDateStrings(arrive, depart, formatter1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
						else
						{
						dialogCaldroidFragment.clearSelectedDates();
						}
						
						 at = arrive.split("-");
					     String dat=at[0];
		                 String month=at[1];
		                 String year=at[2];
		                 System.out.println("date in string"+dat);
		                 System.out.println("month in string"+month);
		                 System.out.println("year in string"+year);
		                 
		                 int m=Integer.parseInt(month);
		                 int y=Integer.parseInt(year);
		                 System.out.println("month in int"+m);
		                 System.out.println("year in int"+y);
											
						Bundle bundle = new Bundle();
						// Setup dialogTitle
						bundle.putString(CaldroidFragment.DIALOG_TITLE,
								"Select Departure date");
						bundle.putInt(CaldroidFragment.MONTH, m); // October
						bundle.putInt(CaldroidFragment.YEAR, y);
						bundle.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
						bundle.putBoolean(CaldroidFragment.SHOW_NAVIGATION_ARROWS, true);
						bundle.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, false);
						dialogCaldroidFragment.setArguments(bundle);
					}
					

					dialogCaldroidFragment.show(getSupportFragmentManager(),
							dialogTag);
					}else
					{
						Toast.makeText(getApplicationContext(),
								"Select Arrival Date" ,
								Toast.LENGTH_SHORT).show();	
					}
				}
			});
			}
			
			//Calendar Functionality End
			 plus=(ImageButton)findViewById(R.id.imageButton2);
			 minus=(ImageButton)findViewById(R.id.imageButton1);
			 bedroomplus=(ImageButton)findViewById(R.id.imageButton4);
			 bedroomminus=(ImageButton)findViewById(R.id.imageButton3);
			 bedplus=(ImageButton)findViewById(R.id.imageButton6);
			 bedminus=(ImageButton)findViewById(R.id.imageButton5);
			 bathplus=(ImageButton)findViewById(R.id.imageButton8);
			 bathminus=(ImageButton)findViewById(R.id.imageButton7);			 
			 filters=(Button)findViewById(R.id.button2);
			 maxguest=(TextView)findViewById(R.id.TextView16);
			 bedrooms=(TextView)findViewById(R.id.TextView19);
			 beds=(TextView)findViewById(R.id.TextView21);
			 bathrooms=(TextView)findViewById(R.id.TextView23);	
			 bathroomsy=(TextView)findViewById(R.id.TextView22);
			 bedroomsy=(TextView)findViewById(R.id.TextView18);
			 bedsy=(TextView)findViewById(R.id.TextView20);
			 
			 
			 Intent im=getIntent();
			// userid=im.getStringExtra("userid");			
			roomid=im.getStringExtra("room_id");
			location=im.getStringExtra("location");
				
			back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent back=new Intent(MoreFilters.this,Search_result.class);
					back.putExtra("room_id",roomid);
					back.putExtra("userid",userid);	
					back.putExtra("location",location);
					startActivity(back);
					finish();
					
				}
			});
			 
	reset.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent back=new Intent(MoreFilters.this,MoreFilters.class);
					back.putExtra("room_id",roomid);
					back.putExtra("userid",userid);	
					back.putExtra("location",location);
					startActivity(back);
					finish();
					
				}
			});
			 
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
							bedsy.setText("beds");
							if(score2==16)
							{
								bedplus.setEnabled(false);
								bedminus.setEnabled(true);
								bedsy.setText("beds");
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
							bedsy.setText("beds");
							if(score2==1)
							{
								bedminus.setEnabled(false);
								bedplus.setEnabled(true);
								bedsy.setText("bed");
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
						if(score1>1)
						{
							bathroomsy.setText("bathrooms");
						}
						else
						{
							bathroomsy.setText("bathroom");
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
						
						if(score1>1)
						{
							bathroomsy.setText("bathrooms");
						}
						else
						{
							bathroomsy.setText("bathroom");
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
						if(score3>1)
						{
							bedroomsy.setText("bedrooms");
						}
						else
						{
							bedroomsy.setText("bedroom");
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
						
						if(score3>1)
						{
							bedroomsy.setText("bedrooms");
						}
						else
						{
							bedroomsy.setText("bedroom");
						}
						bedrooms.setText(String.valueOf(score3));
					}
				});
				
				
				
				
			//***************************************************************end on plus minus functionality*************************	
				img_private.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 switch(v.getId()){
						    case R.id.imageView3:{
						    if(flag)
						    {       
						        img_private.setImageResource(R.drawable.pr_room);
						        txt_private.setTextColor(Color.parseColor("#FF1919"));
						        System.out.println("inside private flag if condition");						        
						        roomtype="Private%20room,";
						        roomtype=((roomtype1==null) ? ((roomtype2==null) ? roomtype : (roomtype.concat(roomtype2))):roomtype.concat(roomtype1));
						        System.out.println("private room value"+roomtype);
						        flag=false;
						    }
						    else
						    {
						        img_private.setImageResource(R.drawable.private_room1);
						        txt_private.setTextColor(Color.parseColor("#666666"));
						        System.out.println("inside private flag else condition");
						        roomtype="";
						        flag=true;
						    }
						        return;
						    }
						  }
					}
				});	
				img_shared.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 switch(v.getId()){
						    case R.id.imageView4:{
						    if(flag1)
						    {       
						        img_shared.setImageResource(R.drawable.sh_room);
						        txt_shared.setTextColor(Color.parseColor("#FF1919"));
						        System.out.println("inside shared flag1 if condition");
						        roomtype1="Shared%20room,";
						        roomtype=((roomtype==null) ? ((roomtype2==null) ? roomtype1 : (roomtype1.concat(roomtype2))):roomtype1.concat(roomtype));
						        System.out.println("shred room value"+roomtype);
						        flag1=false;
						    }
						    else
						    {
						        img_shared.setImageResource(R.drawable.shared);
						        txt_shared.setTextColor(Color.parseColor("#666666"));
						        System.out.println("inside shared flag1 else condition");
						        roomtype1="";
						       roomtype=roomtype1;
						        flag1=true;
						    }
						        return;
						    }
						  }
					}
				});	
				img_entire.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 switch(v.getId()){
						    case R.id.imageView6:{
						    if(flag2)
						    {       
						        img_entire.setImageResource(R.drawable.home);
						        txt_entire.setTextColor(Color.parseColor("#FF1919"));
						        System.out.println("inside entirehome flag2 if condition");
						        roomtype2="Entire%20Home,";
						        roomtype=((roomtype==null) ? ((roomtype1==null) ? roomtype2 : (roomtype2.concat(roomtype1))):roomtype2.concat(roomtype));
						        System.out.println("entirehome room value"+roomtype);
						        flag2=false;
						    }
						    else
						    {
						        img_entire.setImageResource(R.drawable.home_room);
						        txt_entire.setTextColor(Color.parseColor("#666666"));
						        System.out.println("inside entirehome flag2 else condition");
						        roomtype2="";
						        roomtype=roomtype2;
						        flag2=true;
						    }
						        return;
						    }
						  }
					}
				});	
				
						
			
			value=Integer.parseInt(tv1.getText().toString());
			value1=Integer.parseInt(tv2.getText().toString());
			System.out.println("minimum value before onclick"+value);
			System.out.println("maximum value before onclick"+value1);
			
				
				filters.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//upload_webservice();	
						String guest=String.valueOf(score);
						String min_bed=String.valueOf(score2);
						String min_bedrom=String.valueOf(score3);
						String min_bathrom=String.valueOf(score1);
						String min_price=String.valueOf(tv1.getText().toString());
						String max_price=String.valueOf(tv2.getText().toString());
						
						System.out.println("b"+guest);
						System.out.println("b"+min_bed);
						System.out.println("b"+min_bedrom);
						System.out.println("b"+min_bathrom);
						System.out.println("b"+min_price);
						System.out.println("b"+max_price);
						
						if(guest.equals("1"))
						{
							guest="";
						}
						else
						{
							guest=String.valueOf(score);
						}
						if(min_bed.equals("1"))
						{
							min_bed="";
						}
						else
						{
							min_bed=String.valueOf(score2);
						}
						if(min_bedrom.equals("1"))
						{
							min_bedrom="";
						}
						else
						{
							min_bedrom=String.valueOf(score3);
						}
						if(min_bathrom.equals("0.5"))
						{
							min_bathrom="";
						}
						else
						{
							min_bathrom=String.valueOf(score1);
						}
						if(min_price.equals("10"))
						{
							min_price="";
						}
						else
						{
							min_price=String.valueOf(tv1.getText().toString());
						}
						if(max_price.equals("1000"))
						{
							max_price="";
						}
						else
						{
							max_price=String.valueOf(tv2.getText().toString());
						}
System.out.println(""+guest);
System.out.println(""+min_bed);
System.out.println(""+min_bedrom);
System.out.println(""+min_bathrom);
System.out.println(""+min_price);
System.out.println(""+max_price);
						
						Intent back=new Intent(MoreFilters.this,Search_result.class);
						back.putExtra("guests",guest);
						back.putExtra("roomtypes", roomtype);
						back.putExtra("price_min", min_price);
						back.putExtra("price_max", max_price);
						back.putExtra("min_beds", min_bed);
						back.putExtra("min_bedrooms", min_bedrom);
						back.putExtra("min_bathrooms", min_bathrom);
						back.putExtra("location",location );
						startActivity(back);
						 finish();
											
					}
				});
					
	}

	public void back(View v) {
		System.out.println("inside book on click footer");
		Intent book=new Intent(MoreFilters.this,Search_result.class);
		/*book.putExtra("room_id",roomid);
		book.putExtra("userid",userid);*/
		startActivity(book);
		 finish();
	}
public void SeekBarValueChanged(int Thumb1Value, int Thumb2Value) {
		
		if(Thumb1Value==0)
		{
			value=10;
			
			tv1.setText(""+value);
		}
		else 
		{
			value=Thumb1Value*100;
			
			tv1.setText(""+value );
		}
		
		if(Thumb2Value==0)
		{
			value1=10;
			tv2.setText(""+value1);
		}
		else
		{
			value1=Thumb2Value*100;
			tv2.setText(""+value1);
		}
		
		if(Thumb2Value<Thumb1Value)
		{
			tv2.setText(""+value);
			tv1.setText(""+value1);
			
		}
		/*else
		{
			tv1.setText(""+value);
			tv2.setText(""+value1);
			
		}*/
		//tv1.setText("Thumb 1 Value :" +Thumb1Value );
		//tv1.setText("Thumb 1 Value :" +min );
		//tv2.setText("Thumb 2 Value :"+Thumb2Value) ;
		//System.out.println("Thumb2 Value"+Thumb2Value );
		
		
	}
@Override
public void onBackPressed()
{
	System.out.println("inside book on click footer");
	Intent book=new Intent(MoreFilters.this,Search_result.class);
	book.putExtra("room_id",roomid);
	book.putExtra("userid",userid);
	startActivity(book);
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