package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import main.java.view.GameView;
import main.java.view.Settings;
import main.java.model.Audio;
import main.java.view.components.*;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {
    private JButton startButton, settingsButton, exitButton, rulesButton;
    private JFrame mainFrame;
    private final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Fond_Khet.png");

    public Menu(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weighty = 1.8;
        add(Box.createVerticalGlue(), gbc);

        gbc.weighty = 0;

        startButton = createButton("src/main/resources/images/button_start.png");
        settingsButton = createButton("src/main/resources/images/button_settings.png");
        exitButton = createButton("src/main/resources/images/button_exit.png");

        startButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(400, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Audio.stopSound();
                    showGameOptions();
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.setRepeats(false);
            timer.start();
        });

        settingsButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(400, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Audio.stopSound();
                    openSettings();
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.setRepeats(false);
            timer.start();
        });

        exitButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(400, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Audio.stopSound();
                    System.exit(0);
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.setRepeats(false);
            timer.start();
        });

        gbc.gridy = GridBagConstraints.RELATIVE;
        add(startButton, gbc);
        add(settingsButton, gbc);
        add(exitButton, gbc);

        gbc.weighty = 1;
        add(Box.createVerticalGlue(), gbc);

        mainFrame.add(this);

        // Jouer la musique de fond en boucle
        //Audio.playSound("ressources/Music.wav"); // Chemin vers votre fichier audio

        // Ajouter le bouton des règles du jeu en bas à droite
        addRulesButton();
    }

    private void addRulesButton() {
        rulesButton = createButton("src/main/resources/images/acceuil.png");
        rulesButton.addActionListener(e -> {
            showRulesDialog();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 950, 10, 10);
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.SOUTHEAST;

        add(rulesButton, gbc);
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
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    private void showGameOptions() {
        JDialog gameOptionsDialog = new JDialog(mainFrame, "Choose Game Mode", true);
        gameOptionsDialog.setLayout(new GridLayout(1, 0));
        gameOptionsDialog.setSize(400, 100);
        gameOptionsDialog.setLocationRelativeTo(mainFrame);
        gameOptionsDialog.setResizable(false);

        JButton classicButton = createCustomButton("Classic", new Color(70, 130, 180), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton imhotepButton = createCustomButton("Imhotep", new Color(239, 200, 127), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton dynastyButton = createCustomButton("Dynastie", new Color(255, 160, 122), Color.WHITE, new Font("Arial", Font.BOLD, 16));

        gameOptionsDialog.add(classicButton);
        gameOptionsDialog.add(imhotepButton);
        gameOptionsDialog.add(dynastyButton);

        classicButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(800, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ((Timer) e.getSource()).stop();
                    openBoard("Classic");
                     gameOptionsDialog.dispose();
                }
            });
            timer.setRepeats(false);
            timer.start();
            
        });
        imhotepButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(800, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ((Timer) e.getSource()).stop();
                    openBoard("Imhotep");
                    gameOptionsDialog.dispose();
                }
            });
            timer.setRepeats(false);
            timer.start();
            
        });
        dynastyButton.addActionListener(e -> {
            Audio.playSound("ressources/click.wav");
            Timer timer = new Timer(800, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ((Timer) e.getSource()).stop();
                    openBoard("Dynastie");
                    gameOptionsDialog.dispose();
                }
            });
            timer.setRepeats(false);
            timer.start();
           
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

    private void openBoard(String type) {
        //Audio.stopSound(); // Arrêter la musique de fond lorsque le jeu commence
        mainFrame.getContentPane().removeAll();

        // Obtenir le temps par défaut en fonction du type de jeu
        int timeLeft = getDefaultTimeLeft(type);

        // Création et ajout de la vue du jeu
        GameView jeu = new GameView(mainFrame, type, timeLeft);
        mainFrame.setContentPane(jeu);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private int getDefaultTimeLeft(String type) {
        switch (type) {
            case "Classic":
                return 30;
            case "Imhotep":
                return 20;
            case "Dynastie":
                return 15;
            default:
                return 30;
        }
    }

    private void showRulesDialog() {
        String rules = "<html><body>" +
                "<h2>Règles du jeu Khet</h2>" +
                "<p><strong>Matériel de jeu :</strong><br>" +
                "Khet est un jeu à deux joueurs, Jaune et Rouge. Il se joue sur un damier rectangulaire de taille 10 × 8. " +
                "<br>Chaque joueur contrôle un laser de sa couleur placé sur l’un des bords horizontaux du plateau, émettant un rayon lumineux verticalement. " +
                "<br>Chaque joueur dispose également d’un ensemble de pièces de sa couleur : un Pharaon, sept Pyramides, un Djed, un Horus, deux Obélisques. " +
                "<br>Deux Obélisques de même couleur peuvent temporairement être empilés l’un sur l’autre, formant un Obélisque double.</p>" +

                "<p><strong>But du jeu :</strong><br>" +
                "Le but est d'atteindre le Pharaon adverse avec le laser de votre couleur à travers une série de réflexions de miroirs.</p>" +

                "<p><strong>Règles du jeu :</strong><br>" +
                "1. Les joueurs jouent à tour de rôle, en commençant par le joueur Jaune.<br>" +
                "2. À son tour, un joueur doit effectuer une action parmi les suivantes :<br>" +
                "- Déplacer une pièce vers une case vide adjacente et valide.<br>" +
                "- Empiler l’un de ses Obélisques sur un autre adjacent, ou dépiler le sommet d’un Obélisque double sur une case vide adjacente et valide.<br>" +
                "- Échanger la position de son Djed ou Horus avec une Pyramide ou un Obélisque adjacent, quelle que soit leur couleur.<br>" +
                "- Tourner de +/− 90° l’une de ses Pyramides, son Djed ou son Horus.<br>" +
                "3. Après avoir effectué une action, le joueur actionne son laser. Si le rayon touche une partie non réfléchissante d’une pièce, cette pièce est retirée du jeu.<br> Si le rayon atteint un Pharaon, le joueur adverse est déclaré perdant.</p>" +

                "<p><strong>Types de pièces :</strong><br>" +
                "- Pyramide : A un unique miroir réfléchissant le rayon à 90°.<br>" +
                "- Djed : Dispose de deux miroirs accolés, réfléchissant le rayon à 90°.<br>" +
                "- Horus : Possède un miroir semi-réfléchissant qui sépare le rayon en deux, l’un réfléchissant à 90° et l’autre continuant en ligne droite.<br>" +
                "- Obélisque : Peut être empilé pour former un double Obélisque.<br>" +
                "- Pharaon : Ne se déplace pas, doit être protégé.</p>" +

                "<p><strong>Interaction avec les pièces :</strong><br>" +
                "- Pour déplacer une pièce, cliquez sur celle-ci puis cliquez sur une case adjacente vide.<br>" +
                "- Pour tourner une pièce (Pyramide, Djed ou Horus), cliquez sur la pièce avec le bouton droit de la souris pour la faire pivoter de 90° dans le sens horaire.<br>" +
                "- Pour empiler des Obélisques, cliquez sur l'un puis sur l'autre lorsqu'ils sont adjacents.<br>" +
                "- Pour dépiler un double Obélisque, cliquez sur celui-ci puis sur une case adjacente vide.</p>" +

                "</body></html>";
        JOptionPane.showMessageDialog(mainFrame, rules, "Règles du jeu", JOptionPane.INFORMATION_MESSAGE);
    }
}
