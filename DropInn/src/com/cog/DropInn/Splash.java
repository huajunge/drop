package com.cog.DropInn;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;







import com.cog.DropInn.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Window;


public class Splash extends Activity {
		
	private long ms=0;
	private long splashTime=2000;
	private boolean splashActive = true;
	private boolean paused=false;
	private MyThread mythread;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			//pi.setText((CharSequence) msg.obj);
			System.out.println(msg.obj);
			//pi.setText(msg.obj.toString());
			System.out.println("start switch");
			Intent intent = new Intent();
			System.out.println("switch ing------------");
			for(int i=0;i<10000000;){
				i++;
			}
			System.out.println("enter swap");
			//Console("enter swap");
			intent.setClass(Splash.this,Swipe_tabs.class);
			System.out.println("switch finish");
			startActivity(intent);
			finish();
	
		};
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        
        try {
 		    PackageInfo info = getPackageManager().getPackageInfo("com.cog.DropInn",PackageManager.GET_SIGNATURES);
 		          //"com.facebook.samples.loginhowto", PackageManager.GET_SIGNATURES);
 		    System.out.println("Facebook app info"+info);
 		    for (Signature signature : info.signatures){
 		           MessageDigest md = MessageDigest.getInstance("SHA");
 		           md.update(signature.toByteArray());
 		           Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT)); 		    }
 		} catch (NameNotFoundException e) {
 		    System.out.println("Name Not Found Exception");
 		} catch (NoSuchAlgorithmException e) {
 		    System.out.println("NoSuchAlgorithmException");

 		}
        
      //Hides the titlebar
      	//	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        getActionBar().hide();
        	System.out.println("enter sleep");
        	mythread=new MyThread();
      		mythread.start();
      	}
    public Handler getHandler(){
		return this.handler;
	}
    @Override
    public void onBackPressed()
    {
    	
    }
    class MyThread extends Thread {
    	
    	public void run(){
    		
    		try {
    			while (splashActive && ms < splashTime) {
						if(!paused)
							ms=ms+100;
						System.out.println("sleep");
						sleep(100);
					}

    		}catch(Exception e){}
    		System.out.println("sleep end-------------");
    		System.out.println("sleep end-------------");
    		Message msg=new Message();//.obtain();
    		msg.obj=(1);
    		System.out.println("start msg-------------");
    		System.out.println("start msg-------------");
    		handler.sendMessage(msg);
    		System.out.println("msg send-------------");
    		System.out.println("msg send-------------");
    	}
    }   
    }



