package main.java.model;

<<<<<<< HEAD
import main.java.model.enums.Couleur;
=======
import java.util.Random;

import model.enums.Couleur;
>>>>>>> 002fb299eb0ee9a54c3bb226b229718f74589f7a

public class Game {
    private Plateau board;
    private Couleur currentPlayer;

    // classe joueur , elle contient le booleen que il est vivanmt ou non
    // et cette attribut private Couleur currentPlayer;

    // list<joueurs> , tu crres 2 joueurs dans le construct et puis tu fais list.add(j1) et joueurs (j2)
    //si un joueur est plus vivcant pas tu le remove de la list , jeu game over et
    private boolean isGameOver;

    public Game(Plateau board) {
        this.board = board;
        this.currentPlayer = Couleur.JAUNE; // Le jeu commence toujours par le joueur Jaune
    }

    public Couleur getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (board.movePiece(startX, startY, endX, endY)) {
            switchPlayer(); // Change de joueur après un mouvement réussi
            return true;
        }
        return false;

    }

    public void botMove() {
        Random random = new Random();
        int startX, startY, endX, endY;
    
        while (true) {
            startX = random.nextInt(10); // Génère une coordonnée aléatoire entre 0 et 9
            startY = random.nextInt(10);
            Pion piece = board.getPieceAt(startX, startY);
            
            if (piece != null && piece.getCouleur() == Couleur.ROUGE) {
                // Trouve une position de destination valide
                do {
                    endX = random.nextInt(10); 
                    endY = random.nextInt(10);
                } while (!board.isMoveValid(startX, startY, endX, endY));
    
                board.movePiece(startX, startY, endX, endY);
                break;
            }
        }
        switchPlayer() ; // Change de joueur après un mouvement réussi
    }

    public void rotatePiece(Pion pion) {
        board.tournerPion(pion, pion.getDirection().nextClockwise()); // Rotation à droite par exemple
        switchPlayer(); // Change de joueur après une rotation
    }

    public void stackOrUnstackObelisk(Pion pion, int targetX, int targetY) {
        // Ici, ajoutez la logique pour empiler ou dépiler
        switchPlayer(); // Change de joueur après l'empilement/dépilement
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
    }

    public boolean isPlayerTurn(Couleur playerColor) {
        return currentPlayer == playerColor;
    }

    // Modification du constructeur pour accepter un type de plateau
    public Game(String type) {
        this(new Plateau(type)); // Crée un plateau du type spécifié
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
    }

    public Plateau getBoard() {
        return board;
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
        if (currentPlayer == Couleur.ROUGE) {
            // Appeler la méthode pour le déplacement du bot
            botMove();
        }
    }

    public boolean selectPiece(int x, int y) {
        Pion piece = board.getPieceAt(x, y);
        if (piece != null && piece.getCouleur() == currentPlayer) {
            // Logique pour gérer la sélection, peut-être stocker la pièce sélectionnée ici
            return true;
        }
        return false;
    }

    public Pion getPieceAt(int x, int y) {
        return board.getPieceAt(x, y);
    }

    public void start() {
        isGameOver = false;
        currentPlayer = Couleur.JAUNE;
        nextTurn();
    }

    public void shootLaser() {
        boolean hit = board.shootLaser(currentPlayer);
        if (hit) {
            isGameOver = true;
        }
    }

    private void checkWinConditions() {
        // TODO : plus de Condotions
        boolean pharaohHit = board.shootLaser(currentPlayer);
        if (pharaohHit) {
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

}
