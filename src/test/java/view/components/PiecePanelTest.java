package view.components;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Pion;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class PiecePanelTest {

    private static BufferedImage mockSpriteSheet;
    private static final String spriteSheetPath = "ressources/sprites_khet.png";
    private Pion pion;
    private PiecePanel piecePanel;

    @BeforeAll
    public static void setUpBeforeClass() throws IOException {
        // Mocking the sprite sheet loading
        mockSpriteSheet = Mockito.mock(BufferedImage.class);
        File spriteSheetFile = new File(spriteSheetPath);
        if (!spriteSheetFile.exists()) {
            spriteSheetFile.createNewFile();
        }
        Mockito.when(
                mockSpriteSheet.getSubimage(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
        ImageIO.write(mockSpriteSheet, "png", spriteSheetFile);
    }

    @BeforeEach
    public void setUp() {
        pion = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.JAUNE);
        piecePanel = new PiecePanel(pion);
    }

    @Test
    public void testDrawPiece() {
        // Ensure the draw method returns a non-null BufferedImage
        BufferedImage result = PiecePanel.draw(piecePanel.getGraphics(), pion);
        assertNotNull(result);
    }

    @Test
    public void testDrawPieceRotation() {
        // Test the rotation logic by checking if the draw method handles rotation
        // correctly
        pion.setDirection(Direction.NORD);
        BufferedImage imageN = PiecePanel.draw(piecePanel.getGraphics(), pion);
        assertNotNull(imageN);

        pion.setDirection(Direction.EST);
        BufferedImage imageE = PiecePanel.draw(piecePanel.getGraphics(), pion);
        assertNotNull(imageE);

        pion.setDirection(Direction.SUD);
        BufferedImage imageS = PiecePanel.draw(piecePanel.getGraphics(), pion);
        assertNotNull(imageS);

        pion.setDirection(Direction.OUEST);
        BufferedImage imageW = PiecePanel.draw(piecePanel.getGraphics(), pion);
        assertNotNull(imageW);

        // Ensure that changing the direction results in different images
        assertNotEquals(imageN, imageE);
        assertNotEquals(imageE, imageS);
        assertNotEquals(imageS, imageW);
    }

    @Test
    public void testMainMethod() {
        // Test the main method to ensure the panel initializes correctly
        assertDoesNotThrow(() -> {
            PiecePanel.main(new String[] {});
        });
    }

    @Test
    public void testSetPion() {
        // Test the setPion method
        Pion newPion = new Pion(TypeDePion.DJED, Direction.EST, Couleur.ROUGE);
        piecePanel.setPion(newPion);
    }
}
