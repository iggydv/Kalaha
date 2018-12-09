import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PitTest {

    @Test
    public void testEmptyPit() {
        Pit testPit = new Pit(6);
        testPit.emptyPit();
        assertEquals(0, testPit.getStones());
    }

    @Test
    public void testReceive() {
        Pit testPit = new Pit(6);
        testPit.receive(3);
        assertEquals(9, testPit.getStones());
    }
}