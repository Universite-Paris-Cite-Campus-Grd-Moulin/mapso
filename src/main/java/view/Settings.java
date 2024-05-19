package view;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {

    public Settings(JFrame mainFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Créer et configurer le label du titre
        JLabel titleLabel = new JLabel("Settings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Police en gras et taille 30
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Exemple de paramètre : activer/désactiver le son
        JCheckBox soundCheckBox = new JCheckBox("Activer le son");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(soundCheckBox, gbc);

        // Exemple de paramètre : régler le volume
        JLabel volumeLabel = new JLabel("Volume:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(volumeLabel, gbc);

        JSlider volumeSlider = new JSlider(0, 100, 50);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(volumeSlider, gbc);

        JButton applyButton = new JButton("Set Modifications");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(applyButton, gbc);

        JButton cancelButton = new JButton("Back To Menu");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cancelButton, gbc);

        applyButton.addActionListener(e -> {
            boolean isSoundEnabled = soundCheckBox.isSelected();
            int volume = volumeSlider.getValue();
            JOptionPane.showMessageDialog(this, "Paramètres appliqués!");
        });

        cancelButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            Menu menu = new Menu(mainFrame);
            mainFrame.setContentPane(menu);
            mainFrame.revalidate();
            mainFrame.repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color startColor = new Color(80, 198, 236); // Light Blue
        Color endColor = new Color(255, 160, 122); // Shrimp
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, width, height, endColor);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, width, height);
    }
}
