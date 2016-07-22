package com.cog.DropInn;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.adater.TabPagerAdapter1;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.slidingmenu.adapter.NavDraweListAdapterhost;
import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;


import info.androidhive.slidingmenu.model.NavDrawerItem;
import info.androidhive.slidingmenu.model.NavDrawerItemhost;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;


@SuppressLint("WrongViewCast")
public class Swipe_tabshost extends FragmentActivity {
	String summary;
	ViewPager Tab;
    TabPagerAdapter1 TabAdapter1;
	ActionBar actionBar;
	String LiveUrl="http://demo.cogzidel.com/mdropinn_ios_php/mobile/";
	//String LiveUrl="http://10.0.2.2/sri/mdropinn_ios_php2/mobile/";
	private static final String TAG = Swipe_tabs.class.getSimpleName();
	public DrawerLayout mDrawerLayout;
	public ListView mDrawerList;	
	FrameLayout logout,header;
	Bitmap myBitmap;
	int currentPage=0;
	String User_id,profile_image;
	private static LruCache<String, Typeface> sTypefaceCache =
            new LruCache<String, Typeface>(12);
	TextView text,switch1;
	//  av drawer title
	private CharSequence mDrawerTitle;
	// used to store app title
	private CharSequence mTitle;
	String userid="";
	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
String email_id;
	private List<Movie> navDrawerItems=new ArrayList<Movie>();
	private NavDraweListAdapterhost adapter2;	
	ImageView imgIcon;
	//String profile_img;
	 ColorDrawable colorDrawable;
	   SharedPreferences sharedpreferences;
	// Movies json url
	private ProgressDialog pDialog;
	private List<Movie> movieList = new ArrayList<Movie>();	
	private CustomListAdapter adapter;
	
		
	public String userid1;
	public String roomid;
	private String fb_token,fb_user;
	private String signup_userid;
	private String signin_userid,profile_image1;
	ImageView ImageView;
	TextView TextView4;
	String profile_imag;

