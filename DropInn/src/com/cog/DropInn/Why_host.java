package com.cog.DropInn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.view.ViewPager.PageTransformer;
import android.text.method.ScrollingMovementMethod;

import com.cog.DropInn.R;
import com.viewpagerindicator.CirclePageIndicator;

public class Why_host extends Activity {
	
	
	ImagePagerAdapter adapter;
	ViewPager viewPager;
	CirclePageIndicator circleIndicator;
	
	float density;
	TextView Text;
	ImageView back;
	Button Button;
	String userid;
	
	@Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.why_host);
        getActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Text = (TextView) findViewById(R.id.textView1);
    	Text.setText("Earn money by sharing your extra space or entire room");
        Text.setMovementMethod(new ScrollingMovementMethod());
        back=(ImageView)findViewById(R.id.imageView1);
        Button = (Button) findViewById(R.id.Button);
    	Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.blue));
    	  Intent i1=getIntent();
 		 userid=i1.getStringExtra("userid");

  SharedPreferences sharedPreferences3 = 

 PreferenceManager.getDefaultSharedPreferences(this);
          Editor editor = sharedPreferences3.edit();
        /*  editor.putString("liveurl", LiveUrl);*/  
         editor.putString ("userid", userid);
         System.out.println("Australia==== "+userid);
          editor.commit();
          
          SharedPreferences sharedPreferences2 = 

 PreferenceManager.getDefaultSharedPreferences(this);
          
          userid = sharedPreferences2.getString("userid", null);
          System.out.println("india==== "+userid);
    	
        viewPager.setPageTransformer(false, new PageTransformer() {

            @Override
            public void transformPage(View page, float position) {
            	 page.setTranslationX(page.getWidth() * -position);
                // TODO Auto-generated method stub
            	if(position <= -1.0F || position >= 1.0F) {
                    page.setAlpha(0.0F);
                } else if( position == 0.0F ) {
                    page.setAlpha(1.0F);
                } else { 
                    // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                    page.setAlpha(1.0F - Math.abs(position));
                }
            }
        });
        adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);
        circleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
        circleIndicator.setOnPageChangeListener(myOnPageChangeListener);
        
        density = getResources().getDisplayMetrics().density;
        circleIndicator.setRadius(6 * density);

        Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i=new Intent(Why_host.this,Listspace.class);
			i.putExtra("userid", userid);
			startActivity(i);
			finish();
			}
		});
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(Why_host.this,Swipe_tabs.class);
				back.putExtra("userid", userid);
				startActivity(back);
				finish();
				
				
			}
		});
    }
	
	OnPageChangeListener myOnPageChangeListener = 
			   new OnPageChangeListener(){

			  @Override
			  public void onPageScrollStateChanged(int state) {
			   //Called when the scroll state changes.
			  }

			  @Override
			  public void onPageScrolled(int position, 
			    float positionOffset, int positionOffsetPixels) {
			   //This method will be invoked when the current page is scrolled, 
			   //either as part of a programmatically initiated smooth scroll 
			   //or a user initiated touch scroll.
			  }

			  @Override
			  public void onPageSelected(int position) {
			   //This method will be invoked when a new page becomes selected.
				  
				  if(position==0)
				  {
					Text.setText("Earn money by sharing your extra space or entire room");
					Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.blue));
				  }
				  if(position==1)
				  {
		            Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.red));
		            Text.setText("With trusted profiles and tools,you decide who stays and when to host");
				  }
				  else if(position==2)
				  {

		            Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.blue));
		            Text.setText("Welcome travelers around the world to your neighbourhood");  
				  }
				  else if(position==3)
				  {
					Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.red));
		            Text.setText("Join a community of passionate hosts and enjoy a 24-hour support");  
				  }
					  
			  }};

	
	private class ImagePagerAdapter extends PagerAdapter {
        private final int[] mImages = new int[] {
                R.drawable.why_host_1,
                R.drawable.why_host_2,
                R.drawable.why_host_3,
                R.drawable.why_host_4,
        };
        
        @Override
        public void destroyItem(final ViewGroup container, final int position, final Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }

        @Override
        public int getCount() {
            return this.mImages.length;
            
        }
        

		@Override
        public Object instantiateItem(final ViewGroup container, final int position) {
			       
            final Context context = Why_host.this;
            final ImageView imageView = new ImageView(context);
            imageView.setImageResource(this.mImages[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ((ViewPager) container).addView(imageView, 0);
            
            return imageView;
            
        }

        @Override
        public boolean isViewFromObject(final View view, final Object object) {
            return view == ((ImageView) object);
        }
           
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
	