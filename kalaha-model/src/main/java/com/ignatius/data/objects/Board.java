package com.ignatius.data.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


/**
 * Class that allows a game of kalaha to commence.
 * See documentation
 */
public class Board {

    private static Logger logger = LoggerFactory.getLogger(Board.class);

    @Inject
    private Pit[] pits;
    @Inject
    Kalaha kalahaPlayer1, kalahaPlayer2;
    private Player player1, player2;
    private int amountPits;
    private int amountStones;
    HashMap<Player, HashMap<String, Object>> componentMap = new HashMap<>();

    HashMap<String, Object> side1 = new HashMap<>();
    HashMap<String, Object> side2 = new HashMap<>();

    /**
     * A kalaha @{@link Board} consists of two Sides, each side with its own separate objects, which users
     * may alter. A player may only select pits from his own Side.
     *
     * @param amountPits   The amount of pits that should be created on each side of the board
     * @param amountStones The total amount of stones that should be divided amongst all pits on this Side
     */
    public Board(int amountPits, int amountStones) {
        this.amountPits = amountPits;
        this.amountStones = amountStones;
        pits = new Pit[amountPits];
        initializeBoard();
    }

    /**
     * Initialize all the pits to have they initial stones
     */
    private void initializeBoard() {
        kalahaPlayer1 = new Kalaha();
        kalahaPlayer2 = new Kalaha();
        side1.put("kalaha", kalahaPlayer1);
        side2.put("kalaha", kalahaPlayer2);

        for (int i = 0; i < amountPits; i++) {
            pits[i] = new Pit(amountStones / amountPits, false);
            if (i < amountPits / 2) {
                side1.put("pit" + i, pits[i]);
            } else {
                side2.put("pit" + i, pits[i]);
            }

        }
    }

    /**
     * @param pitIndex The index of the @{@link Pit} that should be emptied
     */
    public void emptyPit(int pitIndex) {
        pits[pitIndex].emptyPit();
    }

    /**
     * @param pitIndex     The index of the @{@link Pit} that should be edited
     * @param stonesAmount The amount of stones that should be added to the @{@link Pit}
     */
    public void addToPit(int pitIndex, int stonesAmount) {
        pits[pitIndex].receive(stonesAmount);
    }

    /**
     * @param stonesAmount The amount of stones that needs to be added to the @{@link Player}s @{@link Kalaha}
     */
    public void addToKalaha(Player player, int stonesAmount) {
        if (player.equals(player1)) {
            kalahaPlayer1.receive(stonesAmount);
        } else if (player.equals(player2)) {
            kalahaPlayer2.receive(stonesAmount);
        }
        System.out.println("pes");
    }

    public Kalaha getKalaha(Player player) {
        if (player.equals(player1)) {
            return kalahaPlayer1;
        } else if (player.equals(player2)) {
            return kalahaPlayer2;
        }
        return null;
    }

    public void setPlayer1(Player player) {
        logger.info("Player1 set: {}", player.getPlayerName());
        this.player1 = player;
    }

    public void setPlayer2(Player player) {
        logger.info("Player2 set: {}", player.getPlayerName());
        this.player2 = player;
    }

    public boolean endGameCondition(Player player) {
        int totalStonesOnSide = 0;
        if (player.equals(player1)) {
            Pit[] p = Arrays.copyOf(pits, 6);
            for (Pit pit: p) {
                totalStonesOnSide += pit.getStones();

            }
            logger.info("Total stones = {}", totalStonesOnSide);
            if (totalStonesOnSide == 0) {
                System.out.println("player1 won!");
                logger.info("{} ended the game!", player1.getPlayerName());
                return true;
            }
        }

        if (player.equals(player2)) {
            Pit[] p = Arrays.copyOfRange(pits, 6, 11);
            for (Pit pit: p) {
                totalStonesOnSide += pit.getStones();
            }
            logger.info("Total stones = {}", totalStonesOnSide);
            if (totalStonesOnSide == 0) {
                System.out.println("player2 won!");
                logger.info("{} won!", player2.getPlayerName());
                return true;
            }
        }
        return false;
    }

    public int winnigPlayer() {
        if (kalahaPlayer1.getStones() > kalahaPlayer2.getStones()) {
            // player 1 won
            return 1;
        }
        if (kalahaPlayer1.getStones() < kalahaPlayer2.getStones()) {
            // player 2 won
            return 2;
        } else {
            // draw
            return 0;
        }

    }

    public Player getPlayer1(Player player1) {
        return this.player1;
    }

    public Player getPlayer2(Player player2) {
        return this.player2;
    }

    public Pit getPit(int index) {
        return pits[index];
    }

    public Pit[] getPits() {
        return pits;
    }

    /**
     * Exit the game
     */
    public void destroy() {
        System.out.println("Bye");
        System.exit(0);
    }

}
