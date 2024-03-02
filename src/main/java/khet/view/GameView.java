package khet.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import khet.view.components.BoardPanel;

public class GameView extends JFrame {

    private BoardPanel boardPanel;

    public GameView() {
        setTitle("Khet Game");
        setSize(800, 600); // Ajustez selon vos besoins
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        boardPanel = new BoardPanel();
        add(boardPanel);
    }

    public void displayBoard() {
        // Afficher le plateau de jeu
    }

    public void update() {
        // Mettre à jour l'affichage
    }
}
