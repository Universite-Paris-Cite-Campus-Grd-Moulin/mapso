package view.components;

public interface GameNavigationListener {
    void onBackToMenuRequested();

    void updateGameOver(model.enums.Couleur couleur);
}