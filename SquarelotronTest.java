import static org.junit.Assert.*;
import org.junit.Test;

public class SquarelotronTest {
    
    Squarelotron squarelotron;

    @Test
    public void testConstructor() {
        squarelotron = new Squarelotron(3);
        assertEquals(3, squarelotron.size);
        assertEquals(1, squarelotron.squarelotron[0][0]);
        assertEquals(3, squarelotron.squarelotron[0][2]);
        assertEquals(9, squarelotron.squarelotron[2][2]);
        assertEquals(7, squarelotron.squarelotron[2][0]);
        assertEquals(5, squarelotron.squarelotron[1][1]);
    }

    @Test
    public void testUpDownFlip() {
        squarelotron = new Squarelotron(4);
        squarelotron = squarelotron.upsideDownFlip(1);
        assertEquals(13, squarelotron.squarelotron[0][0]);
        assertEquals(9, squarelotron.squarelotron[0][1]);
        assertEquals(5, squarelotron.squarelotron[0][2]);
        assertEquals(16, squarelotron.squarelotron[0][3]);
    }

    @Test
    public void testMainDiagonalFlip() {
        squarelotron = new Squarelotron(5);
        squarelotron = squarelotron.mainDiagonalFlip(2);
        assertEquals(12, squarelotron.squarelotron[1][3]);
        assertEquals(8, squarelotron.squarelotron[2][1]);
        assertEquals(9, squarelotron.squarelotron[3][1]);   
        assertEquals(17, squarelotron.squarelotron[1][3]);
    }

    @Test
    public void testRotateRight() {
        squarelotron = new Squarelotron(2);
        squarelotron = squarelotron.rotateRight(1);
        assertEquals(3, squarelotron.squarelotron[0][0]);
        assertEquals(1, squarelotron.squarelotron[0][1]);
        assertEquals(4, squarelotron.squarelotron[1][0]);   
        assertEquals(2, squarelotron.squarelotron[1][1]);
    }

    @Test
    public void testRotateLeft() {
        squarelotron = new Squarelotron(2);
        squarelotron = squarelotron.rotateRight(-1);
        assertEquals(2, squarelotron.squarelotron[0][0]);
        assertEquals(4, squarelotron.squarelotron[0][1]);
        assertEquals(3, squarelotron.squarelotron[1][0]);   
        assertEquals(1, squarelotron.squarelotron[1][1]);
    }
}