	int tabselect=0;
	ImageView Image1,Image2,Image3,Image4,Image5,Img1A,Img2A,Img3A,Img4A;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_tabshost);
       
        for (int j=0;j<2;j++)
        {
        	Movie movie = new Movie();
        	movie.getprice("india");
        	navDrawerItems.add(movie);
        }
    
       
        
        actionBar = getActionBar();
        
        // add the custom view to the action bar
        actionBar.setCustomView(R.layout.actionbar_viewhost);
        
        actionBar.setBackgroundDrawable(
        		
				new ColorDrawable(Color.parseColor("#ffffff")));
        
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        
        Image1= (ImageView)findViewById(R.id.Img1);
        Image2= (ImageView)findViewById(R.id.Img2);
     /*   Image3= (ImageView)findViewById(R.id.Img3);
        Image4= (ImageView)findViewById(R.id.Img4);*/
        Image5= (ImageView)findViewById(R.id.Img5);
        
        Img1A= (ImageView)findViewById(R.id.Img1A);
        Img2A= (ImageView)findViewById(R.id.Img2A);
      /*  Img3A= (ImageView)findViewById(R.id.Img3A);
        Img4A= (ImageView)findViewById(R.id.Img4A);*/
        
        //getActionBar().setDisplayShowTitleEnabled(false);
        //getActionBar().setDisplayShowHomeEnabled(false);
        //ImageView =(ImageView)findViewById(R.id.ImageView);
        TextView4 =(TextView)findViewById(R.id.TextView4);
    	
        this.getActionBar().setTitle("Discover");
		  if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
     }
		  getFbKeyHash("com.cog.DropInn");


		 
		  
        Intent i1=getIntent();
		 //userid1=i1.getStringExtra("userid");
		 roomid=i1.getStringExtra("room_id");
		 email_id=i1.getStringExtra("email");
		 profile_imag=i1.getStringExtra("profpic");

		 System.out.println("userid in swipe_tab page ==== "+userid1);
         System.out.println("Email id in Swipe tab page==="+email_id);
        

		  
	    
		 
         SharedPreferences sharedPreferences3 = PreferenceManager.getDefaultSharedPreferences(this);
         Editor editor = sharedPreferences3.edit();
         editor.putString("liveurl", LiveUrl);                                     
         editor.commit();
         
         SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		 profile_image1 = sharedPreferences2.getString("profpic", null);
		 System.out.println("userid in swipe_tab page1 ==== "+userid);

         
        String restoredText = sharedPreferences3.getString("liveurl", null);
         
         
         System.out.println("Liveurl value--->"+restoredText);
         
      
         

		/*	if(profile_imag!=null)
			{
				 
			try{	
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
             .permitAll().build();
     StrictMode.setThreadPolicy(policy);
     String url =profile_imag;


     System.out.println("image" +profile_image);
     BitmapFactory.Options bmOptions;
     bmOptions = new BitmapFactory.Options();	
     bmOptions.inSampleSize = 1;
     Bitmap bm = loadBitmap(url, bmOptions);
     profile_img.setImageBitmap(getRoundedShape(bm));
	 	profile_img.setScaleType(ScaleType.FIT_XY);

	 
			//System.out.println("Bitmap Images *************"+edbitmap);
					
			}
			
			catch(Exception e){
			 	e.printStackTrace();
			 	}
			}*/
		
			// adding movie to movies array


	
		 
		 

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor1 = sharedPreferences.edit();
        System.out.println("userid in swipe_tab  page   shared preferences "+userid1);
        System.out.println("userid in swipe_tab  page   shared preferences "+email_id);
        //editor1.putString("userid", userid1); 
       // editor1.putString("email", email_id);
        editor1.commit();
          
          
       

          
        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		 //  userid1 = sharedPreferences1.getString("userid",null);
		System.out.println("userid in shared preferences in swipe_tabs "+userid1);
	  /* email_id = sharedPreferences1.getString("email", null);	       
        System.out.println("View _profile page email=== "+email_id);*/
	
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
     	mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
     	
     	
        TabAdapter1 = new TabPagerAdapter1(getSupportFragmentManager());
        TabAdapter1.notifyDataSetChanged();
        
        Tab = (ViewPager)findViewById(R.id.pager);
        Tab.setOffscreenPageLimit(1);
        
       
    /*  //Bind the title indicator to the adapter
        TitlePageIndicator titleIndicator = (TitlePageIndicator)findViewById(R.id.titles);
        titleIndicator.setViewPager(Tab);*/
    
       /* Img1A.setVisibility(View.VISIBLE);
		Img2A.setVisibility(View.INVISIBLE);
		Img3A.setVisibility(View.INVISIBLE);
		Img4A.setVisibility(View.INVISIBLE);*/
   
        
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    	currentPage = position;
                    	System.out.println("Inside swipe_tab page position==="+position);
                    	actionBar = getActionBar();
                    	
                    	if (position==0)
                    	{
                    		Image2.setImageResource(R.drawable.yourlisting_nor);
                			Image1.setImageResource(R.drawable.hostlist);
                			/*Image3.setImageResource(R.drawable.msg_nor);
                			Image4.setImageResource(R.drawable.travel_nor);*/
                			Image5.setImageResource(R.drawable.profile_nor);
                			Img1A.setVisibility(View.VISIBLE);
                			Img2A.setVisibility(View.INVISIBLE);
                			/*Img3A.setVisibility(View.INVISIBLE);
                			Img4A.setVisibility(View.INVISIBLE);*/
                    	}
                    	
                    	if (position==1)
                    	{
                    		Image2.setImageResource(R.drawable.yourlisting);
        	     			Image1.setImageResource(R.drawable.hostlist_nor);
        	     			/*Image3.setImageResource(R.drawable.msg_nor);
        	     			Image4.setImageResource(R.drawable.travel_nor);*/
        	     			Image5.setImageResource(R.drawable.profile_nor);
        	     			Img2A.setVisibility(View.VISIBLE);
                			Img1A.setVisibility(View.INVISIBLE);
                			/*Img3A.setVisibility(View.INVISIBLE);
                			Img4A.setVisibility(View.INVISIBLE);*/
                    	}
                    	
                    	/*if (position==2)
                    	{
                    		Image2.setImageResource(R.drawable.searchnew_not);
                 			Image1.setImageResource(R.drawable.menu_not);
                 			Image3.setImageResource(R.drawable.msg);
                 			Image4.setImageResource(R.drawable.travel_nor);
                 			Image5.setImageResource(R.drawable.usernew_nor);
                 			
                 			Img3A.setVisibility(View.VISIBLE);
                			Img2A.setVisibility(View.INVISIBLE);
                			Img1A.setVisibility(View.INVISIBLE);
                			Img4A.setVisibility(View.INVISIBLE);
                    	}
                    	
                    	if (position==3)
                    	{
                    		Image2.setImageResource(R.drawable.searchnew_not);
                			Image1.setImageResource(R.drawable.menu_not);
                			Image3.setImageResource(R.drawable.msg_nor);
                			Image4.setImageResource(R.drawable.travel);
                			Image5.setImageResource(R.drawable.usernew_nor);
                			
                			Img4A.setVisibility(View.VISIBLE);
                			Img2A.setVisibility(View.INVISIBLE);
                			Img3A.setVisibility(View.INVISIBLE);
                			Img1A.setVisibility(View.INVISIBLE);
                    	}*/
                    	
                    	//actionBar.setSelectedNavigationItem(position);  
                    /*	if (position==1)
                    	{
                    		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                	        imm.toggleSoftInput(0, 0);
                    	}*/
                    	
                    		
                    
                    }
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    	
                    
                        // Code goes here
                    }

                    // Called when the scroll state changes: 
                    // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
                    @Override
                    public void onPageScrollStateChanged(int state) {
                        // Code goes here
                    }
                });
        Tab.setAdapter(TabAdapter1);
        
       // actionBar.setDisplayShowTitleEnabled(false);
    
     
        //Enable Tabs on Action Bar
       // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    	actionBar.setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#ffffff")));
    	
	     	logout=(FrameLayout)findViewById(R.id.logout);
	     	header=(FrameLayout)findViewById(R.id.header);
	     	

	     	
	     	//navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], false));
	     
	     	/*navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));*/
	     	
	  
	     	

	     	//navMenuIcons.recycle();

	     	

	     	// setting the nav drawer list adapter
	     	adapter2 = new NavDraweListAdapterhost(this,navDrawerItems);
	     	mDrawerList.setAdapter(adapter2);
	     	//mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
	     	 mDrawerList.setFitsSystemWindows(true);
	   
	     	// enabling action bar app icon and behaving it as toggle button
	     	getActionBar().setDisplayHomeAsUpEnabled(false);
	     	getActionBar().setHomeButtonEnabled(true);
	 
	     	
	     /*	if (userid==null)
	     	{
	     		switch1.setText("Log In or Sign Up");
	     	}
	     	
	     	if (userid!=null)
	     	{
	     		//ImageView.setVisibility(View.GONE);
	     		logout.setBackgroundColor(Color.parseColor("#ffffff"));
	     		//TextView4.setTextColor(Color.parseColor("#ffffff"));
	     	}*/
	     	
	     	/* if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
		          	Image5.setBackgroundResource(R.drawable.usernew_nor);
		               mDrawerLayout.closeDrawer(Gravity.RIGHT);
		           } else {
		          	 Image5.setBackgroundResource(R.drawable.usernew);
		               mDrawerLayout.openDrawer(Gravity.RIGHT);
		           }*/
	     
	     	Image5.setOnClickListener(new View.OnClickListener() {
	     		
	     		@Override
				public void onClick(View v) {
	     
	     	
	     	  if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
	          	Image5.setImageResource(R.drawable.profile_nor);
	               mDrawerLayout.closeDrawer(Gravity.RIGHT);
	           } else {
	          	 Image5.setImageResource(R.drawable.profile);
	               mDrawerLayout.openDrawer(Gravity.RIGHT);
	           }
	     		}
	     	});
	     	
	    
	     	
