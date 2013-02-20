package Sandbox;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class SocketSender{
	public static void main(String[] args) throws IOException, LineUnavailableException{
		InetAddress address = InetAddress.getByName("192.168.2.2");
		DatagramSocket s = new DatagramSocket();
		
		TargetDataLine tLine = null;
		
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
				16, 2, 4, 44100, false);		
		
		DataLine.Info tLineInfo = new DataLine.Info(TargetDataLine.class, format);
		
		tLine = (TargetDataLine)AudioSystem.getLine(tLineInfo);
		
		tLine.open(format);
		
		tLine.start();
		
		byte[] buffer = new byte[2048];
		
		int numBytes = 0;
		
		DatagramPacket dp;
		
		while(true){
			numBytes = tLine.read(buffer, 0, buffer.length);
			dp = new DatagramPacket(buffer, buffer.length, address, 12345);
			s.send(dp);
		}
	}
}