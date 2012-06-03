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
        String URL = Image.images[0];
        Log.i("adads", Image.images[0]);
        image.setTag(URL);
        
        new ImageDownloads().execute(image);
        
        image = (ImageView) findViewById(R.id.img_2);
        URL = Image.images[1];
        Log.i("adads", Image.images[0]);
        image.setTag(URL);
        
        new ImageDownloads().execute(image);
        
        
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
	protected Bitmap doInBackground(ImageView... imageViews) {
		// TODO Auto-generated method stub
		this.imageView = imageViews[0];
	
		return download_Image((String) imageView.getTag());
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		imageView.setImageBitmap(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
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
