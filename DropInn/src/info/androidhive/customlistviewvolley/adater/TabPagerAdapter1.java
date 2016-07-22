package info.androidhive.customlistviewvolley.adater;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;

import com.cog.DropInn.Inbox1;
import com.cog.DropInn.MyReservation;
import com.cog.DropInn.Myhost2;
import com.cog.DropInn.Yourlisting;

public class TabPagerAdapter1 extends FragmentStatePagerAdapter {
	
	DrawerLayout mDrawerLayout;
	String userid="";
	
	
    public TabPagerAdapter1(FragmentManager fm) {
		super(fm);
		
		

		// TODO Auto-generated constructor stub
	}
    
  

	@Override
	public Fragment getItem(int position) {
		
		
		
		System.out.println("inside swicth case adapter ==="+position);
		
		switch (position) {
        case 0:
            //Fragement for discover Tab
        	System.out.println("inside discover case in tab adapter page ");
            return new MyReservation();
            
        case 1:
            //Fragement for discover Tab
        	System.out.println("inside discover case in tab adapter page ");
            return new Yourlisting();
/*        case 2:
           //Fragment for search Tab
        	System.out.println("inside inbox activity case in tab adapter");
        	 return new Inbox1();
        case 3:
            //Fragment for search Tab
         	System.out.println("inside yourlisting activity case in tab adapter");
             return new Myhost2();*/
           
      /*  case 3:
            //Fragment for search Tab
         	System.out.println("inside yourlisting activity case in tab adapter");
        	     */
        
                    
        }
		return null;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return 2; //No of Tabs
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