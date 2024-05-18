package model;

import model.enums.Couleur;

public class Game {
    private model.Plateau board;
    private Couleur currentPlayer;

    // classe joueur , elle contient le booleen que il est vivanmt ou non
    // et cette attribut private Couleur currentPlayer;

    // list<joueurs> , tu crres 2 joueurs dans le construct et puis tu fais list.add(j1) et joueurs (j2)
    //si un joueur est plus vivcant pas tu le remove de la list , jeu game over et
    private boolean isGameOver;

<<<<<<< Updated upstream
    // Modification du constructeur pour accepter un type de plateau
    public Game(String type) {
        this(new Plateau(type)); // Crée un plateau du type spécifié
    }

    public Game(Plateau board) {
        this.board = board;
        this.currentPlayer = Couleur.JAUNE; // Commence le jeu avec le joueur Jaune
        this.isGameOver = false;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (isGameOver) return false; // Empêche les mouvements si le jeu est terminé
        if (currentPlayer == getPieceAt(startX, startY).getCouleur()) {
            if (board.movePiece(startX, startY, endX, endY)) {
                togglePlayer(); // Change de joueur après un mouvement valide
                return true;
            }
        }
        return false;
    }

    public boolean rotatePiece(int x, int y, boolean clockwise) {
        if (isGameOver) return false; // Empêche la rotation si le jeu est terminé
        if (currentPlayer == getPieceAt(x, y).getCouleur()) {
            board.rotatePiece(x, y, clockwise);
            togglePlayer(); // Change de joueur après une rotation valide
=======
    // Nouveau booléen pour suivre l'action du joueur
    private boolean actionEffectuee;

    public Game(model.Plateau board) {
        this.board = board;
        this.currentPlayer = Couleur.JAUNE; // Le jeu commence toujours par le joueur Jaune
        this.actionEffectuee = false;
    }

    // Modification du constructeur pour accepter un type de plateau
    public Game(String type) {
        this(new Plateau(type)); // Crée un plateau du type spécifié
        this.actionEffectuee = false;
    }

    public Couleur getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (!actionEffectuee && board.movePiece(startX, startY, endX, endY)) {
            actionEffectuee = true;
            switchPlayer(); // Change de joueur après un mouvement réussi
            return true;
        }
        return false;
    }

    public void rotatePiece(Pion pion) {
        if (!actionEffectuee) {
            board.tournerPion(pion, pion.getDirection().nextClockwise()); // Rotation à droite par exemple
            actionEffectuee = true;
            switchPlayer(); // Change de joueur après une rotation
        }
    }

    public void stackOrUnstackObelisk(Pion pion, int targetX, int targetY) {
        if (!actionEffectuee) {
            // Ici, ajoutez la logique pour empiler ou dépiler
            actionEffectuee = true;
            switchPlayer(); // Change de joueur après l'empilement/dépilement
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
        actionEffectuee = false;
    }

    public boolean isPlayerTurn(Couleur playerColor) {
        return currentPlayer == playerColor;
    }

    public Plateau getBoard() {
        return board;
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
        actionEffectuee = false;
    }

    public boolean selectPiece(int x, int y) {
        Pion piece = board.getPieceAt(x, y);
        if (piece != null && piece.getCouleur() == currentPlayer) {
            // Logique pour gérer la sélection, peut-être stocker la pièce sélectionnée ici
>>>>>>> Stashed changes
            return true;
        }
        return false;
    }

    public Pion getPieceAt(int x, int y) {
        return board.getPieceAt(x, y);
    }

    public Plateau getPlateau() {
        return board;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
    }

    public Couleur getCurrentPlayer() {
        return currentPlayer;
    }

    public Plateau getBoard() {
        return board;
    }

    public void start() {
        isGameOver = false;
<<<<<<< Updated upstream
        currentPlayer = Couleur.JAUNE; // Commence toujours par le joueur Jaune
        // Initialisation ou réinitialisation du plateau ici si nécessaire
=======
        currentPlayer = Couleur.JAUNE;
        actionEffectuee = false;
        nextTurn();
>>>>>>> Stashed changes
    }

    public void shootLaser() {
        if (board.shootLaser(currentPlayer)) {
            isGameOver = true; // Met fin au jeu si le pharaon est touché
        }
    }

    private void checkWinConditions() {
<<<<<<< Updated upstream
        if (board.checkForPharaohHit(currentPlayer)) {
            isGameOver = true; // Vérifie si un pharaon a été touché
=======
        // TODO : plus de Condotions
        boolean pharaohHit = board.shootLaser(currentPlayer);
        if (pharaohHit) {
            isGameOver = true;
>>>>>>> Stashed changes
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}

