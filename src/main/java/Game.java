import java.util.Scanner;

public class Game {
    /*
     * Purely used for testing at the moment
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player-1 name:");
        String player1Name= scanner.nextLine();

        System.out.println("Enter player-2 name:");
        String player2Name = scanner.nextLine();

        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);

        Board board = new Board(6,72);

        board.destroy();
    }
}
