package com.cog.DropInn;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class Multiimage extends Activity {
    String room_id,dis,dis1,dis2,dis3,dis4,image,state,city,country;
	Button gallery,camera;
	ImageView display,display1,display2,display3,display4,delete,delete1,delete2,delete3,delete4,dustbin;
	TextView remove,photo,photocount;
	ImageButton done;
	Vibrator vibe;
	int count=0;
	Toast toast;
	  //final Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
	private String stry;
	private Bitmap bitmap_profile_image;
	 private String imagepath;
	 String picturePath;
	 String userid;
	 String Liveurl="";
	 String image1,resize,resize1;
	 String picture=null;
	    String[] strArray = new String[5];
	URL Login_Url,Login_Url1;
	
	URL image_Url;
	String  image_inputline,image_status,image_id;
	JSONArray image_jsonarray;
	JSONObject image_jsonobj;
	String step,check;
	String first,second,third,fourth,last;
	   private JSONArray login_jsonarray,login_jsonarray1;
	       private JSONObject login_jsonobj,login_jsonobj1;
	       private String login_status,login_status1;
	       private String login_userid;
	       private String login_inputline,login_inputline1;
	       String latitude1,longitude1;
	private static final int CAM_REQUREST = 1313;
	private static int RESULT_LOAD_IMAGE = 1;
	//private static int RESULT_LOAD_IMAGE1 = 2;
	//private static int RESULT_LOAD_IMAGE2 = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
     }
	
       SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       Liveurl = sharedPreferences.getString("liveurl", null); 
       
		 SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		 userid = sharedPreferences2.getString("userid", null);
       
   
       
   	Intent i=getIntent();
   	//userid=i.getStringExtra("userid");
	room_id=i.getStringExtra("room_id");
	dis1=i.getStringExtra("summary");
	dis2=i.getStringExtra("price");
	System.out.println("check price in multiimage =="+dis2);
	dis3=i.getStringExtra("addresss");
	image=i.getStringExtra("image");
	image1=i.getStringExtra("image1");
	image_id=image1;
	resize=i.getStringExtra("resize");
	dis4=i.getStringExtra("title");  
	city=i.getStringExtra("city");
	state=i.getStringExtra("state");
	country=i.getStringExtra("country");	
	step=i.getStringExtra("step");
	check=i.getStringExtra("check");
	//String latitude1,longitude1;

			latitude1=i.getStringExtra("latitude");
			longitude1=i.getStringExtra("longitude");
			
			/*optional.putExtra("latitude", latitude1);
			optional.putExtra("longitude",longitude1);*/
	System.out.println("city in multimage"+city);
	System.out.println("state in multimage"+state);
	System.out.println("country in multimage"+country);
	System.out.println("address in multimage"+dis3);
	

	
	System.out.println("image in multii image"+image);
	setContentView(R.layout.activity_multiimage);
	getActionBar().hide();
	display=(ImageView)findViewById(R.id.imageView1);
	display1=(ImageView)findViewById(R.id.imageView2);
	display2=(ImageView)findViewById(R.id.imageView3);
	display3=(ImageView)findViewById(R.id.imageView4);
	display4=(ImageView)findViewById(R.id.imageView5);
	gallery=(Button)findViewById(R.id.button1);
	camera=(Button)findViewById(R.id.button2);
	display.setVisibility(View.INVISIBLE);
	display1.setVisibility(View.INVISIBLE);
	display2.setVisibility(View.INVISIBLE);
	display3.setVisibility(View.INVISIBLE);
	display4.setVisibility(View.INVISIBLE);	
	done=(ImageButton)findViewById(R.id.TextView1);
	remove=(TextView)findViewById(R.id.TextView5);
	photo=(TextView)findViewById(R.id.TextView2);
	photocount=(TextView)findViewById(R.id.TextView3);
	delete=(ImageView) findViewById(R.id.imageView6);
	delete1=(ImageView) findViewById(R.id.imageView7);
	delete2=(ImageView) findViewById(R.id.imageView8);
	delete3=(ImageView) findViewById(R.id.imageView9);
	delete4=(ImageView) findViewById(R.id.imageView10);
	dustbin=(ImageView) findViewById(R.id.imageView11);
	delete.setVisibility(View.INVISIBLE);
	delete1.setVisibility(View.INVISIBLE);
	delete2.setVisibility(View.INVISIBLE);
	delete3.setVisibility(View.INVISIBLE);
	delete4.setVisibility(View.INVISIBLE);
	dustbin.setVisibility(View.INVISIBLE);
	remove.setVisibility(View.INVISIBLE);
	

	
	 System.out.println("multiimage page images1===="+ first);
     System.out.println("images2===="+second);
     System.out.println("image3===="+ third);
     System.out.println("image4===="+ fourth);
     System.out.println("images5===="+ last);

	if(image!=null)
	{
	try{
		photo.setVisibility(View.INVISIBLE);
		dustbin.setVisibility(View.VISIBLE);
		remove.setVisibility(View.VISIBLE);
		
	System.out.println("Display inside image first ");
	URL pimage=new URL(image);
	 	Bitmap edbitmap = BitmapFactory.decodeStream(pimage.openStream());
	 	
	 	 Display display1 = getWindowManager().getDefaultDisplay();
         Point size = new Point();
         display1.getSize(size);
         int width = size.x;
         int height = size.y;
         Log.e("Screen width ", " "+width);
         Log.e("Screen height ", " "+height);
         Log.e("img width ", " "+edbitmap.getWidth());
         Log.e("img height ", " "+edbitmap.getHeight());
         float scaleHt =(float) width/edbitmap.getWidth();
         Log.e("Scaled percent ", " "+scaleHt);
         Bitmap scaled = Bitmap.createScaledBitmap(edbitmap,     width, (int) (edbitmap.getWidth()*scaleHt), true);
         display.setImageBitmap(scaled);
         display.setScaleType(ScaleType.FIT_XY);
	//display.setImageBitmap(getResizedBitmap(edbitmap,1282,820));
	/*display.setImageBitmap(edbitmap);
	display.setScaleType(ScaleType.FIT_XY)*/;
	display.setVisibility(View.VISIBLE);
	//count++;
	photocount.setText("1");
	
	}
	
	catch(Exception e){
	 	e.printStackTrace();
	 	}
	}
	
	
	/*else{
		display.setVisibility(View.GONE);
		delete.setVisibility(View.GONE);
	}

	if(second!=null)
	{
	try{
		//photocount.setVisibility(View.INVISIBLE);
		photo.setVisibility(View.INVISIBLE);
		dustbin.setVisibility(View.VISIBLE);
		remove.setVisibility(View.VISIBLE);
	
	
	System.out.println("Display inside image second ");
	URL pimage1=new URL(second);
	 	Bitmap edbitmap1 = BitmapFactory.decodeStream(pimage1.openStream());
	//display.setImageBitmap(getResizedBitmap(edbitmap,1282,820));
	display1.setImageBitmap(edbitmap1);
	display1.setScaleType(ScaleType.FIT_XY);
	display1.setVisibility(View.VISIBLE);
	count++;
	photocount.setText(String.valueOf(count));
	}
	
	catch(Exception e){
	 	e.printStackTrace();
	 	}
	}

	if(third!=null)
	{
	try{
		//photocount.setVisibility(View.INVISIBLE);
		photo.setVisibility(View.INVISIBLE);
		dustbin.setVisibility(View.VISIBLE);
	remove.setVisibility(View.VISIBLE);
	
	
	System.out.println("Display inside image third ");
	URL pimage2=new URL(third);
	 	Bitmap edbitmap2 = BitmapFactory.decodeStream(pimage2.openStream());
	//display.setImageBitmap(getResizedBitmap(edbitmap,1282,820));
	display2.setImageBitmap(edbitmap2);
	display2.setScaleType(ScaleType.FIT_XY);
	display2.setVisibility(View.VISIBLE);
	count++;
	photocount.setText(String.valueOf(count));
	}
	
	catch(Exception e){
	 	e.printStackTrace();
	 	}
	}
	

	if(fourth!=null)
	{
	try{
		//photocount.setVisibility(View.INVISIBLE);
		photo.setVisibility(View.INVISIBLE);
		dustbin.setVisibility(View.VISIBLE);
		remove.setVisibility(View.VISIBLE);
	
	System.out.println("Display inside image fourth ");
	URL pimage3=new URL(fourth);
	 	Bitmap edbitmap3 = BitmapFactory.decodeStream(pimage3.openStream());
	//display.setImageBitmap(getResizedBitmap(edbitmap,1282,820));
	display3.setImageBitmap(edbitmap3);
	display3.setScaleType(ScaleType.FIT_XY);
	display3.setVisibility(View.VISIBLE);
	count++;
	photocount.setText(String.valueOf(count));
	}
	
	catch(Exception e){
	 	e.printStackTrace();
	 	}
	}
	if(last!=null)
	{
	try{
		//photocount.setVisibility(View.INVISIBLE);
		dustbin.setVisibility(View.VISIBLE);
		remove.setVisibility(View.VISIBLE);
	
	
	System.out.println("Display inside image fifth");
	URL pimage4=new URL(last);
	 	Bitmap edbitmap4 = BitmapFactory.decodeStream(pimage4.openStream());
	//display.setImageBitmap(getResizedBitmap(edbitmap,1282,820));
	display4.setImageBitmap(edbitmap4);
	display4.setScaleType(ScaleType.FIT_XY);
	display4.setVisibility(View.VISIBLE);
	count++;
	photocount.setText(String.valueOf(count));
	}
	
	catch(Exception e){
	 	e.printStackTrace();
	 	}
	}*/
	
	done.setOnClickListener(new View.OnClickListener() {
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override
	public void onClick(View v) {
	// TODO Auto-generated method stub
		
	Intent dn=new Intent(Multiimage.this,List_your_space.class);
	Bundle bndlanimation =ActivityOptions.makeCustomAnimation(Multiimage.this, R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
	dn.putExtra("image", image);
	System.out.println("image in multii image inside intent "+image);
	dn.putExtra("image1",image_id);
	System.out.println("image in multii image inside intent "+image_id);
	//dn.putExtra("resize",resize);
	dn.putExtra("room_id", room_id);
	dn.putExtra("write_title", dis4);
	dn.putExtra("write_summary", dis1);
	dn.putExtra("write_price", dis2);
	
	dn.putExtra("city", city);
	dn.putExtra("state", state);
	dn.putExtra("country", country);
	dn.putExtra("address", dis3);
	dn.putExtra("userid", userid);
	
	dn.putExtra("first", first);
	dn.putExtra("second", second);
	dn.putExtra("third", third);
	dn.putExtra("fourth", fourth);
	dn.putExtra("last", last);

	dn.putExtra("latitude", latitude1);
	dn.putExtra("longitude",longitude1);
	dn.putExtra("step",step);
	dn.putExtra("check",check);
	
	startActivity(dn,bndlanimation);
	finish();
	}
	});
	gallery.setOnClickListener(new OnClickListener() {

	
          	public void onClick(View v) {         		
          		
          		
picture="gallery";
          	
          	Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
          	startActivityForResult(i, RESULT_LOAD_IMAGE);
          	
          	}
          });
	
	camera.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
	// TODO Auto-generated method stub
	//count++;
		picture="camera";
	Intent cameraIntent = new Intent(
          	android.provider.MediaStore.ACTION_IMAGE_CAPTURE);


          	cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
          	MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());


          	try {
          	cameraIntent.putExtra("return-data", true);
          	   
          	startActivityForResult(cameraIntent, CAM_REQUREST);
          	

          	} catch (ActivityNotFoundException e) {

          	}
	}
	});
	
