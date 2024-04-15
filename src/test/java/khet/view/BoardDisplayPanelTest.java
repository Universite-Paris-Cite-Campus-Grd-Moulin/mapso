package test.java.khet.view;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import view.BoardDisplayPanel;

public class BoardDisplayPanelTest {

    private BoardDisplayPanel panel;

    @Mock
    private Graphics graphics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        panel = new BoardDisplayPanel();
    }

    @Test
    public void testPaintComponent() {
        verify(graphics, atLeastOnce()).setColor(any(Color.class)); // Check if the setColor method was called
        verify(graphics, atLeastOnce()).fillRect(anyInt(), anyInt(), anyInt(), anyInt()); // Check if the fillRect
                                                                                          // method was used
        verify(graphics, atLeastOnce()).drawRect(anyInt(), anyInt(), anyInt(), anyInt()); // Check if the drawRect
                                                                                          // method was used
    }
}
