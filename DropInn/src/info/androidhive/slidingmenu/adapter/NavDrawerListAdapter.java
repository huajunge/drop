package info.androidhive.slidingmenu.adapter;


import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.util.ArrayList;

import com.cog.DropInn.R;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private LayoutInflater inflater;
	private Activity activity;
	
	public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
           
            convertView = mInflater.inflate(R.layout.drawer_list_item,null);
        }
         
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.profile_image);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
        RelativeLayout f=(RelativeLayout) convertView.findViewById(R.id.rlheader);
        
        RelativeLayout h=(RelativeLayout) convertView.findViewById(R.id.rlRow);
        RelativeLayout s=(RelativeLayout) convertView.findViewById(R.id.set);
        //imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
        txtTitle.setText(navDrawerItems.get(position).getTitle());
      /*  if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
      
        	if(navDrawerItems.get(position).getfooter())
        	{
        	f.setVisibility(View.VISIBLE);
        		h.setVisibility(View.VISIBLE);
        		s.setVisibility(View.VISIBLE);
        	}
        	else
        	{
        		f.setVisibility(View.GONE);
        		h.setVisibility(View.VISIBLE);
        		s.setVisibility(View.GONE);
        		
        	}
        	
        	if(navDrawerItems.get(position).getheader())
        	{
        	s.setVisibility(View.VISIBLE);
        		//h.setVisibility(View.VISIBLE);
        		
        	}
        	else
        	{
        		s.setVisibility(View.GONE);
        		//h.setVisibility(View.VISIBLE);
        	
        	}
        
        	
        	/*if (position==1)
        	{
        		 convertView = inflater.inflate(R.layout.searchimage1,parent,false);
                 convertView.setClickable(true);
        	}*/
        		
        // displaying count
        // check whether it set visible or not
        if(navDrawerItems.get(position).getCounterVisibility()){
        	txtCount.setText(navDrawerItems.get(position).getCount());
        }else{
        	// hide the counter view
        	txtCount.setVisibility(View.GONE);
        }
        
        return convertView;
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
