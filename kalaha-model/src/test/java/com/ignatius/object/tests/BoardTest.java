package com.ignatius.object.tests;

import com.ignatius.data.objects.Board;
import com.ignatius.data.objects.Kalaha;
import com.ignatius.data.objects.Pit;
import com.ignatius.data.objects.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {


    @Mock
    Kalaha kalahaPlayer1, kalahaPlayer2;
    @Mock
    Player player1, player2;

    Board testBoard;


    @Test
    public void testDestroy() {
        //testBoard.destroy();
    }

    @Test
    public void testEndGameConditionNegativeCase() {
        setTestBoard();
        assertFalse(testBoard.endGameCondition(player1));
        assertFalse(testBoard.endGameCondition(player2));
    }

    @Test
    public void testEndGameConditionPositiveCase() {
        setTestBoard();
        for (int i = 0; i < (testBoard.getPits().length)/2; i++) {
            testBoard.getPit(i).setStones(0);
        }
        assertTrue(testBoard.endGameCondition(player1));
        assertFalse(testBoard.endGameCondition(player2));
    }

    @Test
    public void testPlayer2Turn() {
    }

    private void setTestBoard() {
        testBoard = new Board(12, 72);
        testBoard.setPlayer1(player1);
        testBoard.setPlayer2(player2);
    }

}