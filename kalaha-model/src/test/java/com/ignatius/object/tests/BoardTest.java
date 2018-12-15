package com.ignatius.object.tests;

import com.ignatius.data.objects.Board;
import com.ignatius.data.objects.Kalaha;
import com.ignatius.data.objects.Pit;
import com.ignatius.data.objects.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    @Mock
    Kalaha kalahaPlayer1;

    @InjectMocks
    Player player1;

    @Mock
    Kalaha kalahaPlayer2;

    @InjectMocks
    Player player2;

    private Board testBoard;

    @Test
    public void testPitInitialization() {
        setTestBoard();
        Random random = new Random();
        assertEquals(6, testBoard.getStonesInPit(random.nextInt(11)));
        assertEquals(0, testBoard.getKalaha(player1).getStones());
    }

    @Test
    public void testEndGameConditionNegativeCase() {
        setTestBoard();
        assertFalse(testBoard.endGameCondition());
    }

    @Test
    public void testEndGameConditionPositiveCase() {
        setTestBoard();
        for (int i = 0; i < (testBoard.getPits().length)/2; i++) {
            testBoard.getPit(i).setStones(0);
        }
        assertTrue(testBoard.endGameCondition());
    }

    @Test
    public void testPlayer1Won() {
        setTestBoard();
        Kalaha l = testBoard.getKalaha(player1);
        testBoard.addToKalaha(player1, 20);
        assertEquals(1, testBoard.winnigPlayer());
    }

    @Test
    public void testPlayer2Won() {
        setTestBoard();
        testBoard.addToKalaha(player2, 20);
        assertEquals(2, testBoard.winnigPlayer());
    }

    @Test
    public void testDraw() {
        setTestBoard();
        testBoard.addToKalaha(player2, 20);
        testBoard.addToKalaha(player1, 20);
        assertEquals(0, testBoard.winnigPlayer());
    }

    private void setTestBoard() {
        testBoard = new Board(12, 72);
        testBoard.setPlayer1(player1);
        testBoard.setPlayer2(player2);
    }

}