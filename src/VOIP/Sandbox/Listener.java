package Sandbox;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class Listener{
	public static void main(String[] args) throws LineUnavailableException, IOException{		
		TargetDataLine line = null;
		
		AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
		
		DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);
		
		line = (TargetDataLine)AudioSystem.getLine(dataLineInfo);
		
		line.open(format);
		line.start();
		
		AudioFileFormat.Type fileType = AudioFileFormat.Type.AIFF;
		File audioFile = new File("C:/Users/Ian/junk.aif");
		
		AudioSystem.write(new AudioInputStream(line), fileType, audioFile);
	}
}