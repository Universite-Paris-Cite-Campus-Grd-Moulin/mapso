package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import view.GameView;

public class GameController implements MouseListener {
    private static final int Direction = 0;
    private Game game;
    private Plateau board;
    private GameView gameView; // Ajouté pour illustrer la mise à jour de la vue
    private Pion selectedPiece = null;

    public GameController() {
        this.board = new Plateau();
        this.game = new Game(board);
    }

    public GameController(GameView gameView) {
        this.board = new Plateau();
        this.game = new Game(board);
        this.gameView = gameView; // Initialisez la vue dans le constructeur
    }

    public void startGame() {
        game.start();
        gameView.update(); // Mise à jour de l'affichage après le démarrage du jeu

    }

    public void handleUserAction(int startX, int startY, int endX, int endY) {
        boolean success = board.movePiece(startX, startY, endX, endY);
        if (success) {
            shootLaser();
            gameView.update(); // Mise à jour de la vue après chaque action réussie
        } else {
            System.out.println("Mouvement invalide");
            gameView.displayMessage("Mouvement invalide. Veuillez essayer à nouveau."); // Informer l'utilisateur via
                                                                                        // l'interface graphique
        }
    }

    public void rotatePiece(int x, int y, boolean clockwise) {
        Pion piece = board.getPieceAt(x, y);
        if (piece != null) {
            piece.rotate(clockwise);
            shootLaser();
            gameView.update(); // Mise à jour de la vue après rotation
        }
    }

    private void shootLaser() {
        // Assumons que la logique de tir du laser est implémentée dans Game ou Board.
        // Cette méthode simule le tir du laser à travers le plateau et gère les
        // interactions.
        game.shootLaser();
        // Vérifiez les conditions de victoire après chaque tir de laser.
        checkWinConditions();
    }

    private void checkWinConditions() {
        if (game.isGameOver()) {
            // Déterminez le joueur gagnant
            // Note : Cette implémentation suppose que vous avez une manière de déterminer
            // le joueur actuel et le joueur adverse.
            String winningPlayer = (game.getCurrentPlayer() == Couleur.ROUGE) ? "Jaune" : "Rouge";

            // Affichez le résultat du jeu
            // ui.displayGameOver(winningPlayer); Grapiquement
            System.out.println("Le jeu est terminé. Le joueur " + winningPlayer + " gagne !"); // Termmianl

            // Option pour recommencer ou quitter le jeu
            // int response = ui.promptForRestart();
            // if (response == RESTART) {
            // restartGame();
            // } else {
            // System.exit(0);
            // }
        }
    }

    public void restartGame() {
        // Réinitialisez le plateau et commencez un nouveau jeu
        this.board = new Plateau();
        this.game = new Game(board);
        startGame();
        // Assurez-vous également de réinitialiser l'affichage de l'interface
        // utilisateur ici.
    }

    /*
     * @Override
     * public void mouseClicked(MouseEvent e) {
     * int cellSize = gameView.getCellSize();
     * int x = e.getX() / cellSize;
     * int y = e.getY() / cellSize;
     * 
     * // Assurez-vous que le clic est dans les limites du plateau
     * if (x >= 0 && x < gameView.getBoardColumns() && y >= 0 && y <
     * gameView.getBoardRows()) {
     * // Si aucune pièce n'est actuellement sélectionnée
     * if (selectedPiece == null) {
     * Pion piece = board.getPieceAt(x, y);
     * // Si une pièce est présente à ces coordonnées
     * if (piece != null) {
     * // Vérifie si la pièce appartient au joueur actuel
     * if (piece.getCouleur() == game.getCurrentPlayer()) {
     * selectedPiece = piece;
     * System.out.println("Pièce sélectionnée en " + x + ", " + y);
     * } else {
     * // Informe l'utilisateur que la pièce sélectionnée n'appartient pas au joueur
     * // actuel
     * System.out.println("Cette pièce ne vous appartient pas.");
     * gameView.displayMessage(
     * "Cette pièce ne vous appartient pas. Veuillez sélectionner une de vos pièces."
     * );
     * }
     * }
     * } else {
     * // Tentative de déplacement de la pièce sélectionnée
     * if (board.movePiece(selectedPiece.getX(), selectedPiece.getY(), x, y)) {
     * System.out.println("Pièce déplacée à " + x + ", " + y);
     * selectedPiece = null; // Désélectionner après le mouvement
     * gameView.update(); // Mise à jour de l'affichage
     * } else {
     * System.out.println("Déplacement invalide.");
     * gameView.displayMessage("Déplacement invalide. Veuillez essayer à nouveau.");
     * }
     * }
     * }
     * }
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        int cellSize = gameView.getCellSize();
        int x = e.getX() / cellSize;
        int y = e.getY() / cellSize;

        if (x < 0 || x >= gameView.getBoardColumns() || y < 0 || y >= gameView.getBoardRows()) {
            return; // Clique en dehors du plateau
        }

        if (selectedPiece == null) {
            selectPiece(x, y);
        } else {
            moveSelectedPiece(x, y);
        }
    }

    private void selectPiece(int x, int y) {
        Pion piece = board.getPieceAt(x, y);
        if (piece != null && piece.getCouleur() == game.getCurrentPlayer()) {
            selectedPiece = piece;
            System.out.println("Pièce sélectionnée en " + x + ", " + y);
        } else {
            gameView.displayMessage("Sélection invalide.");
        }
    }

    private void moveSelectedPiece(int newX, int newY) {
        if (board.movePiece(selectedPiece.getX(), selectedPiece.getY(), newX, newY)) {
            gameView.update(); // Mettre à jour l'affichage
            selectedPiece = null; // Réinitialiser la pièce sélectionnée
        } else {
            gameView.displayMessage("Déplacement invalide.");
        }
    }

    private boolean isValidMove(Pion piece) {
        // Vérifie si la pièce appartient au joueur actuel
        return piece.getCouleur() == game.getCurrentPlayer();
    }

    // Les autres méthodes MouseListener sont nécessaires, mais vous pouvez les
    // laisser vides si elles ne sont pas utilisées
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
