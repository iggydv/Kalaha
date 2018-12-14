package com.ignatius.data.objects;

public class Player {

    private String playerName;

    /**
     * @param playerName
     */
    public Player(String playerName) {
        this.playerName = playerName;
    }

//    public Player() {
//
//    }

    /**
     * @return
     */
    public int pickPit() {
        return 0;
    }

    /**
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
