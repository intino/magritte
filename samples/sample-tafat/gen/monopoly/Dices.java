package monopoly;

import monopoly.natives.Doubles;
import monopoly.natives.Roll;
import monopoly.natives.Value;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.*;

public class Dices extends Morph {

	protected int value1;
	protected int value2;
	protected Roll roll;
	protected Doubles doubles;
	protected Value value;

	public Dices(Node node) {
		super(node);
		_set("value1", 0);
		_set("value2", 0);
		_set("roll", new roll_meme());
		_set("doubles", new doubles_meme());
		_set("value", new value_meme());
	}

	public Dices(Morph morph, Node node) {
		super(morph, node);
		_set("value1", ((Dices) morph).value1);
		_set("value2", ((Dices) morph).value2);
		_set("roll", ((Dices) morph).roll);
		_set("doubles", ((Dices) morph).doubles);
		_set("value", ((Dices) morph).value);
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
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("value1", value1);
        map.put("value2", value2);
        map.put("roll", roll);
        map.put("doubles", doubles);
        map.put("value", value);
        return map;
    }

	@Override
	protected void _add(Node component) {
	}

	@Override
	protected void _set(String name, Object object) {
		if (name.equalsIgnoreCase("value1")) value1 = (int) object;
		else if (name.equalsIgnoreCase("value2")) value2 = (int) object;
		else if (name.equalsIgnoreCase("roll")) roll = (Roll) _link((NativeCode) object);
		else if (name.equalsIgnoreCase("doubles")) doubles = (Doubles) _link((NativeCode) object);
		else if (name.equalsIgnoreCase("value")) value = (Value) _link((NativeCode) object);
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
