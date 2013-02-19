package Sandbox;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import sun.audio.AudioPlayer;

public class Listener{
	public static void main(String[] args) throws LineUnavailableException, IOException{
		byte[] buffer = new byte[1000];
		
		int numBytesRead = 0;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		
		TargetDataLine line = null;
		
		AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
		
		DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);
		
		line = (TargetDataLine)AudioSystem.getLine(dataLineInfo);
		
		byte[] data = new byte[line.getBufferSize() / 5];
		
		line.open(format);
		line.start();
		
		AudioInputStream as = new AudioInputStream(in, format, data.length);
		
		while(true){
			numBytesRead = line.read(data, 0, data.length);
			out.write(data, 0, numBytesRead);
			as.read(out.toByteArray(), 0, out.toByteArray().length);
			AudioPlayer.player.start(as);
		}
	}
}