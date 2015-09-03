package org.tafat;


public class Test {
//    @Before
//    public void setUp() throws Exception {
//        Node node = new Node();
//        new PlayGameMain().load(node);
//        Tafat.use(node);
//        Monopoly.use(node);
//    }
//
//    @org.junit.Test
//    public void checkTypes() throws Exception {
//        Simulation simulation = Tafat.simulation();
//        assertEquals(1, simulation._instance().types().size());
//        assertTrue(simulation._instance().types().contains("Simulation"));
//
//        Dices dices = Monopoly.dices();
//        assertEquals(2, dices._instance().types().size());
//        assertTrue(dices._instance().types().contains("Entity"));
//        assertTrue(dices._instance().types().contains("Dices"));
//
//        Board board = Monopoly.board();
//        assertEquals(2, board._instance().types().size());
//        assertTrue(board._instance().types().contains("Entity"));
//        assertTrue(board._instance().types().contains("Board"));
//        assertEquals(3, board.square(0)._instance().types().size());
//        assertTrue(board.square(0)._instance().types().contains("Entity"));
//        assertTrue(board.square(0)._instance().types().contains("Square"));
//        assertTrue(board.square(0)._instance().types().contains("Init"));
//
//        Card card = Monopoly.luckyCards().card(0);
//        assertEquals(2, card._instance().types().size());
//        assertTrue(card._instance().types().contains("Entity"));
//        assertTrue(card._instance().types().contains("Card"));
//
//        Player player = Monopoly.player(0);
//        assertEquals(6, player._instance().types().size());
//        assertTrue(player._instance().types().contains("Player"));
//        assertTrue(player._instance().types().contains("Entity"));
//        assertTrue(player._instance().types().contains("Mover_Player"));
//        assertTrue(player._instance().types().contains("Mover"));
//        assertTrue(player._instance().types().contains("Behavior"));
//        assertTrue(player._instance().types().contains("Facet"));
//    }
//
//    @org.junit.Test
//    public void checkVariables() throws Exception {
//        Simulation simulation = Tafat.simulation();
//        assertEquals(2, simulation._instance().variables().size());
//        assertEquals(asDate("01/01/2015 00:00:00"), simulation._instance().variables().get("from"));
//        assertEquals(asDate("02/01/2015 00:00:00"), simulation._instance().variables().get("to"));
//
//        Dices dices = Monopoly.dices();
//        assertEquals(5, dices._instance().variables().size());
//        assertEquals(0, dices._instance().variables().get("value1"));
//        assertEquals(0, dices._instance().variables().get("value2"));
//        assertTrue(dices._instance().variables().get("roll") instanceof NativeCode);
//        assertEquals("roll_meme", dices._instance().variables().get("roll").getClass().getSimpleName());
//        assertTrue(dices._instance().variables().get("doubles") instanceof NativeCode);
//        assertEquals("doubles_meme", dices._instance().variables().get("doubles").getClass().getSimpleName());
//        assertTrue(dices._instance().variables().get("doubles") instanceof NativeCode);
//        assertEquals("doubles_meme", dices._instance().variables().get("doubles").getClass().getSimpleName());
//
//        Board board = Monopoly.board();
//        assertEquals(3, board._instance().variables().size());
//        assertTrue(board._instance().variables().get("squareAt") instanceof NativeCode);
//        assertEquals("SquareAt_meme", board._instance().variables().get("squareAt").getClass().getSimpleName());
//        assertTrue(board._instance().variables().get("squareOf") instanceof NativeCode);
//        assertEquals("SquareOf_meme", board._instance().variables().get("squareOf").getClass().getSimpleName());
//        assertTrue(board._instance().variables().get("positionOf") instanceof NativeCode);
//        assertEquals("Position_meme", board._instance().variables().get("positionOf").getClass().getSimpleName());
//
//        Card card = Monopoly.luckyCards().card(0);
//        assertEquals(2, card._instance().variables().size());
//        assertEquals(-1000, card._instance().variables().get("moveTo"));
//        assertTrue(card._instance().variables().get("transport") instanceof NativeCode);
//        assertEquals("Transport_meme", card._instance().variables().get("transport").getClass().getSimpleName());
//
//        Player player = Monopoly.player(0);
//        assertEquals(5, player._instance().variables().size());
//        assertEquals("p0", player._instance().variables().get("id"));
//        assertEquals(null, player._instance().variables().get("square"));
//        assertEquals(null, player._instance().variables().get("modes"));
//        assertTrue(card._instance().variables().get("transport") instanceof NativeCode);
//        assertEquals("Transport_meme", card._instance().variables().get("transport").getClass().getSimpleName());
//    }
//
//    @org.junit.Test
//    public void checkComponents() throws Exception {
//        Simulation simulation = Tafat.simulation();
//        assertEquals(0, simulation._instance().components().size());
//
//        Dices dices = Monopoly.dices();
//        assertEquals(0, dices._instance().components().size());
//
//        Board board = Monopoly.board();
//        assertEquals(40, board._instance().components().size());
//        for (Node node : board._instance().components()) assertTrue(node.is("Square"));
//
//        Card card = Monopoly.luckyCards().card(0);
//        assertEquals(0, card._instance().components().size());
//
//        Player player = Monopoly.player(0);
//        assertEquals(8, player._instance().components().size());
//        assertTrue(player._instance().components().get(0).is("Behavior$Start"));
//        assertTrue(player._instance().components().get(1).is("Action"));
//        for (int i = 2; i < 8; i++) assertTrue(player._instance().components().get(i).is("Behavior$Knol"));
//    }
//
//    private LocalDateTime asDate(String date) {
//        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
//    }
}
