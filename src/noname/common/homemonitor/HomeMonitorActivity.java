package noname.common.homemonitor;

import android.app.Activity;
import android.os.Bundle;
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
}