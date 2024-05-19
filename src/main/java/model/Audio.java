package model;

import java.io.IOException;
import javax.sound.sampled.*;
import java.io.File;

public class Audio {
    private static Clip clip;

    public static void playSound(String soundFile) {
        try {
            File audioFile = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Pour jouer en boucle
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopSound() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
