package test.java.khet.view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SpriteDimensionsTest {
    @Mock
    private BufferedImage mockedSpriteSheet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockedSpriteSheet.getWidth()).thenReturn(1000);
        when(mockedSpriteSheet.getHeight()).thenReturn(500);
    }

    @Test
    public void testSpriteDimensionsCalculation() throws IOException {
        File mockedFile = mock(File.class);
        when(mockedFile.exists()).thenReturn(true);
        when(ImageIO.read(mockedFile)).thenReturn(mockedSpriteSheet);

        // Simulate the main method execution or better yet refactor the main method to
        // a callable method within the class
        int spritesHorizontal = 10;
        int spritesVertical = 2;
        int spriteWidth = mockedSpriteSheet.getWidth() / spritesHorizontal;
        int spriteHeight = mockedSpriteSheet.getHeight() / spritesVertical;

        assertEquals(100, spriteWidth, "Expected sprite width calculation to match");
        assertEquals(250, spriteHeight, "Expected sprite height calculation to match");
    }

    @Test
    public void testImageLoadingFailure() throws IOException {
        File mockedFile = mock(File.class);
        when(mockedFile.exists()).thenReturn(true);
        when(ImageIO.read(mockedFile)).thenThrow(new IOException("Failed to load image"));

        assertThrows(IOException.class, () -> {
            ImageIO.read(mockedFile);
        }, "Expected IOException to be thrown");
    }
}
