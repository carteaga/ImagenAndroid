package com.clasesandroid.carteaga;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ImagenAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageView image = (ImageView) findViewById(R.id.img_1);
        //String URL = Image.images[1];
        
        /*
         * Los tag es algo que identifica cierta no sé xD
         * 
         */
        //image.setTag(URL);
        
    }
    
}


/**
 * 
 * @author smeild
 *
 */
class ImageDownloads extends AsyncTask<ImageView, Void, Bitmap> {
	
	ImageView imageView = null;
	private Bitmap bm;
	
	@Override
	protected Bitmap doInBackground(ImageView... imageView) {
		// TODO Auto-generated method stub
		//this.imageView = imagesViews[0];
		this.imageView = imageView[0];
		
		return null;
	}
	
	private Bitmap download_Image(String url){
		try{
			URL aURl = new URL(url);
			URLConnection conn = aURl.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bm = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
			 Log.e(ImageDownloads.class.getName(), e.getMessage());
		}
		return bm;
		
	}
}
