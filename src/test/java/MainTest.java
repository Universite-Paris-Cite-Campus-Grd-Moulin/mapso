import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import view.Menu;

public class MainTest {

    private JFrame mainFrame;

    @BeforeEach
    public void setUp() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Khet Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1100, 650);
        mainFrame.setResizable(false);
    }

    @Test
    public void testMainFrameInitialization() {
        assertEquals("Khet Game", mainFrame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, mainFrame.getDefaultCloseOperation());
        assertEquals(1100, mainFrame.getWidth());
        assertEquals(650, mainFrame.getHeight());
        assertFalse(mainFrame.isResizable());
    }

    @Test
    public void testMenuInitialization() {
        Menu menu = new Menu(mainFrame);
        mainFrame.setContentPane(menu);
        mainFrame.setVisible(true);

        assertEquals(menu, mainFrame.getContentPane());
        assertTrue(mainFrame.isVisible());
    }
}
