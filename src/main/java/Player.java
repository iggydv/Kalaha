public class Player {

    private String playerName;

    /**
     * @param playerName
     */
    public Player(String playerName) {
        this.playerName = playerName;
    }

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
