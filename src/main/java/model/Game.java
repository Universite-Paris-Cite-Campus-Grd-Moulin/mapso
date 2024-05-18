package model;

import model.enums.Couleur;

public class Game {
    private Plateau board;
    private Couleur currentPlayer;
    private boolean isGameOver;

    public Game(Plateau board) {
        this.board = board;
        this.currentPlayer = Couleur.JAUNE; // Le jeu commence toujours par le joueur Jaune
        System.out.println("Game initialized with board type: " + board.getClass().getName());
    }

    public Couleur getCurrentPlayer() {
        System.out.println("Current player: " + currentPlayer);
        return currentPlayer;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        System.out.println(
                "Attempting to move piece from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
        if (board.movePiece(startX, startY, endX, endY)) {
            switchPlayer(); // Change de joueur après un mouvement réussi
            System.out.println("Move successful");
            return true;
        }
        System.out.println("Move failed");
        return false;
    }

    public void rotatePiece(Pion pion) {
        System.out.println("Rotating piece at (" + pion.getX() + ", " + pion.getY() + ")");
        board.tournerPion(pion, pion.getDirection().nextClockwise()); // Rotation à droite par exemple
        switchPlayer(); // Change de joueur après une rotation
    }

    public void stackOrUnstackObelisk(Pion pion, int targetX, int targetY) {
        System.out.println("Stacking or unstacking obelisk at (" + pion.getX() + ", " + pion.getY() + ")");
        // Ici, ajoutez la logique pour empiler ou dépiler
        switchPlayer(); // Change de joueur après l'empilement/dépilement
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
        System.out.println("Switched player to: " + currentPlayer);
    }

    public boolean movePieceAndSwitchPlayer(int startX, int startY, int endX, int endY) {
        if (movePiece(startX, startY, endX, endY)) {
            switchPlayer();
            return true;
        }
        return false;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
        System.out.println("Toggled player to: " + currentPlayer);
    }

    public boolean isPlayerTurn(Couleur playerColor) {
        System.out.println("Checking if it's player " + playerColor + "'s turn");
        return currentPlayer == playerColor;
    }

    // Modification du constructeur pour accepter un type de plateau
    public Game(String type) {
        this(new Plateau(type)); // Crée un plateau du type spécifié
        System.out.println("Game initialized with board type: " + type);
    }

    public Plateau getBoard() {
        return board;
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
        System.out.println("Next turn. Current player: " + currentPlayer);
    }

    public boolean selectPiece(int x, int y) {
        Pion piece = board.getPieceAt(x, y);
        System.out.println("Selecting piece at (" + x + ", " + y + "): " + piece);
        if (piece != null && piece.getCouleur() == currentPlayer) {
            return true;
        }
        return false;
    }

    public Pion getPieceAt(int x, int y) {
        Pion piece = board.getPieceAt(x, y);
        System.out.println("Getting piece at (" + x + ", " + y + "): " + piece);
        return piece;
    }

    public void start() {
        isGameOver = false;
        currentPlayer = Couleur.JAUNE;
        nextTurn();
        System.out.println("Game started. Current player: " + currentPlayer);
    }

    public void shootLaser() {
        System.out.println("Shooting laser for player: " + currentPlayer);
        boolean hit = board.shootLaser(currentPlayer);
        if (hit) {
            isGameOver = true;
            System.out.println("Pharaoh hit! Game over.");
        }
    }

    private void checkWinConditions() {
        System.out.println("Checking win conditions for player: " + currentPlayer);
        boolean pharaohHit = board.shootLaser(currentPlayer);
        if (pharaohHit) {
            isGameOver = true;
            System.out.println("Pharaoh hit! Game over.");
        }
    }

    public boolean isGameOver() {
        System.out.println("Is game over? " + isGameOver);
        return isGameOver;
    }
}
