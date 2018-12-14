package com.ignatius.data.objects;

public class Pit {
    private int stones;
    private boolean isKalaha;

    /**
     * A @{@link Side} Object consists of multiple Pit objects, which are editable by the @{@link Player} associated
     * with that side
     *
     * @param stones The initial amount of stones in the pit
     */
    public Pit(int stones, boolean isKalaha) {
        this.stones = stones;
        this.isKalaha = isKalaha;
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
     * Allows a pit to receive a certain amount of stones
     * @param amountStones The amount of stones to be added to the specific pit
     */
    public void receive(int amountStones) {
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

    public boolean isKalaha() {
        return isKalaha;
    }

    public void setKalaha(boolean kalaha) {
        isKalaha = kalaha;
    }
}
