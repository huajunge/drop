package com.cog.DropInn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class How_it_works extends Activity {
	
	
	ImagePagerAdapter adapter;
	ViewPager viewPager;
	CirclePageIndicator circleIndicator;
	
	float density;
	TextView Text;
	Button Button;
	ImageView back;
	String userid;
	
	@Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_it_works);
        getActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Text = (TextView) findViewById(R.id.textView1);
    	Text.setText("Live like a local anywhere in the world");
        Text.setMovementMethod(new ScrollingMovementMethod());
        back=(ImageView)findViewById(R.id.imageView1);
        Button = (Button) findViewById(R.id.Button);
    	Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.blue));
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
        
        SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);

        Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i=new Intent(How_it_works.this,Listspace.class);
			startActivity(i);
			 finish();
			}
		});
back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(How_it_works.this,Swipe_tabs.class);
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
					Text.setText("Live like a local anywhere in the world");
					Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.blue));
				  }
				  if(position==1)
				  {
		            Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.red));
		            Text.setText("Join a community of millions of trusted travellers and hosts");
				  }
				  else if(position==2)
				  {

		            Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.blue));
		            Text.setText("Rent unique places to stay,from villas to tree houses");  
				  }
				  else if(position==3)
				  {
					Button.setBackgroundColor(Button.getContext().getResources().getColor(R.color.red));
		            Text.setText("Share a home with your host or have the place to yourself");  
				  }
					  
			  }};

	
	private class ImagePagerAdapter extends PagerAdapter {
        private final int[] mImages = new int[] {
                R.drawable.how_it_works_1,
                R.drawable.how_it_works_2,
                R.drawable.how_it_works_3,
                R.drawable.how_it_works_4,
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
			       
            final Context context = How_it_works.this;
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

	