//Package Declaration //
package VOIP;

//Java Package Support //
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

//Internal Package Support //
// { Not Applicable }

/**
* 
* VOIP/SocketSender.java
* 
* @author(s)	: Ian Middleton, Zach Ogle, Matthew J Swann
* @version  	: 1.0
* Last Update	: 2013-02-20
* Update By		: Matthew J Swann
* 
* 
* VOIP PACKAGE :: Source code for Comp 6360: Wireless & Mobile Networks
* 	               Assignment 1 :: VOIP
* 
*/

public class SocketSender extends Thread{

	/**
	 * 
	 * 
	 * @param  args
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public static void main(String[] args) throws IOException, LineUnavailableException{
		InetAddress address = InetAddress.getByName("172.17.30.135");
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
		
		// Continues until program is closed.
		while(true){
			numBytes = tLine.read(buffer, 0, buffer.length);
			dp = new DatagramPacket(buffer, buffer.length, address, 12345);
			s.send(dp);
		} // end_while
	} // end_main()
} // end_class_declaration
