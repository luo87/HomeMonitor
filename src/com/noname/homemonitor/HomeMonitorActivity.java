package com.noname.homemonitor;


import java.util.TimerTask;

import com.noname.homemonitor.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
//		try{
//			mCamera = Camera.open();
//		}catch(Exception e){
//			Toast.makeText(this, R.string.camera_open_error, Toast.LENGTH_LONG).show();
//			finish();
//		}
		mMonitor = new Monitor();
		

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
					setPic(mCamera);
//					mCamera.takePicture(null, null, mPicture);
					mMonitor.begin(task);
					item.setTitle(R.string.end_monitor);
				}else {
					mMonitor.cancel();
					item.setTitle(R.string.begin_monitor);
				}
				break;
			case SETTING_MENU:
//	             startActivity(new Intent(Intent.ACTION_PICK));
//				mCamera.stopPreview();
//				mCamera.release();
//				Intent t = new Intent();
				Intent launchPreferencesIntent = new Intent().setClass(this, Setting.class);
				try{
					startActivityForResult(launchPreferencesIntent, 1);
				}catch (Exception e){
					Log.e("settingBtn_error", e.getMessage());
					return false;
				}

//				startActivity(launchPreferencesIntent);
				break;
			default:
				break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		try{
			mCamera = Camera.open();
		}catch(Exception e){
			Toast.makeText(this, R.string.camera_open_error, Toast.LENGTH_LONG).show();
			finish();
		}
		mPreview = new CameraView(this, mCamera);
		setContentView(mPreview);
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		try{
		mMonitor.cancel();
//		mCamera.stopPreview();
		mCamera.release();
		}catch (Exception e){
			Log.e("release camera error", e.getMessage());
		}
		super.onStop();
	}

	private PictureCallback mPicture = new PictureCallback(){

		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			try {
				mMonitor.upload(data);

			} catch (Exception e) {
				Toast.makeText(HomeMonitorActivity.this, R.string.connect_error, Toast.LENGTH_LONG).show();
				mMonitor.cancel();
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
	public TimerTask task = new TimerTask(){
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mCamera.takePicture(null, null, mPicture);
		}
		
	};
}