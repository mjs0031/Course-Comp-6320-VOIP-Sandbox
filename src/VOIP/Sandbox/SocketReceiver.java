package Sandbox;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SocketReceiver extends Thread{
	public static void main(String[] args) throws IOException, LineUnavailableException{
		InetAddress address = InetAddress.getByName("192.168.1.107");
		byte[] buf = new byte[2048];
		DatagramSocket s = new DatagramSocket(12345);
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		
		SourceDataLine sLine = null;
		
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
				16, 2, 4, 44100, false);		
		
		DataLine.Info sLineInfo = new DataLine.Info(SourceDataLine.class, format);
		
		sLine = (SourceDataLine)AudioSystem.getLine(sLineInfo);
		
		sLine.open(format);
		
		sLine.start();
		
		while(true){
			s.receive(dp);
			sLine.write(dp.getData(), 0, dp.getLength());
		}
	}
}