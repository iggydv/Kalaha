package com.ignatius.service.board;

import com.ignatius.data.objects.Board;
import com.ignatius.data.objects.Player;
import com.ignatius.data.objects.Side;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.logging.Logger;

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
    HashMap<String, Side> playerSideMap = new HashMap<>();

    public BoardService() {
        board = new Board(12, 72);
    }

    public boolean assignPlayerNames(String playerName) {
        if (player1 == null) {
            logger.info("{} is Player-1", playerName);
            player1 = new Player(playerName);
            playerSideMap.put(player1.getPlayerName(), board.getFirstPlayerSide());
            return true;
        } else if (player2 == null) {
            player2 = new Player(playerName);
            logger.info("{} is Player-2", playerName);
            playerSideMap.put(player2.getPlayerName(), board.getSecondPlayerSide());
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
        return true;
    }

    public String playerTurn(int player) {
        switch (player) {
            case 0: return player1.getPlayerName();
            case 1: return player2.getPlayerName();
            default: throw new IllegalArgumentException();
        }
    }

    public void play(String playerName, int pitIndex) {
        logger.info("{} Clicked on pit-{}", playerName, pitIndex);
        if (playerSideMap.containsKey(playerName)) {
            Side playerSide = playerSideMap.get(playerName);

            int stonesInHand = playerSide.getPit(pitIndex).emptyPit();
            logger.info("Stones in hand: {}", stonesInHand);
            int loopIndex = pitIndex + 1;
            while (loopIndex < playerSide.getPits().length && stonesInHand != 0) {
                if (loopIndex == playerSide.getPits().length) {
                    logger.info("Adding to Kalaha");
                    playerSide.addToKalaha(1);
                } else {
                    logger.info("Adding to pit-{}", loopIndex);
                    playerSide.addToPit(loopIndex, 1);
                }
                loopIndex++;
                stonesInHand--;
            }
            logger.info("Stones in hand: {}", stonesInHand);
        } else {
            logger.info("Player {} not found", playerName);
        }

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



}
