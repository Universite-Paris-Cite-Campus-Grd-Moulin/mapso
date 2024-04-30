package view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import controller.GameController;
import model.Game;
import view.components.BoardPanel;

public class GameView extends JFrame {
    private BoardPanel boardPanel;
    private BufferedImage spriteSheet;
    private final int SPRITE_WIDTH = 100; // Ajustez en fonction de la largeur de vos sprites
    private final int SPRITE_HEIGHT = 100; // Ajustez en fonction de la hauteur de vos sprites
    private final int BOARD_COLUMNS = 10; // Nombre de colonnes du plateau
    private final int BOARD_ROWS = 8; // Nombre de lignes du plateau

    public GameView() {
        setTitle("Khet Game");
        setSize(800, 600); // Ajustez selon vos besoins
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadSpriteSheet();
        initUI();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                update(); // Mettre à jour l'affichage chaque fois que la fenêtre est redimensionnée
            }
        });
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(new File("path/to/your/sprites_khet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getSprite(int x, int y) {
        if (spriteSheet == null) {
            throw new IllegalStateException("Sprite sheet not loaded.");
        }
        return spriteSheet.getSubimage(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
    }

    private void initUI() {
        boardPanel = new BoardPanel();
        add(boardPanel);
    }

     public void setGame(Game game) {
        boardPanel.setGame(game);  // Méthode à ajouter dans BoardPanel pour relier le jeu au panneau
        update();  // Mise à jour immédiate de l'affichage
    }

    public void setController(GameController controller) {
        boardPanel.setController(controller); // Set controller in BoardPanel
    }

    public void displayBoard(Object[][] boardData) {
        boardPanel.removeAll();
        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                BufferedImage sprite = getSprite(0, 0);
                JLabel label = new JLabel(new ImageIcon(sprite));
                boardPanel.add(label);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public void update() {
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void createAndShowGUI() {
        setVisible(true);
    }

    // Cette méthode calcule et renvoie la taille d'une cellule du plateau
    public int getCellSize() {
        // Calcul basé sur la taille actuelle de GameView et le nombre de cellules
        int cellWidth = this.getWidth() / BOARD_COLUMNS;
        int cellHeight = this.getHeight() / BOARD_ROWS;
        // Retourne la plus petite dimension pour s'assurer que les cellules restent
        // carrées
        return Math.min(cellWidth, cellHeight);
    }

    public int getBoardColumns() {
        return BOARD_COLUMNS;
    }

    public int getBoardRows() {
        return BOARD_ROWS;
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
