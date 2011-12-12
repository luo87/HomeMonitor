package noname.common.homemonitor;

import android.os.Handler;

public class Monitor extends Handler {
	public boolean begin(){
//		HomeMonitorActivity.mCamera.takePicture(null, null, mPicture);
		return true;
	}
	
	public boolean end(){
		return true;
	}
	
	public boolean upload(){
		return true;
	}
}
