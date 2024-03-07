package khet.khet;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class KhetBoard extends JPanel {
    private KhetToile[][] tiles = new KhetToile[10][8];
    private Piece[][] pieces = new Piece[10][8]; 

    public KhetBoard() {
        setLayout(new GridLayout(10, 8));
        setBackground(Color.GRAY);
        initTiles();
        placeInitialPieces(); 
    }

    private void initTiles() {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                Pos position = new Pos(col, row);
                tiles[row][col] = new KhetToile(position);
                add(tiles[row][col]);
            }
        }
    }

    private void placeInitialPieces() {
        tiles[0][0].setIcon(new ImageIcon(getClass().getResource("/ressources/SphinxRouge.png")));
        // Continuez à placer d'autres pièces ici selon la configuration initiale du jeu Khet
    }
}
