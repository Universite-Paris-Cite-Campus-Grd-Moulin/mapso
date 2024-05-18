package model;
import java.io.IOException;
import javax.sound.sampled.*;
import java.io.File;

public class Audio{
    public static void playSound(String soundFile){
        try{
            File audioFile = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }
}
