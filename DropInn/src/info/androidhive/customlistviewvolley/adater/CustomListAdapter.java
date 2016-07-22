package info.androidhive.customlistviewvolley.adater;


import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.WordUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView; 
import com.cog.DropInn.Bookit_page;
import com.cog.DropInn.CircularImageView;
import com.cog.DropInn.Detail_page;
import com.cog.DropInn.Discover;
import com.cog.DropInn.Italy;
import com.cog.DropInn.R;
import com.cog.DropInn.SearchActivity;
import com.cog.DropInn.Search_result;
import com.cog.DropInn.Unitedkingdom;
import com.cog.DropInn.Yourlisting;


@SuppressLint("ViewHolder")
public class CustomListAdapter extends BaseAdapter {
	protected static final String TAG = null;
	private Activity activity;
	private LayoutInflater inflater;
	private List<Movie> movieItems;
	private int selectedIndex;	
	String userid="";
	
	ProgressDialog pDialog;
	Discover d=new Discover();
	
	 	
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Movie> movieItems) {
		this.activity = activity;
		this.movieItems = movieItems;
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
		   userid = sharedPreferences.getString("userid",null);
		System.out.println("userid in shared preferences in custom list adapter"+userid);
	}

	@Override
	public int getCount() {
		return movieItems.size();
	}

	public void setSelectedIndex(int position)
    {
        selectedIndex = position;
        notifyDataSetChanged();
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
	public View getView( int position, View convertView, ViewGroup parent) {
	
		
	
		 final Movie m = movieItems.get(position); 
		 int type = getItemViewType(position);
         System.out.println("getView " + position + " " + convertView + " type = " + type);
		
		
		
		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
		 switch (position) {
         case 0:
        	 convertView = inflater.inflate(R.layout.searchimage,parent,false);
             convertView.setClickable(true);
             break;
         case 1:
        	 convertView = inflater.inflate(R.layout.india,parent,false);
             convertView.setClickable(true);
             break;
             
         case 2:
        	 convertView = inflater.inflate(R.layout.italy,parent,false);
             convertView.setClickable(true);
             break;
             
         case 3:
        	 convertView = inflater.inflate(R.layout.america,parent,false);
             convertView.setClickable(true);
             break;
             
         case 4:
        	 convertView = inflater.inflate(R.layout.russia,parent,false);
             convertView.setClickable(true);
             break;
             
         default:
        	 convertView = inflater.inflate(R.layout.discover_row_view_list,parent,false);
             convertView.setClickable(true);

             break;
     }
	
		
		switch (position)	
		{
		case 0:
			imageLoader = AppController.getInstance().getImageLoader();
			NetworkImageView search = (NetworkImageView) convertView.findViewById(R.id.imageView5);
			/*Button search1 = (Button) convertView.findViewById(R.id.editText1);
			search1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent search=new Intent(activity,SearchActivity.class);
	            	search.putExtra("room_id", m.getid());	            	    	
	            		search.putExtra("userid",userid);
	            	v.getContext().startActivity(search);
				}
			});*/
			search.setImageUrl(m.getThumbnailUrl(), imageLoader);
			search.setScaleType(ScaleType.CENTER_CROP);
			search.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	
	            	Intent search=new Intent(activity,Search_result.class);
	            	search.putExtra("room_id", m.getid());	            	    	
	            		search.putExtra("userid",userid);
	            		search.putExtra("location","New%20York");
	            		search.putExtra("position","0");
	            		activity.startActivity(search);
	            	activity.finish();
	            	//view.getContext().startActivity(new Intent(activity,Bookit_page.class));
	            	
	            }
		});
			break;
		case 1:
			imageLoader = AppController.getInstance().getImageLoader();
			NetworkImageView india = (NetworkImageView) convertView.findViewById(R.id.imageView4);
			india.setImageUrl(m.getThumbnailUrl(), imageLoader);
			india.setScaleType(ScaleType.CENTER_CROP);
			india.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	Intent search=new Intent(activity,Search_result.class);
	            	search.putExtra("room_id", m.getid());	            	
	            	System.out.println("userid in customlist adapter object variable d.userid===="+d.userid1);
	            	search.putExtra("userid",userid);
	            	search.putExtra("location","Paris");
	            	search.putExtra("position","1");
	            	activity.startActivity(search);
	            	activity.finish();
	            	
	            }
		});
			break;
		case 2:
			imageLoader = AppController.getInstance().getImageLoader();
			NetworkImageView italy = (NetworkImageView) convertView.findViewById(R.id.imageView8);
			italy.setImageUrl(m.getThumbnailUrl(), imageLoader);
			italy.setScaleType(ScaleType.CENTER_CROP);
			italy.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	Intent search=new Intent(activity,Search_result.class);
	            	search.putExtra("room_id", m.getid());
	            	search.putExtra("userid",userid);
	            	search.putExtra("location","Berlin");
	            	search.putExtra("position","2");
	            	activity.startActivity(search);
	            	activity.finish();
	            	
	            }
		});
			break;
			
		case 3:
			imageLoader = AppController.getInstance().getImageLoader();
			NetworkImageView america = (NetworkImageView) convertView.findViewById(R.id.imageView4);
			america.setImageUrl(m.getThumbnailUrl(), imageLoader);
			america.setScaleType(ScaleType.CENTER_CROP);
			america.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	Intent search=new Intent(activity,Search_result.class);
	            	search.putExtra("room_id", m.getid());
	            	search.putExtra("userid",userid);
	            	search.putExtra("location","London");
	            	search.putExtra("position","3");
	            	activity.startActivity(search);
	            	activity.finish();
	            	
	            }
		});
			break;
			
		case 4:
			imageLoader = AppController.getInstance().getImageLoader();
			NetworkImageView russia = (NetworkImageView) convertView.findViewById(R.id.imageView4);
			russia.setImageUrl(m.getThumbnailUrl(), imageLoader);
			russia.setScaleType(ScaleType.CENTER_CROP);
			russia.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	Intent search=new Intent(activity,Search_result.class);
	            	search.putExtra("room_id", m.getid());
	            	search.putExtra("userid",userid);
	            	search.putExtra("location","San%20Fransisco");
	            	search.putExtra("position","4");
	            	activity.startActivity(search);
	            	activity.finish();
	            	
	            }
		});
			break;
			
		  default:
				imageLoader = AppController.getInstance().getImageLoader();
				NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.imageView1);
				NetworkImageView thumbNail1 = (NetworkImageView) convertView.findViewById(R.id.imageView2);
				ImageView trans_bg = (ImageView)convertView.findViewById(R.id.imageView3);
				ImageButton heart = (ImageButton)convertView.findViewById(R.id.imageButton1);
				TextView title = (TextView) convertView.findViewById(R.id.TextView4); 
				TextView price = (TextView) convertView.findViewById(R.id.TextView2); 
				TextView address = (TextView) convertView.findViewById(R.id.TextView3); 
				TextView symbol = (TextView) convertView.findViewById(R.id.TextView1); 
				TextView city = (TextView) convertView.findViewById(R.id.TextView10); 
				// thumbnail image
				thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
				thumbNail.setScaleType(ScaleType.CENTER_CROP);
				
				thumbNail1.setImageUrl(m.getThumbnailUrl1(), imageLoader);
				
				DecimalFormat money = new DecimalFormat("00.00");
				money.setRoundingMode(RoundingMode.UP);
				final String india2 = money.format(new Double(m.getprice()));
				 price.setText(india2);				
			
				
				thumbNail.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		            	  
		            	pDialog = new ProgressDialog(activity);
		    			pDialog.setCancelable(false);
		    			pDialog.show();
		    			pDialog.setContentView(R.layout.progress_dialog);
		    			
		            	System.out.println("detail page");
		            	Intent search=new Intent(activity,Detail_page.class);
		            	search.putExtra("room_id", m.getid());
		            	search.putExtra("userid",userid);
		            	search.putExtra("title", m.getTitle());
		            	search.putExtra("address", m.getaddress());
		            	search.putExtra("resize1", m.getThumbnailUrl());
		            	search.putExtra("price", india2);
		            	search.putExtra("room_type", m.roomtype());
		            	search.putExtra("profimage", m.getThumbnailUrl1());
		            	search.putExtra("city", m.getcity());
		            	search.putExtra("symbol", m.getsymbol());
		            	
		            	activity.startActivity(search);
		            	activity.finish();
		            }
				});
				// title
				String titl=m.getTitle();
				String addr=m.roomtype();
				titl=WordUtils.capitalizeFully(titl);
				addr=WordUtils.capitalizeFully(addr);
				title.setText(titl);
				
				 
				 symbol.setText(m.getsymbol());
				 address.setText(addr);
				 city.setText(m.getcity());
				//String d_userid=d.getuserid(userid);
				 
				// System.out.println("userid in customlist adapter object variable d.getuserid() method===="+d_userid);
		       
	             break;
			
			
		}
		
		System.out.println("get view position "+position);	
		System.out.println("GET USER ID "+userid);		
		System.out.println("title in adapter page111 "+m.getTitle());
    
		return convertView;
		
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
		   return targetBitmap;
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