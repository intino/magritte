package monopoly;

import tara.magritte.Node;

import java.util.List;

public class Monopoly {

	private static Board board;
	private static Dices dices;
	private static LuckyCards luckyCards;
	private static CommunityCards communityCards;
	private static List<Player> playerList;

	public static void use(Node node) {
		board = node.components(Board.class).get(0);
		dices = node.components(Dices.class).get(0);
		luckyCards = node.components(LuckyCards.class).get(0);
		communityCards = node.components(CommunityCards.class).get(0);
		playerList = node.components(Player.class);
	}

	public static Board board() {
	    return board;
	}

	public static Dices dices() {
	    return dices;
	}

	public static LuckyCards luckyCards() {
	    return luckyCards;
	}

	public static CommunityCards communityCards() {
	    return communityCards;
	}

	public static List<Player> playerList() {
	    return playerList;
	}

	public static Player player(int index) {
	    return playerList().get(index);
	}

}