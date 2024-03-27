package view.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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