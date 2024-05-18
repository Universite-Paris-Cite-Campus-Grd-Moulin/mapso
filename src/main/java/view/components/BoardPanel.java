package view.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameController;
import model.Game;
import model.NoeudTrajectoire;
import model.Observer;
import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;
import view.GameView;
import java.util.List;


public class BoardPanel extends JPanel implements MouseListener, Observer {
    private Plateau board;
    private int startX, startY;
    private GameController controller;
    private Game game; // GameLecture game ; interface que avec des getters ; tas besoin getBoard , getPion[i,j]
    // getLaserpos ...
    private GameView gameView;
    private Pion selectedPiece = null;
    private JFrame parentFrame;
    private view.components.GameNavigationListener navigationListener;
    private List<NoeudTrajectoire> cheminLaserRouge;
    private List<NoeudTrajectoire> cheminLaserJaune;

    public BoardPanel(String type , JFrame frame , GameView view){
        this.board = new Plateau(type);
        Game jeu = new Game(board);
        this.game = jeu ;
        //todo : le mouse listener est le controller pas le BOARDPANEL
        //TODO : faire bouger tous les mouselistener vers le controller
        //GameController controller = new GameController(jeu,view);
        //view.addMouseListener(controller);
        view.addMouseListener(this);
        board.addObserver(this); // jeu.getBoard().addObserver(this)
        this.parentFrame = frame ;
        setupBoardPanel();
        initLasersAndDrawPaths();
        revalidate();
    }

    public Couleur getCurrentColor(){
        return this.game.getCurrentPlayer();
    }

    public int getCellSize() {
        return 75;
    }

    
    private void setupBoardPanel() {
        setLayout(new BorderLayout()); //TODO : why ?
        addMouseListener(this); //TODO ; doit etre le controller et ici ya repitition ya deja dans le constructeur
        initBoard();
    }

