package magritte.ontology;

import magritte.dsl.TafatDsl;
import monopoly.*;
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
		registerTypes();
		def("Mover_Player").has(21).has(22).has("Mover_Player$PlayerIsJailed").has("Mover_Player$JailAfterThreeDoubles").has("Mover_Player$Advance").has("Mover_Player$ToJailWhenInGoToJailSquare").has("Mover_Player$CheckCard").has("Mover_Player$Doubles").set("turnsToBeInJail", (0)).set("numberOfRolls", (0));
		proto(21).type("Behavior$Start").set("start", new action_start());
		proto(22).type("Action").set("action", new action_mecni());
		proto("Mover_Player$PlayerIsJailed").type("Behavior$Knol").type("Mover_Player$Rule").set("check", new check_drussatjet());
		proto("Mover_Player$JailAfterThreeDoubles").type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_plolfrumus());
		proto("Mover_Player$Advance").type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_psilapsimin());
		proto("Mover_Player$ToJailWhenInGoToJailSquare").type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_brutredras());
		proto("Mover_Player$CheckCard").type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_nunpse());
		proto("Mover_Player$Doubles").type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_natoc()).end();
	}

	private void registerTypes() {
		register("dices", Dices.class);
		register("board", Board.class);
		register("init", Init.class);
		register("terrain", Terrain.class);
		register("community", Community.class);
		register("taxes", Taxes.class);
		register("station", Station.class);
		register("luck", Luck.class);
		register("jail", Jail.class);
		register("company", Company.class);
		register("freeparking", FreeParking.class);
		register("gotojail", GoToJail.class);
		register("luckycards", LuckyCards.class);
		register("card", Card.class);
		register("communitycards", CommunityCards.class);
		register("player", Player.class);
		register("mover_player", Mover_Player.class);
		register("behavior", Behavior.class);
		register("mover_player$playerisjailed", Mover_Player.PlayerIsJailed.class);
		register("mover_player$jailafterthreedoubles", Mover_Player.JailAfterThreeDoubles.class);
		register("mover_player$advance", Mover_Player.Advance.class);
		register("mover_player$tojailwheningotojailsquare", Mover_Player.ToJailWhenInGoToJailSquare.class);
		register("mover_player$checkcard", Mover_Player.CheckCard.class);
		register("mover_player$doubles", Mover_Player.Doubles.class);
		registerAbstract("square");
		registerAbstract("mover_player$rule");
		registerAbstract("cards");
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