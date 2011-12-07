package noname.common.homemonitor;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.hardware.Camera;

public class HomeMonitorActivity extends Activity {
	private SurfaceView preview = null;
	private SurfaceHolder previewHolder = null;
	private Camera camera = null;
	private boolean inPreview = false;
	SurfaceHolder.Callback surfaceCallback = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		preview = (SurfaceView) findViewById(R.id.surfaceView1);
		preview.onTouchEvent(null);
		surfaceCallback = new SurfaceHolder.Callback() {
			public void surfaceCreated(SurfaceHolder holder) {
				try {
					camera.setPreviewDisplay(previewHolder);
				} catch (Throwable t) {
					Log.e("PreviewDemo-surfaceCallback",
							"Exception in setPreviewDisplay()", t);
					Toast.makeText(HomeMonitorActivity.this, t.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				Camera.Parameters parameters = camera.getParameters();

				parameters.setPreviewSize(320, 240);
				camera.setParameters(parameters);
				camera.startPreview();
				inPreview = true;

			}

			public void surfaceDestroyed(SurfaceHolder holder) {
				// no-op
				camera.release();
			}
		};
		camera = Camera.open();
		previewHolder = preview.getHolder();
		previewHolder.addCallback(surfaceCallback);
		this.previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// try {
		// thec.setPreviewDisplay(holder);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// thec.startPreview();
	}
}