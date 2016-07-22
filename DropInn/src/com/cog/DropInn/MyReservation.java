package com.cog.DropInn;



import info.androidhive.customlistviewvolley.adater.MyReservationAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.XListView.IXListViewListener;


public class MyReservation extends Fragment implements IXListViewListener{
	private static final String TAG = Yourlisting.class.getSimpleName();
	Bitmap edbitmap;
	Bitmap edbitmap1;
	private MyReservationAdapter adapter;
	 String Liveurl="";
	 URL Login_Url;
	    String reader,userid;
	    String login_inputline;
	    String login_status;
	    JSONArray login_jsonarray;
	    JSONObject login_jsonobj,login_Error;
	     ListView listViewItems2;
	     ImageView bg_img;
	     RelativeLayout layout;
		 TextView text1,text3,text4;
		 Button explore;
	     String roomid;
	     XListView    listView ;
	     int start=1;
	 	private ArrayList<String> items = new ArrayList<String>();
		private int start1 = 0;
		private static int refreshCnt = 0;
	     String name="ramesh";
	     private ProgressDialog pDialog;
	 	private List<Movie> movieList = new ArrayList<Movie>();
	 	String currency1;
		 int fragVal;
		 
		 static Yourlisting init(int val) {
			 Yourlisting truitonFrag = new Yourlisting();
		 // Supply val input as an argument.
		 Bundle args = new Bundle();
		 args.putInt("val", val);
		 truitonFrag.setArguments(args);
		 return truitonFrag;
		 }
	 	 @Override
		    public View onCreateView(LayoutInflater inflater, ViewGroup container,
		            Bundle savedInstanceState) {
		 
		        View myhost1 = inflater.inflate(R.layout.myreservation, container, false);
		        
		    	this.getActivity().getActionBar().setTitle("Discover");
				  if( Build.VERSION.SDK_INT >= 9){
		              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		              StrictMode.setThreadPolicy(policy);
		       }
				  
				SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
		        Liveurl = sharedPreferences1.getString("liveurl", null);
		       
				 userid = sharedPreferences1.getString("userid", null);
		        currency1= sharedPreferences1.getString("currency1", null);
		        System.out.println("******"+currency1);
				  
		        Intent i=this.getActivity().getIntent();
				// userid1=i.getStringExtra("userid");
				 roomid=i.getStringExtra("room_id");
				 //System.out.println("userid in discover page==== "+userid1);
				 //image=i.getStringExtra("profile");
				// explore=(Button)myhost1.findViewById(R.id.button1);
			      // bg_img=(ImageView)myhost1.findViewById(R.id.imageView1);
				   listView = (XListView)myhost1. findViewById(R.id.listView5);
			       layout=(RelativeLayout)myhost1.findViewById(R.id.beforelogin);
			   /*    text3=(TextView)myhost1.findViewById(R.id.textView3);
			       text4=(TextView)myhost1.findViewById(R.id.textView4);*/
			      // text1=(TextView)myhost1.findViewById(R.id.textView1);
				 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		         Editor editor = sharedPreferences.edit();
		        // System.out.println("userid in discover page upload to  shared preferences "+userid1);
		        // editor.putString("userid", userid1); 
		         editor.putString("liveurl", Liveurl);
		         editor.commit();
		        /* SharedPreferences settings = getSharedPreferences(Edit_profile.PREFS_NAME,0);
		         userid = settings.getString("userid", User_id);
		         System.out.println("userid in discover page after getting   shared preferences "+userid);*/
		         //header=(RelativeLayout)discover.findViewById(R.id.head);
		         //text=(TextView)discover.findViewById(R.id.textView1);
		         //mic=(ImageView)discover.findViewById(R.id.mic);
		        /* text.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					Intent header=new Intent(getActivity(),SearchActivity.class);
					header.putExtra("userid", userid);
					startActivity(header);
					getActivity().finish();
					}
				});
		         
		         header.setOnClickListener(new View.OnClickListener() {
		 			
		 			@Override
		 			public void onClick(View v) {
		 				// TODO Auto-generated method stub
		 			Intent header=new Intent(getActivity(),SearchActivity.class);
		 			header.putExtra("userid", userid);
		 			startActivity(header);
		 			getActivity().finish();
		 			}
		 		});
		         mic.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						promptSpeechInput();
					}
				});*/
		         
		        
		      
				adapter = new MyReservationAdapter(getActivity(), movieList);
				listView.setAdapter(adapter);
				
			
				
				//listView.setSelectionFromTop(3, 0);
				
				listView.setItemsCanFocus(false);
				listView.setPullLoadEnable(true);
				listView.setXListViewListener( this);
				
				listView.hide();
				
				    
				//listView.setOnItemClickListener(new ListDiscover());
						/*listView.setOnScrollListener(new OnScrollListener() {
					        private int mLastFirstVisibleItem;

					        public void onScrollStateChanged(AbsListView view, int scrollState) {

					        }

					        public void onScroll(AbsListView view, int firstVisibleItem,
					                int visibleItemCount, int totalItemCount) {
					      
					        	
						
					             

					            if(mLastFirstVisibleItem<firstVisibleItem)
					            {
					                Log.i("SCROLLING DOWN","TRUE");
					               // header.setVisibility(View.INVISIBLE);
					               
					            
					            }
					            if(mLastFirstVisibleItem>firstVisibleItem)
					            {
					                Log.i("SCROLLING UP","TRUE");
					               // header.setVisibility(View.VISIBLE);
					                
					                
					            }
					            mLastFirstVisibleItem=firstVisibleItem;
					            
					          
					                //Algorithm to check if the last item is visible or not
					              

					        }
					    });*/
				//listView.setOnItemClickListener(new ListDiscover());
						
						
				
				System.out.println("after onclick listener");
				
				adapter.notifyDataSetChanged();
				pDialog = new ProgressDialog(getActivity());
				// Showing progress dialog before making http request
				
				pDialog.setCancelable(false);
				pDialog.show();
				pDialog.setContentView(R.layout.progress_dialog);
					
				if(currency1==null)
				{
					currency1="USD";
				}
		        //final String url = Liveurl+"search/discover";
				   if (isOnline(getActivity())) {
		       final String url=Liveurl+"rooms/myreservations?common_currency="+currency1+"&start="+start+"&user_id="+userid;
		       System.out.println("discover url" +url);
				 //final String url=Liveurl+"search/discover";
		//*******************************************ListView code start*****************************************************
		     
		 
		      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
		        
				// changing action bar color
			

				// Creating volley request obj
				
				JsonArrayRequest movieReq = new JsonArrayRequest(url,
						new Response.Listener<JSONArray>() {
							@Override
							public void onResponse(JSONArray response) {
								Log.d(TAG, response.toString());
								hidePDialog();
								
							

								// Parsing json
								for (int i = 0; i < response.length(); i++) {
									try {
										JSONObject obj = response.getJSONObject(i);

										login_status=obj.getString("resstatus");
										/*if(i!=0||i!=1||i!=3)
										{*/
										if(login_status.matches("No Data Found"))
										{
											layout.setVisibility(View.VISIBLE);
											/* explore.setOnClickListener(new View.OnClickListener() {
													
													@Override
													public void onClick(View v) {
														// TODO Auto-generated method stub
														if(explore.getText().equals("Log In"))
														{
														Intent back=new Intent(getActivity(),MainActivity.class);
														getActivity().startActivity(back);
														}
														else
														{
															
														}
														
													}
												});*/
											
										}
										else
										{
											 pDialog = new ProgressDialog(getActivity());
							         	   
						         		// Showing progress dialog before making http request
						         	
						         		pDialog.setCancelable(false);
						         		pDialog.show();
						         		pDialog.setContentView(R.layout.progress_dialog);
									//explore.setText("Start Explore");
									layout.setVisibility(View.INVISIBLE);
								//	text1.setVisibility(View.INVISIBLE);
									/*text3.setVisibility(View.INVISIBLE);
									text4.setVisibility(View.INVISIBLE);*/
																	
								Movie movie = new Movie();						
										
									
										movie.getid(obj.getString("id"));
										movie.getuserid(obj.getString("user_id"));
											movie.getaddress(obj.getString("address"));	
										/*country_symbol=obj.getString("common_currency_code");
										Currency c  = Currency.getInstance(country_symbol);    
										country_symbol=c.getSymbol();*/
										movie.getprice(obj.getString("price")); 
										movie.getuserby(obj.getString("user_by"));
										movie.resid(obj.getString("list_id"));
										movie.tripstatus(obj.getString("resstatus"));
										movie.getid1(obj.getString("room_type"));
										//movie.userto(obj.getString("userto"));
										//movie.isread(obj.getString("isread"));
										
										//country_symbol=obj.getString("common_currency_code");
										 
										//movie.setsymbol(country_symbol);
										
									
										/*movie.getaddress(obj.getString("username"));									
										movie.getprice(obj.getString("price"));
										country_symbol=obj.getString("currency_code");
										 //Currency c  = Currency.getInstance(country_symbol);    
										 //country_symbol=c.getSymbol();
										movie.setsymbol(country_symbol);*/
										movie.setTitle(obj.getString("title"));
										movie.setThumbnailUrl(obj.getString("profile_pic")); 
										//movie.setstatus(obj.getString("status"));
										movie.setcheckin(obj.getString("checkin"));
										movie.setcheckout(obj.getString("checkout"));
										//movie.setcreated(obj.getString("created"));	
										movie.guest(obj.getString("no_quest"));
										//movie.message(obj.getString("message"));
										//movie.username(obj.getString("username"));
										movie.getprice(obj.getString("common_currency_value")); 
										movie.setThumbnailUrl1(obj.getString("resize1"));
										movie.getcity(obj.getString("city"));
										// adding movie to movies array
										
										movieList.add(movie);
									//footerView.setVisibility(View.VISIBLE);
										//}
										/*else
										{
											JSONObject obj = response.getJSONObject(i);
											Movie movie = new Movie();
											movie.getid(obj.getString("id"));
											movieList.add(movie);
										}*/
										hidePDialog();
										
										}} catch (JSONException e) {	
										e.printStackTrace();
										hidePDialog();
									}

								}

								
								// notifying list adapter about data changes
								// so that it renders the list view with updated data
								adapter.notifyDataSetChanged();
							}
						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								VolleyLog.d(TAG, "Error: " + error.getMessage());
								hidePDialog();

							}
						});

				// Adding request to request queue
				AppController.getInstance().addToRequestQueue(movieReq);
				
				System.out.println("after loop listview view ");
				
				start=start+5;
				   }
				     else
				     {
				    		Toast.makeText(getActivity(), "Check your internet connection",Toast.LENGTH_SHORT).show();
				    		hidePDialog();
				     }
		         
