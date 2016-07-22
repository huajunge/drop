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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;


public class MyTrips extends Fragment  
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
		    RelativeLayout beforelogin;
		   LinearLayout afterlogin;
		 Button button1;
		  
	 	 @Override
		    public View onCreateView(LayoutInflater inflater, ViewGroup container,
		            Bundle savedInstanceState) {
		 
		        View mytrips = inflater.inflate(R.layout.activity_mytrips, container, false);
		        
		        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
				   userid = sharedPreferences1.getString("userid",null);
				   beforelogin = (RelativeLayout) mytrips.findViewById(R.id.beforelogin);
				   afterlogin = (LinearLayout) mytrips.findViewById(R.id.afterlogin);
				   button1 = (Button) mytrips.findViewById(R.id.button1);
				   
				   if (userid==null)
				   {
					   beforelogin.setVisibility(View.VISIBLE);
					   afterlogin.setVisibility(View.INVISIBLE);
				   }
				   else if (userid!=null)
				   {
					   afterlogin.setVisibility(View.VISIBLE);
					   beforelogin.setVisibility(View.INVISIBLE);
				   }
				   
				   button1.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent add1=new Intent(getActivity(),MainActivity.class);
							
							startActivity(add1);
							getActivity().finish();
						}
					});
				   
				   mTabHost = (FragmentTabHost) mytrips.findViewById(android.R.id.tabhost);
			        mTabHost.setup(this.getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

			        mTabHost.addTab(
			                mTabHost.newTabSpec("Previous").setIndicator("Previous",  null),
			                PreviousFragment.class, null);
			        mTabHost.addTab(
			                mTabHost.newTabSpec("Upcoming").setIndicator("Upcoming", null),
			                UpcomingFragment.class, null);
			        
			       
			        
			       mTabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.yourxmlfile);
			        mTabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.yourxmlfile1);
			        
			        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			            @Override
			            public void onTabChanged(String arg0) {

			                setTabColor(mTabHost);
			            }
			             });
			             setTabColor(mTabHost);
			       
			        
			  
			        
			        
					return mytrips;
					
					
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


