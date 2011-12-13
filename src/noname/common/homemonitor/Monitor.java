package noname.common.homemonitor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.app.ProgressDialog;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Handler;
import android.widget.FrameLayout;

public class Monitor extends Handler {
	private static Socket socket = null;
	private static String server_address = "106.187.47.25";
	private static int server_port = 17840;
	public static boolean begin(){
		HomeMonitorActivity.mCamera.takePicture(null, null, mPicture);
		return true;
	}
	
	public static boolean end(){
		return true;
	}
	private static PictureCallback mPicture = new PictureCallback(){

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			try{
				socket = new Socket(server_address, server_port);
			}catch (Exception e) {
				// TODO: handle exception
//				Toast.makeText(HomeMonitorActivity.this, R.string.camera_open_error, Toast.LENGTH_LONG).show();
				camera.startPreview();
				return;
			}
			try {
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.write(data);
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			camera.startPreview();
		}
		
	};
	public static boolean upload(){
		return true;
	}
}
