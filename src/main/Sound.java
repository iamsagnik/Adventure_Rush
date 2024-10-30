package src.main;

import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;


public class Sound {
  
  Clip clip;
  URL soundURL[] = new URL[30];

  public Sound(){

    soundURL[0] = getClass().getResource("/res/sound/worldSong.wav");
  }

  public void setFile( int i){

    try {
      
      AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
      clip = AudioSystem.getClip();
      clip.open(ais);

    } catch (Exception e) {
    }

  }

  public void play(){

    clip.start();

  }

  @SuppressWarnings("static-access")
  public void loop(){

    clip.loop(clip.LOOP_CONTINUOUSLY);

  }

  public void stop(){

    clip.stop();
  }

}
