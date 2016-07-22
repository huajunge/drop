package info.androidhive.customlistviewvolley.adater;
 

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.cog.DropInn.Inbox_detailshost;
import com.cog.DropInn.R;
 
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class CustomInboxHostAdapter extends BaseAdapter {
	protected static final String TAG = null;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    String userid="";
    String reservation_id,Liveurl,login_status1;
    String currency2;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 String currency1;
    public CustomInboxHostAdapter(Activity activity, List<Movie> movieItems) {
    	
    	SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    	Liveurl = sharedPreferences.getString("liveurl", null);
		   userid = sharedPreferences.getString("userid",null);
		   currency1 = sharedPreferences.getString("currenycode",null);
		System.out.println("userid in shared preferences in custom Inbox adapter page"+userid);
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
            convertView = inflater.inflate(R.layout.hostinbox, parent,false);
 
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.list_image);
        TextView name = (TextView) convertView.findViewById(R.id.title2);
        TextView time= (TextView) convertView.findViewById(R.id.TextView4);
        TextView date1 = (TextView) convertView.findViewById(R.id.TextView2);
        TextView date2 = (TextView) convertView.findViewById(R.id.TextView22);
        TextView currency=(TextView)convertView.findViewById(R.id.TextView23);
        TextView guest=(TextView)convertView.findViewById(R.id.TextView25);
        TextView status=(TextView)convertView.findViewById(R.id.TextView26);
        TextView title=(TextView)convertView.findViewById(R.id.TextView28);
        RelativeLayout inbox =(RelativeLayout)convertView.findViewById(R.id.inbox);
 
        // getting movie data for the row
     final   Movie m = movieItems.get(position); 
        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);         
        thumbNail.setScaleType(ScaleType.FIT_XY);
        
        reservation_id=m.getid();
        System.out.println("reservation id after getting in inbox adapter"+reservation_id);
        System.out.println("check out after getting"+m.getcheckout());
        System.out.println("message after getting in inbox adapter"+m.getTitle());
        System.out.println("symbol after getting"+m.getsymbol());
        System.out.println("username after getting"+m.getaddress());
        System.out.println("price after getting"+m.getprice());
        System.out.println("status after getting"+m.getstatus());
        System.out.println("check in after getting"+m.getcheckin());
        System.out.println("check out after getting"+m.getcheckout());
        System.out.println("userby  after getting===="+m.getuserby());
        
        // title
        
        name.setText(m.username());
        time.setText(m.getcreated());
        date1.setText(m.getcheckin());
        date2.setText(m.getcheckout());
       
        if (m.guest().equals("1"))
        {
        guest.setText(m.guest()+ " guest");
        }
        else
        {
        	guest.setText(m.guest()+ " guests");
        }
        
        status.setText(m.getstatus());
        title.setText(m.getTitle());
        
        if (m.isread().equals("1"))
        		{
        	inbox.setBackgroundColor(Color.parseColor("#ffffff"));
        		}
        
        else if (m.isread().equals("0"))
		{
        	inbox.setBackgroundColor(Color.parseColor("#d4d4d4"));
		}
          
        
        if (currency1==null)
		{
			currency1="$";
		}
    /*	if (m.getsymbol()!=null)
		{
		if  (!m.getsymbol().equals("[]"))
		{
			if (!m.getsymbol().equals("INR"))
					{
				Currency c  = Currency.getInstance(m.getsymbol());    
				  currency2=c.getSymbol();
	}
		}
		}*/
        DecimalFormat money = new DecimalFormat("00.00");
		money.setRoundingMode(RoundingMode.UP);
		final String india2 = money.format(new Double(m.getprice()));
    	 currency.setText(currency1+india2);
        convertView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
			@Override
            public void onClick(View view) {
            	
            	final String url=Liveurl+"payment/check_is_read?id="+m.getid1()+"&room_id="+m.getid()+"&user_by="+m.getuserby()+"&user_to="+m.userto()+"&is_read="+1;
 			   System.out.println("url" +url);
 			JsonArrayRequest movieReq = new JsonArrayRequest(url,
 					new Response.Listener<JSONArray>() {
 						@Override
 						public void onResponse(JSONArray response) {
 							 

 							// Parsing json
 							for (int i = 0; i < response.length(); i++) {
 								try {
                    
             	                                  
 									JSONObject obj = response.getJSONObject(i); 
 									login_status1 =  obj.getString("Status");
 									
 									  System.out.println("after json"+login_status1);
 							             
 							             if(login_status1.matches("Message read successfully"))
 							             {
 							            	

 							         		//final AlertDialog alertDialog = new AlertDialog.Builder(List_your_space.this).create();
 							                  
 							              
 							              /*   
 							                  total = 5 -(tit_step+sum_step+photo_step+add_step+pri_step);
 								                System.out.println("totally" +total);
 								                if (total==0)
 								                {
 								                Movie movie = new Movie();
 								               // movie.total(String.valueOf(total));
 								                System.out.println("india" +String.valueOf(total));
 								                movieList.add(movie);
 								                }*/
 							                   // Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
 							                   /* startActivity(i1);
 							                    finish();   */
 							             }
 							             }          
 							          
 							           
 															catch (JSONException e) {
 																e.printStackTrace();
 															}
 															
 														}
 														 //hidePDialog();
 														
 													}
 										}, new Response.ErrorListener() {
 											@Override
 											public void onErrorResponse(VolleyError error) {
 												VolleyLog.d(TAG, "Error: " + error.getMessage());
 												 

 											}
 										});

 									//Adding request to request queue
 									AppController.getInstance().addToRequestQueue(movieReq);
 								   	   	
 
            
               	Intent search=new Intent(activity,Inbox_detailshost.class);
               	search.putExtra("userid",userid);
            	search.putExtra("reservation_id",m.resid());
            	search.putExtra("userby",m.getuserby());
            	search.putExtra("price",india2);
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