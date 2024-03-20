package view;

import javax.swing.*;

import view.components.BoardPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class GameView extends JFrame {

    private BoardPanel boardPanel;
    private BufferedImage spriteSheet;
    private final int SPRITE_WIDTH = 100; // Ajustez en fonction de la largeur de vos sprites
    private final int SPRITE_HEIGHT = 100; // Ajustez en fonction de la hauteur de vos sprites

    public GameView() {
        setTitle("Khet Game");
        setSize(800, 600); // Ajustez selon vos besoins
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadSpriteSheet();
        initUI();
    }

    private void loadSpriteSheet() {
        try {
            // Chargez ici votre feuille de sprites
            spriteSheet = ImageIO.read(new File("path/to/your/sprites_khet.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez correctement les exceptions, par exemple en affichant un message d'erreur ou en quittant l'application
        }
    }

    private BufferedImage getSprite(int x, int y) {
        if (spriteSheet == null) {
            throw new IllegalStateException("Sprite sheet not loaded.");
        }
        // Calculez la position x,y du sprite dans la feuille de sprites
        return spriteSheet.getSubimage(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
    }

    private void initUI() {
        boardPanel = new BoardPanel();
        add(boardPanel);
    }

    public void displayBoard(Object[][] boardData) {
        boardPanel.removeAll(); // Retirez tous les composants avant de les ajouter à nouveau

        // Supposons que boardData est une matrice 2D représentant les pièces sur le plateau
        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                // Convertissez boardData[i][j] en coordonnées de sprite x,y et ajoutez au BoardPanel
                // Par exemple, si boardData[i][j] est une instance de votre enum TypeDePion
                // vous pouvez déterminer les coordonnées de sprite basées sur le type de pion
                BufferedImage sprite = getSprite(0,0); // Remplacez x et y par les coordonnées appropriées
                JLabel label = new JLabel(new ImageIcon(sprite));
                boardPanel.add(label); // Vous devrez peut-être personnaliser le layout de votre BoardPanel
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public void update() {
        // Vous pouvez ajouter une logique pour mettre à jour l'affichage si nécessaire
        // Par exemple, vous pourriez vouloir rafraîchir le BoardPanel pour afficher les mouvements du jeu
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView view = new GameView();
            view.setVisible(true);
            
            // Créez ici votre matrice de données du plateau, exemple :
            Object[][] dummyBoardData = new Object[8][10];
            // ... initialisez dummyBoardData avec les données de votre jeu ...
    
            // Puis, appelez displayBoard sans essayer d'affecter le résultat à une variable.
            view.displayBoard(dummyBoardData);
        });
    }
    
}
