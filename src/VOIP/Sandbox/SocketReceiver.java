package VOIP.Sandbox;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketReceiver{
	public static void main(String[] args) throws IOException{
		InetAddress address = InetAddress.getByName("192.168.2.2");
		byte[] buf = new byte[1000];
		DatagramSocket s = new DatagramSocket(12345);
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		s.receive(dp);
		String rcvd = new String(dp.getData(), 0, dp.getLength());
		System.out.println(rcvd);
	}
}