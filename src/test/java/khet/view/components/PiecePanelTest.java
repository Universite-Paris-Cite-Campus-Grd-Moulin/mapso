package test.java.khet.view.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.Pion;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;
import view.components.PiecePanel;

public class PiecePanelTest {

    @Mock
    private Graphics graphics;
    private BufferedImage testImage;
    private Pion pion;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        testImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        pion = new Pion(TypeDePion.PHARAON, Direction.EST, Couleur.JAUNE);

        // Assume that the static block loads successfully
        File file = mock(File.class);
        when(file.exists()).thenReturn(true);
        ImageIO.write(testImage, "png", file);
    }

    @Test
    public void drawValidPieceImage() {
        BufferedImage resultImage = PiecePanel.draw(graphics, pion);

        assertNotNull(resultImage, "The image should be created.");
        assertTrue(resultImage.getWidth() == 75 && resultImage.getHeight() == 75,
                "The image should be resized correctly.");
    }

    @Test
    public void drawInvalidPieceImage() throws IOException {
        // Simulate failure in loading image
        doThrow(new IOException()).when(ImageIO.class);
        ImageIO.read(any(File.class));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            PiecePanel.draw(graphics, new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE));
        });

        assertTrue(exception.getMessage().contains("Error loading image"),
                "Expected exception to have error loading message.");
    }

    @Test
    public void testImageRotation() {
        // Assuming we are testing if the image rotation is set correctly
        BufferedImage rotatedImage = PiecePanel.draw(graphics, new Pion(TypeDePion.DJED, Direction.SUD, Couleur.ROUGE));
        // Further checks might include verifying the transformation applied or the
        // pixels after rotation
        assertNotNull(rotatedImage, "Rotated image should be generated.");
    }
}
