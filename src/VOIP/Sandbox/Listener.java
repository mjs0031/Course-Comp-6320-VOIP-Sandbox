package Sandbox;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class Listener{
	public static void main(String[] args) throws LineUnavailableException{		
		TargetDataLine tLine = null;
		SourceDataLine sLine = null;
		
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
				16, 2, 4, 44100, false);		
		
		DataLine.Info tLineInfo = new DataLine.Info(TargetDataLine.class, format);
		DataLine.Info sLineInfo = new DataLine.Info(SourceDataLine.class, format);
		
		tLine = (TargetDataLine)AudioSystem.getLine(tLineInfo);
		sLine = (SourceDataLine)AudioSystem.getLine(sLineInfo);
		
		tLine.open(format);
		sLine.open(format);
		
		tLine.start();
		sLine.start();
		
		byte[] buffer = new byte[2048];
		
		int numBytes = 0;
		
		while(true){
			numBytes = tLine.read(buffer, 0, buffer.length);
			sLine.write(buffer, 0, numBytes);
		}
	}
}