package com.cog.DropInn;



import info.androidhive.customlistviewvolley.adater.CustomYourlistAdapter;
import info.androidhive.customlistviewvolley.model.Movie;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;


public class Inbox1 extends FragmentActivity   
{
	private static final String TAG = Yourlisting.class.getSimpleName();
	Bitmap edbitmap;
	Bitmap edbitmap1;
	private CustomYourlistAdapter adapter;
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
		 TextView text1,text3,text4,tv,tv1,tv2;
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
	 	private FragmentTabHost mTabHost;
	 	
		 int fragVal;
		 private ViewPager viewPager;
		    private ActionBar actionBar;
		 Button back;
		 TextView title;
		  
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        ActionBar actionBar = getActionBar();
		        actionBar.hide();
		        setContentView(R.layout.activity_inbox1);
		     
		        back=(Button)findViewById(R.id.imageBack);
			       title=(TextView)findViewById(R.id.TextView14);
		        
		        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
				   userid = sharedPreferences1.getString("userid",null);
				   
				   mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
			        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

			        mTabHost.addTab(
			                mTabHost.newTabSpec("Travel").setIndicator("Travel", null),
			                TravelInboxFragment.class, null);
			        mTabHost.addTab(
			                mTabHost.newTabSpec("Host").setIndicator("Host", null),
			                HostInboxFragment.class, null);
			      
			   
			        mTabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.yourxmlfile);
			        mTabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.yourxmlfile1);
			        
			        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			            @Override
			            public void onTabChanged(String arg0) {

			                setTabColor(mTabHost);
			            }
			             });
			             setTabColor(mTabHost);
			             
			             
			             back.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Intent add1=new Intent(Inbox1.this,Swipe_tabshost.class);
									
									startActivity(add1);
									finish();
								
								}
							});
				   
				
	 	 }
		    @Override
		    public void onBackPressed()
		    {
		    	 Intent t6=new Intent(Inbox1.this,Swipe_tabshost.class);
		 	 	t6.putExtra("userid",userid);
		 	 	startActivity(t6);
		 	 	finish();
		    }
		    
			public void setTabColor(TabHost mtabhost) {

		 	    for(int i=0;i<mtabhost.getTabWidget().getChildCount();i++)
		 	    {
		 	    	  tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
	                 tv.setTextColor(Color.parseColor("#ff6666"));//unselected
		 	    }

		 	    if(mtabhost.getCurrentTab()==0)
		 	    {
		 	    	 tv1 = (TextView) mTabHost.getCurrentTabView().findViewById(android.R.id.title);
		 	         tv1.setTextColor(Color.parseColor("#ffffff"));//1st tab selected
		 	    }
		 	    else
		 	    {
		 	    	tv2 = (TextView) mTabHost.getCurrentTabView().findViewById(android.R.id.title);
		 	    	tv2.setTextColor(Color.parseColor("#ffffff"));//2nd tab selected
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

