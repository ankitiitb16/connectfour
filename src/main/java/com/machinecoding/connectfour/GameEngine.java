package com.machinecoding.connectfour;

import com.machinecoding.connectfour.entitites.Game;
import com.machinecoding.connectfour.entitites.Player;
import com.machinecoding.connectfour.enums.DiscColor;
import com.machinecoding.connectfour.enums.GameState;

import java.util.Scanner;

public class GameEngine {
    public static void main() {
        Player player1 = new Player(DiscColor.RED, "Ram");
        Player player2 = new Player( DiscColor.BLACK, "Shyam");
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(player1, player2, 4, 2);
        while (game.getState() == GameState.IN_PROGRESS) {
            Player currentPlayer = game.getCurrentPlayer();

            System.out.print(currentPlayer.getName() + ", enter column: ");

            int column = scanner.nextInt();

            boolean success = game.makeMove(currentPlayer, column);

            if (!success) {
                System.out.println("Invalid move. Try again.");
            }

            System.out.println(game.getBoard());
        }

        System.out.println(game.getState());

    }
}
