//package com.ignatius.data.objects;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//
//@RestController
//public class KalahaController {
//    private static Logger logger = LoggerFactory.getLogger(KalahaController.class);
//    private static Board board;
//    private static Player player1, player2;
//
//    @RequestMapping("/greeting")
//    public String greeting() {
//        return "Welcome to Kalaha";
//    }
//
//    @RequestMapping("/start_up")
//    public String startUp() {
//        logger.info("Starting UI");
//        board = new Board(12,72);
//        return "Board built";
//    }
//
//    @RequestMapping("/register_players")
//    public String registerPlayers() {
//        logger.info("Registering players");
////        Scanner scanner = new Scanner(System.in);
////        System.out.println("Enter player-1 name:");
////        String player1Name= scanner.nextLine();
////
////        System.out.println("Enter player-2 name:");
////        String player2Name = scanner.nextLine();
//
//        player1 = new Player("Clara");
//        player2 = new Player("Ignatius");
//
//        HashMap<Player, Side> playerMap = new HashMap<Player, Side>();
//        playerMap.put(player1, board.getFirstPlayerSide());
//        playerMap.put(player2, board.getSecondPlayerSide());
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        return gson.toJson(playerMap);
//    }
//
//    @RequestMapping("/player_turn")
//    public String playerTurn() {
//        logger.info("Player {} turn", player1.getPlayerName());
//        return "Welcome to Kalaha";
//    }
//
//
//    @RequestMapping("/end_game")
//    public String endGame() {
//        logger.info("Game over");
//        return "Exiting Kalaha";
//    }
//
//    @RequestMapping("/reset")
//    public String reset() {
//        logger.info("Resetting");
//        return "Welcome to Kalaha";
//    }
//
//    @RequestMapping("/quit")
//    public void quit() {
//        logger.info("Exiting kalaha!");
//        System.exit(0);
//    }
//}
