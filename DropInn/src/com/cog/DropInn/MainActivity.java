package com.cog.DropInn;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cog.DropInn.R;
import com.parse.integratingfacebook.IntegratingFacebookApplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;



@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends Activity {
	
	String LiveUrl="http://demo.cogzidel.com/mdropinn_ios_php/mobile/";
	//String LiveUrl="http://10.0.2.2/sri/mdropinn_ios_php2/mobile/";
	public static final String PREFS_NAME = "MyPrefsFile";
	String Login,SignUp,room_id,userid;
	
	
	private ConnectionDetector cd;
    AlertDialogManager alert = new AlertDialogManager();
	
	Button facebook_login,signup,signin;
	ImageView imageView1,imageView2,profile_img;
	Button back;
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Login="Login";
		SignUp="SignUp";
		//getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();	
		Intent i=getIntent();
		room_id=i.getStringExtra("room_id");
         if( Build.VERSION.SDK_INT >= 9){
               StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
               StrictMode.setThreadPolicy(policy);
        }
         
         try {
 		    PackageInfo info = getPackageManager().getPackageInfo("com.cog.DropInn",PackageManager.GET_SIGNATURES);
 		          //"com.facebook.samples.loginhowto", PackageManager.GET_SIGNATURES);
 		    System.out.println("Facebook app info"+info);
 		    for (Signature signature : info.signatures){
 		           MessageDigest md = MessageDigest.getInstance("SHA");
 		           md.update(signature.toByteArray());
 		           Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
 		    }
 		} catch (NameNotFoundException e) {
 		    System.out.println("Name Not Found Exception");
 		} catch (NoSuchAlgorithmException e) {
 		    System.out.println("NoSuchAlgorithmException");

 		}

         
         
         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Editor editor = sharedPreferences.edit();
         editor.putString("liveurl", LiveUrl);                                     
         editor.commit();
         
         SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
         
         
        String restoredText = sharedPreferences.getString("liveurl", null);
         
         
         System.out.println("Liveurl value--->"+restoredText);        
            
      
         // facebook_login=(Button)findViewById(R.id.imageButton1);
           signin=(Button)findViewById(R.id.imageButton2);
           signup=(Button)findViewById(R.id.imageButton3);
           back=(Button)findViewById(R.id.imageBack);
          // profile_img=(ImageView)findViewById(R.id.imageView);
            
         
           back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(MainActivity.this,Swipe_tabs.class);
				 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
				startActivity(back,bndlanimation);
				finish();
			}
		});
        // Shared Preferences
       //    mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);

         
           cd = new ConnectionDetector(getApplicationContext());

           // Check if Internet present
           if (!cd.isConnectingToInternet()) {
               // Internet Connection is not present
               alert.showAlertDialog(MainActivity.this, "Internet Connection Error",
                       "Please connect to working Internet connection", false);
               // stop executing code by return
               return;
           }
        
                         
         
   
     
         
          /* facebook_login.setOnClickListener(new OnClickListener() {
             
               public void onClick(View v) {
                 
                   Intent i = new Intent(MainActivity.this,IntegratingFacebookApplication.class);
                   i.putExtra("room_id",room_id);
                 Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                   startActivity(i, bndlanimation);
                   
                   finish();
                   
                 
               }
           });*/

         
           signup.setOnClickListener(new OnClickListener() {
             
               public void onClick(View v) {
                   // TODO Auto-generated method stub
                 
                   Intent i = new Intent(MainActivity.this, Signin_signup.class);
                   i.putExtra("signup",SignUp);
                   i.putExtra("room_id",room_id);
                   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                   startActivity(i,bndlanimation);        
                   finish();
                 
               }

			
           });
         
       
           signin.setOnClickListener(new OnClickListener() {
             
               public void onClick(View v) {
                   // TODO Auto-generated method stub
                 
                   Intent i = new Intent(MainActivity.this, Signin_signup1.class);
                   i.putExtra("room_id",room_id);
                   i.putExtra("login",Login);
                   Bundle bndlanimation1 =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                   startActivity(i,bndlanimation1);                         
                   
                   finish();
               }
           });

		
		
		

	}

/*	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
    	{
		case R.id.imageButton1:
			
			Intent i=new Intent(MainActivity.this,IntegratingFacebookApplication.class);			
			startActivity(i);
			finish();
			
			break;
			
		case R.id.imageButton2:
			
			Intent signup=new Intent(MainActivity.this,Signup.class);			
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
            startActivity(signup,bndlanimation);
          	finish();
			 		
			break;
			
		case R.id.imageButton3:
			
			Intent signin=new Intent(MainActivity.this,Signin.class);
			Bundle bndlanimation1 =ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
            startActivity(signin,bndlanimation1);
            
			finish();
			
			
			break;
			
			
    	}
	}*/
	 
	    
	    
	/*@Override
	public void onBackPressed() {
	    new AlertDialog.Builder(MainActivity.this).setTitle("Close")
	    .setMessage("Would you like to Close?")
	    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	           // Intent intent = new Intent(AndroidTabLayoutActivity.this, Login.class);
	            SharedPreferences sp = PreferenceManager
	                    .getDefaultSharedPreferences(MainActivity.this);
	            Editor edit = sp.edit();
	            edit.clear();
	            edit.commit();
	             //startActivity(intent);
	            finish();
	            // Call finish here.
	            
	        }
	     })
	    .setNegativeButton("No", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // user doesn't want to logout
	        	dialog.dismiss();
	        }
	     })
	    .show();
		}		
*/
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