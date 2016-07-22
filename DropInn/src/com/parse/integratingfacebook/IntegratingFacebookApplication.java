package com.parse.integratingfacebook;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.cog.DropInn.R;

public class IntegratingFacebookApplication extends Activity {
	static final String TAG = "MyApp";
	String roomid,symbol1,image;

	@Override
	public void onCreate(Bundle savedBundle) {
		super.onCreate(savedBundle);
		
		Intent i1=getIntent();
		roomid=i1.getStringExtra("room_id");
		symbol1=i1.getStringExtra("symbol");
		image=i1.getStringExtra("image");
		
		Parse.initialize(this, "ga1OW2egG0w0Cq1DsJkWpirY4ESBC9OohNDUgxCq","RqzFot5C5JFPzOW4dOx3K3JsIrTg5cxy8iIxhEpf");
		// Set your Facebook App Id in strings.xml
		ParseFacebookUtils.initialize(getString(R.string.app_id));	
		
		Intent i=new Intent(IntegratingFacebookApplication.this,LoginActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		 i.putExtra("room_id",roomid);
		 i.putExtra("symbol",symbol1);
		 i.putExtra("image",image);
		 
		startActivity(i);
						
		
	}

}
