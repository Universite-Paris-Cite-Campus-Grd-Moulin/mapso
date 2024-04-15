package test.java.khet.view.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import view.components.PieceComponent;

public class PieceComponentTest {

    @Mock
    private File mockFile;
    private PieceComponent pieceComponent;
    private final String validImagePath = "valid/path/to/image.png";
    private final String invalidImagePath = "invalid/path/to/image.png";
    private BufferedImage testImage;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        // Create a dummy BufferedImage to use in tests
        testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        ByteArrayInputStream imageInput = new ByteArrayInputStream(new byte[0]);
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.getPath()).thenReturn(validImagePath);
        ImageIO.setUseCache(false);
        ImageIO.write(testImage, "png", mockFile); // Mocking file I/O
        pieceComponent = new PieceComponent(validImagePath);
    }

    @Test
    public void testLoadImage_ValidPath() {
        // Ensure that the image is loaded properly when the file exists
        assertNotNull(pieceComponent.getIcon(), "Icon should be loaded and not null.");
    }

    @Test
    public void testLoadImage_InvalidPath() throws IOException {
        // Simulate the file not existing
        when(mockFile.exists()).thenReturn(false);
        doThrow(new IOException()).when(mockFile).getPath();
        pieceComponent = new PieceComponent(invalidImagePath);

        assertNull(pieceComponent.getIcon(), "Icon should not be loaded.");
        assertEquals("Image not available", pieceComponent.getText(),
                "Fallback text is not set correctly when image loading fails.");
    }

    @Test
    public void testHandleMissingImage() throws IOException {
        // Test the handling of missing image without a valid fallback
        doThrow(new IOException("Failed to load")).when(mockFile).getPath();
        pieceComponent = new PieceComponent(invalidImagePath);

        assertEquals("Image not available", pieceComponent.getText(),
                "Fallback text should be displayed when default image fails to load.");
    }
}
