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
        assertEquals(1, simulation._node().types().size());
        assertTrue(simulation._node().types().contains("Simulation"));

        Dices dices = Monopoly.dices();
        assertEquals(2, dices._node().types().size());
        assertTrue(dices._node().types().contains("Entity"));
        assertTrue(dices._node().types().contains("Dices"));

        Board board = Monopoly.board();
        assertEquals(2, board._node().types().size());
        assertTrue(board._node().types().contains("Entity"));
        assertTrue(board._node().types().contains("Board"));
        assertEquals(3, board.square(0)._node().types().size());
        assertTrue(board.square(0)._node().types().contains("Entity"));
        assertTrue(board.square(0)._node().types().contains("Square"));
        assertTrue(board.square(0)._node().types().contains("Init"));

        Card card = Monopoly.luckyCards().card(0);
        assertEquals(2, card._node().types().size());
        assertTrue(card._node().types().contains("Entity"));
        assertTrue(card._node().types().contains("Card"));

        Player player = Monopoly.player(0);
        assertEquals(6, player._node().types().size());
        assertTrue(player._node().types().contains("Player"));
        assertTrue(player._node().types().contains("Entity"));
        assertTrue(player._node().types().contains("Mover_Player"));
        assertTrue(player._node().types().contains("Mover"));
        assertTrue(player._node().types().contains("Behavior"));
        assertTrue(player._node().types().contains("Facet"));
    }

    @org.junit.Test
    public void checkVariables() throws Exception {
        Simulation simulation = Tafat.simulation();
        assertEquals(2, simulation._node().variables().size());
        assertEquals(asDate("01/01/2015 00:00:00"), simulation._node().variables().get("from"));
        assertEquals(asDate("02/01/2015 00:00:00"), simulation._node().variables().get("to"));

        Dices dices = Monopoly.dices();
        assertEquals(5, dices._node().variables().size());
        assertEquals(0, dices._node().variables().get("value1"));
        assertEquals(0, dices._node().variables().get("value2"));
        assertTrue(dices._node().variables().get("roll") instanceof NativeCode);
        assertEquals("roll_meme", dices._node().variables().get("roll").getClass().getSimpleName());
        assertTrue(dices._node().variables().get("doubles") instanceof NativeCode);
        assertEquals("doubles_meme", dices._node().variables().get("doubles").getClass().getSimpleName());
        assertTrue(dices._node().variables().get("doubles") instanceof NativeCode);
        assertEquals("doubles_meme", dices._node().variables().get("doubles").getClass().getSimpleName());

        Board board = Monopoly.board();
        assertEquals(3, board._node().variables().size());
        assertTrue(board._node().variables().get("squareAt") instanceof NativeCode);
        assertEquals("SquareAt_meme", board._node().variables().get("squareAt").getClass().getSimpleName());
        assertTrue(board._node().variables().get("squareOf") instanceof NativeCode);
        assertEquals("SquareOf_meme", board._node().variables().get("squareOf").getClass().getSimpleName());
        assertTrue(board._node().variables().get("positionOf") instanceof NativeCode);
        assertEquals("Position_meme", board._node().variables().get("positionOf").getClass().getSimpleName());

        Card card = Monopoly.luckyCards().card(0);
        assertEquals(2, card._node().variables().size());
        assertEquals(-1000, card._node().variables().get("moveTo"));
        assertTrue(card._node().variables().get("transport") instanceof NativeCode);
        assertEquals("Transport_meme", card._node().variables().get("transport").getClass().getSimpleName());

        Player player = Monopoly.player(0);
        assertEquals(5, player._node().variables().size());
        assertEquals("p0", player._node().variables().get("id"));
        assertEquals(null, player._node().variables().get("square"));
        assertEquals(null, player._node().variables().get("modes"));
        assertTrue(card._node().variables().get("transport") instanceof NativeCode);
        assertEquals("Transport_meme", card._node().variables().get("transport").getClass().getSimpleName());
    }

    @org.junit.Test
    public void checkComponents() throws Exception {
        Simulation simulation = Tafat.simulation();
        assertEquals(0, simulation._node().components().size());

        Dices dices = Monopoly.dices();
        assertEquals(0, dices._node().components().size());

        Board board = Monopoly.board();
        assertEquals(40, board._node().components().size());
        for (Node node : board._node().components()) assertTrue(node.is("Square"));

        Card card = Monopoly.luckyCards().card(0);
        assertEquals(0, card._node().components().size());

        Player player = Monopoly.player(0);
        assertEquals(8, player._node().components().size());
        assertTrue(player._node().components().get(0).is("Behavior$Start"));
        assertTrue(player._node().components().get(1).is("Action"));
        for (int i = 2; i < 8; i++) assertTrue(player._node().components().get(i).is("Behavior$Knol"));
    }

    private LocalDateTime asDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
