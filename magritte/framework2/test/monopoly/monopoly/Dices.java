package monopoly.monopoly;

import monopoly.monopoly.natives.Doubles;
import monopoly.monopoly.natives.Roll;
import monopoly.monopoly.natives.Value;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.Random;

public class Dices extends Morph {

	protected int value1 = 0;
	protected int value2 = 0;
	protected Roll roll = new roll_meme();
	protected Doubles doubles = new doubles_meme();
	protected Value value = new value_meme();

	public Dices(Node node) {
		super(node);
	}

	public int value1() {
		return value1;
	}

	public int value2() {
		return value2;
	}

	public void roll() {
		roll.roll();
	}

	public boolean doubles() {
		return doubles.check();
	}

	public int value() {
		return value.value();
	}

	public void value1(int value) {
		value1 = value;
	}

//    public void value1(Expression<Integer> value) {
//        node.set("value1", value);
//    }

	public void value2(int value) {
		value2 = value;
	}

//    public void value2(Expression<Integer> value) {
//        node.set("value2", value);
//    }

	public void roll(Roll value) {
		roll = value;
	}

	public void doubles(Doubles value) {
		doubles = value;
	}

	public void value(Value value) {
		this.value = value;
	}

	@Override
	protected void add(Node component) {
	}

	@Override
	protected void set(String name, Object object) {
		// TODO como no se setean no est� hecho
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

		}

		@Override
		public Class<? extends Morph> $Class() {
			return null;
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

		}

		@Override
		public Class<? extends Morph> $Class() {
			return null;
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

		}

		@Override
		public Class<? extends Morph> $Class() {
			return null;
		}
	}
}
