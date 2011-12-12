package noname.common.homemonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import android.hardware.Camera;

public class HomeMonitorActivity extends Activity {
	public static Camera mCamera;
	private CameraView mPreview;
	public static final int MONITOR_MENU = 1;
	public static final int SETTING_MENU = 2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mCamera = Camera.open();

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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mCamera.release();
		super.onDestroy();
	}
}