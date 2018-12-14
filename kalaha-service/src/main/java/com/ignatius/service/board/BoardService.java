package com.ignatius.service.board;

import com.ignatius.data.objects.Board;
import com.ignatius.data.objects.Pit;
import com.ignatius.data.objects.Player;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author Ignatius de Villiers
 * @since 12 December 2018
 */
public class BoardService {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(BoardService.class);

    @Inject
    private Board board;

    @Inject
    private Player player1, player2;


    private Player activePlayer;

    public boolean isGameEnd() {
        return gameEnd;
    }

    private boolean gameEnd = false;


    public BoardService() {
        board = new Board(12, 72);
    }

    public boolean assignPlayerNames(String playerName) {
        if (player1 == null) {
            logger.info("{} is Player-1", playerName);
            player1 = new Player(playerName);
            board.setPlayer1(player1);
            return true;
        } else if (player2 == null) {
            player2 = new Player(playerName);
            logger.info("{} is Player-2", playerName);
            board.setPlayer2(player2);
            return true;
        }
        return false;
    }

    public boolean startGame() {
        if (player1 == null) {
            logger.warn("Please enter player-1 name");
            return false;
        } else if (player2 == null) {
            logger.warn("Please enter player-2 name");
            return false;
        }
        setActivePlayer(player1);
        return true;
    }

    public Pit[] play(int pitIndex) {

        logger.info("{} Clicked on pit-{}", getActivePlayer().getPlayerName(), pitIndex);
        if (board.getPit(pitIndex).getStones() == 0) {
            logger.info("{} selected and empty pit - choose again", getActivePlayer().getPlayerName());
            return board.getPits();
        }
        int stonesInHand = board.getPit(pitIndex).emptyPit();
        logger.info("Stones in hand: {}", stonesInHand);
        int loopIndex = pitIndex + 1;
        Player nextPlayer = null;
        boolean shouldAddToKalaha = true;
        String lastStoneDished = "";
        int lastAlteredPitIndex = 0;

        if (player1.getPlayerName().equals(getActivePlayer().getPlayerName())) {
            while (loopIndex < board.getPits().length + 1 && stonesInHand != 0) {

                if (loopIndex == 12) {
                    loopIndex = 0;
                    shouldAddToKalaha = true;
                }

                if (loopIndex == 6 && shouldAddToKalaha) {
                    board.addToKalaha(player1, 1);
                    logger.info("Adding to kalaha - total: {}}", board.getKalaha(player1).getStones());
                    lastStoneDished = "kalaha";
                    shouldAddToKalaha = false;
                } else {
                    board.addToPit(loopIndex, 1);
                    logger.info("Adding to pit-{} - total: {}}", loopIndex, board.getPit(loopIndex).getStones());
                    lastStoneDished = "pit";
                    lastAlteredPitIndex = loopIndex;
                    loopIndex++;
                }
                stonesInHand--;
            }

            if ("kalaha".equals(lastStoneDished)) {
                nextPlayer = activePlayer;
            }
            else if ("pit".equals(lastStoneDished)) {
                logger.info("yith");
                logger.info("{}", lastAlteredPitIndex);
                logger.info("{}", board.getPit(lastAlteredPitIndex).getStones());
                if (board.getPit(lastAlteredPitIndex).getStones() == 1) {
                    collectOppositeStones(lastAlteredPitIndex);
                }
                nextPlayer = player2;
            }
        }

        if (player2.getPlayerName().equals(getActivePlayer().getPlayerName())) {

            while (loopIndex < board.getPits().length + 1 && stonesInHand != 0) {

                if (loopIndex == 6) {
                    shouldAddToKalaha = true;
                }

                if (loopIndex == 12 && shouldAddToKalaha) {
                    board.addToKalaha(player2, 1);
                    logger.info("Adding to kalaha - total: {}}", board.getKalaha(player1).getStones());
                    shouldAddToKalaha = false;
                    lastStoneDished = "kalaha";
                    loopIndex = 0;
                } else {
                    board.addToPit(loopIndex, 1);
                    // TODO improve Board methods
                    logger.info("Adding to pit-{} - total: {}}", loopIndex, board.getPit(loopIndex).getStones());
                    lastStoneDished = "pit";
                    lastAlteredPitIndex = loopIndex;
                    loopIndex++;
                }
                stonesInHand--;
            }
            if ("kalaha".equals(lastStoneDished)) {
                nextPlayer = activePlayer;
            }
            else if ("pit".equals(lastStoneDished)) {
                if (board.getPit(lastAlteredPitIndex).getStones() == 1) {
                    collectOppositeStones(lastAlteredPitIndex);
                }
                nextPlayer = player1;
            }
        }
        // TODO: set a flag in the main UI to listen for this occurrence
        if (endGameCondition()) {
            gameEnd = true;
            return board.getPits();
        }
        setActivePlayer(nextPlayer);
        assert nextPlayer != null;
        logger.info("{} will play next", nextPlayer.getPlayerName());
        return board.getPits();
    }

    private boolean endGameCondition() {
        return board.endGameCondition(getActivePlayer());
    }

    // TODO: This rule should only apply to the player's own side
    private void collectOppositeStones(int index) {
        if ((activePlayer.equals(player1) && index < 6)) {
            logger.info("collecting all stones from {} putting in {}", (11 - index), index);
            int stonesWon = board.getPit(11 - index).emptyPit() +  board.getPit(index).emptyPit();
            board.addToKalaha(player1, stonesWon);
        }

        if (activePlayer.equals(player2) && index > 5) {
            logger.info("collecting all stones from {} putting in {}", (11 - index), index);
            int stonesWon = board.getPit(11 - index).emptyPit() +  board.getPit(index).emptyPit();
            board.addToKalaha(player2, stonesWon);
        }
    }

    // TODO use this
    private void setNextPlayer(String lastStoneDished) {
        Player nextPlayer = null;
        if (lastStoneDished.equals("kalaha")) {
            nextPlayer = activePlayer;
        } else {
            nextPlayer = player1;
        }
        setActivePlayer(nextPlayer);
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    private void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    private Player getActivePlayer() {
        return activePlayer;
    }

    public int getActivePlayerNumber() {
        if (getActivePlayer().equals(player1)) {
            return 1;
        } else if (getActivePlayer().equals(player2)) {
            return 2;
        }
        return 0;
    }

    public Board getBoard() {
        return board;
    }

    // TODO make enum
    public int getKalahaStones(int playerKalaha) {
        StringBuilder kalahaStones = new StringBuilder();
        switch (playerKalaha) {
            case 1:
                return board.getKalaha(player1).getStones();
            case 2:
                return board.getKalaha(player2).getStones();
            default:
                throw new IllegalArgumentException("Invalid enum");
        }
    }

}
