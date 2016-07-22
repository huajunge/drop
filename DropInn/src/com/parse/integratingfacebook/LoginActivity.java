package com.parse.integratingfacebook;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.cog.DropInn.MainActivity;
import com.cog.DropInn.R;
import com.parse.integratingfacebook.IntegratingFacebookApplication;

public class LoginActivity extends Activity {
	
	private Dialog progressDialog;
	SharedPreferences preferences;
	ProgressBar brp1;
	ParseUser currentUser = ParseUser.getCurrentUser();
	String roomid,symbol1,image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i1=getIntent();
		roomid=i1.getStringExtra("room_id");
		symbol1=i1.getStringExtra("symbol");
		image=i1.getStringExtra("image");
		// setContentView(R.layout.main12);
		 
		/* try {
	  		    PackageInfo info = getPackageManager().getPackageInfo("com.facebook.LoginActivity", PackageManager.GET_SIGNATURES);
	  		          //"com.facebook.samples.loginhowto", PackageManager.GET_SIGNATURES);
	  		    System.out.println("Facebook app info"+info);
	  		    for (Signature signature : info.signatures){
	  		           MessageDigest md = MessageDigest.getInstance("SHA");
	  		           md.update(signature.toByteArray());
	  		           Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	  		    }
	  		} catch (NameNotFoundException e) {
	  		    System.out.println("Name Not Found Exception");
	  		    e.printStackTrace();
	  		} catch (NoSuchAlgorithmException e) {
	  		    System.out.println("NoSuchAlgorithmException");

	  		}*/

				
		if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
			// Go to the user info activity
			showUserDetailsActivity();
			
		  }
		else
			onLoginButtonClicked();
		System.out.println("after login button clicked ");
		
		
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}

	private void onLoginButtonClicked() {
		
		LoginActivity.this.progressDialog = ProgressDialog.show(LoginActivity.this, "", "Logging in...",true);

		//Toast.makeText(getApplicationContext(), "Login Button Clicked", Toast.LENGTH_LONG).show();	
		
		/*		List<String> permissions1 = Arrays.asList("email","basic_info", "user_about_me",
				"user_relationships", "user_birthday", "user_location");
		 List<String> permissions = Arrays.asList("publish_stream, publish_actions"); */
		
		List<String> permissions = new ArrayList <String>();
		permissions.add("email");
		permissions.add("public_profile");
		//permissions.add("basic_info");
		permissions.add("user_about_me");
		permissions.add("user_relationships");
		permissions.add("user_birthday");
		permissions.add("user_location");
	 // permissions.add("publish_stream");
		final String PREF_FILE_NAME = "PrefFile";
		preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
		   
		   
		ParseFacebookUtils.logIn(permissions,this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException err) {
				LoginActivity.this.progressDialog.dismiss();		
				
				System.out.println("userrrrrrrrrrrrrrrrrr"+user);				
				if (user == null) {
					Log.d(IntegratingFacebookApplication.TAG,"Uh oh. The user cancelled the Facebook login.");
					Intent i=new Intent(LoginActivity.this,MainActivity.class);
					startActivity(i);
					finish();
					
				}else if (user.isNew()) {
					Log.d(IntegratingFacebookApplication.TAG,
							"User signed up and logged in through Facebook!");
					
					System.out.println("after elseif part");
					showUserDetailsActivity();
				} 
				else {
					
					//Toast.makeText(getApplicationContext(), "Logedin using facebook", Toast.LENGTH_LONG).show();	
					Log.d(IntegratingFacebookApplication.TAG,"User logged in through Facebook!");
					//Toast.makeText(getApplicationContext(), "Show user details Activity Start", Toast.LENGTH_LONG).show();	
					showUserDetailsActivity();
					//Toast.makeText(getApplicationContext(), "Show user details Activity end", Toast.LENGTH_LONG).show();
				}
				
		//		showUserDetailsActivity();
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				SharedPreferences.Editor editor = preferences.edit();
				int storedPreference = 0;
				editor.putInt("storedInt", storedPreference); // value to store
				editor.commit();
			}
		});
		
		System.out.println("after onLoginButtonClicked ");
	}

	
	private void showUserDetailsActivity() {
		
		//Toast.makeText(getApplicationContext(), "Show UserDetails Activity", Toast.LENGTH_LONG).show();	
		System.out.println("Entry to  USerDetail Activtiy data'");
		Intent intent = new Intent(this, UserDetailsActivity.class);
		 intent.putExtra("room_id",roomid);
		 intent.putExtra("symbol",symbol1);
		 intent.putExtra("image",image);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);
		finish();
	}
		
		
	
}