Image2.setOnClickListener(new View.OnClickListener() {
	     		
	     		@Override
				public void onClick(View v) {
	     			
	     			Image2.setImageResource(R.drawable.yourlisting);
	     			Image1.setImageResource(R.drawable.hostlist_nor);
	     		/*	Image3.setImageResource(R.drawable.msg_nor);
	     			Image4.setImageResource(R.drawable.travel_nor);*/
	     			Image5.setImageResource(R.drawable.profile_nor);
	     			
	     			Img2A.setVisibility(View.VISIBLE);
	    		/*	Img4A.setVisibility(View.INVISIBLE);
	    			Img3A.setVisibility(View.INVISIBLE);*/
	    			Img1A.setVisibility(View.INVISIBLE);
	     			
	     /*	FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	     			Search fragment = new Search();
	     		
	     			 ft.replace(R.id.frame_container, fragment);
	     			ft.addToBackStack(null);
	     			ft.commit();*/
	     			Tab.setCurrentItem(1);
	     		    
	     		}
	     		});

/*Image3.setOnClickListener(new View.OnClickListener() {
		
		@Override
	public void onClick(View v) {
			
			Image2.setImageResource(R.drawable.searchnew_not);
 			Image1.setImageResource(R.drawable.menu_not);
 			Image3.setImageResource(R.drawable.msg);
 			Image4.setImageResource(R.drawable.travel_nor);
 			Image5.setImageResource(R.drawable.usernew_nor);
 			System.out.println("tab indicator");
 			Img3A.setVisibility(View.VISIBLE);
			Img2A.setVisibility(View.INVISIBLE);
			Img4A.setVisibility(View.INVISIBLE);
			Img1A.setVisibility(View.INVISIBLE);
 			
	FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			Search fragment = new Search();
		
			 ft.replace(R.id.frame_container, fragment);
			ft.addToBackStack(null);
			ft.commit();
			Tab.setCurrentItem(2);
		    
		}
		});

Image4.setOnClickListener(new View.OnClickListener() {
	
	@Override
public void onClick(View v) {
		
		Image2.setImageResource(R.drawable.searchnew_not);
			Image1.setImageResource(R.drawable.menu_not);
			Image3.setImageResource(R.drawable.msg_nor);
			Image4.setImageResource(R.drawable.travel);
			Image5.setImageResource(R.drawable.usernew_nor);
			
			Img4A.setVisibility(View.VISIBLE);
			Img2A.setVisibility(View.INVISIBLE);
			Img3A.setVisibility(View.INVISIBLE);
			Img1A.setVisibility(View.INVISIBLE);
	
 			
FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Search fragment = new Search();
	
		 ft.replace(R.id.frame_container, fragment);
		ft.addToBackStack(null);
		ft.commit();
		Tab.setCurrentItem(3);
	    
	}
	});*/

