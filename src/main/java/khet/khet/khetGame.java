package khet.khet;

import javax.swing.JFrame;
import khet.khet.KhetBoard;

public class khetGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Khet Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new KhetBoard());
        frame.pack(); 
        frame.setVisible(true);
    }
}
