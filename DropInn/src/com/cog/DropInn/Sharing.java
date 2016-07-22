package com.cog.DropInn;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.cog.DropInn.R;


public class Sharing extends Activity{

	ImageButton facebook, twitter;
	String userid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharing);
		
		facebook=(ImageButton)findViewById(R.id.imageButton1);		 
		twitter=(ImageButton)findViewById(R.id.imageButton2);		 
		
		 
		 SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		   userid = sharedPreferences1.getString("userid",null);
		   
		   facebook.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Text...");
            PackageManager packManager = getPackageManager();
            List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(sharingIntent,  PackageManager.MATCH_DEFAULT_ONLY);

            boolean resolved = false;
            for(ResolveInfo resolveInfo: resolvedInfoList){
                if(resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")){
                    sharingIntent.setClassName(
                        resolveInfo.activityInfo.packageName, 
                        resolveInfo.activityInfo.name );
                    resolved = true;
                    break;
                }
            }
            if(resolved){
                startActivity(sharingIntent);
            }else{

                 Builder alert  = new AlertDialog.Builder(Sharing.this);
                    alert.setTitle("Warning");
                    alert.setMessage("Facebook App not found");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) 
                        {
                            dialog.dismiss();

                        }
                    });
                    alert.show();
            } 
				}
});
		   
		   twitter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
					   shareIntent.setType("text/plain");
					   shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
					   shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) v.getTag(R.drawable.ic_launcher));
					    //shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

					   PackageManager pm = v.getContext().getPackageManager();
					   List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
					     for (final ResolveInfo app : activityList) 
					      {
					        if ("com.twitter.android.PostActivity".equals(app.activityInfo.name))
					          {
					             final ActivityInfo activity = app.activityInfo;
					             final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
					             shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
					             shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
					             shareIntent.setComponent(name);
					             v.getContext().startActivity(shareIntent);
					            break;
					          }
					      }
				}
});
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
	
