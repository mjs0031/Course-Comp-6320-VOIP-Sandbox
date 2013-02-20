//Package Declaration //
package VOIP;

//Java Package Support //
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.net.SocketException;
//import java.net.UnknownHostException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

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

public class SocketReceiver extends Thread{
	
	/**
	 * 
	 * 
	 * @param  args
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public static void main(String[] args) throws IOException, LineUnavailableException{
		//InetAddress address = InetAddress.getByName("131.204.20.63");
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
		
		// Continues until program is closed.		
		while(true){
			s.receive(dp);
			sLine.write(dp.getData(), 0, dp.getLength());
		} // end_while
	} // end_main()
} // end_class_declaration
