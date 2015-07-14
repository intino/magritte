package tafat;


import tafat.natives.Check;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;

public class Action extends Morph {

	protected Check check;
	protected tafat.natives.Action action;

	public Action(Node node) {
		super(node);
        set("check", new Check_meme());
    }

	public Action(Morph morph, Node node) {
		super(morph, node);
        set("check", ((Action) morph).check);
		set("action", ((Action) morph).action);
	}

	public boolean condition() {
		return check.check();
	}

    public void condition(Check value) {
        check = value;
    }

    public void action() {
        action.execute();
    }

	public void action(tafat.natives.Action value) {
		action = value;
	}

	@Override
	protected void add(Node component) {
	}

	@Override
	protected void set(String name, Object object) {
		if (name.equalsIgnoreCase("check")) check = (Check) link((NativeCode) object);
		else if (name.equalsIgnoreCase("action")) action = (tafat.natives.Action) link((NativeCode) object);
	}

	@Override
	public List<Node> components() {
		return Collections.emptyList();
	}

	public static class Check_meme implements Check, NativeCode {
		Action $;

		@Override
		public boolean check() {
			return true;
		}

		@Override
		public void set(Morph context) {
            $ = (Action) context;
		}

		@Override
		public Class<? extends Morph> $Class() {
			return Action.class;
		}
	}
}
