package Sandbox;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Listener{
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AudioFileFormat.Type type = AudioFileFormat.Type.AIFF;
		
		TargetDataLine tLine = null;
		SourceDataLine sLine = null;
		
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100,
				16, 2, 4, 44100, false);		
		DataLine.Info tLineInfo = new DataLine.Info(TargetDataLine.class, format);
		DataLine.Info sLineInfo = new DataLine.Info(SourceDataLine.class, format);
		
		tLine = (TargetDataLine)AudioSystem.getLine(tLineInfo);
		sLine = (SourceDataLine)AudioSystem.getLine(sLineInfo);
		
		AudioInputStream stream = new AudioInputStream(tLine);
		
		tLine.open(format);
		sLine.open(format);
		
		sLine.start();
		
		while(true){
			tLine.start();
			AudioSystem.write(stream, type, out);
			Thread.sleep(1000);
			tLine.stop();
			tLine.flush();
			sLine.write(out.toByteArray(), 0, out.toByteArray().length);
		}
	}
}