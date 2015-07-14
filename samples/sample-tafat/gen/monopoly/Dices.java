package monopoly;

import monopoly.natives.Doubles;
import monopoly.natives.Roll;
import monopoly.natives.Value;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dices extends Morph {

	protected int value1;
	protected int value2;
	protected Roll roll;
	protected Doubles doubles;
	protected Value value;

	public Dices(Node node) {
		super(node);
		set("value1", 0);
		set("value2", 0);
		set("roll", new roll_meme());
		set("doubles", new doubles_meme());
		set("value", new value_meme());
	}

	public Dices(Morph morph, Node node) {
		super(morph, node);
		set("value1", ((Dices) morph).value1);
		set("value2", ((Dices) morph).value2);
		set("roll", ((Dices) morph).roll);
		set("doubles", ((Dices)morph).doubles);
		set("value", ((Dices) morph).value);
	}

	public int value1() {
		return value1;
	}

	public void value1(int value) {
		value1 = value;
	}

	public int value2() {
		return value2;
	}

	public void value2(int value) {
		value2 = value;
	}

	public void roll() {
		roll.roll();
	}

	public void roll(Roll value) {
		roll = value;
	}

	public boolean doubles() {
		return doubles.check();
	}

	public void doubles(Doubles value) {
		doubles = value;
	}

	public int value() {
		return value.value();
	}

	public void value(Value value) {
		this.value = value;
	}

	@Override
	protected void add(Node component) {
	}

	@Override
	protected void set(String name, Object object) {
		if (name.equalsIgnoreCase("value1")) value1 = (int) object;
		else if (name.equalsIgnoreCase("value2")) value2 = (int) object;
		else if (name.equalsIgnoreCase("roll")) roll = (Roll) link((NativeCode) object);
		else if (name.equalsIgnoreCase("doubles")) doubles = (Doubles) link((NativeCode) object);
		else if (name.equalsIgnoreCase("value")) value = (Value) link((NativeCode) object);
	}

	@Override
	public List<Node> components() {
		return Collections.emptyList();
	}

	public static class roll_meme implements Roll, NativeCode {
		Dices $;

		@Override
		public void roll() {
			$.value1(new Random().nextInt(6) + 1);
			$.value2(new Random().nextInt(6) + 1);
		}

		@Override
		public void set(Morph context) {
			$ = (Dices) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Dices.class;
		}
	}

	public static class doubles_meme implements Doubles, NativeCode {
		Dices $;

		@Override
		public boolean check() {
			return $.value1() == $.value2();
		}

		@Override
		public void set(Morph context) {
			$ = (Dices) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Dices.class;
		}
	}

	public static class value_meme implements Value, NativeCode {
		Dices $;

		@Override
		public int value() {
			return $.value1() + $.value2();
		}

		@Override
		public void set(Morph context) {
			$ = (Dices) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Dices.class;
		}
	}
}
