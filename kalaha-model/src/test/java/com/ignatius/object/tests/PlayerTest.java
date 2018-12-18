package com.ignatius.object.tests;

import com.ignatius.data.objects.Kalaha;
import com.ignatius.data.objects.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Mock
    Kalaha kalaha;

    @Test
    public void testSetKalaha() {
        Player test = createPlayer();
        test.setKalaha(kalaha);
        assertEquals(kalaha, test.getKalaha());
    }

    @Test
    public void testGetPlayerName() {
        Player test = createPlayer();
        assertEquals("player1", test.getPlayerName());
    }

    @Test
    public void testSetPlayerName() {
        Player test = createPlayer();
        String name = "bol.com";
        test.setPlayerName(name);
        assertEquals(name, test.getPlayerName());
    }

    private Player createPlayer() {
        return new Player("player1");
    }
}