package view.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class BoardPanel extends JPanel implements MouseListener {

    private Plateau board; // MVCCCCCC
    // Passer Game en attribut pas le Board
    private List<Point> trajetLaser; 

    public BoardPanel() {
        setLayout(new GridLayout(8, 10));
        initBoard();
        JButton b = new JButton();
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                put(new Pion(TypeDePion.DJED,Direction.SUD,Couleur.JAUNE));
            }
        });
        b.setSize(new Dimension(100,100));
        add(b);


    }

    private void initBoard() {
        // Initialise le plateau avec les PiecePanel pour chaque case
        removeAll(); // Enlève tous les composants précédemment ajoutés si nécessaire
        Plateau board = new Plateau();
        this.board = board;
        
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawImage(  PiecePanel.draw(g,board.getGrille()[i][j]),i*100,j*100, this);
            }
        }
    }


    public void put ( Pion p ) {
        //placer dans le board 
        //board.deplacerPion(p);*
        board.getGrille()[2][2] = new Pion(TypeDePion.DJED,Direction.SUD,Couleur.JAUNE);
        repaint();
    }

    public void setTrajetLaser(List<Point> trajetLaser) {
        this.trajetLaser = trajetLaser;
        repaint(); // Redessine le panel pour afficher le nouveau trajet du laser
    }

    private void dessinerPlateau(Graphics g) {
        // Code pour dessiner le plateau et les pièces
    }

    private void dessinerTrajetLaser(Graphics g) {
        if (trajetLaser != null && !trajetLaser.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED); // Couleur du laser
            g2.setStroke(new BasicStroke(2)); // Épaisseur du trait

            Point prevPoint = trajetLaser.get(0);
            for (int i = 1; i < trajetLaser.size(); i++) {
                Point point = trajetLaser.get(i);
                g2.drawLine(prevPoint.x, prevPoint.y, point.x, point.y);
                prevPoint = point;
            }
        }
    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("Plateau");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BoardPanel());
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}