					return myhost1;
					
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
			/**
			 * Slide menu item click listener
			 * */
			

			/*** Showing google speech input dialog ***/
		/*	private void promptSpeechInput() {
			    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
			            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
			    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
			            getString(R.string.speech_prompt));
			    try {
			        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
			    } catch (ActivityNotFoundException a) {
			        Toast.makeText(getActivity().getApplicationContext(),
			                getString(R.string.speech_not_supported),
			                Toast.LENGTH_SHORT).show();
			    }
			}
			*//*** Receiving speech input***//*
			@Override
			public void onActivityResult(int requestCode, int resultCode, Intent data) {
			    super.onActivityResult(requestCode, resultCode, data);
			    switch (requestCode) {
			    case REQ_CODE_SPEECH_INPUT: {
			        if (resultCode == RESULT_OK && null != data) {

			            ArrayList<String> result = data
			                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			            Intent i=new Intent(getActivity(),Search_result.class);
			            i.putExtra("speech", result.get(0));
			            getActivity().startActivity(i);
			        }
			        break;
			    }

			    }
			}*/
			
			private void onLoad() {
				listView.stopRefresh();
				listView.stopLoadMore();
				listView.setRefreshTime("5");
			}
			
			public void onRefresh() {
				System.out.println("refresh");
			
						start = ++refreshCnt;
						items.clear();
						
					
					         
						onLoad();
					}
			
			
			

