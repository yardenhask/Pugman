package myPacman;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {

	
	
	public void play (String path)
	{
		try {
			  // open the sound file as a Java input stream
		    String gongFile = path;
		    InputStream in = new FileInputStream(gongFile);
		 
		    // create an audiostream from the inputstream
		    AudioStream audioStream = new AudioStream(in);
		 
		    // play the audio clip with the audioplayer class
		    AudioPlayer.player.start(audioStream);
		    
		   
		    
		    
		}
		
		catch (Exception e)
		{
			
		}

	}
}