    public void initBoard() {
        removeAll();
        revalidate();
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        if (cheminLaserRouge != null && cheminLaserJaune != null) {
            drawLaserPath(g, cheminLaserRouge, Color.RED);
            drawLaserPath(g, cheminLaserJaune, Color.YELLOW);
        }
    }
    private void drawBoard(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawImage(view.components.PiecePanel.draw(g, new Pion(TypeDePion.NONE, Direction.NORD, board.initCouleur(i, j))),
                        j * 75, i * 75, this);
                if (board.getGrille()[i][j] != null) {
                    g.drawImage(view.components.PiecePanel.draw(g, board.getGrille()[i][j]), j * 75, i * 75, this);
                }
            }
        }
    }

    private void drawLaserPath(Graphics g, List<NoeudTrajectoire> cheminLaser, Color couleurLaser) {
        //TODO ATTENTION ON A INVERSE LES COULEURS DU LASER pour debugage
        if (cheminLaser == null || cheminLaser.isEmpty()) {
            System.out.println("je retourne ici");
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        if ( couleurLaser.equals(Color.RED)) {
            g2d.setColor(new Color(9, 100, 131));
        } else if ( couleurLaser.equals(Color.YELLOW) ) {
            g2d.setColor(new Color(245, 125, 77));
        }
        g2d.setStroke(new BasicStroke(4)); // Épaisseur du trait du laser

        NoeudTrajectoire prev = null;
        for (NoeudTrajectoire point : cheminLaser) {
            if (prev != null) {
                int x1 = prev.getPositionI() * getCellSize() + getCellSize() / 2; // Centre de la cellule
                int y1 = prev.getPositionJ() * getCellSize() + getCellSize() / 2;
                int x2 = point.getPositionI() * getCellSize() + getCellSize() / 2;
                int y2 = point.getPositionJ() * getCellSize() + getCellSize() / 2;
                g2d.drawLine(x1, y1, x2, y2);

                // Debug statement to show positions
                System.out.println("Drawing line from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
            }
            prev = point;
        }
    }


    public void initLasersAndDrawPaths() {
        // Propage les lasers pour construire l'arbre de trajectoire
        board.getRed().propagerLaser(board); // a chaque fois tu fais bouger un pion ,
        board.getYellow().propagerLaser(board);
        // Récupère les chemins du laser à partir des arbres de trajectoire
        List<NoeudTrajectoire> cheminLaserRouge = board.getRed().obtenirCheminLaser();
        List<NoeudTrajectoire> cheminLaserJaune = board.getYellow().obtenirCheminLaser();
        // Mise à jour des chemins dans le panel
        updateLaserPath(cheminLaserRouge, Couleur.ROUGE);
        updateLaserPath(cheminLaserJaune, Couleur.JAUNE);

        repaint();
    }

    public void updateLaserPath(List<NoeudTrajectoire> cheminLaser, Couleur couleurLaser) {
        cheminLaserRouge = board.getRed().obtenirCheminLaser(); // jeu.getboard
        cheminLaserJaune = board.getYellow().obtenirCheminLaser();
        if(couleurLaser == Couleur.ROUGE) {
            this.cheminLaserRouge = cheminLaser;
        }else{
            this.cheminLaserJaune = cheminLaser;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked Event Triggered");
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        Pion clickedPiece = board.getPieceAt(col, row);
        System.out.println("Clicked position: (" + col + ", " + row + ")");

        if (e.getButton() == MouseEvent.BUTTON1) { // Bouton gauche pour déplacer ou sélectionner
            System.out.println("Left click detected");
            handleLeftClick(clickedPiece, col, row);
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Bouton droit pour la rotation ou d'autres actions spéciales
            System.out.println("Right click detected");
            handleRightClick(clickedPiece, col, row);
        }
    }

    private void handleLeftClick(Pion clickedPiece, int col, int row) {
        if ( game != null ) {
            if (clickedPiece != null && game.isPlayerTurn(clickedPiece.getCouleur())) {
                System.out.println("Handling action for " + clickedPiece.getCouleur());
                // pour déplacer, empiler ou dépiler selon le type de
                // pion
                if (clickedPiece.getType() == TypeDePion.OBELISQUE) {
                    game.stackOrUnstackObelisk(clickedPiece, col, row);
                } else {
                    if (selectedPiece != null && selectedPiece == clickedPiece) {
                        game.rotatePiece(clickedPiece); // Supposons que le clic répété sur le même pion cause une rotation
                    } else {
                        selectedPiece = clickedPiece;
                    }
                }
            } else {
                System.out.println("It's no turn or piece cannot be moved.");
            }
        }
    }

    private void handleRightClick(Pion clickedPiece, int col, int row) {
        System.out.println("Handling right click");
        if (clickedPiece != null && clickedPiece.getType() != TypeDePion.OBELISQUE
                && clickedPiece.getType() != TypeDePion.DOUBLE_OBELISQUE && clickedPiece.getType() != TypeDePion.NONE) {
            clickedPiece.rotate(true);
            System.out.println("Piece rotated at (" + col + ", " + row + ")");
            gameView.update();
        } else {
            System.out.println("Invalid action for right-click on this type of piece or empty cell.");
            //gameView.update();
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        startX = e.getX() / cellSize;
        startY = e.getY() / cellSize;
        System.out.println("Start position set to (" + startX + ", " + startY + ")");

        if (startX >= 0 && startX < 10 && startY >= 0 && startY < 8) {
            selectedPiece = board.getPieceAt(startX, startY);
            if (selectedPiece != null && selectedPiece.getType() != TypeDePion.NONE) {
                System.out.println(
                        "Mouse pressed at (" + startX + ", " + startY + ") with piece " + selectedPiece.getType());
            } else {
                System.out.println("No valid piece at cell (" + startX + ", " + startY + ")");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released");
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int endX = e.getX() / cellSize;
        int endY = e.getY() / cellSize;
        System.out.println("End position set to (" + endX + ", " + endY + ")");

        Pion endPiece = board.getPieceAt(endX, endY); // Get piece at the end position

        if (e.getButton() == MouseEvent.BUTTON3 && selectedPiece != null
                && selectedPiece.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            if (endPiece == null || endPiece.getType() != TypeDePion.DOUBLE_OBELISQUE) {
                if (board.depilerDoubleObelisque(selectedPiece, startX, startY, endX, endY)) {
                    System.out.println(
                            "Double obelisk separated at (" + startX + ", " + startY + ") to (" + endX + ", " + endY
                                    + ")");
                } else {
                    System.out.println("Unable to separate double obelisk.");
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON1 && selectedPiece != null) {
            if (endPiece == null || !endPiece.equals(selectedPiece)) {
                if (board.movePiece(startX, startY, endX, endY)) {
                    System.out.println(
                            "Piece moved or stacked from (" + startX + ", " + startY + ") to (" + endX + ", " + endY
                                    + ")");
                } else {
                    System.out.println("Invalid move.");
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered the component area.");
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited the component area.");
    }

    @Override
    public void update() {
        initLasersAndDrawPaths();
        repaint();  // Redessine le plateau en réponse à une mise à jour du modèle
    }
}
