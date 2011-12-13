package noname.common.homemonitor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.os.Handler;

public class Monitor extends Handler {
	private static Socket socket = null;
	private static String server_address = "imnoname.com";
	private static int server_port = 17840;
	public boolean begin(){
		return true;
	}
	
	public boolean end(){
		return true;
	}

	public static boolean upload(byte[] data){
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
		return true;
	}

	public boolean connect() {
		// TODO Auto-generated method stub
		try{
			socket = new Socket(server_address, server_port);
		}catch (Exception e) {
			// TODO: handle exception
//			Toast.makeText(HomeMonitorActivity.this, R.string.camera_open_error, Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
}
