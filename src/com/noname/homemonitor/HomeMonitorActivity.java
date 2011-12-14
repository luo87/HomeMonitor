package com.noname.homemonitor;


import com.noname.homemonitor.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;


import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;

public class HomeMonitorActivity extends Activity {
	public static Camera mCamera = null;
	private CameraView mPreview;
	private Monitor mMonitor;
	private boolean Monitoring = false;
	
	public  final int MONITOR_MENU = 1;
	public  final int SETTING_MENU = 2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try{
			mCamera = Camera.open();
		}catch(Exception e){
			Toast.makeText(this, R.string.camera_open_error, Toast.LENGTH_LONG).show();
			finish();
		}
		mMonitor = new Monitor();
		mPreview = new CameraView(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.layout1);

		preview.addView(mPreview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(Menu.NONE, MONITOR_MENU, Menu.NONE, R.string.begin_monitor);
		menu.add(Menu.NONE, SETTING_MENU, Menu.NONE, R.string.setting);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
			case MONITOR_MENU:
	             // When the user center presses, let them pick a contact.
				Monitoring = !Monitoring;
				if (Monitoring){
					new Thread(new Runnable() {
						public void run() {
							
							setPic(mCamera);
							mCamera.takePicture(null, null, mPicture);
//				ProgressDialog dialog = ProgressDialog.show(this, "", 
//                        "Loading. Please wait...", true);
							mMonitor.begin(mCamera, mPicture);
						}
					}).start();
					item.setTitle(R.string.end_monitor);
				}else {
					mMonitor.cancel();
					item.setTitle(R.string.begin_monitor);
				}

				break;
			case SETTING_MENU:
//	             startActivity(new Intent(Intent.ACTION_PICK));
				Intent t = new Intent();
				t.setClass(this, SettingActivity.class);
				startActivity(t);
				break;
			default:
				break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mCamera.release();
		super.onPause();
	}

	private PictureCallback mPicture = new PictureCallback(){

		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			try {
				mMonitor.upload(data);

			} catch (Exception e) {
				e.printStackTrace();
			}
			camera.startPreview();
		}
		
	};
	private Camera setPic(Camera m){
		Camera.Parameters mPar = m.getParameters();
		mPar.setPictureSize(320, 240);
//		mPar.setPictureFormat(PixelFormat.RGB_332);
		m.setParameters(mPar);
		return m;
	}
}