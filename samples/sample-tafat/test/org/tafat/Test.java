package org.tafat;

import magritte.ontology.PlayGameMain;
import monopoly.*;
import tafat.Simulation;
import tafat.Tafat;
import tara.magritte.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test {

    @org.junit.Test
    public void checkTypes() throws Exception {
        Node node = new Node();
        PlayGameMain.box.load(node);
        Tafat.use(node);
        Monopoly.use(node);

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
}
