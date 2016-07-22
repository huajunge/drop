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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class Optional_rooms_beds extends Activity {

	 ImageButton plus,minus,bedplus,bedminus,bathplus,bathminus,bedroomminus,bedroomplus,back; 
	 String strName,strName1;
	String web_userid,web_beds,web_bedrooms,web_bathrooms,web_guest,web_listingtype,web_hometype;
	String home,privateroom,sharedroom;
	TextView listtype,hometype;
	 String Liveurl="";
	 URL Login_Url,Login_Url1;
	    String reader,reader1;
	    String login_inputline,login_inputline1;
	    String login_status,login_status1;
	    JSONArray login_jsonarray,login_jsonarray1;
	    JSONObject login_jsonobj,login_Error,login_jsonobj1,login_Error1;
	   // ObjectItem[] ObjectItemData;
		public  static String id,type;
		 AlertDialog alertDialogStores;
		 String room_id;
		 TextView beds,bedrooms,bathrooms,guests;
		 String userid,dis,dis1,dis2,imaage,address1,city,state,country,image1,resize;
		 int score;
			double score1;
			int score2;
			int score3;
			String listItemText="";
			ProgressDialog pDialog;
			protected static final String TAG = null;
			String step,check;
			String latitude1,longitude1;
			TextView roomtype;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.optional_rooms_beds);
		getActionBar().hide();
