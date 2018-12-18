package com.ignatius.service.board;

import com.ignatius.data.objects.Board;
import com.ignatius.data.objects.Kalaha;
import com.ignatius.data.objects.Pit;
import com.ignatius.data.objects.Player;
import com.ignatius.utils.BoardStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Acts as a link between the Kalaha @{@link Board} objects and the Kalaha Web Application's UI
 *
 * @author Ignatius de Villiers
 * @since 12 December 2018
 */
public class BoardService {

    private static Logger logger = LoggerFactory.getLogger(BoardService.class);
    private boolean gameOver = false;

    @Inject
    private Board board;
    @Inject
    private Player player1, player2, activePlayer;

    public BoardService() {
        board = new Board(12, 72);
    }

    /**
     * Resets the game by creating a new @{@link Board} Object
     */
    public void reset() {
        board = new Board(12, 72);
        board.setPlayer1(player1);
        board.setPlayer2(player2);
        setActivePlayer(player1);
    }

    /**
     * Instantiate the player to either player-1 or player-2
     *
     * @param playerName the player's name
     */
    public void assignPlayerName(String playerName) {
        if (player1 == null) {
            player1 = new Player(playerName);
            board.setPlayer1(player1);
            logger.info("{} is Player-1", playerName);
        } else if (player2 == null) {
            player2 = new Player(playerName);
            board.setPlayer2(player2);
            logger.info("{} is Player-2", playerName);
        }
    }

    /**
     * Instantiate Player-1
     *
     * @param playerName Create a new @{@link Player} object with playerName
     */
    public void assignPlayerOne(String playerName) {
        player1 = new Player(playerName);
        board.setPlayer1(player1);
        logger.info("{} is Player-1", playerName);
    }

    /**
     * Instantiate Player-2
     *
     * @param playerName Create a new @{@link Player} object with playerName
     */
    public void assignPlayerTwo(String playerName) {
        player2 = new Player(playerName);
        board.setPlayer2(player2);
        logger.info("{} is Player-2", playerName);
    }


    /**
     * @return true:   Both @{@link Player}s instances have been instantiated
     *         false:  One or Both Players still need to be instantiated
     */
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

    /**
     * Possible Scenarios:
     * ===================
     * - The selected pit is empty the player will stay the active player
     * - The player picks a pit, containing x amount of stones, the pit is emptied and distributed to the players right
     * - The player distributes 1 stone to each pit, including it's own @{@link Kalaha}
     * - The last stone is distributed to the players Kalaha, the player will stay the active player
     * - The last stone is distributed to one of the players own pits the player collects all the stones from that pit
     *   and and the directly opposite pit and places it in its kalaha
     * - The game ends when all the pits of one player is empty
     * - The winner is the player with the most stones in it's kalaha
     *
     * @param pitIndex The {@link Pit} which has been selected by the @{@link Player}
     */
    public void play(int pitIndex) {

        logger.info("{} Clicked on pit-{}", getActivePlayer().getPlayerName(), pitIndex);

        if (board.getPit(pitIndex).getStones() == 0) {
            logger.info("{} selected and empty pit - choose again", getActivePlayer().getPlayerName());
            return;
        }

        // Collect all the stones from the specified pit
        int stonesInHand = board.emptyPit(pitIndex);
        logger.info("Stones in hand: {}", stonesInHand);

        /* loopIndex           - Set the loopIndex to the next pit, as this is where we want to start dishing stones
         * nextPlayer          - Create an instance of next player, so the service knows where we can start picking up stones from on the next click
         * shouldAddToKalaha   - Simple flag to check whether we want to feed into a certain players kalaha
         * lastStoneDished     - Allows us to apply game logic, by keeping a reference to where the last stone was dished
         * lastAlteredPitIndex - Allows us to apply game logic, by keeping a reference to which Pit index the last stone was dished
         */
        int loopIndex = pitIndex + 1;
        Player nextPlayer = player2;
        boolean PlayerOneToKalaha = true;
        boolean PlayerTwoToKalaha = true;
        String lastStoneDished = "";
        int lastAlteredPitIndex = 0;

        while (loopIndex < board.getPits().length + 1 && stonesInHand != 0) {
            if (loopIndex == 12 && PlayerTwoToKalaha) {
                if (player1.equals(getActivePlayer())) {
                    loopIndex = 0;
                    PlayerOneToKalaha = true;
                } else {
                    board.addToKalaha(player2, 1);
                    logger.info("Adding to kalaha - total: {}}", getKalahaStones(player2));
                    PlayerTwoToKalaha = false;
                    lastStoneDished = BoardStringUtils.KALAHA.getString();
                    loopIndex = 0;
                }

            } else if (loopIndex == 6 && PlayerOneToKalaha) {
                if (player1.equals(getActivePlayer())) {
                    board.addToKalaha(player1, 1);
                    logger.info("Adding to kalaha - total: {}}", getKalahaStones(player1));
                    lastStoneDished = BoardStringUtils.KALAHA.getString();
                    PlayerOneToKalaha = false;
                } else {
                    PlayerTwoToKalaha = true;
                }
            } else {
                board.addToPit(loopIndex, 1);
                logger.info("Adding to pit-{} - total: {}}", loopIndex, board.getPit(loopIndex).getStones());
                lastStoneDished = BoardStringUtils.PIT.getString();
                lastAlteredPitIndex = loopIndex;
                loopIndex++;
            }
            stonesInHand--;
        }
        switch (lastStoneDished) {
            case "kalaha": nextPlayer = activePlayer; break;
            case "pit": {
                if (board.getPit(lastAlteredPitIndex).getStones() == 1) {
                    collectOppositeStones(lastAlteredPitIndex);
                }
                nextPlayer = getNextPlayer(); break;
            }
        }

        // Check whether the end-game condition is met
        if (endGameCondition()) {
            gameOver = true;
            return;
        }
        setActivePlayer(nextPlayer);
    }

