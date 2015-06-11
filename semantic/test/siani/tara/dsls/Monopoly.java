package siani.tara.dsls;

import siani.tara.semantic.model.Tara;

import java.util.Locale;

import static siani.tara.semantic.constraints.RuleFactory.*;

public class Monopoly extends Tara {
	public Monopoly() {
		in("").def(context("").allow(multiple("RecurrentJob"), multiple("Map"), multiple("StartJob"), multiple("Simulation"), multiple("EndJob"), name()).require(oneOf(_single("LuckyCards"), _single("CommunityCards")), _multiple("Player"), _single("Board"), _single("Dices"), _single("LuckyCards"), _single("CommunityCards")));
		in("Board").def(context("Entity", "Concept").allow(multiple("Init"), multiple("Terrain"), multiple("Community"), multiple("Taxes"), multiple("Station"), multiple("Luck"), multiple("Jail"), multiple("Company"), multiple("FreeParking"), multiple("GoToJail"), parameter("squareAt", "native", false, 0, "SquareAt#public Square squareAt(int position)"), parameter("squareOf", "native", false, 1, "SquareOf#public Square squareOf(Class<? extends Square> aClass)"), parameter("positionOf", "native", false, 2, "Position#public int position(Square square)"), name()));
		in("Dices").def(context("Entity", "Concept").allow(parameter("value1", "integer", false, 0, ""), parameter("value2", "integer", false, 1, ""), parameter("roll", "native", false, 2, "Roll#public void execute()"), parameter("doubles", "native", false, 3, "Doubles#public boolean check()"), parameter("v", "native", false, 4, "Value#public int value()"), name()));
		in("LuckyCards").def(context("Entity", "Concept").allow(multiple("Card"), parameter("get", "native", false, 0, "Get#public Card get1()"), name()));
		in("CommunityCards").def(context("Entity", "Concept").allow(multiple("Card"), parameter("get", "native", false, 0, "Get#public Card get1()"), name()));
		in("Card").def(context("Entity", "Concept").allow(parameter("moveTo", "integer", false, 0, ""), parameter("transport", "native", false, 1, "Movement#public boolean involvesMovement()"), name()));
		in("Init").def(context("Entity", "Concept").allow(name()));
		in("Terrain").def(context("Entity", "Concept").allow(name()));
		in("Community").def(context("Entity", "Concept").allow(name()));
		in("Taxes").def(context("Entity", "Concept").allow(name()));
		in("Station").def(context("Entity", "Concept").allow(name()));
		in("Luck").def(context("Entity", "Concept").allow(name()));
		in("Jail").def(context("Entity", "Concept").allow(name()));
		in("Company").def(context("Entity", "Concept").allow(name()));
		in("FreeParking").def(context("Entity", "Concept").allow(name()));
		in("GoToJail").def(context("Entity", "Concept").allow(name()));
		in("Player").def(context("Entity", "Concept").allow(parameter("square", new String[]{"Init", "Terrain", "Community", "Taxes", "Station", "Luck", "Jail", "Company", "FreeParking", "GoToJail"}, false, 1, ""), name(), facet("Mover").allow(parameter("turnsToBeInJail", "integer", false, 0, ""), parameter("numberOfRolls", "integer", false, 1, ""), multiple("DoTurn"), multiple("PlayerIsJailed"), multiple("JailAfterThreeDoubles"), multiple("Advance"), multiple("ToJailWhenInGoToJailSquare"), multiple("CheckCard"), multiple("PlayerIsJailed"), multiple("JailAfterThreeDoubles"), multiple("Advance"), multiple("ToJailWhenInGoToJailSquare"), multiple("CheckCard"), multiple("DicesResult")), facet("JailScape").allow(parameter("modes:word", new String[]{"Card", "Money"}, false, 0, ""))).require(_parameter("id", "string", false, 0, "")));
		in("JailScape").def(context("Behavior", "Concept").allow(parameter("modes:word", new String[]{"Card", "Money"}, false, 0, "")).require(_parameter("step", "integer", false, 0, "", "TERMINAL_INSTANCE"), _name()).assume(isFacetInstance()));
		in("Mover").def(context("Behavior", "Concept").require(_parameter("step", "integer", false, 0, "", "TERMINAL_INSTANCE"), _name()).assume(isFacetInstance()));
		in("DoTurn").def(context("Action", "Concept").allow(name()));
		in("PlayerIsJailed").def(context("Entity", "Concept").allow(parameter("rule", "native", false, 0, "Rule#public boolean apply()"), name()));
		in("JailAfterThreeDoubles").def(context("Entity", "Concept").allow(parameter("rule", "native", false, 0, "Rule#public boolean apply()"), name()));
		in("Advance").def(context("Entity", "Concept").allow(parameter("rule", "native", false, 0, "Rule#public boolean apply()"), name()));
		in("ToJailWhenInGoToJailSquare").def(context("Entity", "Concept").allow(parameter("rule", "native", false, 0, "Rule#public boolean apply()"), name()));
		in("CheckCard").def(context("Entity", "Concept").allow(parameter("rule", "native", false, 0, "Rule#public boolean apply()"), name()));
		in("DicesResult").def(context("Entity", "Concept").allow(name()).require(_parameter("value1", "integer", false, 0, ""), _parameter("value2", "integer", false, 1, "")));
		in("RecurrentJob").def(context("Concept").allow(parameter("job", new String[]{"Job"}, false, 0, ""), name(), parameter("job", new String[]{"Job"}, false, 0, "")).require(_parameter("job", new String[]{"Job"}, false, 0, "")).assume(isTerminalInstance()));
		in("Map").def(context("Concept").allow(multiple("Map.Item"), name()).assume(isTerminalInstance()));
		in("StartJob").def(context("Concept").allow(parameter("job", new String[]{"Job"}, false, 0, ""), name(), parameter("job", new String[]{"Job"}, false, 0, "")).require(_parameter("job", new String[]{"Job"}, false, 0, "")).assume(isTerminalInstance()));
		in("Simulation").def(context("Concept").allow(parameter("from", "date", false, 0, ""), parameter("to", "date", false, 1, ""), name(), parameter("from", "date", false, 0, ""), parameter("to", "date", false, 1, "")).require(_parameter("from", "date", false, 0, ""), _parameter("to", "date", false, 1, "")).assume(isTerminalInstance()));
		in("EndJob").def(context("Concept").allow(parameter("job", new String[]{"Job"}, false, 0, ""), name(), parameter("job", new String[]{"Job"}, false, 0, "")).require(_parameter("job", new String[]{"Job"}, false, 0, "")).assume(isTerminalInstance()));
	}

	@Override
	public String languageName() {
		return "Monopoly";
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

	@Override
	public boolean isTerminalLanguage() {
		return true;
	}
}