Image1.setOnClickListener(new View.OnClickListener() {
	
	@Override
public void onClick(View v) {
		
		Image2.setImageResource(R.drawable.yourlisting_nor);
			Image1.setImageResource(R.drawable.hostlist);
		/*	Image4.setImageResource(R.drawable.msg_nor);
			Image4.setImageResource(R.drawable.travel_nor);*/
			Image5.setImageResource(R.drawable.profile_nor);
			
			Img1A.setVisibility(View.VISIBLE);
			Img2A.setVisibility(View.INVISIBLE);
		/*	Img3A.setVisibility(View.INVISIBLE);
			Img4A.setVisibility(View.INVISIBLE);*/
 			
/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Search fragment = new Search();
	
		 ft.replace(R.id.frame_container, fragment);
		ft.addToBackStack(null);
		ft.commit();*/
		Tab.setCurrentItem(0);
	    
	}
	});
	     // ************************* Sliding Menu Code End *****************************	
TextView4.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (userid!=null)
					{
					 Intent switch1=new Intent(Swipe_tabshost.this,Swipe_tabs.class);
					 pDialog = new ProgressDialog(Swipe_tabshost.this);
						// Showing progress dialog before making http request
						
						pDialog.setCancelable(false);
						pDialog.show();
						pDialog.setContentView(R.layout.progress_dialog);
					 switch1.putExtra("userid",userid);
					 switch1.putExtra("email",email_id);
					
					 	startActivity(switch1);
					 	finish();
					}
					else if (userid==null)
					{
						Intent switch1=new Intent(Swipe_tabshost.this,MainActivity.class);
					
						 	startActivity(switch1);
						 	finish();
					}
					
					// TODO Auto-generated method stub
					/*switch(loginlogout.getText().toString())
					{
					case "LogIn or SignUp":
						Intent i=new Intent(Swipe_tabs.this,MainActivity.class);
						startActivity(i);
						break;
					
					case "Logout":
						
						final Dialog dialog1 = new Dialog(Swipe_tabs.this);
						dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog1.setContentView(R.layout.logout);

						TextView logout= (TextView) dialog1.findViewById(R.id.textView2);
						TextView cancel = (TextView) dialog1.findViewById(R.id.textView3);
						
						logout.setOnClickListener(new View.OnClickListener() {
	
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								SharedPreferences sp = PreferenceManager
										.getDefaultSharedPreferences(Swipe_tabs.this);
								Editor edit = sp.edit();
								edit.clear();
								edit.commit();
								clearApplicationData();
								loadSavedPreferences();
								finish();
								System.exit(0);
								finish();  // Call finish here.
							}
							});
						cancel.setOnClickListener(new View.OnClickListener() {
	
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog1.cancel();
							}
						});
						dialog1.show();

						Window window = dialog1.getWindow();
						window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

					break;
					}
					*/
				}
			});

                // your code here
                new TestAsync1().execute();

    }
    

 // **************************  Sliding Menu Function Start **********************
	
 /*	private class SlideMenuClickListener implements
 	ListView.OnItemClickListener {
 @Override
 public void onItemClick(AdapterView<?> parent, View view, int position,
 		long id) {
 	System.out.println("on item click method");
 	// display view for selected nav drawer item
 	System.out.println("positon number "+position);
 	displayView(position);

 }


 }*/

 /*@Override
 public boolean onCreateOptionsMenu(Menu menu) {
 	// Inflate the menu; this adds items to the action bar if it is present.
 	getMenuInflater().inflate(R.menu.swipe_tabs, menu);
 	SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
 	 
 	
 	return true;
 }*/

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
 // toggle nav drawer on selecting action bar app icon/title
 	switch (item.getItemId()) {
     case R.id.action_search:
         
      	            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
      	                mDrawerLayout.closeDrawer(Gravity.RIGHT);
      	            } else {
      	                mDrawerLayout.openDrawer(Gravity.RIGHT);
      	            }
      	
      	        return false;
 	}
 
 	return super.onOptionsItemSelected(item);
 }


 
 @Override
 protected void onPostCreate(Bundle savedInstanceState) {
 super.onPostCreate(savedInstanceState);
 // Sync the toggle state after onRestoreInstanceState has occurred.
 //mDrawerToggle.syncState();
 }

 @Override
 public void onConfigurationChanged(Configuration newConfig) {
 super.onConfigurationChanged(newConfig);
 // Pass any configuration change to the drawer toggls
 //mDrawerToggle.onConfigurationChanged(newConfig);
 }
 		
 		// **************************  Sliding Menu Function End **********************

