package view;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SettingsTest {

    private JFrame mainFrame;
    private Settings settingsPanel;

    @BeforeEach
    public void setUp() {
        mainFrame = new JFrame();
        settingsPanel = new Settings(mainFrame);
        mainFrame.add(settingsPanel);
        mainFrame.pack();
    }

    @Test
    public void testSettingsPanelInitialization() {
        // Vérifie que le panneau contient tous les composants nécessaires
        assertEquals(7, settingsPanel.getComponentCount()); // 7 composants : titre, checkbox, label volume, slider,
                                                            // deux boutons et espacement

        // Vérifie que le titre est correctement initialisé
        JLabel titleLabel = (JLabel) settingsPanel.getComponent(0);
        assertEquals("Settings", titleLabel.getText());
        assertEquals(new Font("Arial", Font.BOLD, 30), titleLabel.getFont());
        assertEquals(SwingConstants.CENTER, titleLabel.getHorizontalAlignment());

        // Vérifie que la checkbox est correctement initialisée
        JCheckBox soundCheckBox = (JCheckBox) settingsPanel.getComponent(1);
        assertEquals("Activer le son", soundCheckBox.getText());

        // Vérifie que le label de volume est correctement initialisé
        JLabel volumeLabel = (JLabel) settingsPanel.getComponent(2);
        assertEquals("Volume:", volumeLabel.getText());

        // Vérifie que le slider de volume est correctement initialisé
        JSlider volumeSlider = (JSlider) settingsPanel.getComponent(3);
        assertEquals(0, volumeSlider.getMinimum());
        assertEquals(100, volumeSlider.getMaximum());
        assertEquals(50, volumeSlider.getValue());

        // Vérifie que les boutons sont correctement initialisés
        JButton applyButton = (JButton) settingsPanel.getComponent(4);
        assertEquals("Set Modifications", applyButton.getText());

        JButton cancelButton = (JButton) settingsPanel.getComponent(5);
        assertEquals("Back To Menu", cancelButton.getText());
    }

    @Test
    public void testApplyButtonAction() {
        JCheckBox soundCheckBox = (JCheckBox) settingsPanel.getComponent(1);
        JSlider volumeSlider = (JSlider) settingsPanel.getComponent(3);
        JButton applyButton = (JButton) settingsPanel.getComponent(4);

        soundCheckBox.setSelected(true);
        volumeSlider.setValue(75);

        applyButton.doClick();

        boolean isSoundEnabled = soundCheckBox.isSelected();
        int volume = volumeSlider.getValue();

        assertTrue(isSoundEnabled);
        assertEquals(75, volume);
    }

    @Test
    public void testCancelButtonAction() {
        JButton cancelButton = (JButton) settingsPanel.getComponent(5);

        cancelButton.doClick();
    }
}
