package monopoly.boxes;

import monopoly.monopoly.*;
import monopoly.monopoly.mover.Mover_Player;
import monopoly.monopoly.natives.Check;
import monopoly.tafat.Behavior;
import monopoly.tafat.natives.Action;
import siani.tara.magritte.Box;
import siani.tara.magritte.Morph;
import siani.tara.magritte.NativeCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static siani.tara.magritte.MorphFactory.register;
import static siani.tara.magritte.MorphFactory.registerAbstract;


public class MonopolyMain extends Box {
	public static final Box box = new MonopolyMain();

	@Override
	public List<Box> dependencies() {
		return Collections.singletonList(TafatMain.box);
	}

	@Override
	public void write() {
		registerTypes();
		def("Mover+Player").has(21).has(22).has("Mover+Player$PlayerIsJailed").has("Mover+Player$JailAfterThreeDoubles").has("Mover+Player$Advance").has("Mover+Player$ToJailWhenInGoToJailSquare").has("Mover+Player$CheckCard").has("Mover+Player$Doubles").set("turnsToBeInJail", (0)).set("numberOfRolls", (0));
		proto(21).type("Behavior$Start").set("start", new fake());
		proto(22).type("Action").set("action", new action_mecni());
		proto("Mover_Player$PlayerIsJailed").type("Behavior$Knol").type("Mover+Player$Rule").set("check", new check_drussatjet());
		proto("Mover_Player$JailAfterThreeDoubles").type("Mover+Player$Rule").type("Behavior$Knol").set("check", new check_plolfrumus());
		proto("Mover_Player$Advance").type("Mover+Player$Rule").type("Behavior$Knol").set("check", new check_psilapsimin());
		proto("Mover_Player$ToJailWhenInGoToJailSquare").type("Mover_Player$Rule").type("Behavior$Knol").set("check", new check_brutredras());
		proto("Mover_Player$CheckCard").type("Mover+Player$Rule").type("Behavior$Knol").set("check", new check_nunpse());
		proto("Mover_Player$Doubles").type("Mover+Player$Rule").type("Behavior$Knol").set("check", new check_natoc()).end();
		
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

	public static class fake implements Action, NativeCode {

		Mover_Player $;

		@Override
		public void execute() {
			$.square(Monopoly.model.board().squareAt(0));
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
				Monopoly.model.dices().roll();
				$.numberOfRolls($.numberOfRolls() + 1);
				for (Mover_Player.Rule rule : $.ruleSet())
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
				if(Monopoly.model.dices().doubles()) $.turnsToBeInJail(0);
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
			if ($.numberOfRolls() == 3 && Monopoly.model.dices().doubles()) {
				$.turnsToBeInJail(3);
				$.square(Monopoly.model.board().squareOf(Jail.class));
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
			Monopoly monopoly = Monopoly.model;
			$.square(monopoly.board().squareAt(monopoly.board().positionOf($.square()) + monopoly.dices().value()));
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
			if ($.square().is(GoToJail.class)) {
				$.turnsToBeInJail(3);
				$.square(Monopoly.model.board().squareOf(Jail.class));
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
			Monopoly monopoly = Monopoly.model;
			if ($.square().is(Luck.class))
				card = monopoly.luckyCards().get();
			if ($.square().is(Community.class))
				card = monopoly.communityCards().get();

			if (card == null || !card.transport()) return true;

			if (monopoly.board().squareAt(card.moveTo()).is(Jail.class)) {
				$.turnsToBeInJail(3);
				$.square(monopoly.board().squareOf(Jail.class));
				$.square().increment();
				return false;
			}

			if (card.moveTo() >= 0) {
				$.square(monopoly.board().squareAt(card.moveTo()));
				$.square().increment();
				return true;
			}

			if (card.moveTo() == -3) {
				$.square(monopoly.board().squareAt(monopoly.board().positionOf($.square()) + card.moveTo()));
				$.square().increment();
				if ($.square().is(GoToJail.class)) {
					$.turnsToBeInJail(3);
					$.square(monopoly.board().squareOf(Jail.class));
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
			return Monopoly.model.dices().doubles();
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