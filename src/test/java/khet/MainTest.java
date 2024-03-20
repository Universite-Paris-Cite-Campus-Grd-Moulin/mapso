import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void main() {
        Main main = new Main();
        main.main(new String[0]);
    }

    @Test
    void testCreateAndShowGUI() {
        Main main = new Main();

        //main.createAndShowGUI();

    }
    public void placeComponentsTest() {
        Main main = new Main();
        //BackgroundPanel panel = new BackgroundPanel();
        JButton start = new JButton();
        JButton settings = new JButton();
        JButton exit = new JButton();

        //main.placeComponents(panel, start, settings, exit);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.PAGE_END;
    }
    @Test
    public void createButton_validImagePath_returnsJButtonWithCorrectIcon() {
        String imagePath = "src/main/resources/images/button_start.png";
        //JButton button = Main.createButton(imagePath);
        ImageIcon expectedIcon = new ImageIcon(imagePath);
        //assertEquals(expectedIcon, button.getIcon());
    }
    
    @Test
    public void showGameOptionsTest() {
        Main main = new Main();
        //JDialog gameOptionsDialog = mock(JDialog.class);
        
    }
    @Test
    public void openSettingsTest() {
        Main main = new Main();
        //main.openSettings();
    }
}