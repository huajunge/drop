package info.androidhive.slidingmenu.adapter;


import info.androidhive.customlistviewvolley.model.Movie;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cog.DropInn.Inbox1;
import com.cog.DropInn.R;
import com.cog.DropInn.Setting_currency;
import com.cog.DropInn.View_profile;

public class NavDraweListAdapterhost extends BaseAdapter {
	private List<Movie> movieItems;
	private Context context;
	private List<Movie> navDrawerItems;
	private LayoutInflater inflater;
	private Activity activity;
	private int selectedIndex;
	String profile_image1;
	ImageView imgIcon;
	Bitmap bm;
	
	public NavDraweListAdapterhost(Activity activity, List<Movie> navDrawerItems){
		
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(activity);
	     profile_image1 = sharedPreferences2.getString("profpic", null);
	
		    // System.out.println("profile picture" +profile_image);
		this.activity = activity;
		this.navDrawerItems = navDrawerItems;
		 
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}
	public void setSelectedIndex(int position)
    {
        selectedIndex = position;
        notifyDataSetChanged();
    }
	@Override
	public Object getItem(int position) {		
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		
		
		 final Movie m = navDrawerItems.get(position); 
		 int type = getItemViewType(position);
		
		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		 switch (position) {
		 
		   case 0:
		    	
	         
	            
	             convertView = inflater.inflate(R.layout.drawer_list_item1,parent,false);
	         
	 		
	         convertView.setClickable(true);

	

       
       imgIcon = (ImageView) convertView.findViewById(R.id.profile_image1);
      TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
      TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
      RelativeLayout f=(RelativeLayout) convertView.findViewById(R.id.rlheader);
      
      RelativeLayout h=(RelativeLayout) convertView.findViewById(R.id.rlRow);
      RelativeLayout s=(RelativeLayout) convertView.findViewById(R.id.set);
      
      new java.util.Timer().schedule( 
    	        new java.util.TimerTask() {
    	            @Override
    	            public void run() {
    	                // your code here
    	                new TestAsync().execute();
    	            }
    	        }, 
    	        1000 
    	);
      
 
      
      
      imgIcon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          	
          	Intent search1=new Intent(activity,View_profile.class);
          
          	activity.startActivity(search1);
            activity.finish();
          	//view.getContext().startActivity(new Intent(activity,Bookit_page.class));
          	
          }
	});
      //imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
      //txtTitle.setText(navDrawerItems.get(position).getTitle());
      //System.out.println("prof" +m.profilepic());
   
    /*  try{	
			
     	
		      
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
       .permitAll().build();
StrictMode.setThreadPolicy(policy);
String url =profile_image1;


System.out.println("image" +profile_image1);
BitmapFactory.Options bmOptions;
bmOptions = new BitmapFactory.Options();	
bmOptions.inSampleSize = 1;
Bitmap bm = loadBitmap(url, bmOptions);
imgIcon.setImageBitmap(getRoundedShape(bm));
imgIcon.setScaleType(ScaleType.FIT_XY);


				
		}
		
		catch(Exception e){
		 	e.printStackTrace();
		 	}
    */
 
		
	

      break;
	     case 1:
	    	
	    	 convertView = inflater.inflate(R.layout.inboxnav,parent,false);
	    	 
	         convertView.setClickable(true);
	         ImageView setting=(ImageView) convertView.findViewById(R.id.settings2);
	         ImageView inbox=(ImageView) convertView.findViewById(R.id.settings13);
	         
	         
	         setting.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		            	
		            	Intent search=new Intent(activity,Setting_currency.class);
		            
		            	activity.startActivity(search);
		            	//view.getContext().startActivity(new Intent(activity,Bookit_page.class));
		            	  activity.finish();
		            }
			});
	         
	         inbox.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		            	
		            	Intent search=new Intent(activity,Inbox1.class);
		            
		            	activity.startActivity(search);
		            	//view.getContext().startActivity(new Intent(activity,Bookit_page.class));
		            	  activity.finish();
		            }
			});
	         break;
	         
	  
	     default:
	    	 break;
 	   	 }
        return convertView;
	}

	
		class TestAsync extends AsyncTask<Void, Integer, String>
		{
		    protected void onPreExecute (){
		        Log.d("PreExceute","On pre Exceute......");
		    }

		    protected String doInBackground(Void...arg0) {
		        Log.d("DoINBackGround","On doInBackground...");

		        try{	
					
		       
				      
				/*	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		          .permitAll().build();
		  StrictMode.setThreadPolicy(policy);*/
		        	clearApplicationData();
		  String url =profile_image1;


		  System.out.println("image" +profile_image1);
		  BitmapFactory.Options bmOptions;
		  bmOptions = new BitmapFactory.Options();	
		  bmOptions.inSampleSize = 1;
		   bm = loadBitmap(url, bmOptions);
		


						
				}
				
				catch(Exception e){
				 	e.printStackTrace();
				 	}
		        return "You are at PostExecute";
		    }

		    protected void onProgressUpdate(Integer...a){
		     
		    }

		    protected void onPostExecute(String result) {
		    	if (isOnline(activity)) {
		    		if (bm!=null)
		    		{
		    	  imgIcon.setImageBitmap(getRoundedShape(bm));
		    		//imgIcon.setImageBitmap(bm);
				  imgIcon.setScaleType(ScaleType.FIT_XY);
				  getRoundedShape(bm).recycle();
		    		}
		    	}
			     else
			     {
			    		Toast.makeText(activity, "Check your internet connection",Toast.LENGTH_SHORT).show();
			    		
			     }
			
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
			public static Bitmap getBitmapFromURL(String src) {
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
		}
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
			public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
			        int reqWidth, int reqHeight) {

			    // First decode with inJustDecodeBounds=true to check dimensions
			    final BitmapFactory.Options options = new BitmapFactory.Options();
			    options.inJustDecodeBounds = true;
			    BitmapFactory.decodeResource(res, resId, options);

			    // Calculate inSampleSize
			    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

			    // Decode bitmap with inSampleSize set
			    options.inJustDecodeBounds = false;
			    return BitmapFactory.decodeResource(res, resId, options);
			}
			public static int calculateInSampleSize(
		            BitmapFactory.Options options, int reqWidth, int reqHeight) {
		    // Raw height and width of image
		    final int height = options.outHeight;
		    final int width = options.outWidth;
		    int inSampleSize = 1;

		    if (height > reqHeight || width > reqWidth) {

		        final int halfHeight = height / 2;
		        final int halfWidth = width / 2;

		        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
		        // height and width larger than the requested height and width.
		        while ((halfHeight / inSampleSize) > reqHeight
		                && (halfWidth / inSampleSize) > reqWidth) {
		            inSampleSize *= 2;
		        }
		    }

		    return inSampleSize;
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
			public void clearApplicationData() {
			    File cache = activity.getCacheDir();
			    if (cache!=null && cache.isDirectory())
			    {
			    	deleteDir(cache);
			    }
			   /* File appDir = new File(cache.getParent());
			    if (appDir.exists()) {
			        String[] children = appDir.list();
			        for (String s : children) {
			            if (!s.equals("lib")) {
			                deleteDir(new File(appDir, s));*/
			               /* Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");*/
			              /* Intent i=new Intent(Setting_currency.this,Swipe_tabs.class);
			                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			               // clearApplicationData();
			                startActivity(i);
			                finish();*/
			           /* }
			        }
			    }*/
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
}
