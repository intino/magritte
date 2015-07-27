package tafat;


import tafat.natives.Check;
import tara.magritte.Morph;
import tara.magritte.NativeCode;
import tara.magritte.Node;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Action extends Morph {

    protected Check check;
    protected tafat.natives.Action action;

    public Action(Node node) {
        super(node);
        _set("check", new Check_meme());
    }

    public Action(Morph morph, Node node) {
        super(morph, node);
        _set("check", ((Action) morph).check);
        _set("action", ((Action) morph).action);
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
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("check", check);
        map.put("action", action);
        return map;
    }

    @Override
    protected void _add(Node component) {
    }

    @Override
    protected void _set(String name, Object object) {
        if (name.equalsIgnoreCase("check")) check = (Check) _link((NativeCode) object);
        else if (name.equalsIgnoreCase("action")) action = (tafat.natives.Action) _link((NativeCode) object);
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
