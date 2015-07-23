package magritte.ontology.m0;

import magritte.dsl.MonopolyDsl;
import tara.magritte.Box;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PlayGameMain extends Box {

	@Override
	public List<Box> dependencies() {
		return MonopolyDsl.boxes;
	}

	@Override
	public void write() {
		thing(1).root().type("Simulation").set("from", (asDate("01/01/2015 00:00:00"))).set("to", (asDate("02/01/2015 00:00:00"))).end();
		thing(2).type("Dices").root().end();
		thing("board").type("Board").root().has("board.s0").has("board.s1").has("board.s2").has("board.s3").has("board.s4").has("board.s5").has("board.s6").has("board.s7").has("board.s8").has("board.s9").has("board.s10").has("board.s11").has("board.s12").has("board.s13").has("board.s14").has("board.s15").has("board.s16").has("board.s17").has("board.s18").has("board.s19").has("board.s20").has("board.s21").has("board.s22").has("board.s23").has("board.s24").has("board.s25").has("board.s26").has("board.s27").has("board.s28").has("board.s29").has("board.s30").has("board.s31").has("board.s32").has("board.s33").has("board.s34").has("board.s35").has("board.s36").has("board.s37").has("board.s38").has("board.s39").end();
		thing("board.s0").type("Init").end();
		thing("board.s1").type("Terrain").end();
		thing("board.s2").type("Community").end();
		thing("board.s3").type("Terrain").end();
		thing("board.s4").type("Taxes").end();
		thing("board.s5").type("Station").end();
		thing("board.s6").type("Terrain").end();
		thing("board.s7").type("Luck").end();
		thing("board.s8").type("Terrain").end();
		thing("board.s9").type("Terrain").end();
		thing("board.s10").type("Jail").end();
		thing("board.s11").type("Terrain").end();
		thing("board.s12").type("Company").end();
		thing("board.s13").type("Terrain").end();
		thing("board.s14").type("Terrain").end();
		thing("board.s15").type("Station").end();
		thing("board.s16").type("Terrain").end();
		thing("board.s17").type("Community").end();
		thing("board.s18").type("Terrain").end();
		thing("board.s19").type("Terrain").end();
		thing("board.s20").type("FreeParking").end();
		thing("board.s21").type("Terrain").end();
		thing("board.s22").type("Luck").end();
		thing("board.s23").type("Terrain").end();
		thing("board.s24").type("Terrain").end();
		thing("board.s25").type("Station").end();
		thing("board.s26").type("Terrain").end();
		thing("board.s27").type("Terrain").end();
		thing("board.s28").type("Company").end();
		thing("board.s29").type("Terrain").end();
		thing("board.s30").type("GoToJail").end();
		thing("board.s31").type("Terrain").end();
		thing("board.s32").type("Terrain").end();
		thing("board.s33").type("Community").end();
		thing("board.s34").type("Terrain").end();
		thing("board.s35").type("Station").end();
		thing("board.s36").type("Luck").end();
		thing("board.s37").type("Terrain").end();
		thing("board.s38").type("Taxes").end();
		thing("board.s39").type("Terrain").end();
		thing(44).type("LuckyCards").root().has(45).has(46).has(47).has(48).has(49).has(50).has(51).has(52).has(53).has(54).has(55).has(56).has(57).has(58).has(59).has(60).end();
		thing(45).type("Card").end();
		thing(46).type("Card").end();
		thing(47).type("Card").set("moveTo", (24)).end();
		thing(48).type("Card").set("moveTo", (0)).end();
		thing(49).type("Card").end();
		thing(50).type("Card").set("moveTo", (39)).end();
		thing(51).type("Card").end();
		thing(52).type("Card").end();
		thing(53).type("Card").end();
		thing(54).type("Card").set("moveTo", (11)).end();
		thing(55).type("Card").set("moveTo", (15)).end();
		thing(56).type("Card").end();
		thing(57).type("Card").set("moveTo", (10)).end();
		thing(58).type("Card").set("moveTo", (-3)).end();
		thing(59).type("Card").end();
		thing(60).type("Card").end();
		thing(61).type("CommunityCards").root().has(62).has(63).has(64).has(65).has(66).has(67).has(68).has(69).has(70).has(71).has(72).has(73).has(74).has(75).has(76).has(77).end();
		thing(62).type("Card").end();
		thing(63).type("Card").end();
		thing(64).type("Card").end();
		thing(65).type("Card").end();
		thing(66).type("Card").end();
		thing(67).type("Card").end();
		thing(68).type("Card").end();
		thing(69).type("Card").end();
		thing(70).type("Card").set("moveTo", (0)).end();
		thing(71).type("Card").set("moveTo", (1)).end();
		thing(72).type("Card").end();
		thing(73).type("Card").end();
		thing(74).type("Card").end();
		thing(75).type("Card").end();
		thing(76).type("Card").set("moveTo", (10)).end();
		thing(77).type("Card").end();
		for (int i = 0; i < 1e3; i++)
			thing(i + 78).type("Player").type("Mover_Player").root().set("id", ("p" + i)).end();
	}

	private LocalDateTime asDate(String date) {
		return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}