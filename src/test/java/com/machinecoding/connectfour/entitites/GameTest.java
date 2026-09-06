package com.machinecoding.connectfour.entitites;

import com.machinecoding.connectfour.enums.DiscColor;
import com.machinecoding.connectfour.enums.GameState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    @Test
    void shouldDeclareHorizontalWinForFirstPlayer() {
        Player ram = new Player(DiscColor.RED, "Ram");
        Player shyam = new Player(DiscColor.BLACK, "Shyam");
        Game game = new Game(ram, shyam, 4, 4);

        assertTrue(game.makeMove(ram, 0));
        assertTrue(game.makeMove(shyam, 0));
        assertTrue(game.makeMove(ram, 1));
        assertTrue(game.makeMove(shyam, 1));
        assertTrue(game.makeMove(ram, 2));
        assertTrue(game.makeMove(shyam, 2));
        assertTrue(game.makeMove(ram, 3));

        assertEquals(GameState.WON, game.getState());
        assertSame(ram, game.getWinner());
    }
}
