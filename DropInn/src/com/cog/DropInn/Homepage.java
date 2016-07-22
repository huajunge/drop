package com.cog.DropInn;



import java.io.File;
import java.net.URL;




import com.cog.DropInn.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Homepage extends Activity {

	
	String emailid,profileimage,firstname,username,userid1,userid;
	URL pimage;
	Bitmap edbitmap;
	Button logout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		getActionBar().hide();
		TextView name=(TextView)findViewById(R.id.TextView1);
		TextView fname=(TextView)findViewById(R.id.TextView3);
		TextView email=(TextView)findViewById(R.id.TextView5);
		ImageView profileimg=(ImageView)findViewById(R.id.imageView1);
		//TextView search=(TextView)findViewById(R.id.TextView7);
		
		TextView listyourspace=(TextView)findViewById(R.id.TextView6);
		
		logout=(Button)findViewById(R.id.button1);
		
		 Intent i=getIntent();
		// userid1=i.getStringExtra("userid");
		 
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
		 
		 
		 System.out.println("userid in home page"+userid1);
		 
		 emailid= i.getStringExtra("email");
		firstname=i.getStringExtra("firstname");
		username=i.getStringExtra("username");
		profileimage=i.getStringExtra("profileimage");
		
		System.out.println("emailid"+emailid);
		System.out.println("firstname in home page"+firstname);
		System.out.println("profileimage"+profileimage);
		
		fname.setText(firstname);		
		email.setText(emailid);
		
		try{
		pimage=new URL(profileimage);
		 edbitmap = BitmapFactory.decodeStream(pimage.openStream());
         profileimg.setImageBitmap(edbitmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		listyourspace.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent map=new Intent(Homepage.this,Listspace.class);
				map.putExtra("userid",userid);
				startActivity(map);
				 finish();
				
			}
		});
		
		
	/*	search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent map=new Intent(Homepage.this,SearchActivity.class);
				
				startActivity(map);
				
			}
		});*/
		
		
		
		
		
		logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearApplicationData();
				loadSavedPreferences();
		    	finish();
		    	System.exit(0);	    	
		    	
		       Toast.makeText(Homepage.this, "Logout", Toast.LENGTH_SHORT).show();
				
				
			}
		});
		
	name.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(Homepage.this,Swipe_tabs.class);
			startActivity(i);
			 finish();
		}
	});	
	
		
		
	}

	
	public void clearApplicationData() {
	    File cache = getCacheDir();
	    File appDir = new File(cache.getParent());
	    if (appDir.exists()) {
	        String[] children = appDir.list();
	        for (String s : children) {
	            if (!s.equals("lib")) {
	                deleteDir(new File(appDir, s));
	                Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
	                Intent i=new Intent(Homepage.this,MainActivity.class);
	                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	               // clearApplicationData();
	                startActivity(i);
	                finish();
	            }
	        }
	    }
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
	private void loadSavedPreferences() {    
	    //User has successfully logged in, save this information
	    // We need an Editor object to make preference changes.
	   
	    SharedPreferences settings = getSharedPreferences(Signin.PREFS_NAME, 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.clear();
	    editor.commit();

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