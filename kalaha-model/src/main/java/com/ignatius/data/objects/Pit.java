package com.ignatius.data.objects;

public class Pit {
    private int stones;

    /**
     * A @{@link Board} Object consists of multiple Pit objects, which are editable by the @{@link Player} associated
     * with that side
     *
     * @param stones The initial amount of stones in the pit
     */
    public Pit(int stones) {
        this.stones = stones;
    }

    /**
     * Sets the amount of stones in the pit to zero
     */
    public int emptyPit() {
        int stonesInHand = getStones();
        stones = 0;
        return stonesInHand;
    }

    /**
     * Allows a pit to receiveStones a certain amount of stones
     * @param amountStones The amount of stones to be added to the specific pit
     */
    public void receiveStones(int amountStones) {
        stones += amountStones;
    }

    /**
     * @return The amount of stones allocated to a pit
     */
    public int getStones() {
        return stones;
    }

    /**
     * @param stones Hard set the amount of stones to a certain amount
     */
    public void setStones(int stones) {
        this.stones = stones;
    }
}