dustbin.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
	// TODO Auto-generated method stub
remove();
	}
	});
remove.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		remove();
	}
});

	}
	
	/**
	 * Clear the views and free memory
	 */
	
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	super.onActivityResult(requestCode, resultCode, data);
	if(data!=null){
	if (requestCode == CAM_REQUREST) {
	Bundle extras = data.getExtras();
	//if (extras == null || extras.get("data") == null) 
	if (extras != null) {
	
	//count++;
	 
	    bitmap_profile_image = extras.getParcelable("data");
	 bitmap_profile_image = (Bitmap) data.getExtras().get("data");
	
		
		 dustbin.setVisibility(View.VISIBLE);
			remove.setVisibility(View.VISIBLE);
	 display.setImageBitmap(bitmap_profile_image);
	 display.setVisibility(View.INVISIBLE);
	 display.setScaleType(ScaleType.FIT_XY);
	 display.setVisibility(View.VISIBLE);
	 photo.setVisibility(View.INVISIBLE);
	 photocount.setText("1");
	 new ProgressTask(Multiimage.this).execute();	
	 /*imagepath = ImageWrite(bitmap_profile_image);
	    Imageuploading();*/
	
/*	 if(count==2)
	{	
		 dustbin.setVisibility(View.VISIBLE);
			remove.setVisibility(View.VISIBLE);
	 display1.setImageBitmap(bitmap_profile_image);
	 display1.setVisibility(View.VISIBLE);
	 display1.setScaleType(ScaleType.FIT_XY);
	 photocount.setText(String.valueOf(count));
	 new ProgressTask(Multiimage.this).execute();
	 imagepath = ImageWrite(bitmap_profile_image);
	    Imageuploading();
	}
	 if(count==3)
	{	
		 dustbin.setVisibility(View.VISIBLE);
			remove.setVisibility(View.VISIBLE);
	 display2.setImageBitmap(bitmap_profile_image);
	 display2.setVisibility(View.VISIBLE);
	 display2.setScaleType(ScaleType.FIT_XY);
	 photocount.setText(String.valueOf(count));
	 new ProgressTask(Multiimage.this).execute();
	 imagepath = ImageWrite(bitmap_profile_image);
	    Imageuploading();
	}
	 if(count==4)
	{	
		 dustbin.setVisibility(View.VISIBLE);
			remove.setVisibility(View.VISIBLE);
	 display3.setImageBitmap(bitmap_profile_image);
	 display3.setVisibility(View.VISIBLE);
	 display3.setScaleType(ScaleType.FIT_XY);
	 photocount.setText(String.valueOf(count));
	 new ProgressTask(Multiimage.this).execute();
	}
	 if(count==5)
	{	
		 dustbin.setVisibility(View.VISIBLE);
			remove.setVisibility(View.VISIBLE);
	 display4.setImageBitmap(bitmap_profile_image);
	 display4.setVisibility(View.VISIBLE);
	 display4.setScaleType(ScaleType.FIT_XY);
	 photocount.setText(String.valueOf(count));
	 new ProgressTask(Multiimage.this).execute();
	 imagepath = ImageWrite(bitmap_profile_image);
	    Imageuploading();
	}*/
	}
	  
	
	} else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	//count++;
	System.out.println("TEST"+RESULT_LOAD_IMAGE);

	Uri selectedImage = data.getData();
	String[] filePathColumn = {
	MediaStore.Images.Media.DATA
	};
	

	Cursor cursor = getContentResolver().query(selectedImage,
	filePathColumn, null, null, null);
	cursor.moveToFirst();


	int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	 picturePath = cursor.getString(columnIndex);
	 cursor.close();

	//ImageView imageView = (ImageView) findViewById(R.id.displayView);
	/*System.out.println("Display"+display);
	System.out.println("Display"+display.toString());
	System.out.println("Display"+display.getDrawableState());
*/
	 BitmapFactory.Options options = new BitmapFactory.Options();
	 options.inSampleSize = 4;

     Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
     int height = bitmap.getHeight(), width = bitmap.getWidth();
     
     if (height > 1280 && width > 960){
         Bitmap imgbitmap = BitmapFactory.decodeFile(picturePath, options);
         display.setImageBitmap(imgbitmap);

         System.out.println("Need to resize");

     }else {
    	 display.setImageBitmap(bitmap);
         System.out.println("WORKS");
     }
		
		dustbin.setVisibility(View.VISIBLE);
		remove.setVisibility(View.VISIBLE);
		
	//display.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	display.setScaleType(ScaleType.FIT_XY);
	display.setVisibility(View.VISIBLE);
	photo.setVisibility(View.INVISIBLE);
	photocount.setText("1");
	new ProgressTask(Multiimage.this).execute();
	
	/*else if(count==2)
	{
		dustbin.setVisibility(View.VISIBLE);
		remove.setVisibility(View.VISIBLE);
	display1.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	display1.setScaleType(ScaleType.FIT_XY);
	display1.setVisibility(View.VISIBLE);
	photocount.setText(String.valueOf(count));
	new ProgressTask(Multiimage.this).execute();
	}
	else if(count==3)
	{
		dustbin.setVisibility(View.VISIBLE);
		remove.setVisibility(View.VISIBLE);
	display2.setImageBitmap((BitmapFactory.decodeFile(picturePath)));
	display2.setVisibility(View.VISIBLE);
	display2.setScaleType(ScaleType.FIT_XY);
	photocount.setText(String.valueOf(count));
	new ProgressTask(Multiimage.this).execute();
	}
	else if(count==4)
	{
	dustbin.setVisibility(View.VISIBLE);
	remove.setVisibility(View.VISIBLE);
	display3.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	display3.setVisibility(View.VISIBLE);
	display3.setScaleType(ScaleType.FIT_XY);
	photocount.setText(String.valueOf(count));
	new ProgressTask(Multiimage.this).execute();
	}
	else if(count==5)
	{
	dustbin.setVisibility(View.VISIBLE);
	remove.setVisibility(View.VISIBLE);
	display4.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	display4.setVisibility(View.VISIBLE);
	display4.setScaleType(ScaleType.FIT_XY);
	photocount.setText(String.valueOf(count));
	new ProgressTask(Multiimage.this).execute();
	}*/
	}
	}
	
}
/*	public Bitmap getResizedBitmap(Bitmap setImageBitmap,int newWidth,int newHeight) {
	    int width = setImageBitmap.getWidth();
	    int height = setImageBitmap.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // CREATE A MATRIX FOR THE MANIPULATION
	    Matrix matrix = new Matrix();
	    // RESIZE THE BIT MAP
	    matrix.postScale(scaleWidth, scaleHeight);

	    // "RECREATE" THE NEW BITMAP
	    Bitmap resizedBitmap = Bitmap.createBitmap(setImageBitmap, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}*/
	public String ImageWrite(Bitmap bitmap1)
    {
       
         String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            OutputStream outStream = null;
            File file = new File(extStorageDirectory, "slectimage.PNG");
           
            try
            {

                outStream = new FileOutputStream(file);
                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
                outStream.close();

              

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
               
            } catch (IOException e)
            {
                e.printStackTrace();
              
            }
            String imageInSD = "/sdcard/slectimage.PNG";       
            return imageInSD;
       
    }
	
	
	protected void Imageuploading() {
	// TODO Auto-generated method stub

	try {
	Log.e("image", "dfdf");

	HttpURLConnection connection = null;
	DataOutputStream outputStream = null;
	DataInputStream inputStream = null;

	String pathToOurFile = (String) imagepath;

	
	//String urlServer = " http://demo.cogzideltemplates.com/client/gottospot_android/mobile/user/post_upload?room_id=1&user_id=12";
	String urlServer =  Liveurl+"user/post_upload?room_id="+room_id+"&user_id="+userid;
	String lineEnd = "\r\n";
	System.out.println("url"+urlServer);
	String twoHyphens = "--";
	String boundary = "*****";

	int bytesRead, bytesAvailable, bufferSize;
	byte[] buffer;
	int maxBufferSize = 1 * 1024 * 1024;
	FileInputStream fileInputStream = new FileInputStream(new File(
	pathToOurFile));

	URL url = new URL(urlServer);
	connection = (HttpURLConnection) url.openConnection();

	// Allow Inputs & Outputs
	connection.setDoInput(true);
	connection.setDoOutput(true);
	connection.setUseCaches(false);

	// Enable POST method
	connection.setRequestMethod("POST");

	connection.setRequestProperty("Connection", "Keep-Alive");
	connection.setRequestProperty("Content-Type",
	"multipart/form-data;boundary=" + boundary);

	outputStream = new DataOutputStream(connection.getOutputStream());
	outputStream.writeBytes(twoHyphens + boundary + lineEnd);
	outputStream
	.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
	+ pathToOurFile + "\"" + lineEnd);
	outputStream.writeBytes(lineEnd);

	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	buffer = new byte[bufferSize];

	// Read file
	bytesRead = fileInputStream.read(buffer, 0, bufferSize);

	while (bytesRead > 0) {
	outputStream.write(buffer, 0, bufferSize);
	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	}

	outputStream.writeBytes(lineEnd);
	outputStream.writeBytes(twoHyphens + boundary + twoHyphens
	+ lineEnd);
	// Responses from the server (code and message)
	int serverResponseCode = connection.getResponseCode();
	String serverResponseMessage = connection.getResponseMessage();

	// Toast.makeText(getApplicationContext(), serverResponseMessage,
	// Toast.LENGTH_LONG).show();
	System.out.println("image" + serverResponseMessage);

	fileInputStream.close();
	outputStream.flush();
	outputStream.close();

	DataInputStream inputStream1 = null;
	inputStream1 = new DataInputStream(connection.getInputStream());
	System.out.println("before input stream image url" + inputStream1);
	String str = "";
	String Str1_imageurl = "";

	while ((str = inputStream1.readLine()) != null) {
	Log.e("Debug", "Server Response " + str);

	Str1_imageurl = str;
	Log.e("Debug", "Server Response String imageurl" + str);
	}
	inputStream1.close();
	System.out.println("after input stream image url" + inputStream1);
	System.out.println("image url" + Str1_imageurl);
	// Toast.makeText(getApplicationContext(), Str1_imageurl,
	// Toast.LENGTH_LONG).show();

	stry = Str1_imageurl.trim();
	
	//Login_Url1=stry;
	System.out.println("check stry"+stry);
	  
	
	try {         
	            login_jsonarray1 = new JSONArray(stry);        
	                   
	             
	             for(int i=0; i <login_jsonarray1.length(); i++)
	                {
	                                  
	            login_jsonobj1 = login_jsonarray1.getJSONObject(i);
	            image1 =    login_jsonobj1.getString("image");
	            resize=login_jsonobj1.getString("resize");
	            image=login_jsonobj1.getString("resize1");
	                           
	                }

	      System.out.println("after image===="+image1);
	      System.out.println("after resize==="+resize);
	      System.out.println("after resize1===="+image);
	      System.out.println("");
	          
	      
	      
	      
	     // String strName = resize1;
  
	       
	       
	           
	        }  catch (JSONException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	} catch (Exception e) {

		e.printStackTrace();

		}
	
	
	return_image();
	
	//call_image();
	//msgToSend = stry;
	//************************* five images stored in array*****************************
	/*  strArray[count-1]=resize1;
      System.out.println("images===="+ strArray[0]);
      System.out.println("images===="+ strArray[1]);
      System.out.println("images===="+ strArray[2]);
      System.out.println("images===="+ strArray[3]);
      System.out.println("images===="+ strArray[4]);
             
      first=strArray[0];
      second=strArray[1];
      third=strArray[2];
      fourth=strArray[3];
      last=strArray[4];
	
      SharedPreferences sharedpreferences1=PreferenceManager.getDefaultSharedPreferences(this);
      Editor editor = sharedpreferences1.edit();
          
      editor.putString("firstimage",first);
      editor.putString("secondimage",second);
      editor.putString("thirdimage",third);
      editor.putString("fourthimage",fourth);
      editor.putString("lastimage",last);
      editor.commit();*/
//********************************************************************************************
	//script();

	}
	
	public void delete_image()
	{
	  try {
	        	
	        	Login_Url= new URL(Liveurl+"rooms/delete_image?room_id="+room_id+"&image_id="+image_id);
	           
	            //Login_Url= new URL(Liveurl+"mobile/signup_second?user_id="+h_id+"&email="+str1+"&password="+str2+"&phone_number="+str3);
	           System.out.println("delete url"+Login_Url);
	           
	           BufferedReader login_reader;
	            String str_login="";

	            login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
	           
	            String s=Login_Url.toString();
	           
	            while ((login_inputline = login_reader.readLine())!= null)
	            {
	               
	                str_login += login_inputline;
	                              
	            }
	           

	            System.out.print("logo"+str_login);
	                  
	       	           
	           
	            login_jsonarray = new JSONArray(str_login);        
	                   
	             
	             for(int i=0; i <login_jsonarray.length(); i++)
	                {
	                                  
	            login_jsonobj = login_jsonarray.getJSONObject(i);
	            login_status =    login_jsonobj.getString("status");
	                           
	                }

	      System.out.println("after json"+login_status);
	             
	             if(login_status.matches("No Data Found")){
	                 
	            	 /*toast=   Toast.makeText(getApplicationContext(), "No Image Found", Toast.LENGTH_LONG);
	    	         toast.setGravity(Gravity.CENTER, 0, 0);
	    	         toast.show();*/
	            	 
	            	 
	                 System.out.println("no data found");
	                 
	                                 
	             }
	             else if(login_status.matches("Deleted Successfully"))
	             {
	            /*	 toast=   Toast.makeText(getApplicationContext(), "Image Deleted successfully", Toast.LENGTH_LONG);
	    	         toast.setGravity(Gravity.CENTER, 0, 0);
	    	         toast.show(); */
	             }
	             

	       
	           
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	           
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (JSONException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	}
 
	 private class ProgressTask extends AsyncTask<String, Void, Boolean> 
	 {
	     private ProgressDialog dialog;
	     private Multiimage activity;

	     public ProgressTask(Multiimage activity) 
	     {
	         this.activity = activity;
	         context = activity;
	         dialog = new ProgressDialog(context);
	     }

	     private Context context;

	     protected void onPreExecute() 
	     {
	         dialog = new ProgressDialog(context);
	         dialog.setMessage("Uploading...");
	         dialog.setIndeterminate(false);
	         dialog.setCancelable(false);
	         dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	         dialog.show();
	     }

	     @Override
	     protected void onPostExecute(final Boolean success) 
	     {
	         if (dialog.isShowing()) 
	                     {
	             dialog.dismiss();
	         }
	         if (success) 
	                     {
	            // Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
	         } 
	                     else 
	                     {
	             //Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show();
	         }
	     }

	     @Override
	     protected Boolean doInBackground(final String... args) 
	     {
	         try {    
	            // ... processing ...
	        	 if(picture.equals("camera"))
	        	 {
	        		 imagepath = ImageWrite(bitmap_profile_image);
	        	 }
	        	 else if(picture.equals("gallery"))
	        	 {
	        		 imagepath =  ImageWrite(BitmapFactory.decodeFile(picturePath));
	        		 System.out.println("imageeeeeeeeeeeeeeeeeee" +imagepath);
	        	 }
	        	 else{
	        		 
	        	 }
         	
	 	 	    Imageuploading();
	        	 
	        	
	             return true;
	         } catch (Exception e){
	             Log.e("Schedule", "UpdateSchedule failed", e);
	             return false;
	         }
	     }
	 }
	 public void remove()
	 {
			//display2.destroyDrawingCache();
		 Bitmap  bmap = display.getDrawingCache();
		 /*Bitmap  bmap1 = display1.getDrawingCache();
		 Bitmap  bmap2 = display2.getDrawingCache();*/
		 System.out.println("Bitmap IS Check"+bmap);
		/* System.out.println("Bitmap IS Check"+bmap1);
		 System.out.println("Bitmap IS Check"+bmap2);*/
		
		 if(display.getDrawable()==null)
		{
			delete.setVisibility(View.INVISIBLE);
		}
		 
		/* if(display1.getDrawable()==null)
		 {
				delete1.setVisibility(View.INVISIBLE);
		 }
		 if(display2.getDrawable()==null)
		 {
			 delete2.setVisibility(View.INVISIBLE);
		 }
		 if(display3.getDrawable()==null)
		 {
			 delete3.setVisibility(View.INVISIBLE);
		 }
		 if(display4.getDrawable()==null)
		 {
			 delete4.setVisibility(View.INVISIBLE);
		 }*/
		 
			if(display.getDrawable()!=null)
			{
						
			dustbin.setVisibility(View.VISIBLE);
			remove.setVisibility(View.VISIBLE);
			delete.setVisibility(View.VISIBLE);
			delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
				
				display.setImageBitmap(null);
			display.destroyDrawingCache();
			image="";
			resize="";
			image1="";
			delete_image();
			
			display.setVisibility(View.GONE);
			delete.setVisibility(View.GONE);
		
			//count--;
			photocount.setText("0");
			}
			});
			
			}
			/*if(display1.getDrawable()!=null)
			{
				
				System.out.println("second image "+display1);
				dustbin.setVisibility(View.VISIBLE);
				remove.setVisibility(View.VISIBLE);
			delete1.setVisibility(View.VISIBLE);
		
			delete1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			display1.setImageBitmap(null);
			second=null;
			display1.destroyDrawingCache();
			display1.setVisibility(View.INVISIBLE);
			delete1.setVisibility(View.INVISIBLE);	
			
			count--;
			photocount.setText(String.valueOf(count));
			
			}
			});
			}
			if(display2.getDrawable()!=null)
			{
				
				dustbin.setVisibility(View.VISIBLE);
				remove.setVisibility(View.VISIBLE);
			delete2.setVisibility(View.VISIBLE);
			delete2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			display2.setImageBitmap(null);
			third=null;
			display2.destroyDrawingCache();
			display2.setVisibility(View.INVISIBLE);
			delete2.setVisibility(View.INVISIBLE);
			count--;
			photocount.setText(String.valueOf(count));
			}
			});
			}
			if(display3.getDrawable()!=null)
			{
				dustbin.setVisibility(View.VISIBLE);
				remove.setVisibility(View.VISIBLE);
			delete3.setVisibility(View.VISIBLE);
			delete3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			display3.setImageBitmap(null);
			fourth=null;
			display3.destroyDrawingCache();
			display3.setVisibility(View.INVISIBLE);
			delete3.setVisibility(View.INVISIBLE);
			count--;
			photocount.setText(String.valueOf(count));
			}
			});
			}
			if(display4.getDrawable()!=null)
			{
				dustbin.setVisibility(View.VISIBLE);
				remove.setVisibility(View.VISIBLE);
			delete4.setVisibility(View.VISIBLE);
			delete4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			display4.setImageBitmap(null);
			last=null;
			display4.destroyDrawingCache();
			display4.setVisibility(View.INVISIBLE);
			delete4.setVisibility(View.INVISIBLE);
			count--;
			photocount.setText(String.valueOf(count));
			}
			});
			}
			System.out.println("first image "+display.getDrawable());
			System.out.println("second image "+display1.getDrawable());
			System.out.println("third image "+display2.getDrawable());
			System.out.println("fourth image "+display3.getDrawable());
			System.out.println("last image "+display4.getDrawable());*/
	 }
	 public void return_image()
	 {
		 try
         { 
             
        	             
			// currency_Url = new URL("Liveurl+"user/edit_currency?roomid=2&currency=USD");
	            image_Url = new URL(Liveurl+"user/return_image1?room_id="+room_id+"&user_id="+userid+"&image="+image1+"&resize="+resize+"&resize1="+image);
			 System.out.println("return image url "+image_Url);
             BufferedReader image_reader;
             String str_currency="";            
            // Toast.makeText(getApplicationContext(), "The Email-Id U R Entered"+Get_email1,10000).show();
             
            image_reader = new BufferedReader(new InputStreamReader(image_Url.openStream()));
             		             
             
             while ((image_inputline = image_reader.readLine())!= null)
             {
           	  
                 str_currency += image_inputline;
                 
             }
     
             System.out.print("reset"+str_currency);
             
              image_jsonarray = new JSONArray(str_currency);
                 
            
                  for(int i=0; i <image_jsonarray.length(); i++)
                     {
               	   image_jsonobj=image_jsonarray.getJSONObject(i);
                 image_status = image_jsonobj.getString("status");
                 image_id=image_jsonobj.getString("image_id");
                
                 
                     }
                  System.out.print("image_status"+ image_status);
                  System.out.print("image_status"+ image_id);
                  if(image_status.matches("Successfully updated your photo"))
                 {
                     
               	   System.out.println("successfully updated your photo");
                    /* Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
                     toast1.setGravity(Gravity.CENTER, 0, 0);
                     toast1.show();*/
                                    
                   
                 }
                  else if(image_status.matches("Upload valid image"))
                  {
                      
                	   System.out.println("Upload valid image");
                     /* Toast toast1=Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT);
                      toast1.setGravity(Gravity.CENTER, 0, 0);
                      toast1.show();*/
                                     
                    
                  }
         
                   	                        
         
         }
         catch (MalformedURLException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         catch (IOException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         catch (JSONException e)
         {
             // TODO Auto-generated catch block
             e.printStackTrace();
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