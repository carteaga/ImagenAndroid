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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImagenAndroidActivity extends Activity {
	/** Called when the activity is first created. */

	private Integer pos_url = 0; // maneja en que url posición de url va
	private ImageView[] imageViews;
	private Button btn_forward, btn_back;
	private ImageView imagePreview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageViews = new ImageView[] { (ImageView) findViewById(R.id.img_1),
				(ImageView) findViewById(R.id.img_2),
				(ImageView) findViewById(R.id.img_3),
				(ImageView) findViewById(R.id.img_4),
				(ImageView) findViewById(R.id.img_5) };
		btn_forward = (Button) findViewById(R.id.btn_adv);
		btn_back = (Button) findViewById(R.id.btn_back);
		imagePreview = (ImageView) findViewById(R.id.img_preview);

		
		forward();
		btn_back.setEnabled(false);
		
		btn_forward.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				forward();
			}
		});
		
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				back();
			}
		});
		// función para el preview
		imageViews[0].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imagePreview.setImageDrawable(imageViews[0].getDrawable());
			}
		});

		imageViews[0].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imagePreview.setImageDrawable(imageViews[0].getDrawable());
			}
		});

		imageViews[0].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imagePreview.setImageDrawable(imageViews[0].getDrawable());
			}
		});

		imageViews[1].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imagePreview.setImageDrawable(imageViews[1].getDrawable());
			}
		});

		imageViews[2].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imagePreview.setImageDrawable(imageViews[2].getDrawable());
			}
		});

		imageViews[3].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imagePreview.setImageDrawable(imageViews[3].getDrawable());
			}
		});

		imageViews[4].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imagePreview.setImageDrawable(imageViews[4].getDrawable());
			}
		});

	}

	private void forward() {
		Integer img_load = Image.images.length - pos_url;
		Integer stop;
		btn_back.setEnabled(true);
		if (img_load > 5) {
			stop = pos_url + 5;
			load_image(stop);
			btn_forward.setEnabled(true);
		} else {
			stop = (pos_url + img_load);
			load_image(stop);
			btn_forward.setEnabled(false);
		}
	}

	private void back() {
		pos_url = pos_url - 5;
		Integer stop;
		btn_forward.setEnabled(true);
		if(pos_url > 5){
			stop = pos_url;
			pos_url = pos_url - 5;
			load_image(stop);
			btn_back.setEnabled(true);
		}else{
			pos_url = 0;
			stop = 5;
			load_image(stop);
			btn_back.setEnabled(false);
		}
	}
	
	private void load_image(Integer end){
		setIconDefault();
		for (int j = 0; pos_url < end; j++) {
			String URL = Image.images[pos_url];
			Log.i("asdasd", ""+pos_url);
			Log.i("carga", URL);
			imageViews[j].setTag(URL);
			new ImageDownloads().execute(imageViews[j]);
			pos_url++;
		}
	}
	
	private void setIconDefault(){
		for(int i=0; i<5; i++){
			imageViews[i].setImageResource(R.drawable.ic_launcher);
		}
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

	private Bitmap download_Image(String url) {
		try {
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
