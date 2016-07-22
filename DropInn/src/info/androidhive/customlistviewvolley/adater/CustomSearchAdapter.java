package info.androidhive.customlistviewvolley.adater;


import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.WordUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView; 
import com.cog.DropInn.Detail_page;
import com.cog.DropInn.R;
import com.cog.DropInn.SearchActivity;

public class CustomSearchAdapter extends BaseAdapter {
	protected static final String TAG = null;
	private Activity activity;
	private LayoutInflater inflater;
	private List<Movie> movieItems;
	ProgressDialog pDialog;
	VideoView videoview;
	 ProgressBar mSpinner;
	 public static String s1="10";

	 String userid="";
	String currency1;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomSearchAdapter(Activity activity, List<Movie> movieItems) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
		   userid = sharedPreferences.getString("userid",null);
		   currency1 = sharedPreferences.getString("currenycode",null);
		System.out.println("userid in shared preferences in custom search adapter"+userid);
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
			convertView = inflater.inflate(R.layout.search_result_view,parent,false);
		 
        convertView.setClickable(true);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView	.findViewById(R.id.imageView1);
		NetworkImageView thumbNail1 = (NetworkImageView) convertView	.findViewById(R.id.imageView2);
		
		TextView title = (TextView) convertView.findViewById(R.id.TextView4); 
		TextView country = (TextView) convertView.findViewById(R.id.TextView3);
		TextView price = (TextView) convertView.findViewById(R.id.TextView2);
		TextView city = (TextView) convertView.findViewById(R.id.TextView10); 
		
		final Movie m = movieItems.get(position); 
	       
	       
    System.out.println("roomid in adapter search activity"+m.getid());

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		thumbNail.setScaleType(ScaleType.FIT_XY);
		
		thumbNail1.setImageUrl(m.getThumbnailUrl1(), imageLoader);
		
		// title
		String titl=m.getTitle();
		titl=WordUtils.capitalizeFully(titl);
		title.setText(titl);
		city.setText(m.getcity());
		if (currency1==null)
		{
			currency1="$";
		}
		// price
		TextView symbol = (TextView) convertView.findViewById(R.id.TextView1); 
		symbol.setText(currency1);
		DecimalFormat money = new DecimalFormat("00.00");
		money.setRoundingMode(RoundingMode.UP);
		final String india2 = money.format(new Double(m.getprice()));
		 price.setText(india2);		
			//country	
				String addr=m.roomtype();
				addr=WordUtils.capitalizeFully(addr);
				country.setText(addr);
				
				  thumbNail.setOnClickListener(new View.OnClickListener() {
			            @Override
			            public void onClick(View view) {
			            	pDialog = new ProgressDialog(activity);
			    			pDialog.setCancelable(false);
			    			pDialog.show();
			    			pDialog.setContentView(R.layout.progress_dialog);
			            	Intent search=new Intent(activity,Detail_page.class);
			            	search.putExtra("room_id", m.getid());
			            	search.putExtra("userid",userid);
			            	search.putExtra("address", m.getaddress());
			            	search.putExtra("image", m.getThumbnailUrl());
			            	search.putExtra("title", m.getTitle());
			            	search.putExtra("price", india2);
			            	search.putExtra("room_type", m.roomtype());
			            	search.putExtra("profimage", m.getThumbnailUrl1());
			            	search.putExtra("city", m.getcity());
			            	search.putExtra("symbol", m.getsymbol());
			            	activity.startActivity(search);
			            	  activity.finish();
			            	
			            }
					});
		 
		/*convertView.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                Log.d(TAG, "string: " + m.getTitle()); 
	  
	               Intent ii23=new Intent(activity,Detail_page.class);
	               startActivity(ii23);
	                //Toast.makeText(activity, "view clicked: " + m.getTitle(), Toast.LENGTH_SHORT).show();
	            }

				private void startActivity(Intent ii23) {
					// TODO Auto-generated method stub
					
				}
	        });*/
	 

    
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