package com.ignatius.data.objects;

import com.ignatius.utils.BoardStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * Class that provides a collection of all the kalaha game objects
 * See documentation for more information
 *
 * @author Ignatius de Villiers
 * @since 12 December 2018
 */
public class Board {

    private static Logger logger = LoggerFactory.getLogger(Board.class);

    private Pit[] pits;
    private Kalaha kalahaPlayer1, kalahaPlayer2;
    private Player player1, player2;
    private int amountPits;
    private int amountStones;

    /**
     * A typical kalaha @{@link Board} consists of 12 @{@link Pit}s & 72 stones, each side of the board has 1 @{@link Kalaha} and 6 pits,
     * which is filled with 6 stones each. Each @{@link Player} may only pick up stones from its own pits and add stones to
     * its own kalaha
     *
     * @param amountPits   The amount of pits that should be created on each side of the board
     * @param amountStones The total amount of stones that should be divided amongst all pits on this Side
     */
    @Inject
    public Board(int amountPits, int amountStones, Player p1, Player p2, Kalaha k1, Kalaha k2, Pit[] pits) {
        this.amountPits = amountPits;
        this.amountStones = amountStones;
        this.player1 = p1;
        this.player2 = p2;
        this.kalahaPlayer1 = k1;
        this.kalahaPlayer2 = k2;
        this.pits = pits;
    }

    /**
     * @param pitIndex The index of the @{@link Pit} that should be emptied
     */
    public int emptyPit(int pitIndex) {
        return pits[pitIndex].emptyPit();
    }

    /**
     * @param pitIndex The index of the @{@link Pit} requested pit
     * @return amount of stones in the requested pit
     */
    public int getStonesInPit(int pitIndex) {
        return pits[pitIndex].getStones();
    }

    /**
     * @param pitIndex     The index of the @{@link Pit} that should be edited
     * @param stonesAmount The amount of stones that should be added to the @{@link Pit}
     */
    public void addToPit(int pitIndex, int stonesAmount) {
        pits[pitIndex].receiveStones(stonesAmount);
    }

    /**
     * @param player       The player to which's kalaha we need to add stones
     * @param stonesAmount The amount of stones that needs to be added to the @{@link Player}s @{@link Kalaha}
     */
    public void addToKalaha(Player player, int stonesAmount) {
        player.getKalaha().receiveStones(stonesAmount);
    }


    /**
     * @param player who's @{@link Kalaha} is required to be returned
     * @return the players Kalaha
     */
    public Kalaha getKalaha(Player player) {
        return player.getKalaha();
    }

    /**
     * @return true:    if any player's pits are all empty
     *         false:   if both player's pits aren't all empty
     */
    public boolean endGameCondition() {
        int totalStonesOnSide = 0;

        Pit[] player1Pits = Arrays.copyOf(pits, pits.length / 2);
        for (Pit pit : player1Pits) {
            totalStonesOnSide += pit.getStones();
        }
        logger.info("Total stones = {}", totalStonesOnSide);
        if (totalStonesOnSide == 0) {
            logger.info("{} ended the game!", player1.getPlayerName());
            return true;
        }

        totalStonesOnSide = 0;

        Pit[] player2Pits = Arrays.copyOfRange(pits, 6, pits.length);
        for (Pit pit : player2Pits) {
            totalStonesOnSide += pit.getStones();
        }
        logger.info("Total stones = {}", totalStonesOnSide);
        if (totalStonesOnSide == 0) {
            logger.info("{} ended the game!", player2.getPlayerName());
            return true;
        }
        return false;
    }

    /**
     * Compares the players Kalahas to determine the winner
     *
     * @return the @{@link BoardStringUtils} enum associated with the winning player
     */
    public BoardStringUtils winnigPlayer() {
        if (player1.getKalaha().getStones() > player2.getKalaha().getStones()) {
            // player 1 won
            return BoardStringUtils.PLAYER_1;
        } else if (player1.getKalaha().getStones() == player2.getKalaha().getStones()) {
            // draw
            return BoardStringUtils.TIE;
        } else {
            // player 2 won
            return BoardStringUtils.PLAYER_2;
        }
    }

    /**
     * @param index of the required @{@link Pit}
     * @return the Pit at the specific index
     */
    public Pit getPit(int index) {
        return pits[index];
    }

    /**
     * @return the @{@link Pit} array associated with this board
     */
    public Pit[] getPits() {
        return pits;
    }

    /**
     * Sets the player1 variable to reference a specific @{@link Player} Object
     *
     * @param player the player object to be associated with player1
     */
    public void setPlayer1(Player player) {
        logger.info("Player1 set: {}", player.getPlayerName());
        this.player1 = player;
        this.player1.setKalaha(this.kalahaPlayer1);
    }

    /**
     * Sets the player1 variable to reference a specific @{@link Player} Object
     *
     * @param player the player object to be associated with player2
     */
    public void setPlayer2(Player player) {
        logger.info("Player2 set: {}", player.getPlayerName());
        this.player2 = player;
        this.player2.setKalaha(this.kalahaPlayer2);
    }
}
