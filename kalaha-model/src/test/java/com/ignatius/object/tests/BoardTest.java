package com.ignatius.object.tests;

import com.ignatius.data.objects.Board;
import com.ignatius.data.objects.Kalaha;
import com.ignatius.data.objects.Pit;
import com.ignatius.data.objects.Player;
import com.ignatius.utils.BoardStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    @Mock
    Kalaha kalahaPlayer1;

    @Mock
    Player player1;

    @Mock
    Kalaha kalahaPlayer2;

    @Mock
    Player player2;

    private Board testBoard;

    @Test
    public void testEndGameConditionNegativeCase() {
        setTestBoard();
        assertFalse(testBoard.endGameCondition());
    }

    @Test
    public void testEndGameConditionPositiveCasePlayer1() {
        setTestBoard();
        for (int i = 0; i < (testBoard.getPits().length)/2; i++) {
            testBoard.getPit(i).setStones(0);
        }
        assertTrue(testBoard.endGameCondition());
    }

    @Test
    public void testEndGameConditionPositiveCasePlayer2() {
        setTestBoard();
        for (int i = 6; i < (testBoard.getPits().length); i++) {
            testBoard.getPit(i).setStones(0);
        }

        assertTrue(testBoard.endGameCondition());
    }

    @Test
    public void testPlayer1Won() {
        setTestBoard();
        Mockito.when(testBoard.getKalaha(player1)).thenReturn(kalahaPlayer1);
        Mockito.when(testBoard.getKalaha(player2)).thenReturn(kalahaPlayer2);
        Mockito.when(kalahaPlayer1.getStones()).thenReturn(20);
        assertEquals(BoardStringUtils.PLAYER_1, testBoard.winnigPlayer());
    }

    @Test
    public void testPlayer2Won() {
        setTestBoard();
        Mockito.when(testBoard.getKalaha(player1)).thenReturn(kalahaPlayer1);
        Mockito.when(testBoard.getKalaha(player2)).thenReturn(kalahaPlayer2);
        Mockito.when(kalahaPlayer2.getStones()).thenReturn(20);
        assertEquals(BoardStringUtils.PLAYER_2, testBoard.winnigPlayer());
    }

    @Test
    public void testDraw() {
        setTestBoard();
        Mockito.when(testBoard.getKalaha(player1)).thenReturn(kalahaPlayer1);
        Mockito.when(testBoard.getKalaha(player2)).thenReturn(kalahaPlayer2);
        testBoard.addToKalaha(player2, 20);
        testBoard.addToKalaha(player1, 20);
        assertEquals(BoardStringUtils.TIE, testBoard.winnigPlayer());
    }

    private void setTestBoard() {
        Pit[] pits = new Pit[12];
        for (int i = 0; i < 12; i++) {
            pits[i] = new Pit(72 / 12);
        }
        testBoard = new Board(12, 72, player1, player2, kalahaPlayer1, kalahaPlayer2, pits);
    }

}