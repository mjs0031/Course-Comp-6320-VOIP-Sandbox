package VOIP.Sandbox;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketSender{
	public static void main(String[] args) throws IOException{
		InetAddress address = InetAddress.getByName("192.168.2.2");
		String theString = "Greetings from Saturn";
		byte[] b = theString.getBytes();
		DatagramSocket s = new DatagramSocket();
		DatagramPacket dp = new DatagramPacket(b, b.length, address, 12345);
		s.send(dp);
	}
}