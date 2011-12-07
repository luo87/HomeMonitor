package noname.common.homemonitor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.FrameLayout;

import android.hardware.Camera;

public class HomeMonitorActivity extends Activity {
	private Camera mCamera;
	private CameraView mPreview;

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
		menu.add(Menu.NONE, 1, Menu.NONE, R.string.begin_monitor);
		menu.add(Menu.NONE, 2, Menu.NONE, R.string.setting);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
			case 1:
				break;
			case 2:
				break;
			default:
				break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}