//		 listtype=(TextView) findViewById(R.id.TextView12);
		 hometype=(TextView) findViewById(R.id.TextView13);	
		 roomtype=(TextView) findViewById(R.id.TextView51);	
		 beds=(TextView)findViewById(R.id.TextView1);
		 bedrooms=(TextView)findViewById(R.id.TextView2);
		 bathrooms=(TextView)findViewById(R.id.TextView3);
		 guests=(TextView)findViewById(R.id.TextView9);			 
		 plus=(ImageButton)findViewById(R.id.imageButton05);
		 minus=(ImageButton)findViewById(R.id.imageButton1);
		 bedplus=(ImageButton)findViewById(R.id.imageButton6);
		 bedminus=(ImageButton)findViewById(R.id.imageButton7);
		 bathplus=(ImageButton)findViewById(R.id.imageButton2);
		 bathminus=(ImageButton)findViewById(R.id.imageButton4);
		 bedroomminus=(ImageButton)findViewById(R.id.imageButton5);
		 bedroomplus=(ImageButton)findViewById(R.id.imageButton3);	 
		 back=(ImageButton)findViewById(R.id.imageButton9);	 

		  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	         Liveurl = sharedPreferences.getString("liveurl", null);
		 
	         
	         if( Build.VERSION.SDK_INT >= 9){
	             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	             StrictMode.setThreadPolicy(policy);
	      }
	        
	         
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
	        
			//String latitude1,longitude1;

			latitude1=i.getStringExtra("latitude");
			longitude1=i.getStringExtra("longitude");
			
			/*optional.putExtra("latitude", latitude1);
			optional.putExtra("longitude",longitude1);*/
	             
	         
	         
	         
			 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
			 userid = sharedPreferences2.getString("userid", null);
	         
	         
	         //call_webservice();
	         //*********************************************web service********************************
	         
	         try {
		        	
		        	Login_Url1= new URL(Liveurl+"rooms/view_room?roomid="+room_id);
		        	//Login_Url1= new URL(Liveurl+"rooms/view_room?roomid=5");
		           
		            //Login_Url= new URL(Liveurl+"mobile/signup_second?user_id="+h_id+"&email="+str1+"&password="+str2+"&phone_number="+str3);
		           System.out.println("Login url1  "+Login_Url1);
		           
		           BufferedReader login_reader1;
		            String str_login1="";

		            login_reader1 = new BufferedReader(new InputStreamReader(Login_Url1.openStream()));
		           
		           // String s=Login_Url1.toString();
		           
		            while ((login_inputline1 = login_reader1.readLine())!= null)
		            {
		               
		                str_login1 += login_inputline1;
		                              
		            }
		           

		            System.out.print("url1 in "+str_login1);
		            
		            
		         /*toast=   Toast.makeText(getApplicationContext(), "Email Already Taken", Toast.LENGTH_LONG);
		         toast.setGravity(Gravity.CENTER, 0, 0);
		         toast.show();*/
		           
		           
		            login_jsonarray1 = new JSONArray(str_login1);        
		                   
		             
		             for(int i1=0; i1<login_jsonarray1.length(); i1++)
		                {
		                                  
		            login_jsonobj1 = login_jsonarray1.getJSONObject(i1);
		            login_status1 =    login_jsonobj1.getString("status");	            
		            web_userid=login_jsonobj1.getString("user_id");
		            web_beds=login_jsonobj1.getString("beds");
		            web_bedrooms=login_jsonobj1.getString("bedrooms");
		            web_bathrooms=login_jsonobj1.getString("bathrooms");
		            web_guest=login_jsonobj1.getString("guest");
		            web_listingtype=login_jsonobj1.getString("room_type");
		            web_hometype=login_jsonobj1.getString("home_type");
		                           
		                }

		      System.out.println("after json"+login_status1);
		      
		      System.out.println("after listing type"+web_listingtype);
		      System.out.println("after home type"+web_hometype);
		      System.out.println("beds"+web_beds);
		      System.out.println("bedrooms"+web_bedrooms);
		      System.out.println("bathrooms"+web_bathrooms);
		      System.out.println("guests"+web_guest);
		      
		             
		             if(login_status1.matches("Edit Your Details")){
		                 
		         	                 
		                 Toast.makeText(getApplicationContext(), "Edit Your Details", Toast.LENGTH_SHORT).show();
		               /*   Intent i = new Intent(Optional_rooms_beds.this, Homepage.class);
		                          // i.putExtra("password", get_password);
		                    //Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		                    startActivity(i);
		                    finish(); */  
		             }
		             	          
		             else
		             {
		            	 
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
	         
	            
	         
	         beds.setText(web_beds);
	         bedrooms.setText(web_bedrooms);
	         bathrooms.setText(web_bathrooms);
	         guests.setText(web_guest);
	         if(web_listingtype.equals("")||web_hometype.equals(""))
	         {        	 
	         }
	         else
	         {
//	         listtype.setText(web_listingtype);
	         hometype.setText(web_listingtype);   
	         roomtype.setText(web_hometype);   
	         
	         }
	         score=Integer.parseInt(guests.getText().toString());
	         score3=Integer.parseInt(bedrooms.getText().toString());
	         score2=Integer.parseInt(beds.getText().toString());
	         score1=Double.parseDouble(bathrooms.getText().toString());
	         System.out.println("afer getting room id in optinal details"+room_id);
	         
	         System.out.println("sri"+score);
	         System.out.println("sri"+score1);
	         System.out.println("sri"+score2);
	         System.out.println("sri"+score3);
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
	     			
	     			guests.setText(String.valueOf(score));
	     			System.out.println("sri increase"+String.valueOf(score));
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
	     			
	     			guests.setText(String.valueOf(score));
	     			System.out.println("sri decrease"+String.valueOf(score));
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
	    	
	     	
	     	back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent back1=new Intent(Optional_rooms_beds.this,Optional_details.class);
					back1.putExtra("room_id",room_id);            
					
					back1.putExtra("userid",userid);
					back1.putExtra("image", imaage);
					back1.putExtra("image1", image1);
					back1.putExtra("resize", resize);
					back1.putExtra("title",dis);				
					back1.putExtra("summary", dis1);
					back1.putExtra("price",dis2);
					back1.putExtra("city", city);
					back1.putExtra("state", state);
					back1.putExtra("country", country);
					back1.putExtra("addresss",address1);
					back1.putExtra("latitude", latitude1);
					back1.putExtra("longitude",longitude1);
					back1.putExtra("step",step);
					back1.putExtra("check",check);
					add_description();
					startActivity(back1);
					 finish();
				}
			});
	     	
	     	
	     //***************************************************************end on plus minus functionality*************************	
	         
	           
	         
