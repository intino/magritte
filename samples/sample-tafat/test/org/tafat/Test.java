package org.tafat;


public class Test {
//    @Before
//    public void setUp() throws Exception {
//        Node node = new Node();
//        new PlayGameMain().read(node);
//        Tafat.use(node);
//        Monopoly.use(node);
//    }
//
//    @org.junit.Test
//    public void checkTypes() throws Exception {
//        Simulation simulation = Tafat.simulation();
//        assertEquals(1, simulation._declaration().types().size());
//        assertTrue(simulation._declaration().types().contains("Simulation"));
//
//        Dices dices = Monopoly.dices();
//        assertEquals(2, dices._declaration().types().size());
//        assertTrue(dices._declaration().types().contains("Entity"));
//        assertTrue(dices._declaration().types().contains("Dices"));
//
//        Board board = Monopoly.board();
//        assertEquals(2, board._declaration().types().size());
//        assertTrue(board._declaration().types().contains("Entity"));
//        assertTrue(board._declaration().types().contains("Board"));
//        assertEquals(3, board.square(0)._declaration().types().size());
//        assertTrue(board.square(0)._declaration().types().contains("Entity"));
//        assertTrue(board.square(0)._declaration().types().contains("Square"));
//        assertTrue(board.square(0)._declaration().types().contains("Init"));
//
//        Card card = Monopoly.luckyCards().card(0);
//        assertEquals(2, card._declaration().types().size());
//        assertTrue(card._declaration().types().contains("Entity"));
//        assertTrue(card._declaration().types().contains("Card"));
//
//        Player player = Monopoly.player(0);
//        assertEquals(6, player._declaration().types().size());
//        assertTrue(player._declaration().types().contains("Player"));
//        assertTrue(player._declaration().types().contains("Entity"));
//        assertTrue(player._declaration().types().contains("Mover_Player"));
//        assertTrue(player._declaration().types().contains("Mover"));
//        assertTrue(player._declaration().types().contains("Behavior"));
//        assertTrue(player._declaration().types().contains("Morph"));
//    }
//
//    @org.junit.Test
//    public void checkVariables() throws Exception {
//        Simulation simulation = Tafat.simulation();
//        assertEquals(2, simulation._declaration().variables().size());
//        assertEquals(asDate("01/01/2015 00:00:00"), simulation._declaration().variables().get("from"));
//        assertEquals(asDate("02/01/2015 00:00:00"), simulation._declaration().variables().get("to"));
//
//        Dices dices = Monopoly.dices();
//        assertEquals(5, dices._declaration().variables().size());
//        assertEquals(0, dices._declaration().variables().get("value1"));
//        assertEquals(0, dices._declaration().variables().get("value2"));
//        assertTrue(dices._declaration().variables().get("roll") instanceof NativeCode);
//        assertEquals("roll_meme", dices._declaration().variables().get("roll").getClass().getSimpleName());
//        assertTrue(dices._declaration().variables().get("doubles") instanceof NativeCode);
//        assertEquals("doubles_meme", dices._declaration().variables().get("doubles").getClass().getSimpleName());
//        assertTrue(dices._declaration().variables().get("doubles") instanceof NativeCode);
//        assertEquals("doubles_meme", dices._declaration().variables().get("doubles").getClass().getSimpleName());
//
//        Board board = Monopoly.board();
//        assertEquals(3, board._declaration().variables().size());
//        assertTrue(board._declaration().variables().get("squareAt") instanceof NativeCode);
//        assertEquals("SquareAt_meme", board._declaration().variables().get("squareAt").getClass().getSimpleName());
//        assertTrue(board._declaration().variables().get("squareOf") instanceof NativeCode);
//        assertEquals("SquareOf_meme", board._declaration().variables().get("squareOf").getClass().getSimpleName());
//        assertTrue(board._declaration().variables().get("positionOf") instanceof NativeCode);
//        assertEquals("Position_meme", board._declaration().variables().get("positionOf").getClass().getSimpleName());
//
//        Card card = Monopoly.luckyCards().card(0);
//        assertEquals(2, card._declaration().variables().size());
//        assertEquals(-1000, card._declaration().variables().get("moveTo"));
//        assertTrue(card._declaration().variables().get("transport") instanceof NativeCode);
//        assertEquals("Transport_meme", card._declaration().variables().get("transport").getClass().getSimpleName());
//
//        Player player = Monopoly.player(0);
//        assertEquals(5, player._declaration().variables().size());
//        assertEquals("p0", player._declaration().variables().get("id"));
//        assertEquals(null, player._declaration().variables().get("square"));
//        assertEquals(null, player._declaration().variables().get("modes"));
//        assertTrue(card._declaration().variables().get("transport") instanceof NativeCode);
//        assertEquals("Transport_meme", card._declaration().variables().get("transport").getClass().getSimpleName());
//    }
//
//    @org.junit.Test
//    public void checkComponents() throws Exception {
//        Simulation simulation = Tafat.simulation();
//        assertEquals(0, simulation._declaration().components().size());
//
//        Dices dices = Monopoly.dices();
//        assertEquals(0, dices._declaration().components().size());
//
//        Board board = Monopoly.board();
//        assertEquals(40, board._declaration().components().size());
//        for (Node node : board._declaration().components()) assertTrue(node.is("Square"));
//
//        Card card = Monopoly.luckyCards().card(0);
//        assertEquals(0, card._declaration().components().size());
//
//        Player player = Monopoly.player(0);
//        assertEquals(8, player._declaration().components().size());
//        assertTrue(player._declaration().components().get(0).is("Behavior$Start"));
//        assertTrue(player._declaration().components().get(1).is("Action"));
//        for (int i = 2; i < 8; i++) assertTrue(player._declaration().components().get(i).is("Behavior$Knol"));
//    }
//
//    private LocalDateTime asDate(String date) {
//        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
//    }
}
