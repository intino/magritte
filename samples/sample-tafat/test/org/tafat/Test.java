package org.tafat;

import magritte.ontology.m0.PlayGameMain;
import monopoly.*;
import org.junit.Before;
import tafat.Simulation;
import tafat.Tafat;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test {
    @Before
    public void setUp() throws Exception {
        Node node = new Node();
        new PlayGameMain().load(node);
        Tafat.use(node);
        Monopoly.use(node);
    }

    @org.junit.Test
    public void checkTypes() throws Exception {
        Simulation simulation = Tafat.simulation();
        assertEquals(1, simulation.node().types().size());
        assertTrue(simulation.node().types().contains("Simulation"));

        Dices dices = Monopoly.dices();
        assertEquals(2, dices.node().types().size());
        assertTrue(dices.node().types().contains("Entity"));
        assertTrue(dices.node().types().contains("Dices"));

        Board board = Monopoly.board();
        assertEquals(2, board.node().types().size());
        assertTrue(board.node().types().contains("Entity"));
        assertTrue(board.node().types().contains("Board"));
        assertEquals(3, board.square(0).node().types().size());
        assertTrue(board.square(0).node().types().contains("Entity"));
        assertTrue(board.square(0).node().types().contains("Square"));
        assertTrue(board.square(0).node().types().contains("Init"));

        Card card = Monopoly.luckyCards().card(0);
        assertEquals(2, card.node().types().size());
        assertTrue(card.node().types().contains("Entity"));
        assertTrue(card.node().types().contains("Card"));

        Player player = Monopoly.player(0);
        assertEquals(6, player.node().types().size());
        assertTrue(player.node().types().contains("Player"));
        assertTrue(player.node().types().contains("Entity"));
        assertTrue(player.node().types().contains("Mover_Player"));
        assertTrue(player.node().types().contains("Mover"));
        assertTrue(player.node().types().contains("Behavior"));
        assertTrue(player.node().types().contains("Facet"));
    }

    @org.junit.Test
    public void checkVariables() throws Exception {
        Simulation simulation = Tafat.simulation();
        assertEquals(2, simulation.node().variables().size());
        assertEquals(asDate("01/01/2015 00:00:00"), simulation.node().variables().get("from"));
        assertEquals(asDate("02/01/2015 00:00:00"), simulation.node().variables().get("to"));

        Dices dices = Monopoly.dices();
        assertEquals(5, dices.node().variables().size());
        assertEquals(0, dices.node().variables().get("value1"));
        assertEquals(0, dices.node().variables().get("value2"));
        assertTrue(dices.node().variables().get("roll") instanceof NativeCode);
        assertEquals("roll_meme", dices.node().variables().get("roll").getClass().getSimpleName());
        assertTrue(dices.node().variables().get("doubles") instanceof NativeCode);
        assertEquals("doubles_meme", dices.node().variables().get("doubles").getClass().getSimpleName());
        assertTrue(dices.node().variables().get("doubles") instanceof NativeCode);
        assertEquals("doubles_meme", dices.node().variables().get("doubles").getClass().getSimpleName());

        Board board = Monopoly.board();
        assertEquals(3, board.node().variables().size());
        assertTrue(board.node().variables().get("squareAt") instanceof NativeCode);
        assertEquals("SquareAt_meme", board.node().variables().get("squareAt").getClass().getSimpleName());
        assertTrue(board.node().variables().get("squareOf") instanceof NativeCode);
        assertEquals("SquareOf_meme", board.node().variables().get("squareOf").getClass().getSimpleName());
        assertTrue(board.node().variables().get("positionOf") instanceof NativeCode);
        assertEquals("Position_meme", board.node().variables().get("positionOf").getClass().getSimpleName());

        Card card = Monopoly.luckyCards().card(0);
        assertEquals(2, card.node().variables().size());
        assertEquals(-1000, card.node().variables().get("moveTo"));
        assertTrue(card.node().variables().get("transport") instanceof NativeCode);
        assertEquals("Transport_meme", card.node().variables().get("transport").getClass().getSimpleName());

        Player player = Monopoly.player(0);
        assertEquals(5, player.node().variables().size());
        assertEquals("p0", player.node().variables().get("id"));
        assertEquals(null, player.node().variables().get("square"));
        assertEquals(null, player.node().variables().get("modes"));
        assertTrue(card.node().variables().get("transport") instanceof NativeCode);
        assertEquals("Transport_meme", card.node().variables().get("transport").getClass().getSimpleName());
    }

    @org.junit.Test
    public void checkComponents() throws Exception {
        Simulation simulation = Tafat.simulation();
        assertEquals(0, simulation.node().components().size());

        Dices dices = Monopoly.dices();
        assertEquals(0, dices.node().components().size());

        Board board = Monopoly.board();
        assertEquals(40, board.node().components().size());
        for (Node node : board.node().components()) assertTrue(node.is("Square"));

        Card card = Monopoly.luckyCards().card(0);
        assertEquals(0, card.node().components().size());

        Player player = Monopoly.player(0);
        assertEquals(8, player.node().components().size());
        assertTrue(player.node().components().get(0).is("Behavior$Start"));
        assertTrue(player.node().components().get(1).is("Action"));
        for (int i = 2; i < 8; i++) assertTrue(player.node().components().get(i).is("Behavior$Knol"));
    }

    private LocalDateTime asDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
