package magritte.ontology;

import magritte.dsl.TafatDsl;
import monopoly.*;
import monopoly.jailscape.JailScape;
import monopoly.jailscape.JailScape_Player;
import monopoly.mover.Mover;
import monopoly.mover.Mover_Player;
import monopoly.natives.Check;
import tafat.Behavior;
import tafat.natives.Action;
import tara.magritte.Box;
import tara.magritte.Morph;
import tara.magritte.NativeCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static tara.magritte.MorphFactory.register;
import static tara.magritte.MorphFactory.registerAbstract;


public class MonopolyMain extends Box {
	public static final Box box = new MonopolyMain();

	@Override
	public List<Box> dependencies() {
		return TafatDsl.boxes;
	}

	@Override
	public void write() {
        def("Board", Board.class).type("Entity").allowsMultiple("Square").end();
        def("Dices", Dices.class).type("Entity").end();
        abstractDef("Cards", Cards.class).type("Entity").allowsMultiple("Card").end();
        def("LuckyCards", LuckyCards.class).type("Entity").type("Cards").allowsMultiple("Card").end();
        def("CommunityCards", CommunityCards.class).type("Entity").type("Cards").allowsMultiple("Card").end();
        def("Card", Card.class).type("Entity").end();
        abstractDef("Square", Square.class).type("Entity").end();
        def("Init", Init.class).type("Entity").type("Square").end();
        def("Terrain", Terrain.class).type("Entity").type("Square").end();
        def("Community", Community.class).type("Entity").type("Square").end();
        def("Taxes", Taxes.class).type("Entity").type("Square").end();
        def("Station", Station.class).type("Entity").type("Square").end();
        def("Luck", Luck.class).type("Entity").type("Square").end();
        def("Jail", Jail.class).type("Entity").type("Square").end();
        def("Company", Company.class).type("Entity").type("Square").end();
        def("FreeParking", FreeParking.class).type("Entity").type("Square").end();
        def("GoToJail", GoToJail.class).type("Entity").type("Square").end();
        def("Player", Player.class).type("Entity").end();
        abstractDef("JailScape", JailScape.class).type("Behavior").end();
        def("JailScape_Player", JailScape_Player.class).type("Behavior").type("JailScape").end();
        abstractDef("Mover", Mover.class).type("Behavior").end();
		def("Mover_Player", Mover_Player.class).type("Mover").has(21).has(22).has("Mover_Player$PlayerIsJailed").has("Mover_Player$JailAfterThreeDoubles").has("Mover_Player$Advance").has("Mover_Player$ToJailWhenInGoToJailSquare").has("Mover_Player$CheckCard").has("Mover_Player$Doubles").end();
		proto(21).type("Behavior$Start").set("start", new action_start()).end();
		proto(22).type("Action").set("action", new action_mecni()).end();
        abstractDef("Mover_Player$Rule", Mover_Player.Rule.class).type("Behavior$Knol").end();
		proto("Mover_Player$PlayerIsJailed", Mover_Player.PlayerIsJailed.class).type("Behavior$Knol").type("Mover_Player$Rule").set("check", new check_drussatjet()).end();
		proto("Mover_Player$JailAfterThreeDoubles", Mover_Player.JailAfterThreeDoubles.class).type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_plolfrumus()).end();
		proto("Mover_Player$Advance", Mover_Player.Advance.class).type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_psilapsimin()).end();
		proto("Mover_Player$ToJailWhenInGoToJailSquare", Mover_Player.ToJailWhenInGoToJailSquare.class).type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_brutredras()).end();
		proto("Mover_Player$CheckCard", Mover_Player.CheckCard.class).type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_nunpse()).end();
		proto("Mover_Player$Doubles", Mover_Player.Doubles.class).type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_natoc()).end();
	}

	private LocalDateTime asDate(String date){
		return LocalDateTime.from(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").parse(date));
	}

	public static class action_start implements Action, NativeCode {

		Mover_Player $;

		@Override
		public void execute() {
			$.square(Monopoly.board().squareAt(0));
		}

		@Override
		public void set(Morph context) {
			$ = (Mover_Player) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Mover_Player.class;
		}
	}

	public static class action_mecni implements Action, NativeCode {

		Mover_Player $;

		@Override
		public void execute() {
			$.numberOfRolls(0);
			$.turnsToBeInJail($.turnsToBeInJail() - 1);
			do {
				Monopoly.dices().roll();
				$.numberOfRolls($.numberOfRolls() + 1);
				for (Mover_Player.Rule rule : $.ruleList())
					if (!rule.check()) return;
			} while (true);
		}

		@Override
		public void set(Morph context) {
			$ = (Mover_Player) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Mover_Player.class;
		}
	}

	public static class check_drussatjet implements Check, NativeCode {
		Mover_Player $;

		@Override
		public boolean check() {
			if($.turnsToBeInJail() > 0)
				if(Monopoly.dices().doubles()) $.turnsToBeInJail(0);
				else return false;
			return true;
		}


		@Override
		public void set(Morph context) {
			$ = (Mover_Player) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Mover_Player.class;
		}

	}

	public static class check_plolfrumus implements Check, NativeCode {
		Mover_Player $;

		@Override
		public boolean check() {
			if ($.numberOfRolls() == 3 && Monopoly.dices().doubles()) {
				$.turnsToBeInJail(3);
				$.square(Monopoly.board().squareOf("Jail"));
				$.square().increment();
				return false;
			}
			return true;
		}


		@Override
		public void set(Morph context) {
			$ = (Mover_Player) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Mover_Player.class;
		}

	}

	public static class check_psilapsimin implements Check, NativeCode {
		Mover_Player $;

		@Override
		public boolean check() {
			$.square(Monopoly.board().squareAt(Monopoly.board().positionOf($.square()) + Monopoly.dices().value()));
			$.square().increment();
			return true;
		}


		@Override
		public void set(Morph context) {
			$ = (Mover_Player) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Mover_Player.class;
		}

	}

	public static class check_brutredras implements Check, NativeCode {

		Mover_Player $;

		@Override
		public boolean check() {
			if ($.square().is("GoToJail")) {
				$.turnsToBeInJail(3);
				$.square(Monopoly.board().squareOf("Jail"));
				$.square().increment();
				return false;
			}
			return true;
		}


		@Override
		public void set(Morph context) {
			$ = (Mover_Player) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Mover_Player.class;
		}
	}

	public static class check_nunpse implements Check, NativeCode {

		Mover_Player $;

		@Override
		public boolean check() {
			Card card = null;
			if ($.square().is("Luck"))
				card = Monopoly.luckyCards().get();
			if ($.square().is("Community"))
				card = Monopoly.communityCards().get();

			if (card == null || !card.transport()) return true;

			if (Monopoly.board().squareAt(card.moveTo()).is("Jail")) {
				$.turnsToBeInJail(3);
				$.square(Monopoly.board().squareOf("Jail"));
				$.square().increment();
				return false;
			}

			if (card.moveTo() >= 0) {
				$.square(Monopoly.board().squareAt(card.moveTo()));
				$.square().increment();
				return true;
			}

			if (card.moveTo() == -3) {
				$.square(Monopoly.board().squareAt(Monopoly.board().positionOf($.square()) + card.moveTo()));
				$.square().increment();
				if ($.square().is("GoToJail")) {
					$.turnsToBeInJail(3);
					$.square(Monopoly.board().squareOf("Jail"));
					$.square().increment();
					return false;
				}
				return true;
			}
			return true;
		}

		@Override
		public void set(Morph context) {
			$ = (Mover_Player) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Mover_Player.class;
		}

	}

	public static class check_natoc implements Check, NativeCode {
		Mover_Player $;

		@Override
		public boolean check() {
			return Monopoly.dices().doubles();
		}


		@Override
		public void set(Morph context) {
			$ = (Mover_Player) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Mover_Player.class;
		}
	}
}