//		 listtype.setOnClickListener(new View.OnClickListener() {
//				String home1="Entire Room";
//				String privateroom1="Private Room";
//				String sharedroom1="Shared Room";
//				
//				
//				
//				
//				
//				
//				ImageView optional_home,optional_privateroom,optional_sharedroom;
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				/*Intent list_type=new Intent(Optional_rooms_beds.this,Optional_listtype.class);
//				startActivity(list_type);*/
//				
//				final   Dialog d1 = new Dialog(Optional_rooms_beds.this);
//                d1.setContentView(R.layout.optional_listtype);
//                d1.setTitle("Select Listing Type");
//                d1.show();
//                Window window = d1.getWindow();
//				window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//				d1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,820);
//           	 
//            	optional_home=(ImageView)d1.findViewById(R.id.imageView3);
//        		optional_privateroom=(ImageView)d1.findViewById(R.id.imageView4);
//        		optional_sharedroom=(ImageView)d1.findViewById(R.id.ImageView01);
//        		
//        		
//        		
//        		optional_home.setOnClickListener(new View.OnClickListener() {
//        			
//        			@Override
//        			public void onClick(View v) {
//        				// TODO Auto-generated method stub
//        				Intent home2=new Intent(Optional_rooms_beds.this,Optional_rooms_beds.class);
//        				home2.putExtra("home",home1 );
//        				
//        				  
//        				  Intent i=getIntent();
//        					 home=i.getStringExtra(home);
//        					 privateroom=i.getStringExtra(privateroom);
//        					 sharedroom=i.getStringExtra(sharedroom); 
//        					      					 
//        					 listtype.setText(home);
//        					 listtype.setText(privateroom);
//        					 listtype.setText(sharedroom);
//        				 listtype.setText(home);
//        				 listtype.setText(privateroom);
//        				 listtype.setText(sharedroom);
//        				startActivity(home2);
//        				finish();
//        			}
//        		});
//        		
//        optional_privateroom.setOnClickListener(new View.OnClickListener() {
//        			
//        			@Override
//        			public void onClick(View v) {
//        				// TODO Auto-generated method stub
//        				Intent privateroom2=new Intent(Optional_rooms_beds.this,Optional_rooms_beds.class);
//        				privateroom2.putExtra("privateroom",privateroom1 );
//        				
//        				  
//        				  Intent i=getIntent();
//        					 home=i.getStringExtra(home);
//        					 privateroom=i.getStringExtra(privateroom);
//        					 sharedroom=i.getStringExtra(sharedroom);
//        					         					        					 
//        					 listtype.setText(home);
//        					 listtype.setText(privateroom);
//        					 listtype.setText(sharedroom);
//       				 listtype.setText(home);
//       				 listtype.setText(privateroom);
//       				 listtype.setText(sharedroom);
//        				startActivity(privateroom2);
//        				finish();
//        			}
//        		});
//        		
//        		
//        optional_sharedroom.setOnClickListener(new View.OnClickListener() {
//        	
//        	@Override
//        	public void onClick(View v) {
//        		// TODO Auto-generated method stub
//        		Intent sharedroom2=new Intent(Optional_rooms_beds.this,Optional_rooms_beds.class);
//        		sharedroom2.putExtra("sharedroom",sharedroom1 );
//      		  
//      		  Intent i=getIntent();
//      			 home=i.getStringExtra(home);
//      			 privateroom=i.getStringExtra(privateroom);
//      			 sharedroom=i.getStringExtra(sharedroom);  			 
//      			 
//      			 
//      			 listtype.setText(home);
//      			 listtype.setText(privateroom);
//      			 listtype.setText(sharedroom);
//				 listtype.setText(home);
//				 listtype.setText(privateroom);
//				 listtype.setText(sharedroom);
//        		startActivity(sharedroom2);
//        		finish();
//        	}
//        });
//                
//                
//                             
//               		 
//    		 
//    		 
//			}
//		});
		
	       
       
		 
	
		 hometype.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				System.out.println("inside moreoptions");
				final AlertDialog.Builder builderSingle = new AlertDialog.Builder(Optional_rooms_beds.this);
				//builderSingle.setIcon(R.drawable.ic_launcher);
				builderSingle.setTitle("Select Home Type");

				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						Optional_rooms_beds.this,
				        android.R.layout.select_dialog_singlechoice);
				arrayAdapter.add("Entire Home");
				arrayAdapter.add("Private Room");
				arrayAdapter.add("Shared Room");
				
				builderSingle.setNegativeButton(
				        "cancel",
				        new DialogInterface.OnClickListener() {
				            @Override
				            public void onClick(DialogInterface dialog, int which) {
				                dialog.dismiss();
				            }
				        });

				builderSingle.setAdapter(
				        arrayAdapter,
				        new DialogInterface.OnClickListener() {
				            @Override
				            public void onClick(DialogInterface dialog, int which) {
				                 strName = arrayAdapter.getItem(which);
				                 System.out.println("strrrrrrrrrrrrrrrrr" +strName);
				                hometype.setText(strName);
				               /* AlertDialog.Builder builderInner = new AlertDialog.Builder(
				                		Optional_rooms_beds.this);
				                builderInner.setMessage(strName);
				                builderInner.setTitle("Your Selected Item is");
				                builderInner.setPositiveButton(
				                        "Ok",
				                        new DialogInterface.OnClickListener() {
				                            @Override
				                            public void onClick(
				                                    DialogInterface dialog,
				                                    int which) {
				                                dialog.dismiss();
				                            }
				                        });
				                builderInner.show();*/
				            }
				        });
				builderSingle.show();
			       /*try
	                {	      
			        	
			        	System.out.println("Liveurl"+Liveurl);
	                   // Login_Url = new URL(Liveurl+"mobile/log?email="+Get_email+"&password="+Get_password);
	                	Login_Url = new URL(Liveurl+"rooms/other");
	                    
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
	                        
	                        ObjectItem[] ObjectItemData = new ObjectItem[login_jsonarray.length()];
	                     
	                         for(int i=0; i <login_jsonarray.length(); i++)
	                            {
	                            
	                            
	                        login_jsonobj = login_jsonarray.getJSONObject(i);                                        

	                            id =login_jsonobj.getString("id");
	                            type=login_jsonobj.getString("type");
	                            
	                            
	                            System.out.println("id"+id);                            
	                            System.out.println("type of property type"+type);
	                            ObjectItemData[i] = new ObjectItem(id,type);           
	                                             
	                        }
	                      
	                         // our adapter instance
	                         ArrayAdapterItem adapter = new ArrayAdapterItem(getApplicationContext(), R.layout.moreoptions_rowview, ObjectItemData);

	                         // create a new ListView, set the adapter and item click listener
	                         ListView listViewItems = new ListView(Optional_rooms_beds.this);
	                         listViewItems.setAdapter(adapter);
	                         listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());

	                         // put the ListView in the pop up
	                         alertDialogStores = new AlertDialog.Builder(Optional_rooms_beds.this)
	                             .setView(listViewItems)
	                             .setTitle("Select Home type")
	                             .show();                
	                        
	                
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
				
				*/
				
			}
		});
		 

		 roomtype.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				System.out.println("inside moreoptions");
				final AlertDialog.Builder builderSingle = new AlertDialog.Builder(Optional_rooms_beds.this);
				//builderSingle.setIcon(R.drawable.ic_launcher);
				builderSingle.setTitle("Select Home Type");

				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
						Optional_rooms_beds.this,
				        android.R.layout.select_dialog_singlechoice);
				arrayAdapter.add("Apartment");
				arrayAdapter.add("House");
				arrayAdapter.add("Bed and Breakfast");
				
				builderSingle.setNegativeButton(
				        "cancel",
				        new DialogInterface.OnClickListener() {
				            @Override
				            public void onClick(DialogInterface dialog, int which) {
				                dialog.dismiss();
				            }
				        });

				builderSingle.setAdapter(
				        arrayAdapter,
				        new DialogInterface.OnClickListener() {
				            @Override
				            public void onClick(DialogInterface dialog, int which) {
				                 strName1 = arrayAdapter.getItem(which);
				                roomtype.setText(strName1);
				               /* AlertDialog.Builder builderInner = new AlertDialog.Builder(
				                		Optional_rooms_beds.this);
				                builderInner.setMessage(strName);
				                builderInner.setTitle("Your Selected Item is");
				                builderInner.setPositiveButton(
				                        "Ok",
				                        new DialogInterface.OnClickListener() {
				                            @Override
				                            public void onClick(
				                                    DialogInterface dialog,
				                                    int which) {
				                                dialog.dismiss();
				                            }
				                        });
				                builderInner.show();*/
				            }
				        });
				builderSingle.show();
			       /*try
	                {	      
			        	
			        	System.out.println("Liveurl"+Liveurl);
	                   // Login_Url = new URL(Liveurl+"mobile/log?email="+Get_email+"&password="+Get_password);
	                	Login_Url = new URL(Liveurl+"rooms/other");
	                    
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
	                        
	                        ObjectItem[] ObjectItemData = new ObjectItem[login_jsonarray.length()];
	                     
	                         for(int i=0; i <login_jsonarray.length(); i++)
	                            {
	                            
	                            
	                        login_jsonobj = login_jsonarray.getJSONObject(i);                                        

	                            id =login_jsonobj.getString("id");
	                            type=login_jsonobj.getString("type");
	                            
	                            
	                            System.out.println("id"+id);                            
	                            System.out.println("type of property type"+type);
	                            ObjectItemData[i] = new ObjectItem(id,type);           
	                                             
	                        }
	                      
	                         // our adapter instance
	                         ArrayAdapterItem adapter = new ArrayAdapterItem(getApplicationContext(), R.layout.moreoptions_rowview, ObjectItemData);

	                         // create a new ListView, set the adapter and item click listener
	                         ListView listViewItems = new ListView(Optional_rooms_beds.this);
	                         listViewItems.setAdapter(adapter);
	                         listViewItems.setOnItemClickListener(new OnItemClickListenerListViewItem());

	                         // put the ListView in the pop up
	                         alertDialogStores = new AlertDialog.Builder(Optional_rooms_beds.this)
	                             .setView(listViewItems)
	                             .setTitle("Select Home type")
	                             .show();                
	                        
	                
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
				
				*/
				
			}
		});
		 
	}

	/*public class ObjectItem {

	    public String itemId;
	    public String itemName;

	    // constructor
	    public ObjectItem(String itemId, String itemName) {
	        this.itemId = itemId;
	        this.itemName = itemName;
	    }

	}*/


	//here's our beautiful adapter
	/*public class ArrayAdapterItem extends ArrayAdapter<ObjectItem> {

	 Context mContext;
	 int layoutResourceId;
	 ObjectItem data[] = null;

	 public ArrayAdapterItem(Context mContext, int layoutResourceId, ObjectItem[] data) {

	     super(mContext, layoutResourceId, data);

	     this.layoutResourceId = layoutResourceId;
	     this.mContext = mContext;
	     this.data = data;
	 }

	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {

	     
	      * The convertView argument is essentially a "ScrapView" as described is Lucas post 
	      * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
	      * It will have a non-null value when ListView is asking you recycle the row layout. 
	      * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
	      
	     if(convertView==null){
	         // inflate the layout
	         LayoutInflater inflater = ((Optional_rooms_beds.this)).getLayoutInflater();
	         convertView = inflater.inflate(layoutResourceId, parent, false);
	     }

	     // object item based on the position
	     ObjectItem objectItem = data[position];

	     // get the TextView and then set the text (item name) and tag (item ID) values
	     TextView TextViewItem = (TextView) convertView.findViewById(R.id.moreoptionsTextView1);
	     TextViewItem.setText(objectItem.itemName);
	     TextViewItem.setTag(objectItem.itemId);

	     return convertView;

	 }

	}*/


	/*
	 * Here you can control what to do next when the user selects an item
	 */
	/*public class OnItemClickListenerListViewItem implements OnItemClickListener {

	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	        Context context = view.getContext();

	        TextView TextViewItem = ((TextView) view.findViewById(R.id.moreoptionsTextView1));

	        // get the clicked item name
	         listItemText = TextViewItem.getText().toString();

	        // get the clicked item ID
	        String listItemId = TextViewItem.getTag().toString();

	        Intent location=new Intent(Optional_rooms_beds.this,Optional_rooms_beds.class);
	        location.putExtra("propertyid",listItemId);
	        location.putExtra("propertytype",listItemText );
	        startActivity(location);
	        
	        hometype.setText(listItemText);
	        System.out.println("hometype" +listItemText);
	        // just toast it
	        Toast.makeText(context, "Item: " + listItemText + ", Item ID: " + listItemId, Toast.LENGTH_SHORT).show();

	        ((Optional_rooms_beds) context).alertDialogStores.cancel();

	    }

	} */
	
	public void add_description()
	{
		   
		  if(strName!=null)
		   {
			  strName=strName.replaceAll(" ","%20");
			  
				
		   }
		  if(strName1!=null)
		   {
			  strName1=strName1.replaceAll(" ","%20");
			 
				
		   }
		  
		  if(strName==null)
		   {
			   strName= web_listingtype;
			   System.out.println("webbbbbbbbbbbb" +web_hometype);
			   strName=strName.replaceAll(" ","%20");
	
		   }
		  
		  if(strName1==null)
		   {
			   strName1= web_hometype;
			   System.out.println("webbbbbbbbbbbb" +web_listingtype);
			   strName1=strName1.replaceAll(" ","%20");
			
		   }
		  
		/*  hometype.setText(web_listingtype);   
	         roomtype.setText(web_hometype); */
		
		final String url=Liveurl+"rooms/edit_room?roomid="+room_id+"&guest="+String.valueOf(score)+"&bathrooms="+String.valueOf(score1)+"&beds="+String.valueOf(score2)+"&bedrooms="+String.valueOf(score3)+"&home_type="+strName1+"&room_type="+strName;
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
  					                Toast.makeText(getApplicationContext(), "Rooms & Beds added  Successfully", Toast.LENGTH_SHORT).show();
  					                finish();   
  					             }
  							
  					/*		System.out.println("roomid"+room_id);                            
  		                    System.out.println("space"+space);*/
  		         
  							
  						} catch (JSONException e) {
  							e.printStackTrace();
  						}
  						/*Intent i1=new Intent(Optional_rooms_beds.this,Optional_details.class);
  			           
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

  						startActivity(i1);
  						finish();*/
  						
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