public void clearApplicationData() {
    File cache = getCacheDir();
    File appDir = new File(cache.getParent());
    if (appDir.exists()) {
        String[] children = appDir.list();
        for (String s : children) {
            if (!s.equals("lib")) {
                deleteDir(new File(appDir, s));
                Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
                Intent i=new Intent(Swipe_tabshost.this,Swipe_tabs.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // clearApplicationData();
                startActivity(i);
                finish();
            }
        }
    }
}

public static boolean deleteDir(File dir) {
    if (dir != null && dir.isDirectory()) {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir(new File(dir, children[i]));
            if (!success) {
                return false;
            }
        }
    }

    return dir.delete();
}
private void loadSavedPreferences() {    
    //User has successfully logged in, save this information
    // We need an Editor object to make preference changes.
   
	SharedPreferences sp = PreferenceManager
			.getDefaultSharedPreferences(this);
    SharedPreferences.Editor editor = sp.edit();
    editor.clear();
    editor.commit();

	}
@Override
public void onBackPressed()
{
	
}

public String getuserid(String userid){
	this.userid=userid;
	System.out.println("userid in get user id method in discover page "+userid1);
	return userid;
	
	
}

/************Styling Action Bar*****************/
public class TypefaceSpan extends MetricAffectingSpan {
    /** An <code>LruCache</code> for previously loaded typefaces. */
 

  private Typeface mTypeface;

  /**
   * Load the {@link Typeface} and apply to a {@link Spannable}.
   */
  public TypefaceSpan(Context context, String typefaceName) {
      mTypeface = sTypefaceCache.get(typefaceName);

      if (mTypeface == null) {
          mTypeface = Typeface.createFromAsset(context.getApplicationContext()
                  .getAssets(), String.format("fonts/%s", typefaceName));

          // Cache the loaded Typeface
          sTypefaceCache.put(typefaceName, mTypeface);
      }
  }

  public TypefaceSpan(ActionBarDrawerToggle actionBarDrawerToggle,
		String typefaceName) {
	// TODO Auto-generated constructor stub
}

@Override
public void updateMeasureState(TextPaint p) {
	// TODO Auto-generated method stub
	
}

@Override
public void updateDrawState(TextPaint tp) {
	// TODO Auto-generated method stub
	
}

}
public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
	   int targetWidth = 1250;
	   int targetHeight = 1250;
	   Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
	                       targetHeight,Bitmap.Config.ARGB_8888);

	   Canvas canvas = new Canvas(targetBitmap);
	   Paint p=new Paint();
	   p.setAntiAlias(true);
	   p.setFilterBitmap(true);
	   p.setDither(true);
	   Path path = new Path();
	   path.addCircle(((float) targetWidth - 1) / 2,
	       ((float) targetHeight - 1) / 2,
	       (Math.min(((float) targetWidth), 
	       ((float) targetHeight)) / 2),
	       Path.Direction.CCW);

	   canvas.clipPath(path);
	   Bitmap sourceBitmap = scaleBitmapImage;
	   canvas.drawBitmap(sourceBitmap, 
	       new Rect(0, 0, sourceBitmap.getWidth(),
	       sourceBitmap.getHeight()), 
	       new Rect(0, 0, targetWidth, targetHeight), null);
	   return targetBitmap;}
	/*public static Bitmap getBitmapFromURL(String src) {
    try {
        URL url = new URL(src);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap myBitmap = BitmapFactory.decodeStream(input);
        return myBitmap;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}*/
