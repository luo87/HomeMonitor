package com.noname.homemonitor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;


//import android.os.Handler;

public class Monitor {
	private  Socket socket = null;
	private  String server_address = "imnoname.com";
	private  int server_port = 17840;
	private Timer t = new Timer();
	public boolean begin(TimerTask task){
//		if (!socket.isConnected()){
//			connect();
//		}

		t.schedule(task, 0, 5000);
		return true;
	}
	
	public boolean cancel(){
		t.cancel();
		return true;
	}

	public boolean upload(byte[] data) throws IOException{
//		if (socket == null){
		if (!connect()){
			throw new IOException();
		}
//		}
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.write(data);
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
