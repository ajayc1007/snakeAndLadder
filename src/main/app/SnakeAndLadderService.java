package main.app;

import java.util.*;

public class SnakeAndLadderService {
    private final Board board;
    private Queue<Player> players;
    private boolean isGameCompleted;
    private static final int DEFAULT_BOARD_SIZE = 100;
    public SnakeAndLadderService(int size) {
        this.board = new Board(size);
        this.isGameCompleted = false;
    }
    public SnakeAndLadderService() {
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
    }

    public void setSnakes(List<Snake> snakes) {
        board.setSnakes(snakes);
    }
    public void setLadder(List<Ladder> ladders) {
        board.setLadders(ladders);
    }
    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<>(players);
        Map<String, Integer> playerPieces = new HashMap<>();
        this.players.forEach(player -> playerPieces.put(player.getId(), 0));
        this.board.setPlayerPieces(playerPieces);
    }
    private boolean getIsGameCompleted() {
        return isGameCompleted;
    }

    private int getNewPosition(int newPosition) {
        for (Snake snake : this.board.getSnakes()) {
            if (snake.getStart() == newPosition)
                newPosition = snake.getEnd();
        }
        for (Ladder ladder : this.board.getLadders()) {
            if (ladder.getStart() == newPosition)
                newPosition = ladder.getEnd();
        }
        return newPosition;
    }
    private void movePlayer(Player player, int diceValue) {
        int oldPosition = this.board.getPlayerPieces().get(player.getId());
        System.out.println(player.getName() + " rolled a " + diceValue + " and old  " + oldPosition);
        int newPosition = oldPosition +  diceValue;
        int boardSize = this.board.getSize();
        if (newPosition > boardSize)
            newPosition = oldPosition;
        else {
            newPosition = getNewPosition(newPosition);
        }
        System.out.println(player.getName() + " moves to " + newPosition);
        this.board.getPlayerPieces().put(player.getId(), newPosition);
    }

    private boolean hasPlayerWon(Player player) {
        int position = this.board.getPlayerPieces().get(player.getId());
        return position == this.board.getSize();
    }
    public void startGame() {
        while (!getIsGameCompleted()) {
            int diceValue = DiceService.rollDice();
            Player player = players.poll();
            if (player != null) {
                movePlayer(player, diceValue);
                if (hasPlayerWon(player)) {
                    this.isGameCompleted = true;
                    System.out.println("Game won by " + player.getName());
                }
                else {
                    players.offer(player);
                }
            }
        }
    }
}
