import fr.einfolearning.tp2.metiers.TextBuffer;
import fr.einfolearning.tp2.metiers.TextEditor;
import fr.einfolearning.tp2.metiers.EmacsKillRing;

import fr.einfolearning.tp2.metiers.exceptions.EmacsKillRingOverflowException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TextEditorTest {

    @Test
    public void testYank() throws IllegalAccessException, EmacsKillRingOverflowException{
        TextBuffer textBufferMock = mock(TextBuffer.class);
        EmacsKillRing emacsKillringMock = mock(EmacsKillRing.class);
        TextEditor textEditor = new TextEditor("Mehdi");
        when(emacsKillringMock.currentElt()).thenReturn("test");
        when(textBufferMock.maxP()).thenReturn(100);

        textEditor.yank();
        verify(emacsKillringMock, times(2)).currentElt();
        verify(textBufferMock).del(anyInt(), anyInt());
        verify(textBufferMock, times(2)).ins(eq("Test"), anyInt());

    }
    @Test
    public void testYankPop() throws IllegalAccessException, EmacsKillRingOverflowException{
        EmacsKillRing emacsKillringMock = mock(EmacsKillRing.class);
        TextEditor textEditor = new TextEditor("Mehdi");
        textEditor.setMark(0);
        textEditor.setCursor(5);
        when(emacsKillringMock.currentElt()).thenReturn("");

        textEditor.emacsKillring = emacsKillringMock;
        textEditor.yank();
        textEditor.yankPop();
        verify(emacsKillringMock, times(1)).rotateFwd();
    }
}
