package com.ignatius.data.objects;

/**
 * Each user is assigned a player name at the start of the game,
 * which is used to keep track of the amount of stones a certain player has in their @{@link Kalaha}
 *
 * @author Ignatius de Villiers
 * @since 12 December 2018
 */
public class Player {

    private String playerName;
    private Kalaha kalaha;

    /**
     * @param playerName the name associated with a certain player
     */
    public Player(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @param kalaha a reference of the kalaha the player will be associated with
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
