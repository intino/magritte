package monopoly.boxes;

import tara.magritte.Box;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PlayGameMain extends Box {
	public static Box box = new PlayGameMain();

	@Override
	public List<Box> dependencies() {
		return MonopolyDsl.boxes;
	}

	@Override
	public void write() {
		thing(1).root().type("Simulation").set("from", (asDate("01/01/2015 00:00:00"))).set("to", (asDate("02/01/2015 00:00:00")));
		thing(2).type("Dices").root();
		thing("board").type("Board").root().has("board.s0").has("board.s1").has("board.s2").has("board.s3").has("board.s4").has("board.s5").has("board.s6").has("board.s7").has("board.s8").has("board.s9").has("board.s10").has("board.s11").has("board.s12").has("board.s13").has("board.s14").has("board.s15").has("board.s16").has("board.s17").has("board.s18").has("board.s19").has("board.s20").has("board.s21").has("board.s22").has("board.s23").has("board.s24").has("board.s25").has("board.s26").has("board.s27").has("board.s28").has("board.s29").has("board.s30").has("board.s31").has("board.s32").has("board.s33").has("board.s34").has("board.s35").has("board.s36").has("board.s37").has("board.s38").has("board.s39");
		thing("board.s0").type("Init").type("Square");
		thing("board.s1").type("Terrain").type("Square");
		thing("board.s2").type("Community").type("Square");
		thing("board.s3").type("Terrain").type("Square");
		thing("board.s4").type("Taxes").type("Square");
		thing("board.s5").type("Station").type("Square");
		thing("board.s6").type("Terrain").type("Square");
		thing("board.s7").type("Luck").type("Square");
		thing("board.s8").type("Terrain").type("Square");
		thing("board.s9").type("Terrain").type("Square");
		thing("board.s10").type("Jail").type("Square");
		thing("board.s11").type("Terrain").type("Square");
		thing("board.s12").type("Company").type("Square");
		thing("board.s13").type("Terrain").type("Square");
		thing("board.s14").type("Terrain").type("Square");
		thing("board.s15").type("Station").type("Square");
		thing("board.s16").type("Terrain").type("Square");
		thing("board.s17").type("Community").type("Square");
		thing("board.s18").type("Terrain").type("Square");
		thing("board.s19").type("Terrain").type("Square");
		thing("board.s20").type("FreeParking").type("Square");
		thing("board.s21").type("Terrain").type("Square");
		thing("board.s22").type("Luck").type("Square");
		thing("board.s23").type("Terrain").type("Square");
		thing("board.s24").type("Terrain").type("Square");
		thing("board.s25").type("Station").type("Square");
		thing("board.s26").type("Terrain").type("Square");
		thing("board.s27").type("Terrain").type("Square");
		thing("board.s28").type("Company").type("Square");
		thing("board.s29").type("Terrain").type("Square");
		thing("board.s30").type("GoToJail").type("Square");
		thing("board.s31").type("Terrain").type("Square");
		thing("board.s32").type("Terrain").type("Square");
		thing("board.s33").type("Community").type("Square");
		thing("board.s34").type("Terrain").type("Square");
		thing("board.s35").type("Station").type("Square");
		thing("board.s36").type("Luck").type("Square");
		thing("board.s37").type("Terrain").type("Square");
		thing("board.s38").type("Taxes").type("Square");
		thing("board.s39").type("Terrain").type("Square");
		thing(44).type("LuckyCards").root().has(45).has(46).has(47).has(48).has(49).has(50).has(51).has(52).has(53).has(54).has(55).has(56).has(57).has(58).has(59).has(60);
		thing(45).type("Card");
		thing(46).type("Card");
		thing(47).type("Card").set("moveTo", (24));
		thing(48).type("Card").set("moveTo", (0));
		thing(49).type("Card");
		thing(50).type("Card").set("moveTo", (39));
		thing(51).type("Card");
		thing(52).type("Card");
		thing(53).type("Card");
		thing(54).type("Card").set("moveTo", (11));
		thing(55).type("Card").set("moveTo", (15));
		thing(56).type("Card");
		thing(57).type("Card").set("moveTo", (10));
		thing(58).type("Card").set("moveTo", (-3));
		thing(59).type("Card");
		thing(60).type("Card");
		thing(61).type("CommunityCards").root().has(62).has(63).has(64).has(65).has(66).has(67).has(68).has(69).has(70).has(71).has(72).has(73).has(74).has(75).has(76).has(77);
		thing(62).type("Card");
		thing(63).type("Card");
		thing(64).type("Card");
		thing(65).type("Card");
		thing(66).type("Card");
		thing(67).type("Card");
		thing(68).type("Card");
		thing(69).type("Card");
		thing(70).type("Card").set("moveTo", (0));
		thing(71).type("Card").set("moveTo", (1));
		thing(72).type("Card");
		thing(73).type("Card");
		thing(74).type("Card");
		thing(75).type("Card");
		thing(76).type("Card").set("moveTo", (10));
		thing(77).type("Card");
		thing(79).type("Player").type("Mover+Player").root().set("id", ("p2"));
		thing(80).type("Player").type("Mover+Player").root().set("id", ("p3"));
		thing(81).type("Player").type("Mover+Player").root().set("id", ("p4"));
	}

	private LocalDateTime asDate(String date) {
		return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}