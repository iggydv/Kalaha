import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Test
    public void pickPit() {
        Player test = createPlayer();
        assertEquals(1, test.pickPit());
        // check that certain methods are called
    }

    @Test
    public void getPlayerName() {
        Player test = createPlayer();
        assertEquals("player1", test.getPlayerName());
    }

    @Test
    public void setPlayerName() {
        Player test = createPlayer();
        String name = "bol.com";
        test.setPlayerName(name);
        assertEquals(name, test.getPlayerName());
    }

    private Player createPlayer() {
        return new Player("player1");
    }
}