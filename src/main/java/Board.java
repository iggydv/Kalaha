import javax.inject.Inject;


/**
 * Class that allows a game of Kalaha to commence.
 * See documentation
 *
 */
public class Board {

    @Inject private Side A, B;

    /**
     * The @{@link Board} class provides the UI and backend components to play a game of Kalaha
     *
     * @param amountPits The amount of pits that should be created the board
     * @param amountStones The total amount of stones that should be divided amongst all pits
     */
    @Inject
    public Board(int amountPits, int amountStones) {
        A = new Side(amountPits/2, amountStones/amountPits);
        B = new Side(amountPits/2, amountStones/amountPits);
        System.out.println("Magical");
    }

    /**
     * Player-1's side is enabled: the player can now select any @{@link Pit} from his @{@link Side}
     */
    public void Player1Turn() {

    }

    /**
     * Player-2's side is enabled: the player can now select any @{@link Pit} from his @{@link Side}
     */
    public void Player2Turn() {

    }

    /**
     * Exit the game
     */
    public void destroy() {
        System.out.println("Bye");
        System.exit(0);
    }
}