public static Bitmap loadBitmap(String URL, BitmapFactory.Options options) {
    Bitmap bitmap = null;
    InputStream in = null;
    try {
        in = OpenHttpConnection(URL);
        bitmap = BitmapFactory.decodeStream(in, null, options);
        in.close();
    } catch (IOException e1) {
    }
    return bitmap;
}

private static InputStream OpenHttpConnection(String strURL)
        throws IOException {
    InputStream inputStream = null;
    URL url = new URL(strURL);
    URLConnection conn = url.openConnection();

    try {
        HttpURLConnection httpConn = (HttpURLConnection) conn;
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            inputStream = httpConn.getInputStream();
        }
    } catch (Exception ex) {
    }
    return inputStream;
}

public void getFbKeyHash(String packageName) {

		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					packageName,
					PackageManager.GET_SIGNATURES);
			for (android.content.pm.Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("YourKeyHash :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
				System.out.println("YourKeyHash: "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (PackageManager.NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}

	}
private void hidePDialog() {
	if (pDialog != null) {
		pDialog.dismiss();
		pDialog = null;
	}
 			
}
class TestAsync1 extends AsyncTask<Void, Integer, String>
{
    protected void onPreExecute (){
        Log.d("PreExceute","On pre Exceute......");
    }

    protected String doInBackground(Void...arg0) {
        Log.d("DoINBackGround","On doInBackground...");
        if(userid!=null)
		{
			
		
        
        
		
		
		  //final String url = "Liveurl+"search/discover";
	       final String url=LiveUrl+"user/view_profile?user_id="+userid+"&email="+email_id;
	//*******************************************ListView code start*****************************************************
	       System.out.println("url in  swipe tabs== "+url);

			

			// Creating volley request obj
			JsonArrayRequest movieReq = new JsonArrayRequest(url,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {
							Log.d(TAG, response.toString());
						

							// Parsing json
							for (int i = 0; i < response.length(); i++) {
								try {

									JSONObject obj = response.getJSONObject(i);
									NavDrawerItem movie = new NavDrawerItem();
									
									profile_image=obj.getString("profile_pic"); 
									
								
									
									if(profile_image!=null)
									{
									try{	
										StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						                .permitAll().build();
						        StrictMode.setThreadPolicy(policy);
						        String url =profile_image;

						        imgIcon = (ImageView) findViewById(R.id.profile_image1);
						        
						        BitmapFactory.Options bmOptions;
						        bmOptions = new BitmapFactory.Options();	
						        bmOptions.inSampleSize = 1;
						        Bitmap bm = loadBitmap(url, bmOptions);
						        imgIcon.setImageBitmap(getRoundedShape(bm));
						        imgIcon.setScaleType(ScaleType.FIT_XY);

							 /*	Intent back=new Intent(Swipe_tabs.this,Discover.class);
								back.putExtra("profile", profile_image);
							*/
													
									    //System.out.println("Display inside image first "+getBitmapFromURL(profile_image));
									//URL pimage=new URL(profile_image);
									       
									    //profile_img=(ImageView)findViewById(R.id.imageView);
									 	//Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());
								 	    // Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());


									 /*	profile_img.setImageBitmap(getRoundedShape(myBitmap));
									 	profile_img.setScaleType(ScaleType.FIT_XY);*/
									//System.out.println("Bitmap Images *************"+edbitmap);
											
									}
								
									catch(Exception e){
									 	e.printStackTrace();
									 	}
									
									
									}
								
									// adding movie to movies array

									

								} catch (JSONException e) {
									e.printStackTrace();
								}

							}
							adapter2.notifyDataSetChanged();
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
	

   
        return "You are at PostExecute";
    }

    protected void onProgressUpdate(Integer...a){
     
    }

    protected void onPostExecute(String result) {
    
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




