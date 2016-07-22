package info.androidhive.customlistviewvolley.adater;
 

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cog.DropInn.Detail_page;
import com.cog.DropInn.Edit_list;
import com.cog.DropInn.List_your_space;
import com.cog.DropInn.R;
 
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class CustomYourlistAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    String userid="";
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
    public CustomYourlistAdapter(Activity activity, List<Movie> movieItems) {
    	
    	SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
		userid = sharedPreferences.getString("userid",null);
		System.out.println("userid in shared preferences in custom yourlist  adapter"+userid);
        this.activity = activity;
        this.movieItems = movieItems;
    }
 
    @Override
    public int getCount() {
        return movieItems.size();
    }   
 
    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.yourlisting, parent,false);
 
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.list_image);
        TextView title1 = (TextView) convertView.findViewById(R.id.title1);
       //TextView country1= (TextView) convertView.findViewById(R.id.country1);
        TextView roomtype1 = (TextView) convertView.findViewById(R.id.roomtype1);
        TextView complete = (TextView) convertView.findViewById(R.id.TextView1);
 
        // getting movie data for the row
     final   Movie m = movieItems.get(position);
 
        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);         
        thumbNail.setScaleType(ScaleType.FIT_XY);
         
        // title
        System.out.println("adapter" +m.total());
       if(m.check()!=null)
       {
       if(m.check().equals("1") && m.step().equals("0"))
        {
        	complete.setText("Listed");
        	complete.setTextColor(Color.parseColor("#408800"));
        	
        }
       else if (m.check().equals("0") && m.step().equals("0"))
       {
    	   complete.setText("Unlisted");
       	complete.setTextColor(Color.parseColor("#408800"));
       }
       
    }
       /* else
        {
        	complete.setText("Unlisted");
        	complete.setTextColor(Color.parseColor("#408800"));
        	
        }*/
        
        
        if(m.getTitle().equals("null")){
        
        	//set country to title        	
        	//title1.setText(m.getaddress());
            roomtype1.setText(m.getaddress());
        }
        else
        {
     
        
        title1.setText(m.getTitle());
        // country1.setText(m.getsymbol());
         roomtype1.setText(m.getaddress());
         //complete.setText(m.getsymbol());
        }
        
        if(m.step().equals("1"))
        {
        	complete.setText("1 step");
        	complete.setTextColor(Color.parseColor("#5B4532"));
        }
        
        if(m.step().equals("2"))
        {
        	complete.setText("2 steps");
        	complete.setTextColor(Color.parseColor("#5B4532"));
        }
        
        if(m.step().equals("3"))
        {
        	complete.setText("3 steps");
        	complete.setTextColor(Color.parseColor("#5B4532"));
        }
        
        if(m.step().equals("4"))
        {
        	complete.setText("4 steps");
        	complete.setTextColor(Color.parseColor("#5B4532"));
        }
        
        if(m.step().equals("5"))
        {
        	complete.setText("5 steps");
        	complete.setTextColor(Color.parseColor("#5B4532"));
        }
        
        
        
        convertView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
			@Override
            public void onClick(View view) {
            
               	Intent search=new Intent(activity,Edit_list.class);
            	search.putExtra("room_id",m.getid());
            	search.putExtra("userid", userid);
            	search.putExtra("step", m.step());
            	search.putExtra("check", m.check());
            	Bundle bndlanimation =ActivityOptions.makeCustomAnimation(activity, R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
            	activity.startActivity(search,bndlanimation);
                activity.finish();
            

            }
        });
        
 
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