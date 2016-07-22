package com.cog.DropInn;



import info.androidhive.customlistviewvolley.adater.CustomYourlistAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.cog.DropInn.XListView.IXListViewListener;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Myhost2 extends Fragment {
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
		 
		        View myhost2 = inflater.inflate(R.layout.yourlist_listview, container, false);
		        
		        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
				   userid = sharedPreferences1.getString("userid",null);
				   
					return myhost2;
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