package com.ignatius.data.objects;

import javax.inject.Inject;

/**
 * Class that allows contains half of every boards intractable objects
 * See documentation for more details
 *
 */
public class Side {

    @Inject private Pit[] pits;
    @Inject Kalaha kalaha;
    private Player player;
    private int amountPits;
    private int amountStones;

    /**
     * A kalaha @{@link Board} consists of two Sides, each side with its own separate objects, which users
     * may alter. A player may only select pits from his own Side.
     *
     *
     * @param amountPits The amount of pits that should be created on each side of the board
     * @param amountStones The total amount of stones that should be divided amongst all pits on this Side
     */
    public Side(int amountPits, int amountStones) {
        this.amountPits = amountPits;
        this.amountStones = amountStones;
        pits = new Pit[amountPits];
        this.player = player;
        initializePits();
    }

    /**
     * Initialize all the pits to have they initial stones
     */
    private void initializePits() {
        kalaha = new Kalaha();
        for (int i=0; i < amountPits; i++) {
            pits[i] = new Pit(amountStones);
        }
    }

    /**
     * Sets the @{@link Player}s Side to be editable
     *
     */
    public void enableSide() {

    }

    /**
     * Sets the @{@link Player}s Side to be uneditable
     *
     */
    public void disableSide() {

    }


    /**
     * When turn is called, it is the @{@link Player} associated with this com.boldotcom.assessment.Side's turn to play
     */
    public void turn() {
        enableSide();
        player.pickPit();
        disableSide();
        // sets it's playing field enabled
        // Players turn to make a few moves
    }

    /**
     * @param pitIndex The index of the @{@link Pit} that should be emptied
     */
    public void emptyPit(int pitIndex) {
        pits[pitIndex].emptyPit();
    }

    /**
     * @param pitIndex The index of the @{@link Pit} that should be edited
     * @param stonesAmount The amount of stones that should be added to the @{@link Pit}
     */
    public void addToPit(int pitIndex, int stonesAmount) {
        pits[pitIndex].receive(stonesAmount);
    }

    /**
     * @param stonesAmount The amount of stones that needs to be added to the @{@link Player}s @{@link Kalaha}
     */
    public void addToKalaha(int stonesAmount) {
        kalaha.receive(stonesAmount);
    }

    public Player getPlayer() {
        return player;
    }

    public Pit getPit(int index) {
        return pits[index];
    }

    public Pit[] getPits() {
        return pits;
    }

    public void setPits(Pit[] pits) {
        this.pits = pits;
    }
}
