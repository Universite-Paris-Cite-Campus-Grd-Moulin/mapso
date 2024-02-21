package khet.model;

// Représente l'état et la logique du jeu
public class Game {
    private Board board;

    public Game() {
        this.board = new Board();
        // Initialisation supplémentaire si nécessaire
    }

    // Retourne le plateau de jeu
    public Board getBoard() {
        return board;
    }

    // Commence ou redémarre le jeu
    public void startNewGame() {
        // Implémentez la logique pour démarrer un nouveau jeu
    }
}
