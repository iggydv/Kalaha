package com.ignatius.service.board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardServiceTest {

    BoardService boardService;

    @Test
    public void testBoardReset() {
        boardService = createBoardService();
        // Alter the board
        boardService.getBoard().emptyPit(1);
        assertEquals(0, boardService.getBoard().getStonesInPit(1));
        boardService.reset();
        assertEquals(6, boardService.getBoard().getStonesInPit(1));
    }

    @Test
    public void testAssignPlayerOne() {
        boardService = createBoardService();
        boardService.assignPlayerOne("test");
        assertEquals("test", boardService.getPlayer1().getPlayerName());
    }

    @Test
    public void testAssignPlayerTwo() {
        boardService = createBoardService();
        boardService.assignPlayerTwo("test");
        assertEquals("test", boardService.getPlayer2().getPlayerName());
    }

    @Test
    public void testStartGame() {
        boardService = createBoardService();
        boardService.startGame();
        assertEquals(boardService.getPlayer1(), boardService.getActivePlayer());
    }

    /**
     * - Tests if the pit that selected is empty
     * - Tests if a stone was added to the player's other pits
     * - Tests if a stone was added to the player's kalaha
     * - Tests if player-1 gets another turn
     */
    @Test
    public void testPlayLastStoneInKalaha() {
        boardService = createBoardService();
        boardService.startGame();
        boardService.play(0);
        assertEquals(0, boardService.getBoard().getStonesInPit(0));
        assertEquals(7, boardService.getBoard().getStonesInPit(1));
        assertEquals(7, boardService.getBoard().getStonesInPit(2));
        assertEquals(7, boardService.getBoard().getStonesInPit(3));
        assertEquals(7, boardService.getBoard().getStonesInPit(4));
        assertEquals(7, boardService.getBoard().getStonesInPit(5));
        assertEquals(1, boardService.getKalahaStones(boardService.getPlayer1()));
        assertEquals(boardService.getPlayer1(), boardService.getActivePlayer());
    }

    /**
     * - Tests if the pit that selected is empty
     * - Tests if a stone was added to the player's kalaha
     * - Tests if a stone was added to the other player's pit
     * - Tests if player-2 is the now next player
     */
    @Test
    public void testPlayLastStoneInPit() {
        boardService = createBoardService();
        boardService.startGame();
        boardService.play(1);
        assertEquals(0, boardService.getBoard().getStonesInPit(1));
        assertEquals(1, boardService.getKalahaStones(boardService.getPlayer1()));
        assertEquals(7, boardService.getBoard().getStonesInPit(6));
        assertEquals(boardService.getPlayer2(), boardService.getActivePlayer());
    }

    /**
     * - Tests if the pit that was distributed to is empty
     * - Tests if the opposite pit, to the pit that was distributed to is empty
     * - Tests if all stones were collected and added to the player's kalaha
     * - Tests if player-2 is the now next player
     */
    @Test
    public void testPlayLastStoneInEmptyPit() {
        boardService = createBoardService();
        boardService.startGame();

        boardService.getBoard().getPit(1).setStones(0);
        boardService.getBoard().getPit(0).setStones(1);
        boardService.play(0);

        assertEquals(0, boardService.getBoard().getStonesInPit(1));
        assertEquals(0, boardService.getBoard().getStonesInPit(10));
        assertEquals(7, boardService.getKalahaStones(boardService.getPlayer1()));
        assertEquals(boardService.getPlayer2(), boardService.getActivePlayer());
    }

    @Test
    public void testIsGameEndFalse() {
        boardService = createBoardService();
        boardService.startGame();
        assertFalse(boardService.isGameOver());
    }

    @Test
    public void testIsGameEndTrue() {
        boardService = createBoardServiceWithEndgameCondition();
        boardService.startGame();
        boardService.play(0);
        assertTrue(boardService.isGameOver());
    }

    private BoardService createBoardService() {
        boardService = new BoardService();
        boardService.assignPlayerOne("player1");
        boardService.assignPlayerTwo("player2");
        return boardService;
    }

    private BoardService createBoardServiceWithEndgameCondition() {
        boardService = new BoardService();
        boardService.assignPlayerOne("player1");
        boardService.assignPlayerTwo("player2");
        for (int i = 6; i < 12; i++) {
            boardService.getBoard().getPit(i).setStones(0);
        }

        return boardService;
    }
}