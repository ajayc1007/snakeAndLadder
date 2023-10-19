package main.app;

import java.util.ArrayList;
import java.util.List;

public class MainGame {
    public static void main(String[] args) {
        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(20, 15));
        snakes.add(new Snake(80, 75));
        snakes.add(new Snake(60, 25));

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(9, 30));
        ladders.add(new Ladder(35, 90));
        ladders.add(new Ladder(45, 50));

        List<Player> players = new ArrayList<>();
        players.add(new Player("A"));
        players.add(new Player("B"));
        players.add(new Player("C"));

        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setLadder(ladders);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.startGame();
    }
}
