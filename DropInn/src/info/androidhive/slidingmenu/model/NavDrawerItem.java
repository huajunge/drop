package info.androidhive.slidingmenu.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NavDrawerItem {
	
	private String title;
	private int icon;
	private boolean footer,header;
	private String count = "0";
	// boolean to set visiblity of the counter
	private boolean isCounterVisible = false;
	
	public NavDrawerItem(){}

	public NavDrawerItem(String title, boolean footer, boolean header){
		this.title = title;
		this.icon = icon;
		this.footer=footer;
		this.header=header;
	}
	
	public NavDrawerItem(String title, boolean isCounterVisible, String count){
		this.title = title;
		this.icon = icon;
		this.isCounterVisible = isCounterVisible;
		this.count = count;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	
	public boolean getfooter(){
		return this.footer;
	}
	
	public boolean getheader(){
		return this.header;
	}
	
	public String getCount(){
		return this.count;
	}
	
	public boolean getCounterVisibility(){
		return this.isCounterVisible;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	
	
	public void setCount(String count){
		this.count = count;
	}
	
	public void setCounterVisibility(boolean isCounterVisible){
		this.isCounterVisible = isCounterVisible;
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
