package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Audio;
import view.components.*;
import java.awt.event.ActionEvent;

// Cette classe crée le menu
// qui nous permet de se redériger vers le jeu
public class Menu extends JPanel{
    private JButton startButton, settingsButton, exitButton;
    private JFrame mainFrame ;
    private final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Fond_Khet.png");

    private void openBoard(String type) {
        mainFrame.getContentPane().removeAll();
        // Création et ajout de la vue du jeu
        GameView jeu = new GameView(mainFrame,type);
        mainFrame.setContentPane(jeu);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public Menu( JFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_END;

        // Ajoute un espace pour pousser les composants vers le bas
        JPanel spacer = new JPanel();
        spacer.setSize(100,200);
        spacer.setOpaque(false);
        gbc.weighty = 1;
        add(spacer, gbc); // Le spacer est ajouté en premier

        // Réinitialise le poids y pour les boutons
        gbc.weighty = 0;

        startButton = createButton("src/main/resources/images/button_start.png");
        settingsButton = createButton("src/main/resources/images/button_settings.png");
        exitButton = createButton("src/main/resources/images/button_exit.png");

        startButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(800, new ActionListener(){
                public void actionPerformed(ActionEvent e){
                openBoard("Classic");
                }
            });
            timer.start();
        });
        settingsButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
             Timer timer = new Timer(800, new ActionListener(){
            public void actionPerformed(ActionEvent e){
            openBoard("Classic");
            }
        });
        openSettings();
    });

        exitButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(800, new ActionListener(){
                public void actionPerformed(ActionEvent e){
                openBoard("Classic");
                }
            });
            System.exit(0); 
        });

        // Ajoute les boutons
        gbc.gridy = GridBagConstraints.RELATIVE; // Utilise RELATIVE pour une meilleure gestion des positions
        add(startButton, gbc);
        add(settingsButton, gbc);
        add(exitButton, gbc);

        mainFrame.add(this); //  mettre le Jpanel du menu dans le jframe
    }

    private JButton createButton(String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image image = originalIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon buttonIcon = new ImageIcon(image);
        JButton button = new JButton(buttonIcon);
        button.setPreferredSize(new Dimension(100, 50));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }
   
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessine l'image de fond
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    private void showGameOptions() {
        JDialog gameOptionsDialog = new JDialog(mainFrame, "Choose Game Mode", true);
        gameOptionsDialog.setLayout(new GridLayout(1, 0));
        gameOptionsDialog.setSize(400, 100);
        gameOptionsDialog.setLocationRelativeTo(mainFrame);
        gameOptionsDialog.setResizable(false); //  non redimensionnable

        // Personnaliser les boutons
        JButton classicButton = createCustomButton("Classic", new Color(70, 130, 180), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton imhotepButton = createCustomButton("Imhotep", new Color(239, 200, 127), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton dynastyButton = createCustomButton("Dynastie", new Color(255, 160, 122), Color.WHITE, new Font("Arial", Font.BOLD, 16));

        gameOptionsDialog.add(classicButton);
        gameOptionsDialog.add(imhotepButton);
        gameOptionsDialog.add(dynastyButton);

        classicButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(800, new ActionListener(){
                public void actionPerformed(ActionEvent e){
                openBoard("Classic");
                }
            });
            gameOptionsDialog.dispose();
        });  

        imhotepButton.addActionListener(e -> {
            openBoard("Imhotep");
            gameOptionsDialog.dispose();
        });
        dynastyButton.addActionListener(e -> {
            openBoard("Dynastie");
            gameOptionsDialog.dispose();
        });
        gameOptionsDialog.setVisible(true);
    }

    static JButton createCustomButton(String text, Color background, Color foreground, Font font) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
    private void openSettings() {
        mainFrame.getContentPane().removeAll();
        Settings settingsPanel = new Settings(mainFrame);
        mainFrame.setContentPane(settingsPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

   
}
