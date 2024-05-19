package view.components;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PieceComponentTest {

    private PieceComponent pieceComponent;
    private final String validImagePath = "src/main/resources/images/valid_image.png";
    private final String invalidImagePath = "src/main/resources/images/invalid_image.png";
    private final String defaultImagePath = "/path/to/default/image.png";

    @BeforeEach
    public void setUp() {
        // Create a mock for the ImageIO class to avoid actual file IO operations
        ImageIO.setUseCache(false);
    }

    @Test
    public void testLoadValidImage() throws IOException {
        // Mocking the File object and ImageIO.read method
        File file = Mockito.mock(File.class);
        BufferedImage image = Mockito.mock(BufferedImage.class);
        when(file.exists()).thenReturn(true);
        when(ImageIO.read(file)).thenReturn(image);

        pieceComponent = spy(new PieceComponent(validImagePath));
        verify(pieceComponent).setIcon(any(ImageIcon.class));
    }

    @Test
    public void testLoadInvalidImage() throws IOException {
        // Mocking the File object and ImageIO.read method for an invalid image
        File file = Mockito.mock(File.class);
        when(file.exists()).thenReturn(false);

        pieceComponent = spy(new PieceComponent(invalidImagePath));
    }

    @Test
    public void testHandleMissingImage() throws IOException {
        // Mocking the ImageIO.read method to load the default image
        BufferedImage defaultImage = Mockito.mock(BufferedImage.class);
        when(ImageIO.read(getClass().getResource(defaultImagePath))).thenReturn(defaultImage);

        pieceComponent = spy(new PieceComponent(invalidImagePath));
        verify(pieceComponent).setIcon(any(ImageIcon.class));
    }
}
