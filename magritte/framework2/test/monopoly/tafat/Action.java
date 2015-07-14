package monopoly.tafat;


import monopoly.tafat.natives.Check;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

public class Action extends Morph {

	protected Check check = new Check_meme();
	protected monopoly.tafat.natives.Action action;

	public Action(Node node) {
		super(node);
	}

	public Action(Morph morph, Node node) {
		super(morph, node);
		set("check", ((Action) morph).check);
		set("action", ((Action) morph).action);
	}

	public boolean condition() {
		return check.check();
	}

	public void action() {
		action.execute();
	}

	public void condition(Check value) {
		check = value;
	}

	public void action(monopoly.tafat.natives.Action value) {
		action = value;
	}

	@Override
	protected void add(Node component) {

	}

	@Override
	protected void set(String name, Object object) {
		if (name.equalsIgnoreCase("check"))
			check = (Check) (object instanceof Patch ? link((Patch) object) : object);
		if (name.equalsIgnoreCase("action"))
			action = (monopoly.tafat.natives.Action) (object instanceof Patch ? link((Patch) object) : object);
	}

	public static class Check_meme implements Check, NativeCode {
		Action $;

		@Override
		public boolean check() {
			return true;
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
