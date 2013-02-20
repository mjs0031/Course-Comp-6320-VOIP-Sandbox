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
	
	// Audio Variables
	AudioFormat format;
	
	// Transmit Variables
	InetAddress address;
	DatagramPacket dp;
	DatagramSocket s;
	SourceDataLine sLine;
	
	// Control Variables
	boolean is_true = true;
	byte[] buf;
	
	/**
	 * 
	 * 
	 * @param  args
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public SocketReceiver() throws IOException, LineUnavailableException{
		this.buf    = new byte[2048];
		this.s      = new DatagramSocket(10150);
		this.dp     = new DatagramPacket(buf, buf.length);
		this.format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
												16, 2, 4, 44100, false);		
		DataLine.Info sLineInfo = new DataLine.Info(SourceDataLine.class, this.format);
		this.sLine = (SourceDataLine)AudioSystem.getLine(sLineInfo);	
		
	} // end SocketReceiver()
	
	
	/**
	 * 
	 * @param  the_line
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	@Override
	public void run(){
		try{
			this.sLine.open(this.format);
		}
		catch (LineUnavailableException e){
			// empty sub-block	
		}
		this.sLine.start();
		
		// Continues until program is closed.		
		while(this.is_true){
			try{
				this.s.receive(this.dp);
			}
			catch (IOException e){
				// empty sub-block		
			}
			this.sLine.write(this.dp.getData(), 0, this.dp.getLength());
		} // end while
		
	} // end SocketReceiver.run()
	
	
	/**
	 * 
	 */
	public void interrupt_thread(){
		this.sLine.stop();
		this.sLine.close();
	} // end SocketReceiver.interrupt_thread()

	
} // end SocketReceiver class
