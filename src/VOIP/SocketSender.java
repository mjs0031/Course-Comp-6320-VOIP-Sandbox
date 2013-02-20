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

	// Audio Variables
	AudioFormat format;
	
	// Transmit Variables
	InetAddress address;
	DatagramPacket dp;
	DatagramSocket s;
	TargetDataLine tLine;
	
	// Control Variables
	boolean is_true = true;
	byte[] buffer;
	int numBytes;
	
	/**
	 * 
	 * 
	 * @param  args
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public SocketSender(String ip_address) throws IOException, LineUnavailableException{
		this.address = InetAddress.getByName(ip_address);
		this.s       = new DatagramSocket();
		this.format  = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
												16, 2, 4, 44100, false);
		DataLine.Info tLineInfo = new DataLine.Info(TargetDataLine.class, format);
		this.tLine   = (TargetDataLine)AudioSystem.getLine(tLineInfo);
		this.tLine.open(this.format);
		this.tLine.start();
		buffer = new byte[2048];
		
	} // end SocketSender()
		
		
		/**
		 * 
		 * @param  the_line
		 * @throws IOException
		 * @throws LineUnavailableException
		 */
		@Override
		public void run(){	

			// Continues until program is closed.
			while(this.is_true){
				numBytes = this.tLine.read(buffer, 0, buffer.length);
				this.dp = new DatagramPacket(buffer, buffer.length, address, 10150);
				try{
					this.s.send(this.dp);
				}
				catch (IOException e){
					// empty sub-block
				}
			}// end while
			
		} // end SocketSender.run()
		
		
		/**
		 * 
		 */
		public void interrupt_thread(){
			this.tLine.stop();
			this.tLine.close();
		} // end SocketSender.interrupt_thread()

		
} // end SocketSender class
