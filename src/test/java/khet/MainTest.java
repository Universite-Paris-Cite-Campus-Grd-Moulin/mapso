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

        main.createAndShowGUI();

    }
    public void placeComponentsTest() {
        Main main = new Main();
        BackgroundPanel panel = new BackgroundPanel();
        JButton start = new JButton();
        JButton settings = new JButton();
        JButton exit = new JButton();

        main.placeComponents(panel, start, settings, exit);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.PAGE_END;

        assertEquals(gbc, panel.getLayout().getConstraints(start));
        assertEquals(gbc, panel.getLayout().getConstraints(settings));
        assertEquals(gbc, panel.getLayout().getConstraints(exit));
    }
    @Test
    public void createButton_validImagePath_returnsJButtonWithCorrectIcon() {
        String imagePath = "src/main/resources/images/button_start.png";
        JButton button = Main.createButton(imagePath);
        ImageIcon expectedIcon = new ImageIcon(imagePath);
        assertEquals(expectedIcon, button.getIcon());
    }
    
    @Test
    public void showGameOptionsTest() {
        Main main = new Main();
        JDialog gameOptionsDialog = mock(JDialog.class);
        main.mainFrame = mock(JFrame.class);
        when(main.mainFrame.getContentPane()).thenReturn(gameOptionsDialog);
        JButton classicButton = mock(JButton.class);
        JButton imhotepButton = mock(JButton.class);
        JButton dynastyButton = mock(JButton.class);
        when(classicButton.getText()).thenReturn("Classic");
        when(imhotepButton.getText()).thenReturn("Imhotep");
        when(dynastyButton.getText()).thenReturn("Dynastie");
        main.placeComponents(gameOptionsDialog, classicButton, imhotepButton, dynastyButton);

        main.showGameOptions();

        verify(classicButton, times(1)).addActionListener(any());
        verify(imhotepButton, times(1)).addActionListener(any());
        verify(dynastyButton, times(1)).addActionListener(any());
    }
    @Test
    public void openSettingsTest() {
        Main main = new Main();
        main.openSettings();
    }
}