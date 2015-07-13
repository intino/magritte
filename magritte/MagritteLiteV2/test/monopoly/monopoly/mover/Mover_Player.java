package monopoly.monopoly.mover;

import monopoly.monopoly.Player;
import monopoly.monopoly.Square;
import monopoly.monopoly.natives.Check;
import wata._magritte.lite.Morph;
import wata._magritte.lite.NativeCode;
import wata._magritte.lite.Node;

import java.util.ArrayList;
import java.util.List;


public class Mover_Player extends Mover {

	protected Player player;
	protected int turnsToBeInJail = 0;
	protected int numberOfRolls = 0;
	protected ArrayList<Rule> ruleSet = new ArrayList<>(0);

	public Mover_Player(Node node) {
		super(node);
		player = node.morph(Player.class);
	}

	public Square square() {
		return player.square();
	}

	public void square(Square value) {
		player.square(value);
	}

	public int turnsToBeInJail() {
		return turnsToBeInJail;
	}

	public int numberOfRolls() {
		return numberOfRolls;
	}

	public void turnsToBeInJail(int value) {
		turnsToBeInJail = value;
	}

//    public void turnsToBeInJail(Expression<Integer> value) {
//        node.set("turnsToBeInJail", value);
//    }

	public void numberOfRolls(int value) {
		numberOfRolls = value;
	}

//    public void numberOfRolls(Expression<Integer> value) {
//        node.set("numberOfRolls", value);
//    }

	public List<Rule> ruleSet() {
		return ruleSet;
	}

	public Rule rule(int index) {
		return ruleSet().get(index);
	}

	@Override
	protected void add(Node component) {
		if (component.is(Rule.class)) ruleSet.add(component.morph(Rule.class));
	}

	@Override
	protected void set(String name, Object object) {
		// TODO como no se setean no est� hecho
	}

	public static abstract class Rule extends Morph {

		public Rule(Node node) {
			super(node);
		}

		public Rule(Morph morph, Node node) {
			super(morph, node);
		}

		public abstract boolean check();

		public abstract void check(Check value);

	}

	public static class PlayerIsJailed extends Rule {

		protected Check check;

		public PlayerIsJailed(Node node) {
			super(node);
		}

		public PlayerIsJailed(Morph morph, Node node) {
			super(morph, node);
			set("check", ((PlayerIsJailed) morph).check);
		}

		@Override
		protected void add(Node component) {
		}

		@Override
		protected void set(String name, Object object) {
			if (name.equalsIgnoreCase("check"))
				check = (Check) (object instanceof NativeCode ? link((NativeCode) object) : object);
		}

		public boolean check() {
			return check.check();
		}

		public void check(Check value) {
			check = value;
		}
	}

	public static class JailAfterThreeDoubles extends Rule {
		protected Check check;

		public JailAfterThreeDoubles(Node node) {
			super(node);
		}

		public JailAfterThreeDoubles(Morph morph, Node node) {
			super(morph, node);
			set("check", ((JailAfterThreeDoubles) morph).check);
		}

		@Override
		protected void add(Node component) {
		}

		@Override
		protected void set(String name, Object object) {
			if (name.equalsIgnoreCase("check"))
				check = (Check) (object instanceof NativeCode ? link((NativeCode) object) : object);
		}

		public boolean check() {
			return check.check();
		}

		public void check(Check value) {
			check = value;
		}
	}

	public static class Advance extends Rule {
		protected Check check;

		public Advance(Node node) {
			super(node);
		}

		public Advance(Morph morph, Node node) {
			super(morph, node);
			set("check", ((Advance) morph).check);
		}

		@Override
		protected void add(Node component) {
		}

		@Override
		protected void set(String name, Object object) {
			if (name.equalsIgnoreCase("check"))
				check = (Check) (object instanceof NativeCode ? link((NativeCode) object) : object);
		}

		public boolean check() {
			return check.check();
		}

		public void check(Check value) {
			check = value;
		}
	}

	public static class ToJailWhenInGoToJailSquare extends Rule {
		protected Check check;

		public ToJailWhenInGoToJailSquare(Node node) {
			super(node);
		}

		public ToJailWhenInGoToJailSquare(Morph morph, Node node) {
			super(morph, node);
			set("check", ((ToJailWhenInGoToJailSquare) morph).check);
		}

		@Override
		protected void add(Node component) {
		}

		@Override
		protected void set(String name, Object object) {
			if (name.equalsIgnoreCase("check"))
				check = (Check) (object instanceof NativeCode ? link((NativeCode) object) : object);
		}

		public boolean check() {
			return check.check();
		}

		public void check(Check value) {
			check = value;
		}
	}

	public static class CheckCard extends Rule {
		protected Check check;

		public CheckCard(Node node) {
			super(node);
		}

		public CheckCard(Morph morph, Node node) {
			super(morph, node);
			set("check", ((CheckCard) morph).check);
		}

		@Override
		protected void add(Node component) {
		}

		@Override
		protected void set(String name, Object object) {
			if (name.equalsIgnoreCase("check"))
				check = (Check) (object instanceof NativeCode ? link((NativeCode) object) : object);
		}

		public boolean check() {
			return check.check();
		}

		public void check(Check value) {
			check = value;
		}
	}

	public static class Doubles extends Rule {
		protected Check check;

		public Doubles(Node node) {
			super(node);
		}

		public Doubles(Morph morph, Node node) {
			super(morph, node);
			set("check", ((Doubles) morph).check);
		}

		@Override
		protected void add(Node component) {
		}

		@Override
		protected void set(String name, Object object) {
			if (name.equalsIgnoreCase("check"))
				check = (Check) (object instanceof NativeCode ? link((NativeCode) object) : object);
		}

		public boolean check() {
			return check.check();
		}

		public void check(Check value) {
			check = value;
		}
	}
}