			public void onLoadMore() {
				
					
				 if (isOnline(getActivity())) {
						
						 final String url=Liveurl+"rooms/myreservations?common_currency="+currency1+"&start="+start+"&user_id="+userid;
					       System.out.println("discover url" +url);
							 //final String url=Liveurl+"search/discover";
					//*******************************************ListView code start*****************************************************
					     
					 
					      // Toast.makeText(getApplicationContext(), "User Id is"+userid,Toast.LENGTH_SHORT).show();
					        
							// changing action bar color
							

							// Creating volley request obj
							
							JsonArrayRequest movieReq = new JsonArrayRequest(url,
									new Response.Listener<JSONArray>() {
										@Override
										public void onResponse(JSONArray response) {
											Log.d(TAG, response.toString());
											hidePDialog();
											
											System.out.println("3 images" +response.length());
											
											
											// Parsing json
											for (int i = 0; i < response.length(); i++) {
												try {

													
													/*if(i!=0||i!=1||i!=3)
													{*/
													JSONObject obj = response.getJSONObject(i);

													login_status=obj.getString("resstatus");
													/*if(i!=0||i!=1||i!=3)
													{*/
													if(login_status.matches("No Data Found"))
													{
														listView.stopLoadMore();
														listView.hide();
														 Toast.makeText(getActivity(), "No more list found", Toast.LENGTH_SHORT).show();
														  /*explore.setOnClickListener(new View.OnClickListener() {
																
																@Override
																public void onClick(View v) {
																	// TODO Auto-generated method stub
																	if(explore.getText().equals("Log In"))
																	{
																	Intent back=new Intent(getActivity(),MainActivity.class);
																	getActivity().startActivity(back);
																	}
																	else
																	{
																		
																	}
																	
																}
															});*/
														
													}
													else
													{
														 pDialog = new ProgressDialog(getActivity());
										         	   
									         		// Showing progress dialog before making http request
									         	
									         		pDialog.setCancelable(false);
									         		pDialog.show();
									         		pDialog.setContentView(R.layout.progress_dialog);
												//explore.setText("Start Explore");
												//layout.setVisibility(View.INVISIBLE);
												//text1.setVisibility(View.INVISIBLE);
												/*text3.setVisibility(View.INVISIBLE);
												text4.setVisibility(View.INVISIBLE);*/
																				
											Movie movie = new Movie();						
													
												
													movie.getid(obj.getString("id"));
													movie.getuserid(obj.getString("user_id"));
														movie.getaddress(obj.getString("address"));	
													/*country_symbol=obj.getString("common_currency_code");
													Currency c  = Currency.getInstance(country_symbol);    
													country_symbol=c.getSymbol();*/
													movie.getprice(obj.getString("price")); 
													movie.getuserby(obj.getString("user_by"));
													movie.resid(obj.getString("list_id"));
										
													movie.getid1(obj.getString("room_type"));
													//movie.userto(obj.getString("userto"));
													//movie.isread(obj.getString("isread"));
													movie.tripstatus(obj.getString("resstatus"));
													//country_symbol=obj.getString("common_currency_code");
													 
													//movie.setsymbol(country_symbol);
													
												
													/*movie.getaddress(obj.getString("username"));									
													movie.getprice(obj.getString("price"));
													country_symbol=obj.getString("currency_code");
													 //Currency c  = Currency.getInstance(country_symbol);    
													 //country_symbol=c.getSymbol();
													movie.setsymbol(country_symbol);*/
													movie.setTitle(obj.getString("title"));
													movie.setThumbnailUrl(obj.getString("profile_pic")); 
													//movie.setstatus(obj.getString("status"));
													movie.setcheckin(obj.getString("checkin"));
													movie.setcheckout(obj.getString("checkout"));
													//movie.setcreated(obj.getString("created"));	
													movie.guest(obj.getString("no_quest"));
													//movie.message(obj.getString("message"));
													//movie.username(obj.getString("username"));
													movie.getprice(obj.getString("common_currency_value")); 
													movie.setThumbnailUrl1(obj.getString("resize1"));
													movie.getcity(obj.getString("city"));
													// adding movie to movies array
													
													movieList.add(movie);
												//footerView.setVisibility(View.VISIBLE);
													//}
													/*else
													{
														JSONObject obj = response.getJSONObject(i);
														Movie movie = new Movie();
														movie.getid(obj.getString("id"));
														movieList.add(movie);
													}*/
													hidePDialog();
													listView.stopLoadMore();
													listView.hide();
													}
												} catch (JSONException e) {	
													e.printStackTrace();
													listView.stopLoadMore();
													 Toast.makeText(getActivity(), "No more list found", Toast.LENGTH_SHORT).show();
													 listView.hide();
												}

											}

											
											// notifying list adapter about data changes
											// so that it renders the list view with updated data
											adapter.notifyDataSetChanged();
										}
									}, new Response.ErrorListener() {
										@Override
										public void onErrorResponse(VolleyError error) {
											VolleyLog.d(TAG, "Error: " + error.getMessage());
											
											hidePDialog();

										}
									});

							// Adding request to request queue
							AppController.getInstance().addToRequestQueue(movieReq);
							
							System.out.println("after loop listview view ");
							
							start=start+5;
						//onLoad();
				 }
			     else
			     {
			    		Toast.makeText(getActivity(), "Check your internet connection",Toast.LENGTH_SHORT).show();
			     }
			
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



			
			
