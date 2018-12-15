package com.ignatius.data.objects;


import javax.inject.Inject;

/**
 * @author Ignatius de Villiers
 * @since 12 December 2018
 */
public class Player {

    private String playerName;
    private Kalaha kalaha;

    /**
     * @param playerName
     */
    public Player(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @param kalaha receiveStones a reference of the kalaha the player will be associated with
     */
    public void setKalaha(Kalaha kalaha) {
        this.kalaha = kalaha;
    }

    /**
     * @return @{@link Kalaha} that is associated with this player
     */
    public Kalaha getKalaha() {
        return this.kalaha;
    }

    /**
     * @return this Player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName set the player name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
