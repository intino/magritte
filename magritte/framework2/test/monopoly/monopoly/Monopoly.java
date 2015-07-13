package monopoly.monopoly;

import tara.magritte.Node;

import java.util.List;

public class Monopoly {

	public static Node node;
	public static Monopoly model = new Monopoly();
	public static Board board;
	public static Dices dices;
	public static LuckyCards luckyCards;
	public static CommunityCards communityCards;
	public static List<Player> players;

	public static void node(Node node) {
		Monopoly.node = node;
		board = node.components(Board.class).get(0);
		dices = node.components(Dices.class).get(0);
		luckyCards = node.components(LuckyCards.class).get(0);
		communityCards = node.components(CommunityCards.class).get(0);
		players = node.components(Player.class);
	}

	private Monopoly(){}

	public Board board() {
	    return board;
	}

	public Dices dices() {
	    return dices;
	}

	public LuckyCards luckyCards() {
	    return luckyCards;
	}

	public CommunityCards communityCards() {
	    return communityCards;
	}

	public List<Player> players() {
	    return players;
	}

	public Player player(int index) {
	    return players().get(index);
	}

}