package com.ignatius.data.objects;

import javax.inject.Inject;


/**
 * Class that allows a game of kalaha to commence.
 * See documentation
 *
 */
public class Board {

    @Inject private Side firstPlayerSide, secondPlayerSide;

    /**
     * The @{@link Board} class provides the UI and backend components to play a game of kalaha
     *
     * @param amountPits The amount of pits that should be created the board
     * @param amountStones The total amount of stones that should be divided amongst all pits
     */
    public Board(int amountPits, int amountStones) {
        firstPlayerSide = new Side(amountPits/2, amountStones/(amountPits));
        secondPlayerSide = new Side(amountPits/2, amountStones/(amountPits));
        System.out.println("Magical");
    }

    /**
     * Exit the game
     */
    public void destroy() {
        System.out.println("Bye");
        System.exit(0);
    }

    public Side getFirstPlayerSide() {
        return firstPlayerSide;
    }

    public void setFirstPlayerSide(Side firstPlayerSide) {
        this.firstPlayerSide = firstPlayerSide;
    }

    public Side getSecondPlayerSide() {
        return secondPlayerSide;
    }

    public void setSecondPlayerSide(Side secondPlayerSide) {
        this.secondPlayerSide = secondPlayerSide;
    }

}
