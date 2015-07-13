package monopoly.tafat;


import wata._magritte.lite.Morph;
import wata._magritte.lite.Node;
import wata._magritte.lite.Patch;
import monopoly.tafat.natives.Check;

public class Action extends wata._magritte.lite.Morph {

    protected Check check = () -> true;
    protected monopoly.tafat.natives.Action action;

    public Action(Node node) {
        super(node);
    }

    public Action(Morph morph, Node node) {
        super(morph, node);
        set("check",((Action)morph).check);
        set("action", ((Action)morph).action);
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
        if(name.equalsIgnoreCase("check"))
            check = (Check) (object instanceof Patch ? link((Patch) object) : object);
        if(name.equalsIgnoreCase("action"))
            action = (monopoly.tafat.natives.Action) (object instanceof Patch ? link((Patch) object) : object);
    }
}
