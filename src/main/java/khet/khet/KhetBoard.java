package khet.khet;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

class KhetBoard extends JPanel { //A quoi va ressembler le plateau
    private KhetToile[][] tiles = new KhetToile[10][8];
        public KhetBoard() {
        setLayout(new GridLayout(8, 10));     // Définit le gestionnaire de disposition pour GridLayout.
        setBackground(Color.GRAY);
        initTiles();                                    // Initialise les cases du plateau
    }
    
    // Méthode pour initialiser les cases du plateau.
    private void initTiles() {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                Pos position = new Pos(col, row);                   // Crée une nouvelle position pour la case.
                tiles[row][col] = new KhetToile(position);          // Crée une nouvelle case avec la position et l'ajoute au tableau et au JPanel.
                add(tiles[row][col]);                               // Ajoute la case au plateau de jeu.
            }
        }
    }
}