    /**
     * @return @{@link Player} object representation of the non-active player
     */
    private Player getNextPlayer() {
        if (activePlayer.equals(player1)) {
            logger.info("{} will play next", player2.getPlayerName());
            return player2;
        } else {
            logger.info("{} will play next", player1.getPlayerName());
            return player1;
        }
    }

    /**
     * @return true:    Any player's pits are all empty
     *         false:   Both player's pits aren't all empty
     */
    private boolean endGameCondition() {
        return board.endGameCondition();
    }

    /**
     * @return true:    @endGameCondition has been met
     *         false:   otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * A player has distributed a stone into one of its own empty pits, all the stones
     * from their own pit, plus the opposite pit, will be placed in their own kalaha
     *
     * @param index the pit index for which the opposite pit needs to be emptied
     */
    private void collectOppositeStones(int index) {
        if ((activePlayer.equals(player1) && index < 6)) {
            logger.info("collecting all stones from {} putting in {}", (11 - index), index);
            int stonesWon = board.emptyPit(11 - index) + board.emptyPit(index);
            board.addToKalaha(player1, stonesWon);
        }

        if (activePlayer.equals(player2) && index > 5) {
            logger.info("collecting all stones from {} putting in {}", (11 - index), index);
            int stonesWon = board.emptyPit(11 - index) + board.emptyPit(index);
            board.addToKalaha(player2, stonesWon);
        }
    }

    /**
     * @return the @{@link Player} object representation for player-1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * @return the @{@link Player} object representation for player-2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * @param activePlayer  @{@link Player} who's introducing change to the board
     */
    private void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    /**
     * @return the @{@link Player} who's introducing change to the board
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * @return @{@link BoardStringUtils} that represents the active player
     */
    public BoardStringUtils getActivePlayerEnum() {
        if (getActivePlayer().equals(player1)) {
            return BoardStringUtils.PLAYER_1;
        } else if (getActivePlayer().equals(player2)) {
            return BoardStringUtils.PLAYER_2;
        } else {
            logger.error("An unexpected error occurred");
            throw new IllegalArgumentException("Unexpected error");
        }
    }

    /**
     * @return @{@link Board}, that is represented by the UI Board component
     */
    public Board getBoard() {
        return board;
    }

    // TODO make enum
    public int getKalahaStones(Player player) {
        return player.getKalaha().getStones();
    }

}
