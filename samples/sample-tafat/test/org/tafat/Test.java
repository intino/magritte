package org.tafat;

import magritte.ontology.PlayGameMain;
import monopoly.*;
import org.junit.Before;
import tafat.Simulation;
import tafat.Tafat;
import tara.magritte.Node;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test {
    @Before
    public void setUp() throws Exception {
        Node node = new Node();
        PlayGameMain.box.load(node);
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


        Board board = Monopoly.board();

        Card card = Monopoly.luckyCards().card(0);

        Player player = Monopoly.player(0);
    }

    private LocalDateTime asDate(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
