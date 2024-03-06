import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.components.BoardPanel;

public class BoardPanelTest {

    @Test
    public void initBoard_correctNumberOfPiecesAdded() {
        // Arrange
        BoardPanel boardPanel = new BoardPanel();
        int expectedNumPieces = 80;

        // Act
        //boardPanel.initBoard();

        // Assert
        assertEquals(expectedNumPieces, boardPanel.getComponentCount());
    }

}